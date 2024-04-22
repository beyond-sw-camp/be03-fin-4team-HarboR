import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import { router } from './router';
import vuetify from './plugins/vuetify';
import '@/scss/style.scss';
import PerfectScrollbar from 'vue3-perfect-scrollbar';
import VueApexCharts from 'vue3-apexcharts';
import VueTablerIcons from 'vue-tabler-icons';
//Mock Api data
import './_mockApis';
import { fakeBackend } from '@/utils/helpers/fake-backend';
import { Qalendar } from "qalendar";

import { vMaska } from 'maska';
// print
import print from 'vue3-print-nb';
// Table
import Vue3EasyDataTable from 'vue3-easy-data-table';
//i18
import { createI18n } from 'vue-i18n';
import messages from '@/utils/locales/messages';
import axios from 'axios';
const i18n = createI18n({
  locale: 'en',
  messages: messages,
  silentTranslationWarn: true,
  silentFallbackWarn: true
});

const app = createApp(App);
fakeBackend();
app.use(router);
app.use(axios);
app.component('EasyDataTable', Vue3EasyDataTable);
app.use(PerfectScrollbar);
app.use(createPinia());
app.use(VueTablerIcons);
app.use(print);
app.use(i18n);
app.use(Qalendar);
app.directive('maska', vMaska);
app.use(VueApexCharts);
app.use(vuetify).mount('#app');