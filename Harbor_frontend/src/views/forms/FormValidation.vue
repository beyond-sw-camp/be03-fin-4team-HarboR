<script setup>
import { ref, computed } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import VeeValidation from '@/components/forms/validation/VeeValidation.vue';
import { Form } from 'vee-validate';

const page = ref({ title: 'FormValidation Page' });
const breadcrumbs = ref([
  {
    title: 'Forms',
    disabled: false,
    href: '#'
  },
  {
    title: 'FormValidation Page',
    disabled: true,
    href: '#'
  }
]);

const email = ref('');
const firstname = ref('');
const lastname = ref('');
const rules = ref({
  required: (value) => !!value || 'Required.',
  counter: (value) => value.length <= 20 || 'Max 20 characters',
  email: (value) => {
    const pattern =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(value) || 'Invalid e-mail.';
  },
  firstname: (value) => value.length > 2 || 'More than two letters required',
  lastname: (value) => value.length > 2 || 'More than two letters required'
});
const password = ref('');

const pwdrules = ref({
  required: (value) => !!value || 'Required.',
  min: (v) => v.length >= 8 || 'Min 8 characters',
  emailMatch: () => `The email and password you entered don't match`
});

//Checkbox radio button
const drinks = ref([
  { id: 1, name: 'None' },
  { id: 2, name: 'Tea' },
  { id: 3, name: 'Coffee' }
]);

const selectedValues = ref([]);
const selectedRadioValues = ref([]);
const myform = ref(null);
const myRadioform = ref(null);

const validateCheckbox = computed(() => {
  return [selectedValues.value.length > 0 || 'Choose at-list one Drink'];
});

const validateRadio = computed(() => {
  return [selectedRadioValues.value.length > 0 || 'Choose at-list one Drink'];
});
function submit() {
  myform.value.validate();
}
function submitRadio() {
  myRadioform.value.validate();
}
function isLast(index) {
  return drinks.value.length - 1 === index;
}
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="6">
      <UiParentCard title="On Type">
        <Form>
          <v-text-field
            v-model="firstname"
            :rules="[rules.required, rules.firstname]"
            label="First Name"
            variant="outlined"
            :color="firstname.length > 2 ? 'success' : 'primary'"
            class="mb-3"
          ></v-text-field>
          <v-text-field
            v-model="lastname"
            :rules="[rules.required, rules.lastname]"
            label="Last Name"
            :color="lastname.length > 2 ? 'success' : 'primary'"
            variant="outlined"
            class="mb-3"
          ></v-text-field>
          <v-text-field
            v-model="email"
            :rules="[rules.required, rules.email]"
            label="E-mail"
            variant="outlined"
            class="mb-3"
          ></v-text-field>
          <v-text-field
            color="primary"
            v-model="password"
            label="Password"
            :rules="[pwdrules.required, pwdrules.min]"
            variant="outlined"
            hint="At least 8 characters"
            type="password"
          /><br />
          <v-btn color="primary" type="submit">Submit</v-btn>
        </Form>
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="6">
      <UiParentCard title="Vee Validation">
        <VeeValidation />
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="6">
      <UiParentCard title="Checkbox">
        <h5 class="mb-3">Choose a Drink</h5>

        <div class="d-inline-flex flex-column ga-4 items-center">
          <div>
            <v-form ref="myform">
              <div v-for="(drink, index) in drinks" :key="drink.id">
                <v-checkbox
                  v-model="selectedValues"
                  :value="drink.id"
                  :label="drink.name"
                  color="primary"
                  :hide-details="!isLast(index)"
                  :rules="validateCheckbox"
                />
              </div>
              <v-btn @click="submit" variant="outlined" color="primary" class="mt-4"> Submit </v-btn>
            </v-form>
          </div>
        </div>
      </UiParentCard>
    </v-col>
    <v-col cols="12" md="6">
      <UiParentCard title="Radio button">
        <h5 class="mb-3">Choose a Drink</h5>

        <div>
          <v-form ref="myRadioform">
            <div class="d-flex ga-4">
              <v-radio-group v-for="drink in drinks" :key="drink.id" v-model="selectedRadioValues" :rules="validateRadio" hide-details>
                <v-radio :value="drink.id" :label="drink.name" color="primary" />
              </v-radio-group>
            </div>
            <p class="text-error mb-4" v-if="selectedRadioValues.length < 1">You have to choose one Drink</p>
            <v-btn @click="submitRadio" variant="outlined" color="primary">Submit</v-btn>
          </v-form>
        </div>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
