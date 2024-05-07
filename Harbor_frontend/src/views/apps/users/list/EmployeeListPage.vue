<script setup lang="ts">
import { ref, onMounted, computed,shallowRef } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';
// common components
import type { Header, ServerOptions } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { useCodeStore } from '@/stores/codetrans';
const store = useUserCardStore();
const codeStore = useCodeStore();
const token: string | null = localStorage.getItem('token');
onMounted(() => {
  store.fetchlistCards(token);
});
const getPositionName = (position) => {
  return codeStore.getPositionNameByCode(position);
};
const getDepartmentName = (department) => {
  return codeStore.getDepartmentNameByCode(department);
};
const getTeamName = (team) => {
  return codeStore.getTeamNameByCode(team);
};
type ListItem = {
  avatar: string;
  email: string;
  name: string;
  verify: boolean;
  employeeId: string;
  status: string;
  // Add other properties as needed
};
const listCards = computed<ListItem[]>(() => {
  return store.list;
});
console.log(listCards.value);


const headers: Header[] = [
  { text: '사원 정보', value: 'name', sortable: true },
  { text: '전화번호', value: 'phone', sortable: true },
  { text: '소속부서', value: 'department', sortable: true },
  { text: '사원직책', value: 'position', sortable: true },
  { text: '사원상태', value: 'status', sortable: true }
];
const items = ref(listCards);
const themeColor = ref('rgb(var(--v-theme-secondary))');
const showRow = (item: ListItem) => {
  location.href = `/app/user/${item.employeeId}/profile`;
};
//pageable 위한 함수
const currentPage = ref(0);
const changePage = (newPage) => {
  currentPage.value = newPage;
   const type = searchField.value;
  const value = searchValue.value;
  store.fetchlistCards(token, newPage, type, value);
};
// 검색 함수
function changeSearch() {
  const type = searchField.value;
  const value = searchValue.value;
  const page = currentPage.value;
  store.fetchlistCards(token, page, type, value);
}
const searchField = ref('name');
const searchValue = ref('');

const searchList = shallowRef([
  { name: '이름', value: 'name' },
  { name: '사원아이디', value: 'employeeId' },
]);
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Customer List">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3" class="d-flex align-center">
              <v-text-field
              type="text"
              variant="outlined"
              persistent-placeholder
              placeholder="검색하기"
              v-model="searchValue"
              density="compact"
              hide-details
              prepend-inner-icon="mdi-magnify"
              class="mr-2"
              />
              <v-btn @click="changeSearch()">검색하기</v-btn>
          </v-col>
          <v-col cols="12" md="3">
            <!-- <v-select label="Select" v-model="searchField" variant="outlined" @update:model-value="searchByName"
              :items="['', 'name', 'position', 'department']"></v-select> -->
              <select v-model="searchField" style="text-align: center; width: 100px; height: 30px; border: 1px solid black; border-radius: 5px; background-color: #f5f5f5;">
  <option v-for="(work, index) in searchList" :value="work.value" v-bind:key="index">
    {{ work.name }}
  </option>
</select>

          </v-col>
        </v-row>
        <div class="overflow-auto">
          <EasyDataTable :headers="headers" :items="items" table-class-name="customize-table action-position"
            @click-row="showRow" :theme-color="themeColor" :rows-per-page="20" hide-footer>
            <template #item-name="{ name, email, profileImagePath, verify, employeeId }">
              <div class="d-flex align-center ga-4">
                <img :src="profileImagePath" alt="avatar" width="40" />
                <div>
                  <h5 class="text-h5">
                    {{ name }}
                    <v-btn icon v-if="verify" flat color="success" size="small" class="verifybtn">
                      <CheckIcon size="10" />
                    </v-btn>
                  </h5>
                  <small class="text-subtitle">{{ email }}</small>
                  <br />
                  <small class="text-subtitle">{{ employeeId }}</small>
                </div>
              </div>
            </template>
            <template #item-department="{ department, team }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5">
                    {{ getTeamName(team) }}
                  </h5>
                  <small class="text-subtitle"> {{ getDepartmentName(department) }} </small>
                </div>
              </div>
            </template>
            <template #item-status="{ status }">
              <v-chip color="error" v-if="status === null || status === 'O02'" size="small"> 퇴근 </v-chip>
              <v-chip color="success"
                v-else-if="status === 'O01' || status === 'O06' || status === 'O07' || status === 'O08'" size="small"> 출근
              </v-chip>
              <v-chip color="warning" v-else-if="status === 'O04' || status === 'O09'" size="small"> 휴가 </v-chip>
              <v-chip v-else-if="status === 'O05'" size="small"> 휴계 </v-chip>
              <v-chip color="info" v-else-if="status === 'O03'" size="small"> 출장 </v-chip>
            </template>
            <template #item-position="{ position }">
              <div>
                {{ getPositionName(position) }}
              </div>
            </template>
            <template #item-operation>
              <div class="operation-wrapper">
                <v-btn icon color="secondary" variant="text">
                  <MessageIcon size="20" />
                </v-btn>
                <v-btn icon color="error" variant="text">
                  <BanIcon size="20" />
                </v-btn>
              </div>
            </template>
          </EasyDataTable>
          <div class="my-3 mr-5  itme-right">
            <v-btn @click="changePage(currentPage - 1)" :disabled="currentPage == 0">이전</v-btn>
            <span class="mx-6">{{ currentPage+1 }}</span>
            <v-btn @click="changePage(currentPage + 1)">다음</v-btn>
          </div>
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
.itme-right {
  text-align: right;
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
