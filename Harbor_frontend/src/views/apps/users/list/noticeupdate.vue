<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import EditorMenubar from '@/components/forms/plugins/editor/EditorMenubar.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useUserCardStore } from '@/stores/apps/NoticeCard';
import { useRouter } from 'vue-router';
import axios, { setClientHeaders, setContentTypeHeaders } from '@/utils/axios';
import { setUncaughtExceptionCaptureCallback } from 'process';

const router = useRouter();
const store = useUserCardStore();
const noticeId = store.$state.selectNotice.noticeId;
const noticeTitle = ref(store.$state.selectNotice.noticeTitle);
const noticeContent = store.$state.selectNotice.noticeContent;
const beforeFilePath = store.$state.selectNotice.filePath.split('index/')[1];
const filePath = ref(store.$state.selectNotice.filePath);
const baseUrl = `${import.meta.env.VITE_API_URL}`;

const splitUrl = ref(filePath.value.split('/'));
const fileName = splitUrl.value.pop();
const splitFileName = fileName.split('_');
const finalFileName = splitFileName.slice(1).join('_');
const decodedFileName = ref(decodeURIComponent(finalFileName));

const editor = useEditor({
  extensions: [StarterKit],
  content: noticeContent
});

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

const goBack = () => {
  router.push('/noticeList');
};

let selectedImage;
function fileUpload(event) {
  selectedImage = event.target.files[0];
  decodedFileName.value = selectedImage.name;
  const reader = new FileReader();
  reader.onload = () => {
    profileImage.value = reader.result;
  };
  reader.readAsDataURL(selectedImage);
}

const editItem = async () => {
  if (!editor.value) {
    console.error('Editor is not initialized');
    return;
  }
  const formData = new FormData();
  const textContent = editor.value.getText();
  console.log(textContent);
  const noticeForm = {
    title: noticeTitle.value,
    contents: textContent,
    fileName: beforeFilePath
  };
  const blob = new Blob([JSON.stringify(noticeForm, null, 2)], { type: 'application/json' });

  if (selectedImage) {
    formData.append('file', selectedImage);
  }
  formData.append('request', blob);
  console.log(formData.get('file'));
  console.log(formData.get('request'));
  try {
    setClientHeaders();
    setContentTypeHeaders('multipart/form-data');
    const response = await axios.patch(`${baseUrl}/login/notice/update/${noticeId}`, formData);
    if (response.status == 413) throw setUncaughtExceptionCaptureCallback(() => alert('파일의 용량이 너무 큽니다.'));
    console.log('Notice created successfully:', response);
    router.push('/noticeList');
  } catch (error) {
    alert('파일의 용량이 너무 큽니다.');
    console.error('Error creating notice:', error);
  }
};
</script>
<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <UiParentCard title="공지사항 수정하기">
    <v-btn icon flat @click="goBack" size="small"><ChevronLeftIcon size="18" /></v-btn>
    <v-text-field
      v-model="noticeTitle"
      @input="$emit('update:noticeTitle', $event)"
      label="공지사항 제목"
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
    <EditorContent :editor="editor" v-model="noticeContent" />
    <div class="d-flex align-items-center justify-content-between mt-3">
      <span style="background: transparent; border: none; height: 36px; line-height: 36px; padding: 0; margin-bottom: 0">
        {{ decodedFileName }}
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
        style="margin-left: 10px; position: absolute; right: 30px; height: 36px; line-height: 36px"
        class="white--text"
        @click="editItem"
      >
        수정하기
      </v-btn>
    </div>
  </UiParentCard>
</template>
