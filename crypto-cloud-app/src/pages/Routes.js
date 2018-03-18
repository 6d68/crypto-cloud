import React from "react";
import { Route, Switch } from "react-router-dom";
import NotFound from "./NotFound/NotFound";
import About from "./About/About";
import CurrencyRates from "./CurrencyRates/CurrencyRates";

export default ({ childProps }) => (  
  <Switch>
    <Route path="/" exact component={CurrencyRates} props={childProps}/>
    <Route path="/about" exact component={About} props={childProps}/>
    <Route path="*" component={NotFound} />
  </Switch>
  );