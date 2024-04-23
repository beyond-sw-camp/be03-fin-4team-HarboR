<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { MonthStore } from '@/stores/apps/monthUser';
import type { Header } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';
const store = MonthStore();
const token: string | null = localStorage.getItem('token');

// 선택한 월 상태 변수
const selectedMonth = ref<string>('');

// 페이지 로드 시 데이터 가져오는 부분
onMounted(() => {
  const today = new Date().toISOString().split('.')[0]; // 오늘 날짜를 ISO 형식으로 가져옴
  store.fetchlistCards(token, selectedMonth.value || today); // 선택한 월이 없으면 오늘 날짜를 기본값으로 사용
});

// 월 선택 변경 시 데이터 가져오는 부분
const fetchData = () => {
  console.log("42")
  const today = new Date().toISOString().split('.')[0]; // 오늘 날짜를 ISO 형식으로 가져옴
  console.log(selectedMonth.value)
  store.fetchlistCards(token, selectedMonth.value+"-01T00:00:00" || today); // 선택한 월이 없으면 오늘 날짜를 기본값으로 사용
};

const headers: Header[] = [
  { text: '날짜', value: 'attendanceDate', sortable: true },
  { text: '시작 시간', value: 'workStartTime', sortable: true },
  { text: '끝나는 시간', value: 'workEndTime', sortable: true },
  { text: '정책', value: 'workPolicy', sortable: true },
  { text: '지각', value: 'tardy', sortable: true },
];
type ListItem = {
  attendanceDate: string;
  workStartTime: string;
  workEndTime: string;
  workPolicy: string;
  tardy: string;
};

const themeColor = ref('rgb(var(--v-theme-secondary))');

const listCards = computed<ListItem[]>(() => {
  return store.cards;
});
</script>

<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="한달 근무리스트">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3">
            <!-- 월 선택을 위한 input -->
            <input type="month" v-model="selectedMonth" @change="fetchData" />
          </v-col>
        </v-row>
        <div class="overflow-auto">
          <EasyDataTable :headers="headers" :items="listCards" table-class-name="customize-table action-position"
            :theme-color="themeColor" :rows-per-page="31" >
            <template #item-attendanceDate="{ attendanceDate, workStartTime, workEndTime, workPolicy, tardy }">
              <div class="d-flex align-center ga-4">
                <div>
                  <h5 class="text-h5">{{ attendanceDate }}</h5>
                </div>
              </div>
            </template>
          </EasyDataTable>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
