import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.scss';
import { I18nextProvider } from 'react-i18next';
import i18n from './i18n/i18n';
import Login from '../src/views/Pages/Login';
import Page404 from '../src/views/Pages/Page404/Page404';
import Page500 from '../src/views/Pages/Page500/Page500';
import DefaultLayout from '../src/containers/DefaultLayout/DefaultLayout';
import  { PrivateRoute } from './components/Routes/PrivateRoute';

class App extends Component {

  render() {
    return (
      <I18nextProvider i18n={i18n}>
        <BrowserRouter>
            <Switch>
              <Route exact path="/login" name="Login Page" component={Login} />
              <Route exact path="/404" name="Page 404" component={Page404} />
              <Route exact path="/500" name="Page 500" component={Page500} />
              <PrivateRoute path="/" name="Home" component={DefaultLayout} />
            </Switch>
        </BrowserRouter>
      </I18nextProvider>
    );
  }
}

export default App;
