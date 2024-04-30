<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <div class="qalendar-wrapper">
    <Qalendar
      :events="events"
      :config="config"
      @updated-mode="updatedMode"
      @event-was-dragged="handleEventDrag"
      @event-was-resized="handleEventResize"
      @edit-event="handleEditEvent"
      @delete-event="handleDeleteEvent"
      @datetime-was-clicked="handleDateTimeClicked"
      @date-was-clicked="handlerDateClicked"
    />
  </div>
  <v-dialog transition="slide-y-transition" v-model="dialog">
    <v-card class="mx-auto px-6 py-4" max-width="420">
      <v-card-title>
        <span class="headline">{{ dialog_title }}</span>
      </v-card-title>
      <v-form>
        <v-row>
          <v-col cols="7">
            <v-text-field
              v-model="newEvent.title"
              class="mb-2"
              label="제목"
              variant="underlined"
              placeholder="무엇을 할까요?"
            ></v-text-field>
          </v-col>
          <v-col cols="5">
            <v-select
              v-model="selectedColor"
              label="색상"
              :items="['blue', 'yellow', 'green', 'red', 'purple']"
              item-value="color"
              variant="outlined"
            ></v-select>
          </v-col>
        </v-row>
        <v-col col="6" sm="8">
          <v-text-field
            v-model="computedDateTimeFormattedStart"
            label="시작 날짜 / 시간"
            type="datetime-local"
            prepend-inner-icon="mdi-calendar-clock"
            variant="underlined"
          ></v-text-field>
        </v-col>
        <v-col cols="6" sm="8">
          <v-text-field
            v-model="computedDateTimeFormattedEnd"
            label="종료 날짜 / 시간"
            type="datetime-local"
            prepend-inner-icon="mdi-calendar-clock"
            variant="underlined"
          ></v-text-field>
        </v-col>
        <v-textarea v-model="newEvent.description" :v-bind="description" no-resize clearable label="내용" variant="outlined" placeholder="내용을 입력해주세요.">
        </v-textarea>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" variant="text" @click="dialog = false"> 취소 </v-btn>
          <v-btn color="success" variant="text" v-if="create" @click="createEvent"> 생성 </v-btn>
          <v-btn color="success" variant="text" v-if="!create" @click="modifyEvent"> 수정 </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script lang="ts" setup>
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import { Qalendar } from 'qalendar';
import { onMounted, ref, watch } from 'vue';
import axios, { setClientHeaders } from '@/utils/axios';
import type { Event, ScheduleDTO } from '@/types/calendar/Events';

const startDate = ref(new Date());
const endDate = ref(new Date());

function formatDateAndTime(date: Date) {
  let month = '' + (date.getMonth() + 1);
  let day = '' + date.getDate();
  const year = date.getFullYear();
  let hour = '' + date.getHours();
  let minute = '' + date.getMinutes();

  if (month.length < 2) month = '0' + month;
  if (day.length < 2) day = '0' + day;
  if (hour.length < 2) hour = '0' + hour;
  if (minute.length < 2) minute = '0' + minute;

  return `${year}-${month}-${day}T${hour}:${minute}`;
}

function convertTo12Hour(time: string): string {
  let [hours, minutes] = time.split(':');
  let period = 'AM';

  if (parseInt(hours) >= 12) {
    period = 'PM';
    if (parseInt(hours) > 12) hours = (parseInt(hours) - 12).toString();
  } else if (hours === '00') {
    hours = '12';
  }

  return `${hours}:${minutes} ${period}`;
}

const computedDateTimeFormattedStart = ref(formatDateAndTime(startDate.value));
const computedDateTimeFormattedEnd = ref(formatDateAndTime(endDate.value));

watch([computedDateTimeFormattedStart, computedDateTimeFormattedEnd], ([start, end]) => {
  if (new Date(end).getTime() < new Date(start).getTime()) {
    alert('종료 시간은 시작 시간보다 빠를 수 없습니다.');
    endDate.value = startDate.value;
    computedDateTimeFormattedEnd.value = computedDateTimeFormattedStart.value;
  }
});

watch(startDate, (newStartDate) => {
  computedDateTimeFormattedStart.value = formatDateAndTime(newStartDate);
});

watch(endDate, (newEndDate, oldEndDate) => {
  if (newEndDate < startDate.value) {
    alert('시작 날짜보다 이전 날짜를 선택할 수 없습니다.');
    endDate.value = oldEndDate;
  } else {
    computedDateTimeFormattedEnd.value = formatDateAndTime(newEndDate);
  }
});

const page = ref({ title: '개인 일정 캘린더' });
const breadcrumbs = ref([
  {
    title: '근태 관리',
    disabled: true,
    href: '#'
  },
  {
    title: '일정 관리',
    disabled: false,
    href: '#'
  }
]);

const create = ref(false);
const dialog_title = ref('');
const dialog = ref(false);
const baseUrl = `${import.meta.env.VITE_API_URL}`;
const token: string | null = localStorage.getItem('token');
const selectedColor = ref('yellow');
const events: IterableIterator<Event> = ref([]);
const newEvent = ref({
  id: 0,
  title: '',
  description: '',
  time: { start: '', end: '' },
  color: ''
});
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
  defaultMode: 'month',
  isSilent: true,
  showCurrentTime: true,
  issmall: true
};

onMounted(async () => {
  events.value = await reloadEvents();
});
const extractHourMinute = (timeString: string | null) => {
  if (timeString != null) {
    return ' ' + timeString.substr(0, 5);
  } else return '';
};
const reloadEvents = async (): Promise<Event[]> => {
  setClientHeaders(token);
  const response = await axios.get<ScheduleDTO[]>(`${baseUrl}/employee/schedule/list`);
  events.value = response.data.result.map((schedule, index) => ({
    title: schedule.scheduleTitle,
    with: 'To Do List',
    time: {
      start: schedule.scheduleStartDate + extractHourMinute(schedule.scheduleStartTime),
      end: schedule.scheduleEndDate + extractHourMinute(schedule.scheduleEndTime)
    },
    color: schedule.scheduleColor,
    isEditable: true,
    id: schedule.scheduleId,
    description: schedule.scheduleComment
  }));
  return events.value;
};

const toScheduleDTO = (event: Event): ScheduleDTO => ({
  scheduleStartDate: event.time.start.split('T')[0],
  scheduleEndDate: event.time.end.split('T')[0],
  scheduleStartTime: event.time.start.split('T')[1],
  scheduleEndTime: event.time.end.split('T')[1],
  scheduleTitle: event.title,
  scheduleComment: event.description,
  scheduleId: event.id,
  scheduleColor: event.color
});

const toEventScheduleDTO = (event: Event): ScheduleDTO => ({
  scheduleStartDate: event.time.start.split(' ')[0],
  scheduleEndDate: event.time.end.split(' ')[0],
  scheduleStartTime: event.time.start.split(' ')[1],
  scheduleEndTime: event.time.end.split(' ')[1],
  scheduleTitle: event.title,
  scheduleComment: event.description,
  scheduleId: event.id,
  scheduleColor: event.color
});

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

const handleEventDrag = async (event: Event) => {
  console.log('드래그 요청');
  const schedule = toEventScheduleDTO(event);
  await axios.patch(`${baseUrl}/employee/schedule/update/${event.id}`, schedule);
};

const handleEventResize = async (event: Event) => {
  console.log('리사이즈 요청');
  const schedule = toEventScheduleDTO(event);
  await axios.patch(`${baseUrl}/employee/schedule/update/${event.id}`, schedule);
};

const handleEditEvent = async (event: Event) => {
  const response = await axios.get(`${baseUrl}/employee/schedule/detail/${event}`);
  const eventData = response.data.result;
  console.log(eventData);
  newEvent.value.id = eventData.scheduleId;
  newEvent.value.title = eventData.scheduleTitle;
  newEvent.value.description = eventData.scheduleComment;
  startDate.value = new Date(eventData.scheduleStartDate + ' ' + eventData.scheduleStartTime);
  endDate.value = new Date(eventData.scheduleEndDate + ' ' + eventData.scheduleEndTime);
  selectedColor.value = eventData.scheduleColor;
  dialog_title.value = '일정 수정';
  create.value = false;
  dialog.value = true;
};

const handleDeleteEvent = async (event: Event) => {
  console.log('삭제 요청');
  try {
    await axios.delete(`${baseUrl}/employee/schedule/delete/${event}`);
    reloadEvents();
  } catch (error) {
    alert('이미 삭제된 일정입니다.');
    reloadEvents();
  }
};

const handleDateTimeClicked = (datetime: string) => {
  console.log(datetime);
  selectedColor.value = 'yellow';
  newEvent.value.title = '';
  newEvent.value.description = '';
  newEvent.value.color = 'yellow';
  newEvent.value.time.start = datetime;
  newEvent.value.time.end = datetime;

  startDate.value = new Date(datetime.split(' ')[0] + ' ' + convertTo12Hour(datetime.split(' ')[1]));
  endDate.value = new Date(datetime.split(' ')[0] + ' ' + convertTo12Hour(datetime.split(' ')[1]));

  dialog.value = true;
};

const handlerDateClicked = (date: string) => {
  console.log(date);
  selectedColor.value = 'yellow';
  startDate.value = new Date(date);
  endDate.value = new Date(date);
  newEvent.value.title = '';
  newEvent.value.description = '';
  newEvent.value.color = 'yellow';
  newEvent.value.time.start = formatDateAndTime(startDate.value);
  newEvent.value.time.end = formatDateAndTime(endDate.value);
  dialog_title.value = '일정 생성';
  create.value = true;
  dialog.value = true;
};

const createEvent = async () => {
  console.log(newEvent.value);
  newEvent.value.time.start = computedDateTimeFormattedStart.value.toString();
  newEvent.value.time.end = computedDateTimeFormattedEnd.value.toString();
  newEvent.value.color = selectedColor.value;
  const schedule = toScheduleDTO(newEvent.value);
  await axios.post(`${baseUrl}/employee/schedule/create`, schedule);
  newEvent.value = {
    id: '',
    title: '',
    description: '',
    time: {
      start: '',
      end: ''
    },
    color: ''
  };
  dialog.value = false;
  reloadEvents();
};
const modifyEvent = async () => {
  console.log('수정 요청');
  newEvent.value.time.start = computedDateTimeFormattedStart.value.toString();
  newEvent.value.time.end = computedDateTimeFormattedEnd.value.toString();
  newEvent.value.color = selectedColor.value;
  const schedule = toScheduleDTO(newEvent.value);
  console.log(schedule);
  await axios.patch(`${baseUrl}/employee/schedule/update/${newEvent.value.id}`, schedule);
  newEvent.value = {
    id: 0,
    title: '',
    description: '',
    time: {
      start: '',
      end: ''
    },
    color: ''
  };
  dialog.value = false;
  reloadEvents();
};
</script>

<style>
@import 'qalendar/dist/style.css';
.qalendar-wrapper {
  height: 600px;
}
</style>
