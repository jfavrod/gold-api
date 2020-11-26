package com.jasonfavrod.gold.gateways.prices;

import com.jasonfavrod.gold.entities.Price;
import com.jasonfavrod.gold.gateways.TableDataGateway;

import java.sql.Date;
import java.util.List;

public interface PricesGateway extends TableDataGateway<Price> {
    public List<Price> find(boolean last);
    public List<Price> find(Date start);
    public List<Price> find(Date start, Date end);
}
