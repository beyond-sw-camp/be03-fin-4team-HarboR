import { defineStore } from 'pinia';
import { router } from '@/router';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
const baseUrl = `${import.meta.env.VITE_API_URL}`;

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    // initialize state from local storage to enable user to stay logged in
    /* eslint-disable-next-line @typescript-eslint/ban-ts-comment */
    // @ts-ignore
    user: JSON.parse(localStorage.getItem('user')),
    returnUrl: null
  }),
  actions: {
    async login(email: string | undefined, employeeId: string | undefined, password: string) {
      try {
        // loginData 타입을 확장하여 email과 employeeId를 선택적 속성으로 포함
        const loginData: { email?: string; employeeId?: string; password: string } = { password };

        // 이메일로 로그인하는 경우
        if (email && !employeeId) {
          loginData.email = email;
        }
        // 사원번호로 로그인하는 경우
        else if (!email && employeeId) {
          loginData.employeeId = employeeId;
        } else {
          alert('이메일 또는 사원번호 중 하나를 입력해주세요.');
          return; // 이메일과 사원번호 둘 다 있거나 없는 경우 함수를 종료합니다.
        }

        // API 엔드포인트 결정
        const endPoint = email ? 'EmailLogin' : 'EmployeeIdLogin';
        console.log('이메일' + email);
        console.log('사원번호 ㄴㄴ' + employeeId);
        console.log('비밀번호' + password);
        console.log('어떤걸로 로그인?' + endPoint);
        // 로그인 요청 보내기
        const user = await axios.post(`${baseUrl}/login/account/${endPoint}`, loginData);
        // const user = await axios.post(`http://localhost:8000/login/account/${endPoint}`, loginData);
        const token: string = user.data.result.token;
        if (token) {
          const decoded = jwtDecode(token);
          console.log(decoded);
          localStorage.setItem('token', token);
          localStorage.setItem('employeeId', decoded.sub);
          localStorage.setItem('myEmail', decoded.myEmail);
          localStorage.setItem('role', decoded.role);
          location.href = '/dashboard/default';
        } else {
          console.log('200 ok but no token');
          alert('Login Failed');
        }
      } catch (error) {
        console.log(error);
        alert('로그인 실패');
        return;
      }
    },
    logout() {
      this.user = null;
      localStorage.clear();
      // localStorage.removeItem('user');
      router.push('/auth/login');
    }
  }
});
