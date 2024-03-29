<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Pagination' });
const breadcrumbs = ref([
  {
    title: 'Advance',
    disabled: false,
    href: '#'
  },
  {
    title: 'Pagination',
    disabled: true,
    href: '#'
  }
]);

// pagination data
const pagination = ref(1);
const pagination2 = ref(1);
const pagination3 = ref(1);
const pagination4 = ref(1);
const pagination5 = ref(1);
const pagination6 = ref(1);
const pagination7 = ref(1);
const pagination8 = ref(1);
const pagination9 = ref(1);
const pagination10 = ref(1);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Pagination">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic">
              <div class="text-center">
                <v-pagination v-model="pagination" :length="6"></v-pagination>
                <v-pagination active-color="primary" v-model="pagination" :length="6"></v-pagination>
                <v-pagination active-color="secondary" v-model="pagination" :length="6"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Outlined -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Outlined">
              <div class="text-center">
                <v-pagination variant="outlined" v-model="pagination2" :length="6"></v-pagination>
                <v-pagination variant="outlined" active-color="primary" v-model="pagination2" :length="6"></v-pagination>
                <v-pagination variant="outlined" active-color="secondary" v-model="pagination2" :length="6"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Rounded -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Rounded">
              <div class="text-center">
                <v-pagination rounded="circle" v-model="pagination3" :length="6"></v-pagination>
                <v-pagination rounded="circle" active-color="primary" v-model="pagination3" :length="6"> </v-pagination>
                <v-pagination rounded="circle" active-color="secondary" v-model="pagination3" :length="6"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Square -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Square">
              <div class="text-center">
                <v-pagination rounded="0" v-model="pagination4" :length="6"></v-pagination>
                <v-pagination rounded="0" active-color="primary" v-model="pagination4" :length="6"> </v-pagination>
                <v-pagination rounded="0" active-color="secondary" v-model="pagination4" :length="6"> </v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Flat -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Flat">
              <div class="text-center">
                <v-pagination variant="flat" v-model="pagination5" :length="6"></v-pagination>
                <v-pagination variant="flat" active-color="primary" v-model="pagination5" :length="6"> </v-pagination>
                <v-pagination variant="flat" active-color="secondary" v-model="pagination5" :length="6"> </v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Size -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Size">
              <div class="text-center">
                <v-pagination active-color="primary" density="compact" v-model="pagination6" :length="6"></v-pagination>
                <v-pagination active-color="primary" density="comfortable" v-model="pagination6" :length="6"></v-pagination>
                <v-pagination active-color="primary" v-model="pagination6" :length="6"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Disabled -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Disabled">
              <div class="text-center">
                <v-pagination disabled active-color="primary" v-model="pagination7" :length="6"> </v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Icons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Icons">
              <div class="text-center">
                <v-pagination
                  active-color="primary"
                  prev-icon="mdi-menu-left"
                  next-icon="mdi-menu-right"
                  v-model="pagination8"
                  :length="6"
                ></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Length -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Length">
              <div class="text-center">
                <v-pagination active-color="primary" v-model="pagination9" :length="15"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Total visible -->
          <v-col cols="12" lg="8">
            <UiChildCard title="Total visible">
              <div class="text-center overflow-auto">
                <v-pagination active-color="primary" v-model="pagination10" :length="15" :total-visible="7"></v-pagination>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
