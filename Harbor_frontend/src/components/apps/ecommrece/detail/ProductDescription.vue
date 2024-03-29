<script setup lang="ts">
import { shallowRef } from 'vue';
const panel = shallowRef(['Specification']);

type GenealType = {
  title: string;
  desc: string;
};

const generalItem = shallowRef<GenealType[]>([
  {
    title: 'Type',
    desc: 'Hooded Neck, Paint Clothes'
  },
  {
    title: 'Sleeve',
    desc: 'Full'
  },
  {
    title: 'Fit',
    desc: 'Regular'
  },
  {
    title: 'Fabric',
    desc: 'Hosiery, Smooth, Silk'
  },
  {
    title: 'Style',
    desc: 'CV-TS9865'
  },
  {
    title: 'Ideal For',
    desc: 'All'
  },
  {
    title: 'Size',
    desc: 'Free'
  },
  {
    title: 'Pattern',
    desc: 'Printed'
  },
  {
    title: 'Reversible',
    desc: 'No'
  },
  {
    title: 'Secondary Color',
    desc: 'Black, Brown'
  }
]);
const inboxItem = shallowRef<GenealType[]>([
  {
    title: 'Sales Package',
    desc: '5 Items'
  },
  {
    title: 'Gift Box',
    desc: 'Yes'
  },
  {
    title: 'Plastic Wrapper',
    desc: 'Yes'
  },
  {
    title: 'Safety Wrapper',
    desc: 'No'
  }
]);
</script>
<template>
  <v-expansion-panels v-model="panel" multiple class="custom-accordion">
    <v-expansion-panel title="Specification" value="Specification" elevation="0">
      <v-expansion-panel-text>
        <v-row>
          <v-col lg="6">
            <h4 class="text-h4 mb-3">General</h4>
            <v-table>
              <tr v-for="(gen, i) in generalItem" :key="i">
                <td class="py-2">
                  <span class="text-medium-emphasis font-weight-medium">{{ gen.title }}</span>
                </td>
                <td>{{ gen.desc }}</td>
              </tr>
            </v-table>
          </v-col>
          <v-col lg="6">
            <h4 class="text-h4 mb-3">In The Box</h4>
            <v-table>
              <tr v-for="(gen, i) in inboxItem" :key="i">
                <td class="py-2 v-col-lg-4 px-0">
                  <span class="text-medium-emphasis font-weight-medium">{{ gen.title }}</span>
                </td>
                <td>{{ gen.desc }}</td>
              </tr>
            </v-table>
          </v-col>
        </v-row>
      </v-expansion-panel-text>
    </v-expansion-panel>

    <v-expansion-panel title="Material and Wash instruction" value="instruction" elevation="0">
      <v-expansion-panel-text>
        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque
        penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,
        sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut,
        imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum
      </v-expansion-panel-text>
    </v-expansion-panel>

    <v-expansion-panel title="Add on data" value="data" elevation="0">
      <v-expansion-panel-text>
        Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they
        live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place
        and supplies it with the necessary
      </v-expansion-panel-text>
    </v-expansion-panel>
  </v-expansion-panels>
</template>
<style lang="scss">
.custom-accordion {
  .v-expansion-panel-title {
    font-weight: 500;
  }
  .v-expansion-panel-title__overlay {
    background-color: transparent;
  }
}
</style>
