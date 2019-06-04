import * as Sentry from '@sentry/browser';
import 'react-app-polyfill/ie9';
import 'react-app-polyfill/ie11';
import './polyfill'
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { applyMiddleware, compose ,combineReducers, createStore } from "redux";
import { Provider } from "react-redux";
import guidesReducer from './reducers/guides-reducer';
import localesReducer from './reducers/locales-reducer';
import adminsReducer from "./reducers/admins-reducer";
import messagesReducer from "./reducers/messages-reducer";
import thunk from 'redux-thunk';
import pseudoLocalization from 'pseudo-localization';
import {CLEAR_PREVIOUS_ERROR} from './actions/root-actions'

if (process.env.REACT_APP_SUDO_LOCALIZE === 'true') {
  pseudoLocalization.start();
}

Sentry.init({
  dsn: process.env.REACT_APP_SENTRY_DSN
});

const rootReducer = (state, action) => {
  if (action.type === CLEAR_PREVIOUS_ERROR) {
    state = {
      error: null
    };
  }
  return allReducers(state, action)
};


const allReducers = combineReducers({
  guides: guidesReducer,
  messages: messagesReducer,
  locales: localesReducer,
  admin: adminsReducer
});

const allStoreEnhancers = compose(
  applyMiddleware(thunk),
  (window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__()) || compose
);

const store = createStore(rootReducer,
  {
  },allStoreEnhancers
);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root'));

serviceWorker.unregister();
