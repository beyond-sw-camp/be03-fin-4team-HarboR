import {
  BrandGravatarIcon,
  ClipboardTextIcon,
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
  { header: '인사관리' },
  {
    title: '내정보',
    icon: DashboardIcon,
    to: '/dashboard/default'
  },
  {
    title: '사원 목록',
    icon: DeviceAnalyticsIcon,
    to: '/app/user/list'
  },
  {
    title: '사원 승인(인사과)',
    icon: DeviceAnalyticsIcon,
    to: '/dashboard/analytics'
  },
  { divider: true },
  { header: '근태관리' },
  {
    title: '내 일정관리',
    icon: ChartArcsIcon,
    to: '/widget/statistics'
  },
  {
    title: '월별 캘린더',
    icon: ClipboardIcon,
    to: '/widget/data'
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
    // children: [
    //   {
    //     title: '결재 진행',
    //     icon: CircleIcon,
    //     to: '/app/user/social/posts'
    //   },
    //   {
    //     title: '결재 완료',
    //     icon: CircleIcon,
    //     to: '/app/user/account-profile',
    //   },
    // ]
  },
  {
    title: '전자결재 수신',
    icon: UserCheckIcon,
    to: '/attendance/list/res',
    // children: [
    //   {
    //     title: '결재 요청',
    //     icon: CircleIcon,
    //     to: '/app/user/social/posts'
    //   },
    //   {
    //     title: '결재 내역',
    //     icon: CircleIcon,
    //     to: '/app/user/account-profile',
    //   },
    // ]
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
    to: '/dashboard/analytics'
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

]
export default sidebarItem;
