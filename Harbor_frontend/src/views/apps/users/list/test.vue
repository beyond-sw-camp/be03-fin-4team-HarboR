<!-- <script setup lang="ts">
import { ref, onMounted, shallowRef } from 'vue';

import { format } from 'date-fns'

const props = defineProps({
  selectedDetail: {
    type: Object 
  }
})
const sorting = shallowRef([{ title: 'title'}, { title: 'contents'}, { title: 'createdAt'}])
console.log("이건 프롭스여" + props.selectedDetail);

defineEmits(['toggleDetail']);
</script> -->

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
    
    <p>{{ selectedDetail?.contents }}</p>
    <br />
  </div>
  <div class="py-3">
    <h5 class="text-h5"><v-icon>mdi-attachment</v-icon> {{ selectedDetail?.attachments.length }} Attachement</h5>
  </div>
 
</template>