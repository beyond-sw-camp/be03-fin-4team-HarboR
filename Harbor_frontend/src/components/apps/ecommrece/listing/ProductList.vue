<script setup>
import { ref, shallowRef, onMounted, computed } from 'vue';
import ProductItemVue from './ProductItem.vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import { orderBy } from 'lodash';
import { useDisplay } from 'vuetify';
import ProductEmplty from './ProductEmplty.vue';
import ProductFilters from './ProductFilters.vue';
import FloatingCart from '../cart/FloatingCart.vue';

const store = useEcomStore();

onMounted(() => {
  store.fetchProducts();
});

const getProducts = computed(() => {
  return store.products;
});

const sortbyname = shallowRef(['Price:High to Low', 'Price:Low to High', 'Popularity', 'Fresh Arrivals']);
const selected = ref('Price:Low to High');
const searchValue = ref('');
const sDrawer = ref(false);

const getVisibleProduct = (products, sortBy, search, gender, category) => {
  // SORT BY
  if (sortBy === 'Popularity') {
    products = orderBy(products, ['rating'], ['desc']);
  }
  if (sortBy === 'Price:High to Low') {
    products = orderBy(products, ['salePrice'], ['desc']);
  }
  if (sortBy === 'Price:Low to High') {
    products = orderBy(products, ['salePrice'], ['asc']);
  }
  if (sortBy === 'Fresh Arrivals') {
    products = orderBy(products, ['created'], ['asc']);
  }
  if (gender) {
    products = products.filter((product) => {
      return product.gender.includes(gender);
    });
  }
  if (category !== 'all') {
    products = products.filter((product) => {
      return product.categories.includes(category);
    });
  }
  if (search) {
    products = products.filter((product) => {
      return product.name.toLowerCase().includes(search.value.toLowerCase());
    });
  }

  return products;
};

const filteredProducts = computed(() => {
  return getVisibleProduct(getProducts.value, selected.value, searchValue, store.gender, store.category);
});
const toggleSide = ref(false);
const { lgAndUp } = useDisplay();
const handleToggle = () => {
  toggleSide.value = !toggleSide.value;
};
const handleMobileDrawer = () => {
  sDrawer.value = !sDrawer.value;
};

function AddCart(p) {
  store.AddToCart(p);
}
</script>

<template>
  <v-row class="align-center">
    <v-col lg="6"><h3>Shop</h3></v-col>
    <v-col lg="6" sm="6" cols="12">
      <div class="d-flex ga-2 align-center">
        <v-text-field
          variant="outlined"
          v-model="searchValue"
          prepend-inner-icon="mdi-magnify"
          persistent-placeholder
          placeholder="Search Product"
          hide-details
          density="compact"
          color="primary"
        ></v-text-field>

        <v-btn variant="text" color="secondary" @Click="!lgAndUp ? handleMobileDrawer() : handleToggle()"
          ><FilterIcon></FilterIcon>Filter</v-btn
        >
        <div v-if="lgAndUp">
          <v-select :items="sortbyname" color="primary" variant="outlined" hide-details density="compact" v-model="selected"></v-select>
        </div>
      </div>
    </v-col>
  </v-row>
  <v-divider class="my-3 mb-6"></v-divider>
  <v-row>
    <v-col cols>
      <v-row v-if="filteredProducts.length >= 1">
        <v-col :lg="toggleSide ? '3' : '4'" v-for="product in filteredProducts" :key="product.id">
          <ProductItemVue
            :name="product.name"
            :image="product.image"
            :desc="product.description"
            :salePrice="product.salePrice"
            :offerPrice="product.offerPrice"
            :rating="product.rating"
            :goto="product.id"
            @handlecart="AddCart(product)"
          />
        </v-col>
      </v-row>
      <ProductEmplty v-else />
    </v-col>
    <v-col v-if="!toggleSide && lgAndUp" class="filterSidebar">
      <v-card variant="flat">
        <v-card-text class="pa-5"> <ProductFilters /> </v-card-text>
      </v-card>
    </v-col>
  </v-row>
  <v-navigation-drawer temporary location="right" v-model="sDrawer" width="340" top v-if="!lgAndUp">
    <v-card-text class="pa-5">
      <ProductFilters />
    </v-card-text>
  </v-navigation-drawer>
  <FloatingCart />
</template>
<style lang="scss">
.filterSidebar {
  max-width: 350px;
}
</style>
