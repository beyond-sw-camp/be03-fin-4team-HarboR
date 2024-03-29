<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Progress' });
const breadcrumbs = ref([
  {
    title: 'Advance',
    disabled: false,
    href: '#'
  },
  {
    title: 'Progress',
    disabled: true,
    href: '#'
  }
]);

// progress data
const pcolors = ref([
  {
    color: 'primary',
    value: 25
  },
  {
    color: 'primary',
    value: 50
  },
  {
    color: 'primary',
    value: 75
  },
  {
    color: 'primary',
    value: 100
  }
]);

const pcolors2 = ref([
  {
    color: 'primary',
    value: 25
  },
  {
    color: 'secondary',
    value: 50
  },
  {
    color: 'error',
    value: 75
  },
  {
    color: 'warning',
    value: 100
  },
  {
    color: 'success',
    value: 100
  }
]);

const psize = ref([
  {
    color: 'primary',
    size: 25
  },
  {
    color: 'secondary',
    size: 50
  },
  {
    color: 'error',
    size: 55
  },
  {
    color: 'warning',
    size: 65
  },
  {
    color: 'success',
    size: 75
  }
]);

// linear data

const p1 = ref(29);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Progress">
        <v-row>
          <!-- Circular -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Circular">
              <div class="d-flex flex-column flex-sm-row align-center ga-4 justify-center">
                <div v-for="progress in pcolors" :key="progress.color">
                  <v-progress-circular :model-value="progress.value" :color="progress.color"> </v-progress-circular>
                </div>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Circular Indeterminate -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Circular Indeterminate">
              <div class="d-flex flex-column flex-sm-row align-center ga-4 justify-center">
                <div v-for="progress in pcolors" :key="progress.color">
                  <v-progress-circular indeterminate :model-value="progress.value" :color="progress.color"></v-progress-circular>
                </div>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Circular Color -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Circular Color">
              <div class="d-flex flex-column flex-sm-row align-center ga-4 justify-center">
                <div v-for="progress in pcolors2" :key="progress.color">
                  <v-progress-circular :model-value="progress.value" :color="progress.color"> </v-progress-circular>
                </div>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Circular Size -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Circular Size">
              <div class="d-flex flex-column flex-sm-row align-center ga-4 justify-center">
                <div v-for="progress in psize" :key="progress.color">
                  <v-progress-circular :size="progress.size" indeterminate :color="progress.color"> </v-progress-circular>
                </div>
              </div>
            </UiChildCard>
          </v-col>

          <!-- Linear -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear">
              <v-progress-linear color="primary" v-model="p1"></v-progress-linear>
            </UiChildCard>
          </v-col>

          <!-- Linear Intermidiate -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear Intermidiate">
              <v-progress-linear color="primary" indeterminate></v-progress-linear>
            </UiChildCard>
          </v-col>

          <!-- Linear Reverse -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear Reverse">
              <v-progress-linear color="primary" reverse model-value="15"></v-progress-linear>
            </UiChildCard>
          </v-col>

          <!-- Linear Reverse Intermidiate -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear Reverse Intermidiate">
              <v-progress-linear color="primary" reverse indeterminate></v-progress-linear>
            </UiChildCard>
          </v-col>

          <!-- Linear Rounded -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear Rounded">
              <v-progress-linear color="primary" rounded model-value="55"></v-progress-linear>
            </UiChildCard>
          </v-col>

          <!-- Linear Stream -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Linear Stream">
              <v-progress-linear color="primary" model-value="20" buffer-value="0" stream> </v-progress-linear>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
