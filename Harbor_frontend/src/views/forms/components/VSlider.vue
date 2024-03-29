<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { Volume2Icon, VolumeIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Slider' });
const breadcrumbs = ref([
  {
    title: 'Components',
    disabled: false,
    href: '#'
  },
  {
    title: 'Slider',
    disabled: true,
    href: '#'
  }
]);

const slider1 = ref(50);
const slider2 = ref(40);
const volume = ref(40);
const min = ref(-50);
const max = ref(90);
const slider3 = ref(40);
const slider4 = ref(40);
const slider5 = ref(40);
const slider6 = ref(40);
const slider7 = ref(40);
const slider8 = ref(40);
</script>

// ===============================|| Ui Slider ||=============================== //
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Slider">
        <v-row>
          <!-- Basic Slider -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Slider">
              <v-slider v-model="slider1" color="primary" label="basic"></v-slider>
            </UiChildCard>
          </v-col>
          <!-- Disabled -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Disabled">
              <v-slider v-model="slider2" color="primary" label="basic" disabled></v-slider>
            </UiChildCard>
          </v-col>
          <!-- Volume -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Volume">
              <v-slider v-model="volume" color="primary" min="40" max="218" :step="1">
                <template v-slot:prepend>
                  <v-btn size="small" variant="text" color="primary">
                    <Volume2Icon stroke-width="1.5" size="20" />
                  </v-btn>
                </template>

                <template v-slot:append>
                  <v-btn size="small" variant="text" color="primary">
                    <VolumeIcon stroke-width="1.5" size="20" />
                  </v-btn>
                </template>
              </v-slider>
            </UiChildCard>
          </v-col>
          <!-- With Label -->
          <v-col cols="12" lg="4">
            <UiChildCard title="With Label">
              <div class="d-flex ga-4 align-center flex-row">
                <v-label class="text-subtitlte-1">Progress</v-label>
                <v-slider v-model="slider3" class="align-center mx-0" :max="max" :min="min" step="1" hide-details color="primary">
                  <template v-slot:append>
                    <v-text-field variant="plain" v-model="slider3"></v-text-field>
                  </template>
                </v-slider>
              </div>
              <div class="d-flex ga-4 align-center flex-row">
                <v-label class="text-subtitlte-1">Progress</v-label>
                <v-slider v-model="slider4" class="align-center mx-0" :max="max" :min="min" step="1" hide-details color="secondary">
                  <template v-slot:append>
                    <v-text-field variant="plain" v-model="slider4"></v-text-field>
                  </template>
                </v-slider>
              </div>
            </UiChildCard>
          </v-col>
          <!-- With Popup value -->
          <v-col cols="12" lg="4">
            <UiChildCard title="With Popup value">
              <v-slider class="mt-3" v-model="slider5" color="primary" step="1" thumb-label="always"></v-slider>
            </UiChildCard>
          </v-col>
          <!-- Steps With Popup Value -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Steps With Popup Value">
              <v-slider class="mt-3" v-model="slider6" color="primary" step="10" show-ticks="always" thumb-label="always"></v-slider>
            </UiChildCard>
          </v-col>
          <!-- Vertical Slider -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Vertical Slider">
              <div class="d-flex">
                <v-slider v-model="slider7" direction="vertical" label="Regular" color="primary"></v-slider>
                <v-slider v-model="slider8" direction="vertical" label="Regular" color="primary" disabled></v-slider>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
