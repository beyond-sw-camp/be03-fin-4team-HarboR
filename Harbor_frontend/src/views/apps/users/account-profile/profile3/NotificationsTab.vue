<script setup lang="ts">
import { ref } from 'vue';

const sub1 = ref(true);
const sub2 = ref(false);
const sub3 = ref(false);
const sub4 = ref(false);

const checkbox1 = ref(false);
</script>

<template>
  <v-row>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Subscription Preference Center</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <h5 class="text-subtitle-1 mb-6">I would like to receive:</h5>

            <v-checkbox
              color="primary"
              hide-details
              density="compact"
              class="text-truncate"
              style="width: calc(100% - 15px)"
              v-model="sub1"
            >
              <template v-slot:label>
                <span class="text-truncate">Product Announcements and Updates</span>
              </template>
            </v-checkbox>
            <v-checkbox
              color="primary"
              hide-details
              density="compact"
              class="text-truncate"
              style="width: calc(100% - 15px)"
              v-model="sub2"
            >
              <template v-slot:label>
                <span class="text-truncate">Events and Meetups</span>
              </template>
            </v-checkbox>
            <v-checkbox
              color="primary"
              hide-details
              density="compact"
              class="text-truncate"
              style="width: calc(100% - 15px)"
              v-model="sub3"
            >
              <template v-slot:label>
                <span class="text-truncate">User Research Surveys</span>
              </template>
            </v-checkbox>
            <v-checkbox
              color="primary"
              hide-details
              density="compact"
              class="text-truncate"
              style="width: calc(100% - 15px)"
              v-model="sub4"
            >
              <template v-slot:label>
                <span class="text-truncate">Hatch Startup Program</span>
              </template>
            </v-checkbox>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-6">
            <h5 class="text-subtitle-1">Opt me out instead</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-checkbox v-model="checkbox1" hide-details density="compact" class="text-truncate" style="width: calc(100% - 15px)">
              <template v-slot:label>
                <span class="text-truncate">Unsubscribe me from all of the above</span>
              </template>
            </v-checkbox>

            <v-btn color="primary" class="mt-4">Update My Prefereces</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
