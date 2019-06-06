import {
  API_REQUEST_BEGIN,
  FETCH_LOCALES_SUCCESS,
  API_ERROR,
  POST_LOCALES_SUCCESS,
  DELETE_LOCALES_SUCCESS,
  UPDATE_LOCALES_SUCCESS,
  FIND_LOCALE_SUCCESS,
  FIND_ENABLED_LOCALES_SUCCESS
} from '../actions/locales-actions';

const initialState = {
  items: [],
  loading: false,
  error: null
};

function filterLocale(value, locales) {
  return !locales.includes(value);
}

export default function localesReducer(state = initialState, action) {
  switch(action.type) {
    case API_REQUEST_BEGIN:
      return {
        ...state,
        loading: true,
        error: null
      };
    case FETCH_LOCALES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.locales
      };
    case FIND_LOCALE_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.locales
      };
    case FIND_ENABLED_LOCALES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.locales
      };
    case API_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        items: []
      };
    case POST_LOCALES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.locales
      };
    case DELETE_LOCALES_SUCCESS:
      let currentItems = state.items;
      let localesToDelete = action.payload.locales[0];
      let filteredItems = currentItems.filter((locales) => filterLocale(locales.id, localesToDelete));
      return {
        ...state,
        loading: false,
        items: filteredItems
      };
    case UPDATE_LOCALES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.locales
      };

    default:
      return state;
  }
}
