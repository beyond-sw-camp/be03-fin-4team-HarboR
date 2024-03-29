<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const page = ref({ title: 'Grid Page' });
const breadcrumbs = ref([
  {
    title: 'Utilities',
    disabled: false,
    href: '#'
  },
  {
    title: 'Grid Page',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="6">
      <!--Basic Grid-->
      <UiParentCard title="Basic Grid">
        <v-row>
          <v-col xs="8" md="8">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%">xs=8 </v-sheet>
          </v-col>
          <v-col xs="4" md="4">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%">xs=4 </v-sheet>
          </v-col>
          <v-col xs="4" md="4">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%">xs=4 </v-sheet>
          </v-col>
          <v-col xs="8" md="8">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%">xs=8 </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
      <!--Basic Grid-->
    </v-col>
    <v-col cols="12" md="6">
      <!--Multiple Breakpoints-->
      <UiParentCard title="Multiple Breakpoints">
        <v-row>
          <v-col cols="6" md="8">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%"
              >xs=6 md=8
            </v-sheet>
          </v-col>
          <v-col cols="6" md="4">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%"
              >xs=6 md=4
            </v-sheet>
          </v-col>
          <v-col cols="6" md="4">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%"
              >xs=6 md=4
            </v-sheet>
          </v-col>
          <v-col cols="6" md="8">
            <v-sheet color="lightsecondary" rounded="md" class="align-center justify-center d-flex" height="40" width="100%"
              >xs=6 md=8
            </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
      <!--Basic Grid-->
    </v-col>
    <!--Breakpoint Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="Breakpoint sizing">
        <v-row no-gutters>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>

        <v-row no-gutters>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>

        <v-row no-gutters>
          <v-col cols="2">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-2 </v-sheet>
          </v-col>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!--Breakpoint Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="Justify">
        <v-row justify="start">
          <v-col v-for="k in 2" :key="k" cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .justify-start </v-sheet>
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col v-for="k in 2" :key="k" cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .justify-center </v-sheet>
          </v-col>
        </v-row>

        <v-row justify="end">
          <v-col v-for="k in 2" :key="k" cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .justify-end </v-sheet>
          </v-col>
        </v-row>

        <v-row justify="space-around">
          <v-col v-for="k in 2" :key="k" cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .justify-space-around </v-sheet>
          </v-col>
        </v-row>

        <v-row justify="space-between">
          <v-col v-for="k in 2" :key="k" cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .justify-space-between </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!--Offset Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="Offset">
        <v-row class="mb-6" no-gutters>
          <v-col cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-4 </v-sheet>
          </v-col>
          <v-col cols="4" offset="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-4 .offset-4 </v-sheet>
          </v-col>
        </v-row>
        <v-row class="mb-6" no-gutters>
          <v-col cols="3" offset="3">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-3 .offset-3 </v-sheet>
          </v-col>
          <v-col cols="3" offset="3">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-3 .offset-3 </v-sheet>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col cols="6" offset="3">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-6 .offset-3 </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!--Offset Grid-->
    <!--Order Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="Order first and last">
        <v-row no-gutters>
          <v-col order="last">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> First, but last </v-sheet>
          </v-col>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> Second, but unordered </v-sheet>
          </v-col>
          <v-col order="first">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> Third, but first </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!--Grow and Shrink Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="Grow and Shrink">
        <v-row class="mb-6" no-gutters>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>

          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>

          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>

          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>

        <v-row no-gutters>
          <v-col cols="8">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-8 </v-sheet>
          </v-col>

          <v-col cols="4">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-4 </v-sheet>
          </v-col>
        </v-row></UiParentCard
      >
    </v-col>
    <!--Grow and Shrink Grid-->
    <v-col md="12" cols="12">
      <UiParentCard title="One column width">
        <v-row no-gutters>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>

          <v-col cols="6">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-6 </v-sheet>
          </v-col>

          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>

        <v-row no-gutters>
          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>

          <v-col cols="2">
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-2 </v-sheet>
          </v-col>

          <v-col>
            <v-sheet color="lightsecondary" rounded="md" class="pa-2 ma-2"> .v-col-auto </v-sheet>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
