<script setup lang="ts">
import { ref } from 'vue';

const valid = ref(false);
const logform = ref();
const otp = ref('');
</script>

<template>
  <h5 class="text-h5 text-center my-4 text-medium-emphasis">We’ve send you code on jone.****@company.com</h5>
  <v-form ref="logform" lazy-validation v-model="valid" class="mt-7 loginForm code-verification">
    <v-otp-input type="number" v-model="otp" length="4" class="mb-5 pa-0" single-line height="45"></v-otp-input>
    <v-btn color="primary" block class="mt-2" variant="flat" size="large" type="submit">Continue </v-btn>
  </v-form>
  <div class="d-sm-flex align-start justify-space-between my-4">
    <div class="text-subtitle-1 font-weight-regular">Did not receive the email? Check your spam filter, or</div>
    <v-btn variant="plain" color="primary" to="/#" class="text-capitalize mr-n2 mt-2 mt-sm-0 px-sm-3 px-0">Resend Code</v-btn>
  </div>
  <div class="mt-5 text-right">
    <v-divider />
    <v-btn variant="plain" to="/auth/code-verify" class="mt-2 text-capitalize text-wrap mr-n2 px-sm-3 px-2"
      >Did not receive the email? <span class="d-none d-sm-block">Check your spam filter, or</span></v-btn
    >
    <v-btn color="secondary" block class="mt-2" variant="outlined" size="large">Resend Code</v-btn>
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

.code-verification {
  .v-field__input {
    text-align: center;
  }
}

.orbtn {
  padding: 2px 40px;
  border-color: rgba(0, 0, 0, 0.08);
  margin: 20px 15px;
}

.loginForm {
  .v-otp-input {
    padding: 0;
    .v-otp-input__content {
      max-width: 100%;
      padding: 0;
    }
    .v-otp-input__field {
      font-size: 13px;
    }
  }
}
</style>
