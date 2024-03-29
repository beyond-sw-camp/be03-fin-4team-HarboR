<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import ProductItemVue from '../listing/ProductItem.vue';
import { useEcomStore } from '@/stores/apps/eCommerce';
import { Carousel, Slide, Navigation } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
import FloatingCart from '../cart/FloatingCart.vue';

const store = useEcomStore();

onMounted(() => {
  store.fetchProducts();
});

const getProducts = computed(() => {
  return store.products;
});
const settings = ref({
  itemsToShow: 1,
  snapAlign: 'center'
});
const breakpoints = ref({
  700: {
    itemsToShow: 3,
    snapAlign: 'center'
  },
  // 1024 and up
  1024: {
    itemsToShow: 4,
    snapAlign: 'center'
  },
  1450: {
    itemsToShow: 5,
    snapAlign: 'start'
  }
});
/* eslint-disable @typescript-eslint/no-explicit-any */
function AddCart(p: any) {
  store.AddToCart(p);
}
</script>

<template>
  <h2 class="text-h2 mt-8">Related Products</h2>
  <Carousel id="gallery" class="relatedCar" :breakpoints="breakpoints" :settings="settings">
    <Slide v-for="(product, i) in getProducts" :key="i">
      <div class="carousel__item text-left">
        <ProductItemVue
          :name="product.name"
          :image="product.image"
          :desc="product.description"
          :salePrice="product.salePrice"
          :offerPrice="product.offerPrice"
          :rating="product.rating"
          :goto="1"
          @handlecart="AddCart(product)"
        />
      </div>
    </Slide>

    <template #addons>
      <Navigation />
    </template>
  </Carousel>
  <FloatingCart />
</template>
<style lang="scss">
.relatedCar {
  .carousel__slide {
    padding: 10px;
  }
}
</style>
