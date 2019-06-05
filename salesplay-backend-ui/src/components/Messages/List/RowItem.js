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
          <div>
            {item.locale}
          </div>
        </td>
        <td>
          <div>
            {JSON.stringify(item.verified)}
          </div>
        </td>
        <td>
          <div>
            {JSON.stringify(item.excluded)}
          </div>
        </td>
        <td>
          <div>
            {item.key}
          </div>
        </td>
        <td>
          <div>
            {item.content}
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
