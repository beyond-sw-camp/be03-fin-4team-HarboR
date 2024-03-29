<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// tabs import
import UserProfileTab from './UserProfileTab.vue';
import BillingTab from './BillingTab.vue';
import PaymentTab from './PaymentTab.vue';
import ChangePasswordTab from './ChangePasswordTab.vue';

// theme breadcrumb
const page = ref({ title: 'Profile 02' });
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
    title: 'Profile 02',
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
          <v-card-item>
            <v-card-title>Account Settings</v-card-title>
          </v-card-item>
          <v-divider></v-divider>
          <div class="my-3">
            <v-row class="d-flex flex-row">
              <v-col cols="12" sm="4" class="border-right pr-sm-0 py-sm-0">
                <v-card-text>
                  <v-tabs v-model="tab" direction="vertical" color="primary">
                    <v-tab value="tab-profile" rounded="md" class="text-left overflow-hidden" height="70">
                      <UserIcon stroke-width="1.5" width="20" class="v-icon--start" />
                      <div>
                        <div>User Profile</div>
                        <span class="text-subtitle-2 text-disabled font-weight-medium d-block">Profile Settings</span>
                      </div>
                    </v-tab>
                    <v-tab value="tab-billing" rounded="md" class="text-left overflow-hidden" height="70">
                      <FileDescriptionIcon stroke-width="1.5" width="20" class="v-icon--start" />
                      <div>
                        <div>Billing</div>
                        <span class="text-subtitle-2 text-disabled font-weight-medium d-block">Billing Information</span>
                      </div>
                    </v-tab>
                    <v-tab value="tab-payment" rounded="md" class="text-left overflow-hidden" height="70">
                      <CreditCardIcon stroke-width="1.5" width="20" class="v-icon--start" />
                      <div>
                        <div>Payment</div>
                        <span class="text-subtitle-2 text-disabled font-weight-medium d-block">Add & Update Card</span>
                      </div>
                    </v-tab>
                    <v-tab value="tab-change-password" rounded="md" class="mb-3 text-left overflow-hidden" height="70">
                      <KeyIcon stroke-width="1.5" width="20" class="v-icon--start" />
                      <div>
                        <div>Change Password</div>
                        <span class="text-subtitle-2 text-disabled font-weight-medium d-block">Update Profile Security</span>
                      </div>
                    </v-tab>
                  </v-tabs>
                </v-card-text>
              </v-col>
              <v-col cols="12" sm="8" class="pl-sm-0 py-sm-0">
                <v-card-text>
                  <v-window v-model="tab">
                    <v-window-item value="tab-profile">
                      <UserProfileTab />
                    </v-window-item>
                    <v-window-item value="tab-billing">
                      <BillingTab />
                    </v-window-item>
                    <v-window-item value="tab-payment">
                      <PaymentTab />
                    </v-window-item>
                    <v-window-item value="tab-change-password">
                      <ChangePasswordTab />
                    </v-window-item>
                  </v-window>
                </v-card-text>
              </v-col>
            </v-row>
          </div>

          <v-divider></v-divider>
          <div class="pa-6 text-sm-right text-center">
            <v-btn color="primary">Continue</v-btn>
          </div>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
