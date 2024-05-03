<template>
  <v-card elevation="0" class="bg-white overflow-hidden">
    <v-card-text class="d-flex justify-end">
      <div class="d-flex align-start mb-6">
        <div class="ml-auto z-1">
          <!-- 여기에 추가적인 컨텐츠가 있을 수 있습니다 -->
        </div>
      </div>
      <h2 class="text-h1 font-weight-normal text-black">
        <!-- 여기에 제목이 들어갈 수 있습니다 -->
      </h2>
      <!-- 리스트 컨테이너 시작 -->
      <div class="list-container">
        <ul>
          <li v-for="(title, i) in list.content" :key="i" class="list-item">
            <span class="item-title">{{ formatTitle(list.content[i].title) }}</span>
            <span class="item-date">{{ list.content[i].createdAt }}</span>
          </li>
        </ul>
      </div>
      <v-col cols="auto">
        <v-btn @click="toNoticeCard" density="compact" icon="mdi-plus"></v-btn>
      </v-col>    
      <!-- 리스트 컨테이너 끝 -->
    </v-card-text>
  </v-card>
  
</template>


<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'

const baseUrl = `${import.meta.env.VITE_API_URL}`;

const list = ref([]);
onMounted(async () => {
  try {
    const response = await axios.get(`${baseUrl}/login/notice/list`);
    console.log(response.data.result);
    list.value = response.data.result;
    console.log(list.value);
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
})

// 글자수가 15자 이상일 경우 '...'으로 표시하는 메서드 추가
const formatTitle = (title) => {
  return title.length > 15 ? title.substring(0, 15) + "..." : title;
};

const toNoticeCard = () => {
  location.href = "/noticeList"; 
}

</script>

<style>
.list-container li {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 이 부분을 추가합니다 */
  margin-bottom: 10px;
  border-bottom: 1px solid #e0e0e0;
  padding-bottom: 10px;
}

.list-container h3 {
  margin: 0;
  display: flex;
  flex-grow: 1; /* 제목이 더 많은 공간을 차지하도록 설정 */
}

.list-container h3 span {
  margin-left: auto; /* 날짜를 오른쪽으로 밀어내기 위해 자동 마진을 사용 */
}
</style>


