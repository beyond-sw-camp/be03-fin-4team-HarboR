<script setup lang="ts">
import { ref, shallowRef, onMounted, computed } from 'vue';
import { useMailStore } from '@/stores/apps/mail';
// third-party
import { format } from 'date-fns';
import MailDetail from './MailDetail.vue';

const store = useMailStore();

onMounted(() => {
  store.fetchMails();
  store.filterMails(store.activeFilter);
});

type MailItem = {
  id: number;
  isChecked: boolean;
  isRead: boolean;
  starred: boolean;
  important: boolean;
  profile: {
    avatar: string;
    name: string;
  };
  subject: string;
  promotions: string;
  forums: string;
  attachments: string;
  time: number;
  // Add other properties as needed
};
const getMails = computed<MailItem[]>(() => {
  return store.mails;
});

const searchValue = ref('');
const emailDetails = ref(false);
const selectedMail = ref<MailItem | null>(null);

const handleFilter = async (string: string) => {
  store.SelectFilter(string);
  await store.filterMails(string);
};
const countSelectedMail = computed(() => {
  return getMails.value.filter((i) => i.isChecked).length;
});

const handleUserChange = async (data: MailItem | null) => {
  if (data) {
    await store.setRead(data.id.toString()); // Ensure id is converted to string if necessary
    handleFilter(store.activeFilter);
    emailDetails.value = true;
    selectedMail.value = data;
  }
};
const filteredMails = computed(() => {
  return getMails.value.filter((mail) => {
    return mail.profile.name.toLowerCase().includes(searchValue.value.toLowerCase());
  });
});

defineEmits(['sToggle']);
</script>
<template>
  <!-- 검색창 -->
  <div v-if="!emailDetails">
    <!---Topbar Row-->
    <div class="pa-3 d-fl ex align-center flex-wrap justify-space-between">
      <div>
        <v-text-field
          variant="outlined"
          v-model="searchValue"
          prepend-inner-icon="mdi-magnify"
          persistent-placeholder
          placeholder="Search Mail"
          hide-details
          density="compact"
          class="max-width-300"
        ></v-text-field>
      </div>
    </div>
    <!---End Topbar Row-->
    <v-divider />
    <!---If Mail selected-->
    <div v-if="countSelectedMail > 0" class="px-4 pt-3">
      <h3 class="text-h4 text-secondary">{{ countSelectedMail }} Mail Selected</h3>
    </div>
    <!---Mail Listing Table-->
    <v-table class="mt-3 mailTable">
      <tbody>
        <tr
          v-for="mail in filteredMails"
          :key="mail.id"
          :class="`cursor-pointer ${mail.isChecked ? 'bg-lightprimary' : ''} ${
            mail.isRead ? 'text-medium-emphasis' : 'font-weight-medium'
          }`"
        >
          
      
          <td class="text-no-wrap" @click="handleUserChange(mail)">{{ mail.profile.name }}</td>
          <td @click="handleUserChange(mail)">
            <div class="d-flex align-center ga-2">
              <div class="text-no-wrap text-truncate">{{ mail.subject }}</div>
            </div>
          </td>
          <td class="text-no-wrap">
            {{ format(new Date(mail.time), 'd MMM yy HH:mm a') }}
          </td>
        </tr>
      </tbody>
    </v-table>
  </div>
  <!---If Email Detail-->
  <div v-if="emailDetails && selectedMail" class="pa-5">
    <MailDetail @toggleDetail="emailDetails = false" :selectedMail="selectedMail" />
  </div>
</template>
<style lang="scss">
.checkboxWidth {
  width: 125px;
}
.max-width-300 {
  width: 300px;
  @media (max-width: 420px) {
    width: 250px;
  }
}
</style>
