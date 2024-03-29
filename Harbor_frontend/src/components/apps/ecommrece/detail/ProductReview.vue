<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import user1 from '@/assets/images/users/avatar-1.png';
import user2 from '@/assets/images/users/avatar-2.png';
import user3 from '@/assets/images/users/avatar-3.png';

type commentsType = {
  name: string;
  desc: string;
  avatar: string;
  date: string;
  ratings: number;
};
const rating = ref(4);
const comments = shallowRef<commentsType[]>([
  {
    name: 'Adeline Harmon',
    desc: 'Uvikehi dinlo ahevu ogcuz fufijlu wa vutal jizfugcuk losuz kig gavle ji neojo. Jaabupad po ho foj oroku golugo uw cumiwul kurobrah zo di kovotis omtoga nigove.',
    avatar: user1,
    date: 'Mon, Oct 17 2022',
    ratings: 5
  },
  {
    name: 'Lina Price',
    desc: 'Jewubi dinlo ahevu ogcuz fufijlu wa vutal jizfugcuk losuz kig gavle ji neojo. Jaabupad po ho foj oroku golugo uw cumiwul kurobrah zo di kovotis omtoga nigove.',
    avatar: user2,
    date: 'Mon, Oct 17 2022',
    ratings: 3
  },
  {
    name: 'Clifford Hunter',
    desc: 'Nameom haw fitir gisojmo nelavmid lup zokobvij zadehsis vukpepbir johcuwpov ewtetof getotauto to guha takowza jodjer hu okowo. ogcuz fufijlu wa vutal jizfugcuk losuz kig gavle ji neojo. Jaabupad po ho foj oroku golugo uw cumiwul kurobrah zo di kovotis omtoga nigove.',
    avatar: user3,
    date: 'Sun, Oct 9 2022',
    ratings: 5
  }
]);
</script>

<template>
  <v-row>
    <v-col lg="4" class="align-streach">
      <v-card variant="outlined" elevation="0" class="text-center h-100 d-flex align-center justify-center">
        <div class="pa-4">
          <h4 class="text-h4">Average Rating</h4>
          <h1 class="text-primary text-h1 my-4">4.5/5</h1>
          <div class="text-medium-emphasis align-center justify-center d-flex mb-3 ga-2">
            <v-rating color="warning" half-increments v-model="rating" density="compact"> </v-rating>
            <small>(625+)</small>
          </div>
        </div>
      </v-card>
    </v-col>
    <v-col lg="4" class="align-streach justify-center">
      <v-card variant="outlined" elevation="0" class="text-center justify-center h-100">
        <div class="pa-7">
          <div class="d-flex align-center ga-4 my-2">
            <div class="text-no-wrap">1 Stars</div>
            <v-progress-linear model-value="15" style="left: 0; transform: unset" color="secondary" rounded="sm"></v-progress-linear>
            <h5>(125)</h5>
          </div>
          <div class="d-flex align-center ga-4 my-2">
            <div class="text-no-wrap">2 Stars</div>
            <v-progress-linear model-value="15" style="left: 0; transform: unset" color="secondary" rounded="sm"></v-progress-linear>
            <h5>(125)</h5>
          </div>
          <div class="d-flex align-center ga-4 my-2">
            <div class="text-no-wrap">3 Stars</div>
            <v-progress-linear model-value="35" style="left: 0; transform: unset" color="secondary" rounded="sm"></v-progress-linear>
            <h5>(160)</h5>
          </div>
          <div class="d-flex align-center ga-4 my-2">
            <div class="text-no-wrap">4 Stars</div>
            <v-progress-linear model-value="12" style="left: 0; transform: unset" color="secondary" rounded="sm"></v-progress-linear>
            <h5>(105)</h5>
          </div>
          <div class="d-flex align-center ga-4 my-2">
            <div class="text-no-wrap">5 Stars</div>
            <v-progress-linear model-value="55" style="left: 0; transform: unset" color="secondary" rounded="sm"></v-progress-linear>
            <h5>(325)</h5>
          </div>
        </div>
      </v-card>
    </v-col>
    <v-col lg="4" class="align-streach">
      <v-card variant="outlined" elevation="0" class="d-flex align-center justify-center h-100">
        <div class="pa-4">
          <v-btn variant="outlined" color="primary" size="large" prepend-icon="mdi-message-outline"> Write a Review</v-btn>
        </div>
      </v-card>
    </v-col>
    <v-col lg="12">
      <v-card variant="outlined" class="d-flex align-center my-7" v-for="(comment, i) in comments" :key="i">
        <div class="d-flex align-start ga-4 pa-4 w-100">
          <img :src="comment.avatar" alt="users" width="40" />
          <div class="flex-grow-1">
            <h4 class="text-h5">{{ comment.name }}</h4>
            <div class="v-col-lg-2 px-0 py-0">
              <v-rating size="x-small" v-model="comment.ratings" density="compact" color="warning" />
            </div>
            <p>{{ comment.desc }}</p>
          </div>
          <v-btn icon size="small" variant="text"><DotsVerticalIcon size="15" /></v-btn>
        </div>
      </v-card>
    </v-col>
  </v-row>
  <div class="text-center">
    <v-btn variant="text" color="primary">Load More Comments</v-btn>
  </div>
</template>
