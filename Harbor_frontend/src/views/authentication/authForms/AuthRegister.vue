<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import { router } from '@/router';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const checkbox = ref(false);
const show1 = ref(false);
const password = ref('');
const email = ref('');
const Regform = ref();
const name = ref('');
const birth = ref('');
const nameRules = ref([
  (v: string) => !!v || '이름을 입력해주세요'
]);
const birthRules = ref([
  (v: string) => !!v || '생년월일을 입력해주세요',
  (v: string) => (v && v.length === 6) || 'EX : 980924'
]);
const passwordRules = ref([
  (v: string) => !!v || '비밀번호를 입력해주세요',
  (v: string) => (v && v.length >= 8 && v.length <= 20) || '비밀번호를 8자에서 20자 사이로 입력해주세요'
]);
const emailRules = ref([(v: string) => !!v || '이메일을 입력해주세요', (v: string) => /.+@.+\..+/.test(v) || '이메일 형식에 맞춰주세요']);

async function validate() {
  try {
    // v-form 컴포넌트의 validate 메서드를 호출하여 입력 값의 유효성을 검사합니다.
    const isValid = await Regform.value.validate();
    // 유효성 검사에 실패하면 함수를 종료합니다.
    if (!isValid) {
      alert("형식을 모두 채워주세요")
      return;
    }
    // 회원가입 로직을 수행합니다. 여기에 필요한 HTTP 요청 등의 로직을 추가하면 됩니다.
    // 예시로 HTTP 요청을 사용하겠습니다.
    const response = await axios.post(`${baseUrl}/login/account/signup`, {
      name: name.value,
      email: email.value,
      password: password.value,
      birth: birth.value,
    });
    // 회원가입이 성공하면 사용자에게 알림을 표시하거나 다른 작업을 수행할 수 있습니다.
    alert('회원가입이 완료되었습니다.');
    router.push('/auth/login');
  } catch (error) {
    console.error('회원가입 중 오류 발생:', error);
    alert('회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
}

</script>

<template>
  <v-form ref="Regform" lazy-validation action="/dashboards/analytical" class="mt-7 loginForm">
        <v-text-field
          v-model="name"
          :rules="nameRules"
          density="comfortable"
          hide-details="auto"
          variant="outlined"
          color="primary"
          label="이름"
        ></v-text-field>
    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="이메일"
      class="mt-4 mb-4"
      required
      density="comfortable"
      hide-details="auto"
      variant="outlined"
      color="primary"
    ></v-text-field>
    <v-text-field
      v-model="password"
      :rules="passwordRules"
      label="비밀번호"
      required
      density="comfortable"
      variant="outlined"
      color="primary"
      hide-details="auto"
      :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show1 ? 'text' : 'password'"
      @click:append-inner="show1 = !show1"
      class="pwdInput"
    ></v-text-field>
    <v-text-field
      v-model="birth"
      :rules="birthRules"
      label="생년월일"
      required
      class="mt-4 mb-4"
      density="comfortable"
      hide-details="auto"
      variant="outlined"
      color="primary"
    ></v-text-field>
    <!-- <div class="d-sm-inline-flex align-center mt-2 mb-7 mb-sm-0 font-weight-bold">
      <v-checkbox
        v-model="checkbox"
        :rules="[(v: any) => !!v || 'You must agree to continue!']"
        label="이용 약관에 동의하시겠습니까?"
        required
        color="primary"
        class="ms-n2"
        hide-details
      ></v-checkbox>
      <a href="https://www.kakao.com/main" class="ml-1 text-lightText">이용약관</a>
    </div> -->
    <v-btn color="secondary" block class="mt-2" variant="flat" size="large" @click="validate()">승인 요청</v-btn>
  </v-form>
  <div class="mt-5 text-right">
    <v-divider />
    <v-btn variant="plain" to="/auth/login" class="mt-2 text-capitalize mr-n2">이미 계정이 있나요?</v-btn>
    <h5 class="text-center">승인 요청 버튼 클릭 시 관리자 승인 후 <br> 이메일 인증을 통해 회원가입이 완료됩니다. 
</h5>
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
</style>
