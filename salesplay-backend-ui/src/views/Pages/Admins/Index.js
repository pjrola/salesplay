import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import {Button} from "reactstrap";
import { Link } from "react-router-dom";

const mapStateToProps = state => ({
});

const mapActionsToProps  = {
};

class Index extends Component {

  constructor(props) {
    super(props);
  }

  componentDidMount() {
  }

  render() {
    const { error, loading, t } = this.props;

    if (error) {
      return <>
        <Link to="/accounts/administrators/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.guide.operations.add')}
          </Button>
        </Link>
        <Error error={error}/>
        </>
    } else if (loading) {
        return <Loader/>
    }

    return (
      <div className="animated fadeIn">
        <Link to="/accounts/administrators/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.guide.operations.add')}
          </Button>
        </Link>
        Admins Table
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
