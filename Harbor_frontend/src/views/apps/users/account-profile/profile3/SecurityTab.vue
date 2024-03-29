<script setup lang="ts"></script>

<template>
  <v-row>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="px-5 py-6">
            <h5 class="text-subtitle-1">Change Password</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12">
                <v-text-field type="text" label="Current password" color="primary" variant="outlined" hide-details></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field type="email" label="New Password" color="primary" variant="outlined" hide-details></v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field type="text" label="Re-enter New Password" color="primary" variant="outlined" hide-details></v-text-field>
              </v-col>
            </v-row>
            <v-btn color="primary" class="mt-5">Change Password</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-6">
            <h5 class="text-subtitle-1">Delete Account</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <p>
              To deactivate your account, first delete its resources. If you are the only owner of any teams, either assign another owner or
              deactivate the team.
            </p>

            <v-btn color="error" variant="outlined" class="mt-4">Deactivate Account</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
