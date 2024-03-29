<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';

// icons
import {
  ChartArcsIcon,
  CalendarEventIcon,
  FileDescriptionIcon,
  ThumbDownIcon,
  CoinIcon,
  UserCircleIcon,
  BasketIcon,
  BugIcon,
  FolderIcon,
  WalletIcon,
  MoodSmileIcon,
  ShoppingCartIcon,
  ArrowUpIcon,
  ArrowDownIcon,
  BrandFacebookIcon,
  BrandTwitterIcon,
  BrandLinkedinIcon,
  BrandYoutubeIcon,
  EyeIcon,
  CircleDotIcon,
  HandStopIcon,
  TrophyIcon
} from 'vue-tabler-icons';

const page = ref({ title: 'Statistics' });
const breadcrumbs = ref([
  {
    title: 'Widget',
    disabled: false,
    href: '#'
  },
  {
    title: 'Statistics',
    disabled: true,
    href: '#'
  }
]);

const cards = shallowRef([
  { earn: '$30200', text: 'All Earnings', icon: ChartArcsIcon, color: 'primary' },
  { earn: '145', text: 'Task', icon: CalendarEventIcon, color: 'error' },
  { earn: '290+', text: 'Page Views', icon: FileDescriptionIcon, color: 'success' },
  { earn: '500', text: 'Downloads', icon: ThumbDownIcon, color: 'secondary' }
]);

const twocards = shallowRef([
  { title: 'Revenue', earn: '$42,562', text: '$50,032 Last Month', icon: CoinIcon, color: 'secondary' },
  { title: 'Orders Received', earn: '486', text: '20% Increase', icon: UserCircleIcon, color: 'primary' },
  { title: 'Total Sales', earn: '1641', text: '$1,055 Revenue Generated', icon: BasketIcon, color: 'error' }
]);

const threecards = shallowRef([
  { earn: '6035', text: 'Visitors', icon: UserCircleIcon, color: 'primary' },
  { earn: '19', text: 'Invoices', icon: FileDescriptionIcon, color: 'error' },
  { earn: '63', text: 'Issues', icon: BugIcon, color: 'warning' },
  { earn: '95%', text: 'Projects', icon: FolderIcon, color: 'success' }
]);

const fourcards = shallowRef([
  { earn: '2,672', text: 'Last week users', icon: UserCircleIcon, color: 'secondary' },
  { earn: '$6391', text: 'Total earning', icon: WalletIcon, color: 'primary' },
  { earn: '9,276', text: 'Today visitors', icon: MoodSmileIcon, color: 'success' },
  { earn: '3,619', text: 'New order', icon: ShoppingCartIcon, color: 'error' }
]);

const fivecards = shallowRef([
  { earn: 7658, text: 'Total Paid Users', subtext: '8% less Last 3 Months' },
  { earn: 625, text: 'Order Status', subtext: '6% From Last 3 Months' },
  { earn: 6955, text: 'Unique Visitors', subtext: '10% From Last 6 Months' },
  { earn: 5984, text: 'Monthly Earnings', subtext: '36% From Last 6 Months' }
]);

const sixcards = shallowRef([
  { earn: '1165 +', text: 'Facebook Users', icon: BrandFacebookIcon, color: 'secondary' },
  { earn: '780 +', text: 'Twitter Users', icon: BrandTwitterIcon, color: 'primary' },
  { earn: '998 +', text: 'Linked In Users', icon: BrandLinkedinIcon, color: 'warning' },
  { earn: '650 +', text: 'Youtube Videos', icon: BrandYoutubeIcon, color: 'error' }
]);

const sevencards = shallowRef([
  { earn: '1,563', text: 'Impressions', icon: EyeIcon, color: 'primary', color2: 'lightprimary', duedate: 'May 23 - June 01 (2018)' },
  { earn: '30,564', text: 'Goal', icon: CircleDotIcon, color: 'secondary', color2: 'lightsecondary', duedate: 'May 23 - June 01 (2018)' },
  { earn: '42.6%', text: 'Impact', icon: HandStopIcon, color: 'warning', color2: 'lightwarning', duedate: 'May 23 - June 01 (2018)' }
]);

const eightcards = shallowRef([
  { earn: '532', text: 'Published Project', icon: EyeIcon, color: 'primary' },
  { earn: '4,569', text: 'Completed Task', icon: EyeIcon, color: 'success' },
  { earn: '1,005', text: 'Pending Task', icon: CircleDotIcon, color: 'secondary' },
  { earn: '365', text: 'Issues', icon: HandStopIcon, color: 'error' }
]);

const ninecards = shallowRef([
  { earn: '1,658', text: 'Daily user', icon: UserCircleIcon, color: 'primary' },
  { earn: '1K', text: 'Daily page view', icon: FileDescriptionIcon, color: 'secondary' },
  { earn: '5,678', text: 'Last month visitor', icon: TrophyIcon, color: 'success' }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 1 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" sm="6" lg="3" v-for="(card1, i) in cards" :key="i" :value="card1">
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-text>
            <div class="d-flex align-items-center justify-space-between">
              <div>
                <h3 class="text-h3 mb-2">{{ card1.earn }}</h3>
                <span class="text-medium-emphasis">{{ card1.text }}</span>
              </div>
              <span class="d-flex align-center">
                <component :is="card1.icon" stroke-width="1.5" size="30" :class="'text-' + card1.color" />
              </span>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 2 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" lg="4" v-for="(card2, i) in twocards" :key="i" :value="card2">
      <v-card elevation="0" :class="'bg-' + card2.color">
        <v-card variant="outlined">
          <v-card-text>
            <div class="d-flex align-items-center justify-space-between">
              <div>
                <h5 class="text-h5 mb-2">{{ card2.title }}</h5>
                <h3 class="text-h3 mb-2">{{ card2.earn }}</h3>
                <span class="text-medium-emphasis text-white">{{ card2.text }}</span>
              </div>
              <span class="d-flex align-center">
                <component :is="card2.icon" stroke-width="1.5" size="89" class="text-white opacity-50" />
              </span>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 3 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" sm="6" lg="3" v-for="(card3, i) in threecards" :key="i" :value="card3">
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-text>
            <div class="d-flex align-items-center justify-space-between">
              <div>
                <component :is="card3.icon" stroke-width="1.5" size="24" :class="'text-' + card3.color" />

                <span class="font-weight-bold text-medium-emphasis d-block">{{ card3.text }}</span>
              </div>
              <span class="d-flex align-center">
                <h3 class="text-h3">{{ card3.earn }}</h3>
              </span>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 4 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" sm="6" lg="3" v-for="(card4, i) in fourcards" :key="i" :value="card4">
      <v-card elevation="0">
        <v-card variant="outlined" class="overflow-hidden">
          <v-row>
            <v-col cols="4" :class="'d-flex align-center justify-center pr-0 bg-' + card4.color">
              <div class="d-flex align-center justify-center py-7">
                <component :is="card4.icon" stroke-width="1.5" size="35" class="flex-shrink-0" />
              </div>
            </v-col>
            <v-col cols="8">
              <div class="py-7">
                <h3 class="text-h3 mb-2">{{ card4.earn }}</h3>
                <span class="text-medium-emphasis font-weight-medium d-block">{{ card4.text }}</span>
              </div>
            </v-col>
          </v-row>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 5 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" sm="6" lg="3" v-for="(card5, i) in fivecards" :key="i" :value="card5">
      <v-card elevation="0">
        <v-card variant="outlined" class="text-center">
          <v-card-text>
            <span class="text-medium-emphasis font-weight-bold">{{ card5.text }}</span>
            <h3 class="text-h3 my-2 d-flex align-center justify-center" v-if="card5.earn > 6000">
              <ArrowUpIcon class="text-success" stroke-width="1.5" width="18" />
              {{ card5.earn }}
            </h3>
            <h3 class="text-h3 my-2 d-flex align-center justify-center" v-else>
              <ArrowDownIcon class="text-error" stroke-width="1.5" width="18" />
              {{ card5.earn }}
            </h3>
            <span class="text-disabled font-weight-medium">{{ card5.subtext }}</span>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 6-->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" lg="3" v-for="(card6, i) in sixcards" :key="i" :value="card6">
      <v-card elevation="0" :class="'hover-icon-card bg-' + card6.color">
        <v-card variant="outlined">
          <v-card-text>
            <div class="d-flex align-items-center justify-space-between">
              <div>
                <h3 class="text-h3 text-white mb-2">{{ card6.earn }}</h3>
                <span class="text-medium-emphasis text-white">{{ card6.text }}</span>
              </div>
              <span class="d-flex align-center">
                <component :is="card6.icon" stroke-width="1.5" size="40" class="text-white opacity-50 hover-icon" />
              </span>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 7 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" lg="4" v-for="(card7, i) in sevencards" :key="i" :value="card7">
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-text>
            <div class="d-flex align-items-center justify-space-between">
              <div>
                <span class="text-medium-emphasis font-weight-bold">{{ card7.text }}</span>
                <h3 class="text-h3 my-2">{{ card7.earn }}</h3>
                <span class="text-medium-emphasis">{{ card7.duedate }}</span>
              </div>
              <span class="d-flex align-center">
                <v-btn :class="'text-' + card7.color" :color="card7.color2" icon variant="flat">
                  <component :is="card7.icon" stroke-width="1.5" size="24" />
                </v-btn>
              </span>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 8 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12">
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-row>
            <v-col cols="12" sm="6" lg="3" v-for="(card8, i) in eightcards" :key="i" :value="card8">
              <v-card-text>
                <span class="text-medium-emphasis text-subtitle-2 font-weight-medium">{{ card8.text }}</span>
                <h3 class="text-h3 my-2">{{ card8.earn }}</h3>
                <v-progress-linear model-value="15" :color="card8.color"></v-progress-linear>
              </v-card-text>
            </v-col>
          </v-row>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <!-- --------------------------------------------------------------------- -->
  <!-- Cards 9 -->
  <!-- --------------------------------------------------------------------- -->
  <v-row>
    <v-col cols="12" lg="4" v-for="(card9, i) in ninecards" :key="i" :value="card9">
      <v-card elevation="0" :class="'overflow-hidden position-relative bg-' + card9.color">
        <v-card variant="outlined" class="text-center">
          <component :is="card9.icon" stroke-width="2" size="89" class="text-white opacity-50 icon-scale" />
          <v-card-text>
            <h3 class="text-h3 my-2">{{ card9.earn }}</h3>
            <span>{{ card9.text }}</span>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>

  <v-row>
    <!-- Card 10 -->
    <v-col cols="12" lg="4">
      <v-card elevation="0">
        <v-card variant="outlined">
          <v-card-item>
            <v-card-title class="text-18">Customer satisfaction</v-card-title>
          </v-card-item>
          <v-divider></v-divider>
          <v-card-text class="text-center">
            <h3 class="text-h3 mb-5">89.73%</h3>
            <v-progress-linear model-value="15" color="primary"></v-progress-linear>
            <div class="d-flex flex-row justify-space-between mt-5">
              <div class="text-left">
                <span class="text-medium-emphasis text-subtitle-2">previous</span>
                <h5 class="text-subtitle-1 mt-1">56.75</h5>
              </div>
              <div>
                <span class="text-medium-emphasis text-subtitle-2">change</span>
                <h5 class="text-subtitle-1 mt-1">+12.60</h5>
              </div>
              <div>
                <span class="text-medium-emphasis text-subtitle-2">Trend</span>
                <h5 class="text-subtitle-1 mt-1">23.78</h5>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <!-- Card 11 -->
    <v-col cols="12" lg="4">
      <v-card elevation="0" class="overflow-hidden">
        <v-card variant="outlined">
          <v-row>
            <v-col cols="6" class="pa-9 pb-6 border-blue-right">
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
            <v-col cols="6" class="pa-9 pb-6 pl-4 pl-sm-9">
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
            <v-col cols="6" class="pa-9 pt-6 border-blue-right">
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
            <v-col cols="6" class="pa-9 pt-6 pl-4 pl-sm-9">
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
    </v-col>

    <!-- Card 12 -->
    <v-col cols="12" lg="4" class="border-blue-right">
      <v-card elevation="0">
        <v-card variant="outlined" class="overflow-hidden">
          <v-row>
            <v-col cols="6" class="pa-9">
              <div class="d-flex align-center justify-center">
                <div class="text-center">
                  <h2 class="text-h2 mt-1 mb-n1">19°</h2>
                  <span class="text-medium-emphasis text-subtitle-2 text-uppercase">Sunny</span>
                </div>
              </div>
            </v-col>
            <v-col cols="6" class="pa-9 pl-5 bg-primary text-white">
              <div class="d-flex align-center justify-center">
                <div class="text-center mt-1">
                  <SunHighIcon stroke-width="2" size="34" />
                  <span class="text-white d-block text-subtitle-2 text-uppercase">New York , NY</span>
                </div>
              </div>
            </v-col>
          </v-row>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
