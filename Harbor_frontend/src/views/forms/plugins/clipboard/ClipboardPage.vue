<script setup>
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import useClipboard from 'vue-clipboard3';
import UiChildCard from '@/components/shared/UiChildCard.vue';

const { toClipboard } = useClipboard();

const text = ref('https://berrydashboard.io/');
const textArea = ref('Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts');
const page = ref({ title: 'Clipboard Page' });
const snackbar = ref(false);

const copy = async () => {
  try {
    await toClipboard(text.value);
    snackbar.value = true;
  } catch (e) {
    console.error(e);
  }
};

const copyTextArea = async () => {
  try {
    await toClipboard(textArea.value);
    snackbar.value = true;
  } catch (e) {
    console.error(e);
  }
};

const breadcrumbs = ref([
  {
    title: 'Plugins',
    disabled: false,
    href: '#'
  },
  {
    title: 'Clipboard',
    disabled: true,
    href: '#'
  }
]);
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Clipboard">
        <v-row>
          <v-col md="12" cols="12">
            <UiChildCard title="Copy from TextField">
              <div>
                <v-text-field
                  color="primary"
                  label="Website"
                  variant="outlined"
                  v-model="text"
                  :append-icon="'mdi-content-copy'"
                  @click:append="copy"
                />
              </div>
            </UiChildCard>
          </v-col>
          <v-col md="12" cols="12">
            <UiChildCard title="Copy from TextArea">
              <div>
                <v-textarea variant="outlined" name="input-7-4" filled label="Outlined textarea" v-model="textArea"></v-textarea>
                <v-btn color="primary" @click="copyTextArea">Copy</v-btn>
              </div>
            </UiChildCard>
          </v-col>
          <v-col md="12" cols="12">
            <UiChildCard title="Copy from Container">
              {{ textArea }}
              <br /><br />
              <v-btn color="primary" @click="copyTextArea">Copy</v-btn>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>

      <v-snackbar v-model="snackbar" color="success" :timeout="3000" top right rounded="pill">Text Copied!</v-snackbar>
    </v-col>
  </v-row>
</template>
