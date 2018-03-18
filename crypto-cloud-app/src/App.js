import React, { Component } from 'react';
import Routes from "./pages/Routes";
import { Link, withRouter } from 'react-router-dom';
import { Container, Menu, Icon } from 'semantic-ui-react'

class App extends Component {
  render() {
    return (
      <div>
        <Menu fixed='top' inverted>
        <Container>
          <Menu.Item as='a' header>
            <Link to='/'>Crypto Cloud</Link>
        </Menu.Item>
        <Menu.Menu position='right'>
          <Menu.Item  as='a'>
            <Link to="/about"><Icon name='question'/></Link>
          </Menu.Item>
          <Menu.Item as='a' href="https://github.com/6d68/crypto-cloud" target='_BLANK'><Icon name='github'/></Menu.Item>
        </Menu.Menu>
        </Container>
      </Menu>
        <Container text style={{ marginTop: '5em' }}>
          <Routes />
        </Container>
      </div>
    );
  }
}

export default withRouter(App);
