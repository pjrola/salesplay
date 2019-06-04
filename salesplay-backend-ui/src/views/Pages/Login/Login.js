import React, { Component } from 'react';
import LoginForm from "../../../components/Login/LoginForm";
import { Card, CardBody, CardGroup, Col, Container, Row } from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { login } from "../../../actions/admin-actions";

const mapStateToProps = state => ({
  admin: state.admin
});

const mapActionsToProps  = {
  login: login
};

class Login extends Component {

  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onSubmit = (values, { setSubmitting, setErrors }) => {
    this.props.login(values, this.props.history);
    setSubmitting(false);
  };

  render() {
    return (
      <div className="app flex-row align-items-center main">
        <Container>
          <Row className="justify-content-center">
            <Col xs="12" sm="6" md="5">
              <CardGroup>
                <Card className="p-4">
                  <CardBody>
                    <LoginForm onSubmit={this.onSubmit}/>
                  </CardBody>
                </Card>
              </CardGroup>
            </Col>
          </Row>
        </Container>
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
)(Login);
