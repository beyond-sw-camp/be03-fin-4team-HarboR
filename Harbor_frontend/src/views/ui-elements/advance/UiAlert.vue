<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Alert' });
const breadcrumbs = ref([
  {
    title: 'Advance',
    disabled: false,
    href: '#'
  },
  {
    title: 'Alert',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Alert">
        <v-row>
          <!-- Basic -->
          <v-col cols="12">
            <UiChildCard title="Basic">
              <v-alert type="success" class="text-white mb-4">I'm a success alert. </v-alert>
              <v-alert type="info" class="text-white mb-4">I'm a info alert. </v-alert>
              <v-alert type="warning" class="text-white mb-4">I'm a warning alert. </v-alert>
              <v-alert type="error" class="text-white mb-4">I'm a error alert. </v-alert>
            </UiChildCard>
          </v-col>
          <!-- Description -->
          <v-col cols="12">
            <UiChildCard title="Description">
              <v-alert type="success" variant="tonal" class="mb-4">
                <h5 class="text-h5 text-capitalize">success</h5>
                <div>This is an success alert — check it out!</div>
              </v-alert>
              <v-alert type="info" variant="tonal" class="mb-4">
                <h5 class="text-h5 text-capitalize">info</h5>
                <div>This is an info alert — check it out!</div>
              </v-alert>
              <v-alert type="warning" variant="tonal" class="mb-4">
                <h5 class="text-h5 text-capitalize">warning</h5>
                <div>This is an warning alert — check it out!</div>
              </v-alert>
              <v-alert type="error" variant="tonal" class="mb-4">
                <h5 class="text-h5 text-capitalize">error</h5>
                <div>This is an error alert — check it out!</div>
              </v-alert>
            </UiChildCard>
          </v-col>
          <!-- With Action -->
          <v-col cols="12">
            <UiChildCard title="With Action" class="alert-with-action">
              <v-alert type="success" variant="tonal" density="compact" class="mb-4 single-line-alert" closable>
                <div>This is an success alert.</div>
                <template v-slot:append>
                  <v-btn variant="text" color="success" class="ml-auto">Undo</v-btn>
                </template>
              </v-alert>
              <v-alert type="info" variant="tonal" density="compact" class="mb-4 single-line-alert" closable>
                <div>This is an info alert.</div>
                <template v-slot:append>
                  <v-btn variant="text" color="info" class="ml-auto">Undo</v-btn>
                </template>
              </v-alert>
              <v-alert type="warning" variant="tonal" density="compact" class="mb-4 single-line-alert" closable>
                <div>This is an warning alert.</div>
                <template v-slot:append>
                  <v-btn variant="text" color="warning" class="ml-auto">Undo</v-btn>
                </template>
              </v-alert>
              <v-alert type="error" variant="tonal" density="compact" class="mb-4 single-line-alert" closable>
                <div>This is an error alert.</div>
                <template v-slot:append>
                  <v-btn variant="text" color="error" class="ml-auto">Undo</v-btn>
                </template>
              </v-alert>
            </UiChildCard>
          </v-col>
          <!-- With Icons -->
          <v-col cols="12">
            <UiChildCard title="With Icons">
              <v-alert color="success" variant="tonal" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-checkbox-marked-circle-outline</v-icon>
                </template>
                <div>This is an success alert.</div>
              </v-alert>
              <v-alert color="info" variant="tonal" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-circle-outline</v-icon>
                </template>
                <div>This is an info alert.</div>
              </v-alert>
              <v-alert color="warning" variant="tonal" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-outline</v-icon>
                </template>
                <div>This is an warning alert.</div>
              </v-alert>
              <v-alert color="error" variant="tonal" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-circle-outline</v-icon>
                </template>
                <div>This is an error alert.</div>
              </v-alert>
            </UiChildCard>
          </v-col>
          <!-- Outlined -->
          <v-col cols="12">
            <UiChildCard title="Outlined">
              <v-alert type="success" variant="outlined" density="compact" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-checkbox-marked-circle-outline</v-icon>
                </template>
                <div>This is an success alert.</div>
              </v-alert>
              <v-alert type="info" variant="outlined" density="compact" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-circle-outline</v-icon>
                </template>
                <div>This is an info alert.</div>
              </v-alert>
              <v-alert type="warning" variant="outlined" density="compact" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-outline</v-icon>
                </template>
                <div>This is an warning alert.</div>
              </v-alert>
              <v-alert type="error" variant="outlined" density="compact" class="mb-4">
                <template v-slot:prepend>
                  <v-icon class="text-24">mdi-alert-circle-outline</v-icon>
                </template>
                <div>This is an error alert.</div>
              </v-alert>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
