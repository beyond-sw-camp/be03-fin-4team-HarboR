import mock from './mockAdapter';

import './customers';
import './chat';
import './ecommerce';
import './contact';
import './mail';
import './users/cards';
import './users/list';
import './users/posts';
import './users/followers';
import './users/friends';
import './users/gallery';
import './kanban';

mock.onAny().passThrough();
