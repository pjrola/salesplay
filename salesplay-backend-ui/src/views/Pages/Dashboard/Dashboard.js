import React, { Component } from 'react';
import { Card, CardBody, Col, Row } from 'reactstrap';
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import {Link} from "react-router-dom";

const options = {
  title: {
    text: 'User Registration'
  },
  series: [{
    data: [1, 2, 3, 1, 1.5, 1.2, 1.9, 1.5]
  }]
}

const options2 = {
  title: {
    text: 'Hosted Trials'
  },
  series: [{
    data: [1, 1.1, 1.4, 1, 1.1, 1.2, 1, 1.3]
  }]
}

class Dashboard extends Component {

  render() {
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" sm="6" lg="3">
            <Card className="text-black">
              <CardBody className="pb-0 mb-3">
                <div className="text-value">30</div>
                <div>New Accounts</div>
              </CardBody>
            </Card>
          </Col>

          <Col xs="12" sm="6" lg="3">
            <Card className="text-black">
              <CardBody className="pb-0 mb-3">
                <div className="text-value">89</div>
                <div>Hosted Trials running</div>
              </CardBody>
            </Card>
          </Col>

          <Col xs="12" sm="6" lg="3">
            <Card className="text-black">
              <CardBody className="pb-0 mb-3">
                <div className="text-value">144</div>
                <div>Downloads</div>
              </CardBody>
            </Card>
          </Col>

          <Col xs="12" sm="6" lg="3">
            <Card className="text-black">
              <CardBody className="pb-0 mb-3">
                <div className="text-value">9.823</div>
                <div>Members online</div>
              </CardBody>
            </Card>
          </Col>
          <Col xs="12" sm="6" lg="6">
            <div className="chart-wrap">
              <HighchartsReact
                highcharts={Highcharts}
                options={options}
              />
            </div>
          </Col>
          <Col xs="12" sm="6" lg="6">
            <div className="chart-wrap">
              <HighchartsReact
                highcharts={Highcharts}
                options={options2}
              />
            </div>
          </Col>

          <Col xs="12" sm="6" lg="6" className="mt-4">
            <div className="header-item">
              <header className="">
                <h4 className="">Modern Identity Platform </h4>
                <span className="">The enterprise-grade platform for modern identity. </span>
              </header>
              <ul className="menubar">
                <li className="menuitem">
                  <Link to="/webinars" className="card-link">
                    <span className="title">
                      <span className="">Single Sign On</span>
                    </span>
                    <p className="item-description">Securely and easily implement Single Sign On for your customers,
                      partners and employees</p>
                  </Link>
                </li>
                <li className="menuitem">
                  <Link to="/webinars" className="card-link">
                    <span className="title">
                      <span className="">Single Sign On</span>
                    </span>
                    <p className="item-description">Securely and easily implement Single Sign On for your customers,
                      partners and employees</p>
                  </Link>
                </li>
                <li className="menuitem">
                  <Link to="/webinars" className="card-link">
                    <span className="title">
                      <span className="">Single Sign On</span>
                    </span>
                    <p className="item-description">Securely and easily implement Single Sign On for your customers,
                      partners and employees</p>
                  </Link>
                </li>
                <li className="menuitem">
                  <Link to="/webinars" className="card-link">
                    <span className="title">
                      <span className="">Single Sign On</span>
                    </span>
                    <p className="item-description">Securely and easily implement Single Sign On for your customers,
                      partners and employees</p>
                  </Link>
                </li><li className="menuitem">
                <Link to="/webinars" className="card-link">
                    <span className="title">
                      <span className="">Single Sign On</span>
                    </span>
                  <p className="item-description">Securely and easily implement Single Sign On for your customers,
                    partners and employees</p>
                </Link>
              </li>
              </ul>
            </div>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Dashboard;
