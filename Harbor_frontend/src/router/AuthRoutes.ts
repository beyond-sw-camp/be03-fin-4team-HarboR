const AuthRoutes = {
  path: '/auth',
  component: () => import('@/layouts/blank/BlankLayout.vue'),
  meta: {
    requiresAuth: false
  },
  children: [
    {
      name: 'Landingpage',
      path: '/',
      component: () => import('@/views/pages/landingpage/LandingPage.vue')
    },
    {
      name: 'ContactUs',
      path: '/pages/contact-us',
      component: () => import('@/views/pages/landingpage/ContactUs.vue')
    },
    {
      name: 'PrivacyPolicy',
      path: '/pages/privacy-policy',
      component: () => import('@/views/pages/landingpage/PrivacyPolicy.vue')
    },
    {
      name: 'FAQs',
      path: '/pages/faq',
      component: () => import('@/views/pages/landingpage/FAQs.vue')
    },
    {
      name: 'Login',
      path: '/auth/login1',
      component: () => import('@/views/authentication/auth1/LoginPage1.vue')
    },
    {
      name: 'Captcha',
      path: '/auth/login1',
      component: () => import('@/views/authentication/auth1/LoginPage1.vue')
    },
    {
      name: 'Register',
      path: '/auth/register1',
      component: () => import('@/views/authentication/auth1/RegisterPage1.vue')
    },
    {
      name: 'Forgot Password',
      path: '/auth/forgot-pwd1',
      component: () => import('@/views/authentication/auth1/ForgotPwd1.vue')
    },
    {
      name: 'Check Mail',
      path: '/auth/check-mail1',
      component: () => import('@/views/authentication/auth1/CheckMail1.vue')
    },
    {
      name: 'Reset Password',
      path: '/auth/reset-pwd1',
      component: () => import('@/views/authentication/auth1/ResetPwd1.vue')
    },
    {
      name: 'Code Verification',
      path: '/auth/code-verify1',
      component: () => import('@/views/authentication/auth1/CodeVerification1.vue')
    },
    {
      name: 'Login 3',
      path: '/auth/login3',
      component: () => import('@/views/authentication/auth3/LoginPage3.vue')
    },
    {
      name: 'Register 3',
      path: '/auth/register3',
      component: () => import('@/views/authentication/auth3/RegisterPage3.vue')
    },
    {
      name: 'Forgot Password 3',
      path: '/auth/forgot-pwd3',
      component: () => import('@/views/authentication/auth3/ForgotPwd3.vue')
    },
    {
      name: 'Check Mail 3',
      path: '/auth/check-mail3',
      component: () => import('@/views/authentication/auth3/CheckMail3.vue')
    },
    {
      name: 'Reset Password 3',
      path: '/auth/reset-pwd3',
      component: () => import('@/views/authentication/auth3/ResetPwd3.vue')
    },
    {
      name: 'Code Verification 3',
      path: '/auth/code-verify3',
      component: () => import('@/views/authentication/auth3/CodeVerification3.vue')
    },
    {
      name: 'Login 2',
      path: '/auth/login2',
      component: () => import('@/views/authentication/auth2/LoginPage2.vue')
    },
    {
      name: 'Register 2',
      path: '/auth/register2',
      component: () => import('@/views/authentication/auth2/RegisterPage2.vue')
    },
    {
      name: 'Forgot Password 2',
      path: '/auth/forgot-pwd2',
      component: () => import('@/views/authentication/auth2/ForgotPwd2.vue')
    },
    {
      name: 'Check Mail 2',
      path: '/auth/check-mail2',
      component: () => import('@/views/authentication/auth2/CheckMail2.vue')
    },
    {
      name: 'Reset Password 2',
      path: '/auth/reset-pwd2',
      component: () => import('@/views/authentication/auth2/ResetPwd2.vue')
    },
    {
      name: 'Code Verification 2',
      path: '/auth/code-verify2',
      component: () => import('@/views/authentication/auth2/CodeVerification2.vue')
    },
    {
      name: 'Coming Soon 1',
      path: '/pages/comingsoon1',
      component: () => import('@/views/pages/maintenance/comingsoon/Comingsoon1.vue')
    },
    {
      name: 'Under Construction',
      path: '/pages/construction',
      component: () => import('@/views/pages/underconstruction/UnderConstruction.vue')
    },
    {
      name: 'Error 404',
      path: '/pages/error',
      component: () => import('@/views/pages/maintenance/error/Error404Page.vue')
    }
  ]
};

export default AuthRoutes;
