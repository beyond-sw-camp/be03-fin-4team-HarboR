<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import Banner from '../../../../components/apps/socialprofile/BannerSection.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useGalleryStore } from '@/stores/apps/gallery';
import UiParentCard from '@/components/shared/UiParentCard.vue';

const store = useGalleryStore();

onMounted(() => {
  store.fetchGallery();
});

type GalleryItem = {
  title: string;
  image: string;
  dateTime: string;
  // Add other properties as needed
};
const getPhotos = computed<GalleryItem[]>(() => {
  return store.gallery;
});

const searchValue = ref('');
// dropdown data
const actionDD = ref([
  { title: 'Remove Tag' },
  { title: 'Download' },
  { title: 'Make Profile Picture' },
  { title: 'Make Cover Photo' },
  { title: 'Find support or Report Photo' }
]);
const page = ref({ title: 'Social Profile' });

const filteredCards = computed<GalleryItem[]>(() => {
  return getPhotos.value.filter((card) => {
    return card.title.toLowerCase().includes(searchValue.value.toLowerCase());
  });
});

const breadcrumbs = ref([
  {
    title: 'Users',
    disabled: false,
    href: '/'
  },
  {
    title: 'Social Profile',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <Banner />
  <v-row class="justify-content-end mt-5">
    <v-col cols="12">
      <UiParentCard title="Gallery">
        <template v-slot:action>
          <v-row class="justify-end">
            <v-col cols="12" sm="5">
              <div class="d-sm-flex align-center ga-4 mt-4 mt-sm-0">
                <v-text-field
                  color="primary"
                  hide-details
                  width="200"
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Search"
                  density="compact"
                  prepend-inner-icon="mdi-magnify"
                  v-model="searchValue"
                >
                </v-text-field>
                <v-btn color="secondary" class="mt-4 mt-sm-0">Add Photos</v-btn>
              </div>
            </v-col>
          </v-row>
        </template>

        <v-row>
          <v-col cols="12" lg="3" md="4" sm="6" v-for="(card, i) in filteredCards" :key="i">
            <v-card variant="outlined" class="card-hover-border bg-gray100">
              <img :src="card.image" alt="gallery" class="w-100" />
              <v-card-text class="pa-3">
                <div class="d-flex align-center ga-4">
                  <div>
                    <h4 class="text-h5">{{ card.title }}</h4>
                    <small class="opacity-50 d-block text-truncate d-flex align-center ga-2">
                      <CalendarIcon size="14" /> {{ card.dateTime }}
                    </small>
                  </div>
                  <div class="ml-auto">
                    <v-menu>
                      <template v-slot:activator="{ props }">
                        <v-btn size="x-small" color="secondary" v-bind="props" icon variant="text">
                          <DotsVerticalIcon width="14" stroke-width="1.5" />
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
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
