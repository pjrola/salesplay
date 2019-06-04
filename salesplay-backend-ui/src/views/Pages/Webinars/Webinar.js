import React, { Component } from 'react';
import {Badge, Card, CardBody, CardFooter, CardHeader, Col, Row} from 'reactstrap';
import {Link} from "react-router-dom";

class Webinar extends Component {

  render() {
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" sm="6" md="6" lg="3">
            <Card className="guide-card">
              <div className="card-top">
                <Link to="/webinars" className="card-link">
                  <img alt="card" className="card-img-top img-responsive"
                       src="https://images.unsplash.com/photo-1496065187959-7f07b8353c55?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80"></img>
                  <div className="card-desc-overlay">
                <span className="card-back-title mt-3">
                  Create an IoT application using the core building blocks of ThingWorx Foundation....
                </span>
                  </div>
                </Link>
              </div>
              <Link to="/content/webinar" className="card-link">
                <CardHeader>
                  Creating Spring Bean dynamically in the Runtime
                </CardHeader>
              </Link>
              <CardBody>
                <div className="author-block">
                  <i className="fa fa-user-o fa-md"></i>
                  <span className="author-name">Paul Rola</span>
                </div>
              </CardBody>
              <CardFooter>
                <i className="fa fa-clock-o fa-md"></i>
                <span className="est-time">30 Minutes</span>
              </CardFooter>
            </Card>
          </Col>
          <Col xs="12" sm="6" md="6" lg="3">
            <Card className="guide-card">
              <div className="card-top">
                <Badge className="mr-1 info-block" href="#" color="success">New</Badge>
                <Link to="/webinars" className="card-link">
                  <img alt="card" className="card-img-top img-responsive"
                       src="https://images.unsplash.com/photo-1491692222645-c1279173890b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2298&q=80"></img>
                  <div className="card-desc-overlay">
                <span className="card-back-title mt-3">
                  Writing a Full Featured Maven Pom
                </span>
                  </div>
                </Link>
              </div>
              <Link to="/content/webinar" className="card-link">
                <CardHeader>
                  Connect Industrial Devices and Systems
                </CardHeader>
              </Link>
              <CardBody>
                <div className="author-block">
                  <i className="fa fa-user-o fa-md"></i>
                  <span className="author-name">Paul Rola</span>
                </div>
              </CardBody>
              <CardFooter>
                <i className="fa fa-clock-o fa-md"></i>
                <span className="est-time">30 Minutes</span>
              </CardFooter>
            </Card>
          </Col>
          <Col xs="12" sm="6" md="6" lg="3">
            <Card className="guide-card">
              <div className="card-top">
                <Link to="/webinars" className="card-link">
                  <img alt="card" className="card-img-top img-responsive"
                       src="https://images.unsplash.com/photo-1543946602-8496af5aaa53?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80"></img>
                  <div className="card-desc-overlay">
                <span className="card-back-title mt-3">
                  Create an IoT application using the core building blocks of ThingWorx Foundation....
                </span>
                  </div>
                </Link>
              </div>
              <Link to="/content/webinar" className="card-link">
                <CardHeader>
                  Automate Custom EC2 AMIs
                </CardHeader>
              </Link>
              <CardBody>
                <div className="author-block">
                  <i className="fa fa-user-o fa-md"></i>
                  <span className="author-name">Paul Rola</span>
                </div>
              </CardBody>
              <CardFooter>
                <i className="fa fa-clock-o fa-md"></i>
                <span className="est-time">30 Minutes</span>
              </CardFooter>
            </Card>
          </Col>
          <Col xs="12" sm="6" md="6" lg="3">
            <Card className="guide-card">
              <div className="card-top">
                <Link to="/webinars" className="card-link">
                  <img alt="card" className="card-img-top img-responsive"
                       src="https://images.unsplash.com/photo-1547280746-0e984cc4ca31?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2265&q=80"></img>
                  <div className="card-desc-overlay">
                <span className="card-back-title mt-3">
                  Create an IoT application using the core building blocks of ThingWorx Foundation....
                </span>
                  </div>
                </Link>
              </div>
              <Link to="/content/webinar" className="card-link">
                <CardHeader>
                  Exploratory Data Analysis: An Illustration in Python
                </CardHeader>
              </Link>
              <CardBody>
                <div className="author-block">
                  <i className="fa fa-user-o fa-md"></i>
                  <span className="author-name">Paul Rola</span>
                </div>
              </CardBody>
              <CardFooter>
                <i className="fa fa-clock-o fa-md"></i>
                <span className="est-time">30 Minutes</span>
              </CardFooter>
            </Card>
          </Col>
          <Col xs="12" sm="6" md="6" lg="3">
            <Card className="guide-card">
              <div className="card-top">
                <Link to="/webinars" className="card-link">
                  <img alt="card" className="card-img-top img-responsive"
                       src="https://images.unsplash.com/photo-1547280746-0e984cc4ca31?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2265&q=80"></img>
                  <div className="card-desc-overlay">
                <span className="card-back-title mt-3">
                  Create an IoT application using the core building blocks of ThingWorx Foundation....
                </span>
                  </div>
                </Link>
              </div>
              <Link to="/content/webinar" className="card-link">
                <CardHeader>
                  Exploratory Data Analysis: An Illustration in Python
                </CardHeader>
              </Link>
              <CardBody>
                <div className="author-block">
                  <i className="fa fa-user-o fa-md"></i>
                  <span className="author-name">Paul Rola</span>
                </div>
              </CardBody>
              <CardFooter>
                <i className="fa fa-clock-o fa-md"></i>
                <span className="est-time">30 Minutes</span>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Webinar;
