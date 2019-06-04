import React, { Component } from 'react';
import { Col, Nav, NavItem, NavLink, Row, TabContent, TabPane } from 'reactstrap';
import GuideForm from "../../../components/Guides/Form/Index";
import GuideSettings from "../../../components/Guides/Form/Settings";
import MetaForm from "../../../components/Guides/Form/MetaForm";
import { withTranslation } from 'react-i18next';
import GuideSteps from "../../../components/Guides/Form/Step";
import { connect } from "react-redux";
import { compose } from 'redux';
import { createGuide } from "../../../actions/guides-actions";
import Loader from "../../../components/Loading/Loader";
import Error from "../../../components/Error/Error";

const mapStateToProps = state => ({
  guide: state.guides.items,
  loading: state.guides.loading,
  error: state.guides.error
});

const mapActionsToProps  = {
  createGuide: createGuide
};

class Create extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      activeTab: new Array(4).fill('1')
    };
  }

  toggle(tabPane, tab) {
    const newArray = this.state.activeTab.slice();
    newArray[tabPane] = tab;
    this.setState({
      activeTab: newArray,
    });
  }

  onSubmit = (values, { setSubmitting, setErrors }) => {
    console.log(values);
    this.props.createGuide(values, this.props.history);
    setSubmitting(false);
  };

  tabPane() {
    return (
      <>
        <TabPane tabId="1">
          <GuideForm onSubmit={this.onSubmit}/>
        </TabPane>
        <TabPane tabId="2">
          <GuideSteps/>
        </TabPane>
        <TabPane tabId="3">
          <GuideSettings/>
        </TabPane>
        <TabPane tabId="4">
          <MetaForm/>
        </TabPane>
      </>
    );
  }

  render() {
    const { t, error, loading } = this.props;

    if (error) {
      return <Error error={error}/>
    } else if (loading) {
      return <Loader/>
    }
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" md="12" lg="12">
            <Nav tabs>
              <NavItem>
                <NavLink
                  active={this.state.activeTab[0] === '1'}
                  onClick={() => { this.toggle(0, '1'); }}
                >
                  {t('pages.guide.tab.guide')}
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  active={this.state.activeTab[0] === '2'}
                  onClick={() => { this.toggle(0, '2'); }}
                >
                  {t('pages.guide.tab.steps')}
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  active={this.state.activeTab[0] === '3'}
                  onClick={() => { this.toggle(0, '3'); }}
                >
                  {t('pages.guide.tab.settings')}
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  active={this.state.activeTab[0] === '4'}
                  onClick={() => { this.toggle(0, '4'); }}
                >
                  {t('pages.guide.tab.meta')}
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent className="mt-4" activeTab={this.state.activeTab[0]}>
              {this.tabPane()}
            </TabContent>
          </Col>
        </Row>
      </div>
    );
  }
}

export default compose(
  connect(
    mapStateToProps,
    mapActionsToProps
  ),
  withTranslation()
)(Create);
