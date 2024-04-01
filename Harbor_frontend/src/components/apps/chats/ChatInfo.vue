<script setup lang="ts">
import { shallowRef, computed, onMounted } from 'vue';
import { useChatStore } from '@/stores/apps/chat';

import Avatar1 from '@/assets/images/background/background1.png';
import Avatar2 from '@/assets/images/background/background2.png';
import Avatar3 from '@/assets/images/background/background3.png';

const attach = shallowRef([
  {
    avatar: Avatar1,
    name: 'File Name.jpg',
    time: '4/16/2021 07:47:03'
  },
  {
    avatar: Avatar2,
    name: 'File Name.ai',
    time: '4/16/2021 07:47:03'
  },
  {
    avatar: Avatar3,
    name: 'File Name.pdf',
    time: '4/16/2021 07:47:03'
  }
]);

const store = useChatStore();
onMounted(() => {
  store.fetchChats();
});

interface ChatItem {
  // Define the properties of a chat item here
  thumb: string;
  status: string;
  name: string;
  excerpt: string;
  // ... other properties as needed
}

const chatDetail = computed<ChatItem | undefined>(() => {
  return store.chats[store.chatContent];
});
</script>
<template>
  <div v-if="chatDetail" class="customHeight pa-4">
    <div class="pa-4">
      <div class="text-center">
        <img :src="chatDetail.thumb" alt="pro" width="110" class="pa-2 border border-secondary rounded-circle" />
        <div class="mb-2">
          <v-badge
            dot
            :color="
              chatDetail.status === 'away'
                ? 'warning'
                : chatDetail.status === 'busy'
                  ? 'error'
                  : chatDetail.status === 'online'
                    ? 'success'
                    : 'containerBg'
            "
          >
          </v-badge>
        </div>
        <h6 class="text-medium-emphasis font-weight-light">{{ chatDetail.status }}</h6>

        <h4 class="font-weight-medium text-subtitle-1 my-3">{{ chatDetail.name }}</h4>
        <h4 class="font-weight-light text-subtitle-1">{{ chatDetail.excerpt }}</h4>
      </div>
    </div>
    <v-list density="compact" nav>
      <v-list-subheader class="text-h5">Information</v-list-subheader>
      <v-list-item value="home">
        <template v-slot:prepend>
          <MapPinIcon size="20" class="mr-2 text-medium-emphasis" />
        </template>
        <span class="text-h5 font-weight-regular">32188 Sips Parkways, U.S</span>
      </v-list-item>
      <v-list-item value="about">
        <template v-slot:prepend>
          <PhoneIcon size="20" class="mr-2 text-medium-emphasis" />
        </template>
        <span class="text-h5 font-weight-regular">995-250-1803</span>
      </v-list-item>
      <v-list-item value="about">
        <template v-slot:prepend>
          <MailIcon size="20" class="mr-2 text-medium-emphasis" />
        </template>
        <span class="text-h5 font-weight-regular">O’Keefe@codedtheme.com</span>
      </v-list-item>
    </v-list>
    <v-divider></v-divider>
    <v-list density="compact" lines="two">
      <v-list-subheader class="text-h5">Attachment</v-list-subheader>
      <v-list-item v-for="(file, i) in attach" :value="file" :key="i" rounded="sm" color="secondary">
        <template v-slot:prepend>
          <div class="mr-3 py-2">
            <img :src="file.avatar" width="42" height="42" alt="Julia" class="rounded-sm" />
          </div>
        </template>
        <div class="w-100">
          <h6 class="text-subtitle-1">{{ file.name }}</h6>
          <span class="text-subtitle-2 text-medium-emphasis">{{ file.time }}</span>
        </div>
      </v-list-item>
    </v-list>
  </div>
</template>
