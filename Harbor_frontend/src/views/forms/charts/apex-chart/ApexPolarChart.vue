<script setup lang="ts">
import { computed } from 'vue';
import { useTheme } from 'vuetify';
import { getPrimary, getSecondary } from './UpdateColors';

const theme = useTheme();
const successColor = theme.current.value.colors.success;
const errorColor = theme.current.value.colors.error;
const warningColor = theme.current.value.colors.warning;
const chartOptions = computed(() => {
  return {
    chart: {
      type: 'polarArea',
      width: 450,
      height: 450,
      fontFamily: `inherit`,
      foreColor: '#a1aab2'
    },
    colors: [getPrimary.value, getSecondary.value, successColor, errorColor, warningColor],
    fill: {
      opacity: 1
    },
    legend: {
      show: true,
      offsetX: 10,
      offsetY: 10,
      labels: {
        useSeriesColors: false
      },
      markers: {
        width: 12,
        height: 12,
        radius: 5
      },
      itemMargin: {
        horizontal: 25,
        vertical: 4
      }
    },
    responsive: [
      {
        breakpoint: 450,
        chart: {
          width: 280,
          height: 280
        },
        options: {
          legend: {
            show: false,
            position: 'bottom'
          }
        }
      }
    ],
    tooltip: {
      theme: 'light',
      fillSeriesColor: false
    }
  };
});
const polarChart = {
  series: [14, 23, 21, 17, 15, 10, 12, 17, 21]
};
</script>

<template>
  <!-- ---------------------------------------------------- -->
  <!-- Polar Chart -->
  <!-- ---------------------------------------------------- -->

  <apexchart type="polarArea" :options="chartOptions" :series="polarChart.series"> </apexchart>
</template>
