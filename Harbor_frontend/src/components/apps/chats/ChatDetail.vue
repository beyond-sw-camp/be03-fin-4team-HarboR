<script setup lang="ts">
import { ref, computed, onMounted, shallowRef } from 'vue';
import { useChatStore } from '@/stores/apps/chat';
import type { ChatDetailType, ChatHistory } from '@/types/chats/ChatTypes';
import ChatSendMsg from './ChatSendMsg.vue';
import ChatInfo from './ChatInfo.vue';

const items = shallowRef([{ title: 'Name' }, { title: 'Date' }, { title: 'Rating' }, { title: 'Unread' }]);

const infodrawer = ref(false);

const store = useChatStore();
onMounted(() => {
  store.fetchChats();
});

const chatDetail = computed<ChatDetailType | null>(() => {
  const chat = store.chats[store.chatContent];
  if (chat) {
    // Ensure chatHistory follows the correct structure
    const chatHistory: ChatHistory[] = [];
    for (let i = 0; i < chat.chatHistory.length; i++) {
      const historyItem = chat.chatHistory[i];
      // Ensure each history item has the required id property
      const formattedHistoryItem: ChatHistory = {
        from: { from: historyItem.from.from, to: historyItem.from.to, id: 0 },
        to: { from: historyItem.to.from, to: historyItem.to.to, id: 1 }
      };

      chatHistory.push(formattedHistoryItem);
    }
    return { ...chat, chatHistory };
  } else {
    return null;
  }
});
</script>
<template>
  <div v-if="chatDetail" class="customHeight">
    <div class="d-sm-flex align-center ga-4 pa-4">
      <!---Toggle Button-->
      <v-btn icon @click="$emit('sToggle')" variant="text" class="d-none d-lg-flex">
        <Menu2Icon size="20" />
      </v-btn>

      <!---Topbar Row-->
      <div class="d-flex ga-2 align-center">
        <!---User Avatar-->
        <v-avatar>
          <img :src="chatDetail.thumb" alt="pro" width="50" />
        </v-avatar>

        <v-badge
          class="badg-dotDetail"
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
        <!---Name & Last seen-->
        <div>
          <h5 class="text-h5 mb-n1">{{ chatDetail.name }}</h5>
          <small class="text-medium-emphasis"> LastSeen:{{ chatDetail.lastMessage }} </small>
        </div>
      </div>
      <!---Topbar Icons-->
      <div class="ml-auto ga-2 d-flex">
        <v-btn icon variant="text">
          <PhoneIcon size="20" />
        </v-btn>
        <v-btn icon variant="text">
          <VideoPlusIcon size="20" />
        </v-btn>
        <v-btn icon variant="text" @click.stop="infodrawer = !infodrawer">
          <AlertCircleIcon size="20" />
        </v-btn>
        <v-menu>
          <template v-slot:activator="{ props }">
            <v-btn icon variant="text" v-bind="props"><DotsIcon size="20" /></v-btn>
          </template>

          <v-list>
            <v-list-item v-for="(item, index) in items" :key="index" :value="index">
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
      <!---Topbar Icons-->
    </div>
    <v-divider />
    <!---Chat History-->
    <perfect-scrollbar style="height: calc(100vh - 495px)">
      <div v-for="(chat, index) in chatDetail.chatHistory" :key="index" class="pa-5">
        <div v-for="(from, index) in chat" :key="index">
          <div v-for="ch in from.from" :key="ch" class="d-flex">
            <v-sheet class="bg-lightsecondary rounded-md pa-3 mb-1 text-right">
              <p class="text-body-1">{{ ch }}</p>
              <small class="text-medium-emphasis">{{ chatDetail.lastMessage }}</small>
            </v-sheet>
          </div>
          <div v-for="chTo in from.to" :key="chTo" class="justify-end d-flex text-end">
            <v-sheet class="bg-lightprimary rounded-md pa-3 mb-1">
              <p class="text-body-1">{{ chTo }}</p>
              <small class="text-medium-emphasis">
                {{ chatDetail.lastMessage }}
              </small>
            </v-sheet>
          </div>
        </div>
      </div>
    </perfect-scrollbar>
    <!---Chat send-->
    <ChatSendMsg />
    <!-- Info Sidebar -->
    <v-navigation-drawer v-model="infodrawer" temporary location="right" width="300">
      <ChatInfo />
    </v-navigation-drawer>
  </div>
</template>
<style lang="scss">
.customHeight {
  height: calc(100vh - 300px);
}
.badg-dotDetail {
  left: -9px;
  position: relative;
  bottom: -10px;
}
</style>
