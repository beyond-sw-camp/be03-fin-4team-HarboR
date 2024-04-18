<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useNoticeCardStore } from '@/stores/apps/UserCard';
import 'vue3-easy-data-table/dist/style.css';
import UiParentCard from '@/components/shared/UiParentCard.vue';
const store = useNoticeCardStore();

onMounted(() => {
  store.noticeCards();
});

type ListItem = {
  noticeId: string; // 게시물 번호
  title: string; // 제목
  createdAt: string; // 작성일자
};

const listCards = computed<ListItem[]>(() => {
  console.log(store);
  return store.$state.noticelist.map(card => ({
    noticeId: card.noticeId, // 게시물 번호 매핑
    title: card.title,
    createdAt: card.createdAt,
  }));
});

const headers = [
  { text: '게시물 번호', value: 'noticeId', sortable: true }, // 게시물 번호 헤더 추가
  { text: '제목', value: 'title', sortable: true },
  { text: '작성일자', value: 'createdAt', sortable: true },
];

const items = ref(listCards);
const themeColor = ref('rgb(var(--v-theme-secondary))');
</script>

<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="공지사항">
        <div class="overflow-auto">
          <EasyDataTable :headers="headers" :items="items" table-class-name="customize-table"
            :theme-color="themeColor" :rows-per-page="8">
          </EasyDataTable>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style lang="scss">
/* 스타일 변경 없음 */
</style>
