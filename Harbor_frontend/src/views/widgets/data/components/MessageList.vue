<script setup lang="ts">
import { shallowRef } from 'vue';

// icons
import { BrandTwitterIcon, BriefcaseIcon, ChecksIcon, UserCircleIcon } from 'vue-tabler-icons';

const messages = shallowRef([
  {
    color: 'primary',
    icon: BrandTwitterIcon,
    time: '2 hrs ago',
    title: '+ 1652 Followers',
    subtext: 'You’re getting more and more followers, keep it up!'
  },
  {
    color: 'error',
    icon: BriefcaseIcon,
    time: '4 hrs ago',
    title: '+ 5 New Products were added!',
    subtext: 'Congratulations!'
  },
  {
    color: 'success',
    icon: ChecksIcon,
    time: '1 day ago',
    title: 'Database backup completed!',
    subtext: 'Download the latest backup.'
  },
  {
    color: 'secondary',
    icon: UserCircleIcon,
    time: '2 day ago',
    title: '+2 Friend Requests',
    subtext: 'This is great, keep it up!'
  }
]);
</script>

<template>
  <v-timeline truncate-line="both" side="end" line-inset="4" class="my-3">
    <v-timeline-item size="large" fill-dot :dot-color="message.color" v-for="(message, i) in messages" :key="i" :value="message">
      <template v-slot:icon>
        <component :is="message.icon" stroke-width="2" size="20" />
      </template>
      <template v-slot:opposite>
        <span class="text-subtitle-2 text-medium-emphasis">{{ message.time }}</span>
      </template>
      <v-card elevation="0">
        <h5 class="text-h5">
          {{ message.title }}
        </h5>
        <span class="text-subtitle-2 text-medium-emphasis">{{ message.subtext }}</span>
      </v-card>
    </v-timeline-item>
  </v-timeline>
</template>
