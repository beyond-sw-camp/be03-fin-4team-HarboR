<script setup lang="ts">
import axios from 'axios';
import { onMounted } from 'vue';
const baseUrl = `${import.meta.env.VITE_API_URL}`;

onMounted(() => {
  // HB20240001부터 HB20240100까지 반복
  for (let i = 1; i <= 100; i++) {
    // 숫자를 문자열로 변환하고, 앞에 '0'을 붙여 총 길이를 5로 맞춤
    let employeeNumber = i.toString().padStart(4, '0');
    let employeeId = `HB2024${employeeNumber}`;
    fetchSalary(employeeId); // 각 직원 ID에 대해 급여 지급 시도
  }
  alert("지급완료");
});

async function fetchSalary(employeeId: string) {
  try {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('토큰이 존재하지 않습니다.');
      return;
    }
    const response = await axios.get(`${baseUrl}/salary/create/${employeeId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log(`${employeeId}에 대한 지급완료`); // 성공적으로 급여 지급 시 알림
  } catch (error) {
    console.log(`${employeeId}에 대한 지급오류`);
    console.error(error);
  }
};
</script>

<template>
</template>
