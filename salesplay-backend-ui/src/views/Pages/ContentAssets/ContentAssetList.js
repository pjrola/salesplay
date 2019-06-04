import React, { Component } from 'react';
import {Button, Row} from 'reactstrap';
import {Link} from "react-router-dom";

class ContentAssetList extends Component {

  render() {
    return (
      <div className="animated fadeIn">
        <Row>
          <Link to="/marketing/asset">
            <Button color="primary float-left">
              <i className="fa fa-plus"></i>&nbsp;Add New
            </Button>
          </Link>
        </Row>
      </div>
    );
  }
}

export default ContentAssetList;
