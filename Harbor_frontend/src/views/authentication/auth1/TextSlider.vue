<script setup lang="ts">
import { ref } from 'vue';

type slideType = {
  title: string;
  description: string;
};

const slides = ref<slideType[]>([
  {
    title: 'Components Based Design System',
    description: 'Powerful and easy to use multipurpose theme'
  },
  {
    title: 'Components Based Design System',
    description: 'Powerful and easy to use multipurpose theme'
  },
  {
    title: 'Components Based Design System',
    description: 'Powerful and easy to use multipurpose theme'
  }
]);
</script>
<template>
  <v-carousel color="darkText" cycle height="190" hide-delimiter-background class="custom-delimiter" :show-arrows="false">
    <v-carousel-item v-for="(slide, i) in slides" :key="i">
      <div class="text-center">
        <h1 class="text-h1">{{ slide.title }}</h1>
        <p class="text-medium-emphasis text-subtitle-1">{{ slide.description }}</p>
      </div>
    </v-carousel-item>
  </v-carousel>
</template>

<style lang="scss">
.custom-delimiter {
  .v-btn {
    font-size: 5px;
  }
}
</style>
