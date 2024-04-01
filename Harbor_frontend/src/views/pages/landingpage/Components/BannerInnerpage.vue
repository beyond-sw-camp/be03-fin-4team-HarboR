<script setup lang="ts">
// assets
import mailImg from '@/assets/images/landing/img-contact-mail.svg';
const props = defineProps({
  title: String,
  subtitle: String
});
</script>

<template>
  <v-main class="headerBg mx-0">
    <v-container class="maxWidth">
      <div class="my-8 pb-8 text-center">
        <h1 class="text-display1 text-white">{{ props.title }}</h1>
        <p class="v-col-md-6 offset-md-3 text-white">
          {{ props.subtitle }}
        </p>
        <div class="position-relative v-col-12 d-none d-sm-block">
          <img :src="mailImg" alt="mailImage" class="mailImg" />
        </div>
      </div>
      <div class="pt-5">
        <slot></slot>
      </div>
    </v-container>
  </v-main>
</template>
<style lang="scss">
.headerBg {
  background-image: url('@/assets/images/landing/header-bg.jpg');
  background-size: 100% 600px;
  background-attachment: fixed;
  background-repeat: no-repeat;

  padding-top: 74px;
}
.text-display1 {
  font-size: 3.5rem;
}
.mailImg {
  position: absolute;
  top: -40px;
  right: 0px;
  width: 400px;
  max-width: 100%;
  animation: 5s wings ease-in-out infinite;
}
</style>
