<script setup lang="ts">
import { computed } from 'vue';

const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'line',
      height: 105,
      fontFamily: `inherit`,
      foreColor: '#a1aab2',
      sparkline: {
        enabled: true
      }
    },
    dataLabels: {
      enabled: false
    },
    colors: ['#fff'],

    stroke: {
      curve: 'smooth',
      width: 3
    },
    yaxis: {
      min: 20,
      max: 100
    },
    tooltip: {
      fixed: {
        enabled: false
      },
      x: {
        show: false
      },
      marker: {
        show: false
      }
    }
  };
});

// chart 1
const lineChart1 = {
  series: [
    {
      name: 'Sales/Order Per Day',
      data: [55, 35, 75, 25, 90, 50]
    }
  ]
};
</script>

<template>
  <v-card elevation="0">
    <v-card variant="outlined">
      <v-card-text class="bg-primary text-white">
        <div class="d-flex alig-center justify-space-between mb-4">
          <h3 class="text-subtitle-1 mb-1">Order Per Month</h3>
          <span class="text-subtitle-2 ml-auto d-flex align-center">
            <TrendingDownIcon width="34" class="text-white mr-2" stroke-width="2" />28%
          </span>
        </div>

        <apexchart type="line" height="105" class="text-darkText" :options="chartOptions1" :series="lineChart1.series"> </apexchart>
      </v-card-text>
      <v-card-text>
        <v-row>
          <v-col cols="6" class="text-center">
            <h3 class="text-h3 mb-1">1695</h3>
            <span class="text-subtitle-1 font-weight-regular text-medium-emphasis">Total Orders</span>
          </v-col>
          <v-col cols="6" class="text-center">
            <h3 class="text-h3 mb-1">321</h3>
            <span class="text-subtitle-1 font-weight-regular text-medium-emphasis">Today Orders</span>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-card>
</template>
