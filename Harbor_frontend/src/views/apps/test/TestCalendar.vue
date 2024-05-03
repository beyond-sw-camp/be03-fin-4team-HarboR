<template>
    <v-row justify="space-between" class="align-center mb-3">
      <v-col cols="12" md="3">
        <h5 class="text-center">상품 ID</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">수량</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">상품명</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">가격</h5>
      </v-col>
    </v-row>

    <v-row justify="space-between" class="align-center mb-3">
      <v-col cols="12" md="3">
        <h5 class="text-center">1</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">100</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">이원태</h5>
      </v-col>
      <v-col cols="12" md="3">
        <h5 class="text-center">30000원</h5>
      </v-col>
    </v-row>
</template>

<script>
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #c3dafe;
  padding: 8px;
  margin-top: 4px;
  border-radius: 4px;
}

.task-item button {
  background-color: #e53e3e;
  color: #fff;
  border: none;
  padding: 4px 8px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
