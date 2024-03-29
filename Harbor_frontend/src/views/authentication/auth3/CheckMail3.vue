<script setup lang="ts">
import Logo from '@/layouts/full/logo/LogoDark.vue';
import AuthCheckMail from '../authForms/AuthCheckMail.vue';
</script>

<template>
  <v-row class="h-screen" no-gutters>
    <!---Left Part-->
    <v-col cols="12" class="d-flex align-center bg-lightprimary">
      <v-container>
        <div class="pa-7 pa-sm-12">
          <v-row justify="center">
            <v-col cols="12" lg="10" xl="6" md="7">
              <v-card elevation="0" class="loginBox">
                <v-card variant="outlined">
                  <v-card-text class="pa-9">
                    <!---Left Part Logo -->
                    <v-row>
                      <v-col cols="12" class="text-center">
                        <Logo />
                        <h2 class="text-secondary text-h2 mt-8">Hi, Check Your Mail</h2>
                      </v-col>
                    </v-row>
                    <!---Left Part Logo -->

                    <!---Left Part Form-->

                    <AuthCheckMail />

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
    top: 25%;
    left: 18%;
    background-size: 550px;
    width: 455px;
    height: 430px;
    background-image: url('@/assets/images/auth/auth-mail-blue-card.svg');
    background-repeat: no-repeat;
    background-position: center;
    animation: 15s wings ease-in-out infinite;
  }
}

.bgpattern {
  background: url('@/assets/images/auth/auth-pattern.svg') repeat;
}
</style>
