<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import SimpleChart from '@/components/forms/charts/orgchart/SimpleChart.vue';
import HorizontalFlow from '@/components/forms/charts/orgchart/HorizontalFlow.vue';

const page = ref({ title: 'Org Chart' });
const breadcrumbs = ref([
  {
    title: 'Charts',
    disabled: false,
    href: '#'
  },
  {
    title: 'Org Chart',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Simple Flow Chart">
        <SimpleChart />
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="12">
      <UiParentCard title="Horizontal Flow Chart">
        <HorizontalFlow />
      </UiParentCard>
    </v-col>
  </v-row>
</template>
