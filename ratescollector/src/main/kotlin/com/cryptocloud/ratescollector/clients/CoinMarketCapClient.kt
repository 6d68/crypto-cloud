package com.cryptocloud.ratescollector.clients

import com.cryptocloud.ratescollector.model.CoinMarketCapRate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service


@Service
class CoinMarketCapClient(templateBuilder: RestTemplateBuilder) {

    private val restTemplate = templateBuilder.build()

    fun getAllRates() : List<CoinMarketCapRate> {
        return restTemplate.getForObject("https://api.coinmarketcap.com/v1/ticker/?limit=0", Array<CoinMarketCapRate>::class.java).toList()
    }
}