import { defineStore } from 'pinia';
import axios, { setClientHeaders } from '@/utils/axios';

const baseUrl = `${import.meta.env.VITE_API_URL}`;

export const ApproveStore = defineStore({
  id: 'ApproveCard',
  state: () => ({
    cards: [],
    cards2: [],
    card_len_false: 0,
    card_len_all: 0
  }),
  getters: {},
  actions: {
    async fetchlistCards() {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/login/admin/employeelist/`);
        this.cards = response.data.result.content;
        this.card_len_false = this.cards.length;
      } catch (error) {
        alert(error);
      }
    },
    async getCountAll() {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/login/admin/getcountallemployee`);
        this.cards2 = response.data.result.content;
        this.card_len_all = this.cards2.length;
        console.log(this.card_len_all);
      } catch (error) {
        alert(error);
      }
    },
    async uploadAttendanceFile(file: File) {
      try {
        setClientHeaders();
        const formData = new FormData();
        formData.append('file', file);
        console.log('1234');
        const response = await axios.post(`${baseUrl}/employee/personnel/create`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log('5678');
        console.log(response);
      } catch (error) {
        console.log(error.error_message);
        alert(error.error_message);
      }
    },
    async uploadNewMemberFile(file: File) {
      try {
        setClientHeaders();
        const formData = new FormData();
        formData.append('file', file);
        const response = await axios.post(`${baseUrl}/employee/create/basic`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
      } catch (error) {
        alert(error.error_message);
      }
    },
    async getAttendanceList() {
      try {
        setClientHeaders();
        const response = await axios.get(`${baseUrl}/employee/personnel/findAll`);
        this.cards = response.data.result.content;
        this.card_len_false = this.cards.length;
        return this.cards;
      } catch (error) {
        alert(error);
      }
    }
  }
});
