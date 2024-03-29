<script setup lang="ts">
import { computed } from 'vue';
import { getPrimary, getSecondary } from '../../../forms/charts/apex-chart/UpdateColors';

const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'bar',
      height: 180,
      fontFamily: `inherit`,
      foreColor: '#a1aab2',
      sparkline: {
        enabled: true
      }
    },
    colors: [getPrimary.value, getSecondary.value, '#00c853', '#f44336'],
    plotOptions: {
      bar: {
        columnWidth: '55%',
        distributed: true
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      width: 0
    },
    xaxis: {
      categories: ['Desktop', 'Mobile', 'Tablet', 'Laptop']
    }
  };
});

// chart 1
const lineChart1 = {
  series: [
    {
      name: 'Requests',
      data: [66.6, 29.7, 32.8, 50]
    }
  ]
};
</script>

<template>
  <v-card elevation="0">
    <v-card variant="outlined">
      <v-card-text>
        <div class="d-flex align-center justify-space-between mb-7">
          <span class="text-subtitle-1">Page view by device</span>
          <v-btn variant="text" color="primary" size="small">Weekly</v-btn>
        </div>
        <v-row>
          <v-col cols="12" sm="6" class="d-flex align-center">
            <div class="w-100">
              <div class="d-flex justify-space-around align-center">
                <DeviceDesktopIcon stroke-width="1.5" width="20" class="text-primary" />
                <span class="text-subtitle-1">66.6%</span>
                <ArrowUpIcon stroke-width="1.5" width="18" class="text-primary" />
                <span class="text-subtitle-1 text-primary">2%</span>
              </div>
              <div class="d-flex justify-space-around align-center">
                <DeviceMobileOffIcon stroke-width="1.5" width="20" class="text-success" />
                <span class="text-subtitle-1">29.7%</span>
                <ArrowUpIcon stroke-width="1.5" width="18" class="text-success" />
                <span class="text-subtitle-1 text-success">3%</span>
              </div>
              <div class="d-flex justify-space-around align-center">
                <DeviceTabletIcon stroke-width="1.5" width="20" class="text-error" />
                <span class="text-subtitle-1">32.8%</span>
                <ArrowUpIcon stroke-width="1.5" width="18" class="text-error" />
                <span class="text-subtitle-1 text-error">8%</span>
              </div>
              <div class="d-flex justify-space-around align-center">
                <DeviceLaptopIcon stroke-width="1.5" width="20" class="text-error" />
                <span class="text-subtitle-1">50.2%</span>
                <ArrowUpIcon stroke-width="1.5" width="18" class="text-error" />
                <span class="text-subtitle-1 text-error">5%</span>
              </div>
            </div>
          </v-col>
          <v-col cols="12" sm="6">
            <apexchart type="bar" height="180" :options="chartOptions1" :series="lineChart1.series"> </apexchart>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-card>
</template>
