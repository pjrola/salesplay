import React, { Component } from 'react';
import { Badge, ButtonDropdown, DropdownItem, DropdownMenu, DropdownToggle, Nav, NavItem, NavLink } from 'reactstrap';
import { withTranslation } from 'react-i18next';
import { AppHeaderDropdown, AppNavbarBrand, AppSidebarToggler } from '@coreui/react';
import logo from '../../assets/img/brand/logo.svg'
import sygnet from '../../assets/img/brand/sygnet.svg'
import i18next from 'i18next';
import Avatar from 'react-avatar';

class DefaultHeader extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.changeLanguage = this.handleChangeLanguage.bind(this);

    this.state = {
      dropdownOpen: new Array(1).fill(false),
      lng: i18next.language,
    };
  }

  handleChangeLanguage = (lng, e) => {
    this.setState({ lng: lng }, () => {
      i18next.changeLanguage(lng, (err, t) => {
        if (err) return console.log('something went wrong loading', err);
        window.location.reload();
      });
    });
  };

  toggle(i) {
    const newArray = this.state.dropdownOpen.map((element, index) => { return (index === i ? !element : false); });
    this.setState({
      dropdownOpen: newArray,
    });
  }

  List(list) {
    if (!list) {
      return null;
    }

    if (!list.length) {
      return <p>Locales not enabled</p>;
    } else {
      return (
        <>
          {list.map(item =>
            <DropdownItem key={item.id} onClick={(e) => this.changeLanguage(item.code, e)}>{item.name}</DropdownItem>
          )}
        </>
      );
    }
  }

  render() {
    const { t, currentUser, locales } = this.props;
    return (
      <React.Fragment>
        <AppSidebarToggler className="d-lg-none sidebar-toggle" aria-label="side bar toggle" role="button" display="md" mobile />
          <AppNavbarBrand
            href="/dashboard"
            full={{ src: logo, width: 89, height: 25, alt: 'CoreUI Logo' }}
            minimized={{ src: sygnet, width: 30, height: 30, alt: 'CoreUI Logo' }}
          />
        <AppSidebarToggler className="d-md-down-none" aria-label="side bar toggle" role="button" display="lg" />
        <Nav className="ml-auto" navbar>
          <NavItem className="d-md-down-none locale-toggle">
            <ButtonDropdown className="mr-1 float-left ml-2" isOpen={this.state.dropdownOpen[0]} toggle={() => { this.toggle(0); }}>
              <DropdownToggle>
                <span className="current-locale">{this.state.lng}</span>
                <i className={'flag-icon flag-icon-' + this.state.lng}></i>
              </DropdownToggle>
              <DropdownMenu>
                {this.List(locales)}
              </DropdownMenu>
            </ButtonDropdown>
          </NavItem>
          <NavItem className="d-md-down-none">
            <NavLink href="#"><i className="fa fa-bell-o"></i><Badge pill color="danger">5</Badge></NavLink>
          </NavItem>
          <AppHeaderDropdown direction="down" className="mr-3">
            <DropdownToggle nav>
              <Avatar size="35" round={true} color="#0097f7" name={currentUser} />
            </DropdownToggle>
            <DropdownMenu right style={{ right: 'auto' }}>
              <DropdownItem header tag="div" className="text-center"><strong>{t('layout.header.account')}</strong></DropdownItem>
              <DropdownItem><i className="fa fa-envelope-o"></i> {t('layout.header.messages')}<Badge color="success">42</Badge></DropdownItem>
              <DropdownItem><i className="fa fa-comments"></i> {t('layout.header.comments')}<Badge color="warning">42</Badge></DropdownItem>
              <DropdownItem><i className="fa fa-user"></i> {t('layout.header.profile')}</DropdownItem>
              <DropdownItem><i className="fa fa-wrench"></i> {t('layout.header.preferences')}</DropdownItem>
              <DropdownItem onClick={e => this.props.onLogout(e)}><i className="fa fa-lock"></i> {t('layout.header.logout')}</DropdownItem>
            </DropdownMenu>
          </AppHeaderDropdown>
        </Nav>
      </React.Fragment>
    );
  }
}

export default withTranslation()(DefaultHeader);
