package au.com.nab.backend_challenge;


import au.com.nab.backend_challenge.exception.NoTradesFoundException;
import au.com.nab.backend_challenge.model.CryptoCurrencyDeal;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;
import au.com.nab.backend_challenge.service.CryptoCurrencyBestDealCalculator;
import au.com.nab.backend_challenge.service.CryptoCurrencyPricesFetcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptoCurrencyBestDealCalculatorImplTest {

    @Autowired
    private CryptoCurrencyBestDealCalculator cryptoCurrencyBestDealCalculator;

    @Autowired
    private CryptoCurrencyPricesFetcher cryptoCurrencyPricesFetcher;

    @Test
    public void checkBestDeal() throws NoTradesFoundException {
        List<CryptoCurrencyPrice> cryptoCurrencyPrices = cryptoCurrencyPricesFetcher.fetchPrices("BTC", "20180507");
        CryptoCurrencyDeal bestDeal = cryptoCurrencyBestDealCalculator.getBestDeal("BTC", cryptoCurrencyPrices);

        Assert.assertEquals(bestDeal.getBuyPrice().getPrice(), "34.98");
    }
}
