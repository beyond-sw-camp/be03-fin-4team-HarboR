<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';
import 'vue3-easy-data-table/dist/style.css'; 
import { useMailStore } from '@/stores/apps/mail';
import noticedetail from '@/views/apps/users/list/noticedetail.vue'
import notcieCreate from '@/views/apps/users/list/noticeCreate.vue'
import { useRouter } from 'vue-router'

const router = useRouter();

const mailstore = useMailStore();

const store = useUserCardStore();
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
  filepath: string;
};  
const listCards = computed<ListItem[]>(() => {
  // noticeId 기준 내림차순 정렬
  return store.$state.noticelist
    .slice() // 원본 배열을 변경하지 않기 위해 복사본을 생성
    .sort((a, b) => b.noticeId - a.noticeId) // noticeId를 기준으로 내림차순 정렬
    .map(card => ({
      noticeId: card.noticeId,
      title: card.title,
      contents: card.contents,
      createdAt: card.createdAt
    }));
});



const headers = [
  { text: '게시물 번호', value: 'noticeId', sortable: true }, // 게시물 번호 헤더 추가
  { text: '제목', value: 'title', sortable: true },
  { text: '작성일자', value: 'createdAt', sortable: true },
];

const items = ref(listCards);

const noticeDetails = ref(false);
const selectNotice = ref<ListItem | null>(null);

const showRow = async (item: ListItem) => {
  noticeDetails.value = true;
  selectNotice.value = item;

};

const toNoticeCard = () => {
  router.push({ path: '/noticecreate' });    }
defineEmits(['sToggle']);
</script>
<template>
  <!-- 검색창 -->
  <div v-if="!noticeDetails">
    
    <!-- Topbar Row -->
    <div class="pa-3 d-flex align-center flex-wrap justify-space-between">
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
        <!-- <v-text-field type="text" variant="outlined" persistent-placeholder placeholder="검색하기" v-model="searchValue"
              density="compact" hide-details prepend-inner-icon="mdi-magnify" /> -->
      </div>
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
        <v-btn color="blue darken-2" @click="toNoticeCard" density="compact">
          글쓰기
        </v-btn>
      </v-col>
    </v-row>
  </div>
  <!-- 공지사항 상세보기 -->
  <div v-if="noticeDetails && selectNotice" class="pa-5">
    <noticedetail @toggleDetail="noticeDetails = false" :selectedDetail="selectNotice" />
  </div>
</template>


<style lang="scss"></style>
<!-- ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss -->

<script setup lang="ts">
// import { ref, onMounted, shallowRef } from 'vue';
import { format } from 'date-fns'
import axios, {setClientHeaders} from '@/utils/axios'; // axios import
import { useRouter } from 'vue-router';

const token: string | null = localStorage.getItem('token');
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const router = useRouter();


const deleteItem = async () => {
  try {
    const noticeId = props.selectedDetail.noticeId;
    const response = await axios.delete(`${baseUrl}/login/notice/delete/${noticeId}`);
    alert("삭제성공");
    location.reload();

  } catch (error) {
    console.error(error); 
  }
}
const editItem = async () =>{
}
  
const props = defineProps({
  selectedDetail: Object || Array
})
 
</script>

<template>
  <div class="topbarMail d-flex ga-4 align-center w-100">
    <!-- 뒤로가기 버튼 -->
    <v-btn icon flat @click="$emit('toggleDetail')" size="small"><ChevronLeftIcon size="18" /></v-btn>
    <!-- 이메일 이름 -->
    <div class="d-flex align-center ga-4 w-100">
      <div>
        <h4 class="text-h4 mb-n1">noticeAdmin</h4>
        <small>From: admin@test.com</small>
      </div>
    </div>
    <!-- 오른쪽 끝에 날짜 -->
    <div class="ml-auto text-subtitle-2 text-medium-emphasis">{{ format(new Date(selectedDetail?.createdAt!), 'd MMM') }}</div>
  </div>
  <div class="d-flex align-center ga-2 mt-3">
    <h3 class="text-h3 py-4 mr-auto">{{ selectedDetail?.title }}</h3>
  </div>
  <div class="py-4 text-subtitle-1 font-weight-light">
    {{ selectedDetail?.contents }}
    <br />
  </div>

  <!-- 전체 컨테이너 -->
  <div class="d-flex flex-column justify-content-between" style="height: 100%;">
    <!-- 여기에 다른 내용 추가 가능 -->
    <div class="align-self-end">
      <v-button class="edit-button" @click="editItem">수정하기</v-button>
      <v-button class="delete-button" @click="deleteItem">삭제하기</v-button>
    </div>
  </div>
</template>

<style>
.delete-button, .edit-button {
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
.delete-button:hover, .edit-button:hover {
  background-color: #766ee6; 
}

.edit-button {
  background-color: #4CAF50; /* 수정하기 버튼을 위한 다른 색상 */
}

.edit-button:hover {
  background-color: #66bb6a;
}
</style>