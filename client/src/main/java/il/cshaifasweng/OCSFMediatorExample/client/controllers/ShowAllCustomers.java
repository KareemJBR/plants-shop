package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ArrayList<Customer> customers = null;

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
        App.setRoot("controllers/AdministratorHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> customersToShow = null;
        try {
            customers = getAllCustomers();
            customersToShow = FXCollections.observableArrayList(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (customersToShow == null)
            return;

        customersID.setCellValueFactory(new PropertyValueFactory<>("id"));
        customersFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        customersLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        customersEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        customersBudget.setCellValueFactory(new PropertyValueFactory<>("Budget"));

        customersTable.setItems(customersToShow);

    }

    @FXML
    public void rowClicked(javafx.scene.input.MouseEvent event) throws IOException {
        if(event.getClickCount() != 2)
            return;

        int index;
        String customer_id;

        try {

            index = customersTable.getSelectionModel().selectedIndexProperty().get();
            customer_id = customers.get(index).getUser_id();
        } catch (Exception e) {
            return;
            // double-clicked the table but not a row
        }

        // else: a row has been clicked twice, and we need to open a new controller for editing the selected
        // customer's data

        App.setCustomerIDForAdminView(customer_id);
        App.setRoot("controllers/CustomerDetailsToEdit");

    }
}