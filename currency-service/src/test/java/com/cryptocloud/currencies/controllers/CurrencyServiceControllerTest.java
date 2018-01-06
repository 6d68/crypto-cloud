package com.cryptocloud.currencies.controllers;

import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.services.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyServiceController.class)
public class CurrencyServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @Test
    public void givenCurrencies_whenGetCurrencies_thenReturnJsonArray() throws Exception {

        Currency eth = new Currency("ETH", "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);

        List<Currency> all = Collections.singletonList(eth);

        given(service.getCurrencies()).willReturn(all);

        mvc.perform(get("/currencies")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(eth.getName())));
    }
}