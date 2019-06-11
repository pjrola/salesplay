import React from 'react';
import DefaultLayout from './containers/DefaultLayout';

/** Main Dashboard Page ***/
const Dashboard = React.lazy(() => import('./views/Pages/Dashboard'));

/** Guide CRUD Pages ***/
const GuideListPage = React.lazy(() => import('./views/Pages/Guides/Index'));
const GuideCreatePage = React.lazy(() => import('./views/Pages/Guides/Create'));
const GuideUpdatePage = React.lazy(() => import('./views/Pages/Guides/Update'));

/** Admin CRUD Pages ***/
const AdminListPage = React.lazy(() => import('./views/Pages/Admins/Index'));
const AdminCreatePage = React.lazy(() => import('./views/Pages/Admins/Create'));

/** Locale CRUD Pages ***/
const LocaleCreatePage = React.lazy(() => import('./views/Pages/Locales/Create'));
const LocaleListPage = React.lazy(() => import('./views/Pages/Locales/Index'));

/** Message CRUD Pages **/
const MessageListPage = React.lazy(() => import('./views/Pages/Messages/Index'));
const MessageCreatePage = React.lazy(() => import('./views/Pages/Messages/Create'));

/** Guide Slot CRUD Pages **/
const GuideGroupPage = React.lazy(() => import('./views/Pages/Guides/Groups/Index'));

/** Webinar CRUD Pages **/
const WebinarListPage = React.lazy(() => import('./views/Pages/Webinars/Webinar'));

/** Tag CRUD Pages **/
const TagListPage = React.lazy(() => import('./views/Pages/Tags/TagList'));
const Tag = React.lazy(() => import('./views/Pages/Tags/Tag'));

/** ContentAsset CRUD Pages **/
const ContentAssetListPage = React.lazy(() => import('./views/Pages/ContentAssets/ContentAssetList'));
const ContentAssetCreatePage = React.lazy(() => import('./views/Pages/ContentAssets/ContentAsset'));

/** Content Slots CRUD Pages **/
const ContentSlotListPage = React.lazy(() => import('./views/Pages/ContentSlots/ContentSlotList'));
const ContentSlotCreatePage = React.lazy(() => import('./views/Pages/ContentSlots/ContentSlot'));

const routes = [
  { path: '/', exact: true, name: 'Home', component: DefaultLayout },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard },

  { path: '/resources/guide/list', name: 'GuidePage', component: GuideListPage },
  { path: '/resources/guide/create', name: 'Guide', component: GuideCreatePage },
  { path: '/resources/guide/update/:id', exact: true, name: 'Guide Details', component: GuideUpdatePage },
  { path: '/resources/guide/groups/list', name: 'Guide Groups', component: GuideGroupPage },

  { path: '/resources/locales/list', name: 'Locales', component: LocaleListPage },
  { path: '/resources/locales/create', name: 'Locales', component: LocaleCreatePage },

  { path: '/resources/messages/list', name: 'Messages', component: MessageListPage },
  { path: '/resources/message/create', name: 'Messages', component: MessageCreatePage },
  { path: '/resources/webinar', name: 'Webinar', component: WebinarListPage },

  { path: '/resources/tags', name: 'Tag', component: TagListPage },
  { path: '/resources/tag', name: 'Tag', component: Tag },

  { path: '/marketing/assets', name: 'Content Asset List', component: ContentAssetListPage },
  { path: '/marketing/asset', name: 'Content Asset', component: ContentAssetCreatePage },

  { path: '/marketing/slots', name: 'Content Slots List', component: ContentSlotListPage },
  { path: '/marketing/slot', name: 'Content Slot', component: ContentSlotCreatePage },

  { path: '/accounts/administrators/list', name: 'Admins', component: AdminListPage },
  { path: '/accounts/administrators/create', name: 'Admins', component: AdminCreatePage }
];

export default routes;
