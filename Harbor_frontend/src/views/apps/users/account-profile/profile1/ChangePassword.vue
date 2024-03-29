<script setup lang="ts">
import { ref } from 'vue';

// icons
import { AlertCircleIcon } from 'vue-tabler-icons';

const cpassword = ref('');
const npassword = ref('');
const conpassword = ref('');
</script>

<template>
  <v-alert prominent type="warning" variant="outlined" rounded="md" class="mb-6">
    <template v-slot:prepend>
      <AlertCircleIcon width="22" stroke-width="1.5" />
    </template>
    <div>
      <h5 class="text-subtitle-1 mb-1 lightText-text">Alert!</h5>
      <span
        >Your Password will expire in every 3 months. So change it periodically.<span class="font-weight-medium"
          >Do not share your password</span
        >
      </span>
    </div>
  </v-alert>
  <v-row>
    <v-col cols="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Change Password</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="Current Password" variant="outlined" type="password" hide-details v-model="cpassword">
                </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="New Password" variant="outlined" type="password" hide-details v-model="npassword">
                </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field
                  color="primary"
                  label="Confirm Password"
                  variant="outlined"
                  type="password"
                  hide-details
                  v-model="conpassword"
                >
                </v-text-field>
              </v-col>
            </v-row>
            <div class="text-right mt-4">
              <v-btn color="primary">Change Password</v-btn>
              <v-btn color="error" variant="text" class="ml-2">Clear</v-btn>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
