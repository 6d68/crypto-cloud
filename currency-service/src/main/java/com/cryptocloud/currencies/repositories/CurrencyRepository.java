package com.cryptocloud.currencies.repositories;

import com.cryptocloud.currencies.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<Currency, String> {
}
