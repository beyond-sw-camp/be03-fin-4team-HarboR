<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { BusIcon, MotorbikeIcon, ShipIcon } from 'vue-tabler-icons';

const page = ref({ title: 'Pricing Page' });
const breadcrumbs = ref([
  {
    title: 'Pages',
    disabled: false,
    href: '#'
  },
  {
    title: 'Pricing Page',
    disabled: true,
    href: '#'
  }
]);

const plans = shallowRef([
  {
    active: false,
    icon: MotorbikeIcon,
    title: 'Standard',
    description:
      'Create one end product for a client, transfer that end product to your client, charge them for your services. The license is then transferred to the client.',
    price: 69,
    permission: [0, 1]
  },
  {
    active: true,
    icon: BusIcon,
    title: 'Standard Plus',
    description:
      'Create one end product for a client, transfer that end product to your client, charge them for your services. The license is then transferred to the client.',
    price: 129,
    permission: [0, 1, 2, 3]
  },
  {
    active: false,
    icon: ShipIcon,
    title: 'Extended',
    description:
      'You are licensed to use the CONTENT to create one end product for yourself or for one client (a “single application”), and the end product may be sold or distributed for free.',
    price: 599,
    permission: [0, 1, 2, 3, 5]
  }
]);

const planList = ref([
  'One End Product', // 0
  'No attribution required', // 1
  'TypeScript', // 2
  'Figma Design Resources', // 3
  'Create Multiple Products', // 4
  'Create a SaaS Project', // 5
  'Resale Product', // 6
  'Separate sale of our UI Elements?' // 7
]);
//const getcurrent = customizer.actTheme;
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="4" v-for="(plan, index) in plans" v-bind:key="index">
      <v-card elevation="0">
        <v-card variant="outlined" :style="plan.active ? 'border:2px solid rgb(var(--v-theme-secondary))' : ''">
          <v-card-text class="text-center">
            <div class="circleIcon">
              <component :is="plan.icon" size="40"></component>
            </div>
            <h1 class="PlanTitle">{{ plan.title }}</h1>
            <p class="py-8">{{ plan.description }}</p>
            <h2 class="PriceFont"><sup>$</sup>{{ plan.price }}<span>/Lifetime</span></h2>
            <v-list class="PricingList text-left">
              <v-list-item
                class="withBorder"
                :disabled="!plan.permission.includes(index)"
                v-for="(list, index) in planList"
                :key="index"
                :class="!plan.permission.includes(index) ? 'text-disabled' : ''"
              >
                <v-icon color="success" v-if="plan.permission.includes(index)">mdi-check</v-icon>
                <v-icon v-else aria-disabled="true">mdi-check</v-icon>
                {{ list }}
              </v-list-item>
            </v-list>
            <v-btn variant="outlined" color="primary" class="mt-4">Order Now</v-btn>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
<style scoped lang="scss">
.PlanTitle {
  position: relative;
  font-size: 1.5625rem;
  margin: 15px 0;
  &:after {
    position: absolute;
    content: '';
    bottom: -25px;
    left: calc(50% - 25px);
    width: 50px;
    height: 4px;
    background: rgb(var(--v-theme-primary));
    border-radius: 3px;
  }
}
.PriceFont {
  font-size: 2.1875rem;
  padding: 30px 0;
  span {
    font-size: 1.25rem;
  }
}
.withBorder {
  border-bottom: 1px solid rgba(0, 0, 0, 0.08);
}
.circleIcon {
  background-color: rgb(var(--v-theme-lightprimary));
  width: 80px;
  height: 80px;
  margin: 0 auto;
  line-height: 80px;
  color: rgb(var(--v-theme-primary));
  border-radius: 100%;
  margin-bottom: 35px;
  align-items: center;
  justify-content: center;
  display: flex;
}
</style>
