<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

import { UserCircleIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Chip' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Chip',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Chip">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic">
              <div class="d-flex flex-column flex-md-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip> Default </v-chip>

                <v-chip color="primary" closable> Default </v-chip>

                <v-chip draggable color="secondary"> Primary </v-chip>

                <v-chip label color="error"> Label </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Outlined -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Outlined">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip variant="outlined"> Default </v-chip>

                <v-chip variant="outlined" color="primary" closable> Default </v-chip>

                <v-chip variant="outlined" draggable color="secondary"> Primary </v-chip>

                <v-chip variant="outlined" label color="error"> Label </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Outlined Color -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Outlined Color">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip variant="outlined" color="primary"> Primary </v-chip>
                <v-chip variant="outlined" color="secondary"> Secondary </v-chip>
                <v-chip variant="outlined" color="warning"> Warning </v-chip>
                <v-chip variant="outlined" color="success"> Success </v-chip>
                <v-chip variant="outlined" color="error"> Error </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Filled Color -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Filled Color">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip color="primary"> Primary </v-chip>
                <v-chip color="secondary"> Secondary </v-chip>
                <v-chip color="warning"> Warning </v-chip>
                <v-chip color="success"> Success </v-chip>
                <v-chip color="error"> Error </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Closable -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Closable">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip closable color="primary"> Primary </v-chip>
                <v-chip closable color="secondary"> Secondary </v-chip>
                <v-chip closable color="warning"> Warning </v-chip>
                <v-chip closable color="success"> Success </v-chip>
                <v-chip closable color="error"> Error </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Label -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Closable Label">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip label closable color="primary"> Primary </v-chip>
                <v-chip label closable color="secondary"> Secondary </v-chip>
                <v-chip label closable color="warning"> Warning </v-chip>
                <v-chip label closable color="success"> Success </v-chip>
                <v-chip label closable color="error"> Error </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Label with Icon -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Label with Icon">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip color="primary" label text-color="white">
                  <UserCircleIcon class="mr-2" size="18" />
                  Users
                </v-chip>
                <v-chip color="secondary" label text-color="white">
                  <UserCircleIcon class="mr-2" size="18" />
                  Users
                </v-chip>
                <v-chip color="warning" label text-color="white">
                  <UserCircleIcon class="mr-2" size="18" />
                  Users
                </v-chip>
                <v-chip color="success" label text-color="white">
                  <UserCircleIcon class="mr-2" size="18" />
                  Users
                </v-chip>
                <v-chip color="error" label text-color="white">
                  <UserCircleIcon class="mr-2" size="18" />
                  Users
                </v-chip>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Sizes -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Sizes">
              <div class="d-flex flex-column flex-sm-row flex-wrap flex-xl-nowrap align-center ga-4">
                <v-chip color="primary" size="x-small"> x-small chip </v-chip>

                <v-chip color="primary" size="small"> small chip </v-chip>

                <v-chip color="primary"> Default </v-chip>

                <v-chip color="primary" size="large"> large chip </v-chip>

                <v-chip color="primary" size="x-large"> x-large chip </v-chip>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
