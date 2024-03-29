<script setup lang="ts">
import { computed } from 'vue';

const chartOptions1 = computed(() => {
  return {
    chart: {
      type: 'area',
      height: 100,
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
    fill: {
      type: 'solid',
      opacity: 0.4
    },
    stroke: {
      curve: 'smooth',
      width: 3
    },
    yaxis: {
      min: 0,
      max: 30
    },
    tooltip: {
      fixed: {
        enabled: false
      },
      x: {
        show: false
      },
      y: {
        title: {
          formatter: () => 'Total Sales'
        }
      },
      marker: {
        show: false
      }
    }
  };
});

// chart 2
const chartOptions2 = computed(() => {
  return {
    chart: {
      type: 'area',
      height: 100,
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
    fill: {
      type: 'solid',
      opacity: 0.4
    },
    stroke: {
      curve: 'smooth',
      width: 3
    },
    yaxis: {
      min: 0,
      max: 30
    },
    tooltip: {
      fixed: {
        enabled: false
      },
      x: {
        show: false
      },
      y: {
        title: {
          formatter: () => 'Total Sales'
        }
      },
      marker: {
        show: false
      }
    }
  };
});

// chart 3
const chartOptions3 = computed(() => {
  return {
    chart: {
      type: 'area',
      height: 100,
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
    fill: {
      type: 'solid',
      opacity: 0.4
    },
    stroke: {
      curve: 'smooth',
      width: 3
    },
    yaxis: {
      min: 0,
      max: 30
    },
    tooltip: {
      fixed: {
        enabled: false
      },
      x: {
        show: false
      },
      y: {
        title: {
          formatter: () => 'Total Sales'
        }
      },
      marker: {
        show: false
      }
    }
  };
});

// chart 4
const chartOptions4 = computed(() => {
  return {
    chart: {
      type: 'area',
      height: 100,
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
    fill: {
      type: 'solid',
      opacity: 0.4
    },
    stroke: {
      curve: 'smooth',
      width: 3
    },
    yaxis: {
      min: 0,
      max: 30
    },
    tooltip: {
      fixed: {
        enabled: false
      },
      x: {
        show: false
      },
      y: {
        title: {
          formatter: () => 'Total Sales'
        }
      },
      marker: {
        show: false
      }
    }
  };
});

// chart 1
const areaChart1 = {
  series: [
    {
      name: 'series1',
      data: [20, 10, 18, 12, 25, 10, 20]
    }
  ]
};

// chart 2
const areaChart2 = {
  series: [
    {
      name: 'series1',
      data: [10, 20, 18, 25, 12, 10, 20]
    }
  ]
};

// chart 3
const areaChart3 = {
  series: [
    {
      name: 'series1',
      data: [20, 10, 18, 12, 25, 10, 20]
    }
  ]
};

// chart 4
const areaChart4 = {
  series: [
    {
      name: 'series1',
      data: [10, 20, 18, 25, 12, 10, 20]
    }
  ]
};
</script>

<template>
  <v-row>
    <!-- chart 1 -->
    <v-col cols="12" sm="6" lg="3">
      <v-card class="bg-primary" elevation="0">
        <v-card-text>
          <div class="d-flex align-start">
            <div>
              <h3 class="text-h3 mb-1">4000</h3>
              <span class="text-subtitle-1 font-weight-regular">Total Sales</span>
            </div>
            <span class="text-subtitle-1 font-weight-regular ml-auto">42%</span>
          </div>
        </v-card-text>
        <apexchart type="area" class="text-darkText" height="100" :options="chartOptions1" :series="areaChart1.series"> </apexchart>
      </v-card>
    </v-col>

    <!-- chart 2 -->
    <v-col cols="12" sm="6" lg="3">
      <v-card class="bg-error" elevation="0">
        <v-card-text>
          <div class="d-flex align-start">
            <div>
              <h3 class="text-h3 mb-1">2500</h3>
              <span class="text-subtitle-1 font-weight-regular">Total Comment</span>
            </div>
            <span class="text-subtitle-1 font-weight-regular ml-auto">15%</span>
          </div>
        </v-card-text>
        <apexchart type="area" class="text-darkText" height="100" :options="chartOptions2" :series="areaChart2.series"> </apexchart>
      </v-card>
    </v-col>

    <!-- chart 3 -->
    <v-col cols="12" sm="6" lg="3">
      <v-card class="bg-success" elevation="0">
        <v-card-text>
          <div class="d-flex align-start">
            <div>
              <h3 class="text-h3 mb-1">2500</h3>
              <span class="text-subtitle-1 font-weight-regular">Total Status</span>
            </div>
            <span class="text-subtitle-1 font-weight-regular ml-auto">95%</span>
          </div>
        </v-card-text>
        <apexchart type="area" class="text-darkText" height="100" :options="chartOptions3" :series="areaChart3.series"> </apexchart>
      </v-card>
    </v-col>

    <!-- chart 4 -->
    <v-col cols="12" sm="6" lg="3">
      <v-card class="bg-secondary" elevation="0">
        <v-card-text>
          <div class="d-flex align-start">
            <div>
              <h3 class="text-h3 mb-1">12500</h3>
              <span class="text-subtitle-1 font-weight-regular">Total Visitors</span>
            </div>
            <span class="text-subtitle-1 font-weight-regular ml-auto">75%</span>
          </div>
        </v-card-text>
        <apexchart type="area" class="text-darkText" height="100" :options="chartOptions4" :series="areaChart4.series"> </apexchart>
      </v-card>
    </v-col>
  </v-row>
</template>
