package au.com.nab.backend_challenge.service;

import au.com.nab.backend_challenge.model.CryptoCurrencyDeal;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;

import java.util.List;

public interface CryptoCurrencyBestDealCalculator {

    CryptoCurrencyDeal getBestDeal(String cryptoCurrencyName, List<CryptoCurrencyPrice> cryptoCurrencyPrices);

}
