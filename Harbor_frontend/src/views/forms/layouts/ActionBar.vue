<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import CardHeaderFooter from '@/components/shared/CardHeaderFooter.vue';

// theme breadcrumb
const page = ref({ title: 'Action Bar' });
const breadcrumbs = ref([
  {
    title: 'Layouts',
    disabled: false,
    href: '#'
  },
  {
    title: 'Action Bar',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <!-- ----------------------------------------------------- -->
  <!-- Action Bar -->
  <!-- ----------------------------------------------------- -->
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <!-- Simple Action Bar -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Simple Action Bar">
        <v-label class="mb-2 font-weight-medium">Name</v-label>
        <v-text-field
          hint="Please enter your full name"
          persistent-hint
          variant="outlined"
          persistent-placeholder
          placeholder="Enter full name"
          color="primary"
        ></v-text-field>

        <template v-slot:footer>
          <v-btn color="secondary" variant="flat">Submit</v-btn>
          <v-btn color="primary" variant="outlined">Cancel</v-btn>
        </template>
      </CardHeaderFooter>
    </v-col>
    <!-- Right Align Action Bar -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Right Align Action Bar">
        <v-label class="mb-2 font-weight-medium">Name</v-label>
        <v-text-field
          hint="Please enter your full name"
          persistent-hint
          variant="outlined"
          persistent-placeholder
          placeholder="Enter full name"
          color="primary"
        ></v-text-field>

        <template v-slot:footer>
          <div class="text-right w-100">
            <v-btn color="secondary" variant="flat">Submit</v-btn>
            <v-btn color="primary" variant="outlined">Cancel</v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>
    <!-- Action Button with Link -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Action Button with Link">
        <v-label class="mb-2 font-weight-medium">Name</v-label>
        <v-text-field
          hint="Please enter your full name"
          persistent-hint
          variant="outlined"
          persistent-placeholder
          placeholder="Enter full name"
          color="primary"
        ></v-text-field>

        <template v-slot:footer>
          <div class="text-right w-100">
            <v-btn color="secondary" variant="flat" class="mr-3">Submit</v-btn>
            <span class="light--text">or</span>
            <v-btn color="primary" variant="text" class="ml-3">Clear</v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>
    <!-- Horizontal Form -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Horizontal Form">
        <v-row class="py-md-3">
          <v-col cols="12" md="4" class="text-md-right pb-md-3 pb-0">
            <v-label class="mb-md-2 font-weight-medium mt-md-4">Name</v-label>
          </v-col>
          <v-col cols="12" md="8">
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

        <template v-slot:footer>
          <div class="text-center w-100">
            <v-btn color="secondary" variant="flat" class="mr-3">Submit</v-btn>
            <span class="light--text">or</span>
            <v-btn color="primary" variant="text" class="ml-3">Clear</v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>
    <!-- With side action button -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="With side action button">
        <v-label class="mb-2 font-weight-medium">Name</v-label>
        <v-text-field
          hint="Please enter your full name"
          persistent-hint
          variant="outlined"
          persistent-placeholder
          placeholder="Enter full name"
          color="primary"
        ></v-text-field>

        <template v-slot:footer>
          <div class="d-flex align-center justify-space-between w-100">
            <div>
              <v-btn color="secondary" variant="flat">Submit</v-btn>
              <v-btn color="primary" variant="outlined">Cancel</v-btn>
            </div>

            <v-btn color="error" variant="flat">Delete</v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>
    <!-- Top & BOttom Action Bars -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Top & Bottom Actions Bars">
        <template v-slot:actionbar>
          <div class="pa-6 d-flex align-center justify-space-between w-100">
            <h4 class="text-subtitle-1">Top Actions</h4>
            <v-btn color="error" variant="outlined">Delete</v-btn>
          </div>
          <v-divider></v-divider>
        </template>

        <v-label class="mb-2 font-weight-medium">Name</v-label>
        <v-text-field
          hint="Please enter your full name"
          persistent-hint
          variant="outlined"
          persistent-placeholder
          placeholder="Enter full name"
          color="primary"
        ></v-text-field>

        <template v-slot:footer>
          <v-btn color="secondary" variant="flat">Submit</v-btn>
          <v-btn color="primary" variant="outlined">Cancel</v-btn>
        </template>
      </CardHeaderFooter>
    </v-col>
  </v-row>
</template>
