<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

const page = ref({ title: 'Animation Page' });
const breadcrumbs = ref([
  {
    title: 'Utilities',
    disabled: false,
    href: '#'
  },
  {
    title: 'Animation Page',
    disabled: true,
    href: '#'
  }
]);
const fadeTran = ref(true);
const expandTran = ref(true);
const fabTran = ref(true);
const slidxTran = ref(true);
const slidyTran = ref(true);
const scrolTran = ref(true);
const scrolyTran = ref(true);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Animation">
        <v-row>
          <v-col md="6">
            <UiChildCard title="Slide X Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="slidxTran = !slidxTran"> Slide X Transition </v-btn>
              <br />
              <v-slide-x-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="slidxTran" class="rounded-md mt-8 w-75" />
              </v-slide-x-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Slide y Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="slidyTran = !slidyTran"> Slide Y Transition </v-btn>
              <br />
              <v-slide-y-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="slidyTran" class="rounded-md mt-8 w-75" />
              </v-slide-y-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Scroll x Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="scrolTran = !scrolTran"> Scroll x Transition </v-btn>
              <br />
              <v-scroll-x-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="scrolTran" class="rounded-md mt-8 w-75" />
              </v-scroll-x-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Scroll y Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="scrolyTran = !scrolyTran"> Scroll Y Transition </v-btn>
              <br />
              <v-scroll-y-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="scrolyTran" class="rounded-md mt-8 w-75" />
              </v-scroll-y-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Expand Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="expandTran = !expandTran"> Expand-transition </v-btn>
              <br />
              <v-expand-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="expandTran" class="rounded-md mt-8 w-75" />
              </v-expand-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Fab Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="fabTran = !fabTran"> Fab-transition </v-btn>
              <br />
              <v-fab-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="fabTran" class="rounded-md mt-8 w-75" />
              </v-fab-transition>
            </UiChildCard>
          </v-col>
          <v-col md="6">
            <UiChildCard title="Fade Transition">
              <v-btn color="primary" variant="outlined" class="ma-2" @click="fadeTran = !fadeTran"> Fade-transition </v-btn>
              <br />
              <v-fade-transition>
                <img src="@/assets/images/background/img-profile-bg.png" alt="berry" v-show="fadeTran" class="rounded-md mt-8 w-75" />
              </v-fade-transition>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
