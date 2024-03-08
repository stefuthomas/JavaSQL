package converter.dao;

import java.sql.*;
import converter.datasource.MariaDbConnection;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {
    public List<String> getCurrencyNames() {
        List<String> currencies = new ArrayList<>();
        try (Connection conn = MariaDbConnection.getConnection()) {
            String sql = "SELECT abbreviation FROM currency";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                currencies.add(rs.getString("abbreviation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    public Double getRate(String currency) {
        String sql = "SELECT conversion_rate FROM currency WHERE abbreviation = ?";
        Double rate = null;

        try (Connection conn = MariaDbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, currency);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rate = rs.getDouble("conversion_rate");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rate;
    }
}