<script setup lang="ts">
import { ref ,watch} from 'vue';
import { useAuthStore } from '@/stores/auth';
import { Form } from 'vee-validate';

const valid = ref(false);
const show1 = ref(false);
//const logform = ref();
const password = ref('');
const selectedInput = ref('email');
const email = ref('');
const employeeId = ref('');
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
</script>

<template>
  <v-row>
    <v-col class="d-flex align-center">
      <v-divider class="custom-devider" />
      <v-divider class="custom-devider" />
    </v-col>
  </v-row>
  <select class="mt-2 " v-model="selectedInput">
    <option value="email">이메일</option>
    <option value="employeeId">사원번호</option>
  </select>
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
        <a href="javascript:void(0)" class="text-primary text-decoration-none">비밀번호 찾기</a>
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
    <v-btn variant="plain" to="/auth/register3" class="mt-2 text-capitalize mr-n2">회원가입</v-btn>
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
