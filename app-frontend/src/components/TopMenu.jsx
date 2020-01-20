import React from 'react';
import { withRouter } from 'react-router-dom';
import FaceIcon from '@material-ui/icons/Face';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Chip from '@material-ui/core/Chip';
import { navigation } from '../config/path.js';
import ClickAwayListener from '@material-ui/core/ClickAwayListener';

import './topmenu.sass';

class TopMenu extends React.Component {

  constructor() {
    super()
    const userProfile = JSON.parse(window.localStorage.getItem('userProfile'));

    this.state = { ...userProfile, anchorEl: null }
  }

  handleClick = event => {
    this.setState({ anchorEl: event.currentTarget });
  };

  handleClose = () => {
    this.setState({ anchorEl: null, open: false });
  };

  logout = () => {
    window.localStorage.clear();
    this.props.history.push(navigation.login);
  }

  render() {
    const { anchorEl } = this.state;

    return (
      <div>
        <Chip
          icon={<FaceIcon />}
          onClick={this.handleClick}
          label={<span className='name-wrapper'>{
            this.props.userData && Object.keys(this.props.userData).length !== 0 || '--'} <i className="material-icons">arrow_drop_down</i></span>}
          color="primary"
        />

        <ClickAwayListener onClickAway={this.handleClose}>
          <Menu
            id="top-menu"
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={this.handleClose}
          >
            <MenuItem onClick={this.logout}>Logout</MenuItem>
          </Menu>
        </ClickAwayListener>
      </div>
    );
  }
}

export default withRouter(TopMenu);
