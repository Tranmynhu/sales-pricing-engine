module org.example.salespricingengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.salespricingengine to javafx.fxml;
    exports org.example.salespricingengine;
}