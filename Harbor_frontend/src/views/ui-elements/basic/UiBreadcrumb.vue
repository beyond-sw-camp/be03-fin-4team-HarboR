<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

import { ChevronRightIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Breadcrumb' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Breadcrumb',
    disabled: true,
    href: '#'
  }
]);

// breadcrumb data
const items = ref([
  {
    title: 'Dashboard',
    disabled: false,
    href: 'breadcrumbs_dashboard'
  },
  {
    title: 'Basic',
    disabled: false,
    href: 'breadcrumbs_link_1'
  },
  {
    title: 'Breadcrumb',
    disabled: true,
    href: 'breadcrumbs_link_2'
  }
]);
</script>

<style scoped>
.v-breadcrumbs {
  display: block;
}
</style>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Breadcrumb">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <v-breadcrumbs :items="items"></v-breadcrumbs>
              </v-toolbar>
            </UiChildCard>
          </v-col>
          <!-- Custom Separator -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Custom Separator">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <v-breadcrumbs :items="items">
                  <template v-slot:divider>
                    <div class="d-flex align-center">
                      <ChevronRightIcon size="17" />
                    </div>
                  </template>
                </v-breadcrumbs>
              </v-toolbar>
            </UiChildCard>
          </v-col>
          <!-- With Title -->
          <v-col cols="12" lg="6">
            <UiChildCard title="With Title">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <div>
                  <v-toolbar-title class="ml-4 mb-n4">Breadcrumb</v-toolbar-title>
                  <v-breadcrumbs :items="items" class="pb-0">
                    <template v-slot:divider>
                      <div class="d-flex align-center">
                        <ChevronRightIcon size="17" />
                      </div>
                    </template>
                  </v-breadcrumbs>
                </div>
              </v-toolbar>
            </UiChildCard>
          </v-col>
          <!-- Title Bottom -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Title Bottom">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <div>
                  <v-breadcrumbs :items="items" class="pt-0">
                    <template v-slot:divider>
                      <div class="d-flex align-center">
                        <ChevronRightIcon size="17" />
                      </div>
                    </template>
                  </v-breadcrumbs>
                  <v-toolbar-title class="ml-4 mt-n4">Breadcrumb</v-toolbar-title>
                </div>
              </v-toolbar>
            </UiChildCard>
          </v-col>
          <!-- With Icons -->
          <v-col cols="12">
            <UiChildCard title="With Icons">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <div>
                  <v-toolbar-title class="ml-4 mb-n4">Breadcrumb</v-toolbar-title>
                  <v-breadcrumbs :items="items" class="pb-0">
                    <template v-slot:divider>
                      <div class="d-flex align-center">
                        <ChevronRightIcon size="17" />
                      </div>
                    </template>
                    <template v-slot:prepend>
                      <v-icon size="small" icon="mdi-home" class="text-secondary"></v-icon>
                    </template>
                  </v-breadcrumbs>
                </div>
              </v-toolbar>
            </UiChildCard>
          </v-col>
          <!-- Only Dashboard Icon -->
          <v-col cols="12">
            <UiChildCard title="Only Dashboard Icon">
              <v-toolbar rounded="md" color="gray100" class="font-weight-medium" dark prominent>
                <v-toolbar-title>Breadcrumb</v-toolbar-title>

                <v-spacer></v-spacer>

                <v-breadcrumbs :items="items">
                  <template v-slot:divider>
                    <div class="d-flex align-center">
                      <ChevronRightIcon size="17" />
                    </div>
                  </template>
                  <template v-slot:prepend>
                    <v-icon size="small" icon="mdi-home" class="text-secondary"></v-icon>
                  </template>
                </v-breadcrumbs>
              </v-toolbar>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
