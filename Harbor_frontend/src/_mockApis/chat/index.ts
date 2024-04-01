import mock from '../mockAdapter';
import user1 from '../../assets/images/users/avatar-1.png';
import user2 from '../../assets/images/users/avatar-2.png';
import user3 from '../../assets/images/users/avatar-3.png';
import user4 from '../../assets/images/users/avatar-4.png';
import user5 from '../../assets/images/users/avatar-5.png';
import type { ChatTypes } from '@/types/chats/ChatTypes';

const ChatData: ChatTypes[] = [
  {
    id: 1,
    name: 'Alena',
    status: 'online',
    thumb: user1,
    recent: false,
    lastMessage: '2h ago',
    excerpt: 'Theme Developer',
    unReadChatCount: 2,
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 2,
    name: 'Maria',
    status: 'away',
    thumb: user2,
    recent: true,
    lastMessage: '20 AM',
    excerpt: 'Doctor',
    unReadChatCount: 3,
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 3,
    name: 'David Smith',
    status: 'busy',
    thumb: user3,
    recent: false,
    lastMessage: 'Yesterday',
    excerpt: 'Hacker',
    unReadChatCount: 1,
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 4,
    name: 'Rodriguez',
    status: 'offline',
    thumb: user4,
    recent: true,
    lastMessage: '4/25/2021',
    excerpt: 'Accountant',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 5,
    name: 'Robert Smith',
    status: 'online',
    thumb: user5,
    recent: true,
    lastMessage: '4/25/2021',
    excerpt: 'Developer',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 6,
    name: 'Joseph Sarah',
    status: 'busy',
    thumb: user1,
    recent: false,
    lastMessage: '4/25/2021',
    excerpt: 'Graphics Designer',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 7,
    name: 'Thomas Smith',
    status: 'away',
    thumb: user2,
    recent: true,
    lastMessage: '4/25/2021',
    excerpt: 'Back End Developer',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 8,
    name: 'David Elizabeth',
    status: 'offline',
    thumb: user3,
    recent: false,
    lastMessage: '4/25/2021',
    excerpt: 'Theme Developer',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 9,
    name: 'Charles Martha',
    status: 'online',
    thumb: user4,
    recent: false,
    lastMessage: '4/25/2021',
    excerpt: 'Administrator',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 10,
    name: 'Samuel Eliza',
    status: 'online',
    thumb: user5,
    recent: false,
    lastMessage: '4/25/2021',
    excerpt: 'Doctor',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 11,
    name: 'Alena',
    status: 'online',
    thumb: user1,
    recent: false,
    lastMessage: '2h ago',
    excerpt: 'Theme Developer',

    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 12,
    name: 'Maria',
    status: 'away',
    thumb: user2,
    recent: true,
    lastMessage: 'to:20 AM',
    excerpt: 'Doctor',

    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 13,
    name: 'David Smith',
    status: 'busy',
    thumb: user3,
    recent: false,
    lastMessage: 'Yesterday',
    excerpt: 'Hacker',

    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  },
  {
    id: 14,
    name: 'Rodriguez',
    status: 'offline',
    thumb: user4,
    recent: true,
    lastMessage: '4/25/2021',
    excerpt: 'Accountant',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['The sky is clear', 'How was the math test?'],
          to: ['She always speaks to him in a loud voice.']
        },
        to: {
          id: 1,
          from: [
            'The memory we used to share is no longer coherent.',
            'The mysterious diary records the voice.',
            'The old apple revels in its authority.'
          ],
          to: ['Please wait outside of the house.']
        }
      }
    ]
  },
  {
    id: 15,
    name: 'Robert Smith',
    status: 'online',
    thumb: user5,
    recent: true,
    lastMessage: '4/25/2021',
    excerpt: 'Developer',
    chatHistory: [
      {
        from: {
          id: 0,
          from: ['Tom got a small piece of pie.'],
          to: ["I'd rather be a bird than a fish.", 'They got there early, and they got really good seats.']
        },
        to: {
          id: 1,
          from: ['If I don’t like something, I’ll stay away from it.', 'I want more detailed information.'],
          to: ['We need to rent a room for our party.']
        }
      }
    ]
  }
];

mock.onGet('/api/data/chat/ChatData').reply(() => {
  return [200, ChatData];
});

export default ChatData;
