<script setup lang="ts">
import { shallowRef } from 'vue';

import Avatar1 from '@/assets/images/users/avatar-1.png';
import Avatar2 from '@/assets/images/users/avatar-2.png';
import Avatar3 from '@/assets/images/users/avatar-3.png';
import Avatar4 from '@/assets/images/users/avatar-4.png';
import Avatar5 from '@/assets/images/users/avatar-5.png';

const teams = shallowRef([
  {
    avatar: Avatar1,
    name: 'David Jones',
    time: '5 mins ago',
    role: 'Developer'
  },
  {
    avatar: Avatar2,
    name: 'David Jones',
    time: 'Today',
    role: 'Developer'
  },
  {
    avatar: Avatar3,
    name: 'David Jones',
    time: 'Yesterday',
    role: 'Developer'
  },
  {
    avatar: Avatar4,
    name: 'David Jones',
    time: '02-05-2021',
    role: 'Developer'
  },
  {
    avatar: Avatar5,
    name: 'David Jones',
    time: '5 mins ago',
    role: 'Developer'
  }
]);
</script>

<template>
  <v-list class="py-0" lines="two">
    <v-list-item v-for="(team, i) in teams" :key="i" :value="team" rounded="sm" color="secondary" class="no-spacer">
      <template v-slot:prepend>
        <v-avatar size="40" class="mr-3 py-2">
          <img :src="team.avatar" width="40" alt="Julia" />
        </v-avatar>
      </template>
      <div class="d-inline-flex align-center justify-space-between w-100">
        <h6 class="text-subtitle-1">{{ team.name }}</h6>
        <span class="text-subtitle-2 text-medium-emphasis">{{ team.time }}</span>
      </div>

      <p class="text-subtitle-2 text-medium-emphasis">{{ team.role }}</p>
    </v-list-item>
  </v-list>
</template>
