import React, { Component } from 'react';
import { Button } from 'reactstrap';
import { Link } from "react-router-dom";

class Index extends Component {

  render() {
    return (
      <div className="animated fadeIn">
        <Link to="/content/guides/groups/create">
          <Button color="primary float-left">
            <i className="fa fa-plus"></i>&nbsp;Add New
          </Button>
        </Link>
      </div>
    );
  }
}

export default Index;
