<script setup lang="ts">
import axios from 'axios';
import {onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
// 상태 관리를 위한 ref 정의
const employeeDetails = ref([]);
// const phone = ref([]);
// const email = ref([]);
// const name = ref([]);
// const profileImagePath = ref([]);
// const employeeId = ref([]);
// vue-router의 useRoute를 사용하여 현재 라우트 정보에 접근
const route = useRoute();

onMounted(async () => {
  // 라우트 파라미터에서 사원번호(employeeId)를 추출
  const employeeId = route.params.employeeId;
  console.log(employeeId);
  try {
    const response = await axios.get(`${baseUrl}/employee/get/${employeeId}/detail`);
    employeeDetails.value = response.data.result;
    
    // phone.value = employeeDetails.phone;
    // email.value = employeeDetails.email;
    // name.value = employeeDetails.name;
    // profileImagePath.value = employeeDetails.profileImagePath;
    // employeeId.value = employeeDetails.employeeId;
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
});
async function update() {
  try {
    const response = await axios.post(`${baseUrl}/employee/${employeeId}/update`, {phone: employeeDetails.phone});
    alert('수정완료 완료되었습니다.');
  } catch (error) {
    console.error('수정 중 오류 발생:', error);
    alert('수정 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
}
</script>

<template>
  <v-row>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-6">
            <h5 class="text-subtitle-1">Profile Picture</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text class="text-center">
            <img :src="employeeDetails.profileImagePath" alt="avatar" width="150" class="v-avatar"/>
            <p class="text-subtitle-2 text-disabled font-weight-medium my-4"></p>
            <v-btn color="primary" >사진변경</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="px-5 py-6">
            <h5 class="text-subtitle-1">Edit Account Details</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.phone"
                  label="전화번호"
                  hint="Helper Text"
                  color="primary"
                  variant="outlined"
                  persistent-hint
                ></v-text-field>
              </v-col>
              <!-- <v-col cols="12">
                <v-text-field
                  type="email"
                  v-model="employeeDetails.address"
                  label="자택 주소"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col> -->
              <v-col cols="12" lg="6">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.name"
                  label="이름"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.email"
                  label="이메일"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.employeeId"
                  label="사원번호"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
            </v-row>
            <v-btn color="primary" class="mt-5" @click="update()">수정</v-btn>
            <v-btn color="error" class="mt-5 ml-3">삭제</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
