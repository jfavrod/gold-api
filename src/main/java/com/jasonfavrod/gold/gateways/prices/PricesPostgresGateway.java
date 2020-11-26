package com.jasonfavrod.gold.gateways.prices;

import com.jasonfavrod.gold.entities.Price;
import com.jasonfavrod.gold.gateways.PostgresGateway;
import org.springframework.lang.NonNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PricesPostgresGateway extends PostgresGateway implements PricesGateway {
    private final String baseSelect = "SELECT id,price,sample_date,source FROM prices";

    public PricesPostgresGateway() {}

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
        List<Price> prices = new ArrayList<>();
        Connection conn;
        Statement stmt;
        ResultSet result;

        try {
            conn = this.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery(baseSelect);
            prices = PricesPostgresGateway.rowsToPriceList(result);
            conn.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return prices;
    }

    public List<Price> find(boolean last) {
        Connection conn;
        List<Price> prices = new ArrayList<>();
        ResultSet result;
        String sql = baseSelect;
        Statement stmt;

        try {
            sql += " WHERE sample_date = (SELECT MAX(sample_date) FROM prices)";
            conn = this.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            prices = PricesPostgresGateway.rowsToPriceList(result);
            conn.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return prices;
    }

    public List<Price> find(Date start) {
        Connection conn;
        List<Price> prices = new ArrayList<>();
        ResultSet result;
        PreparedStatement stmt;
        String sql = baseSelect;

        try {
            sql += " WHERE sample_date >= ?" +
                "ORDER BY sample_date ASC";
            conn = this.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, start);
            result = stmt.executeQuery();
            prices = PricesPostgresGateway.rowsToPriceList(result);

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return prices;
    }

    public List<Price> find(Date start, Date end) {
        Connection conn;
        List<Price> prices = new ArrayList<>();
        ResultSet result;
        PreparedStatement stmt;
        String sql = baseSelect;

        try {
            sql += " WHERE sample_date >= ?" +
                " AND sample_date <= ?" +
                " ORDER BY sample_date ASC";

            conn = this.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, start);
            stmt.setDate(2, end);
            result = stmt.executeQuery();
            prices = PricesPostgresGateway.rowsToPriceList(result);

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return prices;
    }

    @Override
    public boolean insert(Price price) {
        Connection conn;
        PreparedStatement stmt;
        String sql = "INSERT INTO prices (price, sample_date, source) VALUES (?, ?, ?);";

        try{
            conn = this.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, price.getPrice());
            stmt.setTimestamp(2, price.getSampleDate());
            stmt.setString(3, price.getSource());
            return (stmt.executeUpdate() > 0);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Price entity) {
        return 0;
    }

    @NonNull
    private static List rowsToPriceList(ResultSet rows) throws SQLException {
        Price price;
        ArrayList<Price> prices = new ArrayList<>();

        while (rows.next()) {
            price = new Price();
            price.setId(rows.getInt(1));
            price.setPrice(rows.getFloat(2));
            price.setSampleDate(rows.getTimestamp(3));
            price.setSource(rows.getString(4));
            prices.add(price);
        }

        return prices;
    }
}
