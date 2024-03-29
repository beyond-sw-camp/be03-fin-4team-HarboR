<script setup lang="ts">
import { computed } from 'vue';
import { useEcomStore } from '@/stores/apps/eCommerce';

const store = useEcomStore();
const getCart = computed(() => {
  return store.cart;
});
</script>
<template>
  <v-btn color="warning" variant="flat" large class="cartBtn" to="/ecommerce/checkout">
    <v-badge bordered color="error" :content="getCart?.length" offset-x="-5" offset-y="-5"
      ><v-icon size="large"> mdi-cart-outline </v-icon></v-badge
    >
  </v-btn>
</template>
<style>
.cartBtn.v-btn {
  height: 52px;
  position: fixed;
  right: -2px;
  top: 40%;
  z-index: 9;
}
</style>
