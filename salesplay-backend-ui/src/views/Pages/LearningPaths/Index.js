import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import { Button } from "reactstrap";
import { Link } from "react-router-dom";

const mapStateToProps = state => ({
});

const mapActionsToProps  = {
};

class Index extends Component {

  componentDidMount() {
    this.onDelete = this.onDelete.bind(this);
  }

  onDelete(items) {
  }

  render() {
    const { error, loading, t } = this.props;

    if (error) {
      return <>
        <Link to="/resources/paths/create">
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
        <Link to="/resources/paths/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.locale.operations.add')}
          </Button>
        </Link>
        Learning Path Table
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
