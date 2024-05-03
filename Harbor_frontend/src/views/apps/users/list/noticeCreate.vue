<script setup lang="ts">
import { ref, computed } from 'vue';
import axios, { setClientHeaders, setContentTypeHeaders } from '@/utils/axios'; // axios import
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import EditorMenubar from '@/components/forms/plugins/editor/EditorMenubar.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useRouter } from 'vue-router';
import { defineProps, defineEmits } from 'vue';

const baseUrl = `${import.meta.env.VITE_API_URL}`;
const profileImage = ref('');
const router = useRouter();
const token: string | null = localStorage.getItem('token');
const noticetitle = ref('');
const noticeContent = ref('');

const page = ref({ title: '공지사항' });
const breadcrumbs = ref([
  {
    title: '기타',
    disabled: false,
    href: '/noticeList'
  },
  {
    title: '공지사항',
    disabled: true,
    href: '#'
  }
]);

const editor = useEditor({
  extensions: [StarterKit],
  content: ''
});

const goBack = () => {
  router.push('/noticeList'); // router.push를 사용하여 페이지 이동
};

let selectedImage;
const selectedImageName = ref('');
function fileUpload(event) {
  selectedImage = event.target.files[0];
  selectedImageName.value = selectedImage.name;
  const reader = new FileReader();
  reader.onload = () => {
    profileImage.value = reader.result;
  };
  reader.readAsDataURL(selectedImage);
  console.log(selectedImage.name);
}

const props = defineProps({
  selectedDetail: Object || Array
});
const emits = defineEmits(['toggleDetail']);

// 공지사항 생성 함수
async function createNotice() {
  if (!editor.value) {
    console.error('Editor is not initialized');
    return;
  }
  const formData = new FormData();
  const textContent = editor.value.getText();

  const noticeForm = {
    title: noticetitle.value,
    contents: textContent
  };
  const blob = new Blob([JSON.stringify(noticeForm, null, 2)], { type: 'application/json' });

  if (selectedImage) {
    formData.append('file', selectedImage);
  }
  formData.append('request', blob);
  console.log(formData.get('file'));
  console.log(formData.get('request'));
  try {
    setClientHeaders(token);
    setContentTypeHeaders('multipart/form-data');
    const response = await axios.post(`${baseUrl}/login/notice/create`, formData);

    console.log('Notice created successfully:', response);
    router.push('/noticeList');
  } catch (error) {
    console.error('Error creating notice:', error);
  }
}
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-card variant="flat">
    <v-card-item>
      <div class="d-sm-flex align-center">
        <v-btn icon flat @click="goBack" size="small"><ChevronLeftIcon size="18" /></v-btn>
        <v-card-title>공지사항 작성</v-card-title>
        <slot name="action"></slot>
      </div>
    </v-card-item>
    <v-divider></v-divider>
    <v-card-text>
      <slot />

      <v-text-field
        v-model="noticetitle"
        label="제목"
        class="mt-4 mb-4"
        required
        density="comfortable"
        hide-details="auto"
        variant="outlined"
        color="primary"
      ></v-text-field>
      <div v-if="editor">
        <EditorMenubar :editor="editor" />
      </div>
      <!-- <EditorContent :editor="editor" /> -->
      <EditorContent :editor="editor" v-model="noticeContent" />
      <div class="d-flex align-items-center justify-content-between mt-3">
        <span style="background: transparent; border: none; height: 36px; line-height: 36px; padding: 0; margin-bottom: 0">
          {{ selectedImageName }}
        </span>
        <v-btn class="align-items-right" style="margin-left: 10px; position: absolute; right: 135px; height: 36px">
          <input
            type="file"
            accept="/*"
            @change="fileUpload"
            style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer"
          />
          <v-icon>mdi-attachment</v-icon>
        </v-btn>
        <v-btn
          color="blue darken-2"
          class="white--text"
          style="margin-left: 10px; position: absolute; right: 30px; height: 36px; line-height: 36px"
          @click="createNotice"
        >
          작성하기
        </v-btn>
      </div>
    </v-card-text>
  </v-card>
</template>
