<script setup lang="ts">
import { ref, watch, onMounted, computed } from 'vue';
import { useCustomizerStore } from '../../../stores/customizer';
import { useTokenStore } from '@/stores/apps/token';
import axios, { setClientHeaders } from '@/utils/axios';
import { jwtDecode } from 'jwt-decode';
// Icon Imports
import { AccessPointIcon, BellIcon, SettingsIcon, LanguageIcon, SearchIcon, Menu2Icon, RotateClockwiseIcon } from 'vue-tabler-icons';

// dropdown imports
import LanguageDD from './LanguageDD.vue';
import NotificationDD from './NotificationDD.vue';
import ProfileDD from './ProfileDD.vue';
import MegaMenuDD from './MegaMenuDD.vue';
import Searchbar from './SearchBarPanel.vue';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const expStore = useTokenStore();

onMounted(() => {
  expStore.startTimer();
});

const minute = computed(() => expStore.minute);
const second = computed(() => expStore.second);
const customizer = useCustomizerStore();
const showSearch = ref(false);
const priority = ref(customizer.setHorizontalLayout ? 0 : 0);
function searchbox() {
  showSearch.value = !showSearch.value;
}
watch(priority, (newPriority) => {
  // yes, console.log() is a side effect
  priority.value = newPriority;
});

const reissueToken = async () => {
  const token = localStorage.getItem('token');
  const employeeId = localStorage.getItem('employeeId');
  setClientHeaders(token);
  const response = await axios.get(`${baseUrl}/login/account/reissue/${employeeId}`);
  console.log(response);
  const newToken = response.data.result.token;
  if (newToken) {
    const decoded: string = jwtDecode(newToken);
    localStorage.setItem('token', newToken);
    localStorage.setItem('employeeId', decoded.sub);
    localStorage.setItem('myEmail', decoded.myEmail);
    localStorage.setItem('role', decoded.role);
    expStore.startTimer();
  }
};
</script>

<template>
  <v-app-bar elevation="0" :priority="priority" height="80">
    <v-btn
      class="hidden-md-and-down text-secondary"
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      @click.stop="customizer.SET_MINI_SIDEBAR(!customizer.mini_sidebar)"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>
    <v-btn
      class="hidden-lg-and-up text-secondary ms-3"
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      @click.stop="customizer.SET_SIDEBAR_DRAWER"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>

    <!-- search mobile -->
    <v-btn
      class="hidden-lg-and-up text-secondary ml-3"
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      size="small"
      @click="searchbox"
    >
      <SearchIcon size="17" stroke-width="1.5" />
    </v-btn>

    <v-sheet v-if="showSearch" class="search-sheet v-col-12">
      <Searchbar :closesearch="searchbox" />
    </v-sheet>

    <!-- ---------------------------------------------- -->
    <!-- Search part -->
    <!-- ---------------------------------------------- -->
    <v-sheet class="mx-3 v-col-3 v-col-xl-2 v-col-lg-4 d-none d-lg-block">
      <Searchbar />
    </v-sheet>

    <!---/Search part -->

    <v-spacer />
    <!-- ---------------------------------------------- -->
    <!---right part -->
    <!-- ---------------------------------------------- -->

    <!-- ---------------------------------------------- -->
    <!-- Token Reissuance -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <p class="text-h4">{{ minute }} : {{ second }}</p>
        <v-btn icon class="text-primary mx-2" color="lightprimary" rounded="sm" size="small" variant="flat" @click="reissueToken">
          <RotateClockwiseIcon stroke-width="1.5" size="22" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <NotificationDD />
      </v-sheet>
    </v-menu>
    <!-- ---------------------------------------------- -->
    <!-- Messages -->
    <!-- ---------------------------------------------- -->
    <!-- <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <v-btn
          icon
          class="text-secondary hidden-sm-and-down"
          color="lightsecondary"
          rounded="sm"
          size="small"
          variant="flat"
          v-bind="props"
        >
          <AccessPointIcon stroke-width="1.5" size="22" />
        </v-btn>
      </template>
      <v-sheet width="900" height="395" elevation="12" rounded="md" class="pa-4">
        <MegaMenuDD />
      </v-sheet>
    </v-menu> -->
    <!-- ---------------------------------------------- -->
    <!-- translate -->
    <!-- ----------------------------------------------
    <v-menu :close-on-content-click="false" location="bottom">
      <template v-slot:activator="{ props }">
        <v-btn icon class="text-primary ml-3" color="lightprimary" rounded="sm" size="small" variant="flat" v-bind="props">
          <LanguageIcon stroke-width="1.5" size="22" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="200" elevation="12">
        <LanguageDD />
      </v-sheet>
    </v-menu> -->

    <!-- ---------------------------------------------- -->
    <!-- Notification -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <v-btn icon class="text-secondary mx-2" color="lightsecondary" rounded="sm" size="small" variant="flat" v-bind="props">
          <BellIcon stroke-width="1.5" size="22" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <NotificationDD />
      </v-sheet>
    </v-menu>

    <!-- ---------------------------------------------- -->
    <!-- User Profile -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <v-btn class="profileBtn text-primary" color="lightprimary" variant="flat" rounded="pill" v-bind="props">
          <v-avatar size="30" class="mr-2 py-2">
            <img src="@/assets/images/profile/user-round.svg" alt="Julia" />
          </v-avatar>
          <SettingsIcon stroke-width="1.5" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <ProfileDD />
      </v-sheet>
    </v-menu>
  </v-app-bar>
</template>
