import React from 'react';
import DefaultLayout from './containers/DefaultLayout';

const Dashboard = React.lazy(() => import('./views/Pages/Dashboard'));
const GuidePage = React.lazy(() => import('./views/Pages/Guides/Index'));
const Guide = React.lazy(() => import('./views/Pages/Guides/Create'));
const Locale = React.lazy(() => import('./views/Pages/Locales/Create'));
const GuideDetailsPage = React.lazy(() => import('./views/Pages/Guides/Update'));
const GuideGroupPage = React.lazy(() => import('./views/Pages/Guides/Groups/Index'));
const Webinar = React.lazy(() => import('./views/Pages/Webinars/Webinar'));
const TagList = React.lazy(() => import('./views/Pages/Tags/TagList'));
const Tag = React.lazy(() => import('./views/Pages/Tags/Tag'));
const ContentAssetList = React.lazy(() => import('./views/Pages/ContentAssets/ContentAssetList'));
const ContentAsset = React.lazy(() => import('./views/Pages/ContentAssets/ContentAsset'));
const ContentSlotList = React.lazy(() => import('./views/Pages/ContentSlots/ContentSlotList'));
const ContentSlot = React.lazy(() => import('./views/Pages/ContentSlots/ContentSlot'));
const LocalePage = React.lazy(() => import('./views/Pages/Locales/Index'));
const MessagePage = React.lazy(() => import('./views/Pages/Messages/Index'));

const routes = [
  { path: '/', exact: true, name: 'Home', component: DefaultLayout },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard },
  { path: '/content/guide/list', name: 'GuidePage', component: GuidePage },
  { path: '/content/guide/create', name: 'Guide', component: Guide },
  { path: '/content/guide/update/:id', exact: true, name: 'Guide Details', component: GuideDetailsPage },
  { path: '/content/guide/groups/list', name: 'Guide Groups', component: GuideGroupPage },
  { path: '/content/locales/list', name: 'Locales', component: LocalePage },
  { path: '/content/locales/create', name: 'Locales', component: Locale },
  { path: '/content/messages/list', name: 'Messages', component: MessagePage },
  { path: '/content/webinar', name: 'Webinar', component: Webinar },
  { path: '/content/tags', name: 'Tag', component: TagList },
  { path: '/content/tag', name: 'Tag', component: Tag },
  { path: '/marketing/assets', name: 'Content Asset List', component: ContentAssetList },
  { path: '/marketing/asset', name: 'Content Asset', component: ContentAsset },
  { path: '/marketing/slots', name: 'Content Slots List', component: ContentSlotList },
  { path: '/marketing/slot', name: 'Content Slot', component: ContentSlot }
];

export default routes;
