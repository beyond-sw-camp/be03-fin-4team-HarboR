<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

// theme breadcrumb
const page = ref({ title: 'Ratings' });
const breadcrumbs = ref([
  {
    title: 'Advance',
    disabled: false,
    href: '#'
  },
  {
    title: 'Ratings',
    disabled: true,
    href: '#'
  }
]);

// rating data
const rating = ref(3);
const rating2 = ref(4);
const rating3 = ref(5);
const rating4 = ref(2);
const rating5 = ref(2);
const rating6 = ref(3);
const rating7 = ref(3.5);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiParentCard title="Ratings">
        <v-row>
          <!-- Basic -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Basic">
              <div class="text-center">
                <v-rating v-model="rating"></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Color -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Color">
              <div class="text-center">
                <v-rating v-model="rating2" color="primary"></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Clearable -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Clearable">
              <div class="text-center">
                <v-rating v-model="rating3" clearable></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Read Only -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Read Only">
              <div class="text-center">
                <v-rating v-model="rating4" readonly></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Hover -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Hover">
              <div class="text-center">
                <v-rating v-model="rating5" color="warning" hover></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Label -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Label">
              <div class="text-center">
                <v-rating
                  v-model="rating6"
                  class="ma-2"
                  :item-labels="['sad', '', '', '', 'happy']"
                  item-label-position="top"
                  color="warning"
                  hover
                ></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Icons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Icons">
              <div class="text-center">
                <v-rating
                  class="d-block"
                  v-model="rating7"
                  color="error"
                  empty-icon="mdi-heart-outline"
                  full-icon="mdi-heart"
                  half-icon="mdi-heart-half"
                  half-increments
                  hover
                ></v-rating>

                <v-rating
                  class="d-block"
                  v-model="rating7"
                  color="secondary"
                  empty-icon="mdi-circle-outline"
                  full-icon="mdi-circle"
                  half-icon="mdi-circle-half"
                  half-increments
                  hover
                ></v-rating>
              </div>
            </UiChildCard>
          </v-col>
          <!-- Size -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Size">
              <div class="text-center">
                <v-rating class="d-block" color="primary" value="3" size="x-small"></v-rating>

                <v-rating class="d-block" color="primary" value="3" size="small"></v-rating>

                <v-rating class="d-block" color="primary" value="3" size="large"></v-rating>

                <v-rating class="d-block" color="primary" value="3" size="x-large"></v-rating>
              </div>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style lang="scss">
@media (max-width: 480px) {
  .v-rating {
    .v-rating__item {
      .v-btn--icon {
        width: unset;
        height: unset;
      }
    }
  }
}
</style>
