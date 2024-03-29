<script setup lang="ts">
import { shallowRef } from 'vue';

// icons
import { MailIcon, MapPinIcon, PhoneIcon, EditIcon } from 'vue-tabler-icons';

const items = shallowRef([
  { text: 'Email', icon: MailIcon, divider: true, subtext: 'demo@sample.com' },
  { text: 'Phone', icon: PhoneIcon, divider: true, subtext: '(+99) 9999 999 999' },
  { text: 'Location', icon: MapPinIcon, divider: false, subtext: 'Melbourne' }
]);

const details = shallowRef([
  {
    title: 'Full Name',
    subtext: 'John Doe'
  },
  {
    title: 'Fathers Name',
    subtext: 'Mr. Deepen Handgun'
  },
  {
    title: 'Address',
    subtext: 'Street 110-B Kalians Bag, Dewan, M.P. INDIA'
  },
  {
    title: 'Zip Code',
    subtext: '12345'
  },
  {
    title: 'Phone',
    subtext: '+0 123456789 , +0 123456789'
  },
  {
    title: 'Email',
    subtext: 'support@example.com'
  },
  {
    title: 'Website',
    subtext: 'http://example.com'
  }
]);

const edus = shallowRef([
  {
    year: '2014-2017',
    degree: 'Master Degree',
    title: 'Master Degree in Computer Application',
    location: 'University of Oxford, England'
  },
  {
    year: '2011-2013',
    degree: 'Bachelor',
    title: 'Bachelor Degree in Computer Engineering',
    location: 'Imperial College London'
  },
  {
    year: '2009-2011',
    degree: 'School',
    title: 'Higher Secondary Education',
    location: 'School of London, England'
  }
]);

const emps = shallowRef([
  {
    year: 'Current',
    post: 'Senior',
    title: 'Senior UI/UX designer',
    subtext: 'Perform task related to project manager with the 100+ team under my observation. Team management is key role in this company.'
  },
  {
    year: '2017-2019',
    post: 'Junior',
    title: 'Trainee cum Project Manager',
    subtext: 'Microsoft, TX, USA'
  }
]);

const skills = shallowRef([
  {
    color: 'primary',
    title: 'Junior',
    value: 70
  },
  {
    color: 'primary',
    title: 'UX Researcher',
    value: 80
  },
  {
    color: 'secondary',
    title: 'Wordpress',
    value: 25
  },
  {
    color: 'primary',
    title: 'Graphic Designer',
    value: 80
  },
  {
    color: 'secondary',
    title: 'HTML',
    value: 45
  },
  {
    color: 'primary',
    title: 'PHP',
    value: 65
  }
]);
</script>

<template>
  <v-row>
    <v-col cols="12" lg="4">
      <v-card variant="flat">
        <v-card variant="outlined" class="overflow-hidden">
          <v-list lines="two">
            <v-list-item>
              <template v-slot:prepend>
                <img src="@/assets/images/users/avatar-3.png" alt="John Doe" width="40" class="v-avatar" />
              </template>
              <template v-slot:title>
                <h5 class="text-subtitle-1 mt-1">John Doe</h5>
              </template>
              <template v-slot:subtitle>
                <span class="text-subtitle-2 text-medium-emphasis font-weight-medium">UI/UX Designer</span>
              </template>
              <template v-slot:append>
                <v-chip color="primary" size="small" class="mt-2" variant="flat">Pro</v-chip>
              </template>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-card-text>
            <v-list>
              <template v-for="(item, i) in items" :key="i">
                <v-list-item color="primary" :value="item" class="py-4">
                  <template v-slot:prepend>
                    <!-- <v-icon :icon="item.icon"></v-icon> -->
                    <component :is="item.icon" size="20" stroke-width="1.5" class="mr-2" />
                  </template>

                  <v-list-item-title class="text-subtitle-1">
                    {{ item.text }}
                  </v-list-item-title>

                  <template v-slot:append>
                    <span class="text-subtitle-2 text-disabled font-weight-medium">{{ item.subtext }}</span>
                  </template>
                </v-list-item>
                <v-divider v-if="item.divider"></v-divider>
              </template>
            </v-list>
            <v-row justify="center" class="mt-4">
              <v-col md="3" class="text-center">
                <h3 class="text-h3">37</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Mails </span>
              </v-col>
              <v-col md="3" class="text-center">
                <h3 class="text-h3">2749</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Followers</span>
              </v-col>
              <v-col md="3" class="text-center">
                <h3 class="text-h3">678</h3>
                <span class="text-subtitle-2 text-disabled font-weight-medium">Following</span>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>
    </v-col>
    <v-col cols="12" lg="8">
      <v-card variant="flat">
        <v-card variant="outlined" class="overflow-hidden">
          <v-list>
            <v-list-item>
              <template v-slot:title>
                <h5 class="text-subtitle-1 mt-1">About Me</h5>
              </template>
              <template v-slot:append>
                <v-btn variant="text" size="small" color="primary">
                  <EditIcon size="20" stroke-width="1.5" />
                </v-btn>
              </template>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>
          <v-card-text>
            <p>
              Hello,I’m Anshan Handgun Creative Graphic Designer & User Experience Designer based in Website, I create digital Products a
              more Beautiful and usable place. Morbid accusant ipsum. Nam nec tellus at.
            </p>
            <h5 class="text-subtitle-1 mt-6 pb-4">Personal Details</h5>
            <v-divider class="mb-4"></v-divider>

            <v-row class="py-2" v-for="(detail, i) in details" :key="i">
              <v-col md="3" class="text-subtitle-1">{{ detail.title }}</v-col>
              <v-col md="1" class="text-subtitle-1">:</v-col>
              <v-col md="8" class="text-medium-emphasis">{{ detail.subtext }}</v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-card>

      <v-card variant="flat" class="mt-6">
        <v-card variant="outlined">
          <div class="px-5 py-6">
            <h5 class="text-subtitle-1">Education</h5>
          </div>
          <v-divider></v-divider>
          <v-card-text>
            <v-row class="py-2" v-for="(edu, i) in edus" :key="i">
              <v-col md="4">
                <h4 class="text-subtitle-1">{{ edu.year }}</h4>
                <span class="text-subtitle-2 text-disabled font-weight-medium">{{ edu.degree }}</span>
              </v-col>
              <v-col md="8">
                <h4 class="text-subtitle-1">{{ edu.title }}</h4>
                <span class="text-subtitle-2 text-disabled font-weight-medium">{{ edu.location }}</span>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <v-card variant="flat" class="mt-6">
          <v-card variant="outlined">
            <div class="px-5 py-6">
              <h5 class="text-subtitle-1">Employment</h5>
            </div>
            <v-divider></v-divider>
            <v-card-text>
              <v-row class="py-2" v-for="(emp, i) in emps" :key="i">
                <v-col md="4">
                  <h4 class="text-subtitle-1">{{ emp.year }}</h4>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ emp.post }}</span>
                </v-col>
                <v-col md="8">
                  <h4 class="text-subtitle-1">{{ emp.title }}</h4>
                  <span class="text-subtitle-2 text-disabled font-weight-medium">{{ emp.subtext }}</span>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-card>

        <v-card variant="flat" class="mt-6">
          <v-card variant="outlined">
            <div class="px-5 py-6">
              <h5 class="text-subtitle-1">Skills</h5>
            </div>
            <v-divider></v-divider>
            <v-card-text>
              <v-row class="py-2">
                <v-col cols="12" md="6" v-for="(skill, i) in skills" :key="i">
                  <h5 class="text-subtitle-1 font-weight-regular">{{ skill.title }}</h5>
                  <div class="d-flex align-center">
                    <v-progress-linear style="left: 0; transform: unset" :model-value="skill.value" :color="skill.color">
                    </v-progress-linear>
                    <span class="text-subtitle-1 ml-4 text-medium-emphasis">{{ skill.value }}%</span>
                  </div>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>
