import { UNAUTHENTICATED, AUTHENTICATED, AUTHENTICATION_ERROR } from "../actions/admin-actions";

export default function adminsReducer(state = [], { type, payload }) {
  switch (type) {
    case AUTHENTICATED:
      return payload.admin;
    case UNAUTHENTICATED:
      return payload;
    case AUTHENTICATION_ERROR:
      return payload;
    default:
      return state;
  }
}
