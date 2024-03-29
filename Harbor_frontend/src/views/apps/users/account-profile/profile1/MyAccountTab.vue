<script setup lang="ts">
import { ref, shallowRef } from 'vue';

// icons
import { DeviceDesktopIcon, DeviceMobileIcon, DeviceMobileRotatedIcon, CircleXIcon } from 'vue-tabler-icons';

const language = ref(['Washington', 'India', 'Africa', 'New York', 'Malaysia']);
const signing = ref(['Starterup', '2 years', '3 years', '4 years', '5 years']);

const textFname = ref('Asoka_Tana_16');
const textEmail = ref('demo@sample.com');
const textLanguage = ref<string>('Washington');
const textSigning = ref<string>('Starterup');

const secure = ref(false);
const login = ref(false);
const approve = ref(true);

const recognizes = shallowRef([
  { text: 'Cent Desktop', icon: DeviceDesktopIcon, subtext: '4351 Deans Lane, Chelmsford', active: true },
  { text: 'Imho Tablet', icon: DeviceMobileRotatedIcon, subtext: '4185 Michigan Avenue', active: false, time: ' 5 days ago' },
  { text: 'Albs Mobile', icon: DeviceMobileIcon, subtext: '3462 Fairfax Drive, Montcalm', active: false, time: ' 1 month ago' }
]);

const activeSessions = shallowRef([
  { text: 'Cent Desktop', icon: DeviceDesktopIcon, subtext: '4351 Deans Lane, Chelmsford' },
  { text: 'Imho Tablet', icon: DeviceMobileRotatedIcon, subtext: '4185 Michigan Avenue' }
]);
</script>

<template>
  <v-row>
    <v-col cols="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Personal Information</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" lg="6">
                <v-text-field
                  color="primary"
                  label="Username"
                  variant="outlined"
                  type="text"
                  hide-details
                  v-model="textFname"
                  hint="Your Profile URL: https://pc.com/Ashoka_Tano_16"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="Account Email" variant="outlined" type="email" hide-details v-model="textEmail">
                </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-select color="primary" :items="language" label="Language" variant="outlined" v-model="textLanguage" hide-details>
                </v-select>
              </v-col>
              <v-col cols="12" lg="6">
                <v-select color="primary" :items="signing" label="Signing Using" variant="outlined" v-model="textSigning" hide-details>
                </v-select>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Advance Settings</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <h5 class="text-subtitle-1 text-uppercase">Secure Browsing</h5>
            <v-switch color="primary" v-model="secure" class="text-truncate">
              <template v-slot:label>
                <span class="text-truncate">Browsing Securely ( https ) when it's necessary</span>
              </template>
            </v-switch>

            <h5 class="text-subtitle-1 text-uppercase">Login Notifications</h5>
            <v-switch color="primary" v-model="login" class="text-truncate">
              <template v-slot:label>
                <span class="text-truncate">Notify when login attempted from other place</span>
              </template>
            </v-switch>

            <h5 class="text-subtitle-1 text-uppercase">Login Aprrovals</h5>
            <v-switch color="primary" v-model="approve" class="text-truncate">
              <template v-slot:label>
                <span class="text-truncate">Approvals is not required when login from unrecognized devices.</span>
              </template>
            </v-switch>

            <v-divider class="mb-5"></v-divider>

            <h5 class="text-subtitle-1 text-uppercase">Recognized Devices</h5>

            <v-list>
              <v-list-item v-for="(recognize, i) in recognizes" :key="i" color="primary" class="px-0">
                <template v-slot:prepend>
                  <component :is="recognize.icon" size="20" stroke-width="1.5" class="mr-2" />
                </template>

                <v-list-recognize-title class="text-subtitle-1">
                  {{ recognize.text }}
                  <span class="text-disabled font-weight-medium text-subtitle-2 d-none d-sm-inline-block"
                    >| {{ recognize.subtext }}</span
                  ></v-list-recognize-title
                >
                <template v-slot:append>
                  <span class="text-success" v-if="recognize.active">
                    <v-icon class="text-8 mr-1">mdi-circle</v-icon>
                    <span class="d-none d-sm-inline-block">Current Active</span>
                  </span>
                  <span class="font-weight-medium text-disabled" v-else>
                    <v-icon class="text-8 mr-1">mdi-circle</v-icon>
                    <span class="d-none d-sm-inline-block"> Active {{ recognize.time }}</span>
                  </span>
                  <v-btn icon variant="flat" color="inherit" class="ml-4">
                    <CircleXIcon size="20" stroke-width="1.5" />
                  </v-btn>
                </template>
              </v-list-item>
            </v-list>

            <v-divider class="mb-5"></v-divider>

            <h5 class="text-subtitle-1 text-uppercase">Active Sessions</h5>

            <v-list>
              <v-list-item v-for="(activeSession, i) in activeSessions" :key="i" color="primary" class="px-0">
                <template v-slot:prepend>
                  <component :is="activeSession.icon" size="20" stroke-width="1.5" class="mr-2 text-success" />
                </template>

                <v-list-item-title class="text-subtitle-1">
                  {{ activeSession.text }}
                  <span class="text-disabled font-weight-medium text-subtitle-2">| {{ activeSession.subtext }}</span></v-list-item-title
                >
                <template v-slot:append>
                  <v-btn variant="text" color="error" class="ml-4"> Logout </v-btn>
                </template>
              </v-list-item>
            </v-list>
            <v-divider class="mb-5"></v-divider>
            <div class="text-right">
              <v-btn color="primary">Update Profile</v-btn>
              <v-btn color="error" variant="text" class="ml-2">Clear</v-btn>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
