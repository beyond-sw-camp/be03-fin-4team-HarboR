<script setup lang="ts">
import { shallowRef } from 'vue';
import SvgSprite from '@/components/shared/SvgSprite.vue';

const items = shallowRef([
  {
    name: 'Components',
    icon: 'grid-view',
    rotate: '45deg',
    count: '150+',
    color: 'warning'
  },
  {
    name: 'Application',
    icon: 'widget-outline',
    rotate: '0deg',
    count: '8+',
    color: 'primary'
  },
  {
    name: 'Pages',
    icon: 'web-outline',
    rotate: '0deg',
    count: '100+',
    color: 'secondary'
  }
]);
</script>
<template>
  <div class="spacer">
    <v-container class="maxWidth py-0">
      <v-row class="ma-lg-n5">
        <v-col
          md="4"
          sm="6"
          cols="12"
          v-for="(item, index) in items"
          :key="index"
          class="pa-lg-5"
          data-aos="fade-up"
          data-aos-duration="1000"
        >
          <v-card
            variant="flat"
            class="page-card position-relative overflow-hidden"
            rounded="sm"
            :style="{ backgroundColor: `rgba(var(--v-theme-${item.color}), 0.5)` }"
          >
            <v-card-item>
              <div class="d-flex justify-space-between">
                <v-avatar size="60" color="surface" rounded="md" variant="flat" style="opacity: 0.5">
                  <SvgSprite
                    :name="item.icon || ''"
                    :class="'text-' + item.color"
                    style="width: 30px; height: 30px"
                    :style="{ transform: `rotate(${item.rotate})` }"
                  />
                </v-avatar>
                <div class="text-right" style="z-index: 1">
                  <h2 class="text-h1" style="line-height: 1.2">{{ item.count }}</h2>
                  <h5 class="text-h4">{{ item.name }}</h5>
                </div>
              </div>
            </v-card-item>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<style lang="scss">
.page-card {
  &::before {
    content: '';
    position: absolute;
    width: 150px;
    height: 150px;
    border: 2px solid;
    border-color: rgb(var(--v-theme-surface));
    opacity: 0.21;
    border-radius: 50%;
    top: -97px;
    right: -3px;
  }
  &::after {
    content: '';
    position: absolute;
    width: 150px;
    height: 150px;
    border: 35px solid;
    border-color: rgb(var(--v-theme-surface));
    opacity: 0.4;
    border-radius: 50%;
    top: -72px;
    right: -63px;
  }
}
</style>
