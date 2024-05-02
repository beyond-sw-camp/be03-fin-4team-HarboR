<script setup lang="ts">
import { ref ,watch } from 'vue';
import axios from '@/utils/axios';
import { useAuthStore } from '@/stores/auth';
import { Form } from 'vee-validate';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const valid = ref(false);
const show1 = ref(false);
//const logform = ref();
const password = ref('');
const selectedInput = ref('email');
const selectedOption = ref('findId');
const email = ref('');
const name = ref('');
const employeeId = ref('');
const modalEmail = ref('');
const modalEmployeeId = ref('')
const errormessage = ref('')
const passwordRules = ref([
  (v: string) => !!v || '비밀번호를 입력해주세요',
  (v: string) => (v && v.length >= 8 && v.length <= 20) || '비밀번호를 8자에서 20자 사이로 입력해주세요'
]);
const emailRules = ref([(v: string) => !!v || '이메일을 입력해주세요', (v: string) => /.+@.+\..+/.test(v) || '이메일 형식에 맞춰주세요']);

/* eslint-disable @typescript-eslint/no-explicit-any */
function validate(values: any, { setErrors }: any) {
  const authStore = useAuthStore();
  return authStore.login(email.value,employeeId.value, password.value).catch((error) => setErrors({ apiError: error }));
}
watch(selectedInput, () => {
  email.value = '';
  employeeId.value = '';
});
watch(selectedOption,()=> {
  email.value = '';
  name.value = '';
})
const modalOpen = ref(false);
const modalSuccessOpen = ref(false);

const openModal = () => {
  modalOpen.value = true;
};
const modalOpenSuccess = (employeeId) => {
  modalEmployeeId.value = employeeId;
  modalSuccessOpen.value = true;
}
const findMyEmployeeId = async () => {
  if (modalEmail.value && name.value) { // 이메일과 이름이 모두 입력되었는지 확인
    try {
      const response = await axios.post(`${baseUrl}/login/account/findId`, {
        email: modalEmail.value,
        name: name.value,
      });
      const employeeId = response.data.result;
      modalOpenSuccess(employeeId);
    } catch (error) {
      const message = error.response.data.error_message || "이메일을 적어주세요";
      modalOpenSuccess(message);
    }
  } else {
    const message = "이메일과 이름을 모두 입력하세요.";
    modalOpenSuccess(message);
  }
};
const resetPassword = async () => {
  console.log(modalEmail.value)
  try{
    const response = await axios.get(`${baseUrl}/login/account/TempPassword/${modalEmail.value}`, {
    })
    const message = "해당 이메일로 임시 비밀번호가 발급 되었습니다."
    modalOpenSuccess(message)
  } catch(error){
    const message = error.response.data.error_message;
    modalOpenSuccess(message)
  }
}
const closeModal = () => {
  if(modalSuccessOpen.value){
    modalSuccessOpen.value = false; // 아이디 찾기 성공 모달 창이 열려있으면 닫기
    if (modalOpen.value) {
    modalOpen.value = false; // 아이디 찾기 모달 창이 열려있으면 닫기
    }
  }
    modalOpen.value = false; // 아이디 찾기 모달 창이 열려있으면 닫기

};

</script>

<template>

  <v-row>
    <v-col class="d-flex align-center">
      <v-divider class="custom-devider" />
    </v-col>
  </v-row>
    <div>
      <v-switch
        v-model="selectedInput"
        :label="` ${selectedInput === 'email' ? '이메일' : '사원번호'}`"
        false-value="employeeId"
        true-value="email"
        hide-details
      ></v-switch>
    </div>
    <v-dialog v-model="modalOpen" max-width="400">
      <v-card class="d-flex justify-center">
        <v-card-title>아이디 찾기 / 비밀번호 재발급</v-card-title>
        <v-card-text>
          <v-form @submit.prevent="selectedOption === 'findId' ? findMyEmployeeId() : resetPassword()">
            <div>
              <v-switch
                v-model="selectedOption"
                :label="` ${selectedOption === 'findId' ? '아이디 찾기' : '비밀번호 재발급'}`"
                false-value="findId"
                true-value="resetPassword"
                hide-details
              ></v-switch>
            </div>
            <v-text-field v-model="modalEmail" :rules="emailRules" label="이메일" required></v-text-field>
            <v-text-field v-if="selectedOption === 'findId'" v-model="name" :rules="nameRules" label="이름" required></v-text-field>
            <v-card-actions class="d-flex justify-end">
              <v-btn type="submit" color="primary" :disabled="!modalEmail">찾기</v-btn>
              <v-btn color="primary" @click="closeModal()">취소</v-btn>
            </v-card-actions>
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog v-model="modalSuccessOpen" max-width="400"> <!-- max-width 변경 -->
      <v-card class="d-flex justify-center"> <!-- 컨텐츠를 가운데로 정렬 -->
        <v-card-title> 아이디 찾기 / 비밀번호 재 발급</v-card-title>
    <v-card-text>
      <div v-if="modalEmployeeId">{{ modalEmployeeId }}</div> <!-- EmployeeId 표시 -->
      <div>{{ errormessage }} </div>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="closeModal()">확인</v-btn>
    </v-card-actions>
      </v-card>
    </v-dialog>
  <Form @submit="validate" class="mt-7 loginForm" v-slot="{ errors, isSubmitting }">
    <v-text-field v-if="selectedInput === 'email'" v-model="email" :rules="emailRules" label="이메일" class="mt-4 mb-8"
      required density="comfortable" hide-details="auto" variant="outlined" color="primary"></v-text-field>
    <v-text-field v-else-if="selectedInput === 'employeeId'" v-model="employeeId" label="사원번호" class="mt-4 mb-8"
      required density="comfortable" hide-details="auto" variant="outlined" color="primary"></v-text-field>
    <v-text-field v-model="password" :rules="passwordRules" label="비밀번호" required density="comfortable"
      variant="outlined" color="primary" hide-details="auto" :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show1 ? 'text' : 'password'" @click:append-inner="show1 = !show1" class="pwdInput"></v-text-field>

      <div class="d-sm-flex align-center mt-2 mb-7 mb-sm-0">
        <div class="ml-auto">
          <a href="javascript:void(0);" class="text-primary text-decoration-none" @click="openModal()">아이디 찾기</a>
        </div>
      </div>
    <v-btn color="secondary" :loading="isSubmitting" block class="mt-2" variant="flat" size="large" :disabled="valid"
      type="submit">
      로그인</v-btn>
    <div v-if="errors.apiError" class="mt-2">
      <v-alert color="error">{{ errors.apiError }}</v-alert>
    </div>  
  </Form>
  <div class="mt-5 text-right">
    <v-divider />
    <v-btn variant="plain" to="/auth/register" class="mt-2 text-capitalize mr-n2">회원가입</v-btn>
  </div>
</template>
<style lang="scss">
.custom-devider {
  border-color: rgba(0, 0, 0, 0.08) !important;
}

.googleBtn {
  border-color: rgba(0, 0, 0, 0.08);
  margin: 30px 0 20px 0;
}

.outlinedInput .v-field {
  border: 1px solid rgba(0, 0, 0, 0.08);
  box-shadow: none;
}

.orbtn {
  padding: 2px 40px;
  border-color: rgba(0, 0, 0, 0.08);
  margin: 20px 15px;
}

.loginForm {
  .v-text-field .v-field--active input {
    font-weight: 500;
  }
}
</style>
