<script setup lang="ts">
// imported widget cards
import WidgetCardv2 from '@/components/shared/WidgetCardv2.vue';

// imported components
import MarketShare from '../../widgets/chart/components/MarketShare.vue';
import TotalRevenue from '../../widgets/data/components/TotalRevenue.vue';
import LatestCustomer from '../../widgets/data/components/LatestCustomer.vue';
import SideIconCards from './components/SideIconCards.vue';
import RevenueCards from './components/RevenueCards.vue';
</script>

<template>
  <v-row>
    <!-- column 1 -->
    <v-col cols="12" lg="8">
      <!-- -------------------------------------------------------------------- -->
      <!-- Market Share -->
      <!-- -------------------------------------------------------------------- -->
      <MarketShare />

      <!-- -------------------------------------------------------------------- -->
      <!-- Revenue Cards -->
      <!-- -------------------------------------------------------------------- -->
      <RevenueCards />

      <!-- -------------------------------------------------------------------- -->
      <!-- Latest Customers -->
      <!-- -------------------------------------------------------------------- -->
      <WidgetCardv2 title="Latest Customers">
        <LatestCustomer />

        <template v-slot:footer>
          <div class="text-right mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Latest Customers </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>
    <!-- column 2 -->
    <v-col cols="12" md="4">
      <!-- -------------------------------------------------------------------- -->
      <!-- Shares -->
      <!-- -------------------------------------------------------------------- -->
      <v-card elevation="0" class="mb-6 overflow-hidden">
        <v-card variant="outlined">
          <v-row>
            <v-col cols="6" sm="6" class="pa-9 pb-6 border-blue-right">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <ShareIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">1000</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">Shares</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-col cols="6" sm="6" class="pa-9 pb-6 pl-4 pl-sm-9">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <RouterIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">600</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">NETWORK</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>

            <v-divider></v-divider>
            <v-col cols="6" sm="6" class="pa-9 pt-6 border-blue-right">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <FlowerIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">3550</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">RETURNS</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>
            <v-col cols="6" sm="6" class="pa-9 pt-6 pl-4 pl-sm-9">
              <v-card-text class="pa-0">
                <div class="d-flex align-center">
                  <v-btn class="text-primary" color="lightprimary" icon variant="flat">
                    <BasketIcon stroke-width="1.5" size="24" />
                  </v-btn>

                  <div class="ml-6 text-center">
                    <h5 class="text-subtitle-1 mt-1">100%</h5>
                    <span class="text-medium-emphasis text-subtitle-2 text-uppercase">Order</span>
                  </div>
                </div>
              </v-card-text>
            </v-col>
          </v-row>
        </v-card>
      </v-card>

      <!-- -------------------------------------------------------------------- -->
      <!-- Total Revenue -->
      <!-- -------------------------------------------------------------------- -->
      <WidgetCardv2 title="Total Revenue" hideaction>
        <TotalRevenue />
      </WidgetCardv2>

      <SideIconCards />
    </v-col>
  </v-row>
</template>
