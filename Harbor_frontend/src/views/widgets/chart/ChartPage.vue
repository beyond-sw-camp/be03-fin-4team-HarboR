<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// import components
import ColorCards from './components/ColorCards.vue';
import SmallCharts from './components/SmallCharts.vue';
import VisitCard from './components/VisitCard.vue';
import BounceRate from './components/BounceRate.vue';
import ProductChart from './components/ProductChart.vue';
import MarketShare from './components/MarketShare.vue';
import TotalRevenue from './components/TotalRevenue.vue';
import SalesDay from './components/SalesDay.vue';
import OrderMonth from './components/OrderMonth.vue';
import PageView from './components/PageView.vue';
import CustomerSatisfaction from './components/CustomerSatisfaction.vue';
import NewStock from './components/NewStock.vue';

const page = ref({ title: 'Charts' });
const breadcrumbs = ref([
  {
    title: 'Widget',
    disabled: false,
    href: '#'
  },
  {
    title: 'Charts',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>

  <ColorCards />
  <v-row>
    <v-col cols="12" lg="7">
      <MarketShare />
    </v-col>

    <v-col cols="12" lg="5">
      <TotalRevenue />
    </v-col>
  </v-row>

  <SmallCharts />

  <v-row>
    <v-col cols="12" lg="4">
      <SalesDay />
    </v-col>
    <v-col cols="12" lg="4">
      <OrderMonth />
    </v-col>
    <v-col cols="12" lg="4">
      <PageView />
    </v-col>
  </v-row>

  <v-row>
    <v-col cols="12" lg="4">
      <VisitCard />
    </v-col>
    <v-col cols="12" lg="4">
      <BounceRate />
    </v-col>
    <v-col cols="12" lg="4">
      <ProductChart />
    </v-col>

    <v-col cols="12" lg="6">
      <NewStock />
    </v-col>

    <v-col cols="12" lg="6">
      <CustomerSatisfaction />
    </v-col>
  </v-row>
</template>
