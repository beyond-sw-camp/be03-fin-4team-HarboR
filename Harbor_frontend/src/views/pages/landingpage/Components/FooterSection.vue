<script setup lang="ts">
import { shallowRef } from 'vue';

const list1 = shallowRef([
  {
    name: 'Blog',
    link: 'https://links.codedthemes.com/HTIBc'
  },
  {
    name: 'Documentation',
    link: 'https://codedthemes.gitbook.io/berry-vuetify/'
  },
  {
    name: 'Change log',
    link: 'https://codedthemes.gitbook.io/berry-vuetify/changelog'
  },
  {
    name: 'Support',
    link: 'https://codedthemes.support-hub.io/'
  }
]);

const list2 = shallowRef([
  {
    name: 'License',
    link: 'https://store.vuetifyjs.com/pages/licensing-information'
  },
  {
    name: 'About us',
    link: 'https://store.vuetifyjs.com/pages/about-us'
  },
  {
    name: 'Submit a Request',
    link: 'https://codedthemes.support-hub.io/'
  }
]);

const list3 = shallowRef([
  {
    name: 'Bootstrap 5',
    link: 'https://links.codedthemes.com/VpESi'
  },
  {
    name: 'Angular',
    link: 'https://links.codedthemes.com/iNpKo'
  },
  {
    name: 'CodeIgniter',
    link: 'https://links.codedthemes.com/AdRiB'
  },
  {
    name: '.Net',
    link: 'https://links.codedthemes.com/wkQEu'
  },
  {
    name: 'Shopify',
    link: '/',
    isUpcoming: true
  },
  {
    name: 'Vuetify 3',
    link: 'https://links.codedthemes.com/RqhwV'
  },
  {
    name: 'Full Stack',
    link: 'https://links.codedthemes.com/quhuG'
  },
  {
    name: 'Django',
    link: 'https://links.codedthemes.com/Wfbiy'
  },
  {
    name: 'Flask',
    link: 'https://links.codedthemes.com/quhuG'
  }
]);

const list4 = shallowRef([
  {
    name: 'Free Vue vuetify',
    link: 'https://links.codedthemes.com/zQLHX'
  },
  {
    name: 'Free React MUI',
    link: 'https://links.codedthemes.com/Yfkxg'
  },
  {
    name: 'Free Bootstrap 5',
    link: 'https://links.codedthemes.com/epTmN'
  },
  {
    name: 'Free Angular',
    link: 'https://links.codedthemes.com/seQKN'
  },
  {
    name: 'Free Django',
    link: 'https://links.codedthemes.com/Wfbiy'
  }
]);
</script>

<template>
  <v-row style="background: #111936" class="text-white">
    <v-col cols="12" class="py-0">
      <div class="spacer">
        <v-container class="maxWidth">
          <v-row class="">
            <v-col cols="12" sm="12" lg="4">
              <h4 class="text-h4 font-weight-medium">About Berry</h4>
              <p class="text-body-1 pt-lg-6 pt-3">
                Berry Vue is a dashboard template that utilizes the Vuetify framework and the Vue JavaScript library. It offers a range of
                features and customization options to help you create a powerful and user-friendly admin panel.
              </p>
            </v-col>
            <v-col lg="2" md="3" cols="6">
              <h5 class="text-h4 mb-md-6 mb-4">Help</h5>
              <div class="d-flex flex-column footer-list">
                <a
                  v-for="(list, i) in list1"
                  :key="i"
                  :href="list.link"
                  class="text-h5 text-decoration-none font-weight-regular"
                  target="_blank"
                >
                  {{ list.name }}
                </a>
              </div>
            </v-col>
            <v-col lg="2" md="3" cols="6">
              <h5 class="text-h4 mb-md-6 mb-4">Store Help</h5>
              <div class="d-flex flex-column footer-list">
                <a
                  v-for="(list, i) in list2"
                  :key="i"
                  :href="list.link"
                  class="text-h5 text-decoration-none font-weight-regular"
                  target="_blank"
                >
                  {{ list.name }}
                </a>
              </div>
            </v-col>
            <v-col lg="2" md="3" cols="6">
              <h5 class="text-h4 mb-md-6 mb-4">Berry Eco-System</h5>
              <div class="d-flex flex-column footer-list">
                <a
                  v-for="(list, i) in list3"
                  :key="i"
                  :href="list.link"
                  class="text-h5 text-decoration-none font-weight-regular"
                  target="_blank"
                >
                  {{ list.name }}
                  <v-chip v-if="list.isUpcoming" size="small" variant="outlined" color="primary"> Upcoming </v-chip>
                </a>
              </div>
            </v-col>
            <v-col lg="2" md="3" cols="6">
              <h5 class="text-h4 mb-md-6 mb-4">Free Versions</h5>
              <div class="d-flex flex-column footer-list">
                <a
                  v-for="(list, i) in list4"
                  :key="i"
                  :href="list.link"
                  class="text-h5 text-decoration-none font-weight-regular"
                  target="_blank"
                >
                  {{ list.name }}
                </a>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </v-col>
    <v-col cols="12" class="bg-dark pt-0">
      <v-container class="maxWidth">
        <v-row>
          <v-col sm="6" class="text-center text-sm-left mb-sm-0 mb-n4">
            <small
              ><span style="opacity: 0.4">© Berry is managed by </span>
              <a href="https://codedthemes.com/" target="_" class="text-primary">Codedthemes</a>
            </small>
          </v-col>
          <v-col sm="6" cols="12" class="text-center text-sm-right">
            <ul class="d-flex justify-center justify-sm-end ga-6 customlink align-center">
              <li>
                <a href="https://blog.berrydashboard.io/" target="_blank"><v-icon>mdi-earth</v-icon></a>
              </li>
              <li>
                <a href="https://twitter.com/codedthemes" target="_blank"><v-icon>mdi-twitter</v-icon></a>
              </li>
              <li>
                <a href="https://dribbble.com/codedthemes" target="_blank"><v-icon>mdi-basketball</v-icon></a>
              </li>
            </ul>
          </v-col>
        </v-row>
      </v-container></v-col
    >
  </v-row>
</template>
<style lang="scss">
.customlink {
  list-style: none;
  padding: 0;
  margin: 0;
  li {
    list-style: none;
    a {
      text-decoration: none;
      color: white;
      opacity: 0.4;
      &:hover {
        opacity: 1;
      }
    }
  }
}
.bg-dark {
  background-color: #212946;
}
.footer-list {
  a {
    color: rgb(var(--v-theme-surface));
    + a {
      margin-top: 20px;
      @media (max-width: 960px) {
        margin-top: 12px;
      }
    }
  }
}
</style>
