package com.cryptocloud.ratescollector

import com.cryptocloud.ratescollector.clients.CoinMarketCapClient
import com.cryptocloud.ratescollector.clients.CurrencyServiceClient
import com.cryptocloud.ratescollector.model.CoinMarketCapRate
import com.cryptocloud.ratescollector.model.Currency
import com.cryptocloud.ratescollector.synchronization.RateSynchronizer
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


@RunWith(SpringRunner::class)
class RateSynchronizerTest {

    @MockBean
    lateinit var currencyServiceClient: CurrencyServiceClient

    @MockBean
    lateinit var coinMarketCapClient: CoinMarketCapClient

    @Test
    fun whenCoinMarketCapClientReturnsListOfRates_thenCurrencyServiceClientShouldBeInvokedWithListOfCurrencies() {

        val coinMarketCapRateBTC = CoinMarketCapRate(
                "bitcoin",
                "Bitcoin",
                "BTC",
                "1",
                "573.137",
                "1.0",
                "72855700.0",
                "9080883500.0",
                "15844176.0",
                "15844176.0",
                "15844176.0",
                "0.04",
                "-0.3",
                "-0.57",
                "1472762067"
        )

        Mockito.`when`(coinMarketCapClient.getAllRates()).thenReturn(Arrays.asList(coinMarketCapRateBTC))

        RateSynchronizer(currencyServiceClient, coinMarketCapClient).synchronize()

        `verify`(currencyServiceClient, times(1)).saveRates(Matchers.anyCollectionOf(Currency::class.java))
    }
}