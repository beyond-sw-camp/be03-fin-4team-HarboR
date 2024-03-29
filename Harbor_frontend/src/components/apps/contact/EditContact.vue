<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useContactStore } from '@/stores/apps/contact';
import type { UserProfile } from '@/types/user/userProfile';
const store = useContactStore();

onMounted(() => {
  store.fetchContacts();
});

const contact = computed(() => {
  return store.contact[store.selectedContact];
});
const birthday = ref('November 30, 1997');
const uploader = ref();
const selectedFile = ref('');
function onButtonClick() {
  uploader.value.click();
}
function onFileChanged(e: Event) {
  const inputElement = e.target as HTMLInputElement;
  const file = inputElement.files?.[0];
  selectedFile.value = file ? file.name : '';
}
const modifyUser = async (u: UserProfile) => {
  await store.editContacts(u);
};
</script>

<template>
  <div v-if="contact" class="pa-6">
    <div class="d-flex align-center">
      <img :src="contact.avatar" :alt="contact.avatar" width="48" class="mr-2" />
      <div>
        <v-btn color="primary" size="small" variant="outlined" @click="onButtonClick">Change Avatar</v-btn>
        <input ref="uploader" class="d-none" type="file" accept="image/*" @change="onFileChanged" />
      </div>
    </div>

    <form class="my-5">
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="contact.name"
          prepend-inner-icon="mdi-account-circle-outline"
          label="Name"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="contact.company"
          prepend-inner-icon="mdi-domain"
          label="Company"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="contact.role"
          prepend-inner-icon="mdi-briefcase-outline"
          label="Job Title"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="contact.work_email"
          prepend-inner-icon="mdi-email-outline"
          label="Mail"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="contact.work_phone"
          prepend-inner-icon="mdi-phone-outline"
          label="Phone"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-text-field
          variant="outlined"
          v-model="birthday"
          append-inner-icon="mdi-calendar-outline"
          label="Birthday"
          hide-details
          color="primary"
        ></v-text-field>
      </div>
      <div class="py-3">
        <v-textarea label="Bio" variant="outlined" v-model="contact.birthdayText"> </v-textarea>
      </div>
      <div class="py-3 pt-0 d-flex ga-2">
        <v-btn variant="flat" color="primary" @click="$emit('closeDrawer'), modifyUser(contact)">Save</v-btn>
        <v-btn variant="outlined" color="primary" @click="$emit('closeDrawer')">Cancel</v-btn>
      </div>
    </form>
  </div>
</template>
