<script setup lang="ts">
import { shallowRef } from 'vue';

const footerLink = shallowRef([
  {
    title: 'Home',
    url: 'https://www.songboseok.shop/'
  },
  {
    title: 'Repo',
    url: 'https://github.com/beyond-sw-camp/be03-fin-4team-HarboR'
  },
]);

const teamMembers = [
  { name: '송보석', character: '👩‍💻', url : 'https://github.com/fa7271' },
  { name: '이창선', character: '👨‍💼', url : 'https://github.com/keepself'},
  { name: '손정민', character: '🧑‍🔬', url : 'https://github.com/SonJM'},
  { name: '이원태', character: '👩‍🎨', url : 'https://github.com/wontae0924'}
];
</script>
<template>
  <v-footer class="px-0 footer mt-2">
    <v-row justify="center" align="center" no-gutters>
      <v-col cols="12" sm="6">
        <p class="text-body-1 mb-0 text-sm-left text-center">
          crafted by Team
          <a href="https://github.com/beyond-sw-camp/be03-fin-4team-HarboR" class="text-darkText text-decoration-none" target="_blank">Harbor</a>
        </p>
      </v-col>
      <v-col class="text-center" cols="12" sm="6">
        <span v-for="(member, index) in teamMembers" :key="index" class="mx-1">
          <a :href="member.url" class="text-darkText text-decoration-none" target="_blank">{{ member.character }} {{ member.name }}</a>
        </span>
        <template v-for="(item, i) in footerLink" :key="i">
          <a
            class="mx-2 text-body-1 text-darkText text-decoration-none"
            target="_blank"
            :href="item.url"
          >
            {{ item.title }}
          </a>
        </template>
      </v-col>
    </v-row>
  </v-footer>
</template>

<style lang="scss">
.v-footer {
  position: unset;
}
footer {
  a {
    &:hover {
      color: rgb(var(--v-theme-primary)) !important;
    }
  }
}
</style>
