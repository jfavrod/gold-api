package com.jasonfavrod.gold.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jasonfavrod.gold.entities.GoldPriceZ;
import com.jasonfavrod.gold.entities.Price;
import com.jasonfavrod.gold.gateways.prices.PricesGateway;
import com.jasonfavrod.gold.services.environment.ApplicationEnv;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class FetchPrice {
    @Autowired Environment env;
    @Autowired ApplicationEnv appEnv;
    @Autowired private PricesGateway prices;

    // @Scheduled(cron = "0 0 11 * * 0-5")
    @Scheduled(fixedRate = (2 * 60 * 1000))
    public void updatePrices() {
        Price price = fetchGoldPrice();

        if (price != null) {
            if (prices.insert(price)) {
                System.out.println("inserted price");
            }
            else {
                System.out.println("failed to insert price");
            }
        }
    }

    private Price fetchGoldPrice() {
        String apikey = "";
        ObjectMapper mapper = new ObjectMapper();
        Price price = null;
        String responseJson = "";
        String responseString = "";

        try {
            apikey = appEnv.resolveEnvVar(env.getProperty("upstream_api.key"));
            URL url = new URL(env.getProperty("upstream_api.url"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("X-API-KEY", apikey);
            InputStream responseStream = connection.getInputStream();

            for (int c = responseStream.read() ; c != -1; c = responseStream.read()) {
                responseString += (char)c;
            }

            responseJson = Jsoup.parse(
                responseString.replaceAll("^\\s+", "")
            ).text().replaceFirst("0", "");

            GoldPriceZ goldPriceZ = mapper.readValue(responseJson, GoldPriceZ.class);
            price = goldPriceZ.toPrice();
            price.setSource(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return price;
    }
}
