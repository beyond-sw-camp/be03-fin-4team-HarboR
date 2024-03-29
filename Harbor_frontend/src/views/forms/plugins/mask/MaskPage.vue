<script setup>
import { ref, computed } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { vMaska } from 'maska';
import UiChildCard from '@/components/shared/UiChildCard.vue';

const page = ref({ title: 'Mask Page' });
const breadcrumbs = ref([
  {
    title: 'Plugins',
    disabled: false,
    href: '#'
  },
  {
    title: 'Mask',
    disabled: true,
    href: '#'
  }
]);

const phoneoptions = { mask: ['+1 (###) ##-##-##', '+1 (###) ###-##-##'] };
const phoneValue = ref('');

const contactoptions = { mask: '+91 #### ###-###' };
const contactValue = ref('');

const hexoptions = { mask: '!#HHHHHH', tokens: { H: { pattern: /[0-9a-fA-F]/, uppercase: true } } };
const hexValue = ref('');

const dateoptions = { mask: '##/##/####' };
const dateValue = ref('');

const ipaddress = { mask: '###.###.###.###' };
const ipValue = ref('');

const ipv4address = { mask: '####.####.####.####' };
const ipv4Value = ref('');

const ipv6address = { mask: '####.####.####.####.####.####' };
const ipv6Value = ref('');

const maskedValue = ref('');
const inputValue = ref('');
const maskPattern = computed(() => ({ mask: maskedValue.value }));
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Mask Input">
        <v-row>
          <!-- Base Buttons -->
          <v-col cols="12" lg="6">
            <UiChildCard title="Phone">
              <v-text-field color="primary" label="Phone with Code" variant="outlined" v-maska:[phoneoptions] v-model="phoneValue" />
              <v-text-field color="primary" label="Contact Number" variant="outlined" v-maska:[contactoptions] v-model="contactValue" />
              <v-text-field color="primary" label="Hex color: #121113" variant="outlined" v-maska:[hexoptions] v-model="hexValue" />
              <v-text-field color="primary" label="Date Mask" variant="outlined" v-maska:[dateoptions] v-model="dateValue" />
            </UiChildCard>
          </v-col>
          <v-col cols="12" lg="6">
            <UiChildCard title="Network">
              <v-text-field color="primary" label="Ip Address" variant="outlined" v-maska:[ipaddress] v-model="ipValue" />
              <v-text-field color="primary" label="Ipv4 Address" variant="outlined" v-maska:[ipv4address] v-model="ipv4Value" />
              <v-text-field color="primary" label="Ipv6 Address" variant="outlined" v-maska:[ipv6address] v-model="ipv6Value" />
            </UiChildCard>
          </v-col>
          <v-col cols="12" lg="12">
            <UiChildCard title="Custom">
              <v-text-field v-model="maskedValue" color="primary" variant="outlined" label="Make Mask to apply bottom input"></v-text-field>
              <v-text-field
                v-maska:[maskPattern]
                v-model="inputValue"
                color="primary"
                label="Masked Input"
                variant="outlined"
              ></v-text-field>
            </UiChildCard>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
