<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <UiParentCard title="팀별 스케줄 캘린더 ">
    <div class="qalendar-wrapper">
      <Qalendar :events="events" :config="config" @updated-mode="updatedMode" />
    </div>
  </UiParentCard>
</template>

<script lang="ts" setup>
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import { Qalendar } from 'qalendar';
import { onMounted, ref, watch } from 'vue';
import axios, { setClientHeaders } from '@/utils/axios';
import type { Event, ScheduleDTO } from '@/types/calendar/Events';

const page = ref({ title: '일정 관리' });
const breadcrumbs = ref([
  {
    title: '근태 관리',
    disabled: true,
    href: '#'
  },
  {
    title: '일정 조회',
    disabled: false,
    href: '#'
  }
]);

const config = {
  month: {
    showTrailingAndLeadingDates: false
  },
  week: {
    startsOn: 'sunday',
    nDays: 7,
    scrollToHour: 5
  },
  style: {
    fontFamily: 'Roboto'
  },
  defaultMode: 'week',
  isSilent: true,
  showCurrentTime: true,
  issmall: true
};

const baseUrl = `${import.meta.env.VITE_API_URL}`;
const events: IterableIterator<Event> = ref([]);

onMounted(async () => {
  events.value = await reloadEvents();
});
const extractHourMinute = (timeString: string | null) => {
  if (timeString != null) {
    return ' ' + timeString.substr(0, 5);
  } else return '';
};
const reloadEvents = async (): Promise<Event[]> => {
  setClientHeaders();
  const response = await axios.get<ScheduleDTO[]>(`${baseUrl}/employee/schedule/team/list`);
  events.value = response.data.result.map((schedule, index) => ({
    title: schedule.scheduleTitle,
    with: schedule.scheduleName,
    time: {
      start: schedule.scheduleStartDate + extractHourMinute(schedule.scheduleStartTime),
      end: schedule.scheduleEndDate + extractHourMinute(schedule.scheduleEndTime)
    },
    color: schedule.scheduleColor,
    isEditable: false,
    id: schedule.scheduleId,
    description: schedule.scheduleComment
  }));
  return events.value;
};

const updateBackground = () => {
  let link = document.getElementById('datestyle');
  if (!link) {
    link = document.createElement('style');
    link.id = 'datestyle';
    document.head.appendChild(link);
  }
};

const updatedMode = () => {
  updateBackground();
};
</script>

<style>
@import 'qalendar/dist/style.css';
.qalendar-wrapper {
  height: 600px;
}
</style>
