// project imports
import mock from '../mockAdapter';
import user1 from '@/assets/images/users/avatar-1.png';
import user2 from '@/assets/images/users/avatar-2.png';
import user8 from '@/assets/images/users/avatar-6.png';
import user3 from '@/assets/images/users/avatar-3.png';
import user4 from '@/assets/images/users/avatar-4.png';
import user5 from '@/assets/images/users/avatar-5.png';
import user6 from '@/assets/images/users/avatar-6.png';
import user7 from '@/assets/images/users/avatar-7.png';
import user11 from '@/assets/images/users/avatar-3.png';
import user12 from '@/assets/images/users/avatar-4.png';
import user9 from '@/assets/images/users/avatar-5.png';
import user10 from '@/assets/images/users/avatar-2.png';

// types
export type KeyedObject = {
  [key: string]: string | number | KeyedObject;
};

// friends list
const friends: KeyedObject[] = [
  {
    id: '#1Friends_Barney',
    avatar: user1,
    name: 'Barney',
    location: 'Handburgh',
    mutual: 10
  },
  {
    id: '#2Friends_Thea',
    avatar: user2,
    name: 'Thea',
    location: 'New jana',
    mutual: 89
  },
  {
    id: '#3Friends_Guiseppe',
    avatar: user3,
    name: 'Guiseppe',
    location: 'Jenkinsstad',
    mutual: 65
  },
  {
    id: '#4Friends_Henderson',
    avatar: user4,
    name: 'Henderson',
    location: 'South Antonina',
    mutual: 1
  },
  {
    id: '#5Friends_Maddison',
    avatar: user5,
    name: 'Maddison',
    location: 'New Dorthy',
    mutual: 14
  },
  {
    id: '#6Friends_Wilber',
    avatar: user6,
    name: 'Wilber',
    location: 'Twilahsven',
    mutual: 36
  },
  {
    id: '#7Friends_Hayden',
    avatar: user7,
    name: 'Hayden',
    location: 'Darrelshaire',
    mutual: 106
  },
  {
    id: '#8Friends_Lloyd',
    avatar: user8,
    name: 'Lloyd',
    location: 'New Credrick',
    mutual: 10
  },
  {
    id: '#9Friends_Kris',
    avatar: user9,
    name: 'Kris',
    location: 'New Dianna',
    mutual: 0
  },
  {
    id: '#10Friends_Kyler',
    avatar: user10,
    name: 'Kyler',
    location: 'Murraymouth',
    mutual: 48
  },
  {
    id: '#11Friends_Pamela',
    avatar: user11,
    name: 'Pamela',
    location: 'Murraymouth',
    mutual: 72
  },
  {
    id: '#12Friends_Betty',
    avatar: user12,
    name: 'John Doe',
    location: 'North Zole',
    mutual: 99
  },
  {
    id: '#13Friends_Anthony',
    avatar: user1,
    name: 'Anthony',
    location: 'Lake Judy',
    mutual: 78
  },
  {
    id: '#14Friends_Reggie',
    avatar: user3,
    name: 'Reggie',
    location: 'Kailynland',
    mutual: 1
  },
  {
    id: '#15Friends_Francesca',
    avatar: user4,
    name: 'Francesca',
    location: 'Pagacview',
    mutual: 68
  },
  {
    id: '#16Friends_Carmel',
    avatar: user5,
    name: 'Carmel',
    location: 'Port Lerashire',
    mutual: 15
  },
  {
    id: '#17Friends_Darwin',
    avatar: user6,
    name: 'Darwin',
    location: 'North Jacquesside',
    mutual: 10
  },
  {
    id: '#18Friends_Kaylin',
    avatar: user7,
    name: 'Kaylin',
    location: 'Bergstrombury',
    mutual: 36
  },
  {
    id: '#19Friends_Kamryn',
    avatar: user8,
    name: 'Kamryn',
    location: 'South Norma',
    mutual: 55
  },
  {
    id: '#20Friends_Madelyn',
    avatar: user9,
    name: 'Madelyn',
    location: 'Port Opheliamouth',
    mutual: 78
  }
];

// ==============================|| MOCK SERVICES ||============================== //

mock.onGet('/api/friends/list').reply(200, { friends });
