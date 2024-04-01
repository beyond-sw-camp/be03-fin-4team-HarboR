<script setup lang="ts">
import { ref } from 'vue';
import Appbar from './Components/AppBarMenu.vue';
import BannerInnerpage from './Components/BannerInnerpage.vue';
const panel = ref(['foo']);
</script>

<template>
  <v-layout>
    <Appbar />
    <BannerInnerpage title="FAQs" subtitle="Please refer the Frequently ask question for your quick help">
      <div>
        <v-card elevation="5" class="pa-6 v-col-lg-10 offset-lg-1">
          <v-expansion-panels v-model="panel" multiple>
            <v-expansion-panel
              elevation="0"
              title="When do I need Extended License?"
              text="If your End Product which is sold - Then only your required Extended License. i.e. If you take subscription charges (monthly, yearly, etc...) from your end users in this case you required Extended License."
              value="foo"
            ></v-expansion-panel>
            <v-expansion-panel
              elevation="0"
              title="What Support Includes?"
              text="6 Months of Support Includes with 1 year of free updates. We are happy to solve your bugs, issue."
              value="support"
            ></v-expansion-panel>
            <v-expansion-panel
              elevation="0"
              title="Is Berry Support TypeScript?"
              text="Yes, Berry Support the TypeScript and it is only available in Plus and Extended License."
              value="typescript"
            ></v-expansion-panel>
            <v-expansion-panel
              elevation="0"
              title="Is there any RoadMap for Berry?"
              text="Berry is our flagship React Dashboard Template and we always add the new features for the long run. You can check the Roadmap in Documentation."
              value="raodmap"
            ></v-expansion-panel>
          </v-expansion-panels>
        </v-card>
      </div>
    </BannerInnerpage>
  </v-layout>
</template>
