<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { DotsIcon, MapPinIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Cards' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Cards',
    disabled: true,
    href: '#'
  }
]);

// dropdown data
const actionDD = ref([{ title: 'Edit' }, { title: 'Delete' }]);

// color card data
const colors = ref([
  {
    color: 'primary'
  },
  {
    color: 'secondary'
  },
  {
    color: 'error'
  },
  {
    color: 'success'
  },
  {
    color: 'warning'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Cards">
        <v-row>
          <!-- Basic Card Style 3 -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Card Style 3">
              <v-card variant="outlined" class="card-hover-border bg-gray100">
                <v-card-text>
                  <div class="d-flex align-start">
                    <img src="@/assets/images/users/avatar-6.png" width="60" />
                    <div class="ml-auto">
                      <v-menu>
                        <template v-slot:activator="{ props }">
                          <v-btn size="x-small" v-bind="props" variant="text">
                            <DotsIcon width="20" stroke-width="1.5" />
                          </v-btn>
                        </template>
                        <v-list>
                          <v-list-item v-for="(item, index) in actionDD" :key="index" :value="index" color="secondary">
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                    </div>
                  </div>
                  <div class="d-flex align-center justify-space-between mt-3 mb-4">
                    <h4 class="text-h4">Joanne</h4>
                    <v-chip color="success" size="small">Active</v-chip>
                  </div>
                  <div class="d-flex flex-wrap flex-lg-nowrap ga-4">
                    <v-btn variant="tonal" color="facebook" class="flex-fill">
                      <v-icon>mdi-facebook</v-icon>
                    </v-btn>
                    <v-btn variant="tonal" color="twitter" class="flex-fill">
                      <v-icon>mdi-twitter</v-icon>
                    </v-btn>
                    <v-btn variant="tonal" color="linkedin" class="flex-fill">
                      <v-icon>mdi-linkedin</v-icon>
                    </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Basic Card Style 4 -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Card Style 4">
              <v-card variant="outlined" class="card-hover-border bg-gray100">
                <v-card-text>
                  <div class="d-flex align-start">
                    <img src="@/assets/images/users/avatar-4.png" width="40" />
                    <div class="ml-3">
                      <h4 class="text-h5 mt-n1">Henderson</h4>
                      <div class="text-medium-emphasis text-subtitle-2 d-flex align-center">
                        <MapPinIcon size="16" stroke-width="1.5" class="mr-1" />
                        <span class="text-truncate w-50">South Antonina</span>
                      </div>
                    </div>
                    <div class="ml-auto">
                      <v-menu>
                        <template v-slot:activator="{ props }">
                          <v-btn size="x-small" v-bind="props" variant="text">
                            <DotsIcon width="20" stroke-width="1.5" />
                          </v-btn>
                        </template>
                        <v-list>
                          <v-list-item v-for="(item, index) in actionDD" :key="index" :value="index" color="secondary">
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                    </div>
                  </div>
                  <div class="d-flex ga-4 mt-3">
                    <v-btn variant="outlined" color="secondary" class="flex-fill">
                      <v-tooltip activator="parent" location="top">Video Call</v-tooltip>
                      <v-icon>mdi-video</v-icon>
                    </v-btn>
                    <v-btn variant="outlined" color="primary" class="flex-fill">
                      <v-tooltip activator="parent" location="top">Message</v-tooltip>
                      <v-icon>mdi-message</v-icon>
                    </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Basic Card Style 5 -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Card Style 5">
              <v-card variant="outlined" class="card-hover-border bg-gray100">
                <v-card-text>
                  <div class="d-flex align-start">
                    <img src="@/assets/images/users/avatar-2.png" width="40" />
                    <div class="ml-3">
                      <h4 class="text-h5 mt-n1">Henderson</h4>
                      <div class="text-medium-emphasis text-subtitle-2 d-flex align-center">
                        <MapPinIcon size="16" stroke-width="1.5" class="mr-1" />
                        <span class="text-truncate w-50">South Antonina</span>
                      </div>
                    </div>
                    <div class="ml-auto">
                      <v-menu>
                        <template v-slot:activator="{ props }">
                          <v-btn size="x-small" v-bind="props" variant="text">
                            <DotsIcon width="20" stroke-width="1.5" />
                          </v-btn>
                        </template>
                        <v-list>
                          <v-list-item v-for="(item, index) in actionDD" :key="index" :value="index" color="secondary">
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                    </div>
                  </div>
                  <div class="d-flex ga-4 mt-3">
                    <v-btn variant="outlined" prepend-icon="mdi-account-multiple" color="primary" class="flex-fill"> Followed </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Basic Card Style 1 -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Card Style 1">
              <v-card variant="outlined" class="card-hover-border bg-gray100">
                <v-card-text>
                  <div class="d-flex align-start">
                    <img src="@/assets/images/users/avatar-2.png" width="72" />
                    <div class="ml-auto">
                      <v-menu>
                        <template v-slot:activator="{ props }">
                          <v-btn size="x-small" v-bind="props" variant="text">
                            <DotsIcon width="20" stroke-width="1.5" />
                          </v-btn>
                        </template>
                        <v-list>
                          <v-list-item v-for="(item, index) in actionDD" :key="index" :value="index" color="secondary">
                            <v-list-item-title>{{ item.title }}</v-list-item-title>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                    </div>
                  </div>
                  <div class="mb-4 mt-5">
                    <h4 class="text-h3">Gaetano</h4>
                    <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">Investor Division Strategist</span>

                    <p class="text-subtitle-2 mt-3 mb-4">Try to connect the SAS transmitter, maybe it will index the optical hard drive!</p>

                    <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">Email</span>
                    <p class="text-subtitle-2 font-weight-medium">alia_shields25@yahoo.com</p>
                    <v-row class="mt-3">
                      <v-col cols="6">
                        <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">Phone</span>
                        <p class="text-subtitle-2 font-weight-medium">253-418-5940</p>
                      </v-col>
                      <v-col cols="6">
                        <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">Location</span>
                        <p class="text-subtitle-2 font-weight-medium">Herminahaven</p>
                      </v-col>
                    </v-row>
                  </div>
                  <div class="d-flex flex-wrap flex-lg-nowrap ga-4 mt-5">
                    <v-btn variant="outlined" color="primary" class="flex-fill" prepend-icon="mdi-message"> Message </v-btn>
                    <v-btn variant="outlined" color="error" class="flex-fill" prepend-icon="mdi-block-helper"> Block </v-btn>
                  </div>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Basic Card Style 2 -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Basic Card Style 2">
              <v-card variant="outlined">
                <v-img src="https://berrydashboard.io/static/media/profile-back-9.b5846921.png" height="125px" cover> </v-img>

                <div class="text-center mt-n8 position-relative">
                  <div class="d-flex justify-center">
                    <img src="@/assets/images/profile/user-round.svg" width="72" />
                  </div>
                  <h4 class="text-h3 mt-4 mb-3">Madyson</h4>
                  <span class="text-subtitle-2 text-medium-emphasis font-weight-medium d-block mb-2">Product Tactics Facilitator</span>
                  <v-chip color="success" size="small">Active</v-chip>
                </div>
                <v-card-text>
                  <div class="d-flex flex-wrap flex-lg-nowrap ga-4 mt-4">
                    <v-btn variant="tonal" size="large" color="facebook" class="flex-fill">
                      <v-icon>mdi-facebook</v-icon>
                    </v-btn>
                    <v-btn variant="tonal" size="large" color="twitter" class="flex-fill">
                      <v-icon>mdi-twitter</v-icon>
                    </v-btn>
                    <v-btn variant="tonal" size="large" color="linkedin" class="flex-fill">
                      <v-icon>mdi-linkedin</v-icon>
                    </v-btn>
                  </div>
                  <v-btn variant="outlined" color="primary" class="flex-fill w-100 mt-4" prepend-icon="mdi-message"> Message </v-btn>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <v-col cols="12">
      <UiParentCard title="Cards">
        <v-row>
          <!-- Body Content -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Body Content">
              <v-card variant="outlined" class="bg-gray100">
                <v-card-text> This is some text within a card body. </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Titles, Text, and Links -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Titles, Text, and Links">
              <v-card variant="outlined" title="Card Title" subtitle="Card Subtitle" class="bg-gray100">
                <v-card-text> Some quick example text to build on the card title and make up content. </v-card-text>
                <v-card-actions>
                  <v-btn variant="text" color="primary"> Card Link </v-btn>
                  <v-btn variant="text" color="secondary"> Another </v-btn>
                </v-card-actions>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Header and Footer -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Header and Footer">
              <v-card variant="outlined" class="bg-gray100">
                <v-card-item>
                  <div>
                    <div class="text-overline mb-1">OVERLINE</div>
                    <div class="text-h5 mb-1">Headline</div>
                    <div class="text-caption">Greyhound divisely hello coldly fonwderfully</div>
                  </div>
                </v-card-item>

                <v-card-actions>
                  <v-btn variant="flat" color="primary"> Go Somewhere </v-btn>
                </v-card-actions>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Left Align (Default) -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Left Align (Default)">
              <v-card variant="outlined" class="bg-gray100">
                <v-card-item>
                  <v-card-title>Special title treatment</v-card-title>
                </v-card-item>

                <v-card-text>
                  <p class="text-medium-emphasis">With supporting text below as a natural lead-in to additional content.</p>

                  <v-btn variant="flat" color="primary" class="mt-10"> Go Somewhere </v-btn>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Center Align -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Center Align">
              <v-card variant="outlined" class="text-center bg-gray100">
                <v-card-item>
                  <v-card-title>Special title treatment</v-card-title>
                </v-card-item>

                <v-card-text>
                  <p class="text-medium-emphasis">With supporting text below as a natural lead-in to additional content.</p>

                  <v-btn variant="flat" color="primary" class="mt-10"> Go Somewhere </v-btn>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>
          <!-- Right Align -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Right Align">
              <v-card variant="outlined" class="text-right bg-gray100">
                <v-card-item>
                  <v-card-title>Special title treatment</v-card-title>
                </v-card-item>

                <v-card-text>
                  <p class="text-medium-emphasis">With supporting text below as a natural lead-in to additional content.</p>

                  <v-btn variant="flat" color="primary" class="mt-10"> Go Somewhere </v-btn>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>

          <!-- Image Cap -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Image Cap">
              <v-card variant="outlined" class="overflow-hidden">
                <v-img src="https://berrydashboard.io/vue/assets/background1-24822d62.png" cover> </v-img>
                <v-card-item>
                  <v-card-title>Special title</v-card-title>
                </v-card-item>

                <v-card-text>
                  <p class="text-medium-emphasis mb-8">With supporting text below as a natural lead-in to additional content.</p>

                  <div class="text-medium-emphasis">Last updated 3 mins ago</div>
                </v-card-text>
              </v-card>
            </UiChildCard>
          </v-col>

          <!-- Image Cap -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Image Cap">
              <v-card variant="outlined" class="overflow-hidden">
                <v-card-item>
                  <v-card-title>Special title</v-card-title>
                </v-card-item>

                <v-card-text>
                  <p class="text-medium-emphasis mb-8">With supporting text below as a natural lead-in to additional content.</p>

                  <div class="text-medium-emphasis">Last updated 3 mins ago</div>
                </v-card-text>
                <v-img src="https://berrydashboard.io/vue/assets/background1-24822d62.png" cover> </v-img>
              </v-card>
            </UiChildCard>
          </v-col>

          <!-- Image Overlap -->
          <v-col cols="12" lg="4">
            <UiChildCard title="Image Overlap">
              <v-card variant="outlined" class="overflow-hidden">
                <v-img height="300" src="https://berrydashboard.io/vue/assets/background1-24822d62.png" cover>
                  <v-layout full-height>
                    <div>
                      <v-card-item>
                        <v-card-title class="text-white">Special title</v-card-title>
                      </v-card-item>

                      <v-card-text>
                        <p class="text-medium-emphasis text-white mb-8">
                          With supporting text below as a natural lead-in to additional content.
                        </p>
                      </v-card-text>
                      <v-card-actions>
                        <div class="text-medium-emphasis text-white">Last updated 3 mins ago</div>
                      </v-card-actions>
                    </div>
                  </v-layout>
                </v-img>
              </v-card>
            </UiChildCard>
          </v-col>

          <!-- Color Card -->
          <v-col cols="12">
            <UiChildCard title="Color Card">
              <v-row>
                <v-col cols="12" lg="4" v-for="card in colors" :key="card.color">
                  <v-card :color="card.color">
                    <v-card-item class="text-white">
                      {{ card.color }}
                    </v-card-item>
                    <v-divider></v-divider>
                    <v-card-item>
                      <v-card-title class="text-18 text-white">{{ card.color }} Card Title</v-card-title>
                    </v-card-item>
                    <v-card-text class="text-white">
                      Some quick example text to build on the card title and make up the bulk of the card's content.
                    </v-card-text>
                  </v-card>
                </v-col>
              </v-row>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
