<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { MinusIcon } from 'vue-tabler-icons';

const isAnnual = ref('Monthly');
const page = ref({ title: 'Pricing 2 Page' });
const breadcrumbs = ref([
  {
    title: 'Pages',
    disabled: false,
    href: '#'
  },
  {
    title: 'pricing Page',
    disabled: true,
    href: '#'
  }
]);
type plan = {
  id: number;
  popular: boolean;
  title: string;
  price: {
    monthly: number;
    yearly: number;
  };
};

const plans = ref<plan[]>([
  {
    id: 1,
    popular: false,
    title: 'Starters',
    price: {
      monthly: 25,
      yearly: 225
    }
  },
  {
    id: 2,
    popular: true,
    title: 'Scalability',
    price: {
      monthly: 125,
      yearly: 825
    }
  },
  {
    id: 3,
    popular: false,
    title: 'Enterprise',
    price: {
      monthly: 225,
      yearly: 1025
    }
  }
]);

type planListItem = {
  type: string;
  label: string;
  permission?: number[];
};

const planList = ref<planListItem[]>([
  {
    type: 'group',
    label: 'Features'
  },
  {
    type: 'list',
    label: 'Only 1 User uses',
    permission: [1, 1, 1]
  },
  {
    type: 'list',
    label: '10 Projects for',
    permission: [0, 1, 1]
  },
  {
    type: 'list',
    label: 'Unlimited Bandwidth',
    permission: [0, 0, 1]
  },
  {
    type: 'list',
    label: 'Unlimited Data',
    permission: [0, 0, 1]
  },
  {
    type: 'group',
    label: 'Storage & Security'
  },
  {
    type: 'list',
    label: '5GB of Storage',
    permission: [0, 1, 1]
  },
  {
    type: 'list',
    label: 'Fully Security Suite',
    permission: [0, 0, 1]
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <div class="text-center pa-8 mb-5">
        <v-btn-toggle v-model="isAnnual" tile color="primary" group>
          <v-btn value="Monthly"> Monthly </v-btn>
          <v-btn value="Annual"> Annual </v-btn>
        </v-btn-toggle>
      </div>
      <v-card elevation="0" class="overflow-auto">
        <v-card variant="outlined" class="pricingBox">
          <!---This is for the title-->
          <v-row justify="end" no-gutters>
            <v-col xs="3"></v-col>
            <v-col md="3" xs="3" v-for="plan in plans" :key="plan.id" class="position-relative overflow-hidden">
              <div class="text-center borderLeft titleBox">
                <h3 class="text-primary">{{ plan.title }}</h3>
                <h2 class="PriceFont" v-if="isAnnual == 'Annual'">
                  <sup>$</sup>
                  <b>{{ plan.price.yearly }}</b>
                  <span>/Yearly</span>
                </h2>
                <h2 class="PriceFont" v-else>
                  <sup>$</sup>
                  <b>{{ plan.price.monthly }}</b>
                  <span>/Monthly</span>
                </h2>
              </div>
              <div v-if="plan.popular" class="popular">Popular</div>
            </v-col>
          </v-row>
          <!---End the title-->
          <!---Plan Listing-->
          <v-row no-gutters v-for="(list, index) in planList" :key="index">
            <v-col
              :md="list.type == 'group' ? '12' : '3'"
              :xs="list.type == 'group' ? '12' : '3'"
              class="position-relative overflow-hidden"
            >
              <div v-if="list.type == 'group'" class="planGroup">
                {{ list.label }}
              </div>
              <div v-else class="planLabel">{{ list.label }}</div>
            </v-col>
            <v-col v-for="(item, index) in list.permission" :key="index">
              <div class="PlanIcon">
                <v-icon color="success" v-if="item === 1">mdi-check-circle</v-icon>
                <div class="text-disabled" v-else><MinusIcon /></div>
              </div>
            </v-col>
          </v-row>
          <!---End Plan Listing-->
          <!---Plan Button-->
          <v-row no-gutters class="text-center">
            <v-col md="3" xs="3"> </v-col>
            <v-col md="3" xs="3" class="planButton">
              <v-btn variant="outlined" color="primary"> Order Now </v-btn>
            </v-col>
            <v-col md="3" xs="3" class="planButton">
              <v-btn color="secondary"> Order Now </v-btn>
            </v-col>
            <v-col md="3" xs="3" class="planButton">
              <v-btn variant="outlined" color="primary"> Order Now </v-btn>
            </v-col>
          </v-row>
          <!---End Plan Button-->
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.pricingBox {
  min-width: 850px;
  overflow: auto;
}
.borderLeft {
  border-left: 1px solid rgba(0, 0, 0, 0.08);
}
.planGroup {
  padding: 10px 25px;
  font-weight: 500;
  background-color: rgba(0, 0, 0, 0.05);
}
.planButton {
  border-left: 1px solid rgba(0, 0, 0, 0.08);
  .v-btn {
    margin: 30px 0;
  }
}
.planLabel {
  padding: 15px 25px;
  height: 55px;
  font-size: 0.875rem;
  border-bottom: 1px solid rgba(0, 0, 0, 0.08);
}
.PlanIcon {
  padding: 13px 25px;
  height: 55px;
  justify-content: center;
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.08);
}
.popular {
  display: inline-block;
  padding: 40px 40px 5px;
  font-size: 0.8125rem;
  position: absolute;
  top: -24px;
  right: -55px;
  color: white;
  transform: rotate(45deg);
  background: rgb(var(--v-theme-secondary));
}
.titleBox {
  position: relative;
  padding: 30px;
}
.PriceFont {
  font-size: 1.5rem;
  margin-top: 20px;
  span {
    font-size: 1.25rem;
  }
}
</style>
