package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.shape.Line;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;

public class ClientOrders {


    @FXML // fx:id="ordersList"
    private AnchorPane ordersList; // Value injected by FXMLLoader

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/ClientMainPage");
    }

    @FXML
    void initialize() throws IOException {
       loadPage();
    }

    public void loadorderlist()
    {
        Pane pane=new Pane();
//                    pane.setStyle("-fx-border-color:red;");
        pane.setMinWidth(613);

        //////////// order date ////////////
        TextField date=new TextField("Date");
        date.setEditable(false);
        date.setStyle("-fx-background-color:none");
        date.setLayoutX(28);
        date.setLayoutY(8);
        pane.getChildren().add(date);

        //////////// receipt date ////////////
        TextField receiptdate=new TextField("Receipt Date");
        receiptdate.setEditable(false);
        receiptdate.setStyle("-fx-background-color:none");
        receiptdate.setLayoutX(108);
        receiptdate.setLayoutY(8);
        pane.getChildren().add(receiptdate);

        //////////// Ship address ////////////
        TextField shippingaddress=new TextField("Ship To");
        shippingaddress.setEditable(false);
        shippingaddress.setStyle("-fx-background-color:none");
        shippingaddress.setLayoutX(238);
        shippingaddress.setLayoutY(8);
        pane.getChildren().add(shippingaddress);

        //////////// Order price ////////////
        TextField price=new TextField("Order Total");
        price.setEditable(false);
        price.setStyle("-fx-background-color:none");
        price.setLayoutX(328);
        price.setLayoutY(8);
        pane.getChildren().add(price);

        //////////// Order status ////////////
        TextField status=new TextField("Status");
        status.setEditable(false);
        status.setStyle("-fx-background-color:none");
        status.setLayoutX(428);
        status.setLayoutY(8);
        pane.getChildren().add(status);

        //////////// Action ////////////
        TextField action=new TextField("Action");
        action.setEditable(false);
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

    public String orderStatus(Order order)
    {
        LocalTime receiptime=LocalTime.of(order.getReceipt_hour(),order.getReceipt_minute());
        LocalTime currentime=LocalTime.now();
        LocalDate receiptdate=LocalDate.of(order.getReceipt_year(),order.getReceipt_month(),order.getReceipt_day());
        LocalDate currentdate=LocalDate.now();

        if(order.isGot_cancelled())
        {
            return "cancelled";
        }
        if(receiptdate.isBefore(currentdate))
        {
            return "closed";
        }
        if(receiptdate.isAfter(currentdate))
        {
            return "complete";
        }
        if(receiptdate.equals(currentdate))
        {
            if(currentime.isAfter(receiptime))
            {
                return "closed";
            }
            if(currentime.until(receiptime,ChronoUnit.HOURS)<1)
            {
                return "complete, 0 refund";
            }
            if(currentime.until(receiptime,ChronoUnit.HOURS)>=1 && currentime.until(receiptime,ChronoUnit.HOURS)<=3)
            {
                return "complete, 50% refund";
            }
        }
        return "complete";
    }

    void loadPage() throws IOException {
        ArrayList<Order> orders=getAllClientOrders(LoginClient_userId);
        ordersList.getChildren().removeAll(ordersList.getChildren());
        loadorderlist();

        if(orders!=null)
        {
            if(orders.size()!=0)
            {
                ordersList.setMinHeight(orders.size()*75);
                for(int i=0;i<orders.size();i++)
                {
                    /////////////
                    Pane pane=new Pane();
                    pane.setMaxHeight(50);


                    ////////// order date ////////////
                    LocalDate date=LocalDate.of(orders.get(i).getOrder_year(),orders.get(i).getOrder_month(),orders.get(i).getOrder_day());
                    //  String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(date);
                    TextField orderdate=new TextField(date.toString());
                    orderdate.setEditable(false);
                    orderdate.setStyle("-fx-background-color:none");
                    orderdate.setLayoutX(8);
                    orderdate.setLayoutY(12.5);
                    pane.getChildren().add(orderdate);


                    ////////// order receiptdate ////////////
                    LocalDate date2=LocalDate.of(orders.get(i).getReceipt_year(),orders.get(i).getReceipt_month(),orders.get(i).getReceipt_day());
                    TextField receiptdate=new TextField(date2.toString());
                    receiptdate.setEditable(false);
                    receiptdate.setStyle("-fx-background-color:none");
                    receiptdate.setLayoutX(110);
                    receiptdate.setLayoutY(12.5);
                    pane.getChildren().add(receiptdate);

                    ////////// Ship to ////////////
                    TextField shipto=new TextField(orders.get(i).getShipping_address());
                    shipto.setEditable(false);
                    shipto.setMaxWidth(85);
                    shipto.setStyle("-fx-background-color:none");
                    shipto.setLayoutX(228);
                    shipto.setLayoutY(12.5);
                    pane.getChildren().add(shipto);

                    ////////// price  ////////////
                    TextField price=new TextField(Double.toString(orders.get(i).getPrice()));
                    price.setEditable(false);
                    price.setStyle("-fx-background-color:none");
                    price.setLayoutX(345);
                    price.setLayoutY(12.5);
                    pane.getChildren().add(price);

                    ////////// status  ////////////
                    double refund=0;
                    String orderstatus=orderStatus(orders.get(i));
                    TextField status=new TextField(orderstatus);
                    if(orderstatus.equals("complete, 50% refund"))
                    {
                        status.setText("Processing");
                        refund=0.5*orders.get(i).getPrice();
                    }
                    else if(orderstatus.equals("complete"))
                    {
                        status.setText("Processing");
                        refund=orders.get(i).getPrice();
                    }
                    else if(orderstatus.equals("complete, 0 refund"))
                    {
                        status.setText("Processing");
                    }
                    status.setEditable(false);
                    status.setStyle("-fx-background-color:none");
                    status.setLayoutX(426);
                    status.setLayoutY(12.5);
                    pane.getChildren().add(status);

                    ////////// cancel order btn  ////////////
                    Button btn=new Button();
                    btn.setText("Cancel");
                    btn.setStyle("-fx-background-color:none");
                    btn.setLayoutX(525);
                    btn.setLayoutY(8);
                    btn.setId(String.valueOf(orders.get(i).getId()));

                    if(orderstatus.equals("complete, 0 refund"))
                    {
                        btn.setStyle("-fx-font-size:12.5;-fx-background-radius:2;-fx-background-color:#f17272");

                    }
                    else if(orderstatus.equals("complete, 50% refund"))
                    {
                        btn.setStyle("-fx-font-size:12.5;-fx-background-radius:2;-fx-background-color:#44ead1");

                    }else if(orderstatus.equals("complete"))
                    {
                        btn.setStyle("-fx-font-size:12.5;-fx-background-radius:2;-fx-background-color:#94f589");
                    }


                    double finalRefund = refund;
                    btn.setOnAction(e->{
                        int num= Integer.parseInt(((Button) e.getTarget()).getId());
                        try {
                            cancelOrder(num, finalRefund);
                            loadPage();
                            App.showAlert("order cancelled","order cancelled successfully, you got "+ finalRefund +" refund.");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                    if(orderstatus.equals("closed") || orderstatus.equals("cancelled"))
                    {
                        btn.setDisable(true);
                        btn.setVisible(false);
                    }

                    pane.getChildren().add(btn);

                    Line line = new Line(608,0,0,0);
                    line.setLayoutY(51);
                    line.setLayoutX(4);
                    line.setDisable(true);
                    pane.getChildren().add(line);

                    pane.setLayoutY(i*55+44);


                    ordersList.getChildren().add(pane);
                }
            }
            else
            {
                TextField text=new TextField("No order existed");
            }
        }
    }

}
