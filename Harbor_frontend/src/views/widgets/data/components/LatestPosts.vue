<script setup lang="ts">
import { shallowRef } from 'vue';

import Post1 from '@/assets/images/background/post1.jpg';
import Post2 from '@/assets/images/background/post2.jpg';
import Post3 from '@/assets/images/background/post3.jpg';

const posts = shallowRef([
  {
    avatar: Post1,
    name: 'Up unpacked friendly',
    time: '14 minutes ago'
  },
  {
    avatar: Post2,
    name: 'David Jones',
    time: '14 minutes ago'
  },
  {
    avatar: Post3,
    name: 'David Jones',
    time: '14 minutes ago'
  }
]);
</script>

<template>
  <v-list class="py-0" lines="two">
    <v-list-item v-for="(post, i) in posts" :key="i" :value="post">
      <template v-slot:prepend>
        <div class="mr-5 py-2">
          <img :src="post.avatar" width="90" height="75" class="rounded-sm" :alt="post.avatar" />
        </div>
      </template>
      <h6 class="text-subtitle-1 mb-1">{{ post.name }}</h6>
      <p class="text-subtitle-2 text-medium-emphasis">Video | {{ post.time }}</p>
    </v-list-item>
  </v-list>
</template>
