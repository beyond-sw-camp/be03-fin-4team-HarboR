<script setup lang="ts">
import { ref, computed } from 'vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import OrderSummaryVue from './OrderSummary.vue';
import AddressCard from './AddressCard.vue';
import AddCard from './AddCard.vue';
import Thankyou from './ThankYouDialog.vue';

const payment = ref('paypal');
const dialog = ref(false);
const thankyou = ref(false);

const delivery = ref('free');

const store = useEcomStore();
const getCart = computed(() => {
  return store.cart;
});
</script>

<template>
  <v-row class="mt-5">
    <v-col cols="12" lg="9">
      <h5 class="text-h5 mb-4">Delivery Options</h5>
      <v-radio-group v-model="delivery" inline>
        <v-row>
          <v-col cols="12 " sm="6">
            <div class="py-5 px-4 border rounded-md">
              <v-radio value="free" color="primary" class="label-op-1">
                <template v-slot:label>
                  <div>
                    <h4 class="text-h5 mb-1">Standard Delivery (Free)</h4>
                    <span class="d-block text-subtitle-2 text-medium-emphasis font-weight-medium">Delivery on Monday 8 Jun</span>
                  </div>
                </template>
              </v-radio>
            </div>
          </v-col>
          <v-col cols="12 " sm="6">
            <div class="py-5 px-4 border rounded-md">
              <v-radio value="paid" color="primary" class="label-op-1">
                <template v-slot:label>
                  <div>
                    <h4 class="text-h5 mb-1">Fast Delivery ($5.00)</h4>
                    <span class="d-block text-subtitle-2 text-medium-emphasis font-weight-medium">Delivery on Friday 5 Jun</span>
                  </div>
                </template>
              </v-radio>
            </div>
          </v-col>
        </v-row>
      </v-radio-group>
      <h5 class="text-h5 mb-4">Payment Options</h5>
      <v-row>
        <v-col cols="12" lg="6">
          <v-radio-group v-model="payment" class="payment-options">
            <div class="py-5 px-4 border rounded-md mb-6">
              <v-radio value="paypal" color="primary" class="label-op-1">
                <template v-slot:label>
                  <div class="d-flex align-center w-100 flex-grow-1">
                    <div>
                      <h4 class="text-h5 mb-1">Pay with PayPal</h4>
                      <span class="d-block text-subtitle-2 text-medium-emphasis text-wrap"
                        >You will be redirected to PayPal website to complete your purchase securely.</span
                      >
                    </div>
                    <div class="ml-auto">
                      <img src="@/assets/images/icons/icon-paypal.png" width="15" alt="img" />
                    </div>
                  </div>
                </template>
              </v-radio>
            </div>

            <div class="py-5 px-4 border rounded-md mb-6">
              <v-radio value="credit" color="primary" class="label-op-1">
                <template v-slot:label>
                  <div class="d-flex align-center w-100">
                    <div>
                      <h4 class="text-h5 mb-1">Credit/Debit Card</h4>
                      <span class="d-block text-subtitle-2 text-medium-emphasis text-wrap"
                        >We support Mastercard, Visa, Discover and Stripe.</span
                      >
                    </div>
                    <div class="ml-auto">
                      <img src="@/assets/images/icons/icon-visa.png" width="55" alt="img" />
                    </div>
                  </div>
                </template>
              </v-radio>
            </div>

            <div class="py-5 px-4 border rounded-md mb-6">
              <v-radio value="cash" color="primary" class="label-op-1">
                <template v-slot:label>
                  <div class="d-flex align-center w-100">
                    <div>
                      <h4 class="text-h5 mb-1">Cash on Delivery</h4>
                      <span class="d-block text-subtitle-2 text-medium-emphasis text-wrap"
                        >Pay with cash when your order is delivered.</span
                      >
                    </div>
                    <div class="ml-auto">
                      <img src="@/assets/images/icons/icon-cod.png" width="40" alt="img" />
                    </div>
                  </div>
                </template>
              </v-radio>
            </div>
          </v-radio-group>
        </v-col>
        <v-col cols="12" lg="6">
          <v-card class="mb-6" variant="flat" :disabled="payment !== 'credit'">
            <v-card-item class="py-3">
              <div class="d-flex align-center justify-space-between">
                <v-card-title class="text-subtitle-1">Add New Card</v-card-title>
                <v-btn variant="flat" color="primary" @click="dialog = true">
                  <PlusIcon stroke-width="1.5" size="20" />
                  Add Card
                </v-btn>
              </div>
            </v-card-item>
            <v-divider></v-divider>
            <v-card-text>
              <v-row>
                <v-col cols="12" sm="6">
                  <v-card elevation="0" class="object-card overflow-hidden">
                    <v-card-text class="top-object">
                      <div class="text-right mb-2">
                        <img src="@/assets/images/icons/icon-mastercard.png" alt="img" />
                      </div>
                      <h6 class="text-white text-medium-emphasis text-h5">**** **** **** 2599</h6>
                      <div class="d-flex align-center justify-space-between mt-3">
                        <div>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50">Expire Date</span>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50 mt-1 d-block">05/24</span>
                        </div>
                        <div>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50">CVV</span>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50 mt-1 d-block">085</span>
                        </div>
                      </div>
                    </v-card-text>
                  </v-card>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-card elevation="0" class="object-card object-secondary overflow-hidden">
                    <v-card-text class="top-object">
                      <div class="text-right mb-2 mt-3">
                        <img src="@/assets/images/icons/icon-visa-single.png" alt="img" />
                      </div>
                      <h6 class="text-white text-medium-emphasis text-h5 mt-4">**** **** **** 2599</h6>
                      <div class="d-flex align-center justify-space-between mt-3">
                        <div>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50">Expire Date</span>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50 mt-1 d-block">05/24</span>
                        </div>
                        <div>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50">CVV</span>
                          <span class="text-medium-emphasis text-white text-subtitle-2 opacity-50 mt-1 d-block">085</span>
                        </div>
                      </div>
                    </v-card-text>
                  </v-card>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>

          <!-- Modal -->
          <v-dialog v-model="dialog" max-width="550">
            <AddCard @dialogcard="dialog = false" />
          </v-dialog>
        </v-col>

        <v-col cols="12" sm="6">
          <v-btn color="primary" variant="tonal">Back</v-btn>
        </v-col>
        <v-col cols="12" sm="6" class="text-sm-right">
          <v-btn color="primary" @click="thankyou = true">Complete an Order</v-btn>
          <!-- Modal -->
          <v-dialog v-model="thankyou" max-width="750">
            <Thankyou />
          </v-dialog>
        </v-col>
      </v-row>
    </v-col>
    <v-col lg="3" cols="12">
      <h4 class="text-h4">Cart Item</h4>
      <v-table>
        <tbody>
          <tr v-for="item in getCart" :key="item.name">
            <td>
              <div class="my-3 ga-2">
                <img alt="product" class="rounded-md custom-img-box" :src="item.image" />
                <div class="ma-2">
                  <h4 class="text-h5">{{ item.name }}</h4>
                  <small>size: 8 | Color: Dark Red</small>
                </div>
              </div>
            </td>
            <td class="text-h5">${{ item.salePrice * item.qty }}</td>
          </tr>
        </tbody>
      </v-table>
      <OrderSummaryVue />
      <div v-for="address in store.addresses" :key="address.id">
        <div v-if="address.isDefault">
          <AddressCard
            :name="address.name"
            :destination="address.destination"
            :isDefault="address.isDefault"
            :building="address.building"
            :street="address.street"
            :city="address.city"
            :state="address.state"
            :country="address.country"
            :post="address.post"
            :phone="address.phone"
            :showBtn="false"
          />
        </div>
      </div>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.object-card {
  position: relative;
  background-color: rgba(var(--v-theme-primary), 0.9);
  &.object-secondary {
    background-color: rgba(var(--v-theme-secondary), 0.9);
    &:before {
      background-color: rgb(var(--v-theme-secondary200));
    }
    &:after {
      background-color: rgb(var(--v-theme-darksecondary));
    }
    .top-object {
      &::after {
        background-color: rgb(var(--v-theme-secondary));
      }
    }
  }
  &:before {
    content: '';
    position: absolute;
    background-color: rgb(var(--v-theme-primary200));
    width: 60px;
    height: 60px;
    border-radius: 50%;
    right: -20px;
    bottom: 0px;
  }
  &::after {
    content: '';
    position: absolute;
    background-color: rgb(var(--v-theme-darkprimary));
    width: 60px;
    height: 60px;
    border-radius: 50%;
    right: 12px;
    bottom: -30px;
  }
}
.payment-options {
  .v-selection-control {
    .v-label {
      flex-grow: 1;
    }
  }
}
.top-object {
  &::after {
    content: '';
    position: absolute;
    background-color: rgb(var(--v-theme-primary));
    width: 75px;
    height: 75px;
    border-radius: 50%;
    top: -25px;
    left: -25px;
  }
}
html .label-op-1 label {
  opacity: 1 !important;
}
</style>
