<script setup lang="ts">
import { ref } from 'vue';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Autoplay } from 'swiper/modules';
import 'swiper/css';

import img1 from '@/assets/images/landing/bg-heand.png';

const slides = ref([
  'Easy Customizability',
  'Highly Responsive',
  'Design Consistency',
  'Effective Support',
  'Standardization',
  'Compatibility'
]);

const modules = ref([Autoplay]);
</script>

<template>
  <div class="spacer position-relative overflow-hidden" style="background-color: #111936">
    <v-container class="maxWidth">
      <v-row>
        <v-col>
          <div class="d-flex align-center ga-4">
            <h2 class="feature-text text-white d-inline-block">Choose Berry for</h2>
            <div class="swiper-container d-inline-block">
              <swiper
                :direction="'vertical'"
                class="mySwiper"
                :loop="true"
                :autoplay="{
                  delay: 2500,
                  disableOnInteraction: false
                }"
                :centeredSlides="true"
                :modules="modules"
                :slidesPerView="3"
                style="height: 200px"
              >
                <swiper-slide v-for="slide in slides" :key="slide"
                  ><h1 class="feature-text text-white">{{ slide }}</h1></swiper-slide
                >
              </swiper>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <img :src="img1" alt="hand" class="hand-image" />
  </div>
</template>

<style lang="scss">
.feature-text {
  font-size: 3.125rem;
  @media (max-width: 1200px) {
    font-size: 2.125rem;
  }
  @media (max-width: 800px) {
    font-size: 1.5rem;
  }
  @media (max-width: 480px) {
    font-size: 1rem;
  }
}

.swiper {
  @media (max-width: 1200px) {
    height: 135px !important;
  }
  @media (max-width: 800px) {
    height: 100px !important;
  }
  @media (max-width: 480px) {
    height: 70px !important;
  }
}

.swiper-container {
  width: auto;
}

.swiper-slide {
  opacity: 0.2;
}
.swiper-slide-active {
  opacity: 1;
  h1 {
    color: rgb(var(--v-theme-primary)) !important;
  }
}
.hand-image {
  position: absolute;
  right: 5%;
  max-width: 100%;
  bottom: -40px;
  filter: drop-shadow(0px 0px 50px rgba(33, 150, 243, 0.3));
  height: 390px;
  @media (max-width: 850px) {
    height: 226px;
  }
  @media (max-width: 576px) {
    display: none;
  }
}
</style>
