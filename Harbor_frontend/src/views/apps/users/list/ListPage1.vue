<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useUserCardStore } from '@/stores/apps/UserCard';

// common components
import type { Header } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const store = useUserCardStore();

onMounted(() => {
  store.fetchlistCards();
});
type ListItem = {
  avatar: string;
  email: string;
  name: string;
  verify: boolean;
  // Add other properties as needed
};
const listCards = computed<ListItem[]>(() => {
  return store.list;
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: '#', value: 'id' },
  { text: 'User Profile', value: 'name', sortable: true },
  { text: 'Country', value: 'location', sortable: true },
  { text: 'Friends', value: 'friends', sortable: true },
  { text: 'Followers', value: 'followers', sortable: true },
  { text: 'Status', value: 'status', sortable: true },
  { text: 'Action', value: 'operation' }
];
const items = ref(listCards);
const themeColor = ref('rgb(var(--v-theme-secondary))');
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Customer List">
        <v-row justify="space-between" class="align-center mb-3">
          <v-col cols="12" md="3">
            <v-text-field
              type="text"
              variant="outlined"
              persistent-placeholder
              placeholder="Search Customer"
              v-model="searchValue"
              density="compact"
              hide-details
              prepend-inner-icon="mdi-magnify"
            />
          </v-col>
        </v-row>
        <div class="overflow-auto">
          <EasyDataTable
            :headers="headers"
            :items="items"
            table-class-name="customize-table action-position"
            :theme-color="themeColor"
            :search-field="searchField"
            :search-value="searchValue"
            :rows-per-page="8"
          >
            <template #item-name="{ name, email, avatar, verify }">
              <div class="d-flex align-center ga-4">
                <img :src="avatar" alt="avatar" width="40" />

                <div>
                  <h5 class="text-h5">
                    {{ name }}
                    <v-btn icon v-if="verify" flat color="success" size="small" class="verifybtn">
                      <CheckIcon size="10" />
                    </v-btn>
                  </h5>
                  <small class="text-subtitle">{{ email }}</small>
                </div>
              </div>
            </template>
            <template #item-status="{ status }">
              <v-chip color="success" v-if="status === 'Active'" size="small"> Active </v-chip>
              <v-chip color="error" v-if="status === 'Rejected'" size="small"> Rejected </v-chip>
              <v-chip color="warning" v-if="status === 'Pending'" size="small"> Pending </v-chip>
            </template>
            <template #item-operation>
              <div class="operation-wrapper">
                <v-btn icon color="secondary" variant="text">
                  <MessageIcon size="20" />
                </v-btn>
                <v-btn icon color="error" variant="text">
                  <BanIcon size="20" />
                </v-btn>
              </div>
            </template>
          </EasyDataTable>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.verifybtn {
  height: 12px !important;
  width: 12px !important;
  margin-top: -3px;
}

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

@media (max-width: 475px) {
  .easy-data-table__rows-selector {
    width: 50px !important;
    margin: 0 15px !important;
  }
  .vue3-easy-data-table__footer {
    text-align: center;
    padding-top: 15px !important;
  }
}
</style>
