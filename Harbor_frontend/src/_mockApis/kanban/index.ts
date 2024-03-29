// third-party
import mock from '../mockAdapter';
import { add, set, sub } from 'date-fns';
import { Chance } from 'chance';
import back1 from '@/assets/images/profile/profile-back-1.png';
import back2 from '@/assets/images/profile/profile-back-2.png';
import back3 from '@/assets/images/profile/profile-back-3.png';
import back4 from '@/assets/images/profile/profile-back-4.png';

const chance = new Chance();
type taskType = {
  description: string;
  dueDate: Date;
  id: number;
  userStory: string;
  image?: string;
  priority: string;
  title: string;
};

type KanbanItem = {
  title: string;
  id: number;
  tasks: taskType[];
};

const KanbanData: KanbanItem[] = [
  {
    title: 'New',
    id: 1,
    tasks: [
      {
        description: 'Content of item 1',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: sub(new Date(), { days: 12 }),
        id: 1,
        image: back1,
        priority: 'low',
        title: 'Online fees payment & instant announcements'
      },
      {
        description: 'Content of item 8',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: add(new Date(), { days: 17 }),
        id: 8,

        priority: 'low',
        title: 'Attendance checking & homework details'
      },
      {
        description: 'Content of item 9',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: add(new Date(), { days: 8 }),
        id: 9,

        priority: 'high',
        title: 'Admission, Staff & Schedule management'
      }
    ]
  },
  {
    title: 'Active',
    id: 2,
    tasks: [
      {
        description: 'Content of item 3',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: sub(new Date(), { days: 8 }),
        id: 3,

        priority: 'low',
        title: 'Update React & TypeScript version'
      },
      {
        description: 'Content of item 4',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: sub(new Date(), { days: 6 }),
        id: 4,
        image: back2,
        priority: 'low',
        title: 'Set allowing rules for trusted applications.'
      },
      {
        description: 'Content of item 5',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: sub(new Date(), { days: 9 }),
        id: 5,
        image: back3,
        priority: 'medium',
        title: 'Managing Applications Launch Control'
      }
    ]
  },
  {
    title: 'Resolved',
    id: 3,
    tasks: [
      {
        description: 'Content of item 2',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: sub(new Date(), { days: 18 }),
        id: 2,

        priority: 'high',
        title: 'Creation and Maintenance of Inventory Objects'
      }
    ]
  },
  {
    title: 'Closed',
    id: 4,
    tasks: [
      {
        description: 'Content of item 6',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: set(new Date(), { hours: 10, minutes: 30 }),
        id: 6,

        priority: 'medium',
        title: 'Run codemods'
      },
      {
        description: 'Content of item 7',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: add(new Date(), { days: 5 }),
        id: 7,
        image: back4,
        priority: 'low',
        title: 'Purchase Requisitions, Adjustments, and Transfers.'
      },

      {
        description: 'Content of item 10',
        userStory: `${chance.integer({ min: 1000, max: 9999 })}`,
        dueDate: add(new Date(), { days: 12 }),
        id: 10,

        priority: 'low',
        title: 'Handling breaking changes'
      }
    ]
  }
];

mock.onGet('/api/data/kanban').reply(() => {
  return [200, KanbanData];
});

export default KanbanData;
