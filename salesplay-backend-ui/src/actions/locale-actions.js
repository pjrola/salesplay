export const API_REQUEST_BEGIN   = 'locales:api_request_begin';
export const FETCH_LOCALES_SUCCESS = 'locales:fetch_locales_success';
export const POST_LOCALES_SUCCESS = 'locales:post_guide_success';
export const API_ERROR = 'locales:api_error';
export const DELETE_LOCALES_SUCCESS = 'locales:delete_locales_success';
export const UPDATE_LOCALES_SUCCESS  = 'locales:update_locales_success';
export const FIND_GUIDE_SUCCESS = 'locales:find_guide_by_id_success';
const serviceDownError = 'Failed to connect to service';

const baseUrl = 'http://localhost:9999/api/v1/locales';

export const apiRequestBegin = () => ({
  type: API_REQUEST_BEGIN
});

export const fetchLocalesSuccess = locales => ({
  type: FETCH_LOCALES_SUCCESS,
  payload: { locales }
});

export const apiRequestFailure = error => ({
  type: API_ERROR,
  payload: { error }
});

export const postLocalesSuccess = locales => ({
  type: POST_LOCALES_SUCCESS,
  payload: { locales }
});

export const deleteLocalesSuccess = locales => ({
  type: DELETE_LOCALES_SUCCESS,
  payload: { locales }
});

export const updateLocaleSuccess = locales => ({
  type: UPDATE_LOCALES_SUCCESS,
  payload: { locales }
});

export const findLocaleByIdSuccess = locales => ({
  type: FIND_GUIDE_SUCCESS,
  payload: { locales }
});

export function fetchLocales() {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl)
      .then(handleErrors)
      .then(json => {
        dispatch(fetchLocalesSuccess(json.content));
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

export function createLocale(data, history) {
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
        dispatch(postLocalesSuccess(json));
        history.push('/content/locales/list');
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

export function updateLocale(data) {
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
        dispatch(updateLocaleSuccess(json));
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

export function deleteLocales(locales) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + '/delete', {
      method: 'POST',
      body: JSON.stringify(arrayToObject(locales, 'id')),
      headers:{
        'Content-Type': 'application/json'
      }
    }).then(handleErrors)
      .then(dispatch(deleteLocalesSuccess(locales)))
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  }
}

export function findLocaleById(id) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + "/" + id)
      .then(handleErrors)
      .then(json => {
        dispatch(findLocaleByIdSuccess(json));
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
