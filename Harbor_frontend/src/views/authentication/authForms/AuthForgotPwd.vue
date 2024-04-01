<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const valid = ref(false);
const logform = ref();
const email = ref('');
const emailRules = ref([(v: string) => !!v || 'E-mail is required', (v: string) => /.+@.+\..+/.test(v) || 'E-mail must be valid']);

const router = useRouter();

function validate() {
  logform.value.validate();
  if (logform.value) {
    router.push('/starter');
  }
}
</script>

<template>
  <h4 class="text-h4 my-4 mb-8 font-weight-regular text-medium-emphasis">
    Enter your email address below and we'll send you password reset OTP.
  </h4>
  <v-form ref="logform" lazy-validation v-model="valid" action="/starter" @submit.prevent="validate" class="mt-7 loginForm">
    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="Email Address / Username"
      class="mt-4 mb-8"
      required
      hide-details="auto"
      variant="outlined"
      color="primary"
    ></v-text-field>

    <v-btn color="secondary" block class="mt-2" variant="flat" size="large" :disabled="valid" type="submit">Send Mail </v-btn>
  </v-form>
  <div class="mt-5 text-right">
    <v-divider />
    <v-btn variant="plain" to="/auth/login1" class="mt-2 text-capitalize mr-n2">Already have an account?</v-btn>
  </div>
</template>
