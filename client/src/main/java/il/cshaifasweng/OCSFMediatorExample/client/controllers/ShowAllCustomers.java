package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllCustomers;

public class ShowAllCustomers implements Initializable {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, Double> customersBudget;

    @FXML
    private TableColumn<Customer, String> customersEmail;

    @FXML
    private TableColumn<Customer, String> customersFirstName;

    @FXML
    private TableColumn<Customer, String> customersID;

    @FXML
    private TableColumn<Customer, String> customersLastName;

    @FXML
    void backToHomePage(ActionEvent event) throws IOException {
        App.setRoot("AdministratorHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Customer> customers = null;

        try {
            customers = getAllCustomers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(customers == null)
            return;

        for(int i=0;i<customers.size();i++) {
            customersID.setCellValueFactory(new PropertyValueFactory<Customer, String>
                    (customers.get(i).getUser_id()));
            customersFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>
                    (customers.get(i).getFirst_name()));
            customersLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>
                    (customers.get(i).getLast_name()));

            // TODO: add email

            customersBudget.setCellValueFactory(new PropertyValueFactory<Customer, Double>
                    (Double.toString(customers.get(i).getBudget())));
        }
    }
}