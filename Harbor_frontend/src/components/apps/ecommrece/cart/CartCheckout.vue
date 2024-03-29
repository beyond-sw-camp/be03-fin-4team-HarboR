<script setup lang="ts">
import { ref } from 'vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import StepFirst from './steps/StepFirst.vue';
import StepSecond from './steps/StepSecond.vue';
import Payment from './steps/PaymentCard.vue';

const store = useEcomStore();
const tab = ref('tab-1');
function changeTab(e: string) {
  tab.value = e;
}
</script>
<template>
  <v-card variant="flat">
    <v-card-text>
      <v-tabs v-model="tab" color="primary" class="customTab">
        <v-tab value="tab-1" rounded="md" class="mb-3 mx-2 text-left" height="70">
          <UserIcon stroke-width="1.5" width="20" class="v-icon--start" />
          <div>
            <div>User Profile</div>
            <span class="text-subtitle-2 text-lightText text-medium-emphasis font-weight-medium d-block">Profile Settings</span>
          </div>
        </v-tab>

        <v-tab value="tab-2" rounded="md" class="mb-3 mx-2 text-left" height="70" :disabled="store.cart.length < 1">
          <FileDescriptionIcon stroke-width="1.5" width="20" class="v-icon--start" />
          <div>
            <div>Billing</div>
            <span class="text-subtitle-2 text-lightText text-medium-emphasis font-weight-medium d-block"> Billing Information </span>
          </div>
        </v-tab>

        <v-tab value="tab-3" rounded="md" class="mb-3 mx-2 text-left" height="70" :disabled="store.cart.length < 1">
          <CreditCardIcon stroke-width="1.5" width="20" class="v-icon--start" />
          <div>
            <div>Payment</div>
            <span class="text-subtitle-2 text-lightText text-medium-emphasis font-weight-medium d-block"> Add & Update Card </span>
          </div>
        </v-tab>
      </v-tabs>
      <v-window v-model="tab">
        <v-window-item value="tab-1" class="pa-1">
          <StepFirst />
          <v-row class="mt-3 text-sm-left text-center">
            <v-col cols="12" sm="6">
              <v-btn color="primary" variant="tonal" to="/ecommerce/products">Continue Shopping</v-btn>
            </v-col>
            <v-col cols="12" sm="6" class="text-sm-right">
              <v-btn color="primary" @click="changeTab('tab-2')" v-if="store.cart.length >= 1">CheckOut</v-btn>
            </v-col>
          </v-row>
        </v-window-item>
        <v-window-item value="tab-2" class="pa-1">
          <StepSecond />
          <v-row class="mt-3">
            <v-col cols="6">
              <v-btn color="primary" variant="tonal" @click="changeTab('tab-1')">Back</v-btn>
            </v-col>
            <v-col cols="6" class="text-right">
              <v-btn color="primary" @click="changeTab('tab-3')">Place an Order</v-btn>
            </v-col>
          </v-row>
        </v-window-item>
        <v-window-item value="tab-3" class="pa-1">
          <Payment />
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>
<style lang="scss">
.customTab {
  min-height: 68px;
}
</style>
