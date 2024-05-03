<script setup lang="ts">
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
// 상태 관리를 위한 ref 정의
const employeeDetails = ref([]);
const route = useRoute();
const profileImage = ref('');
let selectedImage;

function fileUpload(event) {
  selectedImage = event.target.files[0];
  const reader = new FileReader();
  reader.onload = () => {
    profileImage.value = reader.result;
  };
  reader.readAsDataURL(selectedImage);
}

onMounted(async () => {
  // 라우트 파라미터에서 사원번호(employeeId)를 추출
  const employeeId = route.params.employeeId;
  try {
    const response = await axios.get(`${baseUrl}/employee/get/${employeeId}/detail`);
    employeeDetails.value = response.data.result;
    profileImage.value = employeeDetails.value.profileImagePath;
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
});
async function update() {
  const formData = new FormData();
  formData.append('phone', employeeDetails.value.phone);
  formData.append('address', employeeDetails.value.address);
  if (selectedImage) {
    formData.append('profileImage', selectedImage);
  }



  try {
    const response = await axios.patch(`${baseUrl}/employee/${employeeDetails.value.employeeId}/update`, formData
    );
    alert('수정 완료되었습니다.');
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
            <img :src="profileImage" alt="avatar" width="150" class="v-avatar" />
            <p class="text-subtitle-2 text-disabled font-weight-medium my-4"></p>
            <v-btn color="primary" style="position: relative; overflow: hidden;">
              <input type="file" accept="image/*" @change="fileUpload"
                style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer;">
              사진변경 (test)
            </v-btn>
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
                <v-text-field type="text" v-model="employeeDetails.phone" label="전화번호" hint="Helper Text"
                  color="primary" variant="outlined" persistent-hint></v-text-field>
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
                <v-text-field type="text" v-model="employeeDetails.name" label="이름" color="primary" variant="outlined"
                  hide-details></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field type="text" v-model="employeeDetails.email" label="이메일" color="primary" variant="outlined"
                  hide-details></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field type="text" v-model="employeeDetails.employeeId" label="사원번호" color="primary"
                  variant="outlined" hide-details></v-text-field>
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
