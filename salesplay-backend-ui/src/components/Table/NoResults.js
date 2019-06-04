import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';

class NoResults extends Component {

  render() {
    const { rowCount, t } = this.props;
    return (
      <tr className="text-center">
        <td colSpan={rowCount}>{t('pages.guide.list.no_results')}</td>
      </tr>
    );
  }
}

export default withTranslation()(NoResults);
