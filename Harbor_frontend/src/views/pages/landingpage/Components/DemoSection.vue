<script setup lang="ts">
import { ref, onMounted } from 'vue';
// assets
import imgDemo1 from '@/assets/images/landing/pre-apps/slider-light-1.png';
import imgDemo2 from '@/assets/images/landing/pre-apps/slider-light-3.png';
import imgDemo4 from '@/assets/images/landing/pre-apps/slider-light-4.png';
import imgDemo5 from '@/assets/images/landing/pre-apps/slider-light-5.png';
import imgDemo6 from '@/assets/images/landing/pre-apps/slider-light-6.png';

import { LinkIcon } from 'vue-tabler-icons';

import { Carousel, Slide, Navigation } from 'vue3-carousel';

import 'vue3-carousel/dist/carousel.css';

const slideShow = [
  {
    image: imgDemo1,
    name: 'E-commerce App',
    link: 'ecommerce/products'
  },
  {
    image: imgDemo2,
    name: 'Chat App',
    link: 'app/chats'
  },
  {
    image: imgDemo4,
    name: 'Kanban App',
    link: 'app/kanban'
  },
  {
    image: imgDemo5,
    name: 'Mail/Message App',
    link: 'app/mail'
  },
  {
    image: imgDemo6,
    name: 'Social Profile',
    link: 'app/user/social/posts'
  }
];

const relativeURL = ref<string | null>(null);

onMounted(async () => {
  try {
    relativeURL.value = await import.meta.env.BASE_URL;
  } catch (error) {
    console.error('Error url not found:', error);
  }
});

const breakpoints = ref({
  300: {
    itemsToShow: 1.05,
    snapAlign: 'center'
  },
  480: {
    itemsToShow: 1.5,
    snapAlign: 'center'
  },
  600: {
    itemsToShow: 2.2,
    snapAlign: 'center'
  }
});
</script>

<template>
  <div class="spacer bg-containerBg">
    <v-container class="maxWidth">
      <v-row class="justify-center">
        <v-col md="7" cols="12" class="text-center">
          <h2 class="text-sm-h1 text-h2 my-3" style="line-height: 1.2">Explore Concenputal Apps</h2>
          <p>Berry has conceptul working apps like Chat, Inbox, E-commerce, Invoice, Kanban, and Calendar</p>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid>
      <v-row>
        <v-col cols="12">
          <Carousel :wrapAround="true" :breakpoints="breakpoints" class="demo-slider" :autoplay="2000" :transition="500">
            <Slide v-for="(slide, i) in slideShow" :key="i">
              <div class="carousel__item">
                <img alt="applications" :src="slide.image" class="w-100" />
                <v-list class="mt-5 d-inline-block" style="background-color: transparent;" variant="text">
                  <v-list-item :href="`${relativeURL}${slide.link}`"
                  target="_blank">
                    <h3>{{ slide.name }}</h3>
                    <template v-slot:append>
                      <v-btn variant="text" class="ml-2" size="x-small" icon>
                        <LinkIcon size="14" />
                      </v-btn>
                    </template>
                  </v-list-item>
                </v-list>
              </div>
            </Slide>
            <template #addons>
              <Navigation />
            </template>
          </Carousel>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style lang="scss">
.demo-slider {
  .carousel__slide {
    padding: 5px;
  }

  .carousel__slide--sliding {
    transition: 0.5s;
  }

  .carousel__slide {
    opacity: 0.5;
    transform: scale(0.8);
  }

  .carousel__slide--active {
    opacity: 1;
    transform: scale(1);
  }

  .carousel__prev,
  .carousel__next {
    background: rgba(var(--v-theme-surface));
    width: 65px;
    height: 65px;
    border-radius: 50%;
    @media (max-width: 959px) {
      width: 45px;
      height: 45px;
    }
  }
}
</style>
