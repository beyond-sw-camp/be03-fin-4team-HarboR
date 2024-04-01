<script setup lang="ts">
import { ref } from 'vue';

const textblock = ref('16657');
const textaname = ref(' Dardan Ranch');
const textstreet1 = ref('Nathaniel Ports');
const textstreet2 = ref('nr. Oran Walks');
const textpostcode = ref('395005');

const city = ref(['Los Angeles', 'Chicago', 'Phoenix', 'San Antonoi']);
const country = ref(['India', 'France', 'USA', 'UAE']);

const textcity = ref<string>('Los Angeles');
const textcountry = ref<string>('Los Angeles');

const checkbox = ref(true);
</script>

<template>
  <div>
    <v-row class="mt-1">
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textblock" label="Block No#" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textaname" label="Apartment Name" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="email" color="primary" v-model="textstreet1" label="Street Line 1" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textstreet2" label="Street Line 2" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="4">
        <v-text-field type="text" color="primary" v-model="textpostcode" label="Postcode" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="4">
        <v-select color="primary" :items="city" label="Select City" variant="outlined" v-model="textcity" hide-details> </v-select>
      </v-col>
      <v-col cols="12" lg="4">
        <v-select color="primary" :items="country" label="Select Country" variant="outlined" v-model="textcountry" hide-details> </v-select>
      </v-col>
      <v-col cols="12">
        <v-checkbox v-model="checkbox" color="primary" density="compact" label="Same as billing address"></v-checkbox>
      </v-col>
    </v-row>
  </div>
</template>
