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
      path: '/auth/login',
      component: () => import('@/views/authentication/auth3/LoginPage3.vue')
    },
    {
      name: 'Register',
      path: '/auth/register',
      component: () => import('@/views/authentication/auth3/RegisterPage3.vue')
    },
    {
      name: 'Forgot Password',
      path: '/auth/forgot-pwd',
      component: () => import('@/views/authentication/auth3/ForgotPwd3.vue')
    },
    {
      name: 'Check Mail',
      path: '/auth/check-mail',
      component: () => import('@/views/authentication/auth3/CheckMail3.vue')
    },
    {
      name: 'Reset Password',
      path: '/auth/reset-pwd',
      component: () => import('@/views/authentication/auth3/ResetPwd3.vue')
    },
    {
      name: 'Code Verification',
      path: '/auth/code-verify',
      component: () => import('@/views/authentication/auth3/CodeVerification3.vue')
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
