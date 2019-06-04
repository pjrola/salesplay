import {
  API_REQUEST_BEGIN,
  FETCH_MESSAGES_SUCCESS,
  API_ERROR,
  POST_MESSAGES_SUCCESS,
  DELETE_MESSAGES_SUCCESS,
  UPDATE_MESSAGES_SUCCESS,
  FIND_MESSAGE_SUCCESS
} from '../actions/messages-actions';

const initialState = {
  items: [],
  loading: false,
  error: null
};

function filterMessage(value, messages) {
  return !messages.includes(value);
}

export default function messagesReducer(state = initialState, action) {
  switch(action.type) {
    case API_REQUEST_BEGIN:
      return {
        ...state,
        loading: true,
        error: null
      };
    case FETCH_MESSAGES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.messages
      };
    case FIND_MESSAGE_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.messages
      };
    case API_ERROR:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        items: []
      };
    case POST_MESSAGES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.messages
      };
    case DELETE_MESSAGES_SUCCESS:
      let currentItems = state.items;
      let messagesToDelete = action.payload.messages[0];
      let filteredItems = currentItems.filter((messages) => filterMessage(messages.id, messagesToDelete));
      return {
        ...state,
        loading: false,
        items: filteredItems
      };
    case UPDATE_MESSAGES_SUCCESS:
      return {
        ...state,
        loading: false,
        items: action.payload.messages
      };

    default:
      return state;
  }
}
