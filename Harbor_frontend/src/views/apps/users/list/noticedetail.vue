<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useNoticeDetailCardStore } from '@/stores/apps/UserCard';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const props = defineProps({
  noticeId: {
    type: String,
    required: true
    
  }
})

const store = useNoticeDetailCardStore();


const noticeId = ref(''); // 가정: URL 또는 다른 방법으로부터 공지사항 ID를 얻음

onMounted(() => {
  store.noticeDetailCards(props.noticeId); // 가정: 공지사항 상세 정보를 가져오는 메서드
});

const noticeDetail = ref({
  title: '', // 제목
  content: '', // 콘텐츠
  createdAt: '', // 작성일자
});

// store 상태를 감시하고, 변화가 있을 때 noticeDetail을 업데이트함

</script>

<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard :title="noticeDetail.title">
        <div class="notice-content">
          <p>{{ noticeDetail.createdAt }}</p>
          <div v-html="noticeDetail.content"></div>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style scoped>
.notice-content {
  p {
    font-size: 0.9rem;
    color: #666;
  }
}
</style>
