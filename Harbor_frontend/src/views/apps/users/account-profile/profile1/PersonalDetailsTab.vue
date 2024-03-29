<script setup lang="ts">
import { ref } from 'vue';

// icons
import { BrandFacebookIcon, BrandTwitterIcon, BrandLinkedinIcon } from 'vue-tabler-icons';

const location = ref(['Washington', 'India', 'Africa', 'New York', 'Malaysia']);
const experience = ref(['Starterup', '2 years', '3 years', '4 years', '5 years']);

const textFname = ref('John Deo');
const textExperience = ref<string>('Starterup');
const textLocation = ref<string>('Washington');
const textBio = ref(
  'I consider myself as a creative, professional and a flexible person. I can adapt with any kind of brief and design style'
);
const textContact = ref('(+99) 9999 999 999');
const textEmail = ref('demo@sample.com');
const textURL = ref('https://demo.com');
const textAddress = ref('3379  Monroe Avenue, Fort Myers, Florida(33912)');
</script>

<template>
  <v-row>
    <v-col cols="12" lg="6">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Personal Information</h5>
          </div>

          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="Name" variant="outlined" type="text" hide-details v-model="textFname"> </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-select color="primary" :items="location" label="Location" variant="outlined" v-model="textLocation" hide-details>
                </v-select>
              </v-col>
              <v-col cols="12">
                <v-textarea color="primary" label="Bio" variant="outlined" v-model="textBio" hide-details rows="3"> </v-textarea>
              </v-col>
              <v-col cols="12">
                <v-select color="primary" :items="experience" label="Experience" variant="outlined" v-model="textExperience" hide-details>
                </v-select>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="6">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Contact Information</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="Contact Phone" variant="outlined" type="text" hide-details v-model="textContact">
                </v-text-field>
              </v-col>
              <v-col cols="12" lg="6">
                <v-text-field color="primary" label="Email" variant="outlined" type="email" hide-details v-model="textEmail">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field color="primary" label="Portfolio URL" variant="outlined" type="text" hide-details v-model="textURL">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea color="primary" label="Address" variant="outlined" rows="3" type="text" hide-details v-model="textAddress">
                </v-textarea>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="6">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-5">
            <h5 class="text-subtitle-1 mt-1">Social Information</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row align="center">
              <v-col cols="2" sm="1">
                <v-avatar color="primary" rounded="md" variant="flat" size="35">
                  <BrandFacebookIcon width="20" stroke-width="1.5" />
                </v-avatar>
              </v-col>
              <v-col cols="10" sm="8">
                <v-text-field color="primary" label="Facebook Profile URL" variant="outlined" type="text" hide-details> </v-text-field>
              </v-col>
              <v-col cols="12" sm="3" class="text-left text-sm-right">
                <v-btn color="secondary">Connect</v-btn>
              </v-col>
            </v-row>
            <v-row align="center">
              <v-col cols="2" sm="1">
                <v-avatar color="primary" rounded="md" variant="flat" size="35">
                  <BrandTwitterIcon width="20" stroke-width="1.5" />
                </v-avatar>
              </v-col>
              <v-col cols="10" sm="8">
                <v-text-field color="primary" label="Twitter Profile URL" variant="outlined" type="text" hide-details> </v-text-field>
              </v-col>
              <v-col cols="12" sm="3" class="text-left text-sm-right">
                <v-btn color="secondary">Connect</v-btn>
              </v-col>
            </v-row>
            <v-row align="center">
              <v-col cols="2" sm="1">
                <v-avatar color="primary" rounded="md" variant="flat" size="35">
                  <BrandLinkedinIcon width="20" stroke-width="1.5" />
                </v-avatar>
              </v-col>
              <v-col cols="10" sm="8">
                <v-text-field color="primary" label="Linkedin Profile URL" variant="outlined" type="text" hide-details> </v-text-field>
              </v-col>
              <v-col cols="12" sm="3" class="text-left text-sm-right">
                <v-btn color="secondary">Connect</v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
