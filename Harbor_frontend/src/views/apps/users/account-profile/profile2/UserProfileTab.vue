<script setup lang="ts">
import { ref } from 'vue';

// icons
import { AlertCircleIcon } from 'vue-tabler-icons';

const textlname = ref('Schorl');
const textfname = ref('Delaney');
const textemail = ref('demo@company.com');
const textphone = ref('000-00-00000');
const textcompany = ref('company.ltd');
const textsite = ref('www.company.com');
</script>

<template>
  <div>
    <div class="d-flex flex-row">
      <img src="@/assets/images/users/avatar-1.png" width="80" alt="user-img" />
      <div class="ml-4 d-flex align-center text-subtitle-2 text-medium-emphasis font-weight-medium">
        <AlertCircleIcon width="20" stroke-width="1.5" class="mr-2" /> Image size Limit should be 125kb Max.
      </div>
    </div>
    <v-row class="mt-6">
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textlname" label="Last Name" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textfname" label="First Name" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="email" color="primary" v-model="textemail" label="Email Address" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textphone" label="Phone Number" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textcompany" label="Company Name" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="url" color="primary" v-model="textsite" label="Site Information" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
    </v-row>
  </div>
</template>
