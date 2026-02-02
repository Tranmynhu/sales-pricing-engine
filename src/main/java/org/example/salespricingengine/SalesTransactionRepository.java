package org.example.salespricingengine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesTransactionRepository {
//    public static void main(String[] args) {
//        SalesTransactionRepository obj = new SalesTransactionRepository();
//        obj.create("01/20/2026", "John", "Jang", "Dell Laptop", 1300, 10, 10, 50, 10, 100, 200, 80, 60, "07/20/2026", 1800);
//    }


    public void create(String createdDate, String customerName, String salespersonName,
                       String itemDescription, double listPrice, double discountRate, double salesTaxRate,
                       double shippingFee, double commissionRate, double totalDiscount, double netPrice,
                       double salesTax, double commissionFee, String expirationDate, double totalPrice) {
        try {
            Connection connection = DatasourceConfiguration.getConnection();
            String sql = "insert into sales_transaction (created_date, customer_name, salesperson_name, item_description, list_price, discount_rate,"
                    + "sales_tax_rate, shipping_fee, commission_rate, total_discount, net_price, sales_tax, commission_fee, expiration_date, total_price) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, createdDate);
            preparedStatement.setString(2, customerName);
            preparedStatement.setString(3, salespersonName);
            preparedStatement.setString(4, itemDescription);
            preparedStatement.setDouble(5, listPrice);
            preparedStatement.setDouble(6, discountRate);
            preparedStatement.setDouble(7, salesTaxRate);
            preparedStatement.setDouble(8, shippingFee);
            preparedStatement.setDouble(9, commissionRate);
            preparedStatement.setDouble(10, totalDiscount);
            preparedStatement.setDouble(11, netPrice);
            preparedStatement.setDouble(12, salesTax);
            preparedStatement.setDouble(13, commissionFee);
            preparedStatement.setString(14, expirationDate);
            preparedStatement.setDouble(15, totalPrice);


            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
