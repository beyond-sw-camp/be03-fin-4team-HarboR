import {
  MenuIcon,
  CircleIcon,
  CircleOffIcon,
  FormsIcon,
  ChartDotsIcon,
  PictureInPictureIcon,
  ClipboardCheckIcon,
  Receipt2Icon,
  KeyIcon,
  BugIcon,
  TableIcon,
  BrushIcon,
  ToolsIcon,
  DashboardIcon,
  ChartArcsIcon,
  AppsIcon,
  BrandChromeIcon,
  HelpIcon,
  SitemapIcon
} from 'vue-tabler-icons';

export interface menu {
  header?: string;
  title?: string;
  icon?: object;
  to?: string;
  divider?: boolean;
  chip?: string;
  chipColor?: string;
  chipVariant?: string;
  chipIcon?: string;
  children?: menu[];
  disabled?: boolean;
  subCaption?: string;
  class?: string;
  extraclass?: string;
  type?: string;
}

const horizontalItems: menu[] = [
  {
    title: 'Dashboard',
    icon: DashboardIcon,
    to: '#',
    children: [
      {
        title: 'Default',
        icon: CircleIcon,
        to: '/dashboard/default'
      },
      {
        title: 'Analytics',
        icon: CircleIcon,
        to: '/dashboard/analytics'
      }
    ]
  },
  {
    title: 'Widgets',
    icon: ChartArcsIcon,
    to: '#',
    children: [
      {
        title: 'Statistics',
        icon: CircleIcon,
        to: '/widget/statistics'
      },
      {
        title: 'Data',
        icon: CircleIcon,
        to: '/widget/data'
      },
      {
        title: 'Chart',
        icon: CircleIcon,
        to: '/widget/chart'
      }
    ]
  },
  {
    title: 'Apps',
    icon: AppsIcon,
    to: '#',
    children: [
      {
        title: 'Users',
        icon: CircleIcon,
        to: '/app/user',
        children: [
          {
            title: 'Social Profile',
            icon: CircleIcon,
            to: '/app/user/social/posts'
          },
          {
            title: 'Account Profile',
            icon: CircleIcon,
            to: '/app/user/account-profile',
            children: [
              {
                title: 'Profile 01',
                icon: CircleIcon,
                to: '/app/user/account-profile/profile1'
              },
              {
                title: 'Profile 02',
                icon: CircleIcon,
                to: '/app/user/account-profile/profile2'
              },
              {
                title: 'Profile 03',
                icon: CircleIcon,
                to: '/app/user/account-profile/profile3'
              }
            ]
          },
          {
            title: 'Cards',
            icon: CircleIcon,
            to: '/app/user/card',
            children: [
              {
                title: 'Style 01',
                icon: CircleIcon,
                to: '/app/user/card/card1'
              },
              {
                title: 'Style 02',
                icon: CircleIcon,
                to: '/app/user/card/card2'
              },
              {
                title: 'Style 03',
                icon: CircleIcon,
                to: '/app/user/card/card3'
              }
            ]
          },
          {
            title: 'List',
            icon: CircleIcon,
            to: '/app/user/list',
            children: [
              {
                title: 'Style 01',
                icon: CircleIcon,
                to: '/app/user/list1'
              },
              {
                title: 'Style 02',
                icon: CircleIcon,
                to: '/app/user/list2'
              }
            ]
          }
        ]
      },
      {
        title: 'Customer',
        icon: CircleIcon,
        to: '/customer/',
        children: [
          {
            title: 'Customer List',
            icon: CircleIcon,
            to: '/customer/customerlist'
          },
          {
            title: 'Create Invoice',
            icon: CircleIcon,
            to: '/app/customer/create-invoice'
          },
          {
            title: 'Order Details',
            icon: CircleIcon,
            to: '/app/customer/order-details'
          },
          {
            title: 'Order List',
            icon: CircleIcon,
            to: '/customer/orderlist'
          },
          {
            title: 'Product List',
            icon: CircleIcon,
            to: '/customer/productlist'
          },
          {
            title: 'Product Review',
            icon: CircleIcon,
            to: '/customer/productreview'
          }
        ]
      },
      {
        title: 'Chat',
        icon: CircleIcon,
        to: '/app/chats'
      },
      {
        title: 'Kanban',
        icon: CircleIcon,
        to: '/app/kanban'
      },
      {
        title: 'Mail',
        icon: CircleIcon,
        to: '/app/mail'
      },
      {
        title: 'Calendar',
        icon: CircleIcon,
        to: '/app/calendar'
      },
      {
        title: 'Contact',
        icon: CircleIcon,
        to: '/app/contacts',
        children: [
          {
            title: 'Card',
            icon: CircleIcon,
            to: '/app/contact/c-card'
          },
          {
            title: 'List',
            icon: CircleIcon,
            to: '/app/contact/c-list'
          }
        ]
      },
      {
        title: 'E-Commerce',
        icon: CircleIcon,
        to: '/ecommerce/',
        children: [
          {
            title: 'Products',
            icon: CircleIcon,
            to: '/ecommerce/products'
          },
          {
            title: 'Product Detail',
            icon: CircleIcon,
            to: '/ecommerce/product/detail/1'
          },
          {
            title: 'Product List',
            icon: CircleIcon,
            to: '/ecommerce/productlist'
          },

          {
            title: 'Checkout',
            icon: CircleIcon,
            to: '/ecommerce/checkout'
          }
        ]
      }
    ]
  },
  {
    title: 'Forms',
    icon: DashboardIcon,
    to: '#',
    children: [
      {
        title: 'Components',
        icon: PictureInPictureIcon,
        to: '/components/',
        children: [
          {
            title: 'Autocomplete',
            icon: CircleIcon,
            to: '/components/autocomplete'
          },
          {
            title: 'Buttons',
            icon: CircleIcon,
            to: '/components/buttons'
          },

          {
            title: 'Checkbox',
            icon: CircleIcon,
            to: '/components/checkbox'
          },
          {
            title: 'Radio',
            icon: CircleIcon,
            to: '/components/radio'
          },
          {
            title: 'TextField',
            icon: CircleIcon,
            to: '/components/textfield'
          },
          {
            title: 'Slider',
            icon: CircleIcon,
            to: '/components/slider'
          },
          {
            title: 'Switch',
            icon: CircleIcon,
            to: '/components/switch'
          }
        ]
      },

      {
        title: 'Plugins',
        icon: FormsIcon,
        to: '/forms/radio',
        children: [
          {
            title: 'Editor',
            icon: CircleIcon,
            to: '/forms/plugins/editor'
          },
          {
            title: 'Mask',
            icon: CircleIcon,
            to: '/forms/plugins/mask'
          },
          {
            title: 'Captcha',
            icon: CircleIcon,
            to: '/auth/login1'
          },
          {
            title: 'Clipboard',
            icon: CircleIcon,
            to: '/forms/plugins/clipboard'
          },
          {
            title: 'Modal',
            icon: CircleIcon,
            to: '/forms/plugins/modal'
          },
          {
            title: 'Tooltip',
            icon: CircleIcon,
            to: '/forms/plugins/tooltip'
          }
        ]
      },

      {
        title: 'Layouts',
        icon: FormsIcon,
        to: '/forms/layouts',
        children: [
          {
            title: 'Layouts',
            icon: CircleIcon,
            to: '/forms/layouts/layouts'
          },
          {
            title: 'Multi Columns Form',
            icon: CircleIcon,
            to: '/forms/layouts/multi-column-forms'
          },
          {
            title: 'Action Bar',
            icon: CircleIcon,
            to: '/forms/layouts/action-bar'
          },
          {
            title: 'Sticky Action Bar',
            icon: CircleIcon,
            to: '/forms/layouts/sticky-action-bar'
          }
        ]
      },
      {
        title: 'Tables',
        icon: TableIcon,
        to: '/forms/tables',
        children: [
          {
            title: 'Basic Table',
            icon: CircleIcon,
            to: '/tables/tbl-basic'
          },
          {
            title: 'Dark Table',
            icon: CircleIcon,
            to: '/tables/tbl-dark'
          },
          {
            title: 'Density Table',
            icon: CircleIcon,
            to: '/tables/tbl-density'
          },
          {
            title: 'Height Table',
            icon: CircleIcon,
            to: '/tables/tbl-height'
          },
          {
            title: 'Fixed Header Table',
            icon: CircleIcon,
            to: '/tables/tbl-fixed-header'
          }
        ]
      },
      {
        title: 'Charts',
        icon: ChartDotsIcon,
        to: '/forms/radio',
        children: [
          {
            title: 'Org Charts',
            icon: CircleIcon,
            to: '/forms/charts/orgchart'
          },
          {
            title: 'Apex Charts',
            icon: CircleIcon,
            to: '/forms/charts/apexchart'
          }
        ]
      },
      {
        title: 'Form Validation',
        icon: ClipboardCheckIcon,
        to: '/forms/formvalidation'
      }
    ]
  },
  {
    title: 'Ui',
    icon: BrushIcon,
    to: '#',
    children: [
      {
        title: 'Basic',
        icon: BrushIcon,
        to: '/basic/',
        children: [
          {
            title: 'Accordion',
            icon: CircleIcon,
            to: '/basic/expansion-panel'
          },
          {
            title: 'Avatar',
            icon: CircleIcon,
            to: '/basic/avatar'
          },
          {
            title: 'Badges',
            icon: CircleIcon,
            to: '/basic/badges'
          },
          {
            title: 'Breadcrumb',
            icon: CircleIcon,
            to: '/basic/breadcrumb'
          },
          {
            title: 'Cards',
            icon: CircleIcon,
            to: '/basic/cards'
          },
          {
            title: 'Chip',
            icon: CircleIcon,
            to: '/basic/chip'
          },
          {
            title: 'List',
            icon: CircleIcon,
            to: '/basic/uilist'
          },
          {
            title: 'Tabs',
            icon: CircleIcon,
            to: '/basic/tabs'
          }
        ]
      },
      {
        title: 'Advance',
        icon: ToolsIcon,
        to: '/basic/tabs',
        children: [
          {
            title: 'Alert',
            icon: CircleIcon,
            to: '/advance/alert'
          },
          {
            title: 'Pagination',
            icon: CircleIcon,
            to: '/advance/pagination'
          },
          {
            title: 'Progress',
            icon: CircleIcon,
            to: '/advance/progress'
          },
          {
            title: 'Ratings',
            icon: CircleIcon,
            to: '/advance/ratings'
          },
          {
            title: 'Snackbar',
            icon: CircleIcon,
            to: '/advance/snackbar'
          },
          {
            title: 'Timeline',
            icon: CircleIcon,
            to: '/advance/timeline'
          },
          {
            title: 'Button Toggle',
            icon: CircleIcon,
            to: '/advance/btn-toggle'
          }
        ]
      },
      {
        title: 'Authentication',
        icon: KeyIcon,
        to: '/auth',
        children: [
          {
            title: 'Auth 1',
            icon: CircleIcon,
            to: '/auth/login',
            children: [
              {
                title: 'Login',
                icon: CircleIcon,
                to: '/auth/login1'
              },
              {
                title: 'Register',
                icon: CircleIcon,
                to: '/auth/register1'
              },
              {
                title: 'Forgot Password',
                icon: CircleIcon,
                to: '/auth/forgot-pwd1'
              },
              {
                title: 'Check Mail',
                icon: CircleIcon,
                to: '/auth/check-mail1'
              },
              {
                title: 'Reset Password',
                icon: CircleIcon,
                to: '/auth/reset-pwd1'
              },
              {
                title: 'Code Verification',
                icon: CircleIcon,
                to: '/auth/code-verify1'
              }
            ]
          },
          {
            title: 'Auth 2',
            icon: CircleIcon,
            to: '/auth/login',
            children: [
              {
                title: 'Login 2',
                icon: CircleIcon,
                to: '/auth/login2'
              },
              {
                title: 'Register 2',
                icon: CircleIcon,
                to: '/auth/register2'
              },
              {
                title: 'Forgot Password 2',
                icon: CircleIcon,
                to: '/auth/forgot-pwd2'
              },
              {
                title: 'Check Mail 2',
                icon: CircleIcon,
                to: '/auth/check-mail2'
              },
              {
                title: 'Reset Password 2',
                icon: CircleIcon,
                to: '/auth/reset-pwd2'
              },
              {
                title: 'Code Verification 2',
                icon: CircleIcon,
                to: '/auth/code-verify2'
              }
            ]
          },
          {
            title: 'Auth 3',
            icon: CircleIcon,
            to: '/auth/login',
            children: [
              {
                title: 'Login 3',
                icon: CircleIcon,
                to: '/auth/login3'
              },
              {
                title: 'Register 3',
                icon: CircleIcon,
                to: '/auth/register3'
              },
              {
                title: 'Forgot Password 3',
                icon: CircleIcon,
                to: '/auth/forgot-pwd3'
              },
              {
                title: 'Check Mail 3',
                icon: CircleIcon,
                to: '/auth/check-mail3'
              },
              {
                title: 'Reset Password 3',
                icon: CircleIcon,
                to: '/auth/reset-pwd3'
              },
              {
                title: 'Code Verification 3',
                icon: CircleIcon,
                to: '/auth/code-verify3'
              }
            ]
          }
        ]
      },
      {
        title: 'Pricing',
        icon: Receipt2Icon,
        to: '/starter',
        children: [
          {
            title: 'Pricing 1',
            icon: CircleIcon,
            to: '/pages/pricing1'
          },
          {
            title: 'Pricing 2',
            icon: CircleIcon,
            to: '/pages/pricing2'
          }
        ]
      },
      {
        title: 'Maintenance',
        icon: BugIcon,
        to: '/maintenenace',
        children: [
          {
            title: 'Error 404',
            icon: CircleIcon,
            to: '/pages/error'
          },
          {
            title: 'Coming soon1',
            icon: CircleIcon,
            to: '/pages/comingsoon1'
          },
          {
            title: 'Under Construction',
            icon: CircleIcon,
            to: '/pages/construction'
          }
        ]
      }
    ]
  },
  {
    title: 'Extra',
    icon: MenuIcon,
    to: '#',
    children: [
      {
        title: 'Front-end',
        icon: CircleIcon,
        to: '/2level',
        children: [
          {
            title: 'Landingpage',
            icon: CircleIcon,
            to: '/',
            type: 'external'
          },
          {
            title: 'Contact Us',
            icon: CircleIcon,
            to: '/pages/contact-us',
            type: 'external'
          },
          {
            title: 'FAQs',
            icon: CircleIcon,
            to: '/pages/faq',
            type: 'external'
          },
          {
            title: 'Privacy Policy',
            icon: CircleIcon,
            to: '/pages/privacy-policy',
            type: 'external'
          }
        ]
      },
      {
        title: 'Utilities',
        icon: CircleIcon,
        to: '/level1',
        children: [
          {
            title: 'Typography',
            icon: CircleIcon,
            to: '/utils/typography'
          },
          {
            title: 'Shadows',
            icon: CircleIcon,
            to: '/utils/shadows'
          },
          {
            title: 'Colors',
            icon: CircleIcon,
            to: '/utils/colors'
          },

          {
            title: 'Tabler Icons',
            icon: CircleIcon,
            to: '/icons/tabler'
          },
          {
            title: 'Material Icons',
            icon: CircleIcon,
            to: '/icons/material'
          },
          {
            title: 'Animation',
            icon: CircleIcon,
            to: '/utils/animation'
          },
          {
            title: 'Grid',
            icon: CircleIcon,
            to: '/utils/grid'
          }
        ]
      },
      {
        title: 'Disabled Menu',
        icon: CircleOffIcon,
        disabled: true,
        to: '/berry'
      },
      {
        title: 'Oval Chip',
        icon: CircleIcon,
        chip: '9',
        chipColor: 'primary',
        to: '/berry'
      },
      {
        title: 'Avatar',
        icon: CircleIcon,
        chip: 'coded',
        chipColor: 'primary',
        chipIcon: 'mdi-account-circle',
        to: '/berry'
      },
      {
        title: 'Outline',
        icon: CircleIcon,
        chip: 'outline',
        chipColor: 'primary',
        chipVariant: 'outlined',
        to: '/berry'
      },
      {
        title: 'Sample Page',
        icon: BrandChromeIcon,
        to: '/starter'
      },
      {
        title: 'Documentation',
        icon: HelpIcon,
        to: 'https://codedthemes.gitbook.io/berry-vuetify/',
        type: 'external'
      },
      {
        title: 'Road Map',
        icon: SitemapIcon,
        to: 'https://codedthemes.gitbook.io/berry-vuetify/roadmap',
        type: 'external'
      }
    ]
  }
];

export default horizontalItems;
