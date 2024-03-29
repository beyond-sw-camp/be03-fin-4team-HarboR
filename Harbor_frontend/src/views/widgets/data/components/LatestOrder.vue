<script setup lang="ts">
import { shallowRef } from 'vue';

import Product1 from '@/assets/images/products/1.jpg';
import Product2 from '@/assets/images/products/2.jpg';
import Product3 from '@/assets/images/products/3.jpg';
import Product4 from '@/assets/images/products/4.jpg';

const products = shallowRef([
  {
    orderid: 81412314,
    customer: 'John Deo',
    avatar: Product1,
    product: 'Moto G5',
    total: '10',
    date: '17-2-2017',
    status: 'pending'
  },
  {
    orderid: 68457898,
    customer: 'Jenny William',
    avatar: Product2,
    product: 'iPhone 8',
    total: '16',
    date: '20-2-2017',
    status: 'paid'
  },
  {
    orderid: 45457898,
    customer: 'Lori Moore',
    avatar: Product3,
    product: 'Redmi 4',
    total: '20',
    date: '17-2-2017',
    status: 'success'
  },
  {
    orderid: 62446232,
    customer: 'Austin Pena',
    avatar: Product4,
    product: 'Jio',
    total: '15',
    date: '125-4-2017',
    status: 'failed'
  }
]);
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th class="text-left text-subtitle-1">Customer</th>
        <th class="text-left text-subtitle-1">Order Id</th>
        <th class="text-left text-subtitle-1">Photo</th>
        <th class="text-left text-subtitle-1">Product</th>

        <th class="text-left text-subtitle-1">Total</th>
        <th class="text-left text-subtitle-1">Date</th>
        <th class="text-left text-subtitle-1">Status</th>
        <th class="text-center text-subtitle-1">Action</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in products" :key="item.orderid">
        <td class="py-4">{{ item.customer }}</td>
        <td class="py-4">#{{ item.orderid }}</td>
        <td class="py-4">
          <img :src="item.avatar" width="20" height="40" :alt="item.avatar" />
        </td>
        <td class="py-4">{{ item.product }}</td>
        <td class="py-4 pl-7">{{ item.total }}</td>
        <td class="py-4">{{ item.date }}</td>
        <td class="py-4">
          <v-chip class="bg-lightwarning text-warning" size="small" v-if="item.status === 'pending'"> Pending</v-chip>
          <v-chip class="bg-lightprimary text-primary" size="small" v-else-if="item.status === 'paid'"> Paid</v-chip>
          <v-chip class="bg-lightsuccess text-success" size="small" v-else-if="item.status === 'success'"> Success</v-chip>
          <v-chip class="bg-lighterror text-error" size="small" v-else>Failed</v-chip>
        </td>
        <td class="py-4">
          <div class="d-flex align-center justify-center">
            <v-btn icon color="primary" variant="text">
              <PencilIcon width="22" stroke-width="1.5" />
            </v-btn>
            <v-btn icon color="inherit" variant="text">
              <TrashIcon width="22" stroke-width="1.5" />
            </v-btn>
          </div>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
