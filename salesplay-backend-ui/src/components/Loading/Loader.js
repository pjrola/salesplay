import React, { Component } from 'react';
import { Col, Row } from "reactstrap";
import { BeatLoader } from "react-spinners";

class Loader extends Component {

  render() {
    return (
      <Row>
        <Col xs="12" md="12">
          <div className="text-center mt-4">
            <BeatLoader
              sizeUnit={"px"}
              size={12}
              color={'#0097f7'}
              loading={true}
            />
          </div>
        </Col>
      </Row>
    );
  }
}

export default Loader;
