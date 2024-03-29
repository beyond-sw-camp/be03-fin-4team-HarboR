<script setup lang="ts">
import { ref } from 'vue';

// icons
import { LockIcon, CreditCardIcon } from 'vue-tabler-icons';

const textcardname = ref('Selena Litten');
const textcardnumber = ref('4012 8888 8888 1881');
const textdate = ref('10/22');
const textcode = ref('123');
const textmail = ref('demo@company.paypal.com');

const payment_choose = ref('visa');
</script>

<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-radio-group v-model="payment_choose" inline>
          <v-radio color="primary" label="Visa Credit/Debit Card" value="visa"></v-radio>
          <v-radio color="primary" label="Paypal" value="paypal"></v-radio>
        </v-radio-group>
      </v-col>
    </v-row>
    <v-row v-if="payment_choose === 'visa'">
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textcardname" label="Name on Card" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textcardnumber" label="Card Number" variant="outlined" hide-details>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textdate" label="Expiry Date" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <v-text-field type="text" color="primary" v-model="textcode" label="CCV Code" variant="outlined" hide-details> </v-text-field>
      </v-col>
      <v-col cols="12">
        <div class="d-flex align-center ga-4">
          <LockIcon width="40" height="40" class="text-primary" />
          <div class="mt-1">
            <h5 class="text-subtitle-1">Secure Checkout</h5>
            <span class="text-subtitle-2 text-disabled font-weight-medium">Secure by Google.</span>
          </div>
        </div>
        <v-btn color="primary" variant="outlined" size="large" class="mt-6 text-subtitle-1">
          <template v-slot:prepend>
            <CreditCardIcon width="24" stroke-width="1.5" />
          </template>
          Add New Card
        </v-btn>
      </v-col>
    </v-row>
    <div v-else>
      <v-row>
        <v-col cols="12">
          <v-text-field variant="outlined" color="primary" label="Paypal Mail" v-model="textmail" hide-details></v-text-field>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
