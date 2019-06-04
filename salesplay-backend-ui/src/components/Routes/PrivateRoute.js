import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import AuthService from '../../services/auth';
const auth = new AuthService();

export const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route {...rest} render={(props) => (
    auth.loggedIn() === true
      ? <Component {...props} />
      : <Redirect to={{
        pathname: '/login',
        state: { from: props.location }
      }} />
  )} />
);
