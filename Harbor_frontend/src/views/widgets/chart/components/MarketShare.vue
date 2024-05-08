<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { ApproveStore } from '@/stores/apps/approveUser.ts';
import axios, { setClientHeaders } from '@/utils/axios';

import type { Header } from 'vue3-easy-data-table';
import { useCodeStore } from '@/stores/codetrans';
import 'vue3-easy-data-table/dist/style.css';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
const codeStore = useCodeStore();
const store = ApproveStore();
const isLoading = ref(false);

onMounted(() => {
  store.fetchlistCards();
});

const activateUser = async (email: string) => {
  if (window.confirm('활성화 하시겠습니까?')) {
    isLoading.value = true;
    try {
      setClientHeaders();
      await axios.get(`${baseUrl}/login/admin/active/${email}`);
      alert('성공');
      location.reload();
    } catch (error) {
      console.log(error);
    } finally {
      isLoading.value = false;
    }
  }
};

const denyUser = async (email: string) => {
  if (window.confirm('삭제 하시겠습니까 ?')) {
    isLoading.value = true;
    try {
      setClientHeaders();
      await axios.delete(`${baseUrl}/login/admin/delete/${email}`);
      alert('성공');
      location.reload();
    } catch (error) {
      console.log(error);
    } finally {
      isLoading.value = false;
    }
  }
};

const headers: Header[] = [
  { text: '이메일', value: 'email', sortable: true },
  { text: '이름', value: 'name', sortable: true },
  { text: '생년월일', value: 'birth', sortable: true },
  { text: '가입날짜', value: 'createdAt', sortable: true },
  { text: '승인여부', value: 'delYn', sortable: true },
  { text: '활성화/삭제', value: 'actions', sortable: false }
];
type ListItem = {
  email: string;
  name: string;
  birth: string;
  createdAt: string;
  delYn: string;
  actions: string;
};

const listCards = computed<ListItem[]>(() => {
  return store.cards;
});
const getDelYnByCode = (delYn) => {
  return codeStore.getApproveByCode(delYn);
};
const themeColor = ref('rgb(var(--v-theme-secondary))');
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="사원 승인 리스트">
        <v-row justify="space-between" class="align-center mb-3"> </v-row>
        <div class="overflow-auto">
          <EasyDataTable
            :headers="headers"
            :items="listCards"
            table-class-name="customize-table action-position"
            :theme-color="themeColor"
            :rows-per-page="10"
          >
            <template #item-data="{ email, name, birth, createdAt, delYn, item, deny }">
              <div class="d-flex align-center ga-4"></div>
            </template>
            <template #item-delYn="{ delYn }">
              <div>{{ getDelYnByCode(delYn) }}</div>
            </template>
            <template #item-actions="{ item, email }">
              <!-- 버튼을 좌우로 정렬하는 flex 컨테이너를 추가합니다 -->
              <div class="d-flex align-center" style="margin-top: 8px; margin-bottom: 8px">
                <!-- "활성화" 버튼 -->
                <v-btn @click="activateUser(email)">활성화</v-btn>
                <!-- "삭제" 버튼 -->
                <v-btn @click="denyUser(email)">삭제</v-btn>
              </div>
            </template>
          </EasyDataTable>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
  <v-dialog v-model="isLoading" persistent max-width="70px">
    <v-progress-circular indeterminate color="primary" :size="70" :width="7" />
  </v-dialog>
</template>
