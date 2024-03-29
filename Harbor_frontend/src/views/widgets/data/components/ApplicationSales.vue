<script setup lang="ts">
import { shallowRef } from 'vue';

const products = shallowRef([
  {
    title: 'Materially',
    subtext: 'Powerful Admin Theme',
    sales: '16,300',
    avgprice: '53',
    total: '15,652'
  },
  {
    title: 'Photoshop',
    subtext: 'Design Software',
    sales: '16,300',
    avgprice: '53',
    total: '15,652'
  },
  {
    title: 'Guruable',
    subtext: 'Best Admin Template',
    sales: '16,300',
    avgprice: '53',
    total: '15,652'
  },
  {
    title: 'Flatable',
    subtext: 'Admin App',
    sales: '16,300',
    avgprice: '53',
    total: '15,652'
  }
]);
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th class="text-left text-subtitle-1">Application</th>
        <th class="text-right text-subtitle-1">Sales</th>
        <th class="text-right text-subtitle-1">Avg. Price</th>
        <th class="text-right text-subtitle-1">Total</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in products" :key="item.title">
        <td class="py-5">
          <h5 class="text-subtitle-1">{{ item.title }}</h5>
          <span class="text-medium-emphasis text-subtitle-2">{{ item.subtext }}</span>
        </td>
        <td class="text-right py-5">{{ item.sales }}</td>
        <td class="text-right py-5">${{ item.avgprice }}</td>
        <td class="text-right py-5">${{ item.total }}</td>
      </tr>
    </tbody>
  </v-table>
</template>
