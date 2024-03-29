<script setup lang="ts">
import { onMounted, computed } from 'vue';
import { useContactStore } from '@/stores/apps/contact';
const store = useContactStore();

onMounted(() => {
  store.fetchContacts();
});

const contact = computed(() => {
  return store.contact[store.selectedContact];
});
</script>

<template>
  <div v-if="contact" class="pa-6">
    <div class="d-flex align-center">
      <img :src="contact.avatar" :alt="contact.avatar" width="48" class="mr-2" />
      <div>
        <h5 class="text-h4">{{ contact.name }}</h5>
        <v-list-item-subtitle class="text-subtitle-2 font-weight-medium">{{ contact.role }}</v-list-item-subtitle>
      </div>
    </div>
    <v-row class="my-3">
      <v-col cols="6">
        <v-btn variant="outlined" color="primary" block prepend-icon="mdi-message-outline" @click="$emit('editContact')">Edit</v-btn>
      </v-col>
      <v-col cols="6">
        <v-btn variant="outlined" color="error" block prepend-icon="mdi-alert">Block</v-btn>
      </v-col>
    </v-row>
    <table class="text-body-1">
      <tbody>
        <tr>
          <td class="pa-2 ps-0"><BuildingIcon /></td>
          <td>{{ contact.company }}</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><BriefcaseIcon /></td>
          <td>{{ contact.role }}</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><MailIcon /></td>
          <td>{{ contact.work_email }}</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><PhoneIcon /></td>
          <td>{{ contact.work_phone }}</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><MapPinIcon /></td>
          <td>{{ contact.location }}</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><CakeIcon /></td>
          <td>November 30, 1997</td>
        </tr>
        <tr>
          <td class="pa-2 ps-0"><AlertCircleIcon /></td>
          <td>{{ contact.birthdayText }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
