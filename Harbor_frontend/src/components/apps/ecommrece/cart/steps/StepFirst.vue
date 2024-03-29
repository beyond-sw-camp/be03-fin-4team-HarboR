<script setup lang="ts">
import { computed } from 'vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import CartEmpty from '../CartEmpty.vue';
import OrderSummaryVue from './OrderSummary.vue';

const store = useEcomStore();
const cart = computed(() => store.cart);
</script>
<template>
  <div v-if="cart.length > 0">
    <h5 class="text-h5 my-8">Cart Item ({{ cart.length }})</h5>
    <v-divider />
    <v-table>
      <thead>
        <tr>
          <th class="text-left">Product</th>
          <th class="text-left">Price</th>
          <th class="text-left">Quantity</th>
          <th class="text-left">Total</th>
          <th class="text-right"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in cart" :key="item.name">
          <td>
            <div class="d-flex align-center my-3 ga-2">
              <img alt="product" class="rounded-md custom-img-box" :src="item.image" />
              <div class="ma-2">
                <h4 class="text-h5">{{ item.name }}</h4>
                <small>size: 8 | Color: Dark Red</small>
              </div>
            </div>
          </td>
          <td>
            <h4 class="text-h5">${{ item.salePrice }}</h4>
            <p class="text-decoration-line-through text-medium-emphasis">${{ item.offerPrice }}</p>
          </td>
          <td>
            <v-btn-toggle variant="outlined" divided color="primary">
              <v-btn size="small" @click="store.decrementQty(item.id)" :disabled="item.qty < 2">
                <MinusIcon size="18" />
              </v-btn>

              <v-btn size="small">
                {{ item.qty }}
              </v-btn>

              <v-btn size="small" @click="store.incrementQty(item, cart)">
                <PlusIcon size="18" />
              </v-btn>
            </v-btn-toggle>
          </td>
          <td class="text-h5">${{ item.salePrice * item.qty }}</td>
          <td class="text-right">
            <v-btn small icon flat><TrashIcon size="18" @click="store.deleteCart(item.id)" /></v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>
    <OrderSummaryVue />
  </div>
  <div v-else class="d-flex justify-center">
    <CartEmpty />
  </div>
</template>
<style>
.custom-img-box {
  width: 60px;
  height: 60px;
}
</style>
