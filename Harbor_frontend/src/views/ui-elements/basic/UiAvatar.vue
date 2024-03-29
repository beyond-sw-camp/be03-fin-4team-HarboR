<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { UserIcon, SearchIcon, FolderIcon, ContrastIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Avatar' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Avatar',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Avatar">
        <v-row>
          <!-- Basic Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Basic Avatar">
              <div class="text-center">
                <v-avatar variant="flat" color="primary">
                  <UserIcon />
                </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Image Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Image Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar size="40" variant="flat">
                  <img src="@/assets/images/users/avatar-1.png" width="40" alt="Julia" />
                </v-avatar>
                <v-avatar size="40" variant="flat">
                  <img src="@/assets/images/users/avatar-2.png" width="40" alt="Julia" />
                </v-avatar>
                <v-avatar size="40" variant="flat">
                  <img src="@/assets/images/users/avatar-3.png" width="40" alt="Julia" />
                </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Letter Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Letter Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar size="40" color="primary" variant="flat"> AD </v-avatar>
                <v-avatar size="40" color="secondary" variant="flat"> GE </v-avatar>
                <v-avatar size="40" color="error" variant="flat"> HY </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Icon Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Icon Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar size="40" color="lightprimary" class="text-primary" variant="flat">
                  <SearchIcon size="20" />
                </v-avatar>
                <v-avatar size="40" variant="flat" color="lightsecondary" class="text-secondary">
                  <FolderIcon size="20" />
                </v-avatar>
                <v-avatar size="40" color="lighterror" class="text-error" variant="flat">
                  <ContrastIcon size="20" />
                </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Variant Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Variant Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar size="40" color="lightprimary" class="text-primary" variant="flat" rounded="sm">
                  <SearchIcon size="20" />
                </v-avatar>
                <v-avatar size="40" color="lightwarning" variant="flat" class="text-warning" rounded="md">
                  <FolderIcon size="20" />
                </v-avatar>
                <v-avatar size="40" color="lightsuccess" class="text-success" variant="flat" rounded="lg">
                  <ContrastIcon size="20" />
                </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Outline Avatar -->
          <v-col cols="12" lg="3">
            <UiChildCard title="Size Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar size="25" color="primary" variant="flat"> AD </v-avatar>
                <v-avatar size="40" color="secondary" variant="flat"> GE </v-avatar>
                <v-avatar size="50" color="error" variant="flat"> HY </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Fallbacks Avatar -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Colors Avatar">
              <div class="d-flex align-center ga-4 justify-center">
                <v-avatar color="primary" variant="flat"> A </v-avatar>
                <v-avatar color="secondary" variant="flat"> B </v-avatar>
                <v-avatar color="error" variant="flat"> C </v-avatar>
                <v-avatar color="warning" variant="flat"> D </v-avatar>
                <v-avatar color="success" variant="flat"> E </v-avatar>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
