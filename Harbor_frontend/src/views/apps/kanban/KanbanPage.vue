<script setup lang="ts">
import UiParentCard from '@/components/shared/UiParentCard.vue';
import KanbanList from '@/components/apps/kanban/KanbanList.vue';
</script>
<template>
  <v-row>
    <v-col lg="12">
      <UiParentCard title="Kanban">
        <KanbanList />
      </UiParentCard>
    </v-col>
  </v-row>
</template>
