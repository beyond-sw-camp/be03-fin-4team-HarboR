<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useCustomers } from '@/stores/apps/customers';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import type { Header, Item } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

const store = useCustomers();

const getOrders = computed(() => {
  return store.getOrders;
});

onMounted(() => {
  store.fetchOrders();
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: 'ID', value: 'id', sortable: true },
  { text: 'Customer name', value: 'name', sortable: true },
  { text: 'Branch', value: 'company', sortable: true },
  { text: 'Payment Type', value: 'type', sortable: true },
  { text: 'Quantity', value: 'qty', sortable: true },
  { text: 'Registered', value: 'date', sortable: true },
  { text: 'Status', value: 'status', sortable: true },
  { text: 'Action', value: 'operation' }
];
const items = ref(getOrders);
const themeColor = ref('rgb(var(--v-theme-secondary))');
const { deleteOrder } = store;
const itemsSelected = ref<Item[]>([]);
</script>

<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Order List">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3">
            <v-text-field
              type="text"
              variant="outlined"
              persistent-placeholder
              placeholder="Search Order"
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
          table-class-name="customize-table action-position"
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
          <template #item-status="{ status }">
            <v-chip color="success" v-if="status === 1" size="small"> Complete </v-chip>
            <v-chip color="error" v-if="status === 2" size="small"> Processing </v-chip>
            <v-chip color="primary" v-if="status === 3" size="small"> Confirm </v-chip>
          </template>
          <template #item-operation="item">
            <div class="operation-wrapper">
              <v-btn icon color="secondary" variant="text">
                <EyeIcon size="20" />
              </v-btn>
              <v-btn icon color="primary" variant="text" @click="deleteOrder(item.id)">
                <TrashIcon size="20" />
              </v-btn>
            </div>
          </template>
        </EasyDataTable>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style lang="scss">
.action-position {
  .vue3-easy-data-table__main {
    .vue3-easy-data-table__header {
      th {
        &:last-child {
          padding-left: 35px;
        }
      }
    }
  }
}
</style>
