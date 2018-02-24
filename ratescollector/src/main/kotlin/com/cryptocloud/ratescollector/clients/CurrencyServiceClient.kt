package com.cryptocloud.ratescollector.clients

import com.cryptocloud.ratescollector.model.Currency
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.RequestBody


@FeignClient("currencyservice")
interface CurrencyServiceClient {
    @RequestMapping(method = [(RequestMethod.POST)], value = ["/currencies"], consumes = ["application/json"])
    open fun saveRates(@RequestBody rates: Iterable<Currency>): Iterable<Currency>
}
