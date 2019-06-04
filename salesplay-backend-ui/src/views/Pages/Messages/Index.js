import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import { Button } from "reactstrap";
import { Link } from "react-router-dom";
import { deleteMessages, fetchMessages } from "../../../actions/messages-actions";

const mapStateToProps = state => ({
  messages: state.messages.items,
  loading: state.messages.loading,
  error: state.messages.error
});

const mapActionsToProps  = {
  fetchMessages: fetchMessages,
  deleteMessages: deleteMessages
};

class Index extends Component {

  componentDidMount() {
    this.props.fetchMessages();
  }

  render() {
    const { error, loading, messages, t } = this.props;

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
        { JSON.stringify(messages) }
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
