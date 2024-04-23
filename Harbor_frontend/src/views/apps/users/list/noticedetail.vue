<script setup lang="ts"> 
import { ref, onMounted } from 'vue'; 
import { useRoute } from 'vue-router'; 
import axios from 'axios'; 

const baseUrl = `${import.meta.env.VITE_API_URL}`; 
const route = useRoute(); 
const noticeDetail = ref({ 
title: '', 
content: '', 
createdAt: '',  
}); 
onMounted(async () => { const noticeId = route.params.noticeId; 
console.log(noticeId); 
try { const response = await axios.get(`${baseUrl}/login/notice/detail/${noticeId}`); 
noticeDetail.value = { 
  title: response.data.title, 
  content: response.data.content, 
  createdAt: response.data.createdAt }; } 
catch (error) { console.error('API 호출 중 오류 발생:', error); } }); 
</script>


<template>


</template>


