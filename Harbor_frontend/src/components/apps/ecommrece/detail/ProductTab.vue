<script setup lang="ts">
import { ref } from 'vue';
import ProductDescription from './ProductDescription.vue';
import ProductReview from './ProductReview.vue';

const tab = ref(null);
</script>

<template>
  <v-tabs v-model="tab" color="primary" class="mt-8 border-bottom">
    <v-tab value="one">Description</v-tab>
    <v-tab value="two">Review <v-chip color="secondary" size="small" class="ml-1">350</v-chip></v-tab>
  </v-tabs>
  <div class="mt-5">
    <v-window v-model="tab">
      <v-window-item value="one">
        <ProductDescription />
      </v-window-item>
      <v-window-item value="two">
        <ProductReview />
      </v-window-item>
    </v-window>
  </div>
</template>
