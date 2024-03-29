<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { MailIcon } from 'vue-tabler-icons';

// combo data
const items = ref([
  'The Dark Knight',
  'Control with Control',
  'Combo with Solo',
  'The Dark',
  'Fight Club',
  'demo@company.com',
  'Pulp Fiction'
]);
const items2 = ref([
  'The Dark Knight',
  'Control with Control',
  'Combo with Solo',
  'The Dark',
  'Fight Club',
  'demo@company.com',
  'Pulp Fiction'
]);
const value = ref(['The Dark Knight']);
const cap_value = ref(['demo@company.com']);
const cap_value2 = ref(['demo@company.com']);
const multi_value = ref(['The Dark Knight', 'Fight Club']);

// theme breadcrumb
const page = ref({ title: 'Autocomplete' });
const breadcrumbs = ref([
  {
    title: 'Components',
    disabled: false,
    href: '#'
  },
  {
    title: 'Autocomplete',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Autocomplete">
        <v-row>
          <!-- Combo Box -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Combo Box">
              <v-autocomplete v-model="value" :items="items" color="primary" variant="outlined" hide-details></v-autocomplete>
            </UiChildCard>
          </v-col>
          <!-- With Caption -->
          <v-col cols="12" lg="4">
            <UiChildCard title="With Caption">
              <v-autocomplete
                v-model="cap_value"
                :items="items"
                color="primary"
                label="Email Address"
                variant="outlined"
                hide-details
              ></v-autocomplete>

              <v-autocomplete
                v-model="cap_value2"
                :items="items"
                color="primary"
                label="Email Address"
                variant="outlined"
                hide-details
                class="mt-5"
              >
                <template v-slot:prepend-inner>
                  <MailIcon stroke-width="1.5" size="22" />
                </template>
              </v-autocomplete>
            </UiChildCard>
          </v-col>
          <!-- Combo with Multiple Options -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Combo with Multiple Options">
              <v-autocomplete
                v-model="multi_value"
                :items="items2"
                variant="outlined"
                color="primary"
                label="Outlined"
                multiple
                hide-details
                closable-chips
              >
                <template v-slot:chip>
                  <v-chip label color="secondary" size="large" class="mb-1 text-subtitle-1 font-weight-regular"></v-chip>
                </template>
              </v-autocomplete>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
