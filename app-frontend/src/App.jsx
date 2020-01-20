import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Provider } from 'react-redux';
import Login from './containers/Auth/Login';
import isAuthenticated from './containers/Auth/requiresAuth';
import Header from './containers/Header';

import store from './store/store.js';

import './App.sass';
import './sass/typo.scss';

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div>
            <Switch>
              <Route path="/login" component={Login} />
              <Route path="/" component={isAuthenticated(Header)} />
            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
