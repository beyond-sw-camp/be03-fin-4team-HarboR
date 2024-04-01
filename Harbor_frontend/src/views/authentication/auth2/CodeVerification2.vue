<script setup lang="ts">
import Logo from '@/layouts/full/logo/LogoDark.vue';
import AuthCodeVerification from '../authForms/AuthCodeVerification.vue';
import TextSlider from '../auth1/TextSlider.vue';
</script>

<template>
  <v-row class="h-screen" no-gutters>
    <!---Left Part-->
    <v-col cols="12" lg="7" xl="7" class="d-flex">
      <v-container>
        <Logo />
        <div class="">
          <v-row justify="center" class="heightCalc">
            <v-col cols="12" lg="10" xl="6" md="7" class="d-flex align-center">
              <v-card elevation="0" class="loginBox">
                <v-card-text class="pa-7">
                  <!---Left Part Logo -->
                  <v-row>
                    <v-col cols="12" class="text-center">
                      <h2 class="text-secondary text-h2">Verification Code</h2>
                      <h4 class="text-disabled font-weight-medium text-h4 mt-4">We send you on mail.</h4>
                    </v-col>
                  </v-row>
                  <!---Left Part Logo -->

                  <!---Left Part Form-->
                  <AuthCodeVerification />
                  <!---Left Part Form-->
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-col>
    <!---Left Part-->
    <!---Right Part-->
    <v-col cols="12" lg="5" xl="5" class="d-none d-lg-flex align-end justify-center bg-lightprimary position-relative">
      <div class="bgpattern">
        <v-container>
          <div class="pa-10">
            <v-row justify="center">
              <v-col cols="12" xl="11">
                <div class="mb-12 pb-14">
                  <TextSlider />
                </div>

                <div class="text-center">
                  <img src="@/assets/images/auth/img-a2-login.svg" alt="login2" width="300" />
                </div>
              </v-col>
            </v-row>
          </div>
        </v-container>
      </div>
    </v-col>
    <!---Right Part-->
  </v-row>
</template>
<style lang="scss">
.loginBox {
  max-width: 475px;
  margin: 0 auto;
}
.heightCalc {
  height: calc(100vh - 150px);
}

.bgpattern {
  background: url('@/assets/images/auth/img-a2-grid.svg') repeat;
  position: absolute;
  background-position: left bottom;
  background-repeat: no-repeat;
  overflow: hidden;
  margin: 0px 0px 0px auto;
  padding: 50px 0px;
  inset: 0px;
}
</style>
