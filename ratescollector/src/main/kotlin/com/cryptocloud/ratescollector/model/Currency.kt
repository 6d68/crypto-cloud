package com.cryptocloud.ratescollector.model

import java.util.Date

data class Currency(
    val id: String,
    val name: String,
    val symbol: String,
    val lastUpdated: Date,
    val change1hInPercent: Double?,
    val priceInPriceCurrency: Double?,
    val priceCurrency: String,
    val change7dInPercent: Double?,
    val change24hInPercent: Double?
)
