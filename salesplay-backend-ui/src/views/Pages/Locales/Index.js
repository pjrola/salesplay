import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import {Button} from "reactstrap";
import { Link } from "react-router-dom";

class Index extends Component {

  constructor(props) {
    super(props);
  }

  componentDidMount() {

  }

  render() {
    const { error, loading, guides, t } = this.props;

    if (error) {
      return <>
        <Link to="/content/guide/create">
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
        Locales
      </div>
    );
  }
}

export default compose(
  withTranslation()
)(Index);
