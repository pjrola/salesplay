import jwt_decode from 'jwt-decode';
import AuthService from '../services/auth';
const auth = new AuthService();
export const AUTHENTICATED = 'admins:authenticated';
export const UNAUTHENTICATED = 'admins:unauthenticated';
export const AUTHENTICATION_ERROR = 'admins:authentication_error';

export function login(data, history) {
  return dispatch => {
    fetch('https://reqres.in/api/login', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error(response.status + " " + response.statusText);
      }
    })
      .then(response => {
        const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        const decoded = jwt_decode(token);
        dispatch(successLogin(decoded));

        auth.setToken(token);
        history.push('/dashboard');
      })
      .catch(error => dispatch(showError(error.toString())));
  }
}

export function showError(error) {
  return {
    type: AUTHENTICATION_ERROR,
    payload: {
      admin: {},
      isAuthenticated: false,
      error
    }
  }
}

function successLogin(data) {
  return {
    type: AUTHENTICATED,
    payload: {
      admin: {data},
      isAuthenticated: true
    }
  }
}

export function logout(history) {
  auth.logout();
  history.push('/login');

  return {
    type: UNAUTHENTICATED,
    payload: {
      admin: {},
      isAuthenticated: false
    }
  };
}
