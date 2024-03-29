<script setup lang="ts">
import { computed } from 'vue';
import { getPrimary } from '../../../forms/charts/apex-chart/UpdateColors';
const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'area',
      height: 40,
      fontFamily: `inherit`,
      foreColor: '#a1aab2',
      sparkline: {
        enabled: true
      }
    },
    colors: [getPrimary.value],
    dataLabels: {
      enabled: false
    },
    fill: {
      type: 'solid',
      opacity: 0.3
    },
    markers: {
      size: 4,
      strokeWidth: 2,
      hover: {
        size: 6
      }
    },
    stroke: {
      curve: 'straight',
      width: 3
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
      name: 'Visits',
      data: [9, 66, 41, 89, 63, 25, 44, 12, 36, 20, 54, 25, 9]
    }
  ]
};
</script>

<template>
  <v-card elevation="0">
    <v-card variant="outlined">
      <v-card-text>
        <h3 class="text-h3 mb-1">$16, 756</h3>
        <span class="text-subtitle-1 text-medium-emphasis d-flex align-center mb-5"
          >Visits
          <ChevronDownIcon stroke-width="2" width="20" class="ml-2 text-error" />
        </span>

        <apexchart type="area" height="40" :options="chartOptions1" :series="lineChart1.series"> </apexchart>
      </v-card-text>
    </v-card>
  </v-card>
</template>
