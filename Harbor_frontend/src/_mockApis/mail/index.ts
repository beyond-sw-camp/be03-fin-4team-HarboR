// third-party
import { sub } from 'date-fns';
import { Chance } from 'chance';

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
import attach1 from '../../assets/images/profile/img-gal-1.png';
import attach2 from '../../assets/images/profile/img-gal-2.png';
import attach3 from '../../assets/images/profile/img-gal-3.png';
// types
import type { MailProps } from '@/types/mail';

const chance = new Chance();

// mail conversation
let mails: MailProps[] = [
  {
    id: '#2Mail_Phoebe',
    subject: chance.sentence({ words: 10 }),
    isRead: false,
    isChecked: false,
    important: true,
    starred: false,
    time: sub(new Date(), { days: 0, hours: 1, minutes: 45 }),
    promotions: false,
    forums: false,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user3,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user4,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.paragraph({ sentences: 1 })
    },
    message: chance.paragraph(),
    attachments: []
  },
  {
    id: '#1Mail_Phoebe',
    subject: chance.sentence({ words: 8 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: true,
    time: sub(new Date(), { days: 0, hours: 5, minutes: 45 }),
    promotions: true,
    forums: true,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      },
      {
        id: '#3Attach',
        image: attach3,
        title: 'granite_cheese.wav'
      }
    ]
  },
  {
    id: '#3Mail_Phoebe',
    subject: chance.sentence({ words: 5 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: false,
    time: sub(new Date(), { days: 1, hours: 1, minutes: 0 }),
    promotions: false,
    forums: false,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user5,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user6,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: []
  },
  {
    id: '#4Mail_Phoebe',
    subject: chance.sentence({ words: 12 }),
    isRead: false,
    isChecked: false,
    important: true,
    starred: false,
    time: sub(new Date(), { days: 2, hours: 8, minutes: 15 }),
    promotions: true,
    forums: false,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user7,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user8,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      },
      {
        id: '#3Attach',
        image: attach3,
        title: 'granite_cheese.wav'
      }
    ]
  },
  {
    id: '#5Mail_Phoebe',
    subject: chance.sentence({ words: 8 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: true,
    time: sub(new Date(), { days: 6, hours: 12, minutes: 55 }),
    promotions: false,
    forums: true,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#3Attach',
        image: attach3,
        title: 'granite_cheese.wav'
      }
    ]
  },
  {
    id: '#6Mail_Phoebe',
    subject: chance.sentence({ words: 10 }),
    isRead: true,
    isChecked: false,
    important: true,
    starred: true,
    time: sub(new Date(), { days: 8, hours: 6, minutes: 20 }),
    promotions: false,
    forums: false,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user3,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' })
    },
    sender: {
      avatar: user5,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: chance.email({ domain: 'company.com' }),
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: []
  },
  {
    id: '#7Mail_Phoebe',
    subject: chance.sentence({ words: 3 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: true,
    time: sub(new Date(), { days: 10, hours: 8, minutes: 5 }),
    promotions: false,
    forums: true,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      }
    ]
  },
  {
    id: '#8Mail_Phoebe',
    subject: chance.sentence({ words: 6 }),
    isRead: false,
    isChecked: false,
    important: false,
    starred: false,
    time: sub(new Date(), { days: 12, hours: 12, minutes: 5 }),
    promotions: true,
    forums: false,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user5,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: []
  },
  {
    id: '#9Mail_Phoebe',
    subject: chance.sentence({ words: 10 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: false,
    time: sub(new Date(), { days: 13, hours: 12, minutes: 45 }),
    promotions: false,
    forums: false,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      }
    ]
  },
  {
    id: '#10Mail_Phoebe',
    subject: chance.sentence({ words: 5 }),
    isRead: true,
    isChecked: false,
    important: true,
    starred: true,
    time: sub(new Date(), { days: 14, hours: 1, minutes: 5 }),
    promotions: true,
    forums: true,
    attach: true,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user6,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      }
    ]
  },
  {
    id: '#11Mail_Phoebe',
    subject: chance.sentence({ words: 7 }),
    isRead: true,
    isChecked: false,
    important: false,
    starred: false,
    time: sub(new Date(), { days: 14, hours: 11, minutes: 45 }),
    promotions: false,
    forums: false,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: [
      {
        id: '#1Attach',
        image: attach1,
        title: '1080p_table_denar.pdf'
      },
      {
        id: '#2Attach',
        image: attach2,
        title: 'handmade.mp2'
      }
    ]
  },
  {
    id: '#12Mail_Phoebe',
    subject: chance.sentence({ words: 10 }),
    isRead: false,
    isChecked: false,
    important: false,
    starred: false,
    time: sub(new Date(), { days: 15, hours: 12, minutes: 5 }),
    promotions: true,
    forums: true,
    attach: false,
    sent: chance.bool(),
    draft: chance.bool(),
    spam: false,
    trash: chance.bool(),
    profile: {
      avatar: user2,
      name: chance.name({ nationality: 'en' }),
      email: 'guiseppe.thea@company.com',
      to: 'carmel.pamela@company.com'
    },
    sender: {
      avatar: user1,
      name: chance.name({ nationality: 'en' }),
      email: chance.email({ domain: 'company.com' }),
      to: 'guiseppe.thea@company.com',
      about: chance.sentence({ words: 8 })
    },
    message: chance.paragraph(),
    attachments: []
  }
];

// filter functions
const getInboxMails = () => mails.filter((item) => !item.spam);
const getSentMails = () => mails.filter((item) => item.sent);
const getDraftMails = () => mails.filter((item) => item.draft);
const getSpamMails = () => mails.filter((item) => item.spam);
const getTrashMails = () => mails.filter((item) => item.trash);
const getStarredMails = () => mails.filter((item) => item.starred);
const getImportantMails = () => mails.filter((item) => item.important);
const getPromotionsMails = () => mails.filter((item) => item.promotions);
const getForumMails = () => mails.filter((item) => item.forums);

// ==============================|| MOCK SERVICES ||============================== //

mock.onGet('/api/mails/list').reply(() => [200, { mails }]);

mock.onPost('/api/mails/setRead').reply((config) => {
  try {
    const { id } = JSON.parse(config.data);
    const mailIndex = mails.findIndex((i) => i.id === id);
    mails[mailIndex] = { ...mails[mailIndex], isRead: true };
    mails = [...mails];
    return [200, []];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});

mock.onPost('/api/mails/setImportant').reply((config) => {
  try {
    const { id } = JSON.parse(config.data);
    const mailIndex = mails.findIndex((i) => i.id === id);
    mails[mailIndex] = { ...mails[mailIndex], important: !mails[mailIndex].important };
    mails = [...mails];
    return [200, []];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});

mock.onPost('/api/mails/setStarred').reply((config) => {
  try {
    const { id } = JSON.parse(config.data);
    const mailIndex = mails.findIndex((i) => i.id === id);
    mails[mailIndex] = { ...mails[mailIndex], starred: !mails[mailIndex].starred };
    mails = [...mails];
    return [200, []];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});

mock.onPost('/api/mails/filter').reply((config) => {
  try {
    const { filter } = JSON.parse(config.data);

    let result = [];
    switch (filter) {
      case 'inbox':
        result = getInboxMails();
        break;
      case 'sent':
        result = getSentMails();
        break;
      case 'draft':
        result = getDraftMails();
        break;
      case 'spam':
        result = getSpamMails();
        break;
      case 'trash':
        result = getTrashMails();
        break;
      case 'starred':
        result = getStarredMails();
        break;
      case 'important':
        result = getImportantMails();
        break;
      case 'promotions':
        result = getPromotionsMails();
        break;
      case 'forums':
        result = getForumMails();
        break;
      case 'all':
      default:
        result = mails;
        break;
    }

    return [200, result];
  } catch (err) {
    console.error(err);
    return [500, { message: 'Internal server error' }];
  }
});
