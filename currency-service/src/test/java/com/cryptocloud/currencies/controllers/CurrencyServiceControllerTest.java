package com.cryptocloud.currencies.controllers;

import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.services.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.Is.is;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyServiceController.class)
@EnableSpringDataWebSupport
public class CurrencyServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @Test
    public void givenCurrencies_whenGetCurrencies_thenReturnPageWithJsonArray() throws Exception {

        Currency eth = new Currency("ETH", "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);
        Currency etc = new Currency("ETC", "ETC", "ETC", Date.from(Instant.now()), 1.1, 90.32, "EUR", 1.5, 2.1);
        Currency btc = new Currency("BTC", "BTC", "BTC", Date.from(Instant.now()), 1.1, 9000.32, "EUR", 1.5, 2.1);
        List<Currency> all = Arrays.asList(eth, etc, btc);

        int page = 0, size = 20, total = 3;

        Pageable pageRequest = new PageRequest(page,size);
        Page<Currency> allCurrenciesPage = new PageImpl<>(all, pageRequest, total);

        given(service.pagedCurrencies(pageRequest)).willReturn(allCurrenciesPage);

        mvc.perform(get(String.format("/currencies?page=%s&size=%s", page, size))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(total)))
                .andExpect(jsonPath("$.content[*].name", hasItem(eth.getName())))
                .andExpect(jsonPath("$.content[*].name", hasItem(etc.getName())))
                .andExpect(jsonPath("$.content[*].name", hasItem(btc.getName())))
                .andExpect(jsonPath("$.size", is(size)));
    }
}