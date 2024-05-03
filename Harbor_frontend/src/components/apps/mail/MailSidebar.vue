<script setup lang="ts">
import { ref, shallowRef, onMounted, computed } from 'vue';
import { useMailStore } from '@/stores/apps/mail';
import MailCompose from './MailCompose.vue';

const store = useMailStore();

onMounted(() => {
  store.fetchMails();
  store.filterMails(store.activeFilter);
});

type MailItem = {
  avatar: string;
  name: string;
  location: string;
  follow: number;
  isRead: boolean;
  spam: boolean;
  sent: boolean;
  trash: boolean;
  // Add other properties as needed
};
const getCounts = computed<MailItem[]>(() => {
  return store.mails;
});

type ItemsType = {
  text?: string;
  icon?: string;
  label?: object;
  iconColor?: string;
  header?: string;
  divider?: boolean;
  slug?: string;
};

const filter = computed(() => {
  return store.activeFilter;
});

const handleFilter = async (string: string) => {
  store.SelectFilter(string);
  await store.filterMails(string);
};
const allCount = computed(() => {
  return getCounts.value.filter((i: MailItem) => !i.isRead).length;
});
const inboxCount = computed(() => {
  return getCounts.value.filter((i: MailItem) => !i.spam && !i.isRead).length;
});
const sentCount = computed(() => {
  return getCounts.value.filter((i: MailItem) => i.sent && !i.isRead).length;
});

const trashCount = computed(() => {
  return getCounts.value.filter((i: MailItem) => i.trash && !i.isRead).length;
});
const items = shallowRef<ItemsType[]>([
  { text: 'All Mail', slug: 'all', icon: 'mdi-email-outline', label: allCount },
  { text: 'Inbox', slug: 'inbox', icon: 'mdi-inbox', label: inboxCount },
  { text: 'Sent', slug: 'sent', icon: 'mdi-send-outline', label: sentCount },
  { text: 'Draft', slug: 'draft', icon: 'mdi-notebook-outline' },
  { text: 'Spam', slug: 'spam', icon: 'mdi-alert-outline' },
  { text: 'Trash', slug: 'trash', icon: 'mdi-delete-outline', label: trashCount },
  { divider: true },
  { header: 'Filters' },
  { text: 'Starred', slug: 'starred', icon: 'mdi-star-outline' },
  { text: 'Important', slug: 'important', icon: 'mdi-bookmark-outline' },
  { divider: true },
  { header: 'Label' },
  { text: 'Promotions', slug: 'promotions', icon: 'mdi-label-outline', iconColor: 'text-primary' },
  { text: 'Forums', slug: 'forums', icon: 'mdi-label-outline', iconColor: 'text-warning' }
]);
const dialog = ref(false);
</script>
<template>

</template>
<style lang="scss">
.v-list-item__prepend > .v-icon.icon-width {
  margin-inline-end: 15px;
}
</style>
