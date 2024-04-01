<script setup lang="ts">
import { ref } from 'vue';

import Logo from '../../../../layouts/full/logo/LogoMain.vue';

const invoices = ref([
  {
    name: 'Logo Design',
    quantity: 6,
    amount: 200
  },
  {
    name: 'Landing Page',
    quantity: 7,
    amount: 100
  },
  {
    name: 'Admin Template',
    quantity: 5,
    amount: 150
  }
]);
</script>

<template>
  <v-row class="justify-center">
    <v-col cols="12" lg="8">
      <v-card variant="outlined" id="printMe">
        <v-card-item class="py-4">
          <div class="d-md-flex align-center justify-space-between">
            <h4 class="text-h4">Invoice #125863478945</h4>
            <div class="d-md-block d-none">
              <Logo />
            </div>
          </div>
        </v-card-item>
        <v-divider></v-divider>
        <v-card-text>
          <h5 class="text-h5 mb-1">Demo Company Inc.</h5>
          <span class="text-subtitle-1 text-medium-emphasis">1065 Mandan Road, Columbia MO,</span> <br />
          <span class="text-subtitle-1 text-medium-emphasis">Missouri. (123)-65202</span><br />
          <a href="/" class="text-primary">demo@company.com</a><br />
          <span class="text-subtitle-1 text-medium-emphasis">(+91) 9999 999 999</span>
          <v-row class="mt-4">
            <v-col cols="12" md="4">
              <h5 class="text-h5 mb-1">Customer :</h5>
              <h5 class="text-h5 mb-1 mt-3">John Doe</h5>
              <span class="text-subtitle-1 text-medium-emphasis">1065 Mandan Road, Columbia MO,</span>
              <br />
              <span class="text-subtitle-1 text-medium-emphasis">Missouri. (123)-65202</span><br />
              <span class="text-subtitle-1 text-medium-emphasis">(+61) 9999 567 891</span><br />
              <a href="/" class="text-primary">demo@company.com</a>
            </v-col>
            <v-col cols="12" md="4">
              <h5 class="text-h5 mb-1">Order Details :</h5>
              <v-row class="mt-3">
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">Date :</v-col>
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">November 14</v-col>
              </v-row>
              <v-row class="">
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">Status :</v-col>
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">
                  <v-chip color="warning" size="small" variant="outlined">Pending</v-chip>
                </v-col>
              </v-row>
              <v-row class="">
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">Order Id :</v-col>
                <v-col cols="6" class="text-medium-emphasis text-subtitle-1">
                  <a href="/">#146859</a>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <v-table class="mt-5 text-no-wrap">
            <thead>
              <tr>
                <th class="text-left text-uppercase text-subtitle-1">Description</th>
                <th class="text-left text-uppercase text-subtitle-1">Quantity</th>
                <th class="text-left text-uppercase text-subtitle-1">Amount</th>
                <th class="text-left text-uppercase text-subtitle-1">Total</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="invoice in invoices" :key="invoice.name">
                <td class="py-3">
                  <h5 class="text-subtitle-1">{{ invoice.name }}</h5>
                  <p class="mt-1">lorem ipsum dolor sit amat, connecter adieu</p>
                </td>
                <td class="py-3">{{ invoice.quantity }}</td>
                <td class="py-3">${{ invoice.amount }}</td>
                <td class="py-3">${{ invoice.amount * invoice.quantity }}</td>
              </tr>
            </tbody>
          </v-table>
          <v-sheet class="bg-lightprimary pa-2 pa-sm-6 mt-6" rounded="sm">
            <v-row class="justify-end">
              <v-col cols="6" sm="3" lg="2" class="text-right">
                <h5 class="py-2 text-subtitle-1">Sub Total :</h5>
                <h5 class="py-2 text-subtitle-1">Taxes (10%) :</h5>
                <h5 class="py-2 text-subtitle-1 text-no-wrap">Discount (5%) :</h5>
                <h5 class="py-2 text-subtitle-1 text-primary mt-7">Total</h5>
              </v-col>
              <v-col cols="6" sm="3" lg="2" class="text-right">
                <h5 class="py-2 text-subtitle-1 text-disabled">$2,650.00</h5>
                <h5 class="py-2 text-subtitle-1 text-disabled">$265.00</h5>
                <h5 class="py-2 text-subtitle-1 text-disabled">$145.75</h5>
                <h5 class="py-2 text-subtitle-1 text-primary mt-7">$2,769.25</h5>
              </v-col>
            </v-row>
          </v-sheet>

          <h4 class="text-h5 mb-1 mt-4">Terms and Condition :</h4>
          <p class="text-subtitle-1 text-medium-emphasis font-weight-regular">
            lorem ipsum dolor sit connecter adieu siccing eliot, sed do elusion tempore incident ut laborer et dolors magna aliquot.
          </p>
        </v-card-text>
      </v-card>
      <div class="text-center mt-6">
        <v-btn color="primary" v-print="'#printMe'">Print</v-btn>
      </div>
    </v-col>
  </v-row>
</template>
