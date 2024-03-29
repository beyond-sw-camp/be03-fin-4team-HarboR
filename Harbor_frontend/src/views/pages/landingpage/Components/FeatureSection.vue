<script setup lang="ts">
import { shallowRef } from 'vue';

import offer1 from '@/assets/images/landing/offer/offer-1.png';
import offer2 from '@/assets/images/landing/offer/offer-2.png';
import offer3 from '@/assets/images/landing/offer/offer-3.png';
import offer4 from '@/assets/images/landing/offer/offer-4.png';
import offer5 from '@/assets/images/landing/offer/offer-5.png';
import offer6 from '@/assets/images/landing/offer/offer-6.png';

const items = shallowRef([
  {
    title: 'Beautiful User Interface',
    image: offer1,
    content:
      'Berry can improve the user experience of your web application by providing a clear and intuitive layout, and consistent look and feel.'
  },
  {
    title: 'Time and Cost Savings',
    image: offer2,
    content:
      'Berry can save developers time and effort by providing a pre-built user interface, allowing them to focus on other aspects of the project.'
  },
  {
    title: 'Reduce Development Complexity',
    image: offer3,
    content: 'Berry simplifies admin panel development with easy theme setup and clear code with flexible layouts options.'
  },
  {
    title: 'Improved Scalability',
    image: offer4,
    content:
      'Berry uses scalable technologies and resources to ensure that your admin panel remains efficient and effective as your needs evolve.'
  },
  {
    title: 'Well-Documented and Supported',
    image: offer5,
    content: 'With a range of resources including user guides, tutorials, and FAQs to help users understand and effectively use the Berry.'
  },
  {
    title: 'Performance Centric',
    image: offer6,
    content: 'Berry is a performance-centric dashboard template that is designed to deliver optimal performance for your admin panel.'
  }
]);
</script>

<template>
  <div class="spacer">
    <v-container class="maxWidth">
      <v-row class="justify-center">
        <v-col md="6" cols="12" class="text-center">
          <h2 class="text-sm-h1 text-h2 my-3" style="line-height: 1.2">What does Berry offer?</h2>
          <p>
            Berry is a reliable choice for your admin panel needs, offering a wide range of features to easily manage your backend panel
          </p>
        </v-col>
        <v-col md="12" cols="12"></v-col>
        <v-col md="4" sm="6" cols="12" data-aos="fade-up" data-aos-duration="800" v-for="(item, index) in items" :key="index">
          <v-card variant="outlined" class="pa-5 bg-containerBg">
            <v-avatar size="56">
              <img :src="item.image" width="56" height="56" alt="Julia" class="rounded-sm" />
            </v-avatar>
            <h3 class="text-h3 mt-4 font-weight-medium">{{ item.title }}</h3>
            <p class="my-3 text-body-1">{{ item.content }}</p>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
