<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import { usePostsStore } from '@/stores/apps/posts';
import uniqueId from 'lodash/uniqueId';
import type { Reply } from '@/types/user/post';

const store = usePostsStore();
const props = defineProps({
  comments: Object || Array,
  postId: String
});
const replyValue = ref('');
const items = shallowRef([{ title: 'Edit' }, { title: 'Delete' }]);

const onSubmit = async (id: string, commentid: string, reply: Reply | string) => {
  const replyId = uniqueId('#REPLY_');
  const newReply = {
    id: replyId,
    profile: {
      id: uniqueId('#REPLY_'),
      avatar: props.comments?.profile.avatar,
      name: props.comments?.profile.name,
      time: 'now'
    },
    data: {
      comment: typeof reply === 'string' ? reply : reply?.data?.comment ?? '',
      likes: {
        like: false,
        value: 0
      },
      replies: []
    }
  };
  store.addReply(id, commentid, newReply);
  replyValue.value = '';
};
const showReplyBox = ref(false);
const toggleReplybox = () => {
  return (showReplyBox.value = !showReplyBox.value);
};
</script>

<template>
  <v-card variant="flat" elevation="0" class="bg-gray100 mb-3 pa-5">
    <div class="d-flex ga-4 align-center">
      <img :src="comments?.profile.avatar" width="30" alt="avatar" />
      <div class="d-block d-sm-flex align-center ga-4">
        <h5 class="text-h5">{{ comments?.profile.name }}</h5>
        <span class="text-subtitle-2 opacity-50">
          <CircleIcon size="8" fill="inherit" class="opacity-50" />
          {{ comments?.profile.time }}
        </span>
      </div>
      <v-menu>
        <template v-slot:activator="{ props }">
          <v-btn icon size="small" variant="text" color="secondary" class="ml-auto" v-bind="props"><DotsVerticalIcon size="14" /></v-btn>
        </template>

        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index" :value="index">
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <div class="py-3 text-body-1">
      {{ comments?.data.comment }}
    </div>
    <!---Like and comment count-->
    <div class="my-1 d-flex ga-4">
      <v-btn color="primary" variant="text" size="small" prepend-icon="mdi-thumb-up-outline">
        {{ comments?.data && comments?.data.likes && comments?.data.likes.value }} Like
      </v-btn>
      <v-btn color="secondary" variant="text" size="small" prepend-icon="mdi-reply" @click="toggleReplybox"> Reply </v-btn>
    </div>
  </v-card>

  <div v-if="comments?.data.replies" class="ml-10">
    <div v-for="reply in comments?.data.replies" :key="reply.name">
      <v-card variant="flat" elevation="0" class="bg-gray100 mb-3 pa-5">
        <div class="d-flex ga-4 align-center">
          <img :src="reply.profile.avatar" width="30" alt="avatar" />
          <div class="d-block d-sm-flex align-center ga-4">
            <h5 class="text-h5">{{ reply.profile.name }}</h5>
            <span class="text-subtitle-2 opacity-50">
              <CircleIcon size="8" fill="inherit" class="opacity-50" />
              {{ reply.profile.time }}
            </span>
          </div>
          <v-menu>
            <template v-slot:activator="{ props }">
              <v-btn icon size="small" variant="text" color="secondary" class="ml-auto" v-bind="props"
                ><DotsVerticalIcon size="14"
              /></v-btn>
            </template>

            <v-list>
              <v-list-item v-for="(item, index) in items" :key="index" :value="index">
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <div class="py-3 text-body-1">
          {{ reply.data.comment }}
        </div>
        <!---Like and comment count-->
        <div class="my-1 d-flex ga-4">
          <v-btn color="primary" variant="text" size="small" prepend-icon="mdi-thumb-up-outline">
            {{ reply.data && reply.data.likes && reply.data.likes.value }} Like
          </v-btn>
          <v-btn color="secondary" variant="text" size="small" prepend-icon="mdi-reply" @click="toggleReplybox"> Reply </v-btn>
        </div>
      </v-card>
    </div>
  </div>
  <div class="d-block d-sm-flex ga-4 align-center mb-5 ml-10" v-if="showReplyBox">
    <img :src="comments?.profile.avatar" width="40" class="flex-shrink-0 d-none d-sm-block" alt="avatar" />
    <v-text-field variant="outlined" color="primary" v-model="replyValue" label="Write Reply" hide-details></v-text-field>
    <v-btn
      color="secondary"
      class="mt-3 mt-sm-0"
      variant="flat"
      size="large"
      @click="postId && comments && comments.id && onSubmit(postId, comments.id, replyValue)"
    >
      Reply
    </v-btn>
  </div>
</template>
