package org.example.salespricingengine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelloController implements Initializable {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
    static Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Label todayDate;

    @FXML
    private TextField discountRateTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date now = new Date();
        todayDate.setText(simpleDateFormat.format(now));

        discountRateTextField.setText("10");
        salesTaxRateTextField.setText("10");
        shippingFeeTextField.setText("50");
        commissionRateTextField.setText("10");
    }

    @FXML
    private TextField customerNameTextField;

    @FXML
    private TextField salespersonNameTextField;

    @FXML
    private TextField itemDescriptionTextField;

    @FXML
    private TextField listPriceTextField;

    @FXML
    private TextField salesTaxRateTextField;

    @FXML
    private TextField shippingFeeTextField;

    @FXML
    private TextField commissionRateTextField;

    @FXML
    private Label totalDiscountLabel;

    @FXML
    private Label netPriceLabel;

    @FXML
    private Label salesTaxLabel;

    @FXML
    private Label shippingFeeLabel;

    @FXML
    private Label commissionFeeLabel;

    @FXML
    private Label expirationDateLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private void onCalculateButtonClick() {
//        List<TextField> textFields = new ArrayList<>();
//        textFields.add(customerNameTextField);
//        textFields.add(salespersonNameTextField);
//        textFields.add(itemDescriptionTextField);
//        textFields.add(listPriceTextField);
//        textFields.add(discountRateTextField);
//        textFields.add(salesTaxRateTextField);
//        textFields.add(shippingFeeTextField);
//        textFields.add(commissionRateTextField);

        List<TextField> textFields = Arrays.asList(customerNameTextField, salespersonNameTextField, itemDescriptionTextField, listPriceTextField
                , discountRateTextField, salesTaxRateTextField, shippingFeeTextField, commissionRateTextField);

//        TextField[] textFields = {customerNameTextField, salespersonNameTextField, itemDescriptionTextField, listPriceTextField
//                , discountRateTextField, salesTaxRateTextField, shippingFeeTextField, commissionRateTextField};

        TextField blankTextField = null;
        for (TextField textField : textFields) {
            if (textField.getText().isBlank()) {
                blankTextField = textField;
                break;
            }
        }

        if (blankTextField != null) {
            showMissingFieldAlert(blankTextField);
            return;
        }


//        if (customerNameTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Customer's Name", customerNameTextField);
//            return;
//        }
//
//        if (salespersonNameTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Salesperson's Name", salespersonNameTextField);
//            return;
//        }
//
//        if (itemDescriptionTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Item Description", itemDescriptionTextField);
//            return;
//        }
//
//        if (listPriceTextField.getText().isBlank()) {
//            // Pop up alert
//            showMissingFieldAlert("List Price", listPriceTextField);
//            return;
//        }
//
//        if (discountRateTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Discount Rate", discountRateTextField);
//            return;
//        }
//
//        if (salesTaxRateTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Sales Tax Rate", salesTaxRateTextField);
//            return;
//        }
//
//        if (shippingFeeTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Shipping Fee", shippingFeeTextField);
//            return;
//        }
//
//        if (commissionRateTextField.getText().isBlank()) {
//            //Pop up alert
//            showMissingFieldAlert("Commission Rate", commissionRateTextField);
//            return;
//        }

        try {
            // Convert listPriceTextField to double
            double listPrice = Double.parseDouble(listPriceTextField.getText());

            // Convert discountRateTextField to double
            double discountRate = Double.parseDouble(discountRateTextField.getText());

            // Convert salesTaxRateTextField to double
            double salesTaxRate = Double.parseDouble(salesTaxRateTextField.getText());

            // Convert shippingFeeTextField to double
            double shippingFee = Double.parseDouble(shippingFeeTextField.getText());

            // Convert commissionRateTextField to double
            double commissionRate = Double.parseDouble(commissionRateTextField.getText());

            // Calculate totalDiscount
            double totalDiscount = listPrice * discountRate / 100;

            // Calculate netPrice
            double netPrice = listPrice - totalDiscount;

            // Calculate salesTax
            double salesTax = listPrice * salesTaxRate / 100;

            // Calculate commissionFee
            double commissionFee = listPrice * commissionRate / 100;

            // Calculate total Price
            double totalPrice = netPrice + salesTax + shippingFee + commissionFee;

            // Expiration Date
            // Calendar object that defaults to "now"
            Calendar cal = Calendar.getInstance();

            // Add 7 days to the current date
            cal.add(Calendar.DATE, 7);

            // Obtain next week's date
            Date nextWeek = cal.getTime();

            // Display everything
            totalDiscountLabel.setText(" " + currencyFormatter.format(totalDiscount));
            netPriceLabel.setText(" " + currencyFormatter.format(netPrice));
            salesTaxLabel.setText(" " + currencyFormatter.format(salesTax));
            shippingFeeLabel.setText(" " + currencyFormatter.format(shippingFee));
            commissionFeeLabel.setText(" " + currencyFormatter.format(commissionFee));
            totalPriceLabel.setText(" " + currencyFormatter.format(totalPrice));
            expirationDateLabel.setText(" " + simpleDateFormat.format(nextWeek));

            // save to database
            SalesTransactionRepository repository = new SalesTransactionRepository();
            //repository.create();

        } catch (NumberFormatException e) {
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a Numeric Value Only. Example: 1000 or 1000.00");
            alert.show();
        }
    }

    private void showMissingFieldAlert(String fieldName, TextField textField) {
        alert.setTitle("Missing Required Field");
        alert.setHeaderText(null);
        alert.setContentText("Please Enter the " + fieldName);
        textField.requestFocus();
        alert.show();
    }

    private void showMissingFieldAlert(TextField textField) {
        alert.setTitle("Missing Required Field");
        alert.setHeaderText(null);
        alert.setContentText("Please Fill In This Field");
        textField.requestFocus();
        alert.show();
    }

    @FXML
    private void onClearButtonClick() {
        customerNameTextField.setText(" ");
        salespersonNameTextField.setText(" ");
        itemDescriptionTextField.setText(" ");
        listPriceTextField.setText(" ");
        errorLabel.setText(" ");

        totalDiscountLabel.setText("-");
        netPriceLabel.setText("-");
        salesTaxLabel.setText("-");
        shippingFeeLabel.setText("-");
        commissionFeeLabel.setText("-");
        totalPriceLabel.setText("-");
        expirationDateLabel.setText("-");

        discountRateTextField.setText("10");
        salesTaxRateTextField.setText("10");
        shippingFeeTextField.setText("50");
        commissionRateTextField.setText("10");

    }
}
