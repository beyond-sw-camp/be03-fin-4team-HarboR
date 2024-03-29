<script setup lang="ts">
import { shallowRef } from 'vue';

const projects = shallowRef([
  {
    subject: 'Website down for one week',
    dept: 'Support',
    date: 'Today 2:00',
    status: 'Open'
  },
  {
    subject: 'Loosing control on server',
    dept: 'Support',
    date: 'Yesterday',
    status: 'Progress'
  },
  {
    subject: 'Authorizations keys',
    dept: 'Support',
    date: '27, Aug',
    status: 'Closed'
  },
  {
    subject: 'Restoring default settings',
    dept: 'Support',
    date: 'Today 9:00 ',
    status: 'Open'
  },
  {
    subject: 'Loosing control on server',
    dept: 'Support',
    date: 'Yesterday',
    status: 'Progress'
  },
  {
    subject: 'Authorizations keys',
    dept: 'Support',
    date: '27, Aug',
    status: 'Closed'
  },
  {
    subject: 'Restoring default settings',
    dept: 'Support',
    date: 'Today 9:00',
    status: 'Open'
  },
  {
    subject: 'Authorizations keys',
    dept: 'Support',
    date: '27, Aug',
    status: 'Closed'
  }
]);
</script>

<template>
  <v-table>
    <thead>
      <tr>
        <th class="text-left text-subtitle-1">Subject</th>
        <th class="text-left text-subtitle-1">Department</th>
        <th class="text-left text-subtitle-1">Date</th>
        <th class="text-left text-subtitle-1">Status</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in projects" :key="item.subject">
        <td class="py-4">
          {{ item.subject }}
        </td>
        <td class="py-4">{{ item.dept }}</td>
        <td class="py-4">{{ item.date }}</td>
        <td class="py-4">
          <v-chip variant="outlined" size="small">{{ item.status }}</v-chip>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>
