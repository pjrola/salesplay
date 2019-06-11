import i18next from 'i18next';

export const API_REQUEST_BEGIN   = 'guides:api_request_begin';
export const FETCH_GUIDES_SUCCESS = 'guides:fetch_guides_success';
export const POST_GUIDES_SUCCESS = 'guides:post_guide_success';
export const API_ERROR = 'guides:api_error';
export const DELETE_GUIDES_SUCCESS = 'guides:delete_guides_success';
export const UPDATE_GUIDES_SUCCESS  = 'guides:update_guides_success';
export const FIND_GUIDE_SUCCESS = 'guides:find_guide_by_id_success';
const serviceDownError = 'Failed to connect to service';

const baseUrl = 'http://localhost:9999/api/v1/guides' + '?lang=' + i18next.language;

export const apiRequestBegin = () => ({
  type: API_REQUEST_BEGIN
});

export const fetchGuidesSuccess = guides => ({
  type: FETCH_GUIDES_SUCCESS,
  payload: { guides }
});

export const apiRequestFailure = error => ({
  type: API_ERROR,
  payload: { error }
});

export const postGuidesSuccess = guides => ({
  type: POST_GUIDES_SUCCESS,
  payload: { guides }
});

export const deleteGuidesSuccess = guides => ({
  type: DELETE_GUIDES_SUCCESS,
  payload: { guides }
});

export const updateGuideSuccess = guides => ({
  type: UPDATE_GUIDES_SUCCESS,
  payload: { guides }
});

export const findGuideByIdSuccess = guides => ({
  type: FIND_GUIDE_SUCCESS,
  payload: { guides }
});

export function fetchGuides() {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl)
      .then(handleErrors)
      .then(json => {
        dispatch(fetchGuidesSuccess(json.content));
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

export function createGuide(data, history) {
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
        dispatch(postGuidesSuccess(json));
        history.push('/resources/guide/list');
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

export function updateGuide(data) {
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
        dispatch(updateGuideSuccess(json));
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

export function deleteGuides(guides) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + '/delete', {
      method: 'POST',
      body: JSON.stringify(arrayToObject(guides, 'id')),
      headers:{
        'Content-Type': 'application/json'
      }
    }).then(handleErrors)
      .then(dispatch(deleteGuidesSuccess(guides)))
      .catch(error => {
        if (isServiceDown(error.message)) {
          error.status = serviceDownError;
        }
        dispatch(apiRequestFailure(error));
      });
  }
}

export function findGuideById(id) {
  return dispatch => {
    dispatch(apiRequestBegin());
    return fetch(baseUrl + "/" + id)
      .then(handleErrors)
      .then(json => {
        dispatch(findGuideByIdSuccess(json));
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
