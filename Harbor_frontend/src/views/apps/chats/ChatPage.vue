<script setup lang="ts">
import { ref } from 'vue';
import { useDisplay } from 'vuetify';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import ChatListing from '@/components/apps/chats/ChatListing.vue';
import ChatDetail from '@/components/apps/chats/ChatDetail.vue';
import ChatProfile from '@/components/apps/chats/ChatProfile.vue';

const page = ref({ title: 'Chats Page' });
const { lgAndUp } = useDisplay();
const breadcrumbs = ref([
  {
    title: 'Applications',
    disabled: false,
    href: '#'
  },
  {
    title: 'Chat',
    disabled: true,
    href: '#'
  }
]);
const toggleSide = ref(false);
const sDrawer = ref(false);
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>

  <v-row>
    <v-col v-if="!toggleSide && lgAndUp" class="d-flex align-stretch chatSidebar">
      <v-card variant="flat">
        <perfect-scrollbar style="height: calc(100vh - 300px)">
          <v-card-text class="pa-5">
            <ChatProfile />
            <ChatListing />
          </v-card-text>
        </perfect-scrollbar>
      </v-card>
    </v-col>
    <v-col class="d-flex align-stretch">
      <v-card variant="flat">
        <!---Toggle Button For mobile-->
        <v-btn icon @click="sDrawer = !sDrawer" variant="text" class="d-lg-none d-md-flex d-sm-flex ml-4 mt-4">
          <Menu2Icon size="20" />
        </v-btn>
        <ChatDetail @s-Toggle="toggleSide = !toggleSide" />
      </v-card>
    </v-col>
  </v-row>

  <v-navigation-drawer temporary v-model="sDrawer" width="300" top v-if="!lgAndUp">
    <perfect-scrollbar style="height: calc(100vh - 100px)">
      <v-card-text class="pa-5">
        <ChatProfile />
        <ChatListing />
      </v-card-text>
    </perfect-scrollbar>
  </v-navigation-drawer>
</template>
<style lang="scss">
.custom-main {
  margin: 0;
}
.chatSidebar {
  max-width: 350px;
}
</style>
