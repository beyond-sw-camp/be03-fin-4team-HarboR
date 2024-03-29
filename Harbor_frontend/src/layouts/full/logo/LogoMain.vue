<script setup>
import { computed } from 'vue';
import LogoLight from './LogoLight.vue';
import LogoDark from './LogoDark.vue';
import { useCustomizerStore } from '@/stores/customizer';

const customizer = useCustomizerStore();

const dark = computed(() => {
  if (
    customizer.actTheme === 'DarkPurpleTheme' ||
    customizer.actTheme === 'DarkGreenTheme' ||
    customizer.actTheme === 'DarkSpeechBlueTheme' ||
    customizer.actTheme === 'DarkOliveGreenTheme' ||
    customizer.actTheme === 'DarkPinkTheme' ||
    customizer.actTheme === 'DarkYellowTheme' ||
    customizer.actTheme === 'DarkSeaGreenTheme'
  ) {
    return true;
  } else {
    return false;
  }
});
</script>
<template>
  <LogoLight v-if="dark" />
  <LogoDark v-else />
</template>
