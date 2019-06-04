import React, {Component} from 'react';
import {Row, Col, FormGroup, Label, Input, Card, CardBody, FormFeedback, Button} from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { Form, Formik } from 'formik';
import * as Yup from 'yup'
import LaddaButton, { EXPAND_LEFT } from 'react-ladda';
import Wysiwyg from "../../Editor/Wysiwyg";
import i18next from 'i18next';

const validationSchema = function (values) {
  return Yup.object().shape({
    title: Yup.string()
      .required('Title is required'),
    subtitle: Yup.string()
      .required('Subtitle is required'),
    slug: Yup.string()
      .required('Slug is required'),
    image: Yup.string()
      .required('Image is required!'),
    overview: Yup.string()
      .required('Overview is required!'),
    locale: Yup.string()
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
    this.findFirstError('guideForm', (fieldName) => {
      return Boolean(errors[fieldName])
    })
  }

  touchAll(setTouched, errors) {
    setTouched({
      title: true,
      subtitle: true,
      slug: true,
      image: true,
      overview: true
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
              <Form onSubmit={handleSubmit} noValidate name='guideForm'>
                 <Row>
                  <Col xs="12" md="12" lg="6">
                    <Card>
                      <CardBody>
                        <Row>
                          <Col xs="12">
                            <FormGroup>
                              <Label htmlFor="status">{t('pages.guide.form.main.label.status')}</Label>
                              <Input type="select" name="editorialStatus" id="editorialStatus" onChange={handleChange} defaultValue={values.editorialStatus}>
                                <option value="draft">{t('general.edit_status.draft')}</option>
                                <option value="review">{t('general.edit_status.review')}</option>
                                <option value="published">{t('general.edit_status.published')}</option>
                              </Input>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="status">{t('pages.guide.form.main.label.visibility')}</Label>
                              <Input type="select" name="visibility" id="visibility" onChange={handleChange} defaultValue={values.visibility}>
                                <option value="public">{t('general.visibility.public')}</option>
                                <option value="private">{t('general.visibility.private')}</option>
                              </Input>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="title">{t('pages.guide.form.main.label.title')}</Label>
                              <Input type="text"
                                 name="title"
                                 autoComplete="off"
                                 id="title"
                                 valid={!errors.title}
                                 invalid={touched.title && !!errors.title}
                                 autoFocus={true}
                                 required
                                 onChange={handleChange}
                                 onBlur={handleBlur}
                                 value={values.title} />
                              <FormFeedback>{errors.title}</FormFeedback>
                            </FormGroup>
                            <FormGroup>
                              <Label htmlFor="subtitle">Subtitle</Label>
                              <Input type="text"
                                 name="subtitle"
                                 autoComplete="off"
                                 id="subtitle"
                                 valid={!errors.subtitle}
                                 invalid={touched.subtitle && !!errors.subtitle}
                                 required
                                 onChange={handleChange}
                                 onBlur={handleBlur}
                                 value={values.subtitle} />
                              <FormFeedback>{errors.subtitle}</FormFeedback>
                            </FormGroup>
                          </Col>
                        </Row>
                      </CardBody>
                    </Card>
                  </Col>
                   <Col xs="12" md="12" lg="6">
                     <Card>
                       <CardBody>
                         <Row>
                           <Col xs="12">
                             <FormGroup>
                               <Label htmlFor="slug">{t('pages.guide.form.main.label.slug')}</Label>
                               <Input type="slug"
                                  name="slug"
                                  autoComplete="off"
                                  id="slug"
                                  valid={!errors.slug}
                                  invalid={touched.slug && !!errors.slug}
                                  required
                                  onChange={handleChange}
                                  onBlur={handleBlur}
                                  value={values.slug} />
                               <FormFeedback>{errors.slug}</FormFeedback>
                             </FormGroup>
                             <FormGroup>
                               <Label htmlFor="image">{t('pages.guide.form.main.label.card_image')}</Label>
                               <Input type="text"
                                  name="image"
                                  autoComplete="off"
                                  id="image"
                                  valid={!errors.image}
                                  invalid={touched.image && !!errors.image}
                                  required
                                  onChange={handleChange}
                                  onBlur={handleBlur}
                                  value={values.image} />
                               <FormFeedback>{errors.image}</FormFeedback>
                             </FormGroup>
                           </Col>
                         </Row>
                       </CardBody>
                     </Card>
                   </Col>
                </Row>
                <div className="mb-4">
                  <Wysiwyg
                    field={'overview'}
                    value={values.overview}
                    className={(touched.overview && !!errors.overview) ? 'is-invalid' : ''}
                    onChange={(value) => setFieldValue('overview', value)}
                  />
                  <Input type="text"
                     name="overview"
                     autoComplete="off"
                     id="overview"
                     valid={!errors.overview}
                     invalid={touched.overview && !!errors.overview}
                     required
                     onChange={handleChange}
                     className="hidden"
                     onBlur={handleBlur}
                     value={values.overview} />
                  <FormFeedback>{errors.overview}</FormFeedback>
                </div>
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
                <Input type="hidden"
                       name="locale"
                       autoComplete="off"
                       id="locale"
                       valid={!errors.locale}
                       invalid={touched.locale && !!errors.locale}
                       required
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.locale} />
              </Form>
            )}
        />
      </div>
    )
  }
}

Index.defaultProps = {
  guide: {
    title: "",
    subtitle: "",
    slug: "",
    image: "",
    overview: "",
    editorialStatus: "draft",
    visibility: "public",
    locale: i18next.language
  }
};

export default withTranslation()(Index);
