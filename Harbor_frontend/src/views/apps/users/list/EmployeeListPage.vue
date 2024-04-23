<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';
// common components
import type { Header } from 'vue3-easy-data-table';
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
  // Add other properties as needed
};
const listCards = computed<ListItem[]>(() => {
  return store.list;
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: '사원 정보', value: 'name', sortable: true },
  { text: '전화번호', value: 'phone', sortable: true },
  { text: '소속부서', value: 'department', sortable: true },
  { text: '사원직책', value: 'position', sortable: true },
  { text: '사원상태', value: 'status', sortable: true },
];
const items = ref(listCards);
const themeColor = ref('rgb(var(--v-theme-secondary))');
const showRow = (item: ListItem) => {
  location.href=`/app/user/${item.employeeId}/profile`
};
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Customer List">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3">
            <v-text-field type="text" variant="outlined" persistent-placeholder placeholder="검색하기" v-model="searchValue"
              density="compact" hide-details prepend-inner-icon="mdi-magnify" />
          </v-col>
          <v-col cols="12" md="3">
            <v-select label="Select" v-model="searchField" variant="outlined"
            :items="['','name', 'position', 'department']"></v-select>
            </v-col>
        </v-row>
        <div class="overflow-auto">
          <EasyDataTable :headers="headers" :items="items" table-class-name="customize-table action-position" @click-row="showRow"
            :theme-color="themeColor" :search-field="searchField" :search-value="searchValue" :rows-per-page="8">
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
              <v-chip color="success" v-if="status === 'Active'" size="small"> Active </v-chip>
              <v-chip color="error" v-if="status === 'Rejected'" size="small"> Rejected </v-chip>
              <v-chip color="warning" v-if="status === 'Pending'" size="small"> Pending </v-chip>
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
