package com.cryptocloud.currencies.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Currency {
    @Id
    private String id;
    private String name;
    private String symbol;
    private Date lastUpdated;
    private Double change1hInPercent;
    private Double priceInPriceCurrency;
    private String priceCurrency;
    private Double change7dInPercent;
    private Double change24hInPercent;

    public Currency() {
    }

    public Currency(String id, String name, String symbol, Date lastUpdated, Double change1hInPercent,
            Double priceInPriceCurrency, String priceCurrency, Double change7dInPercent, Double change24hInPercent) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.lastUpdated = lastUpdated;
        this.change1hInPercent = change1hInPercent;
        this.priceInPriceCurrency = priceInPriceCurrency;
        this.priceCurrency = priceCurrency;
        this.change7dInPercent = change7dInPercent;
        this.change24hInPercent = change24hInPercent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Double getChange1hInPercent() {
        return change1hInPercent;
    }

    public Double getPriceInPriceCurrency() {
        return priceInPriceCurrency;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public Double getChange7dInPercent() {
        return change7dInPercent;
    }

    public Double getChange24hInPercent() {
        return change24hInPercent;
    }
}