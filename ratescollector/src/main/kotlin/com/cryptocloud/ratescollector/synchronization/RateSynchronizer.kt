package com.cryptocloud.ratescollector.synchronization

import com.cryptocloud.ratescollector.clients.CoinMarketCapClient
import com.cryptocloud.ratescollector.model.CoinMarketCapRate
import com.cryptocloud.ratescollector.model.Currency
import com.cryptocloud.ratescollector.clients.CurrencyServiceClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId
import java.util.*

@Component
class RateSynchronizer(@Autowired private val currencyServiceClient: CurrencyServiceClient, @Autowired private val coinMarketCapClient: CoinMarketCapClient) {

    private val logger = LoggerFactory.getLogger(RateSynchronizer::class.java)

    @Scheduled(fixedRate = 180000)
    fun synchronize() {

        val rates:Iterable<CoinMarketCapRate> = coinMarketCapClient.getAllRates()
        val currencies: Iterable<Currency> = rates.map { toCurrency(it) }

        currencyServiceClient.saveRates(currencies)
        logger.info("Saved rates!")

    }

    private fun toCurrency(rate: CoinMarketCapRate): Currency {

        val unixTimeStamp: Long = rate.last_updated?.toLong() ?: Instant.now().epochSecond
        val localDate = Instant.ofEpochSecond(unixTimeStamp)

        return Currency(
                rate.id,
                rate.name,
                rate.symbol,
                Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()),
                rate.percent_change_1h?.toDoubleOrNull(),
                rate.price_usd?.toDoubleOrNull(),
                "USD",
                rate.percent_change_7d?.toDoubleOrNull(),
                rate.percent_change_24h?.toDoubleOrNull()
        )
    }
}