import { defineStore } from 'pinia';

// 토큰의 만료 시간을 가져오는 함수
function getExpiredTime() {
  const token = localStorage.getItem('token');
  if (!token) return null;

  // 토큰을 파싱하여 만료 시간을 가져옵니다.
  const { exp } = JSON.parse(atob(token.split('.')[1]));
  return exp;
}

// 상태를 관리하는 스토어를 정의합니다.
export const useTokenStore = defineStore({
  id: 'timerStore',
  state: () => ({
    minute: 0,
    second: 0
  }),
  actions: {
    updateExpiredTime() {
      const expiredTime = getExpiredTime();
      if (!expiredTime) return;

      const now = Date.now() / 1000;
      const remainingTime = expiredTime - now;
      if (remainingTime < 1) {
        alert('세션이 만료되었습니다.');
        localStorage.clear();
        location.reload();
      }
      this.minute = Math.floor(remainingTime / 60);
      this.second = Math.floor(remainingTime % 60);
    },
    startTimer() {
      this.updateExpiredTime();
      setInterval(() => this.updateExpiredTime(), 1000);
    }
  }
})