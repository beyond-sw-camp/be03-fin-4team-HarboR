<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { AccessPointIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Buttons' });
const breadcrumbs = ref([
  {
    title: 'Components',
    disabled: false,
    href: '#'
  },
  {
    title: 'Buttons',
    disabled: true,
    href: '#'
  }
]);

// buttons color data
const btnsColor = ref(['primary', 'secondary', 'success', 'error', 'warning']);

// buttons color data
const btnsSize = ref(['x-small', 'small', 'large', 'x-large']);
</script>

// ===============================|| Ui Buttons ||=============================== //
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Buttons">
        <v-row>
          <!-- Base Buttons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Base">
              <div class="d-flex ga-4 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn v-for="btn in btnsColor" :key="btn" :color="btn">
                  {{ btn }}
                </v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Flat Buttons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Flat">
              <div class="d-flex ga-4 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn v-for="btn in btnsColor" :key="btn" :color="btn" variant="flat">
                  {{ btn }}
                </v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Outlined Buttons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Outlined">
              <div class="d-flex ga-4 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn v-for="btn in btnsColor" :key="btn" :color="btn" variant="outlined">
                  {{ btn }}
                </v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- With Icons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="With Icons">
              <div class="d-flex ga-4 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn flat color="primary"><AccessPointIcon stroke-width="1.5" size="22" /> Button </v-btn>
                <v-btn flat color="primary"> Button <AccessPointIcon stroke-width="1.5" size="22" /></v-btn>
                <v-btn flat color="primary"> <AccessPointIcon stroke-width="1.5" size="22" /></v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Sizes -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Size">
              <div class="d-flex flex-wrap ga-4 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn v-for="btn in btnsSize" :key="btn" :size="btn" color="primary"> Button </v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Text Buttons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Text Button">
              <div class="d-flex flex-wrap ga-4 my-2 align-center flex-column flex-wrap flex-xl-nowrap flex-sm-row fill-height">
                <v-btn v-for="btn in btnsColor" :key="btn" :color="btn" variant="text">
                  {{ btn }}
                </v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Shadow Button -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Shadow Button">
              <div class="d-flex flex-wrap ga-4 align-center flex-column flex-sm-row fill-height">
                <v-btn variant="flat" color="primary" class="primary-shadow"> Primary </v-btn>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
