// project imports
import mock from '../mockAdapter';
import user1 from '../../assets/images/users/avatar-1.png';
import user2 from '../../assets/images/users/avatar-2.png';
import user3 from '../../assets/images/users/avatar-3.png';
import user4 from '../../assets/images/users/avatar-4.png';
import user5 from '../../assets/images/users/avatar-5.png';
import user6 from '../../assets/images/users/avatar-6.png';
import user7 from '../../assets/images/users/avatar-7.png';

import profile1 from '../../assets/images/profile/profile-back-1.png';
import profile2 from '../../assets/images/profile/profile-back-2.png';
import profile3 from '../../assets/images/profile/profile-back-3.png';
import profile4 from '../../assets/images/profile/profile-back-4.png';
import profile5 from '../../assets/images/profile/profile-back-5.png';
import profile6 from '../../assets/images/profile/profile-back-6.png';
import profile7 from '../../assets/images/profile/profile-back-7.png';
import profile8 from '../../assets/images/profile/profile-back-8.png';
import profile9 from '../../assets/images/profile/profile-back-9.png';
import profile10 from '../../assets/images/profile/profile-back-10.png';
import profile11 from '../../assets/images/profile/profile-back-11.png';
import profile12 from '../../assets/images/profile/profile-back-12.png';

// types
type KeyedObject = {
  [key: string]: string | number | KeyedObject | boolean;
};

// user list
const users: KeyedObject[] = [
  {
    id: '#1Card_Phoebe',
    avatar: user1,
    name: 'Phoebe',
    role: 'Dynamic Operations Officer',
    about: 'Use the neural RSS application, then you can program the bluetooth firewall! #DOO',
    email: 'claudia_kuhn@yahoo.com',
    contact: '380-293-0177',
    location: 'Port Narcos',
    profile: profile1,
    status: true
  },
  {
    id: '#2Card_Gaetano',
    avatar: user2,
    name: 'Gaetano',
    role: 'Investor Division Strategist',
    about: 'Try to connect the SAS transmitter, maybe it will index the optical hard drive!',
    email: 'alia_shields25@yahoo.com',
    contact: '253-418-5940',
    location: 'Herminahaven',
    profile: profile2,
    status: false
  },
  {
    id: '#3Card_Elisabeth',
    avatar: user3,
    name: 'Elisabeth',
    role: 'Future Markets Associate',
    about: 'If we calculate the monitor, we can get to the CSS sensor through the open-source AGP application!',
    email: 'kieran.mertz87@hotmail.com',
    contact: '283-029-1364',
    location: 'Kihnland',
    profile: profile3,
    status: true
  },
  {
    id: '#4Card_Rosalia',
    avatar: user4,
    name: 'Rosalia',
    role: 'Global Brand Planner',
    about: 'The SCSI program is down, override the cross-platform sensor so we can quantify the SAS firewall!',
    email: 'luis.nader30@hotmail.com',
    contact: '972-477-5225',
    location: 'Collinsborough',
    profile: profile4,
    status: false
  },
  {
    id: '#5Card_Lizeth',
    avatar: user5,
    name: 'Lizeth',
    role: 'District Intranet Executive',
    about: 'The TCP hard drive is down, navigate the redundant driver so we can connect the SMS sensor!',
    email: 'alicia.ohara@company.com',
    contact: '474-215-1871',
    location: 'Alizaville',
    profile: profile5,
    status: true
  },
  {
    id: '#6Card_Jessyca',
    avatar: user6,
    name: 'Jessyca',
    role: 'Future Accountability Liaison',
    about: "You can't program the protocol without parsing the 1080p PNG bandwidth!",
    email: 'titus.kunde76@hotmail.com',
    contact: '235-802-6863',
    location: 'Brandonville',
    profile: profile6,
    status: false
  },
  {
    id: '#7Card_Roberto',
    avatar: user7,
    name: 'Roberto',
    role: 'Product Communications Analyst',
    about: 'Use the solid state JBOD application, then you can generate the multi-byte pixel!',
    email: 'keira61@hotmail.com',
    contact: '712-162-3638',
    location: 'Manuelastad',
    profile: profile7,
    status: true
  },
  {
    id: '#8Card_Ava',
    avatar: user2,
    name: 'Ava',
    role: 'Forward Accounts Assistant',
    about: 'Use the 1080p HDD circuit, then you can calculate the open-source interface!',
    email: 'jailyn_wilderman@company.com',
    contact: '192-612-2096',
    location: 'Lake Keenan',
    profile: profile8,
    status: false
  },
  {
    id: '#9Card_Dillon',
    avatar: user4,
    name: 'Dillon',
    role: 'Future Factors Strategist',
    about: 'The PNG alarm is down, navigate the back-end application so we can generate the FTP driver!',
    email: 'caesar80@yahoo.com',
    contact: '798-877-8614',
    location: 'Russelfurt',
    profile: profile9,
    status: true
  },
  {
    id: '#10Card_Domingo',
    avatar: user7,
    name: 'Domingo',
    role: 'Investor Identity Facilitator',
    about: 'Use the neural XSS firewall, then you can generate the cross-platform capacitor!',
    email: 'hudson.welch54@company.com',
    contact: '642-979-7584',
    location: 'Itzelview',
    profile: profile10,
    status: true
  },
  {
    id: '#11Card_Connor',
    avatar: user1,
    name: 'Connor',
    role: 'Dynamic Intranet Strategist',
    about: 'The CSS program is down, copy the mobile capacitor so we can override the AI alarm!',
    email: 'reese_daniel54@yahoo.com',
    contact: '838-147-6612',
    location: 'Margarettstad',
    profile: profile11,
    status: false
  },
  {
    id: '#12Card_Delfina',
    avatar: user5,
    name: 'Delfina',
    role: 'Chief Response Orchestrator',
    about: "Parsing the monitor won't do anything, we need to navigate the back-end JBOD bus!",
    email: 'stevie.corwin25@company.com',
    contact: '777-709-1293',
    location: 'North Monty',
    profile: profile12,
    status: true
  }
];

// ==============================|| MOCK SERVICES ||============================== //

mock.onGet('/api/details-card/list').reply(() => {
  return [200, users];
});

mock.onPost('/api/details-card/filter').reply((config) => {
  try {
    const { key } = JSON.parse(config.data);

    const results = users.filter((row: KeyedObject) => {
      let matches = true;

      const properties: string[] = ['name', 'role', 'about', 'email', 'contact', 'location'];
      let containsQuery = false;

      properties.forEach((property) => {
        if (row[property].toString().toLowerCase().includes(key.toString().toLowerCase())) {
          containsQuery = true;
        }
      });

      if (!containsQuery) {
        matches = false;
      }
      return matches;
    });

    return [200, { results }];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});
