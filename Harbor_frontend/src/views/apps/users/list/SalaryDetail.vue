<script setup lang="ts">
import axios, { setClientHeaders } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import html2pdf from "html2pdf.js";
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const route = useRoute();
const salary = ref([]);
const token: string | null = localStorage.getItem('token');
onMounted(() => {
  fetchSalary();
});
async function fetchSalary() {
  const salaryId = route.params.salaryId;
  try {
    const token = localStorage.getItem('token');
    setClientHeaders(token);
    const response = await axios.get(`${baseUrl}/salary/mysalary/${salaryId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    salary.value = response.data.result;
  } catch (error) {
    console.log(error);
  }
};

function savePDF() {
  var element = document.getElementById("save_result");
  var opt = {
    margin: 0.1,
    filename: "test.pdf",
    image: { type: "jpeg", quality: 1 },
    html2canvas: { scale: 3 },
    jsPDF: { unit: "in", format: "a4", orientation: "portrait" },
  };
  html2pdf().set(opt).from(element).save();
};

</script>

<template>
  <div class="my-3 text-right">
    <v-btn @click="savePDF">
      <FileDescriptionIcon stroke-width="1.5" width="20" class="v-icon--start" /> 인쇄하기
    </v-btn>
  </div>
  <v-row id="save_result">
    <v-col cols="12" lg="12">
      <v-card variant="flat">
        <v-card variant="outlined">
          <v-card-text class="text-center">
            <h2 class=" mt-3"> 급여 명세서</h2>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" >
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
                      <!-- 직위 -->
                      <v-col cols="12" sm="3" class="ml-3">
                        <h4 class="mb-2 text-h4">사원번호 :
                          <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">{{ salary.employeeId
                            }}</span>
                        </h4>
                        <h4 class="mb-2 text-h4">직위 :
                          <span
                            class="text-subtitle-2 text-medium-emphasis font-weight-medium">{{ salary.position?.slice(0, 2) }}</span>
                        </h4>
                        <h4 class="mt-2 text-h4">호봉 :
                          <span
                            class="text-subtitle-2 text-medium-emphasis font-weight-medium">{{ salary.position?.slice(2) }}</span>
                        </h4>
                        <h4 class="mt-2 text-h4">지급기간 :
                          <span
                            class="text-subtitle-2 text-medium-emphasis font-weight-medium">{{ salary.salaryMonthOfYear?.slice(2) }}</span>
                        </h4>
                      </v-col>
                      <!-- 기본급 -->
                      <v-col cols="12" sm="5" lg="4" class="text-right">
                        <h5 class="py-2 text-subtitle-1">기본급 :</h5>
                        <h5 class="py-2 text-subtitle-1">공제총액 :</h5>
                        <h5 class="py-2 text-subtitle-1 text-primary mt-7">실 지급</h5>
                      </v-col>
                      <v-col cols="12" sm="3" lg="4" class="text-center">
                        <h5 class="py-2 text-subtitle-1 text-disabled">{{ Math.floor(salary.baseSalary / 12) }}</h5>
                        <h5 class="py-2 text-subtitle-1 text-disabled"> - {{ Math.floor((salary.baseSalary / 12) -
      salary.salaryGross) }}</h5>
                        <h5 class="py-2 text-subtitle-1 text-primary mt-7">{{ Math.floor(salary.salaryGross) }}</h5>

                      </v-col>
                    </v-row>
                    <v-divider class="my-4"></v-divider>
                    <v-row>
                    </v-row>
                  </UiChildCard>
                  <!-- shopping list -->
                  <v-card elevation="0" class="mt-6">
                    <v-card variant="outlined">
                      <v-card-item class="py-3">
                        <v-card-title class="text-h5">급여 내역</v-card-title>
                      </v-card-item>
                      <v-divider></v-divider>
                     
                      <v-divider></v-divider>
                      <v-card-text>
                        <v-sheet class="bg-lightprimary pa-2 pa-sm-6" rounded="sm">
                          <v-row class="justify-end">
                            <v-col cols="6" sm="6" lg="8" class="text-left my-10">
                              <h4 class="mb-2 text-h4">공제 계산표</h4>
                              <h5 class="py-2 text-subtitle-1 text-boid">국민연금료 : 
                              <span class="py-2 text-subtitle-1">기준소득월액 * 4.5%</span>
                              </h5>
                              <h5 class="py-2 text-subtitle-1">건강보험료 :
                              <span class="py-2 text-subtitle-1">보수월액 * 3.545% 또는 과세대상임금 * 3.545%</span>
                              </h5>
                              <h5 class="py-2 text-subtitle-1">장기요양보험료 :
                              <span class="py-2 text-subtitle-1">건강보험료 * 12.81</span>
                              </h5>
                              <h5 class="py-2 text-subtitle-1">고용보험료 :
                              <span class="py-2 text-subtitle-1">월평균보수 * 0.9% 또는 과세대상임금 * 0.9%</span>
                              </h5>
                            </v-col>
                            <v-col cols="6" sm="3" lg="2" class="text-right">
                              <h5 class="py-2 text-subtitle-1">기본급 :</h5>
                              <h5 class="py-2 text-subtitle-1">국민연금료 :</h5>
                              <h5 class="py-2 text-subtitle-1 text-no-wrap">건강보험료 :</h5>
                              <h5 class="py-2 text-subtitle-1 text-no-wrap">장기요양보험료 :</h5>
                              <h5 class="py-2 text-subtitle-1 text-no-wrap">고용보험료 :</h5>
                              <h5 class="py-2 text-subtitle-1 text-primary mt-7">Total</h5>
                            </v-col>
                            <v-col cols="6" sm="3" lg="2" class="text-right">
                              <h5 class="py-2 text-subtitle-1 text-disabled">{{ Math.floor(salary.baseSalary / 12) }} 원</h5>
                              <h5 class="py-2 text-subtitle-1 text-disabled"> - {{ Math.floor(salary.baseSalary / 12 * 0.045) }} 원</h5>
                              <h5 class="py-2 text-subtitle-1 text-disabled"> - {{ Math.floor(salary.baseSalary / 12 * 0.035) }} 원</h5>
                              <h5 class="py-2 text-subtitle-1 text-disabled"> - {{ Math.floor(salary.baseSalary / 12 * 0.004) }} 원</h5>
                              <h5 class="py-2 text-subtitle-1 text-disabled"> - {{ Math.floor(salary.baseSalary / 12 * 0.009) }} 원</h5>
                              <h5 class="py-2 text-subtitle-1 text-primary mt-7">{{ Math.floor(salary.salaryGross) }} 원</h5>
                            </v-col>
                          </v-row>
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
</template>
