<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// icons
import { FileDescriptionIcon, FileInvoiceIcon, TruckDeliveryIcon } from 'vue-tabler-icons';

import DetailsTab from './DetailsTab.vue';
import InvoiceTab from './InvoiceTab.vue';
import StatusTab from './StatusTab.vue';

// theme breadcrumb
const page = ref({ title: 'Order Details' });
const breadcrumbs = ref([
  {
    title: 'Customer',
    disabled: false,
    href: '#'
  },
  {
    title: 'Order Details',
    disabled: true,
    href: '#'
  }
]);

const tab2 = ref(null);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <!-- customer detail table -->
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-text>
            <v-tabs v-model="tab2" color="primary">
              <v-tab value="11"> <FileDescriptionIcon stroke-width="1.5" width="20" class="v-icon--start" /> Details </v-tab>
              <v-tab value="22"> <FileInvoiceIcon stroke-width="1.5" width="20" class="v-icon--start" /> Invoice </v-tab>
              <v-tab value="33"> <TruckDeliveryIcon stroke-width="1.5" width="20" class="v-icon--start" /> Status </v-tab>
            </v-tabs>
            <v-divider></v-divider>
            <v-card-text class="px-0">
              <v-window v-model="tab2">
                <v-window-item value="11">
                  <!-- Details tab -->
                  <DetailsTab />
                </v-window-item>

                <v-window-item value="22">
                  <InvoiceTab />
                </v-window-item>

                <v-window-item value="33">
                  <StatusTab />
                </v-window-item>
              </v-window>
            </v-card-text>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
