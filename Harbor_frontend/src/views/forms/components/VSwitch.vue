<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Switch' });
const breadcrumbs = ref([
  {
    title: 'Components',
    disabled: false,
    href: '#'
  },
  {
    title: 'Switch',
    disabled: true,
    href: '#'
  }
]);

const model = ref(true);
</script>

// ===============================|| Ui Switch ||=============================== //
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Switch">
        <v-row>
          <!-- Basic Switch -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic Switch">
              <div class="d-sm-flex justify-space-between">
                <v-switch color="primary" :model-value="true" hide-details></v-switch>

                <v-switch color="primary" :model-value="false" hide-details></v-switch>
                <v-switch color="primary" :model-value="true" disabled hide-details></v-switch>

                <v-switch color="primary" :model-value="false" disabled hide-details></v-switch>
                <v-switch loading="error" :model-value="true" hide-details></v-switch>

                <v-switch :model-value="false" loading="error" hide-details></v-switch>
              </div>
            </UiChildCard>
          </v-col>
          <!-- With Label -->
          <v-col cols="12" lg="6">
            <UiChildCard title="With Label">
              <div class="d-flex justify-space-between">
                <v-switch color="primary" :model-value="true" label="Label" hide-details></v-switch>

                <v-switch color="primary" disabled label="Disabled" :model-value="false" hide-details></v-switch>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Inset -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Inset">
              <v-switch v-model="model" hide-details color="primary" inset :label="`Switch: ${model.toString()}`"></v-switch>
            </UiChildCard>
          </v-col>
          <!-- Inset Colors -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Inset Colors">
              <div class="d-flex ga-4 flex-column flex-sm-row">
                <v-switch aria-label="switch" color="primary" :model-value="true" hide-details inset></v-switch>
                <v-switch aria-label="switch" color="secondary" :model-value="true" hide-details inset></v-switch>
                <v-switch aria-label="switch" color="success" :model-value="true" hide-details inset></v-switch>
                <v-switch aria-label="switch" color="error" :model-value="true" hide-details inset></v-switch>
                <v-switch aria-label="switch" color="warning" :model-value="true" hide-details inset></v-switch>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Colors -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Colors">
              <div class="d-flex ga-4 flex-column flex-sm-row">
                <v-switch aria-label="switch" color="primary" :model-value="true" hide-details></v-switch>
                <v-switch aria-label="switch" color="secondary" :model-value="true" hide-details></v-switch>
                <v-switch aria-label="switch" color="success" :model-value="true" hide-details></v-switch>
                <v-switch aria-label="switch" color="error" :model-value="true" hide-details></v-switch>
                <v-switch aria-label="switch" color="warning" :model-value="true" hide-details></v-switch>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
