<script setup lang="ts">
import Logo from '@/layouts/full/logo/LogoDark.vue';
import AuthForgotPwd from '../authForms/AuthForgotPwd.vue';
import TextSlider from './TextSlider.vue';
</script>

<template>
  <v-row class="h-screen" no-gutters>
    <!---Left Part-->
    <v-col cols="12" lg="7" xl="7" class="d-flex align-center bg-lightprimary">
      <v-container>
        <div class="pa-0 pa-sm-12">
          <v-row justify="center">
            <v-col cols="12" lg="10" xl="6" md="7">
              <v-card elevation="0" class="loginBox">
                <v-card variant="outlined">
                  <v-card-text class="pa-9">
                    <!---Left Part Logo -->
                    <v-row>
                      <v-col cols="12" md="7" sm="7" order-sm="first" order="last" class="text-sm-start text-center">
                        <h2 class="text-secondary text-h2">Forgot password?</h2>
                        <h4 class="font-weight-medium text-h4">Enter credentials to continue</h4>
                      </v-col>
                      <v-col class="text-sm-end text-center" sm="5" cols="12">
                        <Logo />
                      </v-col>
                    </v-row>
                    <!---Left Part Logo -->

                    <!---Left Part Form-->
                    <AuthForgotPwd />
                    <!---Left Part Form-->
                  </v-card-text>
                </v-card>
              </v-card>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-col>
    <!---Left Part-->
    <!---Right Part-->
    <v-col cols="12" lg="5" xl="5" class="d-none d-lg-flex align-end justify-center bgpattern position-relative">
      <v-container>
        <div class="pa-10">
          <v-row justify="center">
            <v-col cols="12" xl="11">
              <div class="cardAnimation"></div>
              <TextSlider />
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-col>
    <!---Right Part-->
  </v-row>
</template>
<style lang="scss">
.loginBox {
  max-width: 475px;
  margin: 0 auto;
}

.cardAnimation {
  &:after {
    content: '';
    position: absolute;
    top: 18%;
    left: 18%;
    background-size: 600px;
    width: 500px;
    height: 470px;
    background-image: url('@/assets/images/auth/auth-forgot-pass-multi-card.svg');
    background-repeat: no-repeat;
    background-position: center;
    animation: 15s wings ease-in-out infinite;
  }
}

.bgpattern {
  background: url('@/assets/images/auth/auth-pattern.svg') repeat;
}
</style>
