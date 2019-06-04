import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import {Button, Col, Form, FormFeedback, FormGroup, Input, Label, Row} from 'reactstrap';
import * as Yup from "yup";
import {Formik} from "formik";
import LaddaButton, { EXPAND_LEFT } from 'react-ladda';
import { withTranslation } from 'react-i18next';

const validationSchema = function (values) {
  return Yup.object().shape({
    username: Yup.string()
      .required('Username is required')
      .email('Invalid email'),
    password: Yup.string()
      .required('Password is required')
  })
};

const validate = (getValidationSchema) => {
  return (values) => {
    const validationSchema = getValidationSchema(values);
    try {
      validationSchema.validateSync(values, { abortEarly: false });
      return {}
    } catch (error) {
      return getErrorsFromValidationError(error)
    }
  }
};

const getErrorsFromValidationError = (validationError) => {
  const FIRST_ERROR = 0;
  return validationError.inner.reduce((errors, error) => {
    return {
      ...errors,
      [error.path]: error.errors[FIRST_ERROR],
    }
  }, {})
};

const initialValues = {
  username: "",
  password: ""
};

class LoginForm extends Component {

  constructor(props) {
    super(props);
    this.touchAll = this.touchAll.bind(this);

    this.state = {
      expLeft: false
    };
  }

  toggle(name) {
    this.setState({
      [name]: !this.state[name]
    })
  }

  findFirstError (formName, hasError) {
    const form = document.forms[formName];
    for (let i = 0; i < form.length; i++) {
      if (hasError(form[i].name)) {
        form[i].focus();
        break
      }
    }
  }

  validateForm (errors) {
    this.findFirstError('loginForm', (fieldName) => {
      return Boolean(errors[fieldName])
    })
  }

  touchAll(setTouched, errors) {
    setTouched({
      title: true,
      subtitle: true,
      slug: true,
      image: true
    });
    this.validateForm(errors)
  }

  render() {
    const { t, error } = this.props;
    return (

      <Formik
        initialValues={initialValues}
        validate={validate(validationSchema)}
        onSubmit={this.props.onSubmit}
        render={
          ({
             values,
             errors,
             touched,
             status,
             dirty,
             handleChange,
             handleBlur,
             handleSubmit,
             isSubmitting,
             isValid,
             handleReset,
             setTouched
           }) => (
            <Form onSubmit={handleSubmit} name="loginForm">
              <h1 className="text-center mb-4">{t('pages.login.form.title')}</h1>
              <Row>
                <Col xs="12">
                  <div className="text-danger font-weight-bold mb-3">{error}</div>
                  <FormGroup>
                    <Label htmlFor="username">{t('pages.login.form.username_label')}</Label>
                    <Input type="text"
                       name="username"
                       id="username"
                       valid={!errors.username}
                       invalid={touched.username && !!errors.username}
                       autoFocus={true}
                       required
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.username} />
                    <FormFeedback>{errors.username}</FormFeedback>
                  </FormGroup>
                </Col>
              </Row>
              <Row>
                <Col xs="12">
                  <FormGroup>
                    <Label htmlFor="password">{t('pages.login.form.password_label')}</Label>
                    <Input type="password"
                       name="password"
                       autoComplete="off"
                       id="password"
                       valid={!errors.password}
                       invalid={touched.title && !!errors.password}
                       required
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.password} />
                    <FormFeedback>{errors.password}</FormFeedback>
                  </FormGroup>
                </Col>
              </Row>
              <Row>
                <Col xs="6">
                  <LaddaButton
                    disabled={isSubmitting || !isValid}
                    type="submit"
                    className="btn btn-primary btn-ladda mr-2 px-4"
                    loading={isSubmitting}
                    data-style={EXPAND_LEFT}
                    onClick={() => this.toggle('expLeft')}
                  >
                    {t('pages.login.form.submit')}
                  </LaddaButton>
                </Col>
                <Col xs="6" className="text-right">
                  <Link to="/register">
                    <Button color="link" className="px-0">{t('pages.login.form.forgot_password')}</Button>
                  </Link>
                </Col>
              </Row>
            </Form>
          )}
      />
    );
  }
}

export default withTranslation()(LoginForm);
