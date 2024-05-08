<script setup lang="ts">
import { ref, shallowRef, onMounted } from 'vue';
import axios, { setClientHeaders } from '@/utils/axios';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
const tab = ref(null);
const workList = shallowRef([
  { name: '출장', value: 'O03' },
  { name: '휴가', value: 'O04' },
  { name: '휴계', value: 'O05' },
  { name: '시차', value: 'O07' },
  { name: '반차', value: 'O08' },
  { name: '병가', value: 'O09' }
]);
const selectedWork = shallowRef('O04');
// 현재 시간을 가져오는 함수
const getCurrentDateTimeInKST = () => {
  const currentDateTime = new Date();
  // UTC 시간에 +9시간을 더해 한국시간으로 맞췄음
  const koreanDateTime = new Date(currentDateTime.getTime() + 9 * 60 * 60 * 1000);
  return koreanDateTime.toISOString().slice(0, 16);
};
onMounted(() => {
  annualremain();
});
// 남은 휴가 변수
const vacation = ref('');
// 남은 휴가 확인 api 호출
async function annualremain() {
  try {
    setClientHeaders();
    const response = await axios.get(`${baseUrl}/total/employee/annualremain`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    vacation.value = response.data.result;
  } catch (error) {
    console.log(error);
  }
}
// 전자결재 리스트
const modalVisible = ref(false);
const content = ref('');
const auths = shallowRef<any[]>([]);
const selecteFirst = ref<string>(''); // 선택된 값을 저장할 변수
const selecteSecond = ref<string>(''); // 선택된 값을 저장할 변수
const selecteThird = ref<string>(''); // 선택된 값을 저장할 변수
const selectedFirstName = ref('승인자 지정'); // 선택된 값을 저장할 변수
const selectedSecondName = ref('승인자 지정'); // 선택된 값을 저장할 변수
const selectedThirdName = ref('승인자 지정'); // 선택된 값을 저장할 변수
// 전자결제 모달창
const openModal = () => {
  modalVisible.value = true;
  authlist();
};
async function authlist() {
  try {
    setClientHeaders();
    const response = await axios.get(`${baseUrl}/total/annual/read/authlist`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    auths.value = response.data.result;
  } catch (error) {
    console.log(error);
  }
}
function handleAuthorizerChange(authorizerName, selecte) {
  if (selecte == 'first') {
    selectedFirstName.value = authorizerName;
  }
  if (selecte == 'second') {
    selectedSecondName.value = authorizerName;
  }
  if (selecte == 'third') {
    selectedThirdName.value = authorizerName;
  }
}
const closeModal = () => {
  modalVisible.value = false;
};

const saveModal = () => {
  closeModal();
};
// 시간 관련 변수들
const currentISODateTime = getCurrentDateTimeInKST();
const startDate = ref(currentISODateTime);
const endDate = ref(currentISODateTime);
const clickedStartDateInfo = ref('');
const clickedEndDateInfo = ref('');
const minDate = currentISODateTime;

// 시작일시 세팅 함수
const handleStartDateChange = (event) => {
  console.log(event);
  const selectedDateTime = event.target.value;
  clickedStartDateInfo.value = selectedDateTime;
  console.log(clickedStartDateInfo.value);
};
// 종료일시 세팅 함수
const handleEndDateChange = (event) => {
  console.log(event);
  const selectedDateTime = event.target.value;
  clickedStartDateInfo.value = selectedDateTime;
  console.log(clickedStartDateInfo.value);
};
async function attendanceReq() {
  if(selectedWork.value === "O07"){
    if(!clickedStartDateInfo.value || !clickedEndDateInfo.value){
      alert('시간을 입력해주세요.');
    return;
  }
  }else if (!clickedStartDateInfo.value || !clickedEndDateInfo.value || !content.value || !selecteFirst.value || !selecteSecond.value || !selecteThird.value) {
    alert('모든 전자 결재 정보를 입력해주세요.');
    return;
  }
  try {
    const response = await axios.post(`${baseUrl}/total/attendance/requestwork`, {
      workStartTime: clickedStartDateInfo.value,
      workEndTime: clickedEndDateInfo.value,
      workPolicy: selectedWork.value,
      adjustmentComment: content.value,
      firstSignId: selecteFirst.value,
      secondSignId: selecteSecond.value,
      thirdSignId: selecteThird.value
    });
    // 회원가입이 성공하면 사용자에게 알림을 표시하거나 다른 작업을 수행할 수 있습니다.
    alert('전자결재완료');
    location.href = '/attendance/list/req'; 
  } catch (error) {
    console.error('전자결재:', error);
    alert('전자결재 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
  }
}
</script>

<template>
  <v-row>
    <v-col cols="12" lg="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <v-card-text class="text-center">
            <h2 class="mt-3">전자 결재</h2>
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
                      <v-col cols="12" sm="6">
                        <h4 class="mb-2 text-h4">
                          결재 종류 :
                          <select v-model="selectedWork" style="text-align: center; width: 100px; height: 30px; border: 1px solid black">
                            <option v-for="(work, index) in workList" :value="work.value" v-bind:key="index">
                              {{ work.name }}
                            </option>
                          </select>
                        </h4>

                        <!-- <v-select hide-details :items="workPolicy" density="compact" v-model="selectedWorkPolicy" variant="outlined"
                          style="width: 120px"></v-select> -->
                      </v-col>
                      <!-- 승인자 -->
                      <v-col cols="12" sm="6" v-if="selectedWork != 'O07'">
                        <v-row v-model="tab">
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">1차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1" style="color: blue" @click="openModal">{{ selectedFirstName }}</td>
                              </tr>
                            </v-table>
                          </v-col>
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">2차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1" style="color: blue" @click="openModal">{{ selectedSecondName }}</td>
                              </tr>
                            </v-table>
                          </v-col>
                          <v-col cols="6" sm="4" class="text-center">
                            <v-table class="border-lg-red px-2 py-1">
                              <tr>
                                <td class="py-2 text-subtitle-1 font-weight-black">3차 승인자</td>
                              </tr>
                              <tr>
                                <td class="py-2 text-subtitle-1" style="color: blue" @click="openModal">{{ selectedThirdName }}</td>
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
                          <input type="datetime-local" v-model="startDate" :max="maxDate" :min="minDate" @change="handleStartDateChange" />
                        </v-col>
                        <v-col cols="6" sm="2" class="text-center">~</v-col>
                        <v-col cols="6" sm="2">
                          종료 일시
                          <input type="datetime-local" v-model="endDate" :max="maxDate" :min="minDate" @change="handleEndDateChange" />
                        </v-col>
                      </v-row>
                      <v-divider></v-divider>
                      <v-row class="text-center ml-3 py-5">
                        <v-col cols="6" sm="2"> 잔여 연차 : {{ vacation }} </v-col>
                        <!-- <v-col cols="6" sm="2">
                          신청 일수 :
                        </v-col>
                        <v-col cols="6" sm="2">
                          신청 후 휴가 : 
                        </v-col> -->
                      </v-row>
                      <v-divider></v-divider>
                      <v-card-text>
                        <v-sheet class="bg-lightprimary pa-2 pa-sm-6" rounded="sm">
                          <textarea v-model="content" style="background-color: white; width: 100%; min-height: 100px"></textarea>
                        </v-sheet>
                      </v-card-text>
                    </v-card>
                  </v-card>
                  <v-row class="justify-end mr-10 my-3">
                    <v-btn @click="attendanceReq" class="my-1">
                      <FileDescriptionIcon stroke-width="1.5" width="20" class="v-icon--start" /> 제출하기
                    </v-btn>
                  </v-row>
                </v-window-item>
              </v-window>
            </v-card-text>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
  <!-- 승인리스트 모달창 -->
  <div>
    <v-dialog v-model="modalVisible" style="width: auto">
      <v-card>
        <v-card-title>승인 목록</v-card-title>
        <v-tabs v-model="tab" color="primary" class="my-2 border-bottom">
          <v-tab value="one">1차승인</v-tab>
          <v-tab value="two">2차승인</v-tab>
          <v-tab value="three">3차승인</v-tab>
        </v-tabs>
        <v-divider></v-divider>
        <v-card-text style="height: 300px" class="overflow-auto" v-model="tab">
          <!-- 1차승인 -->
          <div v-if="tab === 'one'">
            <v-radio-group v-model="selecteFirst" column>
              <v-radio
                v-for="(authorizer, index) in auths.firstAuthorizer"
                :key="index"
                :label="authorizer.name"
                :value="authorizer.employeeId"
                @change="handleAuthorizerChange(authorizer.name, 'first')"
              ></v-radio>
            </v-radio-group>
          </div>
          <!-- 2차승인 -->
          <div v-if="tab === 'two'">
            <!-- secondAuthorizer 배열이 비어 있지 않은 경우에만 라디오 버튼 그룹을 표시합니다. -->
            <v-radio-group v-if="auths.secondAuthorizer && auths.secondAuthorizer.length > 0" v-model="selecteSecond" column>
              <v-radio
                v-for="(authorizer, index) in auths.secondAuthorizer"
                :key="index"
                :label="authorizer.name"
                :value="authorizer.employeeId"
                @change="handleAuthorizerChange(authorizer.name, 'second')"
              ></v-radio>
            </v-radio-group>
            <!-- secondAuthorizer 배열이 비어 있는 경우 메시지를 표시합니다. -->
            <div v-else class="text-center py-2">2차 승인자가 없습니다.</div>
          </div>
          <!-- 3차승인 -->
          <div v-if="tab === 'three'">
            <!-- thirdAuthorizer 배열에 대해서도 동일한 로직을 적용할 수 있습니다. -->
            <v-radio-group v-if="auths.thirdAuthorizer && auths.thirdAuthorizer.length > 0" v-model="selecteThird" column>
              <v-radio
                v-for="(authorizer, index) in auths.thirdAuthorizer"
                :key="index"
                :label="authorizer.name"
                :value="authorizer.employeeId"
                @change="handleAuthorizerChange(authorizer.name, 'third')"
              ></v-radio>
            </v-radio-group>
            <div v-else class="text-center py-2">3차 승인자가 없습니다.</div>
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn color="error" variant="text" @click="closeModal">Close</v-btn>
          <v-btn color="success" variant="text" @click="saveModal">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
