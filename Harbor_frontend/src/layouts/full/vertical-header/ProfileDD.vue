<script setup lang="ts">
import { ref } from 'vue';
import { SettingsIcon, LogoutIcon, UserIcon } from 'vue-tabler-icons';
import { useAuthStore } from '@/stores/auth';
const employeeId: string | null = localStorage.getItem('employeeId'); //사원번호

function goToMyPage() {
  location.href = `/app/user/${employeeId}/profile`;
};

const swt1 = ref(true);
const swt2 = ref(false);
const authStore = useAuthStore();
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!-- profile DD -->
  <!-- ---------------------------------------------- -->
  <div class="pa-4">
      
      <!-- My Page 추가 -->
      <v-list-item @click="goToMyPage()" color="secondary" rounded="md">
        <template v-slot:prepend>
          <UserIcon size="20" class="mr-2" /> <!-- MyPageIcon은 가정한 것으로, 실제 프로젝트에 맞는 아이콘을 사용해야 합니다. -->
        </template>
        <v-list-item-title class="text-subtitle-2">My Page</v-list-item-title>
      </v-list-item>
      <!-- Logout -->
      <v-list-item @click="authStore.logout()" color="secondary" rounded="md">
        <template v-slot:prepend>
          <LogoutIcon size="20" class="mr-2" />
        </template>
        <v-list-item-title class="text-subtitle-2">Logout</v-list-item-title>
      </v-list-item>
      
  </div>
</template>

