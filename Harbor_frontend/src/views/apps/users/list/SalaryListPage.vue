<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import 'vue3-easy-data-table/dist/style.css';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import axios from 'axios';

const list = ref([]);
const baseUrl = `${import.meta.env.VITE_API_URL}`;
onMounted(() => {
  fetchSalary();
});
async function fetchSalary() {
  try {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('토큰이 존재하지 않습니다.');
      return;
    }
    const response = await axios.get(`${baseUrl}/salary/mysalarys`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    list.value = response.data.result;
  } catch (error) {
    console.log(error);
  }
};
type ListItem = {
  salaryId: string;
  // Add other properties as needed
};
const headers: Header[] = [
  { text: '제목', value: 'salary', sortable: true },
  { text: '지급일자', value: 'salaryMonthOfYear', sortable: true }
];
const listCards = computed<ListItem[]>(() => {
  return list.value;
});
const items = ref(listCards);
const showRow = (item: ListItem) => {
  location.href = `/app/user/salarydetail/${item.salaryId}`
};
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="급여 목록">
        <div class="overflow-auto">
          <EasyDataTable :headers="headers" :items="items" table-class-name="customize-table action-position"
            @click-row="showRow" :rows-per-page="8">
            <template #item-salary="{salaryMonthOfYear }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5">
                    {{ salaryMonthOfYear.slice(2,7) }} 월 급여
                  </h5>
                </div>
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
