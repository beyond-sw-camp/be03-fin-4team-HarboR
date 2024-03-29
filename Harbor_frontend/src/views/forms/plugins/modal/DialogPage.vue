<script setup>
import { ref } from 'vue';

import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

import DialogsActivator from '@/components/forms/plugins/dialogs/DialogsActivator.vue';
import DialogsModel from '@/components/forms/plugins/dialogs/DialogsModel.vue';
import DialogsFullscreen from '@/components/forms/plugins/dialogs/DialogsFullscreen.vue';
import DialogsTransitions from '@/components/forms/plugins/dialogs/DialogsTransitions.vue';
import DialogsPersistent from '@/components/forms/plugins/dialogs/DialogsPersistent.vue';
import DialogsScrollable from '@/components/forms/plugins/dialogs/DialogsScrollable.vue';
import DialogsForm from '@/components/forms/plugins/dialogs/DialogsForm.vue';
import DialogsNested from '@/components/forms/plugins/dialogs/DialogsNested.vue';
import UiChildCard from '@/components/shared/UiChildCard.vue';

const page = ref({ title: 'Modal Page' });
const breadcrumbs = ref([
  {
    title: 'Plugins',
    disabled: false,
    href: '#'
  },
  {
    title: 'Modal Page',
    disabled: true,
    href: '#'
  }
]);
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Modal Dialog">
        <v-row>
          <v-col cols="12" sm="12" lg="6">
            <UiChildCard title="Activator">
              <DialogsActivator />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6">
            <UiChildCard title="V-model">
              <DialogsModel />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Fullscreen">
              <DialogsFullscreen />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Transitions">
              <DialogsTransitions />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Persistent">
              <DialogsPersistent />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Scrollable">
              <DialogsScrollable />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Form">
              <DialogsForm />
            </UiChildCard>
          </v-col>
          <v-col cols="12" sm="12" lg="6" class="d-flex align-items-stretch">
            <UiChildCard title="Nested Dialog">
              <DialogsNested />
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
