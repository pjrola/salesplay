import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';
import { withTranslation } from 'react-i18next';
import {AppSwitch} from "@coreui/react";

class Settings extends Component {

  render() {
    return (
      <Row>
        <Col>
          <div className="aside-options">
            <div className="clearfix mt-2">
              <small className="ml-2"><b>Promoted</b></small>
              <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} defaultChecked size={'sm'}/>
            </div>
            <div className="mt-2">
              <small className="text-muted">Displays the guide in first position on guide grid page</small>
            </div>
          </div>
          <div className="aside-options">
            <div className="clearfix mt-2">
              <small className="ml-2"><b>Redirect after finish</b></small>
              <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} defaultChecked size={'sm'}/>
            </div>
            <div className="mt-2">
              <small className="text-muted">Redirect the user after a guide is finished</small>
            </div>
          </div>
          <div className="aside-options">
            <div className="clearfix mt-2">
              <small className="ml-2"><b>Error Reporting</b></small>
              <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} defaultChecked size={'sm'}/>
            </div>
            <div className="mt-2">
              <small className="text-muted">Allows users to report issues</small>
            </div>
          </div>
          <div className="aside-options">
            <div className="clearfix mt-2">
              <small className="ml-2"><b>Add estimated read time</b></small>
              <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} defaultChecked size={'sm'}/>
            </div>
            <div className="mt-2">
              <small className="text-muted">Adds the estimate read time</small>
            </div>
          </div>
        </Col>
      </Row>
    );
  }
}

export default withTranslation()(Settings);
