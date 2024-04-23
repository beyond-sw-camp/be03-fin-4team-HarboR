import { defineStore } from 'pinia';
export const useCodeStore = defineStore({
  id: 'code',
  state: () => ({
    // initialize state from local storage to enable user to stay logged in
    /* eslint-disable-next-line @typescript-eslint/ban-ts-comment */
    // @ts-ignore
    user: JSON.parse(localStorage.getItem('user')),
    returnUrl: null
  }),
  actions: {
    // 직위 - 호봉 X 
    getPositionNameByCode(position : string) {
      const code = parseInt(position.slice(1), 10);
      if (code >= 11 && code <= 15) {
        return '사원';
      } else if (code >= 21 && code <= 25) {
        return '주임';
      } else if (code >= 31 && code <= 35) {
        return '대리';
      } else if (code >= 41 && code <= 45) {
        return '과장';
      } else if (code >= 51 && code <= 55) {
        return '차장';
      } else if (code >= 61 && code <= 65) {
        return '부장';
      } else {
        return 'Unknown'; // Handle other cases if necessary
      }
    },
    // 직위 - 호봉 O 
     getPositionNumberByCode(position: string) {
      // 직위 코드의 첫 번째 문자(S)는 무시하고, 두 번째 문자로 직위를, 마지막 두 숫자로 호봉을 결정합니다.
      const positionCode = position.charAt(1);
      const level = parseInt(position.substring(2), 10);
    
      let positionName: string;
    
      switch (positionCode) {
        case '1':
          positionName = '사원';
          break;
        case '2':
          positionName = '주임';
          break;
        case '3':
          positionName = '대리';
          break;
        case '4':
          positionName = '과장';
          break;
        case '5':
          positionName = '차장';
          break;
        case '6':
          positionName = '부장';
          break;
        default:
          return '알 수 없는 직위';
      }
      return `${positionName} ${level}호봉`;
    },
    // 은행
    getBankNameByCode(code: string): string {
      const bankMap: { [key: string]: string } = {
        B01: '국민은행',
        B02: '우리은행',
        B03: '신한은행',
        B04: '하나은행',
        B05: '기업은행',
        B06: 'NH농협은행',
        B07: '산업은행',
        B08: '한국수출입은행',
        B09: '한국투자공사',
        B10: 'BNK금융그룹'
      };
      return bankMap[code] || '알 수 없는 은행';
    },
    getDutyNameByCode(code: string): string {
      const jobMap: { [key: string]: string } = {
        D01: '아날로그 엑스퍼트',
        D02: '디지털 엑스퍼트',
        D03: '시스템 아키텍처 설계자',
        D04: '운영체제 엔지니어',
        D05: '네트워크 엔지니어',
        D06: '데이터베이스 엔지니어',
        D07: '미들웨어 개발자',
        D08: '미들웨어 시스템 관리자',
        D09: '어플리케이션 소프트웨어 개발자',
        D10: '어플리케이션 품질보증 엔지니어',
        D11: '어플리케이션 소프트웨어 아키텍트',
        D12: '어플리케이션 프로젝트 매니저',
        D13: '어플리케이션 유지보수 엔지니어',
        D14: '기타 소프트웨어 매니저',
        D15: '기계 관리자',
        D16: 'ICT 시스템 분석 및 설계자',
        D17: 'ICT 프로그래밍 개발자',
        D18: 'ICT 버전 관리자',
        D19: 'ICT 품질 관리자',
        D20: 'ICT 배포 관리자',
        D21: 'ICT 시스템 모니터링 관리자',
        D22: 'ICT 고객 관리자',
        D23: 'ICT 보안 관리자',
        D24: 'IPS 보안 분석가',
        D25: 'IPS 보안 엔지니어',
        D26: 'IPS 보안 아키텍트',
        D27: 'IPS 보안 운영 및 관리자',
        D28: 'IPS 보안 컨설턴트',
        D29: '인사담당자',
        D30: '재무 담당자',
        D31: '문서 관리자',
        D32: '시설 관리자',
        D33: '자문 및 컨설팅 담당자',
        D34: 'IDC 네트워크 엔지니어',
        D35: 'IDC 시스템 엔지니어',
        D36: 'IDC 보안 엔지니어',
        D37: 'IDC 데이터베이스 관리자',
        D38: 'IDC 시설 관리자',
        D39: 'R&D 연구원',
        D40: 'R&D 개발자',
        D41: 'R&D 설계 엔지니어',
        D42: 'R&D 프로젝트 관리자',
        D43: 'R&D 품질 보증 엔지니어',
        D44: '솔루션 아키텍트',
        D45: '시스템 아키텍트',
        D46: '데이터 아키텍트',
        D47: '기업 아키텍트',
        D48: '마케팅 전략 및 기획자',
        D49: '디지털 마케터',
        D50: '콘텐츠 마케터',
        D51: '고객 관리 및 리텐션'
      };
      return jobMap[code] || '직위 확인 불가';
    },
    getDepartmentNameByCode(code: string): string {
      const teamMap: { [key: string]: string } = {
        E01: '개발 1팀',
        E02: '개발 2팀',
        E03: '개발 3팀',
        E04: '영업 1팀',
        E05: '영업 2팀',
        E06: '영업 3팀',
        E07: '기획 1팀',
        E08: '기획 2팀',
        E09: '기획 3팀',
        E10: '인사 1팀',
        E11: '인사 2팀',
        E12: '인사 3팀',
        E13: '마케팅 1팀',
        E14: '마케팅 2팀',
        E15: '마케팅 3팀'
      };
      return teamMap[code] || '알 수 없는 팀';
    },
    getTeamNameByCode(code: string): string {
      const departmentMap: { [key: string]: string } = {
        T01: '개발팀',
        T02: '영업팀',
        T03: '기획팀',
        T04: '인사팀',
        T05: '마케팅팀'
      };
      return departmentMap[code] || '알 수 없는 부서';
    },
    getNameByCodes(name: string): string {
      let prefix = '';
      switch (name) {
        case '사원':
          prefix = 'S1';
          break;
        case '주임':
          prefix = 'S2';
          break;
        case '대리':
          prefix = 'S3';
          break;
        case '과장':
          prefix = 'S4';
          break;
        case '차장':
          prefix = 'S5';
          break;
        case '부장':
          prefix = 'S6';
          break;
        default:
          prefix = ''; // 해당하는 직위가 없을 때 빈 문자열 반환
          break;
      }
      return prefix;
    }
    
  }
});
