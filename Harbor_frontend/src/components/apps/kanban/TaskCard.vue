<script setup lang="ts">
import { shallowRef } from 'vue';
const props = defineProps({
  task: Object
});
const items = shallowRef([
  {
    title: 'Edit'
  },
  {
    title: 'Delete'
  }
]);
</script>
<template>
  <v-card variant="flat" class="cursor-move">
    <v-card-text class="pa-4">
      <div class="d-flex align-start ga-2">
        <div class="d-block text-truncate">
          <h5 class="text-h5 text-truncate">{{ props.task?.title }}</h5>
          <a class="text-secondary text-subtitle-2 d-flex align-center cursor-pointer">
            <NotebookIcon size="14" /> User Story #{{ props.task?.userStory }}
          </a>
        </div>
        <v-btn icon size="small" variant="text" class="ml-auto">
          <DotsVerticalIcon size="14" />
          <v-menu activator="parent" class="rounded-md">
            <v-list>
              <v-list-item v-for="(item, index) in items" :key="index" :value="index">
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-btn>
      </div>
      <img v-if="task?.image" :src="props.task?.image" class="w-100 mt-3 rounded-sm" alt="Avatar" />
    </v-card-text>
  </v-card>
</template>
<style>
.cursor-move {
  cursor: move;
}
</style>
