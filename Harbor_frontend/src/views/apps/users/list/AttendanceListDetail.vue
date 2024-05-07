<script setup lang="ts">
import { format } from 'date-fns'
import { useCodeStore } from '@/stores/codetrans';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
// const router = useRouter();

const props = defineProps({
  selectedDetail: Object || Array
})
const codeStore = useCodeStore();
const getStatusCode = (payStatusCode) => {
  return codeStore.getStatusNameByCode(payStatusCode);
};
</script>

<template>
  <div class="topbarMail d-flex ga-4 align-center w-100 mb-3">
    <!-- 뒤로가기 버튼 -->
    <v-btn icon flat @click="$emit('toggleDetail')" size="small">
      <ChevronLeftIcon size="34" />
    </v-btn>
  </div>
  <v-row>
    <v-col cols="12" lg="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <v-card-text class="text-center">
            <h2 class=" mt-3"> 상세 조회</h2>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12">
      <!-- customer detail table -->
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-text class="mb-3">

            <v-divider></v-divider>
            <v-card-text class="px-0">
              <v-window>
                <v-window-item value="11">
                  <UiChildCard title="Customer">
                    <v-row>
                      <!-- 직위 및 카테고리 -->
                      <v-col cols="12" sm="4">
                        <h4 class="mb-2 text-h4">결재 종류 :
                          <p>{{ getStatusCode(selectedDetail?.payStatusCode) }}</p>
                        </h4>
                        <h4 class="mb-2 text-h4" v-if="selectedDetail?.requestName">상신자 :
                          <p>{{ selectedDetail?.requestName }}</p>
                        </h4>
                      </v-col>
                      <!-- 승인자 -->
                      <v-col cols="12" sm="8" v-if="selectedWork != 'O07'">
                        <v-row v-model="tab">
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">1차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1">
                                  {{ selectedDetail?.firstApprovalName }} </td>
                              </tr>
                              <tr>
                                <td v-if="selectedDetail?.firstApprovalDate" class="text-subtitle text-center"
                                  style="color: green;">{{ selectedDetail?.firstApprovalDate }} </td>
                                <td v-if="!selectedDetail?.firstApprovalDate" class="text-subtitle text-center"
                                  style="color: blue;"> 진행중 </td>
                              </tr>
                            </v-table>
                          </v-col>
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">2차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1">
                                  {{ selectedDetail?.secondApprovalName }}</td>
                              </tr>
                              <tr>
                                <td v-if="selectedDetail?.secondApprovalDate" class="text-subtitle text-center"
                                  style="color: green;">{{ selectedDetail?.secondApprovalDate }} </td>
                                <td v-if="!selectedDetail?.secondApprovalDate && selectedDetail?.firstApprovalDate"
                                  class="text-subtitle text-center" style="color: blue;"> 진행중 </td>
                                <td v-if="!selectedDetail?.secondApprovalDate && !selectedDetail?.firstApprovalDate"
                                  class="text-subtitle text-center">  </td>
                              </tr>

                            </v-table>
                          </v-col>
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">3차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1" >
                                  {{ selectedDetail?.thirdApprovalName }}</td>
                              </tr>
                              <tr>
                                <td v-if="selectedDetail?.thirdApprovalDate" class="text-subtitle text-center"
                                  style="color: green;">{{ selectedDetail?.thirdApprovalDate }} </td>
                                <td v-if="!selectedDetail?.thirdApprovalDate && selectedDetail?.secondApprovalDate"
                                  class="text-subtitle text-center" style="color: blue;"> 진행중 </td>
                                <td v-if="!selectedDetail?.thirdApprovalDate && !selectedDetail?.secondApprovalDate"
                                  class="text-subtitle text-center"></td>
                              </tr>
                            </v-table>
                          </v-col>
                        </v-row>
                      </v-col>
                    </v-row>
                    <v-divider class="my-4"></v-divider>
                  </UiChildCard>
                  <!-- 전자결재 내용 -->
                  <v-card elevation="0" class="mt-6">
                    <v-card variant="outlined">
                      <v-card-item class="py-3">
                        <v-card-title class="text-h5">결자결재 내용</v-card-title>
                      </v-card-item>
                      <v-divider></v-divider>
                      <v-row class="ml-3 py-5">
                        <v-col cols="6" sm="2">
                          시작 일시
                          <p>{{ selectedDetail?.workStartTime.slice(2, 10) }} {{ selectedDetail?.workStartTime.slice(11,
      16)
                            }}</p>
                        </v-col>
                        <v-col cols="6" sm="2" class="text-center">~</v-col>
                        <v-col cols="6" sm="2">
                          종료 일시 :
                          <p>{{ selectedDetail?.workEndTime.slice(2, 10) }} {{
      selectedDetail?.workEndTime.slice(11, 16) }}</p>
                        </v-col>
                      </v-row>
                      <v-divider></v-divider>
                      <v-card-text>
                        <v-sheet class="bg-lightprimary pa-2 pa-sm-6" rounded="sm">
                          <div style="background-color: white; width: 100%; min-height: 100px;">
                            <p class="mx-2 my-2"> {{ selectedDetail?.adjustmentComment }} </p>
                          </div>
                        </v-sheet>
                      </v-card-text>
                    </v-card>
                  </v-card>
                </v-window-item>
              </v-window>
            </v-card-text>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
  <!-- 전체 컨테이너 -->
  <div class="mt- d-flex flex-column justify-content-between" style="height: 100%;">
    <!-- 여기에 다른 내용 추가 가능 -->

  </div>
</template>

<style>
.delete-button,
.edit-button {
  margin-left: 10px;
  background-color: #5938de;
  color: white;
  border-radius: 10px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

/* 버튼에 마우스를 올렸을 때의 스타일 */
.delete-button:hover,
.edit-button:hover {
  background-color: #766ee6;
}

.edit-button {
  background-color: #4CAF50;
  /* 수정하기 버튼을 위한 다른 색상 */
}

.edit-button:hover {
  background-color: #66bb6a;
}
</style>