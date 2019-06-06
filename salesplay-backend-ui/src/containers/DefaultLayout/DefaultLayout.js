import React, { Component, Suspense } from 'react';
import routes from '../../routes';
import { Container } from 'reactstrap';
import { Redirect, Route, Switch } from 'react-router-dom';
import { withTranslation } from "react-i18next";
import SideNav from "../../SideNav";
import { compose } from "redux";
import { connect } from "react-redux";
import { logout } from "../../actions/admin-actions";
import { findLocaleByEnabled } from "../../actions/locales-actions";
import { clearPreviousErrors } from "../../actions/root-actions";
import AuthService from '../../services/auth';

import {
  AppHeader,
  AppSidebar,
  AppSidebarFooter,
  AppSidebarForm,
  AppSidebarHeader,
  AppSidebarMinimizer,
} from '@coreui/react';

const DefaultHeader = React.lazy(() => import('./DefaultHeader'));
const auth = new AuthService();

const mapStateToProps = state => ({
  locales: state.locales.items,
  loading: state.locales.loading,
  error: state.locales.error
});

const mapActionsToProps  = {
  logout: logout,
  findLocaleByEnabled: findLocaleByEnabled,
  clearPreviousErrors: clearPreviousErrors
};

class DefaultLayout extends Component {

  componentWillMount() {
    this.unlisten = this.props.history.listen((location, action) => {
      this.props.clearPreviousErrors();
    });
    this.props.findLocaleByEnabled();
  }
  componentWillUnmount() {
    this.unlisten();
  }

  loading = () =>
    <div className="animated fadeIn pt-3">
      <div className="loadingBar"/>
    </div>;

  signOut(e) {
    e.preventDefault();
    this.props.logout(this.props.history);
  }

  render() {
    return (
      <div className="app">
        <AppHeader fixed>
          <Suspense  fallback={this.loading()}>
            <DefaultHeader locales={this.props.locales} onLogout={e=>this.signOut(e)} currentUser={auth.getProfile().name}/>
          </Suspense>
        </AppHeader>
        <div className="app-body">
          <AppSidebar display="lg">
            <AppSidebarHeader />
            <AppSidebarForm />
            <Suspense>
              <SideNav {...this.props}/>
            </Suspense>
              <AppSidebarFooter />
            <AppSidebarMinimizer aria-label="sidebar minimize" role="button"/>
          </AppSidebar>
          <main className="main">
            <Container fluid>
              <Suspense fallback={this.loading()}>
                <Switch>
                  {routes.map((route, idx) => {
                    return route.component ? (
                      <Route
                        key={idx}
                        path={route.path}
                        exact={route.exact}
                        name={route.name}
                        render={props => (
                          <route.component {...props} />
                        )} />
                    ) : (null);
                  })}
                  <Redirect from="/" to="/dashboard" />
                </Switch>
              </Suspense>
            </Container>
          </main>
        </div>
      </div>
    );
  }
}

export default compose(
  connect(
    mapStateToProps,
    mapActionsToProps
  ),
  withTranslation()
)(DefaultLayout);
