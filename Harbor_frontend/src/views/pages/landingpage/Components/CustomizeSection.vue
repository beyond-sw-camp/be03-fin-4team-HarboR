<script setup lang="ts">
import { shallowRef } from 'vue';
import SvgSprite from '@/components/shared/SvgSprite.vue';
// assets
import img1 from '@/assets/images/landing/customization-left.png';
import img2 from '@/assets/images/landing/customization-right.png';

import { CircleCheckIcon } from 'vue-tabler-icons';

const items = shallowRef([
  {
    title: 'A straightforward and simple folder structure.'
  },
  {
    title: 'Code that is organized in a clear and logical manner.'
  },
  {
    title: 'Setting up Typography and Color schemes is easy and effortless.'
  },
  {
    title: 'Multiple layout options that can be easily adjusted.'
  },
  {
    title: 'A theme that can be easily configured on a single page.'
  }
]);

const items1 = shallowRef([
  {
    title: 'Professional Kit for Designer'
  },
  {
    title: 'Properly Organised Pages'
  },
  {
    title: 'Dark/Light Design'
  },
  {
    title: '*Figma file included only in Plus & Extended Licenses.'
  },
  {
    title: 'A theme that can be easily configured on a single page.'
  }
]);
</script>
<template>
  <div class="spacer bg-containerBg">
    <v-container class="maxWidth">
      <v-row class="justify-center align-center">
        <v-col md="5" cols="12">
          <v-row class="justify-center">
            <v-col md="12" sm="6" cols="9">
              <v-img :src="img1" alt="cover" />
            </v-col>
          </v-row>
        </v-col>
        <v-col md="5" cols="12" offset-lg="1">
          <h5 class="text-sm-h1 text-h2 font-weight-medium mb-2" style="line-height: 1.2">Easy Developer Experience</h5>
          <h6 class="text-h4 font-weight-regular mb-3">Berry has made it easy for developers of any skill level to use their product.</h6>
          <v-list bg-color="containerBg">
            <v-list-item :title="item.title" v-for="(item, index) in items" class="px-0" :key="index">
              <template v-slot:prepend>
                <CircleCheckIcon size="20" class="mr-3 text-secondary" />
              </template>
            </v-list-item>
          </v-list>
        </v-col>
      </v-row>
      <v-row class="justify-center align-center mt-4">
        <v-col md="5" cols="12" class="order-md-0 order-3">
          <h5 class="text-sm-h1 text-h2 font-weight-medium mb-2" style="line-height: 1.2">Figma Design System</h5>
          <h6 class="text-h4 font-weight-regular mb-3">
            Streamlining the development process and saving you time and effort in the initial design phase.
          </h6>
          <v-list bg-color="containerBg">
            <v-list-item :title="item.title" v-for="(item, index) in items1" class="px-0" :key="index">
              <template v-slot:prepend>
                <CircleCheckIcon size="20" class="mr-3 text-secondary" />
              </template>
            </v-list-item>
          </v-list>
          <v-btn
            color="primary"
            class="mt-4"
            variant="flat"
            href="https://www.figma.com/file/2u2TmauA6lanVMYiywzS1o/berry-figma-v3.0?node-id=0%3A1"
            target="_blank"
          >
            <template v-slot:prepend>
              <SvgSprite name="layer-twotone" style="width: 20px; height: 20px" />
            </template>
            Explore Figma
          </v-btn>
        </v-col>
        <v-col md="5" cols="12" offset-lg="1" class="order-md-3">
          <v-row class="justify-center">
            <v-col md="12" sm="6" cols="9">
              <v-img :src="img2" alt="cover" />
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
