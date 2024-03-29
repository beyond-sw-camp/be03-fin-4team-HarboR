<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// tabs import
import ProfileTab from './ProfileTab.vue';
import PersonalDetailsTab from './PersonalDetailsTab.vue';
import MyAccountTab from './MyAccountTab.vue';
import ChangePassword from './ChangePassword.vue';
import SettingsTab from './SettingsTab.vue';

// icons
import { UserCircleIcon, FileDescriptionIcon, IdIcon, LockIcon, MailIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Profile 01' });
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
    title: 'Profile 01',
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
              <v-tab variant="plain" value="tab-profile"
                ><UserCircleIcon class="v-icon--start" width="20" stroke-width="1.5" /> Profile</v-tab
              >
              <v-tab variant="plain" value="tab-personal"
                ><FileDescriptionIcon class="v-icon--start" width="20" stroke-width="1.5" />Personal Details</v-tab
              >
              <v-tab variant="plain" value="tab-account"><IdIcon class="v-icon--start" width="20" stroke-width="1.5" />My Account</v-tab>
              <v-tab variant="plain" value="tab-password"
                ><LockIcon class="v-icon--start" width="20" stroke-width="1.5" />Change Password</v-tab
              >
              <v-tab variant="plain" value="tab-setting"><MailIcon class="v-icon--start" width="20" stroke-width="1.5" />Settings</v-tab>
            </v-tabs>
            <v-divider></v-divider>
            <div class="pt-6">
              <v-window v-model="tab">
                <v-window-item value="tab-profile">
                  <ProfileTab />
                </v-window-item>

                <v-window-item value="tab-personal">
                  <PersonalDetailsTab />
                </v-window-item>

                <v-window-item value="tab-account">
                  <MyAccountTab />
                </v-window-item>

                <v-window-item value="tab-password">
                  <ChangePassword />
                </v-window-item>

                <v-window-item value="tab-setting">
                  <SettingsTab />
                </v-window-item>
              </v-window>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
