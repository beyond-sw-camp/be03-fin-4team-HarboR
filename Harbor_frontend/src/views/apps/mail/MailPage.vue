<script setup lang="ts">
import { ref } from 'vue';
import { useDisplay } from 'vuetify';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import MailSidebar from '@/components/apps/mail/MailSidebar.vue';
import MailListing from '@/components/apps/mail/MailListing.vue'; 
import noticeList from '@/views/apps/users/list/noticeList.vue';
const { lgAndUp } = useDisplay();


const page = ref({ title: '공지사항' });

const breadcrumbs = ref([
  {
    title: '기타',
    disabled: false,
    href: '#'
  },
  {
    title: '공지사항',
    disabled: true,
    href: '#'
  }
]);
const toggleSide = ref(false);
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row> 
    <v-col class="d-flex align-stretch overflow-auto">
      <v-card variant="flat">
        <div class="overflow-auto">
          <!-- 테스트 -->
          <noticeList @s-Toggle="toggleSide = !toggleSide" />
        </div>
      </v-card>
    </v-col>
  </v-row>

  <!-- <v-navigation-drawer temporary v-model="sDrawer" width="300" top v-if="!lgAndUp">
    <perfect-scrollbar style="height: calc(100vh - 100px)">
      <v-card-text class="pa-5"> <MailSidebar /> </v-card-text>
    </perfect-scrollbar>
  </v-navigation-drawer> -->
</template>
<style lang="scss">
.custom-main {
  margin: 0;
}
.mailSidebar {
  max-width: 350px;
}
</style>
