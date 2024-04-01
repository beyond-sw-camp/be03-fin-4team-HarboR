<script setup lang="ts">
// assets
import imageEmpty from '@/assets/images/e-commerce/empty.svg';
</script>

<template>
  <v-row class="justify-center">
    <v-col class="text-center" lg="6">
      <v-img :src="imageEmpty" alt="cover" />
      <h1 class="text-h1">Cart is Empty</h1>
      <p>Look like you have no items in your shopping cart.</p>
      <v-btn class="mt-3" to="/ecommerce/products" variant="flat" color="primary">Go Back to Shopping</v-btn>
    </v-col>
  </v-row>
</template>
