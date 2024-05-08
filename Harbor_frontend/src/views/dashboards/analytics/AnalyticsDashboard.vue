<script setup lang="ts">
import MarketShare from '../../widgets/chart/components/MarketShare.vue';
import { ref, onMounted, computed } from 'vue';
import { ApproveStore } from '@/stores/apps/approveUser.ts';
import { useRouter } from 'vue-router';

const router = useRouter();
const isLoading = ref(false);

const goToAttendanceDetailPage = () => {
  router.push('/dashboard/analytics/attendancedetail');
};

const store = ApproveStore();
const token: string | null = localStorage.getItem('token');
onMounted(() => {
  store.fetchlistCards(token);
  store.getCountAll(token);
});
const num1 = computed(() => {
  return store.card_len_false;
});
const num2 = computed(() => {
  return store.card_len_all;
});

const newMemberFileInput = ref(null);

const uploadFileNewMember = () => {
  newMemberFileInput.value.click();
};
const handleFileUploadNewMember = async (event) => {
  isLoading.value = true; // 로딩 시작
  console.log('로딩시작');
  const file = event.target.files[0];
  try {
    const result = await store.uploadNewMemberFile(token, file); // await 키워드 추가
    alert('성공');
    location.reload();
  } catch (error) {
    console.log(error);
  } finally {
    isLoading.value = false; // 로딩 종료
  }
};
</script>

<template>
  <v-row>
    <!-- column 1 -->
    <v-col cols="12" lg="8">
      <MarketShare />
    </v-col>

    <v-col cols="12" md="4">
      <v-card elevation="0" class="mb-6 overflow-hidden">
        <v-card variant="outlined">
          <v-row>
            <v-col cols="6" sm="6" class="pa-9 pb-6 border-blue-right">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <ShareIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">{{ num1 }}</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">미승인</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-col cols="6" sm="6" class="pa-9 pb-6 pl-4 pl-sm-9">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <RouterIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">{{ num2 + num1 }}</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">회원수</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-divider></v-divider>
            <v-col cols="6" sm="6" class="pa-9 pt-6 border-blue-right">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat" @click="goToAttendanceDetailPage">
                    <FlowerIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">인사발령 상세보기</h5>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-col cols="6" sm="6" class="pa-9 pt-6 pl-4 pl-sm-9">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat" @click="uploadFileNewMember">
                    <BasketIcon stroke-width="1.5" size="24" />
                  </v-btn>
                  <input type="file" ref="newMemberFileInput" style="display: none" @change="handleFileUploadNewMember" />

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">신입사원 인사정보 업데이트</h5>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-divider></v-divider>
          </v-row>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
  <v-dialog v-model="isLoading" persistent max-width="140px">
    <img src="@/assets/images/harbor.png" alt="로딩 중" width="140" height="140" />
  </v-dialog>
</template>
