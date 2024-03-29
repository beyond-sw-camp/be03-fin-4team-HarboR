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
const compact = ref(false);
const sorting = shallowRef([{ title: 'Name' }, { title: 'Date' }, { title: 'Rating' }, { title: 'Unread' }]);

const emailDetails = ref(false);
const selectedMail = ref<MailItem | null>(null);

const handleFilter = async (string: string) => {
  store.SelectFilter(string);
  await store.filterMails(string);
};

const handleImportantChange = async (id: string) => {
  await store.setImportant(id);
  handleFilter(store.activeFilter);
};
const handleStarredChange = async (id: string) => {
  if (id) {
    await store.setStarred(id);
    handleFilter(store.activeFilter);
  }
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
  <div v-if="!emailDetails">
    <!---Topbar Row-->
    <div class="pa-3 d-flex align-center flex-wrap justify-space-between">
      <div class="d-flex align-center">
        <v-btn icon size="small" class="d-lg-block d-none" variant="text" @click="$emit('sToggle')"><Menu2Icon size="18" /></v-btn>
        <v-btn icon size="small" flat @click="compact = !compact"><ArrowsMoveVerticalIcon size="16" /></v-btn>
        <v-btn icon size="small" flat id="menu-activator"><DotsIcon size="16" /></v-btn>
        <v-menu activator="#menu-activator" width="100">
          <v-list>
            <v-list-item v-for="(item, index) in sorting" :key="index" :value="index">
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
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
          <td :class="`checkboxWidth d-table ${compact ? '' : 'py-1'}`">
            <v-checkbox v-model="mail.isChecked" hide-details color="primary"></v-checkbox>
          </td>
          <td class="checkboxWidth">
            <v-btn icon size="small" flat @click="handleStarredChange(`${mail.id}`)">
              <v-icon v-if="mail.starred" color="warning">mdi-star</v-icon>
              <v-icon v-else>mdi-star-outline</v-icon>
            </v-btn>
          </td>
          <td width="30">
            <v-btn icon size="small" flat @click="handleImportantChange(`${mail.id}`)">
              <v-icon v-if="mail.important" color="secondary">mdi-label</v-icon>
              <v-icon v-else>mdi-label-outline</v-icon>
            </v-btn>
          </td>
          <td @click="handleUserChange(mail)">
            <img :src="mail.profile.avatar" :alt="mail.profile.avatar" width="36" />
          </td>
          <td class="text-no-wrap" @click="handleUserChange(mail)">{{ mail.profile.name }}</td>
          <td @click="handleUserChange(mail)">
            <div class="d-flex align-center ga-2">
              <div class="text-no-wrap text-truncate">{{ mail.subject }}</div>
              <v-chip size="small" color="primary" v-if="mail.promotions">Promotions</v-chip>
              <v-chip size="small" color="warning" v-if="mail.forums">Forums</v-chip>
              <v-btn icon v-if="mail.attachments.length > 0" size="small" flat><v-icon>mdi-attachment</v-icon></v-btn>
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
