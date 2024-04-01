<script setup lang="ts">
import { computed } from 'vue';
import { getPrimary } from '../../../forms/charts/apex-chart/UpdateColors';

const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'pie',
      height: 300,
      fontFamily: `inherit`,
      foreColor: '#a1aab2',
      sparkline: {
        enabled: true
      }
    },
    colors: [getPrimary.value],
    labels: ['extremely Satisfied', 'Satisfied', 'Poor', 'Very Poor'],
    legend: {
      show: true,
      position: 'bottom',
      fontFamily: 'inherit',
      labels: {
        colors: 'inherit'
      }
    },
    dataLabels: {
      enabled: true,
      dropShadow: {
        enabled: false
      }
    },
    theme: {
      monochrome: {
        enabled: true
      }
    }
  };
});

// chart 1
const lineChart1 = {
  series: [66, 50, 40, 30]
};
</script>

<template>
  <v-card elevation="0">
    <v-card variant="outlined">
      <v-card-text>
        <span class="text-subtitle-1">Customer Satisfaction</span>
        <apexchart type="pie" height="300" :options="chartOptions1" :series="lineChart1.series"> </apexchart>
      </v-card-text>
    </v-card>
  </v-card>
</template>
