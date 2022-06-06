package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddSale implements Initializable {

    private ArrayList<Item> items=null;

    @FXML
    private TableColumn<Item, String> CatalogNumber;

    @FXML
    private TableColumn<Item, String> underSale;

    @FXML
    private TableColumn<Item, String> color;

    @FXML
    private TableColumn<Item, String> finalPrice;

    @FXML
    private TableColumn<Item, String> ItemName;

    @FXML
    private TableColumn<Item, String> itemId;

    @FXML
    private TableColumn<Item, String> salePercante;
    @FXML
    private TableColumn<Item, String> originalPrice;
    @FXML
    private TableColumn<Item, String> type;


    @FXML
    private TableView<Item> itemsTable;

    @FXML
    void rowClicked(MouseEvent event) throws IOException {
        if(event.getClickCount() != 2)
            return;

        int index;
        Item item_id;

        try {

            index = itemsTable.getSelectionModel().selectedIndexProperty().get();
            item_id = items.get(index);
        } catch (Exception e) {
            return;
            // double-clicked the table but not a row
        }

        // else: a row has been clicked twice, and we need to open a new controller for editing the selected
        // customer's data
        App.setEdit_item(item_id);
        App.setRoot("controllers/ItemToEdit");
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {

        App.setRoot("controllers/NetWorkerHomePage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Item> customersToShow = null;
        try {
            items = App.getAllItems();
            customersToShow = FXCollections.observableArrayList(items);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (customersToShow == null)
            return;

        itemId.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("id"));
        underSale.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("underSale"));
        finalPrice.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("price"));

        salePercante.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("salePercent"));

        ItemName.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("name"));

        originalPrice.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("Original_price"));

        type.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("type"));
        CatalogNumber.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("catalogNumber"));
        color.setCellValueFactory(new PropertyValueFactory<Item, String>
                ("color"));

        itemsTable.setItems(customersToShow);
    }
}
