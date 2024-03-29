<script setup lang="ts">
import { ref } from 'vue';
// assets
import Angular from '@/assets/images/landing/frameworks/angular.svg';
import Bootstrap from '@/assets/images/landing/frameworks/bootstrap.svg';
import Django from '@/assets/images/landing/frameworks/django.svg';
import Codeigniter from '@/assets/images/landing/frameworks/codeigniter.svg';
import DotNet from '@/assets/images/landing/frameworks/dot-net.svg';
import Flask from '@/assets/images/landing/frameworks/flask.svg';
import Shopify from '@/assets/images/landing/frameworks/shopify.svg';
import FullStack from '@/assets/images/landing/frameworks/full-stack.svg';
import Vue from '@/assets/images/landing/frameworks/vue.svg';

import { Carousel, Slide, Pagination } from 'vue3-carousel';

import 'vue3-carousel/dist/carousel.css';

const slideShow = [
  {
    title: 'Bootstrap 5',
    logo: Bootstrap,
    link: 'https://links.codedthemes.com/VpESi'
  },
  {
    title: 'Angular',
    logo: Angular,
    link: 'https://links.codedthemes.com/iNpKo'
  },
  {
    title: 'CodeIgniter',
    logo: Codeigniter,
    link: 'https://links.codedthemes.com/AdRiB'
  },
  {
    title: '.Net',
    logo: DotNet,
    link: 'https://links.codedthemes.com/wkQEu'
  },
  {
    title: 'Shopify',
    logo: Shopify,
    link: '/',
    isUpcoming: true
  },
  {
    title: 'Vuetify 3',
    logo: Vue,
    link: 'https://links.codedthemes.com/RqhwV'
  },
  {
    title: 'Full Stack',
    logo: FullStack,
    link: 'https://links.codedthemes.com/quhuG'
  },
  {
    title: 'Django',
    logo: Django,
    link: 'https://links.codedthemes.com/Wfbiy'
  },
  {
    title: 'Flask',
    logo: Flask,
    link: 'https://links.codedthemes.com/quhuG'
  }
];

const breakpoints = ref({
  300: {
    itemsToShow: 1,
    snapAlign: 'center'
  },
  400: {
    itemsToShow: 2,
    snapAlign: 'center'
  },
  600: {
    itemsToShow: 3,
    snapAlign: 'center'
  },
  768: {
    itemsToShow: 4,
    snapAlign: 'center'
  },
  992: {
    itemsToShow: 4.6,
    snapAlign: 'center'
  },
  1199: {
    itemsToShow: 5,
    snapAlign: 'center'
  },
  1360: {
    itemsToShow: 7,
    snapAlign: 'center'
  }
});
</script>

<template>
  <div class="spacer">
    <v-container class="maxWidth">
      <v-row class="justify-center">
        <v-col md="12" cols="12" class="text-center">
          <h2 class="text-sm-h1 text-h2 my-3" style="line-height: 1.2">Berry is now available in multiple frameworks*</h2>
          <p>
            Each framework is sold separately on different platforms. Click to
            <a href="https://codedthemes.gitbook.io/berry/berry-eco-system" target="_blank" class="text-primary more-link">Learn more</a>
          </p>
        </v-col>
      </v-row>
    </v-container>
    <v-container fluid>
      <v-row>
        <v-col cols="12">
          <Carousel :wrapAround="true" class="framework-carousel" :breakpoints="breakpoints" :autoplay="2000" :transition="500">
            <Slide v-for="(slide, i) in slideShow" :key="i">
              <div class="carousel__item">
                <v-card class="bg-containerBg framework-box mb-3" variant="flat">
                  <v-card-item class="py-8">
                    <a :href="slide.link" target="_" class="text-darkText text-decoration-none">
                      <img alt="framework" :src="slide.logo" style="max-width: 100px; height: 40px" />
                      <h3>{{ slide.title }}</h3>
                      <v-chip
                        v-if="slide.isUpcoming"
                        size="x-small"
                        variant="outlined"
                        class="position-absolute upcoming-chip bg-surface"
                        color="primary"
                      >
                        Coming soon
                      </v-chip>
                    </a>
                  </v-card-item>
                </v-card>
              </div>
            </Slide>
            <template #addons>
              <Pagination />
            </template>
          </Carousel>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style lang="scss">
.framework-box {
  min-width: 180px;
}
.framework-carousel {
  .carousel__pagination {
    margin-top: 50px;
    @media (max-width: 600px) {
      margin-top: 10px;
    }
    .carousel__pagination-item {
      .carousel__pagination-button {
        &::after {
          width: 10px;
          height: 10px;
          border-radius: 100%;
          opacity: 0.25;
        }
        &.carousel__pagination-button--active {
          &::after {
            opacity: 1;
            background: rgb(var(--v-theme-primary));
          }
        }
      }
    }
  }
}
.upcoming-chip {
  bottom: -10px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
