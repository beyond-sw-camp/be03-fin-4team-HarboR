<script>
import axios, { setClientHeaders } from '@/utils/axios';

export default {
  name: 'SeverancePayCalculator',
  data() {
    return {
      severancePayResult: null,
    };
  },
  methods: {
    async severancePay() {
      const baseUrl = `${import.meta.env.VITE_API_URL}`;
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/salary/svDetails`);
        this.severancePayResult = response.data.severancePay;
        alert("퇴직금 계산 완료");
      } catch (error) {
        console.error('퇴직금 계산 중 오류 발생:', error);
        alert('퇴직금 계산 중 오류가 발생했습니다.');
      }
    },
    formatSeverancePay(amount) {
        // 숫자를 3자리마다 쉼표로 구분하여 문자열로 변환하여 반환합니다.
        return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },
};
</script>
<template>
  <v-row>
    <v-col cols="12" md="12">
      <div class="ui-parent-card">
        <div class="card-header">퇴직금 계산기</div>
        <div class="card-body">
          <!-- 유의사항 -->
          <div class="severance-pay-guidelines">
            <h3>퇴직금 계산 유의사항</h3>
            <p>1. 퇴직금은 1년 이상 재직한 근로자에 한해 지급됩니다. 별도의 사규가 없다면 재직기간 1년당 30일분의 평균임금이 지급됩니다.</p>
            <p>2. "평균임금"이란 이를 산정하여야 할 사유가 발생한 날 이전 3개월 동안에 그 근로자에게 지급된 임금의 총액을 그 기간의 총일수로 나눈 금액입니다.</p>
            <p>3. 근로자가 취업한 후 3개월 미만인 경우도 이에 준합니다.</p>
            <p>4. 이에 따라 산출된 금액이 그 근로자의 통상임금보다 적으면 그 통상임금액을 평균임금으로 합니다.</p>
            <p>5. 위 계산결과는 근로자퇴직급여 보장법상 퇴직금 지급규정에 따른 산출금액이며, 별도의 사규 등이 있는 경우 위 계산결과와 약간의 오차가 발생할 수 있습니다.</p>
          </div>
          <div class="severance-pay-calculator">
            <!-- 퇴직금 계산 버튼 -->        
            <v-btn class="severance-pay-button" @click="severancePay">
              <CalculatorIcon stroke-width="1.5" width="30" class="v-icon--start" /> 퇴직금 계산하기
            </v-btn>
            
            <!-- 퇴직금 계산 결과 표시 -->
            <div v-if="severancePayResult !== null" class="receipt-style">
  <h3>퇴직금 계산 결과 :</h3>
  <p>{{ formatSeverancePay(severancePayResult) }} 원</p>
</div>
          </div>
        </div>
      </div>
    </v-col>
  </v-row>
</template>
<style scoped>
.receipt-style {
  padding: 20px;
  background-color: #f9f9f9;
  border: 1px dashed #ccc;
  max-width: 500px;
  margin: auto;
  font-family: 'Courier New', Courier, monospace;
}

.receipt-style h3 {
  text-align: center;
  text-decoration: underline;
}

.receipt-style p {
  font-size: 16px;
  line-height: 1.5;
}
</style>

<style>
.severance-pay-button {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<style>
.ui-parent-card {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  margin: 20px 0;
}

.card-header {
  padding: 15px;
  background-color: #ffffff;
  border-bottom: 1px solid #e0e0e0;
  font-size: 20px;
  font-weight: bold;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
}

.card-body {
  padding: 20px;
}

.severance-pay-calculator {
  margin-top: 20px;
}

.severance-pay-calculator button {
  padding: 10px 15px;
  background-color: #ffffff;
  color: rgb(0, 0, 0);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.severance-pay-calculator button:hover {
  background-color: #0056b3;
  color: #ffffff;
}
</style>



