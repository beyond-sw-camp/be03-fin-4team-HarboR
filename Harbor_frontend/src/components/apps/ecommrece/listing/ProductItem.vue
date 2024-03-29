<script setup lang="ts">
import { ref } from 'vue';
const props = defineProps({
  name: String,
  image: String,
  desc: String,
  rating: Number,
  salePrice: Number,
  offerPrice: Number,
  goto: Number || String || Object || Array
});
const successsnackbar = ref(false);
const rate = ref(props.rating);
</script>

<template>
  <v-card variant="flat" class="rounded white overflow-hidden">
    <router-link :to="`/ecommerce/product/detail/${goto}`">
      <img alt="product" :src="image" class="w-100" />
    </router-link>
    <v-card-item>
      <v-card-title class="text-h4">{{ name }}</v-card-title>
    </v-card-item>

    <v-card-text>
      <p class="text-medium-emphasis mb-3 descriptionH">{{ desc }}</p>
      <div class="text-medium-emphasis align-center d-flex mb-3 ga-2">
        <v-rating color="warning" half-increments size="small" v-model="rate" density="compact"> </v-rating>
        <small>({{ rating }}+)</small>
      </div>
      <div class="d-flex align-center ga-2">
        <h4 class="text-h4">${{ salePrice }}</h4>
        <p class="text-decoration-line-through text-medium-emphasis">${{ offerPrice }}</p>
        <v-btn color="primary" class="ml-auto" size="small" @click="$emit('handlecart', (successsnackbar = true))"
          ><BasketIcon size="18"
        /></v-btn>
      </div>
    </v-card-text>
    <v-snackbar variant="flat" location="top right" color="success" rounded="md" class="text-white" v-model="successsnackbar">
      Item Added to The Cart
    </v-snackbar>
  </v-card>
</template>
<style>
.descriptionH {
  height: 40px;
  overflow: hidden;
}
</style>
