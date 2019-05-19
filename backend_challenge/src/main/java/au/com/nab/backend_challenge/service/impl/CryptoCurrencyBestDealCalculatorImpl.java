package au.com.nab.backend_challenge.service.impl;

import au.com.nab.backend_challenge.model.CryptoCurrencyDeal;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;
import au.com.nab.backend_challenge.service.CryptoCurrencyBestDealCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyBestDealCalculatorImpl implements CryptoCurrencyBestDealCalculator {

    @Override
    public CryptoCurrencyDeal getBestDeal(String cryptoCurrencyName, List<CryptoCurrencyPrice> cryptoCurrencyPrices) {

        double profit = 0;
        double sellPrice = 0;
        String sellTime = null;

        double buyPrice = 0;
        String buyTime = null;

        for (int i = cryptoCurrencyPrices.size() - 1; i >= 0; i--) {
            if (Double.parseDouble(cryptoCurrencyPrices.get(i).getPrice()) > sellPrice) {
                sellPrice = Double.parseDouble(cryptoCurrencyPrices.get(i).getPrice());
                sellTime = cryptoCurrencyPrices.get(i).getTime();
            } else if (sellPrice - Double.parseDouble(cryptoCurrencyPrices.get(i).getPrice()) > profit) {
                profit = sellPrice - Double.parseDouble(cryptoCurrencyPrices.get(i).getPrice());
                buyPrice = Double.parseDouble(cryptoCurrencyPrices.get(i).getPrice());
                buyTime = cryptoCurrencyPrices.get(i).getTime();
            }
        }

        return new CryptoCurrencyDeal(cryptoCurrencyName, new CryptoCurrencyPrice(Double.toString(buyPrice), buyTime),
                new CryptoCurrencyPrice(Double.toString(sellPrice), sellTime), String.format("%.2f", profit));
    }
}
