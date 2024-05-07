<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const baseUrl = import.meta.env.VITE_API_URL;

const handleButtonClick = async (type: string) => {
  try {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('토큰이 존재하지 않습니다.');
      return;
    }
    let url = '';
    if (type === '출근') {
      url = `${baseUrl}/total/commute/attendance`;
    } else if (type === '퇴근') {
      url = `${baseUrl}/total/commute/leavework`;
    } else {
      console.error('잘못된 유형입니다.');
      return;
    }
    const response = await axios.get(url, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    alert(response.data.message);
    location.reload();
  } catch (error) {
    // 오류 발생 시 오류 메시지를 알림으로 보여주기
    alert(error.response.data.error_message);
  }
};
</script>

<template>
  <div>
    <v-card elevation="0" class="bg-primary overflow-hidden bubble-shape-sm bubble-primary mb-6" @click="handleButtonClick('출근')">
      <v-card-text class="pa-5">
        <div class="d-flex align-center ga-4">
          <v-btn color="darkprimary" icon rounded="sm" variant="flat">
            <TableIcon stroke-width="1.5" width="25" />
          </v-btn>
          <div>
            <h4 class="text-h4 font-weight-medium">출근</h4>
            <span class="text-subtitle-2 text-medium-emphasis text-white"></span>
          </div>
        </div>
      </v-card-text>
    </v-card>

    <!-- 퇴근 버튼에 대한 수정 부분 -->
    <v-card elevation="0" class="bubble-shape-sm overflow-hidden bubble-warning" @click="handleButtonClick('퇴근')">
      <v-card-text class="pa-5">
        <div class="d-flex align-center ga-4">
          <v-btn color="lightwarning" icon rounded="sm" variant="flat">
            <BuildingStoreIcon stroke-width="1.5" width="25" class="text-warning" />
          </v-btn>
          <div>
            <h4 class="text-h4 font-weight-medium">퇴근</h4>
            <span class="text-subtitle-2 text-disabled font-weight-medium"></span>
          </div>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
