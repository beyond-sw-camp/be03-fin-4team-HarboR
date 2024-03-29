<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import CardHeaderFooter from '@/components/shared/CardHeaderFooter.vue';

// icons
import { ClipboardListIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Sticky Action Bar' });
const breadcrumbs = ref([
  {
    title: 'Layouts',
    disabled: false,
    href: '#'
  },
  {
    title: 'Sticky Action Bar',
    disabled: true,
    href: '#'
  }
]);

const checkbox1 = ref(false);
const checkbox2 = ref(false);
const checkbox3 = ref(false);
const checkbox4 = ref(false);
const checkbox5 = ref(false);
const checkbox6 = ref(false);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <CardHeaderFooter title="Simple Form Layout">
        <template v-slot:actionbar>
          <v-toolbar flat class="px-2 py-2" color="inherit">
            <v-toolbar-title class="text-subtitle-1">Sticky Action Bar</v-toolbar-title>
            <template v-slot:append>
              <v-btn variant="flat" color="secondary" class="mr-3">Submit</v-btn>
              <v-btn variant="flat" color="primary">Clear</v-btn>
            </template>
          </v-toolbar>
          <v-divider></v-divider>
        </template>
        <div class="pb-0 pb-sm-6">
          <v-row class="pb-4 pb-sm-0">
            <v-col cols="3" lg="4">
              <div class="d-flex align-center justify-end">
                <v-btn icon color="secondary" rounded="sm" variant="flat">
                  <ClipboardListIcon stroke-width="1.5" size="22" />
                </v-btn>
              </div>
            </v-col>
            <v-col cols="7" lg="6">
              <h3 class="text-18 mb-n1 mt-n1">Personal Information</h3>
              <span class="text-subtitle-1 text-medium-emphasis">Sticky Action Bar Lorem Ipsum is simply</span>
            </v-col>
          </v-row>
        </div>
        <v-divider></v-divider>
        <v-row class="pt-0 pt-sm-7 mb-4 mb-md-0">
          <v-col sm="3" lg="4"></v-col>
          <v-col cols="12" lg="6" sm="9">
            <h4 class="font-weight-medium mb-2">A. Personal Info:</h4>
          </v-col>
        </v-row>

        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end mb-0 mb-sm-5">Name</v-label>
          </v-col>
          <v-col cols="12" sm="7" lg="6">
            <v-text-field
              hint="Please enter your full name"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter full name"
              color="primary"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-sm-0 pt-5 pb-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end mb-0 mb-sm-5">Email</v-label>
          </v-col>
          <v-col cols="12" sm="7" lg="6">
            <v-text-field
              hint="Please enter your Email"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter email"
              color="primary"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-divider class="mt-9"></v-divider>
        <v-row class="pt-0 pt-sm-7 mb-4 mb-sm-0">
          <v-col sm="3" lg="4"></v-col>
          <v-col lg="8" cols="12" sm="9">
            <h4 class="font-weight-medium mb-2">B. Educational Info:</h4>
          </v-col>
        </v-row>
        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end mb-0 mb-sm-5">Degree Name</v-label>
          </v-col>
          <v-col cols="12" sm="7" lg="6">
            <v-text-field
              hint="Please enter your Degree name"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter Degree name"
              color="primary"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-sm-0 pt-5 pb-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end mb-0 mb-sm-5">Passing Year</v-label>
          </v-col>
          <v-col cols="12" sm="7" lg="6">
            <v-text-field
              hint="Please enter Passing Year"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter Passing Year"
              color="primary"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-sm-0 pt-5 pb-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end">Language</v-label>
          </v-col>
          <v-col cols="12" sm="9" lg="6">
            <div class="d-flex ga-4">
              <v-checkbox
                density="compact"
                v-model="checkbox1"
                hide-details
                color="primary"
                label="English"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox
                density="compact"
                v-model="checkbox2"
                hide-details
                color="primary"
                label="French"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox density="compact" v-model="checkbox3" hide-details color="primary" label="Dutch" class="flex-grow-0"></v-checkbox>
            </div>
          </v-col>
        </v-row>

        <v-row class="d-flex align-center">
          <v-col cols="12" sm="3" lg="4" class="py-sm-0 pt-5 pb-0">
            <v-label class="font-weight-medium d-flex justify-start justify-sm-end">Hobby</v-label>
          </v-col>
          <v-col cols="12" sm="9" lg="6">
            <div class="d-flex ga-4 flex-wrap">
              <v-checkbox
                density="compact"
                v-model="checkbox4"
                hide-details
                color="primary"
                label="Reading"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox
                density="compact"
                v-model="checkbox5"
                hide-details
                color="primary"
                label="Dancing"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox
                density="compact"
                v-model="checkbox6"
                hide-details
                color="primary"
                label="Swimming"
                class="flex-grow-0"
              ></v-checkbox>
            </div>
          </v-col>
        </v-row>

        <template v-slot:footer>
          <v-row>
            <v-col cols="12" sm="3" lg="4" class="d-none d-sm-block"></v-col>
            <v-col cols="12" sm="9" lg="8">
              <v-btn variant="flat" color="secondary" class="mr-2">Submit</v-btn>
              <v-btn variant="flat" color="primary">Clear</v-btn>
            </v-col>
          </v-row>
        </template>
      </CardHeaderFooter>
    </v-col>
  </v-row>
</template>
