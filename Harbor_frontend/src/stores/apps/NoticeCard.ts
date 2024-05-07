import { defineStore } from 'pinia';
// project imports
import axios, { setClientHeaders } from '@/utils/axios';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
export const useUserCardStore = defineStore({
  id: 'userCard',
  state: () => ({
    noticelist: [],
    noticedetail: [],
    selectNotice: { noticeId: '', noticeTitle: '', noticeContent: '', filePath: '' }
  }),
  getters: {},
  actions: {
    async noticeCards() {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/login/notice/list`);
        this.noticelist = response.data.result.content;
      } catch (error) {
        alert(error);
      }
    },
    async noticedetailCards(noticeId: string) {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/login/notice/detail/${noticeId}`);
        this.noticedetail = response.data.result.content;
      } catch (error) {
        alert(error);
      }
    },
    async saveSelectNotice(notice: { noticeId: string; title: string; contents: string; filePath: string }) {
      this.selectNotice.noticeId = notice.noticeId;
      this.selectNotice.noticeTitle = notice.title;
      this.selectNotice.noticeContent = notice.contents;
      this.selectNotice.filePath = notice.filePath;
    }
  }
});
