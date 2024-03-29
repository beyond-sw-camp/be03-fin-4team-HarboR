<script setup lang="ts">
import Appbar from './Components/AppBarMenu.vue';
import BannerInnerpage from './Components/BannerInnerpage.vue';
</script>

<template>
  <v-layout>
    <Appbar />
    <BannerInnerpage
      title="Talk to our account expert"
      subtitle="The starting point for your next project based on easy-to-customize Material-UI © helps you build apps faster and better.
"
    >
      <div>
        <v-card elevation="5" class="pa-6 v-col-lg-10 offset-lg-1">
          <v-row>
            <v-col cols="12" lg="6" md="6">
              <v-text-field variant="outlined" color="primary" label="Name" hide-details></v-text-field>
            </v-col>
            <v-col cols="12" lg="6" md="6">
              <v-text-field variant="outlined" color="primary" label="Company Name" hide-details> </v-text-field>
            </v-col>
            <v-col cols="12" lg="6" md="6">
              <v-text-field variant="outlined" color="primary" label="Email Id" hide-details> </v-text-field>
            </v-col>
            <v-col cols="12" lg="6" md="6">
              <v-text-field variant="outlined" color="primary" label="Phone number" type="number" hide-details></v-text-field>
            </v-col>
            <v-col cols="12" lg="6" md="6">
              <v-select label="Company Size" hide-details variant="outlined" color="primary" :items="['1-5', '5-10', '10+']"></v-select>
            </v-col>
            <v-col cols="12" lg="6" md="6">
              <v-select
                label="Project Budget"
                variant="outlined"
                hide-details
                color="primary"
                :items="['Below $1000', '$1000 - $5000', 'Not Spacified']"
              ></v-select>
            </v-col>
            <v-col cols="12" lg="12" md="12">
              <v-textarea label="Message" hide-details variant="outlined" color="primary"></v-textarea>
            </v-col>
            <v-col cols="12" lg="8" md="8">
              By submitting this, you agree to the <router-link to="/pages/contact-us">Privacy Policy </router-link> and
              <router-link to="/pages/contact-us">Cookie Policy</router-link>
            </v-col>
            <v-col cols="12" lg="4" md="4" class="text-right">
              <v-btn variant="flat" color="secondary">Get Started</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </div>
    </BannerInnerpage>
  </v-layout>
</template>
