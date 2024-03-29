<script setup lang="ts">
import { onMounted } from 'vue';
import { useEcomStore } from '@/stores/apps/eCommerce';

const store = useEcomStore();
onMounted(() => {
  store.getsubTotal();
  store.getTotal();
  store.getDiscount();
});
</script>
<template>
  <v-card variant="outlined" class="my-3">
    <v-table>
      <thead>
        <tr>
          <th>Order Summary</th>
          <th class="text-right"></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Sub Total</td>
          <td class="text-right font-weight-medium">${{ store.subTotal }}</td>
        </tr>
        <tr>
          <td>Coupon Discount 5%</td>
          <td class="text-right font-weight-medium">- ${{ store.discount }}</td>
        </tr>
        <tr>
          <td>Shipping Charges</td>
          <td class="text-right font-weight-medium">-</td>
        </tr>
        <tr>
          <td>Total</td>
          <td class="text-right font-weight-medium">${{ store.total }}</td>
        </tr>
      </tbody>
    </v-table>
  </v-card>
</template>
