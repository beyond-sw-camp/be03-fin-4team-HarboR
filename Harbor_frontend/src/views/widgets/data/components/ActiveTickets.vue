<script setup lang="ts">
import { shallowRef } from 'vue';

import Avatar1 from '@/assets/images/users/avatar-1.png';
import Avatar2 from '@/assets/images/users/avatar-2.png';
import Avatar3 from '@/assets/images/users/avatar-3.png';
import Avatar4 from '@/assets/images/users/avatar-4.png';

const products = shallowRef([
  {
    avatar: Avatar1,
    name: 'John Deo',
    time: 12,
    title: '[#1183] Workaround for OS X selects printing bug',
    subtext: 'Chrome fixed the bug several versions ago, thus rendering this...'
  },
  {
    avatar: Avatar2,
    name: 'Jems Win',
    time: 16,
    title: '[#1249] Vertically center carousel controls',
    subtext: 'Chrome fixed the bug several versions ago, thus rendering this...'
  },
  {
    avatar: Avatar3,
    name: 'Jeny Wiliiam',
    time: 40,
    title: '[#1254] Inaccurate small pagination height',
    subtext: 'Chrome fixed the bug several versions ago, thus rendering this...'
  },
  {
    avatar: Avatar4,
    name: 'Jems Win',
    time: 12,
    title: '[#1249] Vertically center carousel controls',
    subtext: 'Chrome fixed the bug several versions ago, thus rendering this...'
  }
]);
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th class="text-left text-subtitle-1">Due</th>
        <th class="text-left text-subtitle-1">Name</th>
        <th class="text-left text-subtitle-1">Position</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in products" :key="item.time">
        <td class="py-5">
          <h5 class="text-subtitle-1">{{ item.time }}</h5>
          <span class="text-medium-emphasis text-subtitle-2">hours</span>
        </td>
        <td class="py-5">
          <div class="d-flex align-center">
            <img :src="item.avatar" width="40" alt="Julia" class="rounded-circle" />
            <h5 class="text-subtitle-1 ml-4">{{ item.name }}</h5>
          </div>
        </td>
        <td class="py-5">
          <h5 class="text-subtitle-1">{{ item.title }}</h5>
          <span class="text-medium-emphasis text-subtitle-2">{{ item.subtext }}</span>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
