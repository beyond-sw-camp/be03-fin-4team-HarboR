<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { MailIcon, ShoppingCartIcon, HomeIcon, UsersIcon, BuildingStoreIcon, BellIcon, Menu2Icon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Badges' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Badges',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Badges">
        <v-row>
          <!-- Basic Badges -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Badges">
              <div class="d-flex align-center flex-column flex-sm-row ga-4 justify-center">
                <v-badge color="error" size="small" content="2">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="primary" size="small" content="2">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="secondary" size="small" content="2">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Maximum Value Badges -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Maximum Value Badges">
              <div class="d-flex align-center flex-column flex-sm-row ga-6 justify-center">
                <v-badge color="error" content="9+">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="primary" content="99+">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="secondary" content="999+">
                  <MailIcon stroke-width="1.5" />
                </v-badge>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Dot Badges -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Dot Badges">
              <div class="d-flex align-center ga-6 justify-center">
                <v-badge color="primary" dot>
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="secondary" dot>
                  <MailIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="error" dot>
                  <MailIcon stroke-width="1.5" />
                </v-badge>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Customized Badges -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Customized Badges">
              <div class="d-flex align-center ga-6 justify-center">
                <v-badge color="error" content="2">
                  <ShoppingCartIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="primary" inline content="2">
                  <ShoppingCartIcon stroke-width="1.5" />
                </v-badge>
                <v-badge color="secondary" bordered content="2">
                  <ShoppingCartIcon stroke-width="1.5" />
                </v-badge>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Content -->
          <v-col cols="12" lg="8">
            <UiChildCard title="Content">
              <v-toolbar density="compact" color="surface">
                <v-spacer></v-spacer>

                <v-btn class="text-none" stacked>
                  <v-badge dot color="success">
                    <HomeIcon stroke-width="1.5" width="20" />
                  </v-badge>
                </v-btn>

                <v-btn class="text-none" stacked>
                  <UsersIcon stroke-width="1.5" width="20" />
                </v-btn>

                <v-btn class="text-none" stacked>
                  <v-badge content="9+" color="error">
                    <BuildingStoreIcon stroke-width="1.5" width="20" />
                  </v-badge>
                </v-btn>

                <v-btn class="text-none" stacked>
                  <v-badge content="2" color="secondary">
                    <BellIcon stroke-width="1.5" width="20" />
                  </v-badge>
                </v-btn>

                <v-btn class="text-none" stacked>
                  <Menu2Icon stroke-width="1.5" width="20" />
                </v-btn>

                <v-spacer></v-spacer>
              </v-toolbar>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
