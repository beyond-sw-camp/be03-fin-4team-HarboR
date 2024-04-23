<script setup lang="ts">
import { shallowRef } from 'vue';
// third-party
import { format } from 'date-fns';
const props = defineProps({
  selectedMail: Object || Array
});
const sorting = shallowRef([{ title: 'Name' }, { title: 'Date' }, { title: 'Rating' }, { title: 'Unread' }]);

defineEmits(['toggleDetail']);
</script>

<template>
  <div class="topbarMail d-flex ga-4 align-center w-100">
    <!-- 뒤로가기 버튼 -->
    <v-btn icon flat @click="$emit('toggleDetail')" size="small"><ChevronLeftIcon size="18" /></v-btn>
    <!-- 이메일 이름 -->
    <div class="d-flex align-center ga-4 w-100">
      <div>
        <h4 class="text-h4 mb-n1">{{ props.selectedMail?.profile.name }}</h4>
        <small>From: {{ props.selectedMail?.profile.email }}</small>
      </div>
    <!-- 오른쪽 끝에 날짜 -->
      <div class="ml-auto text-subtitle-2 text-medium-emphasis">{{ format(new Date(selectedMail?.time!), 'd MMM') }}</div>
    </div>
  </div>
  <div class="d-flex align-center ga-2 mt-3">
    <h3 class="text-h3 py-4 mr-auto">{{ selectedMail?.subject }}</h3>
    <v-menu activator="#menu-activator" width="100">
      <v-list>
        <v-list-item v-for="(item, index) in sorting" :key="index" :value="index">
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
  <div class="py-4 text-subtitle-1 font-weight-light">
    Dear {{ selectedMail?.profile.name }},<br /><br />
    <p>{{ selectedMail?.message }}</p>
    <br />
    <p>Kindly Regards,</p>
    <p>{{ selectedMail?.sender.name }}</p>
  </div>
  <div class="py-3">
    <h5 class="text-h5"><v-icon>mdi-attachment</v-icon> {{ selectedMail?.attachments.length }} Attachement</h5>
    <v-row class="mt-4">
      <v-col cols="3" v-for="attach in selectedMail?.attachments" :key="attach.title">
        <v-card class="overflow-hidden">
          <v-img :src="attach.image" height="100px" cover></v-img>
          <div class="pa-3">
            <div class="d-flex align-center">
              <h5 class="mr-auto text-truncate">{{ attach.title }}</h5>
              <v-btn icon size="small" flat><DownloadIcon size="16" /></v-btn>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>
