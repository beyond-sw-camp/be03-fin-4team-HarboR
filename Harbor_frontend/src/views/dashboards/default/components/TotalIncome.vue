<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const baseUrl = import.meta.env.VITE_API_URL;
const modalOpen = ref(false);
const modalContent = ref('');

const openModal = (title) => {
  modalContent.value = title;
  modalOpen.value = true;
};

const closeModal = () => {
  modalOpen.value = false;
};

const handleButtonClick = async (type) => { // type 매개변수 추가
  try {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('토큰이 존재하지 않습니다.');
      return;
    }
    let url = ''; // URL 변수 초기화
    if (type === '출근') {
      url = `${baseUrl}/total/commute/attendance`; // 출근 URL 설정
    } else if (type === '퇴근') {
      url = `${baseUrl}/total/commute/leavework`; // 퇴근 URL 설정
    } else {
      console.error('잘못된 유형입니다.');
      return;
    }
    const response = await axios.get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    alert(response.data.message)
    closeModal()
  } catch (error) {
    alert(error.response.data.error_message)
    closeModal()
  }
};
</script>
const baseUrl = `${import.meta.env.VITE_API_URL}`;
<template>
  <div>
    <v-dialog v-model="modalOpen" max-width="400"> <!-- max-width 변경 -->
      <v-card class="d-flex justify-center"> <!-- 컨텐츠를 가운데로 정렬 -->
        <v-card-text>
          <v-btn color="primary" @click="handleButtonClick(modalContent)">{{ modalContent }}버튼</v-btn>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="closeModal">닫기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-card elevation="0" class="bg-primary overflow-hidden bubble-shape-sm bubble-primary mb-6">
      <v-card-text class="pa-5">
        <div class="d-flex align-center ga-4">
          <v-btn color="darkprimary" icon rounded="sm" variant="flat" @click="openModal('출근')">
            <TableIcon stroke-width="1.5" width="25" />
          </v-btn>
          <div>
            <h4 class="text-h4 font-weight-medium">출근</h4>
            <span class="text-subtitle-2 text-medium-emphasis text-white">Total Income</span>
          </div>
        </div>
      </v-card-text>
    </v-card>

    <v-card elevation="0" class="bubble-shape-sm overflow-hidden bubble-warning">
      <v-card-text class="pa-5">
        <div class="d-flex align-center ga-4">
          <v-btn color="lightwarning" icon rounded="sm" variant="flat" @click="openModal('퇴근')">
            <BuildingStoreIcon stroke-width="1.5" width="25" class="text-warning" />
          </v-btn>
          <div>
            <h4 class="text-h4 font-weight-medium">퇴근</h4>
            <span class="text-subtitle-2 text-disabled font-weight-medium">Total Income</span>
          </div>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
