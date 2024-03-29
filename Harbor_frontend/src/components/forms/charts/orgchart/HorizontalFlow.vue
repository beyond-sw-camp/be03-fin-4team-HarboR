<script setup>
//This is for Chart data
import { dataHorizontal } from './data.ts';
import { ref } from 'vue';
import { VueFlow } from '@vue-flow/core';
import { Background } from '@vue-flow/background';
import { Controls } from '@vue-flow/controls';
import { MiniMap } from '@vue-flow/minimap';

const ds = ref(dataHorizontal);
</script>

<template>
  <div>
    <VueFlow
      v-model="ds"
      class="basicflow"
      :default-edge-options="{ type: 'smoothstep' }"
      :default-zoom="1.2"
      :min-zoom="0.2"
      :max-zoom="4"
      :fit-view-on-init="true"
    >
      <Background pattern-color="#aaa" gap="8" />
      <MiniMap />
      <Controls />
    </VueFlow>
  </div>
</template>
<style>
:root {
  --vf-node-bg: transparent;
  --vf-node-text: inherit;
  --vf-connection-path: rgb(var(--v-theme-secondary));
  --vf-handle: rgba(85, 85, 85, 0.386);
}
</style>
