package au.com.nab.backend_challenge.service;

import au.com.nab.backend_challenge.exception.NoTradesFoundException;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;

import java.util.List;

public interface CryptoCurrencyPricesFetcher {

    List<CryptoCurrencyPrice> fetchPrices(String cryptoCurrencyName, String date) throws NoTradesFoundException;
}
