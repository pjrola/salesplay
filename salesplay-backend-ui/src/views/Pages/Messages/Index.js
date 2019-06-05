import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import { compose } from 'redux';
import { Button } from "reactstrap";
import { Link } from "react-router-dom";
import { deleteMessages, fetchMessages } from "../../../actions/messages-actions";
import CrudTable from "../../../components/Messages/List/Table";

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
    this.onDelete = this.onDelete.bind(this);
    this.props.fetchMessages();
  }

  onDelete(items) {
    this.props.deleteMessages(items);
  }

  render() {
    const { error, loading, messages, t } = this.props;

    if (error) {
      return <>
        <Link to="/content/message/create">
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
        <Link to="/content/message/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;{t('pages.guide.operations.add')}
          </Button>
        </Link>
        <CrudTable items={messages} onDelete={this.onDelete}/>
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
