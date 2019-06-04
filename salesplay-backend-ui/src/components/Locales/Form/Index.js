import React, {Component} from 'react';
import {Row, Col, FormGroup, Label, Input, Card, CardBody, FormFeedback, Button} from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { Form, Formik } from 'formik';
import * as Yup from 'yup'
import LaddaButton, { EXPAND_LEFT } from 'react-ladda';
import i18next from 'i18next';

const validationSchema = function (values) {
  return Yup.object().shape({
    name: Yup.string()
      .required('Name is required'),
    code: Yup.string()
      .required('Code is required'),
    isDefault: Yup.string()
      .required('isDefault is required'),
    isEnabled: Yup.string()
      .required('isEnabled is required!')
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
      expLeft: false,
      lng: i18next.language,
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
    this.findFirstError('localeForm', (fieldName) => {
      return Boolean(errors[fieldName])
    })
  }

  touchAll(setTouched, errors) {
    setTouched({
      name: true,
      code: true,
      isDefault: true,
      isEnabled: true
    });
    this.validateForm(errors)
  }

  render() {
    const { t } = this.props;

    return (
      <div className="animated fadeIn">
        <Formik
          initialValues={this.props.guide}
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
              <Form onSubmit={handleSubmit} noValidate name='localeForm'>
                 <Row>
                  <Col xs="12" md="12" lg="6">
                    <Card>
                      <CardBody>
                        <Row>
                          <Col xs="12">
                            <FormGroup>
                              <Label htmlFor="status">Locale Name</Label>
                              <Input type="text"
                                     name="name"
                                     autoComplete="off"
                                     id="name"
                                     valid={!errors.name}
                                     invalid={touched.name && !!errors.name}
                                     autoFocus={true}
                                     required
                                     onChange={handleChange}
                                     onBlur={handleBlur}
                                     value={values.name} />
                              <FormFeedback>{errors.name}</FormFeedback>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="status">ISO Code</Label>
                              <Input type="text"
                                     name="code"
                                     autoComplete="off"
                                     id="code"
                                     valid={!errors.code}
                                     invalid={touched.code && !!errors.code}
                                     autoFocus={true}
                                     required
                                     onChange={handleChange}
                                     onBlur={handleBlur}
                                     value={values.code} />
                              <FormFeedback>{errors.code}</FormFeedback>
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
  locale: {
    name: "",
    code: "",
    isDefault: "",
    isEnabled: "",
    locale: i18next.language
  }
};

export default withTranslation()(Index);
