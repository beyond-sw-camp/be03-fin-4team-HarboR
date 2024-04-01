<script setup>
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const page = ref({ title: 'Tooltip Page' });
const show = ref(false);
const breadcrumbs = ref([
  {
    title: 'Plugins',
    disabled: false,
    href: '#'
  },
  {
    title: 'Tooltip Page',
    disabled: true,
    href: '#'
  }
]);
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Simple Tooltips">
        <div class="d-flex flex-column flex-sm-row ga-4 justify-space-around">
          <v-btn variant="outlined" color="primary">
            Start
            <v-tooltip activator="parent" location="start">Tooltip</v-tooltip>
          </v-btn>

          <v-btn variant="outlined" color="primary">
            End
            <v-tooltip activator="parent" location="end">Tooltip</v-tooltip>
          </v-btn>

          <v-btn variant="outlined" color="primary">
            Top
            <v-tooltip activator="parent" location="top">Tooltip</v-tooltip>
          </v-btn>

          <v-btn variant="outlined" color="primary">
            Bottom
            <v-tooltip activator="parent" location="bottom">Tooltip</v-tooltip>
          </v-btn>
        </div>
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="6">
      <UiParentCard title="Icon Tooltips">
        <div class="d-flex justify-space-around">
          <v-tooltip location="bottom">
            <template v-slot:activator="{ props }">
              <v-icon color="primary" v-bind="props"> mdi-home </v-icon>
            </template>
            <span>Tooltip</span>
          </v-tooltip>
          <v-tooltip location="top">
            <template v-slot:activator="{ props }">
              <v-icon color="primary" v-bind="props"> mdi-content-copy </v-icon>
            </template>
            <span>Copy</span>
          </v-tooltip>
        </div>
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="6">
      <UiParentCard title="Toggle Tolltip">
        <div class="d-flex justify-space-around">
          <v-btn @click="show = !show" variant="outlined">
            {{ !show ? 'Show Tooltip' : 'Hide Tooltip' }}
          </v-btn>
          <v-tooltip v-model="show" top>
            <template v-slot:activator="{ props }">
              <v-btn icon v-bind="props">
                <v-icon> mdi-cart </v-icon>
              </v-btn>
            </template>
            <span>Programmatic tooltip</span>
          </v-tooltip>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
