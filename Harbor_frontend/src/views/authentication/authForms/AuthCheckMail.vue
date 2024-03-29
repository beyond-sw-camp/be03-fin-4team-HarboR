<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const valid = ref(false);
const logform = ref();

const router = useRouter();

function validate() {
  logform.value.validate();
  if (logform.value) {
    router.push('/starter');
  }
}
</script>

<template>
  <h4 class="text-h4 my-4 font-weight-regular text-medium-emphasis">We have sent a password recover instructions to your email.</h4>
  <v-form ref="logform" lazy-validation v-model="valid" action="/starter" @submit.prevent="validate" class="mt-5 loginForm">
    <v-btn color="secondary" block class="mt-2" variant="flat" size="large" :disabled="valid" type="submit">Open Mail </v-btn>
  </v-form>
</template>
