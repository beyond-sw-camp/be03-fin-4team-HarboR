<script setup lang="ts">
import axios from 'axios';
import { shallowRef, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
// icons
import { MailIcon, MapPinIcon, PhoneIcon, EditIcon } from 'vue-tabler-icons';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const items = shallowRef([
  { text: 'Email', icon: MailIcon, divider: true, subtext: 'demo@sample.com' },
  { text: '전회번호', icon: PhoneIcon, divider: true, subtext: '(+99) 9999 999 999' },
  { text: '소속', icon: MapPinIcon, divider: false, subtext: 'Melbourne' }
]);
// 상태 관리를 위한 ref 정의
const employeeDetails = ref([]);

// vue-router의 useRoute를 사용하여 현재 라우트 정보에 접근
const route = useRoute();

onMounted(async () => {
  // 라우트 파라미터에서 사원번호(employeeId)를 추출
  const employeeId = route.params.employeeId;
  console.log(employeeId);
  try {
    const response = await axios.get(`${baseUrl}/employee/get/${employeeId}/detail`);
    employeeDetails.value = response.data.result;
    console.log(employeeDetails.value);
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
});

</script>

<template>
  <v-row>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined" class="overflow-hidden">
          <v-list lines="two">
            <v-list-item>
              <template v-slot:prepend>
                <img src="@/assets/images/users/avatar-3.png" alt="John Doe" width="40" class="v-avatar" />
              </template>
              <template v-slot:title>
                <h5 class="text-subtitle-1 mt-1">{{ employeeDetails.name }}</h5>
              </template>
              <template v-slot:subtitle>
                <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">{{ employeeDetails.employeeId }}</span>
              </template>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-card-text>
            <v-list>
              <template v-for="(item, i) in items" :key="i">
                <v-list-item color="primary" :value="item" class="py-4">
                  <template v-slot:prepend>
                    <!-- <v-icon :icon="item.icon"></v-icon> -->
                    <component :is="item.icon" size="20" stroke-width="1.5" class="mr-2" />
                  </template>

                  <v-list-item-title class="text-subtitle-1">
                    {{ item.text }}
                  </v-list-item-title>

                  <template v-slot:append>
                    <span class="text-subtitle-2 text-disabled font-weight-medium">{{ item.subtext }}</span>
                  </template>
                </v-list-item>
                <v-divider v-if="item.divider"></v-divider>
              </template>
            </v-list>
            <v-row justify="center" class="mt-4">
              <v-col md="3" class="text-center">
                <h3 class="text-h3">37</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Mails </span>
              </v-col>
              <v-col md="3" class="text-center">
                <h3 class="text-h3">2749</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Followers</span>
              </v-col>
              <v-col md="3" class="text-center">
                <h3 class="text-h3">678</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Following</span>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined" class="overflow-hidden">
          <!-- 뭐 만들지 -->
        <v-row class="my-3 mx-2">
          <v-col cols="6">
            <div class="text-center">
              <h2 class="text-primary">{{ employeeDetails.name }}</h2>
              <p class="mt-1">{{ employeeDetails.position }}</p>
            </div>
          </v-col>
          <v-col cols="6">
            <div class="text-right">
              <img src="@/assets/images/logos/logo.svg" class="v-avatar" />
            </div>
          </v-col>
        </v-row>
        <v-row class="mt-4">
          <v-col cols="6">
            <div class="text-center">
              <p>company </p>
              <p>email</p>
              <p>phone</p>
            </div>
          </v-col>
        </v-row>
          <!-- 여기까지 -->
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
