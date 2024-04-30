<script setup lang="ts">
import { ref,onMounted ,computed} from 'vue';
import { ApproveStore } from '@/stores/apps/approveUser.ts';
import axios, { setClientHeaders } from '@/utils/axios';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
import type { Header } from 'vue3-easy-data-table';
import { useCodeStore } from '@/stores/codetrans';
import 'vue3-easy-data-table/dist/style.css';

const codeStore = useCodeStore();
const store = ApproveStore();
const token: string | null = localStorage.getItem('token');
const cards = ref<ListItem[]>([]);
const isLoading = ref(false);

onMounted(async () => {
  console.log("1")
  cards.value = await store.getAttendanceList(token);
});

const headers: Header[] = [
  { text: '사원번호', value: 'employeeId', sortable: false },
  { text: '현재 부서', value: 'beforeDepartmentCode', sortable: true },
  { text: '바뀔 부서', value: 'afterDepartmentCode', sortable: true },
  { text: '계급', value: 'positionCode', sortable: true },
  { text: '직무', value: 'updateDutyCode', sortable: true },
  { text: '발령예정일', value: 'issueDate', sortable: true },
  { text: '삭제', value: 'delete', sortable: false }
];
type ListItem = {
  appointmentId : number;
  beforeDepartmentCode: string;
  afterDepartmentCode: string;
  positionCode: string;
  issueDate: string;
  employeeId: string;
  updateDutyCode : string;
  delyn: string;
};


const listCards = computed<ListItem[]>(() => {
  return cards.value; // cards.value로 수정
});
const getDepartmentName = (beforeDepartmentCode) => {
  return codeStore.getDepartmentNameByCode(beforeDepartmentCode);
};
const getDutyName = (updateDutyCode) =>{
    return codeStore.getDutyNameByCode(updateDutyCode);
}
const getPositionName = (positionCode) =>{
    return codeStore.getPositionNameByCode(positionCode)
}
const deleteItem = async (appointmentId: number) => {
    if (window.confirm('삭제 하시겠습니까 ??')) {
    try {
      setClientHeaders(token)
      await axios.delete(`${baseUrl}/employee/personnel/${appointmentId}/delete`)
      alert("성공")
      location.reload()
    } catch (error) {
      console.log(error)
    }
  }
};

const personnelFileInput = ref(null);
const uploadFileAttendance = () => {
  personnelFileInput.value.click();
};

const handleFileUploadAttendance = async (event) => {
  isLoading.value = true; // 로딩 시작
  const file = event.target.files[0];
  try {
    const result = await store.uploadAttendanceFile(token, file); // await 키워드 추가
    alert("성공")
    location.reload()
  } catch (error) {
    console.log(error)
  } finally {
    isLoading.value = false; // 로딩 종료
  }
};

const themeColor = ref('rgb(var(--v-theme-secondary))');
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="사원 승인 리스트">
        <v-row justify="space-between" class="align-center mb-3">
          
        </v-row>
        <div class="overflow-auto">
            <EasyDataTable :headers="headers" :items="listCards" table-class-name="customize-table action-position"
                :theme-color="themeColor" :rows-per-page="10">
                <template #item-employeeId="{ employeeId }">
                <div>{{ employeeId }}</div>
                </template>
                <template #item-beforeDepartmentCode="{ beforeDepartmentCode }">
                <div>{{ getDepartmentName(beforeDepartmentCode) }}</div>
                </template>
                <template #item-afterDepartmentCode="{ afterDepartmentCode }">
                <div>{{ getDepartmentName(afterDepartmentCode) }}</div>
                </template>
                <template #item-positionCode="{ positionCode }">
                <div>{{ getPositionName(positionCode) }}</div>
                </template>
                <template #item-updateDutyCode="{ updateDutyCode }">
                <div>{{ getDutyName(updateDutyCode) }}</div>
                </template>
                <template #item-delete="{ appointmentId }">
                    <v-btn color="error" @click="deleteItem(appointmentId)">삭제</v-btn>
                  </template>
            </EasyDataTable>
        </div>
        <div class="d-flex justify-end"> <!-- 오른쪽으로 정렬하는 Flexbox 컨테이너 -->
            <v-btn color="primary" @click="uploadFileAttendance" >버튼</v-btn>
            <input type="file" ref="personnelFileInput" style="display: none" @change="handleFileUploadAttendance"/>
          </div>
      </UiParentCard>
    </v-col>
  </v-row>
  <v-dialog v-model="isLoading" persistent max-width="70px">
    <v-progress-circular
      indeterminate
      color="primary"
      :size="70"
      :width="7"
    />
  </v-dialog>
</template>

<style>
.delete-button,
.edit-button {
  margin-left: 10px;
  background-color: #5938de;
  color: white;
  border-radius: 10px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

/* 버튼에 마우스를 올렸을 때의 스타일 */
.delete-button:hover,
.edit-button:hover {
  background-color: #766ee6;
}

.edit-button {
  background-color: #4CAF50;
  /* 수정하기 버튼을 위한 다른 색상 */
}

.edit-button:hover {
  background-color: #66bb6a;
}
</style>