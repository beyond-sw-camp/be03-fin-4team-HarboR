<script setup>
import { ref, watch } from 'vue';
const props = defineProps({
  dialogs: Object
});
const text = ref(
  'If youre coming from a Series 3, the choice is more difficult. The Series 4 is undoubtedly the better device. And if you care about fall detection, the ECG app, or a larger screen then upgrading makes sense. But I think most people will remain satisfied with their Series 3s.'
);
const items = ref(['Pending', 'Published', 'Confirm']);
const ratting = ref(3.5);

const dialogVisible = ref(props.dialogs.dialog);

// Watch for changes in the prop and update the local state accordingly
watch(
  () => props.dialogs.dialog,
  (newValue) => {
    dialogVisible.value = newValue;
  }
);
</script>
<template>
  <v-dialog v-model="dialogVisible" max-width="400">
    <v-card>
      <v-card-text>
        <h3 class="text-h3 mb-8">Edit Review</h3>
        <v-text-field variant="outlined" label="Product" model-value="Apple Watch Series 4"></v-text-field>
        <v-text-field variant="outlined" label="Author" model-value="Joseph William"></v-text-field>
        <v-textarea variant="outlined" label="Text of Review" v-model="text"> </v-textarea>
        <div>Rating</div>
        <v-rating
          background-color="orange lighten-3"
          color="warning"
          density="compact"
          half-increments
          hover
          size="16"
          v-model="ratting"
        ></v-rating>
        <br />
        <v-select :items="items" label="Outlined variant" variant="outlined" model-value="Pending" class="mt-5"></v-select>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" variant="flat" @click="$emit('close')">Create</v-btn
        ><v-btn variant="flat" @click="$emit('close')">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
