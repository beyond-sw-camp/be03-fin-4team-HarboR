<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios, {setClientHeaders} from '@/utils/axios'; // axios import
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import EditorMenubar from '@/components/forms/plugins/editor/EditorMenubar.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import {useRouter} from 'vue-router';
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
  content: '',
});

const goBack = () => {
  router.push('/noticeList'); // router.push를 사용하여 페이지 이동
};

let selectedImage;
function fileUpload(event) {
  selectedImage = event.target.files[0];
  const reader = new FileReader();
  console.log(selectedImage)
  reader.onload = () => {
    profileImage.value = reader.result;
  };
  reader.readAsDataURL(selectedImage);
}

const props = defineProps({
  selectedDetail: Object || Array
})
const emits = defineEmits(['toggleDetail']);




// 공지사항 생성 함수
async function createNotice() {
  if (!editor.value) {
    console.error('Editor is not initialized');
    return;
  }
  const formData = new FormData();
  const htmlContent = editor.value.getHTML();
  const textContent = editor.value.getText();

  const noticeForm = {
    "title": noticetitle.value,
    "contents": textContent
  };  
const blob = new Blob([JSON.stringify(noticeForm, null, 2)], {type : 'application/json'});
  
  if (selectedImage) {
    formData.append('file', selectedImage);
  }
  console.log(htmlContent);
  formData.append('request', blob);
  try {
    setClientHeaders(token);
    const response = await axios.post(`${baseUrl}/login/notice/create`, formData);
    
    console.log('Notice created successfully:', response);
    router.push('/noticeList')
  } catch (error) {
    console.error('Error creating notice:', error);
  }
}

</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <UiParentCard title="공지사항 작성">
    <v-btn icon flat @click="goBack" size="small"><ChevronLeftIcon size="18" /></v-btn>

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
    <div class="text-right mt-3">
      <!-- '첨부파일' 버튼을 '작성하기' 버튼의 왼쪽으로 이동 -->
      <v-btn style="margin-right: 18px; position: relative; overflow: hidden;">
        <input type="file" accept="image/*" @change="fileUpload"
          style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; opacity: 0; cursor: pointer;">
        <v-icon>mdi-attachment</v-icon>
      </v-btn>
      <v-btn color="blue darken-2" class="white--text" @click="createNotice">
        작성하기
      </v-btn>
    </div>
  </UiParentCard>
</template>


