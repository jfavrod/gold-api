package com.jasonfavrod.gold.gateways.prices;

import com.jasonfavrod.gold.entities.Price;
import com.jasonfavrod.gold.gateways.MySqlGateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PricesMySqlGateway extends MySqlGateway implements PricesGateway {

    @Override
    public List<Price> find(boolean last) {
        return null;
    }

    @Override
    public List<Price> find(Date start) {
        return null;
    }

    @Override
    public List<Price> find(Date start, Date end) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int delete(Price entity) {
        return 0;
    }

    @Override
    public List<Price> find() {
        return null;
    }

    @Override
    public boolean insert(Price entity) {
        Connection conn;
        int rows = 0;
        String sql = "INSERT INTO prices (price, sample_date, source) VALUES (?, ?, ?)";
        PreparedStatement stmt;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setFloat(1, entity.getPrice());
            stmt.setTimestamp(2, entity.getSampleDate());
            stmt.setString(3, entity.getSource());

            rows = stmt.executeUpdate();
            conn.close();
        }
        catch (SQLException throwables) {
            logger.warning("Failed to insert: " + throwables.getMessage());
            throwables.printStackTrace();
        }
        finally {
            return rows > 0;
        }
    }

    @Override
    public int update(Price entity) {
        return 0;
    }
}
