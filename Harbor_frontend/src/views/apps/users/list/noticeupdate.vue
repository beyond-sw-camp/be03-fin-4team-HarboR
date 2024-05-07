<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useEditor, EditorContent } from '@tiptap/vue-3';
import StarterKit from '@tiptap/starter-kit';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import EditorMenubar from '@/components/forms/plugins/editor/EditorMenubar.vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { useUserCardStore } from '@/stores/apps/NoticeCard';
import { useRouter } from 'vue-router';
import axios from '@/utils/axios';

const router = useRouter();
const store = useUserCardStore();
const noticeId = store.$state.selectNotice.noticeId;
const noticeTitle = store.$state.selectNotice.noticeTitle;
const noticeContent = store.$state.selectNotice.noticeContent;
const filePath = store.$state.selectNotice.filePath;
const baseUrl = `${import.meta.env.VITE_API_URL}`;

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

const editItem = async () => {
  console.log("1:",noticeId,"2:",noticeTitle,"3:",noticeContent,"4:",filePath)
  try {
    const response = await axios.patch(`${baseUrl}/login/notice/update/${noticeId}`, {
      title: noticeTitle,
      content: noticeContent,
      filePath: filePath,
    });
    console.log('Notice updated successfully:', response);
    router.push('/noticeList');
  } catch (error) {
    console.error('Error updating notice:', error);
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
    <!-- EditorContent의 v-model을 제거하고, 에디터가 업데이트 될 때 마다 noticeContent를 업데이트하는 로직 필요 -->
    <EditorContent :editor="editor" :value="noticeContent" />
    <div class="text-right mt-3">
      <v-btn color="blue darken-2" class="white--text" @click="editItem">
        수정하기
      </v-btn>
    </div>
  </UiParentCard>
</template>
