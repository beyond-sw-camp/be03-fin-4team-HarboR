<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/NoticeCard';
import 'vue3-easy-data-table/dist/style.css';
import noticedetail from '@/views/apps/users/list/noticedetail.vue';
import { useRouter } from 'vue-router';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

const store = useUserCardStore();
const router = useRouter();

const searchValue = ref('');
const token: string | null = localStorage.getItem('token');
onMounted(() => {
  store.noticeCards(token);
});

type ListItem = {
  noticeId: number; // 게시물 번호
  title: string; // 제목
  createdAt: string; // 작성일자
  contents: string;
  filePath: string;
};
const listCards = computed<ListItem[]>(() => {
  // noticeId 기준 내림차순 정렬
  return store.$state.noticelist
    .slice() // 원본 배열을 변경하지 않기 위해 복사본을 생성
    .sort((a, b) => b.noticeId - a.noticeId) // noticeId를 기준으로 내림차순 정렬
    .map((card) => ({
      noticeId: card.noticeId,
      title: card.title,
      contents: card.contents,
      createdAt: card.createdAt,
      filePath: card.filePath
    }));
});

const headers = [
  { text: '게시물 번호', value: 'noticeId', sortable: true }, // 게시물 번호 헤더 추가
  { text: '제목', value: 'title', sortable: true },
  { text: '작성일자', value: 'createdAt', sortable: true }
];

const items = ref(listCards);

const noticeDetails = ref(false);
const selectNotice = ref<ListItem | null>(null);

const showRow = async (item: ListItem) => {
  noticeDetails.value = true;
  selectNotice.value = item;
  store.saveSelectNotice(item);
};

const toNoticeCard = () => {
  router.push({ path: '/noticecreate' });
};
defineEmits(['sToggle']);


</script>
<template>
  <!-- 검색창 -->
  <div v-if="!noticeDetails">
    <!-- Topbar Row -->
    <div class="pa-3 d-flex align-center flex-wrap justify-space-between">
      <v-text-field
        variant="outlined"
        v-model="searchValue"
        prepend-inner-icon="mdi-magnify"
        persistent-placeholder
        placeholder="검색"
        hide-details
        density="compact"
        class="max-width-300"
      ></v-text-field>
      <!-- <v-text-field type="text" variant="outlined" persistent-placeholder placeholder="검색하기" v-model="searchValue"
              density="compact" hide-details prepend-inner-icon="mdi-magnify" /> -->
    </div>
    <v-divider />

    <div class="overflow-auto">
      <EasyDataTable
        :headers="headers"
        :items="items"
        :search-value="searchValue"
        table-class-name="customize-table action-position"
        :theme-color="themeColor"
        :rows-per-page="8"
        @click-row="showRow"
      />
    </div>

    <!-- '글쓰기' 버튼이 리스트 화면에서만 보임 -->
    <v-row justify="end">
      <v-col cols="auto">
        <v-btn color="blue darken-2" @click="toNoticeCard" density="compact"> 글쓰기 </v-btn>
      </v-col>
    </v-row>
  </div>
  <!-- 공지사항 상세보기 -->
  <div v-if="noticeDetails && selectNotice" class="pa-5">
    <noticedetail @toggleDetail="noticeDetails = false" :selectedDetail="selectNotice" />
  </div>
</template>
