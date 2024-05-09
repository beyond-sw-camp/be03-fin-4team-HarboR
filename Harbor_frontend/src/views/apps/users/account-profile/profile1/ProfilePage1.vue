<script setup lang="ts">
import { ref } from 'vue';
import ProfileTab from './ProfileTab.vue';
import ProfileUpdate from './ProfileUpdate.vue';
import { UserCircleIcon, UserEditIcon } from 'vue-tabler-icons';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useRoute } from 'vue-router';

const page = ref({ title: '사원 정보' });
const role = localStorage.getItem('role');
const getEmployeeId = localStorage.getItem('employeeId');
const route = useRoute();
const routeEmployeeId = route.params.employeeId;
const breadcrumbs = ref([
  { title: '인사관리', disabled: false, href: '#' },
  { title: '사원목록', disabled: false, href: '#' },
  { title: '조회', disabled: true, href: '#' }
]);

const tab = ref('tab-profile'); // 초기 탭 설정
</script>

<template>
<BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
<v-row>
  <v-col cols="12">
    <v-card variant="flat">
      <v-card variant="outlined">
        <v-card-text>
          <v-tabs v-model="tab" color="primary">
            <v-tab variant="plain" value="tab-profile">
              <UserCircleIcon class="v-icon--start" width="20" stroke-width="1.5" /> 상세조회
            </v-tab>
            <v-tab variant="plain" value="tab-update" v-if="getEmployeeId === routeEmployeeId || role === 'ADMIN'" >
              <UserEditIcon class="v-icon--start" width="20" stroke-width="1.5" /> 수정
            </v-tab>
          </v-tabs>
          <v-divider></v-divider>
          <div class="pt-6">
            <v-window v-model="tab">
              <v-window-item value="tab-profile">
                <ProfileTab />
              </v-window-item>
              <v-window-item value="tab-update">
                <ProfileUpdate />
              </v-window-item>
            </v-window>
          </div>
        </v-card-text>
      </v-card>
    </v-card>
  </v-col>
</v-row>
</template>
