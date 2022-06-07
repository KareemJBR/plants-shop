package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.OrderShop;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.Cart.OrderSubtotal;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_userId;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.Login_customer;

public class Checkout {
    public static double oldSubtotal = OrderSubtotal;
    @FXML
    private TextField AddressTextFeild;

    @FXML
    private RadioButton CashRadioBtn;

    @FXML
    private RadioButton CreditcardRadioBtn;

    @FXML
    private RadioButton DeliveryRadioBtn;


    @FXML // fx:id="Greeting"
    private TextArea Greeting; // Value injected by FXMLLoader

    @FXML
    private TextField NameTextFeild;

    @FXML
    private TextField PhoneTextFeild;

    @FXML
    private RadioButton PickupRadioBtn;

    @FXML
    private Label ReceiverDetailsLabel;

    @FXML
    private CheckBox useBudget;

    @FXML
    private Label SubtotalLabel;
    @FXML
    private Label budget;

    @FXML
    private DatePicker datew;

    @FXML
    private ComboBox<String> Hour;

    @FXML
    private AnchorPane Controller;

    @FXML
    private ComboBox<String> Minute;

    @FXML // fx:id="recieverRadioBtn"
    private RadioButton recieverRadioBtn; // Value injected by FXMLLoader


    @FXML
    public void initialize() throws IOException, InterruptedException {
        datew.setValue(LocalDate.now());

        SubtotalLabel.setText("Subtotal: " + OrderSubtotal + " ₪");
        recieverRadioBtn.setSelected(false);
        loadTime();
        budget.setText("your current budget is: " + Login_customer.getBudget());
        budget.setMinWidth(300);
        if(Login_customer.getBudget()==0){
            useBudget.setDisable(true);
        }
        else{
            useBudget.setDisable(false);
        }
        PhoneTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            PhoneTextFeild.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }

    public void loadTime() {
        Hour.getItems().removeAll(Hour.getItems());
        Minute.getItems().removeAll(Minute.getItems());

        Minute.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09");
        for (int i = 10; i < 60; i++) {
            Minute.getItems().add(String.valueOf(i));
        }
        Minute.getSelectionModel().select(0);

        if (LocalDate.now().equals(datew.getValue())) {
            int hour = LocalTime.now().getHour() + 1;
            if (hour <= 20 && hour >= 8) {
                for (int i = hour; i <= 20; i++) {
                    if (i < 10) {
                        Hour.getItems().add("0" + i);
                    } else {
                        Hour.getItems().add(String.valueOf(i));
                    }
                }
                Hour.getSelectionModel().select(0);
                return;
            } else if (hour > 20) {
                datew.setValue(datew.getValue().plusDays(1));
            }
        }
        Hour.getItems().addAll("08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
        Hour.getSelectionModel().select(0);
    }

    @FXML
    void Checkdate(ActionEvent event) {
        if (LocalDate.now().isAfter(datew.getValue()))    //if the client entered and old date
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
        if (CashRadioBtn.isSelected()) {
            CashRadioBtn.setSelected(true);
            CreditcardRadioBtn.setSelected(false);
        } else {
            CashRadioBtn.setSelected(false);
            CreditcardRadioBtn.setSelected(true);
        }
    }

    @FXML
    void Checkout(ActionEvent event) throws IOException {
        clear();
        ArrayList<Shop> allshops = getAllShops();
        Shop shop = null;
        if (allshops != null) {
            if (allshops.size() != 0) {
                for (Shop allshop : allshops) {
                    if (allshop.getAddress().equals(OrderShop)) {
                        shop = allshop;
                    }
                }
            }
        }

        if (DeliveryRadioBtn.isSelected()) {
            if (NameTextFeild.getText().equals("") || PhoneTextFeild.getText().equals("") || AddressTextFeild.getText().equals("")) {
                showAlert("error", "please fill out all Receiver details");
                if (NameTextFeild.getText().equals("")) {
                    NameTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if (PhoneTextFeild.getText().equals("")) {
                    PhoneTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
                if (AddressTextFeild.getText().equals("")) {
                    AddressTextFeild.setStyle("-fx-background-radius:15;-fx-background-color:#f5c0c0;");
                }
            } else {
                ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
                ArrayList<CartItem> cartItems = searchCartItems(LoginClient_userId);
                String paymethod = CashRadioBtn.isSelected() ? "Cash" : "CreditCard";
                String shipingmethod = DeliveryRadioBtn.isSelected() ? "Delivery" : "Pickup";
                int hour = Integer.parseInt(Hour.getValue());
                int minute = Integer.parseInt(Minute.getValue());
                MsgClass msg = new MsgClass("#add order");
                Order order = new Order(shop, searchCustomer(LoginClient_userId), LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), datew.getValue().getYear(),
                        datew.getValue().getMonthValue(), datew.getValue().getDayOfMonth(), LocalTime.now().getHour(),
                        LocalTime.now().getMinute(), hour, minute, OrderSubtotal, paymethod, shipingmethod,
                        Greeting.getText(), false, "");

                for (CartItem cartItem : cartItems) {
                    OrderItem orderItem = new OrderItem(cartItem);
                    AddOrderIem(orderItem);
                    orderItems.add(orderItem);
                }
//            order.setOrderitems(searchCartItems(LoginClient_userId));
                order.setOrderitems(orderItems);
                msg.setObj(order);
                boolean deliveryforclient = recieverRadioBtn.isSelected();
                order = new Order(shop, searchCustomer(LoginClient_userId), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), datew.getValue().getYear(), datew.getValue().getMonthValue(), datew.getValue().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute(), hour, minute, OrderSubtotal, paymethod, shipingmethod, Greeting.getText(), deliveryforclient, AddressTextFeild.getText());
                order.setOrderitems(orderItems);
                msg.setObj(order);
                SimpleClient.getClient().sendToServer(msg);
                deleteCart(LoginClient_userId);
                showAlert("Order successful", "Order Successfully placed");
                App.setRoot("controllers/ClientMainPage");
            }

        } else {
            ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
            ArrayList<CartItem> cartItems = searchCartItems(LoginClient_userId);
            String paymethod = CashRadioBtn.isSelected() ? "Cash" : "CreditCard";
            String shipingmethod = DeliveryRadioBtn.isSelected() ? "Delivery" : "Pickup";
            int hour = Integer.parseInt(Hour.getValue());
            int minute = Integer.parseInt(Minute.getValue());
            MsgClass msg = new MsgClass("#add order");
            Order order = new Order(shop, searchCustomer(LoginClient_userId), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), datew.getValue().getYear(), datew.getValue().getMonthValue(), datew.getValue().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute(), hour, minute, OrderSubtotal, paymethod, shipingmethod, Greeting.getText(), false, "");
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem(cartItem);
                AddOrderIem(orderItem);
                orderItems.add(orderItem);
            }
//            order.setOrderitems(searchCartItems(LoginClient_userId));
            order.setOrderitems(orderItems);
            msg.setObj(order);
            SimpleClient.getClient().sendToServer(msg);
            deleteCart(LoginClient_userId);
            showAlert("Order successful", "Order Successfully placed");
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
        if (CreditcardRadioBtn.isSelected()) {
            CashRadioBtn.setSelected(false);
            CreditcardRadioBtn.setSelected(true);
        } else {
            CashRadioBtn.setSelected(true);
            CreditcardRadioBtn.setSelected(false);
        }
    }

    @FXML
    void DeliveryRadioBtnChange(ActionEvent event) {
        if (DeliveryRadioBtn.isSelected()) {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            recieverRadioBtn.setVisible(true);
        } else {
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
        if (PickupRadioBtn.isSelected()) {
            DeliveryRadioBtn.setSelected(false);
            PickupRadioBtn.setSelected(true);
            ReceiverDetailsLabel.setVisible(false);
            PhoneTextFeild.setVisible(false);
            NameTextFeild.setVisible(false);
            AddressTextFeild.setVisible(false);
            recieverRadioBtn.setVisible(false);
        } else {
            DeliveryRadioBtn.setSelected(true);
            PickupRadioBtn.setSelected(false);
            ReceiverDetailsLabel.setVisible(true);
            PhoneTextFeild.setVisible(true);
            NameTextFeild.setVisible(true);
            AddressTextFeild.setVisible(true);
            recieverRadioBtn.setVisible(true);
        }
    }

    public Customer searchCustomer(String customerId) throws IOException {
        ArrayList<Customer> customers = getAllCustomers();
        if (customers != null) {
            if (customers.size() != 0) {
                for (Customer customer : customers) {
                    if (customer.getUser_id().equals(customerId)) {
                        return customer;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<CartItem> searchCartItems(String ClientId) throws IOException {
        ArrayList<CartItem> allcartitems = getAllCartItems();
        ArrayList<CartItem> returnedcartitems = new ArrayList<CartItem>();

        if (allcartitems != null) {
            for (CartItem allcartitem : allcartitems) {
                if (allcartitem.getCustomer().getUser_id().equals(ClientId)) {
                    returnedcartitems.add(allcartitem);
                }
            }
        }

        return returnedcartitems;
    }


    @FXML
    void receiverRadioBtnChange(ActionEvent event) {
        if (recieverRadioBtn.isSelected()) {
            NameTextFeild.setText(Login_customer.getFirst_name());
            NameTextFeild.setEditable(false);
        } else {
            NameTextFeild.setText("");
            NameTextFeild.setEditable(true);
        }
    }

    @FXML
    void budgetUse(ActionEvent event) throws IOException {
        double total=0;
        if (useBudget.isSelected()) {
            if (Login_customer.getBudget() > OrderSubtotal) {
                Login_customer.setBudget(Login_customer.getBudget() - OrderSubtotal);
                SubtotalLabel.setText("Subtotal After Using the budget is: 0");
            } else {
                total = OrderSubtotal - Login_customer.getBudget();
                Login_customer.setBudget(0);
                SubtotalLabel.setText("Subtotal After Using the budget is: "+total);
            }
        } else {
            SubtotalLabel.setText("Subtotal: "+OrderSubtotal);
        }
        updateCustomer(Login_customer);
        SubtotalLabel.setMinWidth(300);
        SubtotalLabel.setLayoutX(380);
        Controller.getChildren().remove(SubtotalLabel);
        Controller.getChildren().add(SubtotalLabel);
    }
}