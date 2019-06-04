import {
  API_REQUEST_BEGIN,
  FETCH_GUIDES_SUCCESS,
  API_ERROR,
  POST_GUIDES_SUCCESS,
  DELETE_GUIDES_SUCCESS,
  UPDATE_GUIDES_SUCCESS,
  FIND_GUIDE_SUCCESS
} from '../actions/guides-actions';

const initialState = {
  items: [],
  loading: false,
  error: null
};

function filterGuide(value, guides) {
  return !guides.includes(value);
}

export default function guidesReducer(state = initialState, action) {
  switch(action.type) {
    case API_REQUEST_BEGIN:
      return {
        ...state,
        loading: true,
        error: null
      };
    case FETCH_GUIDES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.guides
      };
    case FIND_GUIDE_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.guides
      };
    case API_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        items: []
      };
    case POST_GUIDES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.guides
      };
    case DELETE_GUIDES_SUCCESS:
      let currentItems = state.items;
      let guidesToDelete = action.payload.guides[0];
      let filteredItems = currentItems.filter((guides) => filterGuide(guides.id, guidesToDelete));
      return {
        ...state,
        loading: false,
        items: filteredItems
      };
    case UPDATE_GUIDES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.guides
      };

    default:
      return state;
  }
}
