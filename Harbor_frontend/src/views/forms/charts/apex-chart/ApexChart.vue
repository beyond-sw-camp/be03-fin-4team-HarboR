<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

// apexcharts types
import ApexColumnChart from './ApexColumnChart.vue';
import ApexBarChart from './ApexBarChart.vue';
import ApexLineChart from './ApexLineChart.vue';
import ApexAreaChart from './ApexAreaChart.vue';
import ApexMixedChart from './ApexMixedChart.vue';
import ApexRedialChart from './ApexRedialChart.vue';
import ApexPolarChart from './ApexPolarChart.vue';
import ApexPieChart from './ApexPieChart.vue';

// theme breadcrumb
const page = ref({ title: 'Apex Chart' });
const breadcrumbs = ref([
  {
    title: 'Charts',
    disabled: false,
    href: '#'
  },
  {
    title: 'Apex Chart',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <!-- ---------------------------------------------------- -->
  <!-- Apex Chart -->
  <!-- ---------------------------------------------------- -->
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" lg="6">
      <UiParentCard title="Column Chart">
        <ApexColumnChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="6">
      <UiParentCard title="Bar Chart">
        <ApexBarChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4">
      <UiParentCard title="Line Chart">
        <ApexLineChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4">
      <UiParentCard title="Area Chart">
        <ApexAreaChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4">
      <UiParentCard title="Mixed Chart">
        <ApexMixedChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4" class="d-flex align-stretch">
      <UiParentCard title="Redial Chart">
        <ApexRedialChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4" class="d-flex align-stretch">
      <UiParentCard title="Polar Chart">
        <ApexPolarChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" lg="4" class="d-flex align-stretch">
      <UiParentCard title="Pie Chart">
        <ApexPieChart />
      </UiParentCard>
    </v-col>
  </v-row>
</template>
