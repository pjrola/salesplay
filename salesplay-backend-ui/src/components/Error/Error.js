import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Col, Row, Button } from "reactstrap";
import * as Sentry from '@sentry/browser';
import Page404 from "../../views/Pages/Page404";

class Error extends Component {

  componentWillMount() {
    Sentry.captureException(this.props.error);
  }

  render() {
    const { error } = this.props;

    if (error !== undefined && error.hasOwnProperty('status') && error.status.toString() === "404") {
      return <Page404 message={error.message}/>
    }

    return (
      <Row>
        <Col xs="12" md="12">
          <div className="text-danger font-weight-bold mt-4 text-center">
            <p>We're sorry - something's gone wrong {error.status}</p>
          </div>
          <div className="text-center">
            <p>Our team has been notified, but click
               <Button color="link" className="report-error" onClick={() => Sentry.lastEventId() && Sentry.showReportDialog()}>here</Button>to fill out a report.
            </p>
          </div>
        </Col>
      </Row>
    );
  }
}

export default withTranslation()(Error);
