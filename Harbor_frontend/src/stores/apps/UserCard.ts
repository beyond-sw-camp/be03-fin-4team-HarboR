import { defineStore } from 'pinia';
// project imports
import axios, { setClientHeaders } from '@/utils/axios';
const baseUrl = `${import.meta.env.VITE_API_URL}`;
export const useUserCardStore = defineStore({
  id: 'userCard',
  state: () => ({
    cards: [],
    list: [],
    list2: [],
    noticelist: []
  }),
  getters: {},
  actions: {
    // Fetch cards from action
    async fetchCards() {
      try {
        // const response = await axios.get('/api/details-card/list');
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/employee/get/list`);
        this.cards = response.data;
      } catch (error) {
        alert(error);
      }
    },
    // Fetch filtered mail from action
    async filterCards(filter: string) {
      try {
        const response = await axios.post('/api/details-card/filter', { filter });
        this.cards = response.data.results;
      } catch (error) {
        alert(error);
      }
    },
    // Fetch cards from action
    async fetchlistCards(page: number, searchField: string | null, searchValue: string | null) {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/employee/get/list?page=${page}&${searchField}=${searchValue}`);
        this.list = response.data.result;
      } catch (error) {
        alert(error);
      }
    },
    // Fetch cards from action
    async fetchlist2Cards() {
      try {
        const response = await axios.get(`${baseUrl}/employee/get/list`);
        this.list2 = response.data;
      } catch (error) {
        alert(error);
      }
    },
    async noticeCards() {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/login/notice/list`);
        this.noticelist = response.data.result.content;
      } catch (error) {
        alert(error);
      }
    }
  }
});
