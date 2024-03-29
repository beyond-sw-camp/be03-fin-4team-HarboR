<script setup lang="ts">
import { ref, shallowRef } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';
import { AppsIcon, CircleDotIcon, DragDropIcon, FolderIcon, ChevronUpIcon, ChevronDownIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Lists' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Lists',
    disabled: true,
    href: '#'
  }
]);

// List 1,3 Data
const list1 = shallowRef([
  {
    name: 'Sample Page',
    icon: AppsIcon,
    id: 1,
    link: '/basic/uilist'
  },
  {
    name: 'Elements',
    icon: AppsIcon,
    id: 2,
    link: ''
  },
  {
    name: 'Page Layouts',
    icon: AppsIcon,
    id: 3,
    link: ''
  }
]);

// List 2 data
const open = ref(['Users']);

// List 4 data
const swt1 = ref(true);
const swt2 = ref(false);

// List 5 data
const folders = shallowRef([
  {
    subtitle: 'Jan 9, 2022',
    title: 'Photos'
  },
  {
    subtitle: 'Jan 17, 2022',
    title: 'Recipes'
  }
]);

// custom list data
const customs = shallowRef([
  {
    title: 'Bajaj Finsery',
    profit: true,
    percent: '10',
    price: '1839.00'
  },
  {
    title: 'TTML',
    profit: false,
    percent: '10',
    price: '100.00'
  },
  {
    title: 'Reliance',
    profit: true,
    percent: '10',
    price: '200.00'
  },
  {
    title: 'TTML',
    profit: false,
    percent: '10',
    price: '189.00'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="List">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic">
              <v-list>
                <v-list-item class="mb-2" v-for="(list, i) in list1" :value="list" rounded="md" :key="i" color="secondary">
                  <template v-slot:prepend>
                    <component :is="list.icon" size="20" stroke-width="1.5" class="mr-2" />
                  </template>
                  <v-list-item-title>{{ list.name }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Nested List -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Nested List">
              <v-list v-model:opened="open">
                <v-list-group value="Users">
                  <template v-slot:activator="{ props }">
                    <v-list-item v-bind="props" rounded="md" color="secondary" title="1st Level with Icon">
                      <template v-slot:prepend>
                        <DragDropIcon size="20" stroke-width="1.5" class="mr-2" />
                      </template>
                    </v-list-item>
                  </template>
                  <v-list-item value="Nested List" color="secondary" title="Nested List" rounded="md">
                    <template v-slot:prepend>
                      <CircleDotIcon size="15" stroke-width="1.5" class="mr-2" />
                    </template>
                  </v-list-item>
                  <v-list-item value="Nested List 2" color="secondary" title="Nested List 2" rounded="md">
                    <template v-slot:prepend>
                      <CircleDotIcon size="15" stroke-width="1.5" class="mr-2" />
                    </template>
                  </v-list-item>
                </v-list-group>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Selected List Item -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Selected List Item">
              <v-list>
                <v-list-item class="mb-1" v-for="(list, i) in list1" rounded="md" :value="list" :to="list.link" :key="i" color="secondary">
                  <template v-slot:prepend>
                    <component :is="list.icon" size="20" stroke-width="1.5" class="mr-2" />
                  </template>
                  <v-list-item-title>{{ list.name }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Radio Button List -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Radio Button List">
              <div class="bg-lightprimary rounded-sm px-5 py-3 my-3">
                <v-switch v-model="swt1" label="Start DND Mode" color="primary" hide-details></v-switch>
                <v-switch v-model="swt2" label="Allow Notifications" color="primary" hide-details> </v-switch>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Folder List -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Folder List">
              <v-list lines="two">
                <v-list-item v-for="folder in folders" :key="folder.title" :subtitle="folder.subtitle">
                  <template v-slot:prepend>
                    <v-avatar color="primary">
                      <FolderIcon size="20" stroke-width="1.5" />
                    </v-avatar>
                  </template>

                  <template v-slot:title>
                    <h5 class="text-subtitle-1">{{ folder.title }}</h5>
                  </template>
                  <template v-slot:subtitle>
                    <span class="text-subtitle-2">{{ folder.subtitle }}</span>
                  </template>
                </v-list-item>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Rounded List -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Rounded List">
              <v-list>
                <v-list-item v-for="(list, i) in list1" :value="list" rounded="md" :key="i" color="secondary">
                  <template v-slot:prepend>
                    <component :is="list.icon" size="20" stroke-width="1.5" class="mr-2" />
                  </template>
                  <v-list-item-title>{{ list.name }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Custom Aligned List -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Custom Aligned List">
              <v-list two-lines>
                <template v-for="(custom, i) in customs" :key="i">
                  <v-list-item color="secondary" class="py-3">
                    <v-list-item-title class="text-subtitle-1">{{ custom.title }}</v-list-item-title>
                    <v-list-item-subtitle v-if="custom.profit" class="text-subtitle-2 text-success text-high-emphasis"
                      >{{ custom.percent }}% Profit</v-list-item-subtitle
                    >
                    <v-list-item-subtitle v-else class="text-subtitle-2 text-error text-high-emphasis"
                      >{{ custom.percent }}% Loss</v-list-item-subtitle
                    >
                    <template v-slot:append>
                      <v-list-item-subtitle class="text-subtitle-1 text-high-emphasis mr-3"> ${{ custom.price }}</v-list-item-subtitle>
                      <v-chip color="success" label size="x-small" v-if="custom.profit">
                        <ChevronUpIcon size="17" stroke-width="1.5" />
                      </v-chip>
                      <v-chip color="error" label size="x-small" v-else>
                        <ChevronDownIcon size="17" stroke-width="1.5" />
                      </v-chip>
                    </template>
                  </v-list-item>
                  <v-divider></v-divider>
                </template>
              </v-list>
            </UiChildCard>
          </v-col>
          <!-- Scrollable List -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Scrollable List">
              <perfect-scrollbar style="max-height: 310px">
                <v-list>
                  <v-list-item v-for="(n, index) in 200" :value="n" :key="index" color="secondary">
                    <v-list-item-title>Item {{ n }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </perfect-scrollbar>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
