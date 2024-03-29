<script setup lang="ts">
import { ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import CardHeaderFooter from '@/components/shared/CardHeaderFooter.vue';
import WidgetCard from '@/components/shared/WidgetCard.vue';
import WidgetCardv2 from '@/components/shared/WidgetCardv2.vue';

// imports widgets
import TodoList from './components/TodoList.vue';
import TrafficSources from './components/TrafficSources.vue';
import TeamMembers from './components/TeamMembers.vue';
import UserActivity from './components/UserActivity.vue';
import LatestPosts from './components/LatestPosts.vue';
import Messages from './components/MessageList.vue';
import Projects from './components/ProjectList.vue';
import ProductSales from './components/ProductSales.vue';
import Tasks from './components/TaskList.vue';
import ApplicationSales from './components/ApplicationSales.vue';
import ActiveTickets from './components/ActiveTickets.vue';
import Feeds from './components/FeedList.vue';
import LatestCustomer from './components/LatestCustomer.vue';
import LatestOrder from './components/LatestOrder.vue';
import IncomingRequest from './components/IncomingRequest.vue';
import TotalRevenue from './components/TotalRevenue.vue';
import NewCustomers from './components/NewCustomers.vue';
import RecentTickets from './components/RecentTickets.vue';

const page = ref({ title: 'Data' });
const breadcrumbs = ref([
  {
    title: 'Widget',
    disabled: false,
    href: '#'
  },
  {
    title: 'Data',
    disabled: true,
    href: '#'
  }
]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>

  <v-row>
    <!-- ---------------------------------------------------------------- -->
    <!-- Todo List -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <CardHeaderFooter title="To Do List">
        <TodoList />
        <template v-slot:footer>
          <div class="text-right mt-2 w-100">
            <v-btn color="primary" variant="flat" icon>
              <PlusIcon stroke-width="1.5" size="24" />
            </v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Traffic Sources -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCard title="Traffic Sources">
        <TrafficSources />
      </WidgetCard>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Team Members -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <CardHeaderFooter title="Team Members">
        <TeamMembers />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- User Activity -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="User Activity" class="activity-card">
        <UserActivity />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Messages -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="6">
      <CardHeaderFooter title="Messages">
        <Messages />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View Friend List </v-btn>
          </div>
        </template>
      </CardHeaderFooter>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Projects -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="6">
      <WidgetCardv2 title="Projects">
        <Projects />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Product Sales -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="6">
      <WidgetCardv2 title="Product Sales" hideaction>
        <ProductSales />
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Tasks -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="Tasks">
        <Tasks />
        <template v-slot:footer>
          <div class="text-sm-right text-center mt-3 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Application Sales -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="8">
      <WidgetCardv2 title="Application Sales">
        <ApplicationSales />
        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Active Tickets -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="8">
      <WidgetCardv2 title="Active Tickets">
        <ActiveTickets />
        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Projects </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Latest Posts -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="Latest Posts">
        <LatestPosts />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View Friend List </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Feeds -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="Feeds">
        <Feeds />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Feeds </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Latest Customers -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="8">
      <WidgetCardv2 title="Latest Customers">
        <LatestCustomer />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Latest Customers </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Latest Order -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12">
      <WidgetCardv2 title="Latest Order">
        <LatestOrder />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Orders </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Incoming Requests -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="Incoming Requests">
        <IncomingRequest />

        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> Show More </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Total Revenue -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="Total Revenue" hideaction>
        <TotalRevenue />
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- New Customers -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12" lg="4">
      <WidgetCardv2 title="New Customers" hideaction>
        <NewCustomers />
      </WidgetCardv2>
    </v-col>

    <!-- ---------------------------------------------------------------- -->
    <!-- Recent Tickets -->
    <!-- ---------------------------------------------------------------- -->
    <v-col cols="12">
      <WidgetCardv2 title="Recent Tickets">
        <RecentTickets />
        <template v-slot:footer>
          <div class="text-sm-right text-center mt-2 w-100">
            <v-btn color="primary" variant="text"> View All Tickets </v-btn>
          </div>
        </template>
      </WidgetCardv2>
    </v-col>
  </v-row>
</template>

<style lang="scss">
.activity-card {
  .v-card-text {
    padding: 0;
  }
}
</style>
