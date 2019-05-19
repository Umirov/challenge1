package au.com.nab.backend_challenge.controller;

import au.com.nab.backend_challenge.exception.NoTradesFoundException;
import au.com.nab.backend_challenge.model.CryptoCurrencyDeal;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;
import au.com.nab.backend_challenge.service.CryptoCurrencyBestDealCalculator;
import au.com.nab.backend_challenge.service.CryptoCurrencyPricesFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CryptocoinsController {

    private CryptoCurrencyBestDealCalculator cryptoCurrencyBestDealCalculator;

    private CryptoCurrencyPricesFetcher cryptoCurrencyPricesFetcher;

    @Autowired
    public CryptocoinsController(CryptoCurrencyBestDealCalculator cryptoCurrencyBestDealCalculator, CryptoCurrencyPricesFetcher cryptoCurrencyPricesFetcher) {
        this.cryptoCurrencyBestDealCalculator = cryptoCurrencyBestDealCalculator;
        this.cryptoCurrencyPricesFetcher = cryptoCurrencyPricesFetcher;
    }

    @RequestMapping(method = GET, path = "/cryptodeal")
    public CryptoCurrencyDeal getDeal(@RequestParam(value = "cryptoCurrencyName") String cryptoCurrencyName, @RequestParam(value = "date") String date) {
        List<CryptoCurrencyPrice> cryptoCurrencyPrices;
        try {
            cryptoCurrencyPrices = cryptoCurrencyPricesFetcher.fetchPrices(cryptoCurrencyName, date);
        } catch (NoTradesFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return cryptoCurrencyBestDealCalculator.getBestDeal(cryptoCurrencyName, cryptoCurrencyPrices);
    }
}
