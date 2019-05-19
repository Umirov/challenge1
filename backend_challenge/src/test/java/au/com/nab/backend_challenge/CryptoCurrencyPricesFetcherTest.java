package au.com.nab.backend_challenge;

import au.com.nab.backend_challenge.exception.NoTradesFoundException;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;
import au.com.nab.backend_challenge.service.CryptoCurrencyPricesFetcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptoCurrencyPricesFetcherTest {

    @Autowired
    private CryptoCurrencyPricesFetcher cryptoCurrencyPricesFetcher;

    @Test(expected = NoTradesFoundException.class)
    public void checkNonExistentCryptoCurrency() throws NoTradesFoundException {
        List<CryptoCurrencyPrice> cryptoCurrencyPrices = cryptoCurrencyPricesFetcher.fetchPrices("zxc", "20180507");

    }
}
