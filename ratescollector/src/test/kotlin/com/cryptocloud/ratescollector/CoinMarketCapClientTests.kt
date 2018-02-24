package com.cryptocloud.ratescollector

import com.cryptocloud.ratescollector.clients.CoinMarketCapClient
import com.cryptocloud.ratescollector.model.CoinMarketCapRate
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsNot
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import java.util.*


@RunWith(SpringRunner::class)
@RestClientTest(CoinMarketCapClient::class)
class CoinMarketCapClientTests {

    @Autowired
    lateinit var coinMarketCapClient: CoinMarketCapClient

    @Autowired
    lateinit var server: MockRestServiceServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Before
    fun setUp() {
        val coinMarketCapApiURL = "https://api.coinmarketcap.com/v1/ticker/?limit=0"

        val rate =
                CoinMarketCapRate("bitcoin", "Bitcoin", "BTC", "1", "573.137", "1.0", "72855700.0", "9080883500.0",
                        "15844176.0", "15844176.0", "15844176.0", "0.04", "-0.3", "-0.57", "1472762067")

        val rates = Arrays.asList(rate)
        val rateAsString = objectMapper.writeValueAsString(rates)

        server.expect(requestTo(coinMarketCapApiURL)).andRespond(withSuccess(rateAsString, MediaType.APPLICATION_JSON))
    }

    @Test
    fun whenClientRespondsWithExpectedJson_ServiceShouldReturnCorrectSetOfObjects() {

        val allRates = coinMarketCapClient.getAllRates()
        assertThat(allRates, `hasSize`(1))

        val btc = allRates.find { it.id == "bitcoin" }
        assertThat("Collection must have exactly one item with id: 'bitcoin'!", btc, IsNot(nullValue()))
    }
}