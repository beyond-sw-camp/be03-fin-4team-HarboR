<script setup lang="ts">
import { shallowRef } from 'vue';

const requests = shallowRef([
  {
    color: 'primary',
    name: 'You have 3 pending tasks.'
  },
  {
    color: 'error',
    name: 'New order received'
  },
  {
    color: 'success',
    name: 'You have 3 pending tasks.'
  },
  {
    color: 'primary',
    name: 'New order received'
  },
  {
    color: 'warning',
    name: 'Order cancelled'
  }
]);
</script>

<template>
  <v-list class="py-0">
    <v-list-item class="d-flex align-center no-spacer" v-for="(request, i) in requests" :key="i" :value="request">
      <template v-slot:prepend>
        <v-avatar size="15" :color="request.color" variant="flat" class="mr-3"> </v-avatar>
      </template>
      <div class="d-inline-flex align-center justify-space-between w-100">
        <h6 class="text-subtitle-1 font-weight-regular">{{ request.name }}</h6>
      </div>
    </v-list-item>
  </v-list>
</template>
