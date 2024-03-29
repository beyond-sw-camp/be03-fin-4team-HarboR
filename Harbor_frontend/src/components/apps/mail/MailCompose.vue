<script setup lang="ts">
import { ref } from 'vue';
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import EditorMenubar from '@/components/forms/plugins/editor/EditorMenubar.vue';
const ccBcc = ref(false);
const editor = useEditor({
  extensions: [StarterKit],
  content: ``
});
</script>
<template>
  <v-card elevation="0">
    <v-card-text class="pb-4">
      <v-row>
        <v-col sm="12" cols="12">
          <div class="d-flex align-center">
            <h4>New Message</h4>
            <v-btn icon size="small" class="ml-auto" elevation="0" @click="$emit('closeDialog')"><xIcon size="16" /></v-btn>
          </div>
        </v-col>
        <v-col sm="12" cols="12">
          <p class="text-secondary text-right cursor-pointer" @click="ccBcc = !ccBcc">CC & BCC</p>
        </v-col>
        <v-col sm="12" cols="12">
          <v-text-field label="To" required variant="outlined" color="primary" hide-details></v-text-field>
        </v-col>
        <v-col sm="12" cols="12">
          <v-text-field label="Subject" required variant="outlined" color="primary" hide-details></v-text-field>
        </v-col>
        <v-col sm="12" cols="12" v-if="ccBcc">
          <div>
            <v-text-field label="CC" required variant="outlined" color="primary" hide-details class="mb-5"></v-text-field>
            <v-text-field label="BCC" required variant="outlined" color="primary" hide-details></v-text-field>
          </div>
        </v-col>
        <v-col sm="12" cols="12">
          <div v-if="editor">
            <EditorMenubar :editor="editor" />
          </div>
          <editor-content :editor="editor" />
        </v-col>
        <v-col sm="12" cols="12">
          <div class="d-flex">
            <v-btn icon elevation="0"><UploadIcon size="16" /></v-btn>
            <v-btn icon elevation="0"><v-icon>mdi-attachment</v-icon></v-btn>
            <v-btn elevation="0" color="primary" class="ml-auto">Reply</v-btn>
          </div>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>
