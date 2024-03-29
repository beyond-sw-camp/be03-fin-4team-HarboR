<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiTableCard from '@/components/shared/UiTableCard.vue';

// theme breadcrumb
const page = ref({ title: 'Height Table' });
const breadcrumbs = ref([
  {
    title: 'Tables',
    disabled: false,
    href: '#'
  },
  {
    title: 'Height Table',
    disabled: true,
    href: '#'
  }
]);

// table data

const desserts = ref([
  {
    name: 'Frozen Yogurt',
    calories: 159,
    fat: 6.0,
    carbs: 24,
    protein: 4.0
  },
  {
    name: 'Ice cream sandwich',
    calories: 237,
    fat: 6.0,
    carbs: 24,
    protein: 4.0
  },
  {
    name: 'Eclair',
    calories: 262,
    fat: 6.0,
    carbs: 24,
    protein: 4.0
  },
  {
    name: 'Cupcake',
    calories: 305,
    fat: 6.0,
    carbs: 24,
    protein: 4.0
  },
  {
    name: 'Gingerbread',
    calories: 356,
    fat: 6.0,
    carbs: 24,
    protein: 4.0
  }
]);
</script>

<template>
  <!-- ---------------------------------------------------- -->
  <!-- Table Height -->
  <!-- ---------------------------------------------------- -->
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12">
      <UiTableCard title="Height Table">
        <v-table height="300px">
          <thead>
            <tr>
              <th class="text-left text-subtitle-1">Dessert (100g serving)</th>
              <th class="text-left text-subtitle-1">Calories</th>
              <th class="text-left text-subtitle-1">Fat (g)</th>
              <th class="text-left text-subtitle-1">Carbs (g)</th>
              <th class="text-left text-subtitle-1">Protein (g)</th>
              <th class="text-left text-subtitle-1">Protein (g)</th>
              <th class="text-left text-subtitle-1">Protein (g)</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in desserts" :key="item.name" class="text-lighttext">
              <td class="text-subtitle-1 font-weight-regular">{{ item.name }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.calories }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.fat }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.carbs }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.protein }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.protein }}</td>
              <td class="text-subtitle-1 font-weight-regular">{{ item.protein }}</td>
            </tr>
          </tbody>
        </v-table>
      </UiTableCard>
    </v-col>
  </v-row>
</template>
