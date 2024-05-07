// sidebarItem.ts

import {
  BrandGravatarIcon,
  ClipboardTextIcon,
  HomeIcon,
  MenuIcon,
  CoinIcon,
  CoinsIcon,
  CoinOffIcon,
  CircleIcon,
  ShapeIcon,
  CircleOffIcon,
  BoxMultipleIcon,
  FormsIcon,
  ChartDotsIcon,
  PictureInPictureIcon,
  ClipboardCheckIcon,
  WindmillIcon,
  Receipt2Icon,
  TypographyIcon,
  ShadowIcon,
  PaletteIcon,
  LayoutGridAddIcon,
  BrandFramerIcon,
  KeyIcon,
  BugIcon,
  TableIcon,
  BrushIcon,
  ToolsIcon,
  BasketIcon,
  MessagesIcon,
  UserCheckIcon,
  NfcIcon,
  MailIcon,
  LayoutKanbanIcon,
  CalendarIcon,
  DashboardIcon,
  DeviceAnalyticsIcon,
  ChartArcsIcon,
  ClipboardIcon,
  ChartInfographicIcon,
  BellRingingIcon,
  PhoneCallIcon,
  QuestionMarkIcon,
  ShieldLockIcon,
  BrandChromeIcon,
  HelpIcon,
  SitemapIcon
} from 'vue-tabler-icons';

export interface menu {
  header?: string;
  title?: string;
  icon?: object;
  to?: string;
  getURL?: boolean;
  divider?: boolean;
  chip?: string;
  chipColor?: string;
  chipVariant?: string;
  chipIcon?: string;
  children?: menu[];
  disabled?: boolean;
  type?: string;
  subCaption?: string;
}

const sidebarItem: menu[] = [
  
  {
    title: '홈',
    icon: HomeIcon,
    to: '/dashboard/default'
  },

  { header: '인사관리' },
  {
    title: '사원 목록',
    icon: DeviceAnalyticsIcon,
    to: '/app/user/list'
  },
  { divider: true },
  { header: '근태관리' },
  {
    title: '내 일정관리',
    icon: ChartArcsIcon,
    to: '/schedule/qalendar'
  },
  {
    title: '월별 캘린더',
    icon: ClipboardIcon,
    to: '/team/schedule'
  },
  { divider: true },
  { header: '전자결재 관리' },
  {
    title: '전자결재 신청',
    icon: ChartArcsIcon,
    to: '/attendance/create'
  },
  {
    title: '전자결재 상신',
    icon: UserCheckIcon,
    to: '/attendance/list/req',
  },
  {
    title: '전자결재 수신',
    icon: UserCheckIcon,
    to: '/attendance/list/res',
  },
  { divider: true },
  { header: '급여 명세 관리' },
  {
    title: '급여목록조회',
    icon: CoinIcon,
    to: '/app/user/salarylist'
  },
  {
    title: '퇴직금조회 ',
    icon: CoinOffIcon,
    to: '/severancePay'
  },
  {
    title: '급여 지급버튼 ',
    icon: CoinsIcon,
    to: '/app/user/salaryCreate'
  },
  { header: '기타', },
  {
    title: '공지사항',
    icon: ClipboardTextIcon,
    to: '/noticeList'
  },
];

// Check role from localStorage
const role = localStorage.getItem('role');
if (role === 'ADMIN') {
  sidebarItem.splice(3, 0, {
    title: '사원 승인(인사과)',
    icon: DeviceAnalyticsIcon,
    to: '/dashboard/analytics'
  });
}

export default sidebarItem;
