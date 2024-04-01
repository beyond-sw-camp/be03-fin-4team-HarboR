<script setup lang="ts">
import { shallowRef } from 'vue';

// import icons
import { ChevronUpIcon, ChevronDownIcon } from 'vue-tabler-icons';

const revenues = shallowRef([
  {
    name: 'Bitcoin',
    price: 145.58
  },
  {
    name: 'Ethereum',
    price: 6.368
  },
  {
    name: 'Ripple',
    price: 458.63
  },
  {
    name: 'Neo',
    price: 5.631
  },
  {
    name: 'Ethereum',
    price: 6.368
  },
  {
    name: 'Ripple',
    price: 458.63
  },
  {
    name: 'Bitcoin',
    price: 145.58
  }
]);
</script>

<template>
  <perfect-scrollbar v-bind:style="{ height: '320px' }">
    <v-list class="py-0">
      <v-list-item v-for="(revenue, i) in revenues" :key="i" :value="revenue" color="primary">
        <template v-slot:prepend>
          <ChevronUpIcon stroke-width="1.5" width="20" class="mr-3 text-success" v-if="revenue.price > 145" />
          <ChevronDownIcon stroke-width="1.5" width="20" class="mr-3 text-error" v-else />
        </template>
        <div class="d-inline-flex align-center justify-space-between w-100">
          <h6 class="text-subtitle-1 font-weight-regular">{{ revenue.name }}</h6>
          <div class="ml-auto text-subtitle-1 font-weight-regular text-success" v-if="revenue.price > 145">+ ${{ revenue.price }}</div>
          <div class="ml-auto text-subtitle-1 font-weight-regular text-error" v-else>- ${{ revenue.price }}</div>
        </div>
      </v-list-item>
    </v-list>
  </perfect-scrollbar>
</template>
