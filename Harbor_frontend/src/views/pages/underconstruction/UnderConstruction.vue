<script setup lang="ts"></script>

<template>
  <v-row no-gutters class="h-screen">
    <v-col class="d-flex align-center justify-center">
      <div class="text-center">
        <div class="CardMediaWrapper">
          <img src="@/assets/images/maintenance/img-bg-grid.svg" alt="grid" />
          <img src="@/assets/images/maintenance/img-bg-parts.svg" alt="grid" class="CardMediaParts" />
          <img src="@/assets/images/maintenance/img-build.svg" alt="build" class="CardMediaBuild" />
        </div>
        <h1 class="text-h1">Under Construction</h1>
        <p>
          <small>This site is on under construction!! Please check <br />after some time </small>
        </p>
        <v-btn variant="flat" color="primary" class="mt-4" to="/" prepend-icon="mdi-home"> Home</v-btn>
      </div>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.CardMediaWrapper {
  max-width: 720;
  margin: 0 auto;
  position: relative;
}
.CardMediaBuild {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 5s bounce ease-in-out infinite;
}
.CardMediaParts {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 10s blink ease-in-out infinite;
}
</style>
