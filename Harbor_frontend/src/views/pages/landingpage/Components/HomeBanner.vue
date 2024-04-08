<script setup lang="ts">
// assets
import dashboard from '@/assets/images/landing/dashboard.png';
import bg1 from '@/assets/images/landing/bg-hero-block-light.png';
import widget1 from '@/assets/images/landing/widget-1.png';
import widget2 from '@/assets/images/landing/widget-2.png';
import tech from '@/assets/images/landing/tech.svg';



</script>

<template>
  <div class="home-bg position-relative" style="height: calc(100vh - 74px)">
    <v-container class="maxWidth">
      <v-row class="spacer align-center" style="height: calc(100vh - 74px)">
        <v-col cols="12" lg="5" md="5">
          <div class="pr-lg-4 text-md-left text-center">
            <h1 class="bannerText my-5" data-aos="fade-up" data-aos-duration="500">
              한화시스템 BEYOND SW캠프<span class="text-primary">HorboR</span>
            </h1>
            <p class="mb-8" data-aos="fade-up" data-aos-duration="800">
              직원들이 편안한 HR 만들기 위해 HorboR는 노력하고 있습니다.
            </p>
            <div data-aos="fade-up" data-aos-duration="1000">
              <v-btn color="secondary" size="large" class="mr-2" to="/dashboard/default"> 
                <template v-slot:prepend>
                  <v-icon style="margin-bottom: -2px;">mdi-play</v-icon>
                </template>
                들어가기
              </v-btn>
            </div>
            <img data-aos="fade-up" data-aos-duration="1100" :src="tech" alt="technology" class="mt-8" />
          </div>
        </v-col>
        <v-col cols="12" lg="7" md="7" class="d-none d-md-block">
          <div class="position-relative" style="z-index: 1">
            <img :src="dashboard" alt="dashboard" class="HeaderImage" />
            <div class="widget1"><img :src="widget1" alt="widget" class="widgetImages" /></div>
            <div class="widget2"><img :src="widget2" alt="widget" class="widgetImages" /></div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <img :src="bg1" alt="background" class="BgImage d-none d-md-block" />
  </div>
</template>
<style lang="scss">
.home-bg {
  background: linear-gradient(360deg, rgb(var(--v-theme-containerBg)) 1.09%, rgb(var(--v-theme-surface)) 100%);
  min-height: 690px;
  @media (max-width: 959px) {
    min-height: 460px;
  }
}
.BgImage {
  max-width: 100%;
  position: absolute;
  filter: none;
  width: 50%;
  transform-origin: 50% 50%;
  transform: rotateY(0deg);
  right: 0;
  bottom: 0;
}
.bannerText {
  font-size: 3.5rem;
  line-height: 1.2;
}
.HeaderImage {
  max-width: 100%;
  border-radius: 20px;
  transform: scale(1.6);
  transform-origin: 0 25%;
}
.widget1 {
  position: absolute;
  top: -40px;
  right: -170px;
  width: 290px;
  animation: 10s slideY linear infinite;
}
.widget2 {
  position: absolute;
  bottom: -90px;
  left: 300px;
  width: 280px;
  animation: 10s slideY linear infinite;
  animation-delay: 2s;
}
.widgetImages {
  max-width: 100%;
  filter: drop-shadow(0px 0px 50px rgb(33 150 243 / 30%));
}
@media (max-width: 800px) {
  .bannerText {
    font-size: 2rem;
  }
}
</style>
