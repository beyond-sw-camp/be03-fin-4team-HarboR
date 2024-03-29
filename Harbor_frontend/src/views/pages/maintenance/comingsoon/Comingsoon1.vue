<script>
import { ref } from 'vue';

export default {
  name: 'CounterCards',
  setup() {
    const years = ref(0);
    const days = ref(0);
    const hours = ref(0);
    const minutes = ref(0);
    const seconds = ref(0);
    const lunchDate = new Date('25 May 2024');

    setInterval(() => {
      const currentDate = new Date();
      const lunchTime = lunchDate - currentDate;
      seconds.value = parseInt(lunchTime / 1000);
      minutes.value = parseInt(seconds.value / 60);
      hours.value = parseInt(minutes.value / 60);
      days.value = parseInt(hours.value / 24);
      years.value = parseInt(days.value / 365);
    }, 1000);
    return { years, days, hours, minutes, seconds };
  }
};
</script>

<template>
  <v-row no-gutters class="h-screen">
    <v-col class="d-flex align-center justify-center">
      <div class="text-center">
        <h1 class="text-h1">Coming Soon</h1>
        <small>Something new is on it's way</small>
        <div class="CardMediaWrapper my-6">
          <img src="@/assets/images/maintenance/img-soon-grid.svg" alt="maintenance" class="gridbg w-100" />
          <img src="@/assets/images/maintenance/img-soon-block.svg" alt="maintenance" class="CardMediaBlock" />
          <img src="@/assets/images/maintenance/img-soon-blue-block.svg" alt="maintenance" class="CardMediaBlue" />
          <img src="@/assets/images/maintenance/img-soon-purple-block.svg" alt="maintenance" class="CardMediaPurple" />
        </div>
        <div class="max450 align-center">
          <v-row class="ma-0">
            <v-col cols="6" sm="3">
              <v-sheet rounded="md" class="Timeblock" color="lightsecondary text-h1 text-secondary">{{ days % 365 }} </v-sheet>
              <small>Days</small>
            </v-col>
            <v-col cols="6" sm="3">
              <v-sheet rounded="md" class="Timeblock" color="lightsecondary text-h1 text-secondary">{{ hours % 24 }}</v-sheet>
              <small>Hours</small>
            </v-col>
            <v-col cols="6" sm="3">
              <v-sheet rounded="md" class="Timeblock" color="lightsecondary text-h1 text-secondary">{{ minutes % 60 }}</v-sheet>
              <small>Minutes</small>
            </v-col>
            <v-col cols="6" sm="3">
              <v-sheet rounded="md" class="Timeblock" color="lightsecondary text-h1 text-secondary">{{ seconds % 60 }} </v-sheet>
              <small>Seconds</small>
            </v-col>
            <v-col cols="12" class="d-sm-flex ga-4 align-center">
              <v-text-field variant="outlined" label="Email Address" color="primary" hide-details></v-text-field>
              <v-btn color="primary" size="large" class="mt-4 mt-sm-0">Notify Me</v-btn>
            </v-col>
          </v-row>
        </div>
      </div>
    </v-col>
  </v-row>
</template>
<style lang="scss">
.max450 {
  max-width: 450px;
  margin: 0 auto;
}
.CardMediaWrapper {
  max-width: 720;
  margin: 0 auto;
  position: relative;
}
.Timeblock {
  padding: 10px 5px;
}
.CardMediaBlock {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 8s blink ease-in-out infinite;
}
.CardMediaBlue {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 15s wings ease-in-out infinite;
}
.CardMediaPurple {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  animation: 12s wings ease-in-out infinite;
}
</style>
