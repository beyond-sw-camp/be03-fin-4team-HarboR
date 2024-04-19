<!-- eslint-disable prettier/prettier -->
<template>
    <Qalendar 
        :config="config"
        :events="events"
    />
</template>

<script lang="ts">
import { Qalendar } from 'qalendar';
import axios from 'axios'; // axios를 import합니다.

export default {
  components: {
    Qalendar
  },

  data() {
    return {
      events: [], // events를 빈 배열로 초기화합니다.
      config: {
        week: {
          startsOn: 'sunday',
          nDays: 7,
          scrollToHour: 5
        },
        month: {
          showTrailingAndLeadingDates: false
        },
        locale: 'en-US',
        defaultMode: 'day',
        isSilent: true,
        showCurrentTime: true
      }
    };
  },

  async created() { // 또는 mounted()를 사용할 수 있습니다.
    const response = await axios.get('YOUR_API_URL'); // 여기에 실제 API URL을 입력하세요.
    this.events = response.data.map((item: CommuteReqDTO) => ({
      title: item.employeeId, // 적절한 필드를 선택하여 매핑합니다.
      time: { start: item.attendanceTime, end: item.leaveworkTime },
      color: 'green', // 색상을 선택합니다.
      isEditable: true,
      id: item.employeeId, // 각 이벤트에 고유한 ID를 제공합니다.
      description: item.status_code // 적절한 필드를 선택하여 매핑합니다.
    }));
  }
};
</script>

<style>
@import 'qalendar/dist/style.css';
</style>
