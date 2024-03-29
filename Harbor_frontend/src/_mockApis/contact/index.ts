// project imports
import mock from '../mockAdapter';
import user1 from '../../assets/images/users/avatar-1.png';
import user2 from '../../assets/images/users/avatar-2.png';
import user3 from '../../assets/images/users/avatar-3.png';
import user4 from '../../assets/images/users/avatar-4.png';
import user5 from '../../assets/images/users/avatar-5.png';
import user6 from '../../assets/images/users/avatar-6.png';
import user7 from '../../assets/images/users/avatar-7.png';
import user8 from '../../assets/images/users/avatar-1.png';
import user9 from '../../assets/images/users/avatar-2.png';

// contact list
const contacts = [
  {
    id: 1,
    name: 'Alene',
    company: 'ABC Pvt Ltd',
    role: 'Sr. Customer Manager',
    work_email: 'alene_work@company.com',
    personal_email: 'alene@company.com',
    work_phone: '380-293-0177',
    personal_phone: '380-293-0177',
    location: 'Port Narcos',
    avatar: user1,
    status: 'Technical Department',
    lastMessage: '2h ago',
    birthdayText: 'happy Birthday Alene',
    unReadChatCount: 2,
    online_status: 'available'
  },
  {
    id: 2,
    name: 'Keefe',
    company: 'ABC Pvt Ltd',
    role: 'Dynamic Operations Officer',
    work_email: 'keefe_work@gmil.com',
    personal_email: 'keefe@gmil.com',
    work_phone: '253-418-5940',
    personal_phone: '253-418-5940',
    location: 'Afghanistan',
    avatar: user2,
    status: 'Support Executive',
    lastMessage: '1:20 AM',
    birthdayText: 'happy Birthday Keefe',
    unReadChatCount: 3,
    online_status: 'available'
  },
  {
    id: 3,
    name: 'Lazaro',
    company: 'ABC Pvt Ltd',
    role: 'Resource Investigator',
    work_email: 'lazaro_work@gmil.com',
    personal_email: 'lazaro@gmil.com',
    work_phone: '283-029-1364',
    personal_phone: '283-029-1364',
    location: 'Albania',
    avatar: user3,
    status: 'Resource Investigator',
    lastMessage: 'Yesterday',
    birthdayText: 'happy Birthday Lazaro',
    unReadChatCount: 1,
    online_status: 'available'
  },
  {
    id: 4,
    name: 'Hazle',
    company: 'ABC Pvt Ltd',
    role: 'Teamworker',
    work_email: 'hazle_work@gmil.com',
    personal_email: 'hazle@gmil.com',
    work_phone: '380-293-0177',
    personal_phone: '380-293-0177',
    location: 'Algeria',
    avatar: user4,
    status: 'Teamworker',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Hazle',
    unReadChatCount: 0,
    online_status: 'do_not_disturb'
  },
  {
    id: 5,
    name: 'Herman Essertg',
    company: 'ABC Pvt Ltd',
    role: 'Co-ordinator',
    work_email: 'herman_essertg_work@gmil.com',
    personal_email: 'herman_essertg@gmil.com',
    work_phone: '253-418-5940',
    personal_phone: '253-418-5940',
    location: 'Andorra',
    avatar: user5,
    status: 'Co-ordinator',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Herman',
    unReadChatCount: 0,
    online_status: 'do_not_disturb'
  },
  {
    id: 6,
    name: 'Wilhelmine Durrg',
    company: 'ABC Pvt Ltd',
    role: 'Monitor Evaluator',
    work_email: 'wilhelmine_durrg_work@gmil.com',
    personal_email: 'wilhelmine_durrg@gmil.com',
    work_phone: '380-293-0177',
    personal_phone: '380-293-0177',
    location: 'Angola',
    avatar: user6,
    status: 'Monitor Evaluator',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Wilhelmine',
    unReadChatCount: 0,
    online_status: 'available'
  },
  {
    id: 7,
    name: 'Agilulf Fuxg',
    company: 'ABC Pvt Ltd',
    role: 'Specialist',
    work_email: 'agilulf_fuxg_work@gmil.com',
    personal_email: 'agilulf_fuxg@gmil.com',
    work_phone: '253-418-5940',
    personal_phone: '253-418-5940',
    location: 'Antigua and Barbuda',
    avatar: user7,
    status: 'Specialist',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Agilulf',
    unReadChatCount: 0,
    online_status: 'available'
  },
  {
    id: 8,
    name: 'Adaline Bergfalks',
    company: 'ABC Pvt Ltd',
    role: 'Shaper',
    work_email: 'adaline_bergfalks_work@gmil.com',
    personal_email: 'adaline_bergfalks@gmil.com',
    work_phone: '253-118-5940',
    personal_phone: '253-118-5940',
    location: 'Argentina',
    avatar: user8,
    status: 'Shaper',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Adaline',
    unReadChatCount: 0,
    online_status: 'offline'
  },
  {
    id: 9,
    name: 'Eadwulf Beckete',
    company: 'ABC Pvt Ltd',
    role: 'Implementer',
    work_email: 'eadwulf_beckete_work@gmil.com',
    personal_email: 'eadwulf_beckete@gmil.com',
    work_phone: '153-418-5940',
    personal_phone: '153-418-5940',
    location: 'Armenia',
    avatar: user9,
    status: 'Implementer',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Eadwulf',
    unReadChatCount: 0,
    online_status: 'offline'
  },
  {
    id: 10,
    name: 'Midas',
    company: 'ABC Pvt Ltd',
    role: 'Leader',
    work_email: 'midas_work@gmil.com',
    personal_email: 'midas@gmil.com',
    work_phone: '252-418-5940',
    personal_phone: '252-418-5940',
    location: 'Australia',
    avatar: user1,
    status: 'Leader',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Midas',
    unReadChatCount: 0,
    online_status: 'offline'
  },
  {
    id: 11,
    name: 'Uranus',
    company: 'ABC Pvt Ltd',
    role: 'Facilitator',
    work_email: 'uranus_work@gmil.com',
    personal_email: 'uranus@gmil.com',
    work_phone: '253-218-5940',
    personal_phone: '253-218-5940',
    location: 'Austria',
    avatar: user3,
    status: 'Facilitator',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Uranus',
    unReadChatCount: 0,
    online_status: 'available'
  },
  {
    id: 12,
    name: 'Peahen',
    company: 'ABC Pvt Ltd',
    role: 'Coach',
    work_email: 'peahen_work@gmil.com',
    personal_email: 'peahen@gmil.com',
    work_phone: '253-418-1940',
    personal_phone: '253-418-1940',
    location: 'Azerbaijan',
    avatar: user4,
    status: 'One of the Graces.',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Peahen',
    unReadChatCount: 0,
    online_status: 'do_not_disturb'
  },
  {
    id: 13,
    name: 'Menelaus',
    company: 'ABC Pvt Ltd',
    role: 'Facilitator',
    work_email: 'menelaus_work@gmil.com',
    personal_email: 'menelaus@gmil.com',
    work_phone: '053-418-5940',
    personal_phone: '053-418-5940',
    location: 'Bahamas',
    avatar: user5,
    status: 'To stay',
    lastMessage: '4/25/2021',
    birthdayText: 'happy Birthday Menelaus',
    unReadChatCount: 0,
    online_status: 'offline'
  }
];

// ==============================|| MOCK SERVICES ||============================== //

mock.onGet('/api/contact/list').reply(() => {
  return [200, contacts];
});
mock.onPost('/api/contact/modify').reply((config) => {
  try {
    const user = JSON.parse(config.data);
    if (user.id) {
      const index = contacts.findIndex((u) => u.id === user.id);
      if (index > -1) {
        contacts[index] = { ...contacts[index], ...user };
      }
    } else {
      contacts.push({ ...user, id: contacts.length + 1 });
    }
    return [200, contacts];
  } catch (err) {
    return [500, { message: 'Internal server error' }];
  }
});

mock.onPost('/api/contact/add').reply((config) => {
  try {
    const user = JSON.parse(config.data);
    if (!user.id) {
      contacts.push({ ...user, id: contacts.length + 1 });
    }
    return [200, contacts];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});

export default contacts;
