import React, { Component } from 'react'
import CryptoIcons from './crypto-coins.svg'
import PropTypes from 'prop-types';

class CryptoIcon extends Component {
    render() {
        return (
            <svg width={this.props.size} height={this.props.size}>
                <use xlinkHref={`${CryptoIcons}#${this.props.name}`} />
            </svg>
        )
    }
}

CryptoIcon.propTypes = {
  name: PropTypes.string,
  size: PropTypes.number
};


export default CryptoIcon