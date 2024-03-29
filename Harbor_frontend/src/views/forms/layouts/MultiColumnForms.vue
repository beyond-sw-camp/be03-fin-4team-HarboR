<script setup lang="ts">
import { ref } from 'vue';
// common components
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

// icons
import { LockIcon, LinkIcon } from 'vue-tabler-icons';

// theme breadcrumb
const page = ref({ title: 'Multi Column Forms' });
const breadcrumbs = ref([
  {
    title: 'Layouts',
    disabled: false,
    href: '#'
  },
  {
    title: 'Multi Column Forms',
    disabled: true,
    href: '#'
  }
]);

const checkbox1 = ref(true);
const checkbox2 = ref(false);
const checkbox3 = ref(false);
const checkbox4 = ref(true);
const checkbox5 = ref(false);
const checkbox6 = ref(true);
const checkbox7 = ref(false);
</script>

<template>
  <!-- ----------------------------------------------------- -->
  <!-- Multi Column Forms -->
  <!-- ----------------------------------------------------- -->
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <!-- 2 Columns Form Layout -->
    <v-col cols="12">
      <UiParentCard title="2 Columns Form Layout">
        <v-row>
          <!-- column 1 -->
          <v-col cols="12" lg="6">
            <v-label class="mb-2 font-weight-medium">Name</v-label>
            <v-text-field
              hint="Please enter your full name"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter full name"
              color="primary"
            ></v-text-field>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="6">
            <v-label class="mb-2 font-weight-medium">Email</v-label>
            <v-text-field
              hint="Please enter your Email"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter email"
              color="primary"
            ></v-text-field>
          </v-col>
          <!-- column 3 -->
          <v-col cols="12" lg="6">
            <v-label class="mb-2 font-weight-medium">Password</v-label>
            <v-text-field
              hint="Please enter Password"
              persistent-hint
              variant="outlined"
              type="password"
              persistent-placeholder
              placeholder="Enter password"
              color="primary"
            >
              <template v-slot:append-inner>
                <LockIcon stroke-width="1.5" size="22" />
              </template>
            </v-text-field>
          </v-col>
          <!-- column 4 -->
          <v-col cols="12" lg="6">
            <v-label class="mb-2 font-weight-medium">Profile URL</v-label>
            <v-text-field
              hint="Please enter your Profile URL"
              persistent-hint
              variant="outlined"
              type="password"
              persistent-placeholder
              placeholder="Please enter your Profile URL"
              color="primary"
            >
              <template v-slot:append-inner>
                <LinkIcon stroke-width="1.5" size="22" />
              </template>
            </v-text-field>
          </v-col>
          <!-- column 5 -->
          <v-col cols="12" lg="6">
            <v-label class="mb-2 font-weight-medium">Language</v-label>
            <div class="d-flex ga-4">
              <v-checkbox
                density="compact"
                v-model="checkbox1"
                hide-details
                color="primary"
                label="English"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox
                density="compact"
                v-model="checkbox2"
                hide-details
                color="primary"
                label="French"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox density="compact" v-model="checkbox3" hide-details color="primary" label="Dutch" class="flex-grow-0"></v-checkbox>
            </div>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!-- 2 Columns Horizontal Form Layout -->
    <v-col cols="12">
      <UiParentCard title="2 Columns Horizontal Form Layout">
        <v-row>
          <!-- column 1 -->
          <v-col cols="12" lg="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4">
                <v-label class="font-weight-medium mt-4">Name</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your full name"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter full name"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4">
                <v-label class="font-weight-medium mt-4">Password</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter Password"
                  persistent-hint
                  variant="outlined"
                  type="password"
                  persistent-placeholder
                  placeholder="Enter password"
                  color="primary"
                >
                  <template v-slot:append-inner>
                    <LockIcon stroke-width="1.5" size="22" />
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
            <!-- row 3 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4">
                <v-label class="font-weight-medium mt-4">Language</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <div class="d-flex ga-4">
                  <v-checkbox
                    density="compact"
                    v-model="checkbox1"
                    hide-details
                    color="primary"
                    label="English"
                    class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                    density="compact"
                    v-model="checkbox2"
                    hide-details
                    color="primary"
                    label="French"
                    class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                    density="compact"
                    v-model="checkbox3"
                    hide-details
                    color="primary"
                    label="Dutch"
                    class="flex-grow-0"
                  ></v-checkbox>
                </div>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4">
                <v-label class="font-weight-medium mt-4">Email</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your Email"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter email"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4">
                <v-label class="font-weight-medium mt-4">Profile URL</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your Profile URL"
                  persistent-hint
                  variant="outlined"
                  type="password"
                  persistent-placeholder
                  placeholder="Please enter your Profile URL"
                  color="primary"
                >
                  <template v-slot:append-inner>
                    <LinkIcon stroke-width="1.5" size="22" />
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!-- 3 Columns Form Layout -->
    <v-col cols="12">
      <UiParentCard title="3 Columns Form Layout">
        <v-row>
          <!-- column 1 -->
          <v-col cols="12" lg="4">
            <v-label class="font-weight-medium mb-2">Name</v-label>

            <v-text-field
              hint="Please enter your full name"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter full name"
              color="primary"
            ></v-text-field>
            <v-label class="font-weight-medium mb-2 mt-5">Contact</v-label>

            <v-text-field
              hint="Please enter your contact"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter contact number"
              color="primary"
            ></v-text-field>
            <v-label class="font-weight-medium mb-2 mt-5">Address</v-label>

            <v-text-field
              hint="Please enter your address"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter your address"
              color="primary"
            ></v-text-field>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="4">
            <v-label class="font-weight-medium mb-2">Email</v-label>

            <v-text-field
              hint="Please enter your Email"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter email"
              color="primary"
            ></v-text-field>

            <v-label class="font-weight-medium mb-2 mt-5">Profile URL</v-label>

            <v-text-field
              hint="Please enter your Profile URL"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter Profile URL"
              color="primary"
            >
              <template v-slot:append-inner>
                <LinkIcon stroke-width="1.5" size="22" />
              </template>
            </v-text-field>

            <v-label class="font-weight-medium mb-2 mt-5">User Type</v-label>
            <div class="d-flex ga-4">
              <v-checkbox
                density="compact"
                v-model="checkbox4"
                hide-details
                color="primary"
                label="Administrator"
                class="flex-grow-0"
              ></v-checkbox>
              <v-checkbox
                density="compact"
                v-model="checkbox5"
                hide-details
                color="primary"
                label="Author"
                class="flex-grow-0"
              ></v-checkbox>
            </div>
          </v-col>
          <!-- column 3 -->
          <v-col cols="12" lg="4">
            <v-label class="font-weight-medium mb-2">Password</v-label>

            <v-text-field
              hint="Please enter Password"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter password"
              color="primary"
            >
              <template v-slot:prepend-inner>
                <LockIcon stroke-width="1.5" size="22" />
              </template>
            </v-text-field>
            <v-label class="font-weight-medium mb-2 mt-5">Pincode</v-label>

            <v-text-field
              hint="Please enter your postcode"
              persistent-hint
              variant="outlined"
              persistent-placeholder
              placeholder="Enter your postcode"
              color="primary"
            >
            </v-text-field>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
    <!-- 3 Columns Form Layout -->
    <v-col cols="12">
      <UiParentCard title="3 Columns Form Layout">
        <!-- Row 1 -->
        <v-row class="pb-6">
          <!-- column 1 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Name</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your full name"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter full name"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Email</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your Email"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter email"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 3 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Password</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter Password"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter password"
                  color="primary"
                >
                  <template v-slot:prepend-inner>
                    <LockIcon stroke-width="1.5" size="22" />
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <!-- row 2 -->
        <v-row class="pt-6">
          <!-- column 1 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center pb-6">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Contact</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your contact"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter contact number"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center pb-6">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Profile URL</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your Profile URL"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter Profile URL"
                  color="primary"
                >
                  <template v-slot:append-inner>
                    <LinkIcon stroke-width="1.5" size="22" />
                  </template>
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 3 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center pb-6">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Pincode</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your postcode"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter your postcode"
                  color="primary"
                >
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <!-- row 3 -->
        <v-row class="pt-6">
          <!-- column 1 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center pb-6">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">Address</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <v-text-field
                  hint="Please enter your address"
                  persistent-hint
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Enter your address"
                  color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" lg="4">
            <v-row class="d-flex align-center pb-6">
              <v-col cols="12" lg="4" class="py-0 py-md-3">
                <v-label class="font-weight-medium d-flex justify-start justify-md-end mb-0 mb-md-5">User Type</v-label>
              </v-col>
              <v-col cols="12" lg="8">
                <div>
                  <v-checkbox
                    density="compact"
                    v-model="checkbox6"
                    hide-details
                    color="primary"
                    label="Administrator"
                    class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                    density="compact"
                    v-model="checkbox7"
                    hide-details
                    color="primary"
                    label="Author"
                    class="flex-grow-0"
                  ></v-checkbox>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>
