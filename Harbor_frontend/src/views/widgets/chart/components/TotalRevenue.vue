<script setup lang="ts">
import { computed } from 'vue';
import { getPrimary, getSecondary } from '../../../forms/charts/apex-chart/UpdateColors';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'donut',
      height: 200,
      fontFamily: `inherit`,
      foreColor: '#a1aab2'
    },
    colors: ['#f44336', getPrimary.value, getSecondary.value],
    dataLabels: {
      enabled: false
    },
    labels: ['Youtube', 'Facebook', 'Twitter'],
    legend: {
      show: true,
      position: 'bottom',
      fontFamily: 'inherit',
      labels: {
        colors: 'inherit'
      },
      itemMargin: {
        horizontal: 10,
        vertical: 10
      }
    }
  };
});

// chart 1
const lineChart1 = {
  series: [1258, 975, 500]
};
</script>

<template>
  <UiParentCard title="Total Revenue">
    <apexchart type="donut" height="200" :options="chartOptions1" :series="lineChart1.series"> </apexchart>
    <v-row class="mt-6">
      <v-col cols="4">
        <span class="text-subtitle-2 font-weight-medium">Youtube</span>
        <h6 class="text-error text-h5">+ 16.85%</h6>
      </v-col>
      <v-col cols="4">
        <span class="text-subtitle-2 font-weight-medium">Facebook</span>
        <h6 class="text-primary text-h5">+ 45.36%</h6>
      </v-col>
      <v-col cols="4">
        <span class="text-subtitle-2 font-weight-medium">Twitter</span>
        <h6 class="text-secondary text-h5">- 50.69%</h6>
      </v-col>
    </v-row>
  </UiParentCard>
</template>
