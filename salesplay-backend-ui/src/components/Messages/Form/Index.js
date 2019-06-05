import React, {Component} from 'react';
import {Row, Col, FormGroup, Label, Input, Card, CardBody, FormFeedback, Button} from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { Form, Formik } from 'formik';
import * as Yup from 'yup'
import LaddaButton, { EXPAND_LEFT } from 'react-ladda';
import { AppSwitch } from "@coreui/react";

const validationSchema = function (values) {
  return Yup.object().shape({
    locale: Yup.string()
      .min(2, 'Invalid Length')
      .required('Locale Name is required'),
    key: Yup.string()
      .min(2, 'Invalid Length')
      .required('Message Key is required'),
    content: Yup.string()
      .min(2, 'Invalid Length')
      .required('Message Content is required')
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

class Index extends Component {
  constructor(props){
    super(props);
    this.touchAll = this.touchAll.bind(this);

    this.state = {
      expLeft: false
    }
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
    this.findFirstError('messageForm', (fieldName) => {
      return Boolean(errors[fieldName])
    })
  }

  touchAll(setTouched, errors) {
    setTouched({
      locale: true,
      key: true,
      content: true,
    });
    this.validateForm(errors)
  }

  render() {
    const { t } = this.props;

    return (
      <div className="animated fadeIn">
        <Formik
          initialValues={this.props.message}
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
               setTouched,
               setFieldValue
             }) => (
              <Form onSubmit={handleSubmit} noValidate name='messageForm'>
                 <Row>
                  <Col xs="12" md="12" lg="6">
                    <Card>
                      <CardBody>
                        <Row>
                          <Col xs="12">
                            <div className="aside-options mb-3">
                              <div className="clearfix mt-2">
                                <small className="ml-2"><b>Translation Verified</b></small>
                                <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} size={'lg'}/>
                              </div>
                              <div className="mt-2">
                                <small className="text-muted">Translator verified translation</small>
                              </div>
                            </div>
                            <div className="aside-options mb-3">
                              <div className="clearfix mt-2">
                                <small className="ml-2"><b>Translation Excluded</b></small>
                                <AppSwitch className={'float-left'} variant={'pill'} label color={'success'} size={'lg'}/>
                              </div>
                              <div className="mt-2">
                                <small className="text-muted">Excluded from translation</small>
                              </div>
                            </div>
                            <FormGroup>
                              <Label htmlFor="status">Locale</Label>
                              <Input type="text"
                                     name="locale"
                                     autoComplete="off"
                                     id="locale"
                                     valid={!errors.locale}
                                     invalid={touched.locale && !!errors.locale}
                                     required
                                     placeholder={"e.g. en"}
                                     onChange={handleChange}
                                     onBlur={handleBlur}
                                     value={values.locale} />
                              <FormFeedback>{errors.locale}</FormFeedback>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="status">Message Key</Label>
                              <Input type="text"
                                     name="key"
                                     autoComplete="off"
                                     id="key"
                                     valid={!errors.key}
                                     invalid={touched.key && !!errors.key}
                                     required
                                     placeholder={"e.g. validation.error.msg"}
                                     onChange={handleChange}
                                     onBlur={handleBlur}
                                     value={values.key} />
                              <FormFeedback>{errors.key}</FormFeedback>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="status">Message Content</Label>
                              <Input type="textarea"
                                     name="content"
                                     autoComplete="off"
                                     id="content"
                                     valid={!errors.content}
                                     invalid={touched.content && !!errors.content}
                                     required
                                     onChange={handleChange}
                                     onBlur={handleBlur}
                                     value={values.content} />
                              <FormFeedback>{errors.content}</FormFeedback>
                            </FormGroup>
                          </Col>
                        </Row>
                      </CardBody>
                    </Card>
                  </Col>
                </Row>
                <FormGroup>
                  <LaddaButton
                    disabled={isSubmitting}
                    type="submit"
                    className="btn btn-primary btn-ladda mr-2"
                    loading={isSubmitting}
                    data-style={EXPAND_LEFT}
                    onClick={() => this.toggle('expLeft')}
                  >
                    Save
                  </LaddaButton>
                  <Button type="reset" color="danger" className="mr-1" onClick={handleReset}>Reset</Button>
                </FormGroup>
              </Form>
            )}
        />
      </div>
    )
  }
}

Index.defaultProps = {
  message: {
    locale: "",
    key: "",
    content: ""
  }
};

export default withTranslation()(Index);
