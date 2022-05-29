package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.shape.Line;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.util.ArrayList;


import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllClientOrders;
import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllOrders;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;

public class ClientOrders {

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ordersList"
    private AnchorPane ordersList; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void initialize() throws IOException {
        ArrayList<Order> orders=getAllClientOrders(LoginClient_userId);
        loadorderlist();
        if(orders!=null)
        {
            if(orders.size()!=0)
            {
                for(int i=0;i<orders.size();i++)
                {
                    /////////////
                    Pane pane=new Pane();
                    //////////// order date ////////////
                  //  TextField date=new TextField(orders.get(i).get);
//                    date.setStyle("-fx-background-color:none");
//                    date.setLayoutX(28);
//                    date.setLayoutY(8);
//                    pane.getChildren().add(date);
                }
            }
            else
            {
                showAlert("no","no");
            }
        }

    }

    public void loadorderlist()
    {
        Pane pane=new Pane();
//                    pane.setStyle("-fx-border-color:red;");
        pane.setMinWidth(613);

        //////////// order date ////////////
        TextField date=new TextField("Date");
        date.setStyle("-fx-background-color:none");
        date.setLayoutX(28);
        date.setLayoutY(8);
        pane.getChildren().add(date);

        //////////// receipt date ////////////
        TextField receiptdate=new TextField("Receipt Date");
        receiptdate.setStyle("-fx-background-color:none");
        receiptdate.setLayoutX(108);
        receiptdate.setLayoutY(8);
        pane.getChildren().add(receiptdate);

        //////////// Ship address ////////////
        TextField shippingaddress=new TextField("Ship To");
        shippingaddress.setStyle("-fx-background-color:none");
        shippingaddress.setLayoutX(238);
        shippingaddress.setLayoutY(8);
        pane.getChildren().add(shippingaddress);

        //////////// Order price ////////////
        TextField price=new TextField("Order Total");
        price.setStyle("-fx-background-color:none");
        price.setLayoutX(328);
        price.setLayoutY(8);
        pane.getChildren().add(price);

        //////////// Order status ////////////
        TextField status=new TextField("Status");
        status.setStyle("-fx-background-color:none");
        status.setLayoutX(428);
        status.setLayoutY(8);
        pane.getChildren().add(status);

        //////////// Action ////////////
        TextField action=new TextField("Action");
        action.setStyle("-fx-background-color:none");
        action.setLayoutX(528);
        action.setLayoutY(8);
        pane.getChildren().add(action);

        Line line = new Line(608,0,0,0);
        line.setLayoutY(40);
        line.setLayoutX(4);
        line.setDisable(true);
        pane.getChildren().add(line);

        ordersList.getChildren().add(pane);
    }

    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.showAndWait();
            }
        });
    }

}
