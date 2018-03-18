import React, { Component } from 'react';
import { Dimmer, Grid, Loader, Segment, Pagination } from 'semantic-ui-react'
import CryptoIcon from '../../components/CryptoIcon/CryptoIcon'

const API = '/api/currencies';
const PAGESIZE = 10;

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rates: [],
      isLoading: true,
      activePage: 0,
      totalPages: 0,
    };
  }

  componentWillMount = () => this.fetchCurrencies(0, PAGESIZE)

  fetchCurrencies = (page, size) => {
    this.setState({isLoading: true});
    
    fetch(`${API}?page=${page}&size=${size}`)
      .then(response => response.json())
      .then(data => this.setState({
        rates: data.content,
        isLoading: false,
        activePage: data.number + 1,
        totalPages: data.totalPages
      }));
  }

  handlePaginationChange = (e, { activePage }) => this.fetchCurrencies(activePage - 1, PAGESIZE)

  render() {
    let rates = this.state.rates.map((rate) => {
      return this.renderRate(rate);
    });

    return (
      <div>
         <Dimmer.Dimmable dimmed={this.state.isLoading}>
          <Dimmer active={this.state.isLoading} inverted>
            <Loader>Loading</Loader>
          </Dimmer>
        <Grid celled>
          {rates}
        </Grid>
        <Segment basic textAlign='center'>
          <Pagination
            activePage={this.state.activePage}
            onPageChange={this.handlePaginationChange}
            size='mini'
            totalPages={this.state.totalPages}
          />
        </Segment>
        </Dimmer.Dimmable>
      </div>
    );
  }

  renderRate(rate) {
    return (
      <Grid.Row columns={14}>
        <Grid.Column width={2} textAlign='left'>
        <CryptoIcon name={rate.symbol.toLowerCase()} size={32}/>
        </Grid.Column>
        <Grid.Column width={4} textAlign='left'>
          {rate.name}
        </Grid.Column>
        <Grid.Column width={4}>
          $ {rate.priceInPriceCurrency != null ? rate.priceInPriceCurrency.toFixed(2) : 0.00}
        </Grid.Column>
        <Grid.Column width={4}>
          {rate.change24hInPercent != null ? rate.change24hInPercent.toFixed(2) : 0.00} %
        </Grid.Column>
      </Grid.Row>
    );
  }
}
