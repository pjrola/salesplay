import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { connect } from "react-redux";
import { compose } from 'redux';
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";

const mapStateToProps = state => ({

});

const mapActionsToProps  = {
};

class Create extends Component {
  constructor(props) {
    super(props);
  }

  onSubmit = (values, { setSubmitting, setErrors }) => {

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
            Create form Learning Paths
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
