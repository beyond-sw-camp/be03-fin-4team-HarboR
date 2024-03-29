<script setup lang="ts">
import { shallowRef } from 'vue';

const sources = shallowRef([
  {
    text: 'Direct',
    percent: 80,
    color: 'primary'
  },
  {
    text: 'Social',
    percent: 50,
    color: 'secondary'
  },
  {
    text: 'Referral',
    percent: 20,
    color: 'primary'
  },
  {
    text: 'Bounce',
    percent: 58,
    color: 'secondary'
  },
  {
    text: 'Internet',
    percent: 40,
    color: 'primary'
  },
  {
    text: 'Social',
    percent: 90,
    color: 'primary'
  },
  {
    text: 'Bounce',
    percent: 50,
    color: 'secondary'
  }
]);
</script>

<template>
  <div class="py-3" v-for="(source, i) in sources" :key="i" :value="source">
    <div class="d-flex align-center justify-space-between mb-3">
      <span class="text-subtitle-1 text-medium-emphasis">{{ source.text }}</span>
      <span class="text-subtitle-1 text-medium-emphasis">{{ source.percent }}%</span>
    </div>
    <v-progress-linear :model-value="source.percent" :color="source.color"></v-progress-linear>
  </div>
</template>
