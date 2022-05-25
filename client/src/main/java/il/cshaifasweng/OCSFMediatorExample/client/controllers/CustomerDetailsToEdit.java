package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCustomers;

public class CustomerDetailsToEdit {

    private Customer base_customer;

    @FXML
    private TextField customerAccountTypeTextField;

    @FXML
    private TextField customerEmailTextField;

    @FXML
    private TextField customerFirstNameTextField;

    @FXML
    private TextField customerIDTextField;

    @FXML
    private TextField customerLastNameTextField;

    @FXML
    private TextField customerPasswordTextField;

    @FXML
    private TextField customerUsernameTextField;

    @FXML
    void backToCustomersView(ActionEvent event) throws IOException {
        App.setRoot("controllers/ShowAllCustomers");

    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }

    @FXML
    void resetButtonClicked(ActionEvent event) {
        fill_with_base_customer();
    }

    @FXML
    void saveChanges(ActionEvent event) {
        String update_customer_query = "update customers set customer_first_name=" +
                customerFirstNameTextField.getText() + ", customer_last_name=" + customerLastNameTextField.getText() +
                ", customer_user_name=" + customerUsernameTextField.getText() + ", customer_password=" +
                customerPasswordTextField.getText() + ", account_type=" + customerAccountTypeTextField.getText() +
                ", customer_emil=" + customerEmailTextField.getText() + " where cutomer_id=" +
                customerIDTextField.getText();

        Customer new_cutomer = new Customer(customerIDTextField.getId(), customerFirstNameTextField.getText(),
                customerLastNameTextField.getText(), customerUsernameTextField.getText(),
                customerPasswordTextField.getText(), base_customer.getCr)

        update_customer()

    }

    public void start_controller(String customer_id) throws IOException {
        ArrayList<Customer> customers = getAllCustomers();

        for (Customer customer : customers) {
            if (customer.getUser_id().equals(customer_id)) {
                base_customer = new Customer(customer.getUser_id(), customer.getFirst_name(), customer.getLast_name(),
                        customer.getUser_name(), customer.getPassword(), null, customer.getAcount_type(),
                        customer.getEmail());

                fill_with_base_customer();
                return;
            }
        }
    }

    private void fill_with_base_customer() {
        customerIDTextField.textProperty().set(base_customer.getUser_id());
        customerIDTextField.disableProperty().set(true);

        customerFirstNameTextField.textProperty().set(base_customer.getFirst_name());
        customerLastNameTextField.textProperty().set(base_customer.getLast_name());
        customerEmailTextField.textProperty().set(base_customer.getEmail());
        customerUsernameTextField.textProperty().set(base_customer.getUser_name());
        customerPasswordTextField.textProperty().set(base_customer.getPassword());
        customerAccountTypeTextField.textProperty().set(base_customer.getAcount_type());
    }

}
