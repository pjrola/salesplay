import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import { Button } from "reactstrap";
import { Link } from "react-router-dom";
import { deleteLocales, fetchLocales } from "../../../actions/locales-actions";

const mapStateToProps = state => ({
  locales: state.locales.items,
  loading: state.locales.loading,
  error: state.locales.error
});

const mapActionsToProps  = {
  fetchLocales: fetchLocales,
  deleteLocales: deleteLocales
};

class Index extends Component {

  componentDidMount() {
    this.props.fetchLocales();
  }

  render() {
    const { error, loading, locales, t } = this.props;

    if (error) {
      return <>
        <Link to="/content/locales/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.locale.operations.add')}
          </Button>
        </Link>
        <Error error={error}/>
      </>
    } else if (loading) {
      return <Loader/>
    }

    return (
      <div className="animated fadeIn">
        <Link to="/content/locales/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.locale.operations.add')}
          </Button>
        </Link>
        <div>{ JSON.stringify(locales) }</div>
      </div>
    );
  }
}

export default compose(
  connect(
    mapStateToProps,
    mapActionsToProps
  ),
  withTranslation()
)(Index);
