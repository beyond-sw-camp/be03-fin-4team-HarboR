<script lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';

export default {
  setup() {
    const store = useUserCardStore();
    onMounted(() => {
      store.noticeCards();
    });

    type ListItem = {
      noticeId: number; // 게시물 번호
      title: string; // 제목
      createdAt: string; // 작성일자
    };

    // listCards를 수정하여 날짜가 최신인 것으로 3개의 게시물만 반환하도록 함
    const listCards = computed<ListItem[]>(() => {
      return store.$state.noticelist
        .sort((a, b) => Date.parse(b.createdAt) - Date.parse(a.createdAt)) // 날짜 내림차순 정렬
        .slice(0, 3) // 상위 3개 선택
        .map(card => ({
          noticeId: card.noticeId, // 게시물 번호 매핑
          title: card.title,
          createdAt: card.createdAt,
        }));
    });

    // items를 computed 속성으로 정의
    const items = ref(listCards);

    // 글자수가 15자 이상일 경우 '...'으로 표시하는 메서드
    const formatTitle = (title: string) => {
      return title.length > 15 ? title.substring(0, 15) + "..." : title;
    };

    const toNoticeCard = () => {
      location.href = "/noticeList";
    }

    // setup 함수에서 사용할 변수와 함수를 반환
    return {
      items,
      formatTitle,
      toNoticeCard
    };
  }
}
</script>




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
          <li v-for="(item, i) in items" :key="i" class="list-item">
            <span class="item-title">{{ formatTitle(item.title) }}</span>
            <span class="item-date">{{ item.createdAt }}</span>
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


