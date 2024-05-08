<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import 'vue3-easy-data-table/dist/style.css';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { useCodeStore } from '@/stores/codetrans';
import axios, { setClientHeaders } from '@/utils/axios';
import AttendanceListDetail from '@/views/apps/users/list/AttendanceListDetail.vue';
const codeStore = useCodeStore();
const list = ref<any[]>([]);
const tab = ref(null);
const baseUrl = `${import.meta.env.VITE_API_URL}`;
onMounted(() => {
  fetchStatus();
});
const getStatusCode = (payStatusCode) => {
  return codeStore.getStatusNameByCode(payStatusCode);
};
async function fetchStatus() {
  try {
    setClientHeaders();
    const response = await axios.get(`${baseUrl}/total/annual/read/send`);
    const tempItems = response.data.result;

    // 모든 결재자의 이름을 조회합니다.
    const updatedItems = await Promise.all(
      tempItems.map(async (item) => {
        const firstApprovalName = await employeeIdByName(item.firstApprovalId);
        const secondApprovalName = await employeeIdByName(item.secondApprovalId);
        const thirdApprovalName = await employeeIdByName(item.thirdApprovalId);

        return {
          ...item,
          firstApprovalName: firstApprovalName || 'N/A',
          secondApprovalName: secondApprovalName || 'N/A',
          thirdApprovalName: thirdApprovalName || 'N/A'
        };
      })
    );

    list.value = updatedItems;
  } catch (error) {
    alert('결재 목록 조회에 실패하였습니다.');
    console.log(error);
  }
}
type ListItem = {
  payStatusCode: string;
  firstApprovalId: string;
  secondApprovalId: string;
  thirdApprovalId: string;
  firstApprovalDate: string;
  secondApprovalDate: string;
  thirdApprovalDate: string;
  annualId: number;
  firstApprovalName: string;
  secondApprovalName: string;
  thirdApprovalName: string;
};
const headers: Header[] = [
  { text: '결재 종류', value: 'payStatusCode', sortable: true },
  { text: '1차 결재자', value: 'firstApprovalId', sortable: true },
  { text: '2차 결재자', value: 'secondApprovalId', sortable: true },
  { text: '3차 결재자', value: 'thirdApprovalId', sortable: true }
];
const listCards = computed<ListItem[]>(() => {
  return list.value;
});
const details = ref(false);
const selectAttendance = ref<ListItem | null>(null);
const items = ref(listCards);
const showRow = (item: ListItem) => {
  details.value = true;
  selectAttendance.value = item;
};

// 사원 번호로 이름 변경
async function employeeIdByName(employeeId) {
  try {
    setClientHeaders();
    const response = await axios.get(`${baseUrl}/total/employee/name/${employeeId}`);
    return response.data.result;
  } catch (error) {
    alert('결재 목록 조회에 실패하였습니다.');
    console.log(error);
  }
}
// 삭제
async function attendanceDelete(annualId: number) {
  try {
    setClientHeaders();
    const response = await axios.delete(`${baseUrl}/total/annual/delete/${annualId}`);
    alert('삭제 완료되었습니다.');
    location.href = '/attendance/list/req';
  } catch (error) {
    console.error('삭제 중 오류 발생:', error);
    alert('삭제 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
}
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="전자 결재">
        <v-tabs v-model="tab" color="primary" class="my-2 border-bottom" v-if="!details">
          <v-tab value="ing">진행중</v-tab>
          <v-tab value="finish">완료</v-tab>
        </v-tabs>
        <v-divider></v-divider>
        <!-- 진행중일경우 -->
        <div class="overflow-auto mt-2" v-if="tab === 'ing'">
          <EasyDataTable
            @click-row="showRow"
            :headers="headers"
            :items="items"
            table-class-name="customize-table action-position"
            :rows-per-page="8"
            v-if="!details"
          >
            <!-- 휴가 종류 -->
            <template #item-payStatusCode="{ payStatusCode }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5">
                    {{ getStatusCode(payStatusCode) }}
                  </h5>
                </div>
              </div>
            </template>
            <!-- 1차 승인자 -->
            <template #item-firstApprovalId="{ firstApprovalName, firstApprovalDate}">
              <div class="d-flex align-center ga-4">
                <div v-if="firstApprovalDate !== 'companion'">
                  <h5 class="text-h5" >
                    {{ firstApprovalName }}
                  </h5>
                  <small v-if="firstApprovalDate" class="text-subtitle text-center" style="color: green">{{ firstApprovalDate }} </small>
                  <small v-if="!firstApprovalDate" class="text-subtitle text-center" style="color: blue"> 진행중 </small>
                </div>
              </div>
            </template>
            <template #item-secondApprovalId="{ secondApprovalName, secondApprovalDate, firstApprovalDate ,thirdApprovalDate}">
              <div class="d-flex align-center ga-4">
                <div v-if=" firstApprovalDate && firstApprovalDate !== 'companion' || secondApprovalDate !== 'companion'">
                  <h5 class="text-h5">
                    {{ secondApprovalName }}
                  </h5>
                  <small v-if="secondApprovalDate" class="text-subtitle text-center">{{ secondApprovalDate }} </small>
                  <small v-if="firstApprovalDate && !secondApprovalDate" class="text-subtitle text-center" style="color: blue">
                    진행중
                  </small>
                  <small v-if="!firstApprovalDate && !secondApprovalDate" class="text-subtitle text-center"> </small>
                </div>
              </div>
            </template>
            <template #item-thirdApprovalId="{ thirdApprovalName, thirdApprovalDate, secondApprovalDate}">
              <div class="d-flex align-center ga-4">
                <div v-if="secondApprovalDate && secondApprovalDate !== 'companion' ||  thirdApprovalDate !== 'companion'">
                  <h5 class="text-h5">
                    {{ thirdApprovalName }}
                  </h5>
                  <small v-if="thirdApprovalDate" class="text-subtitle text-center">{{ thirdApprovalDate }} </small>
                  <small v-if="!thirdApprovalDate && secondApprovalDate" class="text-subtitle text-center" style="color: blue">
                    진행중
                  </small>
                  <small v-if="!thirdApprovalDate && !secondApprovalDate" class="text-subtitle text-center"> </small>
                </div>
              </div>
            </template>
          </EasyDataTable>
        </div>
        <!-- 완료일경우 -->
        <div class="overflow-auto mt-2" v-if="tab === 'finish'">
          <EasyDataTable
            @click-row="showRow"
            :headers="headers"
            :items="items"
            table-class-name="customize-table action-position"
            :rows-per-page="8"
            v-if="!details"
          >
            <!-- 휴가 종류 -->
            <template #item-payStatusCode="{ payStatusCode, firstApprovalDate }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5" v-if="firstApprovalDate">
                    {{ getStatusCode(payStatusCode) }}
                  </h5>
                </div>
              </div>
            </template>
            <!-- 1차 승인자 -->
            <template #item-firstApprovalId="{ firstApprovalName, firstApprovalDate,thirdApprovalDate }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5" v-if="thirdApprovalDate || firstApprovalDate === 'companion' ||  secondApprovalDate === 'companion'">
                    {{ firstApprovalName }}
                  </h5>
                  <small v-if="firstApprovalDate" class="text-subtitle text-center" style="color: green">{{ firstApprovalDate }} </small>
                </div>
              </div>
            </template>
            <template #item-secondApprovalId="{ secondApprovalName, secondApprovalDate,thirdApprovalDate}">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5" v-if="thirdApprovalDate || secondApprovalDate === 'companion'">
                    {{ secondApprovalName }}
                  </h5>
                  <small v-if="secondApprovalDate" class="text-subtitle text-center">{{ secondApprovalDate }} </small>
                </div>
              </div>
            </template>
            <template #item-thirdApprovalId="{ thirdApprovalName, thirdApprovalDate }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5" v-if="thirdApprovalDate">
                    {{ thirdApprovalName }}
                  </h5>
                  <small v-if="thirdApprovalDate" class="text-subtitle text-center">{{ thirdApprovalDate }} </small>
                </div>
              </div>
            </template>
          </EasyDataTable>
        </div>
        <!-- 상세보기 -->
        <div v-if="details && selectAttendance" class="pa-5">
          <AttendanceListDetail @toggleDetail="details = false" :selectedDetail="selectAttendance" />
          <v-row class="justify-end mr-10 my-3">
            <div class="align-self-end mt-3">
              <v-button class="delete-button" @click="attendanceDelete(selectAttendance.annualId)">삭제하기</v-button>
            </div>
          </v-row>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.verifybtn {
  height: 12px !important;
  width: 12px !important;
  margin-top: -3px;
}

.action-position {
  .vue3-easy-data-table__main {
    .vue3-easy-data-table__header {
      th {
        &:last-child {
          padding-left: 35px;
        }
      }
    }
  }
}

@media (max-width: 475px) {
  .easy-data-table__rows-selector {
    width: 50px !important;
    margin: 0 15px !important;
  }

  .vue3-easy-data-table__footer {
    text-align: center;
    padding-top: 15px !important;
  }
}
</style>
