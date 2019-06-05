import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import Checkbox from "../../Input/Checkbox";

class RowItem extends Component {

  render() {
    const {
      item,
      selectedItems,
      checkedListAll
    } = this.props;

    console.log(item);

    return (
      <tr >
        <td className="text-center">
          <div className="pretty p-icon">
            <Checkbox
              item={item}
              selectedItems={selectedItems}
              isChecked={checkedListAll.includes(item.id)}
              handleCheckboxClick={this.props.handleCheckboxClick}
            />
            <div className="state p-primary">
              <i className="fa fa-check icon"/>
              <label/>
            </div>
          </div>
        </td>
        <td>
          <div className="text-center">
            {item.name}
          </div>
        </td>
        <td>
          <div className="text-center">
            {item.code}
          </div>
        </td>
        <td>
          <div className="text-center">
            {JSON.stringify(item.isEnabled)}
          </div>
        </td>
        <td>
          <div className="text-center">
            {JSON.stringify(item.isDefault)}
          </div>
        </td>
        <td className="text-center">
          {item.createdAt}
        </td>
        <td className="text-center">
          {item.lastModifiedAt}
        </td>
      </tr>
    );
  }
}

export default withTranslation()(RowItem);
