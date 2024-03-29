<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useCustomers } from '@/stores/apps/customers';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import type { Header, Item } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

const store = useCustomers();

const getProducts = computed(() => {
  return store.getProducts;
});

onMounted(() => {
  store.fetchProducts();
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: 'ID', value: 'id', sortable: true },
  { text: 'Product name', value: 'name', sortable: true },
  { text: 'Category', value: 'category', sortable: true },
  { text: 'Price', value: 'price', sortable: true },
  { text: 'Date', value: 'date', sortable: true },
  { text: 'Qty', value: 'qty', sortable: true },
  { text: 'Action', value: 'operation' }
];
const items = ref(getProducts);
const themeColor = ref('rgb(var(--v-theme-secondary))');

const itemsSelected = ref<Item[]>([]);
</script>

<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Product List">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3">
            <v-text-field
              type="text"
              variant="outlined"
              persistent-placeholder
              placeholder="Search Product"
              v-model="searchValue"
              density="compact"
              hide-details
              prepend-inner-icon="mdi-magnify"
            />
          </v-col>
          <v-col cols="12" md="3">
            <div class="d-flex ga-2 justify-end">
              <v-btn icon variant="text">
                <CopyIcon size="20" />
              </v-btn>
              <v-btn icon variant="text">
                <PrinterIcon size="20" />
              </v-btn>
              <v-btn icon variant="text">
                <FilterIcon size="20" />
              </v-btn>
            </div>
          </v-col>
        </v-row>
        <EasyDataTable
          :headers="headers"
          :items="items"
          table-class-name="customize-table"
          :theme-color="themeColor"
          :search-field="searchField"
          :search-value="searchValue"
          :rows-per-page="5"
          v-model:items-selected="itemsSelected"
        >
          <template #item-id="{ id }">
            <div class="player-wrapper">
              <h5 class="text-h5">#{{ id }}</h5>
            </div>
          </template>
          <template #item-name="{ name }">
            <div class="player-wrapper">
              <h5 class="text-h5">{{ name }}</h5>
            </div>
          </template>
          <template #item-price="{ price }">
            <div class="player-wrapper">
              <h5 class="text-h5">${{ price }}</h5>
            </div>
          </template>
          <template #item-operation>
            <div class="operation-wrapper">
              <v-btn icon variant="text">
                <DotsIcon size="18" />
              </v-btn>
            </div>
          </template>
        </EasyDataTable>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
