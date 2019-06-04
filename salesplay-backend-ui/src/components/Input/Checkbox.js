import React, { Component } from 'react';

class Checkbox extends Component {
  render() {
    const { item, isChecked } = this.props;

    return (
      <input
        type="checkbox"
        value={item.id}
        checked={isChecked}
        onChange={this.props.handleCheckboxClick}
      />
    );
  }
} export default Checkbox;
