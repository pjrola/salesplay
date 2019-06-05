export const API_REQUEST_BEGIN   = 'messages:api_request_begin';
export const FETCH_MESSAGES_SUCCESS = 'messages:fetch_messages_success';
export const POST_MESSAGES_SUCCESS = 'messages:post_message_success';
export const API_ERROR = 'messages:api_error';
export const DELETE_MESSAGES_SUCCESS = 'messages:delete_messages_success';
export const UPDATE_MESSAGES_SUCCESS  = 'messages:update_messages_success';
export const FIND_MESSAGE_SUCCESS = 'messages:find_message_by_id_success';
const serviceDownError = 'Failed to connect to service';

const baseUrl = 'http://localhost:9999/api/v1/messageResources';

export const apiRequestBegin = () => ({
  type: API_REQUEST_BEGIN
});

export const fetchMessagesSuccess = messages => ({
  type: FETCH_MESSAGES_SUCCESS,
  payload: { messages }
});

export const apiRequestFailure = error => ({
  type: API_ERROR,
  payload: { error }
});

export const postMessagesSuccess = messages => ({
  type: POST_MESSAGES_SUCCESS,
  payload: { messages }
});

export const deleteMessagesSuccess = messages => ({
  type: DELETE_MESSAGES_SUCCESS,
  payload: { messages }
});

export const updateMessageSuccess = messages => ({
  type: UPDATE_MESSAGES_SUCCESS,
  payload: { messages }
});

export const findMessageByIdSuccess = messages => ({
  type: FIND_MESSAGE_SUCCESS,
  payload: { messages }
});

export function fetchMessages() {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl)
      .then(handleErrors)
      .then(json => {
        dispatch(fetchMessagesSuccess(json.content));
        return json.content;
      })
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  };
}

export function createMessage(data, history) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl, {
      method: 'POST',
      body: JSON.stringify(data),
      headers:{
        'Content-Type': 'application/json'
      }
    }).then(handleErrors)
      .then(json => {
        dispatch(postMessagesSuccess(json));
        history.push('/content/messages/list');
        return json;
      })
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  }
}

export function updateMessage(data) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl, {
      method: 'PUT',
      body: JSON.stringify(data),
      headers:{
        'Content-Type': 'application/json'
      }
    }).then(handleErrors)
      .then(json => {
        dispatch(updateMessageSuccess(json));
        return json;
      })
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  }
}

export function deleteMessages(messages) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + '/delete', {
      method: 'POST',
      body: JSON.stringify(arrayToObject(messages, 'id')),
      headers:{
        'Content-Type': 'application/json'
      }
    }).then(handleErrors)
      .then(dispatch(deleteMessagesSuccess(messages)))
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  }
}

export function findMessageById(id) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + "/" + id)
      .then(handleErrors)
      .then(json => {
        dispatch(findMessageByIdSuccess(json));
        return json;
      })
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  };
}

function handleErrors(response) {
  return response.text()
    .then((text) => text.length ? JSON.parse(text) : {})
    .then((json) => {
      if (!response.ok) {
        const error = Object.assign({}, json, {
          status: response.status,
          statusText: response.statusText,
        });

        return Promise.reject(error);
      }
      return json;
    });
}

function arrayToObject(array, keyField) {
  let res = [];
  for (const item of array[0]) {
    res.push({[keyField]:item});
  }
  return res;
}

function isServiceDown(error) {
  if (error === 'Failed to fetch') {
    return true;
  }
  return false;
}
