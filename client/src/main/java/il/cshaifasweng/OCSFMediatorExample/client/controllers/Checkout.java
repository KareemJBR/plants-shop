package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.OrderShop;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.OrderSubtotal;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Login_customer;

public class Checkout {
    @FXML
    private TextField AddressTextFeild;

    @FXML
    private RadioButton CashRadioBtn;

    @FXML
    private Button CheckoutBtn;

    @FXML
    private RadioButton CreditcardRadioBtn;

    @FXML
    private RadioButton DeliveryRadioBtn;

    @FXML
    private TextField Greeting;

    @FXML
    private TextField NameTextFeild;

    @FXML
    private TextField PhoneTextFeild;

    @FXML
    private RadioButton PickupRadioBtn;

    @FXML
    private Label ReceiverDetailsLabel;

    @FXML
    private Label ReceiverDetailsLabel1;

    @FXML
    private Label ReceiverDetailsLabel2;

    @FXML
    private Label SubtotalLabel;

    @FXML
    private Button backBtn;

    @FXML
    private Label dateLabel;

    @FXML
    private DatePicker datew;

    @FXML
    private ComboBox<String> Hour;

    @FXML
    private ComboBox<String> Minute;

    @FXML // fx:id="recieverRadioBtn"
    private RadioButton recieverRadioBtn; // Value injected by FXMLLoader

    @FXML // fx:id="Controller"
    private AnchorPane Controller; // Value injected by FXMLLoader


    @FXML
    public void initialize() throws IOException, InterruptedException {
        datew.setValue(LocalDate.now());
        SubtotalLabel.setText("Subtotal: "+ OrderSubtotal);
        recieverRadioBtn.setSelected(false);
        Controller.setStyle("-fx-background-color: #D4F1F4");
        loadTime();

    }

    public  void loadTime()
    {
        Hour.getItems().removeAll(Hour.getItems());
        Minute.getItems().removeAll(Minute.getItems());

        Minute.getItems().addAll("00","01","02","03","04","05","06","07","08","09");
        for(int i=10;i<60;i++)
        {
            Minute.getItems().add(String.valueOf(i));
        }
        Minute.getSelectionModel().select(0);

        if(LocalDate.now().equals(datew.getValue()))
        {
            int hour=LocalTime.now().getHour()+1;
            if(hour<=20 && hour>=8)
            {
                for(int i=hour;i<=20;i++)
                {
                    if(i<10)
                    {
                        Hour.getItems().add("0"+i);
                    }
                    else
                    {
                        Hour.getItems().add(String.valueOf(i));
                    }
                }
                Hour.getSelectionModel().select(0);
                return;
            }
            else if(hour>20)
            {
                datew.setValue(datew.getValue().plusDays(1));
            }
        }
        Hour.getItems().addAll("08","09","10","11","12","13","14","15","16","17","18","19","20");
        Hour.getSelectionModel().select(0);
    }

    @FXML
    void Checkdate(ActionEvent event) {
        if(LocalDate.now().isAfter(datew.getValue()))    //if the client entered and old date
        {
            datew.setValue(LocalDate.now());
        }
        loadTime();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("controllers/Cart");
    }


    @FXML
    void CashRadioBtnChange(ActionEvent event) {
        if(CashRadioBtn.isSelected())
        {
            CashRadioBtn.setSelected(true);
            CreditcardRadioBtn.setSelected(false);
        }
        else
        {
            CashRadioBtn.setSelected(false);
            CreditcardRadioBtn.setSelected(true);
        }
    }

    @FXML
    void Checkout(ActionEvent event) throws IOException {
        clear();
        ArrayList<Shop> allshops=getAllShops();
        Shop shop=null;
        if(allshops!=null)
        {
            if(allshops.size()!=0)
            {
                for(int i=0;i<allshops.size();i++)
                {
                    if(allshops.get(i).getAddress().equals(OrderShop))
                    {
                        shop=allshops.get(i);
                    }
                }
            }
        }

        if(DeliveryRadioBtn.isSelected())
        {
            if(NameTextFeild.getText().equals("") || PhoneTextFeild.getText().equals("") || AddressTextFeild.getText().equals(""))
            {
                showAlert("error","please fill out all Receiver details");
                if(NameTextFeild.getText().equals(""))
                {
                    NameTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if(PhoneTextFeild.getText().equals(""))
                {
                    PhoneTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if(AddressTextFeild.getText().equals(""))
                {
                    AddressTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
            }
            else
            {
                ArrayList<OrderItem> orderItems=new ArrayList<OrderItem>();
                ArrayList<CartItem> cartItems=searchCartItems(LoginClient_userId);
                String paymethod=CashRadioBtn.isSelected()==true?"Cash":"CreditCard";
                String shipingmethod=DeliveryRadioBtn.isSelected()==true?"Delivery":"Pickup";
                int hour= Integer.parseInt(Hour.getValue());
                int minute= Integer.parseInt(Minute.getValue());
                MsgClass msg = new MsgClass("#add order");
                Order order=new Order(shop, searchCustomer(LoginClient_userId), LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), datew.getValue().getYear(),
                        datew.getValue().getMonthValue(), datew.getValue().getDayOfMonth(), LocalTime.now().getHour(),
                        LocalTime.now().getMinute(), hour, minute, OrderSubtotal, paymethod, shipingmethod,
                        Greeting.getText(), false, "");

                for(int i=0;i<cartItems.size();i++)
                {
                    OrderItem orderItem=new OrderItem(cartItems.get(i));
                    AddOrderIem(orderItem);
                    orderItems.add(orderItem);
                }
//            order.setOrderitems(searchCartItems(LoginClient_userId));
                order.setOrderitems(orderItems);
                msg.setObj(order);
                boolean deliveryforclient=false;
                if(recieverRadioBtn.isSelected())
                {
                    deliveryforclient=true;
                }
                 order=new Order(shop,searchCustomer(LoginClient_userId),LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth(),datew.getValue().getYear(),datew.getValue().getMonthValue(),datew.getValue().getDayOfMonth(),LocalTime.now().getHour(),LocalTime.now().getMinute(),hour,minute, OrderSubtotal,paymethod,shipingmethod,Greeting.getText(),deliveryforclient,AddressTextFeild.getText());
                order.setOrderitems(orderItems);
                msg.setObj(order);
                SimpleClient.getClient().sendToServer(msg);
                deleteCart(LoginClient_userId);
                showAlert("Order successful","Order Successfully placed");
                App.setRoot("controllers/ClientMainPage");
            }

        }
        else
        {
            ArrayList<OrderItem> orderItems=new ArrayList<OrderItem>();
            ArrayList<CartItem> cartItems=searchCartItems(LoginClient_userId);
            String paymethod=CashRadioBtn.isSelected()==true?"Cash":"CreditCard";
            String shipingmethod=DeliveryRadioBtn.isSelected()==true?"Delivery":"Pickup";
            int hour= Integer.parseInt(Hour.getValue());
            int minute= Integer.parseInt(Minute.getValue());
            MsgClass msg = new MsgClass("#add order");
            Order order=new Order(shop,searchCustomer(LoginClient_userId),LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth(),datew.getValue().getYear(),datew.getValue().getMonthValue(),datew.getValue().getDayOfMonth(),LocalTime.now().getHour(),LocalTime.now().getMinute(),hour,minute, OrderSubtotal,paymethod,shipingmethod,Greeting.getText(),false,"");
            for(int i=0;i<cartItems.size();i++)
            {
                OrderItem orderItem=new OrderItem(cartItems.get(i));
                AddOrderIem(orderItem);
                orderItems.add(orderItem);
            }
//            order.setOrderitems(searchCartItems(LoginClient_userId));
            order.setOrderitems(orderItems);
            msg.setObj(order);
            SimpleClient.getClient().sendToServer(msg);
            deleteCart(LoginClient_userId);
            showAlert("Order successful","Order Successfully placed");
            App.setRoot("controllers/ClientMainPage");
        }

    }

    private void clear() {
        AddressTextFeild.setStyle("-fx-background-radius:15;");
        PhoneTextFeild.setStyle("-fx-background-radius:15;");
        NameTextFeild.setStyle("-fx-background-radius:15;");
    }

    @FXML
    void CreditcardRadioBtnChange(ActionEvent event) {
        if(CreditcardRadioBtn.isSelected())
        {
            CashRadioBtn.setSelected(false);
            CreditcardRadioBtn.setSelected(true);
        }
        else
        {
            CashRadioBtn.setSelected(true);
            CreditcardRadioBtn.setSelected(false);
        }
    }

    @FXML
    void DeliveryRadioBtnChange(ActionEvent event) {
        if(DeliveryRadioBtn.isSelected())
        {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            recieverRadioBtn.setVisible(true);
        }
        else
        {
            DeliveryRadioBtn.setSelected(false);
            PickupRadioBtn.setSelected(true);
            ReceiverDetailsLabel.setVisible(false);
            PhoneTextFeild.setVisible(false);
            NameTextFeild.setVisible(false);
            AddressTextFeild.setVisible(false);
            recieverRadioBtn.setVisible(false);
        }
    }

    @FXML
    void PickupRadioBtnChange(ActionEvent event) {
        if(PickupRadioBtn.isSelected())
        {
            DeliveryRadioBtn.setSelected(false);
            PickupRadioBtn.setSelected(true);
            ReceiverDetailsLabel.setVisible(false);
            PhoneTextFeild.setVisible(false);
            NameTextFeild.setVisible(false);
            AddressTextFeild.setVisible(false);
            recieverRadioBtn.setVisible(false);
        }
        else
        {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            recieverRadioBtn.setVisible(true);
        }
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

    public Customer searchCustomer(String customerId) throws IOException {
        ArrayList<Customer> customers=getAllCustomers();
        if(customers!=null)
        {
            if(customers.size()!=0)
            {
                for(int i=0;i<customers.size();i++)
                {
                    if(customers.get(i).getUser_id().equals(customerId))
                    {
                        return customers.get(i);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<CartItem> searchCartItems(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems=getAllCartItems();
        ArrayList<CartItem> returnedcartitems=new ArrayList<CartItem>();

        if(allcartitems !=null)
        {
            for(int i=0;i<allcartitems.size();i++)
            {
                if(allcartitems.get(i).getCustomer().getUser_id().equals(ClientId))
                {
                    returnedcartitems.add(allcartitems.get(i));
                }
            }
        }

        return returnedcartitems;
    }


    @FXML
    void receiverRadioBtnChange(ActionEvent event) {
      if(recieverRadioBtn.isSelected())
      {
          NameTextFeild.setText(Login_customer.getFirst_name());
          NameTextFeild.setEditable(false);
      }
      else
      {
          NameTextFeild.setText("");
          NameTextFeild.setEditable(true);
      }
    }



}