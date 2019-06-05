import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import {Table, Row, Col, ButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem, Input} from 'reactstrap';
import NoResults from '../../Table/NoResults';
import RowItem from "../../Locales/List/RowItem";

class CrudTable extends Component {

  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.handleCheckboxClick = this.handleCheckboxClick.bind(this);

    this.state = {
      dropdownOpen: false,
      checkedListAll: [],
      ItemsChecked: false
    };
  }

  toggle(i) {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen
    });
  }

  selectedItems(e) {
    const { value, checked } = e.target;
    let { checkedListAll } = this.state;

    if (checked) {
      checkedListAll = [...checkedListAll, value];
    } else {
      checkedListAll = checkedListAll.filter(el => el !== value);
      if (this.state.ItemsChecked) {
        this.setState({
          ItemsChecked: !this.state.ItemsChecked
        });
      }
    }
    this.setState({ checkedListAll });
  }

  onDeleteItems(e, list) {
    e.preventDefault();
    this.props.onDelete(list);
    this.setState({
      checkedListAll: [],
      ItemsChecked: false
    });
  }

  handleCheckboxClick(e) {
    const { value, checked } = e.target;

    if (checked) {
      this.setState(prevState => ({
        checkedListAll: [...prevState.checkedListAll, value * 1]
      }));
    } else {
      this.setState(prevState => ({
        checkedListAll: prevState.checkedListAll.filter(item => item.toString() !== value)
      }));
    }
  }

  selectItem(e) {
    const { checked } = e.target;
    const { items } = this.props;
    const collection = [];

    if (checked) {
      for (const item of items) {
        collection.push(item.id);
      }
    }

    this.setState({
      checkedListAll: collection,
      ItemsChecked: checked
    });
  }

  List(list) {
    if (!list) {
      return null;
    }

    if (!list.length) {
      return <NoResults rowCount={7}/>;
    } else {
      const { checkedListAll, ItemsChecked } = this.state;
      return (
        <>
          {list.map(item =>
            <RowItem
              key={item.slug}
              item={item}
              selectedItems={this.selectedItems.bind(this)}
              ItemsChecked={ItemsChecked}
              checkedListAll={checkedListAll}
              handleCheckboxClick={this.handleCheckboxClick}
            />
          )}
        </>
      );
    }
  }

  render() {
    const { items, t } = this.props;
    const { ItemsChecked, checkedListAll } = this.state;
    return (
      <>
        <Row className="mb-3">
          <Col xs="12" sm="8" lg="9" className="text-center mt-0 p-0">
            <ButtonDropdown className="mr-1 float-left ml-2" isOpen={this.state.dropdownOpen} toggle={this.toggle}>
              <DropdownToggle caret color="primary">
                {t('pages.guide.operations.action')}
              </DropdownToggle>
              <DropdownMenu>
                <DropdownItem><i className="fa fa-copy text-primary"></i>{t('pages.guide.operations.duplicate')}</DropdownItem>
                <DropdownItem onClick={e=>this.onDeleteItems(e, [checkedListAll])}><i className="fa fa-trash-o text-danger"></i>{t('pages.guide.operations.delete')}</DropdownItem>
                <DropdownItem><i className="fa fa-file-archive-o text-warning"></i>{t('pages.guide.operations.archive')}</DropdownItem>
              </DropdownMenu>
            </ButtonDropdown>
          </Col>
          <Col xs="4" sm="4" md="4" lg="3" className="text-center mt-0 p-0">
            <Input type="text" id="name" placeholder="Search by name" required />
          </Col>
        </Row>
        <Row>
          <Table hover responsive className="mb-0">
            <thead>
            <tr>
              <th className="text-center">
                <div className="pretty p-icon p-smooth">
                  <input type="checkbox" onChange={this.selectItem.bind(this)} checked={ItemsChecked}/>
                  <div className="state p-primary">
                    <i className="fa fa-check icon"/>
                    <label></label>
                  </div>
                </div>
              </th>
              <th className="text-center">Name</th>
              <th className="text-center">Code</th>
              <th className="text-center">Enabled</th>
              <th className="text-center">Default</th>
              <th className="text-center">{t('pages.guide.list.header.created')}</th>
              <th className="text-center">Updated</th>
            </tr>
            </thead>
            <tbody>
            {this.List(items)}
            </tbody>
          </Table>
        </Row>
      </>
    );
  }
}

export default withTranslation()(CrudTable);
