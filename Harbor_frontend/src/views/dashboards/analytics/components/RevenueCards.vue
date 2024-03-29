<script setup lang="ts">
import { shallowRef } from 'vue';
import { CoinIcon, UserCircleIcon } from 'vue-tabler-icons';

const twocards = shallowRef([
  { title: 'Revenue', earn: '$42,562', text: '$50,032 Last Month', icon: CoinIcon, color: 'secondary' },
  { title: 'Orders Received', earn: '486', text: '20% Increase', icon: UserCircleIcon, color: 'primary' }
]);
</script>

<template>
  <v-row>
    <v-col cols="12" sm="6" v-for="(card2, i) in twocards" :key="i" :value="card2">
      <v-card elevation="0" :class="'mb-6 bg-' + card2.color">
        <v-card variant="outlined" class="border-0">
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
</template>
