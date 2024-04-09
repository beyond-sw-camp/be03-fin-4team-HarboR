<script setup lang="ts">
import { ref, onMounted, shallowRef } from 'vue';
const dropdownMenu = shallowRef([
  { header: 'User Quick' },
  { header: 'Applications' },
  { header: 'Primitives' },
  {
    link: 'Social Profile',
    href: 'app/user/social/posts'
  },
  {
    link: 'Chat',
    href: 'app/chats'
  },
  {
    link: 'Colors',
    href: 'utils/colors'
  },
  {
    link: 'Account Profile',
    href: 'app/user/account-profile/profile'
  },
  {
    link: 'Kanban',
    href: 'app/kanban'
  },
  {
    link: 'Typography',
    href: 'utils/typography'
  },
  {
    link: 'User Cards',
    href: 'app/user/card/card1'
  },
  {
    link: 'Mail',
    href: 'app/mail'
  },
  {
    link: 'Shadows',
    href: 'utils/shadows'
  },
  {
    link: 'User List',
    href: 'app/user/list2'
  },
  {
    link: 'Calendar',
    href: 'app/calendar'
  },
  {
    link: 'Icons',
    href: 'icons/tabler'
  },
  {
    link: 'Contact',
    href: 'app/contact/c-card'
  },
  {
    link: 'E-Commerce',
    href: 'ecommerce/products'
  },
  {
    link: 'Elements',
    href: 'basic/expansion-panel'
  }
]);

const relativeURL = ref<string | null>(null);

onMounted(async () => {
  try {
    relativeURL.value = await import.meta.env.BASE_URL;
  } catch (error) {
    console.error('Error url not found:', error);
  }
});
</script>

<template>
  <!-- ---------------------------------------------- -->
  <!-- mega menu DD -->
  <!-- ---------------------------------------------- -->
  <div>
    <v-row>
      <v-col cols="12" md="4">
        <img src="@/assets/images/background/mega-bg.svg" />
      </v-col>
      <v-col cols="12" md="8">
        <v-list density="compact" class="overflow-hidden pt-6">
          <v-row no-gutters>
            <template v-for="(item, i) in dropdownMenu" :key="i">
              <v-col cols="6" sm="4">
                <v-list-item v-if="item.header" rounded="md">
                  <v-list-item-title class="text-subtitle-1">{{ item.header }}</v-list-item-title>
                </v-list-item>
                <v-list-item
                  v-if="item.link"
                  :href="`${relativeURL}${item.href}`"
                  rounded="md"
                  color="secondary"
                  target="_blank"
                  class="no-spacer"
                >
                  <template v-slot:prepend>
                    <v-icon class="text-8 mr-2">mdi-circle</v-icon>
                  </template>
                  <v-list-item-title class="text-subtitle-1 font-weight-regular">{{ item.link }} </v-list-item-title>
                </v-list-item>
              </v-col>
            </template>
          </v-row>
        </v-list>
      </v-col>
    </v-row>
  </div>
</template>
