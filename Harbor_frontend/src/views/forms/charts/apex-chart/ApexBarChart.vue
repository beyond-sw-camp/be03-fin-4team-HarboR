<script setup lang="ts">
import { useTheme } from 'vuetify';

const theme = useTheme();
const successColor = theme.current.value.colors.success;

const barChart = {
  series: [
    {
      data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380]
    }
  ],
  chartOptions: {
    chart: {
      type: 'bar',
      height: 350,
      fontFamily: `inherit`,
      foreColor: '#a1aab2'
    },
    colors: successColor,
    plotOptions: {
      bar: {
        horizontal: true
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      show: true,
      width: 2,
      colors: ['transparent']
    },

    xaxis: {
      categories: [
        'South Korea',
        'Canada',
        'United Kingdom',
        'Netherlands',
        'Italy',
        'France',
        'Japan',
        'United States',
        'China',
        'Germany'
      ]
    },
    fill: {
      opacity: 1
    },
    grid: {
      borderColor: 'rgba(0,0,0,0.1)'
    }
  }
};
</script>

<template>
  <!-- ---------------------------------------------------- -->
  <!-- Bar Chart -->
  <!-- ---------------------------------------------------- -->

  <apexchart type="bar" height="350" :options="barChart.chartOptions" :series="barChart.series"> </apexchart>
</template>
