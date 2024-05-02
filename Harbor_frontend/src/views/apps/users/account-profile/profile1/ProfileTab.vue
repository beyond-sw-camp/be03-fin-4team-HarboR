<script setup lang="ts">
import axios, { setClientHeaders } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { useCodeStore } from '@/stores/codetrans';
// icons
import {
  MailIcon,
  MapPinIcon,
  PhoneIcon,
  MeepleIcon,
  BrandYcombinatorIcon,
  BuildingArchIcon,
  BrandInertiaIcon,
  ReportIcon
} from 'vue-tabler-icons';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
// 상태 관리를 위한 ref 정의
const employeeDetails = ref([]);
const positionName = ref([]);
const bankName = ref([]);
const dutyName = ref([]);
const departmentName = ref([]);
const teamName = ref([]);
// vue-router의 useRoute를 사용하여 현재 라우트 정보에 접근
const route = useRoute();
const token: string | null = localStorage.getItem('token');

onMounted(async () => {
  // 라우트 파라미터에서 사원번호(employeeId)를 추출
  const employeeId = route.params.employeeId;
  try {
    const codeStore = useCodeStore();
    setClientHeaders(token);
    const response = await axios.get(`${baseUrl}/employee/get/${employeeId}/detail`);
    employeeDetails.value = response.data.result;
    positionName.value = codeStore.getPositionNameByCode(employeeDetails.value.position);
    bankName.value = codeStore.getBankNameByCode(employeeDetails.value.bank);
    dutyName.value = codeStore.getDutyNameByCode(employeeDetails.value.duty);
    departmentName.value = codeStore.getDepartmentNameByCode(employeeDetails.value.department);
    teamName.value = codeStore.getTeamNameByCode(employeeDetails.value.team);
  } catch (error) {
    console.error('API 호출 중 오류 발생:', error);
  }
});
</script>

<template>
  <v-row>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined">
          <div class="pa-6">
            <h5 class="text-subtitle-1">{{ employeeDetails.employeeId }}</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text class="text-center">
            <img :src="employeeDetails.profileImagePath" alt="avatar" width="150" class="v-avatar" />
            <h2 class="mt-3">{{ employeeDetails.name }}</h2>
            <p class="text-subtitle-1 text-disabled font-weight-medium my-4">항상 행복하세요~</p>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined" class="overflow-hidden">
          <v-divider></v-divider>
          <v-card-text>
            <v-list>
              <!-- 이름 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <MeepleIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1">  이름 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ employeeDetails.name }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>

              <!-- 소속 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <BuildingArchIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 부서 / 팀 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium"> {{ teamName }} / {{ departmentName }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>
              <!-- 직급 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <BrandYcombinatorIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 직급 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ positionName }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>
              <!-- 직무 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <ReportIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 직무 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ dutyName }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>
              <!-- 이메일 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <MailIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> Email </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ employeeDetails.email }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>

              <!-- 전화번호 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <PhoneIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 전화번호 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ employeeDetails.phone }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>

              <!-- 입사일 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <BrandInertiaIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 입사일 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ employeeDetails.joinDate }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>

              <!-- 주소 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <MapIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 주소 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ employeeDetails.address }}</span>
                </template>
              </v-list-item>
              <v-divider></v-divider>

              <!-- 전화번호 -->
              <v-list-item color="primary" class="py-4">
                <template v-slot:prepend>
                  <PhoneIcon size="20" stroke-width="1.5" class="mr-2" />
                </template>
                <v-list-item-title class="text-subtitle-1"> 계좌번호 </v-list-item-title>
                <template v-slot:append>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">
                    {{ bankName }} / {{ employeeDetails.accountNumber }}</span
                  >
                </template>
              </v-list-item>
              <v-divider></v-divider>
            </v-list>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
