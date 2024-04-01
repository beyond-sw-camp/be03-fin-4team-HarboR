<script setup lang="ts">
import { onMounted, ref } from 'vue';
import AddressCard from './AddressCard.vue';
import AddAddress from './AddAddress.vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import OrderSummary from './OrderSummary.vue';

const store = useEcomStore();
onMounted(() => {
  store.fetchAddress();
});
const dialog = ref(false);
</script>

<template>
  <v-row>
    <v-col lg="8" cols="12" md="12">
      <div class="d-flex align-center my-5">
        <h4 class="text-h5">Billing Address</h4>
        <v-btn size="small" color="primary" class="ml-auto" @click="dialog = true"><PlusIcon size="16" /> Add Address</v-btn>
      </div>
      <v-row>
        <v-col lg="6" md="12" cols="12" v-for="address in store.addresses" :key="address.id">
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
            :showBtn="true"
          />
        </v-col>
        <v-col lg="12">
          <div class="p-3">
            <OrderSummary />
          </div>
        </v-col>
      </v-row>
    </v-col>
    <v-col lg="4" cols="12" md="12">
      <v-card elevation="0" variant="outlined">
        <v-card-text>
          <div class="d-flex align-center ga-4 mb-4">
            <UserIcon class="text-primary" />
            <h3 class="text-h3">Delia Pope</h3>
          </div>
          <small class="text-medium-emphasis">Email</small>
          <h6 class="text-h5 mb-4">deliaturewpo@company.com</h6>
          <small class="text-medium-emphasis">Contact</small>
          <h6 class="text-h5 mb-4">(980) 473-2942</h6>
          <small class="text-medium-emphasis">No. of order</small>
          <h6 class="text-h5 mb-4">19</h6>
        </v-card-text>
      </v-card>
      <div v-for="address in store.addresses" :key="address.name" class="mt-7">
        <template v-if="address.isDefault">
          <h4 class="text-h3 mb-4">Shipping Address</h4>
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
          />
        </template>
      </div>
    </v-col>
  </v-row>
  <v-dialog v-model="dialog" max-width="600">
    <AddAddress @handledialog="dialog = false" />
  </v-dialog>
</template>
