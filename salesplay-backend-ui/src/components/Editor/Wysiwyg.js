import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import ReactQuill from 'react-quill';
import * as PropTypes from "prop-types";

const defaultProps = {
  modules: {
    toolbar: [
      [{ 'header': '1'}, {'header': '2'}],
      ['bold', 'italic', 'underline', 'strike', 'code-block'],
      [{'list': 'ordered'}, {'list': 'bullet'}],
      ['link', 'image', 'video']
    ],
    clipboard: {
      matchVisual: false,
    }
  }
};

class Wysiwyg extends Component {

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(value) {
    this.props.onChange(value);
  }

  render() {
    const { className, modules, value } = this.props;
    return (
      <>
        <ReactQuill
          value={value}
          onChange={(value) => this.handleChange(value)}
          modules={modules || defaultProps.modules}
          className={className}
        />
      </>
    );
  }
}

Wysiwyg.propTypes = {
  field: PropTypes.string.isRequired
};

Wysiwyg.defaultProps = defaultProps;

export default withTranslation()(Wysiwyg);
