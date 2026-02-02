package org.example.salespricingengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatasourceConfiguration {
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=sales_pricing_engine&useSSL=false&serverTimezone=UTC";
        String username = "postgres";
        String password = "Tmn_14112004";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connectivity successfully!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
