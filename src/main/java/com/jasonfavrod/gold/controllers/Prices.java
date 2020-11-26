package com.jasonfavrod.gold.controllers;

import com.jasonfavrod.gold.entities.Price;
import com.jasonfavrod.gold.entities.Response;
import com.jasonfavrod.gold.gateways.prices.PricesGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class Prices {
    @Autowired PricesGateway gateway;
    @Autowired Logger logger;

    @GetMapping("/prices")
    @ResponseBody
    public Response getPrices(
            @RequestParam(required = false) String last,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String start
    ) {
        Date startDate = null;
        Date endDate = null;
        List<Price> prices = new ArrayList<Price>();
        Response res = new Response();

        try {
            if (start != null && end != null) {
                startDate = Date.valueOf(start);
                endDate = Date.valueOf(end);
                prices = this.gateway.find(startDate, endDate);
            } else if (start != null) {
                startDate = Date.valueOf(start);
                prices = this.gateway.find(startDate);
            }
            else if (last != null) {
                prices = this.gateway.find(true);
            }
            else {
                prices = this.gateway.find();
            }

            res.setCode(200);
            res.setData(prices);
            res.setError(false);

            if (prices.size() == 0) {
                String mesg = "";
                mesg = (startDate != null) ? "No prices from start date: " + startDate : "No price data available.";
            }
            else {
                res.setMessage("");
            }
        }
        catch (Exception ex) {
            res.setMessage(ex.getMessage());
            logger.warning(this.getClass().getSimpleName() + ".getPrices(): " + ex.getMessage());
        }

        return res;
    }
}
