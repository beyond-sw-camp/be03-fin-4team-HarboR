import { defineStore } from 'pinia';
import axios, { setClientHeaders } from '@/utils/axios';

const baseUrl = `${import.meta.env.VITE_API_URL}`;

export const MonthStore = defineStore({
    id: 'MonthCard',
    state: () => ({
      cards: [],
    }),
    getters: {},
    actions: {
      // 월별 카드 가져오기
      async fetchlistCards(token: string | null, selectedMonth: string) {
        try {
          setClientHeaders(token);
          const response = await axios.post(`${baseUrl}/total/commute/Monthlyattendence`, { month: selectedMonth });
          this.cards = response.data.result;
        } catch (error) {
          alert(error);
        }
      },
    }
});