import { computed } from 'vue';
import * as themeColors from '@/theme/LightTheme';
import { useCustomizerStore } from '@/stores/customizer';

const custmizer = useCustomizerStore();

const getPrimary = computed(() => {
  if (custmizer.actTheme === 'GreenTheme') {
    return themeColors.GreenTheme.colors.primary;
  } else if (custmizer.actTheme === 'PinkTheme') {
    return themeColors.PinkTheme.colors.primary;
  } else if (custmizer.actTheme === 'YellowTheme') {
    return themeColors.YellowTheme.colors.primary;
  } else if (custmizer.actTheme === 'SeaGreenTheme') {
    return themeColors.SeaGreenTheme.colors.primary;
  } else if (custmizer.actTheme === 'OliveGreenTheme') {
    return themeColors.OliveGreenTheme.colors.primary;
  } else if (custmizer.actTheme === 'SpeechBlueTheme') {
    return themeColors.SpeechBlueTheme.colors.primary;
  } else {
    return themeColors.PurpleTheme.colors.primary;
  }
});

const getLightPrimary = computed(() => {
  if (custmizer.actTheme === 'GreenTheme') {
    return themeColors.GreenTheme.colors.primary200;
  } else if (custmizer.actTheme === 'PinkTheme') {
    return themeColors.PinkTheme.colors.primary200;
  } else if (custmizer.actTheme === 'YellowTheme') {
    return themeColors.YellowTheme.colors.primary200;
  } else if (custmizer.actTheme === 'SeaGreenTheme') {
    return themeColors.SeaGreenTheme.colors.primary200;
  } else if (custmizer.actTheme === 'OliveGreenTheme') {
    return themeColors.OliveGreenTheme.colors.primary200;
  } else if (custmizer.actTheme === 'SpeechBlueTheme') {
    return themeColors.SpeechBlueTheme.colors.primary200;
  } else {
    return themeColors.PurpleTheme.colors.primary200;
  }
});

const getSecondary = computed(() => {
  if (custmizer.actTheme === 'GreenTheme') {
    return themeColors.GreenTheme.colors.secondary;
  } else if (custmizer.actTheme === 'PinkTheme') {
    return themeColors.PinkTheme.colors.secondary;
  } else if (custmizer.actTheme === 'YellowTheme') {
    return themeColors.YellowTheme.colors.secondary;
  } else if (custmizer.actTheme === 'SeaGreenTheme') {
    return themeColors.SeaGreenTheme.colors.secondary;
  } else if (custmizer.actTheme === 'OliveGreenTheme') {
    return themeColors.OliveGreenTheme.colors.secondary;
  } else if (custmizer.actTheme === 'SpeechBlueTheme') {
    return themeColors.SpeechBlueTheme.colors.secondary;
  } else {
    return themeColors.PurpleTheme.colors.secondary;
  }
});

const getLightSecondary = computed(() => {
  if (custmizer.actTheme === 'GreenTheme') {
    return themeColors.GreenTheme.colors.secondary200;
  } else if (custmizer.actTheme === 'PinkTheme') {
    return themeColors.PinkTheme.colors.secondary200;
  } else if (custmizer.actTheme === 'YellowTheme') {
    return themeColors.YellowTheme.colors.secondary200;
  } else if (custmizer.actTheme === 'SeaGreenTheme') {
    return themeColors.SeaGreenTheme.colors.secondary200;
  } else if (custmizer.actTheme === 'OliveGreenTheme') {
    return themeColors.OliveGreenTheme.colors.secondary200;
  } else if (custmizer.actTheme === 'SpeechBlueTheme') {
    return themeColors.SpeechBlueTheme.colors.secondary200;
  } else {
    return themeColors.PurpleTheme.colors.secondary200;
  }
});

export { getPrimary, getSecondary, getLightPrimary, getLightSecondary };
