<script setup lang="ts">
import Appbar from './Components/AppBarMenu.vue';
import HomeBanner from './Components/HomeBanner.vue';
import Features from './Components/FeatureSection.vue';
import Demos from './Components/DemoSection.vue';
import KeyFeatures from './Components/KeyFeatures.vue';
import Pepole from './Components/PepoleSection.vue';
import Footer from './Components/FooterSection.vue';
import 'aos/dist/aos.css';
import { onMounted } from 'vue';
import AOS from 'aos';
import CardSection from './Components/CardSection.vue';
import CustomizeSection from './Components/CustomizeSection.vue';
import FrameworkSection from './Components/FrameworkSection.vue';

onMounted(() => {
  AOS.init();
});
</script>

<template>
  <v-layout>
    <Appbar />
    <v-main class="ma-0">
      <HomeBanner />
      <CardSection />
      <CustomizeSection />
      <Features />
      <Demos />
      <Pepole />
      <KeyFeatures />
      <FrameworkSection />
      <Footer />
    </v-main>
  </v-layout>
</template>
