import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import { compose } from 'redux';
import { createMessage } from "../../../actions/messages-actions";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";
import MessageForm from "../../../components/Messages/Form/Index"

const mapStateToProps = state => ({
  message: state.messages.items,
  loading: state.messages.loading,
  error: state.messages.error
});

const mapActionsToProps  = {
  createMessage: createMessage
};

class Create extends Component {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);

  }

  onSubmit = (values, { setSubmitting, setErrors }) => {
    console.log(values);
    this.props.createMessage(values, this.props.history);
    setSubmitting(false);
  };

  render() {
    const { t, error, loading } = this.props;

    if (error) {
      return <Error error={error}/>
    } else if (loading) {
      return <Loader/>
    }
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" md="12" lg="12">
            <MessageForm onSubmit={this.onSubmit}/>
          </Col>
        </Row>
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
)(Create);
