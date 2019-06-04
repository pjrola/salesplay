import React, { Component } from 'react';
import GuideTable from "../../../components/Guides/List/Table";
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import { deleteGuides, fetchGuides } from "../../../actions/guides-actions";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import {Button} from "reactstrap";
import { Link } from "react-router-dom";

const mapStateToProps = state => ({
  guides: state.guides.items,
  loading: state.guides.loading,
  error: state.guides.error
});

const mapActionsToProps  = {
  fetchGuides: fetchGuides,
  deleteGuides: deleteGuides
};

class Index extends Component {

  constructor(props) {
    super(props);
    this.onDelete = this.onDelete.bind(this);
  }

  componentDidMount() {
    this.props.fetchGuides();
  }

  onDelete(guides) {
    this.props.deleteGuides(guides);
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
        <Link to="/content/guide/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.guide.operations.add')}
          </Button>
        </Link>
        <GuideTable items={guides} onDelete={this.onDelete}/>
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
