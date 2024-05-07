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
const passwordConfirmation = ref('');
const show2 = ref(false)
const isFormValid = ref(false);

const nameRules = ref([
  (v: string) => !!v || '이름을 입력해주세요'
]);
const passwordConfirmationRules = ref([
  (v: string) => !!v || '비밀번호 재입력을 입력해주세요',
  (v: string) => v === password.value || '비밀번호와 일치하지 않습니다'
]);

const birthRules = ref([
  (v: string) => !!v || '생년월일을 입력해주세요',
  (v: string) => (v && v.length === 6) || 'EX : 980924'
]);
const passwordRules = ref([
  (v: string) => !!v || '비밀번호를 입력해주세요',
  (v: string) => /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(v) || '특수문자, 영어, 숫자를 포함하여 8자에서 20자 사이로 입력해주세요'
]);
const emailRules = ref([(v: string) => !!v || '이메일을 입력해주세요', (v: string) => /.+@.+\..+/.test(v) || '이메일 형식에 맞춰주세요']);

async function validate() {
  try {
    const isValid = await Regform.value.validate();
    isFormValid.value = isValid;
    if (isValid === false) {
        console.log(2);
        alert("형식을 모두 채워주세요");
      return;
    }
    if (password.value !== passwordConfirmation.value) {
      alert("비밀번호와 비밀번호 재설정이 일치하지 않습니다.");
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
    alert(error.response.data.error_message);
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
        v-model="passwordConfirmation"
        :rules="passwordConfirmationRules"
        label="비밀번호 재입력"
        required
        density="comfortable"
        variant="outlined"
        color="primary"
        hide-details="auto"
        :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
        :type="show2 ? 'text' : 'password'"
        @click:append-inner="show2 = !show2"
        class="pwdInput2"
        style="margin-top: 14px;"
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
