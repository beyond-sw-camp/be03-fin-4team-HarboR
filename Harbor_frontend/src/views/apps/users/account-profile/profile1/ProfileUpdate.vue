<script setup lang="ts">
import axios, { setClientHeaders, setContentTypeHeaders } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
// 상태 관리를 위한 ref 정의
const employeeDetails = ref([]);
const route = useRoute();
const profileImage = ref('');
const show1 = ref(false);
const show2 = ref(false);
let selectedImage;
const token: string | null = localStorage.getItem('token');
const role: string | null  = localStorage.getItem('role');
const getEmployeeId : string | null = localStorage.getItem('employeeId');
const routeEmployeeId = route.params.employeeId;
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
  
  try {
    setClientHeaders(token);
    const response = await axios.get(`${baseUrl}/employee/get/${routeEmployeeId}/detail`);
    employeeDetails.value = response.data.result;
    profileImage.value = employeeDetails.value.profileImagePath;
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
});
async function update() {
  const formData = new FormData();
  const noticeForm = {
    employeeId: employeeDetails.value.employeeId,
    phone: employeeDetails.value.phone,
    address: employeeDetails.value.address
  };

  const blob = new Blob([JSON.stringify(noticeForm, null, 2)], { type: 'application/json' });
  if (selectedImage) {
    formData.append('file', selectedImage);
  }
  formData.append('request', blob);
  try {
    setContentTypeHeaders('multipart/form-data');
    await axios.patch(`${baseUrl}/employee/update`, formData);
    alert('수정 완료되었습니다.');
  } catch (error) {
    console.error('수정 중 오류 발생:', error);
    alert('수정 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
};
async function employeeDelete() {
  try {
    await axios.delete(`${baseUrl}/login/admin/delete/${employeeDetails.email}`);
    alert('삭제 완료되었습니다.');
    location.href = '/app/user/list';
  } catch (error) {
    console.error('삭제 중 오류 발생:', error);
    alert('삭제 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
}
// 비밀번호 변경
const tempPassword = ref('');
const changePw = ref('');
const passwordRules = ref([
  (v: string) => !!v || '비밀번호를 입력해주세요',
  (v: string) => (v && v.length >= 8 && v.length <= 20) || '비밀번호를 8자에서 20자 사이로 입력해주세요'
]);
async function pwUpdate() {
  try {
    await axios.patch(`${baseUrl}/login/account/changePw`, {
      employeeId: getEmployeeId,
      tempPassword: tempPassword.value,
      changePw: changePw.value,
    });
    alert('비밀번호 수정 완료되었습니다.');
  } catch (error) {
    console.error('비밀번호 수정 중 오류 발생:', error);
    alert('비밀번호 수정 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
};
</script>

<template>
  <v-row>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-6">
            <h5 class="text-subtitle-1">프로필 사진</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text class="text-center">
            <img :src="profileImage" alt="avatar" width="150" class="v-avatar" />
            <p class="text-subtitle-2 text-disabled font-weight-medium my-4"></p>
            <v-btn color="primary" style="position: relative; overflow: hidden" v-if="getEmployeeId === routeEmployeeId">
              <input
                type="file"
                accept="image/*"
                @change="fileUpload"
                style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer"
              />
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
            <h5 class="text-subtitle-1">수정 하기</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" v-if="getEmployeeId === routeEmployeeId">
                <v-text-field
                v-model="tempPassword"
                label="기존 비밀번호"
                required
                :rules="passwordRules"
                density="comfortable"
                variant="outlined"
                color="primary"
                hide-details="auto"
                :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show1 ? 'text' : 'password'"
                @click:append-inner="show1 = !show1"
                class="pwdInput"
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="10"v-if="getEmployeeId === routeEmployeeId">
                <v-text-field
                v-model="changePw"
                label="변경할 비밀번호"
                required
                :rules="passwordRules"
                density="comfortable"
                variant="outlined"
                color="primary"
                hide-details="auto"
                :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show2 ? 'text' : 'password'"
                @click:append-inner="show2 = !show2"
                class="pwdInput">
              </v-text-field>
              </v-col>
              <v-col cols="12" lg="2" v-if="getEmployeeId === routeEmployeeId">
                <v-btn color="primary"  @click="pwUpdate()">비밀번호 수정</v-btn>
              </v-col>
              <v-col cols="12" v-if="getEmployeeId === routeEmployeeId">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.phone"
                  label="전화번호"
                  color="primary"
                  variant="outlined"
                  persistent-hint
                ></v-text-field>
              </v-col>

              <v-col cols="12" v-if="getEmployeeId === routeEmployeeId">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.address"
                  label="자택 주소"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="6"  v-if="role === 'ADMIN'">
                <v-text-field
                  type="text"
                  v-model="employeeDetails.name"
                  label="이름"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="6" v-if="role === 'ADMIN'">
                <v-text-field
                  type="email"
                  v-model="employeeDetails.email"
                  label="이메일"
                  color="primary"
                  variant="outlined"
                  hide-details
                ></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                
              </v-col>
            </v-row>
            <v-btn color="primary" class="mt-5" @click="update()">수정</v-btn>
            <v-btn color="error" class="mt-5 ml-3" @click="employeeDelete" v-if="role === 'ADMIN'">삭제</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
