<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// icons
import { MoodSmileIcon, BuildingSkyscraperIcon, CoinIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Accordion' });
const breadcrumbs = ref([
  {
    title: 'Ui elements',
    disabled: false,
    href: '#'
  },
  {
    title: 'Accordion',
    disabled: true,
    href: '#'
  }
]);

const panel = ref([0, 1]);
const multiple = ref([0, 1]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Expansion Panel">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic">
              <v-expansion-panels>
                <v-expansion-panel v-for="i in 3" :key="i" elevation="0">
                  <v-expansion-panel-title> Item #{{ i }} </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
                    eget.
                  </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
          <!-- Disabled -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Disabled">
              <v-expansion-panels disabled>
                <v-expansion-panel v-for="i in 3" :key="i" elevation="0">
                  <v-expansion-panel-title> Item #{{ i }} </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
                    eget.
                  </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
          <!-- Default Expand -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Default Expand">
              <v-expansion-panels v-model="panel">
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #1</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #2</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #3</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
          <!-- Multiple Expand -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Multiple Expand">
              <v-expansion-panels v-model="multiple" multiple>
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #1</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #2</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #3</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>

          <!-- Inset -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Inset">
              <v-expansion-panels variant="inset">
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #1</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #2</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #3</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
          <!-- Popout -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Popout">
              <v-expansion-panels variant="popout">
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #1</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #2</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title>Item #3</v-expansion-panel-title>
                  <v-expansion-panel-text> Some content </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
          <!-- Customized Icon -->
          <v-col cols="12">
            <UiChildCard title="Customized Icon">
              <v-expansion-panels>
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title expand-icon="mdi-menu-down" class="text-primary font-weight-semibold">
                    <MoodSmileIcon size="20" stroke-width="1.5" class="mr-2" />
                    Profile
                  </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    <h4 class="mb-4 mt-3">Joseph William</h4>
                    <v-divider></v-divider>
                    <p class="pt-3">
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
                      eget.
                    </p>
                  </v-expansion-panel-text>
                </v-expansion-panel>
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title expand-icon="mdi-menu-down" class="text-error font-weight-semibold">
                    <BuildingSkyscraperIcon size="20" stroke-width="1.5" class="mr-2" />
                    Address
                  </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    <h4 class="mb-4 mt-3">Joseph William</h4>
                    <v-divider></v-divider>
                    <p class="pt-3">
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
                      eget.
                    </p>
                  </v-expansion-panel-text>
                </v-expansion-panel>
                <v-expansion-panel elevation="0">
                  <v-expansion-panel-title expand-icon="mdi-menu-down" class="text-secondary font-weight-semibold">
                    <CoinIcon size="20" stroke-width="1.5" class="mr-2" />
                    Account Info
                  </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    <h4 class="mb-4 mt-3">Joseph William</h4>
                    <v-divider></v-divider>
                    <p class="pt-3">
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
                      eget.
                    </p>
                  </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
