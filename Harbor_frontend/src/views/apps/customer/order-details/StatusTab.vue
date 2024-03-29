<script setup lang="ts"></script>

<template>
  <v-card variant="outlined">
    <v-card-item class="py-4">
      <h4 class="text-h5 text-uppercase">Order Status</h4>
    </v-card-item>
    <v-divider></v-divider>
    <v-card-text>
      <v-row>
        <v-col cols="12" sm="3">
          <h5 class="text-h5 mb-1">Order Place Date</h5>
          <span class="text-subtitle-1 text-medium-emphasis">10th Mar, 2021</span>
        </v-col>
        <v-col cols="12" sm="2">
          <h5 class="text-h5 mb-1">Order Status</h5>
          <span class="text-subtitle-1 text-medium-emphasis">Processing</span>
        </v-col>
        <v-col cols="12" sm="3">
          <h5 class="text-h5 mb-1">Delivery Option</h5>
          <span class="text-subtitle-1 text-medium-emphasis">Fedex Express Delivery</span>
        </v-col>
        <v-col cols="12" sm="2">
          <h5 class="text-h5 mb-1">Payment</h5>
          <span class="text-subtitle-1 text-medium-emphasis">Credit Card</span>
        </v-col>
        <v-col cols="12" sm="2">
          <h5 class="text-h5 mb-1">Order Amount</h5>
          <span class="text-subtitle-1 text-medium-emphasis">$90,020</span>
        </v-col>
      </v-row>
      <v-row class="mt-5">
        <v-col cols="12" lg="9">
          <v-row class="justify-center">
            <v-col cols="12" lg="10">
              <v-timeline align="start" class="mt-5" side="end" truncate-line="start">
                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <div class="text-right">
                      <span class="text-subtitle-2 font-weight-medium">Order Placed</span>
                      <h6 class="text-subtitle-1 text-medium-emphasis font-weight-medium">12 jun</h6>
                    </div>
                  </template>
                  <div>
                    <h5 class="pb-4 text-subtitle-1 font-weight-regular">The order was validated.</h5>
                    <v-divider></v-divider>
                    <h5 class="py-4 text-subtitle-1 font-weight-regular">The order was placed.</h5>
                    <v-divider></v-divider>
                    <h5 class="py-4 text-subtitle-1 font-weight-regular">The order was placed.</h5>
                  </div>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <div class="text-right">
                      <span class="text-subtitle-2 font-weight-medium">Order Processing</span>
                      <h6 class="text-subtitle-1 text-medium-emphasis font-weight-medium">14 jun</h6>
                    </div>
                  </template>
                  <div>
                    <h5 class="text-subtitle-1 font-weight-regular">
                      Payment transaction [method: Credit Card, type: sale, amount: $90,020, status: Processing ]
                    </h5>
                  </div>
                </v-timeline-item>
                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <div class="text-right">
                      <span class="text-subtitle-2 font-weight-medium">Order Shipping</span>
                      <h6 class="text-subtitle-1 text-medium-emphasis font-weight-medium">16 Jun</h6>
                    </div>
                  </template>
                  <div>
                    <h5 class="text-subtitle-1 font-weight-regular">Sent a notification to the client by e-mail.</h5>
                  </div>
                </v-timeline-item>
                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <div class="text-right">
                      <span class="text-subtitle-2 font-weight-medium">Order Delivered</span>
                      <h6 class="text-subtitle-1 text-medium-emphasis font-weight-medium">17 jun</h6>
                    </div>
                  </template>
                  <div>
                    <h5 class="text-subtitle-1 font-weight-regular">Order Delivered</h5>
                  </div>
                </v-timeline-item>
              </v-timeline>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12" lg="3">
          <v-textarea variant="outlined" name="input-7-1" rows="9" color="primary" filled label="Write a review" auto-grow> </v-textarea>
          <v-btn color="primary">Post Review</v-btn>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
