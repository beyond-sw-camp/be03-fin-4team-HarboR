<template>
  <RouterView></RouterView>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router';
let handlingF12Event = false;
const role = localStorage.getItem('role');
// 개발자 모드 방지 함수
window.addEventListener('keydown', function(e) {
  if (e.key === 'F12') {
    if (!handlingF12Event) {
      e.preventDefault();
      handlingF12Event = true;
      setTimeout(() => {
        handlingF12Event = false;
      }, 3000);
    }
  }
});
// 우클릭을 차단하는 함수
window.addEventListener('contextmenu', function(e) {
  this.alert("보안 정책상 우클릭을 금지 합니다.");
  e.preventDefault();
});


</script>
