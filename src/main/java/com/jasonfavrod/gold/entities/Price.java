package com.jasonfavrod.gold.entities;

import java.sql.Timestamp;
import java.time.Instant;

public class Price {
    private int id;
    private float price;
    private Timestamp sampleDate;
    private String source;

    public Price() {
        this.id = -1;
        this.price = -1;
        this.sampleDate = Timestamp.from(Instant.ofEpochMilli(0));
        this.source = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Timestamp sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
