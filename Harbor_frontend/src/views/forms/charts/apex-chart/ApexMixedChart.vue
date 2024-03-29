<script setup lang="ts">
import { computed } from 'vue';
import { useTheme } from 'vuetify';
import { getPrimary, getSecondary } from './UpdateColors';

const theme = useTheme();
const successColor = theme.current.value.colors.success;
const chartOptions = computed(() => {
  return {
    chart: {
      type: 'line',
      stacked: false,
      height: 350,
      fontFamily: `inherit`,
      foreColor: '#a1aab2'
    },
    colors: [getSecondary.value, getPrimary.value, successColor],
    dataLabels: {
      enabled: false
    },
    stroke: {
      width: [1, 1, 4]
    },
    xaxis: {
      categories: [2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016]
    },
    legend: {
      show: true,
      fontFamily: `inherit`,
      position: 'bottom',
      offsetX: 10,
      offsetY: 10,
      labels: {
        useSeriesColors: false
      },
      markers: {
        width: 16,
        height: 16,
        radius: 5
      },
      itemMargin: {
        horizontal: 15,
        vertical: 8
      }
    },
    grid: {
      borderColor: 'rgba(0,0,0,0.1)'
    },
    tooltip: {
      fixed: {
        enabled: true,
        position: 'topLeft', // topRight, topLeft, bottomRight, bottomLeft
        offsetY: 30,
        offsetX: 60
      }
    }
  };
});
const mixedChart = {
  series: [
    {
      name: 'Income',
      type: 'column',
      data: [14, 2, 25, 15, 25, 28, 38, 46]
    },
    {
      name: 'Cashflow',
      type: 'column',
      data: [11, 3, 31, 4, 41, 49, 65, 85]
    },
    {
      name: 'Revenue',
      type: 'line',
      data: [20, 29, 37, 36, 44, 45, 50, 58]
    }
  ]
};
</script>

<template>
  <!-- ---------------------------------------------------- -->
  <!-- Mixed Chart -->
  <!-- ---------------------------------------------------- -->

  <apexchart type="line" height="350" :options="chartOptions" :series="mixedChart.series"> </apexchart>
</template>
