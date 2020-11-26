package com.jasonfavrod.gold.entities;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.sql.Timestamp;

public class GoldPriceZ {
    @JsonAlias("ounce_price_usd")
    private float ouncePriceUsd;
    @JsonAlias("gmt_ounce_price_usd_updated")
    private Timestamp gmtOuncePriceUsdUpdated;
    @JsonAlias("ounce_price_ask")
    private float ouncePriceAsk;
    @JsonAlias("ounce_price_bid")
    private float ouncePriceBid;
    @JsonAlias("ounce_price_usd_today_low")
    private float ouncePriceUsdTodayLow;
    @JsonAlias("ounce_price_usd_today_high")
    private float ouncePriceUsdTodayHigh;

    public GoldPriceZ() {
        this.ouncePriceAsk = 0;
        this.ouncePriceBid = 0;
        this.ouncePriceUsd = 0;
        this.ouncePriceUsdTodayHigh = 0;
        this.ouncePriceUsdTodayLow = 0;
        this.gmtOuncePriceUsdUpdated = null;
    }

    public float getOuncePriceUsd() {
        return ouncePriceUsd;
    }

    public void setOuncePriceUsd(float ouncePriceUsd) {
        this.ouncePriceUsd = ouncePriceUsd;
    }

    public Timestamp getGmtOuncePriceUsdUpdated() {
        return gmtOuncePriceUsdUpdated;
    }

    public void setGmtOuncePriceUsdUpdated(String gmtOuncePriceUsdUpdated) {
        String dateString = "";
        int hrsAdjust = -4;
        int date = 0, dateDay = 0, hrs = 0;
        int time = 1, dateMonth = 1, mins = 1;
        int tod = 2, dateYear = 2, secs = 2;

        String parts[] = gmtOuncePriceUsdUpdated.split("\\s");
        String dateParts[] = parts[date].split("-");
        String timeParts[] = parts[time].split(":");

        dateString = dateParts[dateYear] + "-";
        dateString += dateParts[dateMonth] + "-";
        dateString += dateParts[dateDay] + " ";

        if (parts[tod].toLowerCase().equals("pm")) {
            timeParts[hrs] = Integer.valueOf(Integer.valueOf(timeParts[hrs]) + 12 + hrsAdjust).toString();
        }
        else {
            timeParts[hrs] = Integer.valueOf(Integer.valueOf(timeParts[hrs]) + hrsAdjust).toString();
        }

        if (Integer.valueOf(timeParts[hrs]) < 10) {
            dateString += "0" + timeParts[hrs] + ":";
        }
        else {
            dateString += timeParts[hrs] + ":";
        }

        dateString += timeParts[mins] + ":" + timeParts[secs];
        this.gmtOuncePriceUsdUpdated = Timestamp.valueOf(dateString);
    }

    public float getOuncePriceAsk() {
        return ouncePriceAsk;
    }

    public void setOuncePriceAsk(float ouncePriceAsk) {
        this.ouncePriceAsk = ouncePriceAsk;
    }

    public float getOuncePriceBid() {
        return ouncePriceBid;
    }

    public void setOuncePriceBid(float ouncePriceBid) {
        this.ouncePriceBid = ouncePriceBid;
    }

    public float getOuncePriceUsdTodayLow() {
        return ouncePriceUsdTodayLow;
    }

    public void setOuncePriceUsdTodayLow(float ouncePriceUsdTodayLow) {
        this.ouncePriceUsdTodayLow = ouncePriceUsdTodayLow;
    }

    public float getOuncePriceUsdTodayHigh() {
        return ouncePriceUsdTodayHigh;
    }

    public void setOuncePriceUsdTodayHigh(float ouncePriceUsdTodayHigh) {
        this.ouncePriceUsdTodayHigh = ouncePriceUsdTodayHigh;
    }

    public Price toPrice() {
        Price price = new Price();
        price.setPrice(this.ouncePriceUsd);
        price.setSampleDate(this.gmtOuncePriceUsdUpdated);
        return price;
    }
}
