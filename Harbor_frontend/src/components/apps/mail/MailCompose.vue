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
  
</template>
