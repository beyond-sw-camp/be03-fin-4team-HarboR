<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';
import 'vue3-easy-data-table/dist/style.css'; 
const store = useUserCardStore();

onMounted(() => {
  store.noticeCards();
});

type ListItem = {
  noticeId: number; // 게시물 번호
  title: string; // 제목
  createdAt: string; // 작성일자
};  

const listCards = computed<ListItem[]>(() => {
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
const searchValue = ref('');
const noticeDetails = ref(false);

const themeColor = ref('rgb(var(--v-theme-secondary))');

const showRow = (item: ListItem) => {
  location.href=`/${item.noticeId}/detail`
};

</script>

<template>
  <!-- 검색창 -->
   <div v-if="!noticeDetails">
    <!---Topbar Row-->
    <div class="pa-3 d-fl ex align-center flex-wrap justify-space-between">
      <div>
        <v-text-field
          variant="outlined"
          v-model="searchValue"
          prepend-inner-icon="mdi-magnify"
          persistent-placeholder
          placeholder="Search Mail"
          hide-details
          density="compact"
          class="max-width-300"
        ></v-text-field>
      </div>
    </div>
    <v-divider />

    

  
  <div class="overflow-auto" >
    <EasyDataTable 
      :headers="headers" 
      :items="items" 
      table-class-name="customize-table action-position"
      :theme-color="themeColor" 
      :rows-per-page="8"
      @click-row="showRow"
    />
  </div>
  </div>

</template>

<style lang="scss"></style>
