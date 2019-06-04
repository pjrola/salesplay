import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { withTranslation } from 'react-i18next';
import Checkbox from "../../Input/Checkbox";

class RowItem extends Component {

  render() {
    const {
      guide,
      selectedItems,
      checkedListAll
    } = this.props;

    console.log(guide);

    return (
        <tr >
          <td className="text-center">
            <div className="pretty p-icon">
              <Checkbox
                item={guide}
                selectedItems={selectedItems}
                isChecked={checkedListAll.includes(guide.id)}
                handleCheckboxClick={this.props.handleCheckboxClick}
              />
              <div className="state p-primary">
                <i className="fa fa-check icon"/>
                <label/>
              </div>
            </div>
          </td>
          <td>
            <div>{guide.translations[0].title}</div>
            <div className="small text-muted">
              Author: prola | Url: <Link to={{ pathname: '/content/guide/update/' + guide.id }}>view</Link>
            </div>
          </td>
          <td className="text-center">
            <div>
              <span className={'edit-status-' + guide.editorialStatus}>{guide.editorialStatus}</span>
            </div>
          </td>
          <td>
            <div className="text-center">
              {guide.visibility}
            </div>
          </td>
          <td>
            <div className="text-center">
              {guide.slug}
            </div>
          </td>
          <td className="text-center">
            {guide.last_edited_by}
          </td>
          <td className="text-center">
            {guide.createdAt}
          </td>
          <td className="text-center">
            {guide.lastModifiedAt}
          </td>
        </tr>
    );
  }
}

export default withTranslation()(RowItem);
