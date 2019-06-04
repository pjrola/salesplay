import React, { Component } from 'react';
import { Row, Col, FormGroup, Label, Input, Card, CardBody } from 'reactstrap';

class MetaForm extends Component {

  render() {
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" md="6">
            <Card>
              <CardBody>
                <Row>
                  <Col xs="12">
                    <FormGroup>
                      <Label htmlFor="title">Page Title</Label>
                      <Input type="text" id="title" required autoFocus/>
                    </FormGroup>
                  </Col>
                    <Col xs="12">
                      <FormGroup>
                        <Label htmlFor="slug">Meta Description</Label>
                        <Input type="textarea" name="textarea-input" rows="6" id="slug" required/>
                      </FormGroup>
                    </Col>
                    <Col xs="12">
                      <FormGroup>
                        <Label htmlFor="slug">Meta Keywords</Label>
                        <Input type="textarea" id="slug" rows="6" required/>
                      </FormGroup>
                    </Col>
                </Row>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default MetaForm;
