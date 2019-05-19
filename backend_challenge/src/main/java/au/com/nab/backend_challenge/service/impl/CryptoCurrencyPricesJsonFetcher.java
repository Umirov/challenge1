package au.com.nab.backend_challenge.service.impl;

import au.com.nab.backend_challenge.exception.NoTradesFoundException;
import au.com.nab.backend_challenge.model.CryptoCurrencyPrice;
import au.com.nab.backend_challenge.service.CryptoCurrencyPricesFetcher;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CryptoCurrencyPricesJsonFetcher implements CryptoCurrencyPricesFetcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoCurrencyPricesJsonFetcher.class);

    private ResourceLoader resourceLoader;

    @Autowired
    public CryptoCurrencyPricesJsonFetcher(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<CryptoCurrencyPrice> fetchPrices(String cryptoCurrencyName, String date) throws NoTradesFoundException {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + date + ".json");
            if (!resource.exists()) {
                LOGGER.error("file for given date was not found " + date);
                throw new NoTradesFoundException("No trades were found for given date");
            }
            String jsonString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            JsonElement jsonElement = new JsonParser().parse(jsonString);

            for (JsonElement currentCurrency : jsonElement.getAsJsonArray()) {
                if (cryptoCurrencyName.equalsIgnoreCase(currentCurrency.getAsJsonObject().get("currency").getAsJsonPrimitive().getAsString())) {
                    JsonElement quotes = currentCurrency.getAsJsonObject().get("quotes");
                    return Arrays.asList(new Gson().fromJson(quotes, CryptoCurrencyPrice[].class));
                }
            }
            LOGGER.error("non existing cryptocurrency");
            throw new NoTradesFoundException("No trades were found for given cryptocurrency");
        } catch (IOException e) {
            LOGGER.error("error while file fetching", e);
        }
        return Collections.emptyList();
    }
}
