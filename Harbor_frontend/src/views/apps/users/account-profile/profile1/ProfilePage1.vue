<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// tabs import
import ProfileTab from './ProfileTab.vue';
import ProfileUpdate from './ProfileUpdate.vue';

// icons
import { UserCircleIcon } from 'vue-tabler-icons';
// theme breadcrumb
const page = ref({ title: '사원 정보' });
const breadcrumbs = ref([
  {
    title: 'Users',
    disabled: false,
    href: '#'
  },
  {
    title: 'Account',
    disabled: false,
    href: '#'
  },
  {
    title: 'Profile',
    disabled: true,
    href: '#'
  }
]);
// tabs data
const tab = ref('tab-profile');
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <v-card-text>
            <v-tabs v-model="tab" color="primary">
              <v-tab variant="plain" value="tab-profile">
                <UserCircleIcon class="v-icon--start" width="20" stroke-width="1.5" /> Profile
              </v-tab>
              <v-tab variant="plain" value="tab-update">
                <UserCircleIcon class="v-icon--start" width="20" stroke-width="1.5" /> 수정
              </v-tab>
            </v-tabs>
            <v-divider></v-divider>
            <div class="pt-6">
              <v-window v-model="tab">
                <v-window-item value="tab-profile">
                  <ProfileTab />
                </v-window-item>
              </v-window>
              <v-window v-model="tab">
                <v-window-item value="tab-update">
                  <ProfileUpdate />
                </v-window-item>
              </v-window>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
