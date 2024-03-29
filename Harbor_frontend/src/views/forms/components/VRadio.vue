<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Radio' });
const breadcrumbs = ref([
  {
    title: 'Components',
    disabled: false,
    href: '#'
  },
  {
    title: 'Radio',
    disabled: true,
    href: '#'
  }
]);

const radioGroup = ref('1');
const radioGroup2 = ref('1');
const radioGroup3 = ref('1');
const radioColumn = ref('1');
const radioInline = ref('1');
const radioColors = ref('1');

// radio color data
const radioColor = ref(['primary', 'secondary', 'success', 'error', 'warning']);
</script>

// ===============================|| Ui Radio ||=============================== //
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Radio">
        <v-row>
          <!-- Basic Radio -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic Radio">
              <div class="d-flex ga-4 align-center flex-column flex-sm-row">
                <v-radio-group inline hide-details v-model="radioGroup">
                  <v-radio color="primary" value="1"></v-radio>
                  <v-radio color="primary" value="2"></v-radio>
                </v-radio-group>

                <v-radio-group inline hide-details v-model="radioGroup2">
                  <v-radio disabled color="primary" value="1"></v-radio>
                  <v-radio disabled color="primary" value="2"></v-radio>
                </v-radio-group>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Radio with Label -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Radio with Label">
              <div class="d-flex ga-4 align-center flex-column flex-sm-row">
                <v-radio-group hide-details v-model="radioGroup3" inline>
                  <v-radio label="Checked" color="primary" value="1"></v-radio>
                  <v-radio label="Unchecked" color="primary" value="2"></v-radio>
                </v-radio-group>
                <v-radio-group inline hide-details v-model="radioGroup3">
                  <v-radio disabled color="primary" label="Checked" value="1"></v-radio>
                  <v-radio disabled color="primary" label="Unchecked" value="2"></v-radio>
                </v-radio-group>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Direction -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Direction">
              <div class="d-flex ga-4 align-center flex-column flex-sm-row">
                <v-radio-group v-model="radioColumn" column>
                  <v-radio label="Option 1" color="primary" value="1"></v-radio>
                  <v-radio label="Option 2" color="primary" value="2"></v-radio>
                </v-radio-group>
                <v-radio-group v-model="radioInline" inline>
                  <v-radio label="Option 1" color="primary" value="1"></v-radio>
                  <v-radio label="Option 2" color="primary" value="2"></v-radio>
                </v-radio-group>
              </div>
            </UiChildCard>
          </v-col>
          <!-- With Colors -->
          <v-col cols="12" lg="6">
            <UiChildCard title="With Colors">
              <div class="d-flex ga-4 align-center flex-column flex-sm-row">
                <v-radio-group v-model="radioColors" inline>
                  <v-radio v-for="btn in radioColor" :key="btn" :color="btn" :label="btn" value="1" hide-details></v-radio>
                </v-radio-group>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
