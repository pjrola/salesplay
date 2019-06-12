import React, { Component } from 'react';

import { AppSidebarNav } from '@coreui/react';
import {withTranslation} from "react-i18next";

class SideNav extends Component {

  render() {
    const { t } = this.props;

    const navConfig = {
      items: [
        {
          name: t('layout.sidebar.dashboard'),
          url: '/dashboard',
          icon: 'fa fa-dashboard',
        },
        {
          title: true,
          name: 'Cms'
        },
        {
          name: t('layout.sidebar.resources'),
          icon: 'fa fa-book',
          children: [
            {
              name: t('layout.sidebar.guides'),
              url: '/resources/guide/list',
              icon: 'fa fa-leaf',
            },
            {
              name: 'Guide Groups',
              url: '/resources/guide/groups/list',
              icon: 'fa fa-leaf'
            },
            {
              name: 'Learning Paths',
              url: '/resources/paths/list',
              icon: 'fa fa-play-circle'
            },
            {
              name: t('layout.sidebar.webinars'),
              url: '/resources/webinars',
              icon: 'fa fa-play-circle'
            },
            {
              name: t('layout.sidebar.tags'),
              url: '/resources/tags',
              icon: 'fa fa-tags',
            },
          ]

        },
        {
          name: t('layout.sidebar.marketing'),
          icon: 'fa fa-globe',
          children: [
            {
              name: t('layout.sidebar.content_slots'),
              url: '/marketing/slots',
              icon: 'fa fa-drivers-license-o',
            },
            {
              name: t('layout.sidebar.content_assets'),
              url: '/marketing/assets',
              icon: 'fa fa-cubes',
            },
          ]

        },
        {
          name: t('layout.sidebar.media'),
          url: '/test',
          icon: 'fa fa-film',
        },
        {
          name: t('layout.sidebar.downloads'),
          icon: 'fa fa-cloud-download',
          children: [
            {
              name: t('layout.sidebar.trial_editions'),
              url: '/test',
              icon: 'fa fa-drivers-license-o',
            },
            {
              name: t('layout.sidebar.trial_downloads'),
              url: '/test',
              icon: 'fa fa-download',
            },
          ]
        },
        {
          title: true,
          name: t('layout.sidebar.manage')
        },
        {
          name: t('layout.sidebar.accounts'),
          icon: 'fa fa-users',
          children: [
            {
              name: t('layout.sidebar.administrators'),
              url: '/accounts/administrators/list',
              icon: 'fa fa-shield',
            },
            {
              name: t('layout.sidebar.users'),
              url: '/accounts/users',
              icon: 'fa fa-users',
            },
            {
              name: t('layout.sidebar.roles'),
              url: '/accounts/roles',
              icon: 'fa fa-leaf',
            },
            {
              name: t('layout.sidebar.permissions'),
              url: '/accounts/permissions',
              icon: 'fa fa-cubes',
            }
          ],
        },
        {
          name: t('layout.sidebar.system'),
          icon: 'fa fa-cogs',
          children: [
            {
              name: t('layout.sidebar.settings'),
              url: '/base/breadcrumbs',
              icon: 'fa fa-cogs',
            },
            {
              name: t('layout.sidebar.locations'),
              url: '/widgets',
              icon: 'fa fa-globe',
            },
            {
              name: t('layout.sidebar.health'),
              url: '/widgets',
              icon: 'fa fa-heartbeat',
            },
          ],
        },
        {
          name: t('layout.sidebar.cloud'),
          icon: 'fa fa-cloud-upload',
          children: [
            {
              name: t('layout.sidebar.instances'),
              url: '/buttons/button-dropdowns',
              icon: 'fa fa-server',
            },
            {
              name: t('layout.sidebar.templates'),
              url: '/buttons/button-groups',
              icon: 'fa fa-code-fork',
            }
          ],
        },
        {
          name: t('layout.sidebar.analytics'),
          icon: 'fa fa-line-chart',
          children: [
            {
              name: t('layout.sidebar.reports'),
              url: '/buttons/button-dropdowns',
              icon: 'fa fa-server',
            },
            {
              name: 'GA',
              url: '/buttons/button-groups',
              icon: 'fa fa-code-fork',
            }
          ],
        },
        {
          name: t('layout.sidebar.localization'),
          icon: 'fa fa-language',
          children: [
            {
              name: t('layout.sidebar.locales'),
              url: '/resources/locales/list',
              icon: 'fa fa-globe',
            },
            {
              name: t('layout.sidebar.messaging'),
              url: '/resources/messages/list',
              icon: 'fa fa-language',
            }
          ],
        },
        {
          name: t('layout.sidebar.notifications'),
          icon: 'fa fa-bell-o',
          children: [
            {
              name: t('layout.sidebar.alerts'),
              url: '/notifications/alerts',
              icon: 'icon-bell',
            }
          ],
        },
      ],
    };
    return (
      <AppSidebarNav navConfig={navConfig} {...this.props} />
    );
  }
}

export default withTranslation()(SideNav);
