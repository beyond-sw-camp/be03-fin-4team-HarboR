<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Timeline' });
const breadcrumbs = ref([
  {
    title: 'Advance',
    disabled: false,
    href: '#'
  },
  {
    title: 'Timeline',
    disabled: true,
    href: '#'
  }
]);

// timeline1 data

const time2 = ref([
  {
    color: 'primary',
    text: 'Eat'
  },
  {
    color: 'secondary',
    text: 'Code'
  },
  {
    color: 'error',
    text: 'Sleep'
  },
  {
    color: 'warning',
    text: 'Repeat'
  }
]);

const time3 = ref([
  {
    color: 'secondary',
    text: 'Eat',
    icon: 'mdi-food',
    time: '09:30 am',
    subtext: 'Because you need strength'
  },
  {
    color: 'primary',
    text: 'Code',
    icon: 'mdi-laptop',
    time: '10:00 am',
    subtext: "Because it's awesome!"
  },
  {
    color: 'secondary',
    text: 'Sleep',
    icon: 'mdi-sleep',
    time: '09:30 pm',
    subtext: 'Because you need rest'
  },
  {
    color: 'primary',
    text: 'Repeat',
    icon: 'mdi-repeat',
    time: '6:30 am',
    subtext: 'Because this is the life you love!'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Timeline">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic">
              <v-timeline side="end" align="start">
                <v-timeline-item dot-color="primary" size="x-small">
                  <div class="text-subtitle-1">Eat</div>
                </v-timeline-item>

                <v-timeline-item dot-color="secondary" size="x-small">
                  <div class="text-subtitle-1">Code</div>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <div class="text-subtitle-1">Sleep</div>
                </v-timeline-item>

                <v-timeline-item dot-color="secondary" size="x-small">
                  <div class="text-subtitle-1">Repeat</div>
                </v-timeline-item>
              </v-timeline>
            </UiChildCard>
          </v-col>
          <!-- Alternating -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Alternating">
              <v-timeline align="start">
                <v-timeline-item v-for="(time, i) in time2" :key="i" :dot-color="time.color" size="x-small">
                  <v-card-title class="text-subtitle-1">
                    {{ time.text }}
                  </v-card-title>
                </v-timeline-item>
              </v-timeline>
            </UiChildCard>
          </v-col>
          <!-- Dot Color -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Dot Color">
              <v-timeline side="end" align="start" class="truncate-timeline">
                <v-timeline-item dot-color="primary" size="x-small">
                  <div class="d-flex">
                    <strong class="mr-4">5pm</strong>
                    <div>
                      <strong class="text-truncate">New Icon</strong>
                      <div class="text-caption">Mobile App</div>
                    </div>
                  </div>
                </v-timeline-item>

                <v-timeline-item dot-color="secondary" size="x-small">
                  <div class="d-flex">
                    <strong class="mr-4">3-4pm</strong>
                    <div>
                      <strong class="text-truncate">Design Stand Up</strong>
                      <div class="text-caption mb-2">Hangouts</div>
                    </div>
                  </div>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <div class="d-flex">
                    <strong class="mr-4">12pm</strong>
                    <div>
                      <strong class="text-truncate">Lunch break</strong>
                    </div>
                  </div>
                </v-timeline-item>

                <v-timeline-item dot-color="secondary" size="x-small">
                  <div class="d-flex">
                    <strong class="mr-4">9-11am</strong>
                    <div>
                      <strong class="text-truncate">Finish Home Screen</strong>
                      <div class="text-caption">Web App</div>
                    </div>
                  </div>
                </v-timeline-item>
              </v-timeline>
            </UiChildCard>
          </v-col>
          <!-- Opposite Content -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Opposite Content">
              <v-timeline truncate-line="start">
                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">09:30 am</span>
                  </template>
                  <span class="font-weight-medium">Eat</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">10:00 am</span>
                  </template>
                  <span class="font-weight-medium">Code</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">12:00 am </span>
                  </template>
                  <span class="font-weight-medium">Sleep</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">9:00 am</span>
                  </template>
                  <span class="font-weight-medium">Repeat</span>
                </v-timeline-item>
              </v-timeline>
            </UiChildCard>
          </v-col>
          <!-- Line inset -->
          <v-col cols="12" lg="8">
            <UiChildCard title="Line inset">
              <v-timeline truncate-line="both" direction="horizontal" line-inset="12" class="overflow-auto">
                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">09:30 am</span>
                  </template>
                  <span class="font-weight-medium">Eat</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">10:00 am</span>
                  </template>
                  <span class="font-weight-medium">Code</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">12:00 am </span>
                  </template>
                  <span class="font-weight-medium">Sleep</span>
                </v-timeline-item>

                <v-timeline-item dot-color="primary" size="x-small">
                  <template v-slot:opposite>
                    <span class="text-medium-emphasis font-weight-medium">9:00 am</span>
                  </template>
                  <span class="font-weight-medium">Repeat</span>
                </v-timeline-item>
              </v-timeline>
            </UiChildCard>
          </v-col>
          <!-- Customized -->
          <v-col cols="12">
            <UiChildCard title="Customized">
              <div class="overflow-auto">
                <v-timeline align="start">
                  <v-timeline-item v-for="(time, i) in time3" :key="i" :dot-color="time.color" fill-dot :icon="time.icon">
                    <v-card>
                      <template>
                        <span class="text-medium-emphasis font-weight-medium">{{ time.time }}</span>
                      </template>
                      <div class="bg-lightprimary rounded-md pa-4">
                        <div class="text-h5">{{ time.text }}</div>
                        <p class="text-medium-emphasis">
                          {{ time.subtext }}
                        </p>
                      </div>
                    </v-card>
                  </v-timeline-item>
                </v-timeline>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style lang="scss">
.truncate-timeline {
  .text-truncate {
    @media (max-width: 480px) {
      max-width: 70px;
      display: inline-block;
    }
  }
}
</style>