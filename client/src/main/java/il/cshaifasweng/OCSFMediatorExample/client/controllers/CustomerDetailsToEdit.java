package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;

public class CustomerDetailsToEdit implements Initializable {

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
    void deleteCustomer(ActionEvent event) throws IOException {
        App.deleteCustomer(base_customer);
        backToCustomersView(event);
    }

    @FXML
    void resetButtonClicked(ActionEvent event) {
        fill_with_base_customer();
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {

        Customer new_customer=null;

        ArrayList<Customer> customers=getAllCustomers();
        if(customers!=null)
        {
            for(Customer customer:customers)
            {
                if(customer.getUser_id().equals(customerIDTextField.getText()))
                {
                    customer.setFirst_name(customerFirstNameTextField.getText());
                    customer.setLast_name(customerLastNameTextField.getText());
                    customer.setEmail(customerEmailTextField.getText());
                    customer.setPassword(customerPasswordTextField.getText());
                    customer.setAccount_type(customerAccountTypeTextField.getText());
                    customer.setUser_name(customerUsernameTextField.getText());
                    new_customer=customer;
                    break;
                }
            }
        }

        updateCustomer(new_customer);
        base_customer =new_customer;
        fill_with_base_customer();
    }

    private void fill_with_base_customer() {
        customerIDTextField.textProperty().set(base_customer.getUser_id());

        customerFirstNameTextField.textProperty().set(base_customer.getFirst_name());
        customerLastNameTextField.textProperty().set(base_customer.getLast_name());
        customerEmailTextField.textProperty().set(base_customer.getEmail());
        customerUsernameTextField.textProperty().set(base_customer.getUser_name());
        customerPasswordTextField.textProperty().set(base_customer.getPassword());
        customerAccountTypeTextField.textProperty().set(base_customer.getAcount_type());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIDTextField.editableProperty().set(false);

        ArrayList<Customer> customers = null;
        String customer_id = App.getCustomer_id_for_admin_view();

        try {
            customers = getAllCustomers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert customers != null;
        for (Customer customer : customers) {
            if (customer.getUser_id().equals(customer_id)) {
                base_customer = customer;
                fill_with_base_customer();
                return;
            }
        }
    }
}
