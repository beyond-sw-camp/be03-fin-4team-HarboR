<script setup lang="ts">
import { shallowRef } from 'vue';

import Avatar1 from '@/assets/images/users/avatar-1.png';
import Avatar2 from '@/assets/images/users/avatar-2.png';
import Avatar3 from '@/assets/images/users/avatar-3.png';
import Avatar4 from '@/assets/images/users/avatar-4.png';

const projects = shallowRef([
  {
    avatar: Avatar1,
    username: 'John Deo',
    role: 'Graphics Designer',
    name: 'Materially',
    date: 'Jun, 26',
    priority: 'low'
  },
  {
    avatar: Avatar2,
    username: 'Jenifer Vintage',
    role: 'Web Designer',
    name: 'Mashable',
    date: 'March, 31',
    priority: 'high'
  },
  {
    avatar: Avatar3,
    username: 'William Jem',
    role: 'Developer',
    name: 'Flatable',
    date: 'Aug, 02',
    priority: 'medium'
  },
  {
    avatar: Avatar4,
    username: 'David Jones',
    role: 'Developer',
    name: 'Guruable',
    date: 'Sep, 22',
    priority: 'high'
  }
]);
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th class="text-left text-subtitle-1">Assigned</th>
        <th class="text-left text-subtitle-1">Name</th>
        <th class="text-left text-subtitle-1">Due Date</th>
        <th class="text-left text-subtitle-1">Priority</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in projects" :key="item.name">
        <td class="py-4">
          <div class="d-flex align-center">
            <img :src="item.avatar" width="45" alt="Julia" class="rounded-circle" />
            <div class="ml-4">
              <h5 class="text-h5">{{ item.username }}</h5>
              <span class="text-medium-emphasis text-subtitle-2">{{ item.role }}</span>
            </div>
          </div>
        </td>
        <td class="py-4">{{ item.name }}</td>
        <td class="py-4">{{ item.date }}</td>
        <td class="py-4">
          <v-chip class="bg-lightwarning text-warning" size="small" v-if="item.priority === 'low'">Low</v-chip>
          <v-chip class="bg-lighterror text-error" size="small" v-else-if="item.priority === 'high'">High</v-chip>
          <v-chip class="bg-lightprimary text-primary" size="small" v-else>Medium</v-chip>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
