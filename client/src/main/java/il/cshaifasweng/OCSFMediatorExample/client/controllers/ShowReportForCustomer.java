package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.getAllReports;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.currentCustomerData;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.LogIN.LoginClient_username;

public class ShowReportForCustomer {


    @FXML
    private Button back;
    @FXML
    private Button deleteReportbtn;
    @FXML
    private AnchorPane pane_contianer;

    @FXML
    private Label title1;

    @FXML
    private Label title2;
    @FXML
    private AnchorPane mainPane;

    private List<CheckBox> cheackBoxs = new ArrayList<CheckBox>();

    @FXML
    void deleteReport(ActionEvent event) throws IOException, InterruptedException {
        int cheaked = 0;
        MsgClass msg = new MsgClass("#get current customer", LoginClient_username);
        SimpleClient.getClient().sendToServer(msg);

        while (currentCustomerData == null) {System.out.println("wait");}
        ArrayList<Report> allReports =new ArrayList<>();
        Customer cus = (Customer) currentCustomerData;
        for(int i=0;i<getAllReports().size();i++){
            if(getAllReports().get(i).getCustomer().getId().equals(cus.getId())){
                allReports.add(getAllReports().get(i));
            }
            System.out.println(getAllReports().get(i).getCustomer());
        }
        System.out.println("all report::::::::"+allReports);
        for (int i = 0; i < cheackBoxs.size(); i++) {
            if (cheackBoxs.get(i).isSelected() == true) {
                MsgClass msg2 = new MsgClass("#delete Report", allReports.get(i));
                SimpleClient.getClient().sendToServer(msg2);
                cheaked++;
            }
        }
        if (cheaked == 0) {
            showAlert("error", "pls select report to be deleted");
        }
        App.setRoot("controllers/showReportForCustomer");
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get customers", null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/ClientMainPage");
    }


    public void initialize() throws IOException, InterruptedException, ParseException {
       boolean moveRight=false;
        int j=0;
        MsgClass msg = new MsgClass("#get current customer", LoginClient_username);
        SimpleClient.getClient().sendToServer(msg);

        while (currentCustomerData == null) {System.out.println("wait");}
        ArrayList<Report> allReports =new ArrayList<>();
        Customer cus = (Customer) currentCustomerData;
        for(int i=0;i<getAllReports().size();i++){
            if(getAllReports().get(i).getCustomer().getId().equals(cus.getId())){
                allReports.add(getAllReports().get(i));
            }
            System.out.println(getAllReports().get(i).getCustomer());
        }

        //in case there in no reports
        if (allReports == null || allReports.size() == 0) {
            System.out.println("was here");
            TextField error = new TextField();
            error.setText("you have no reports");
            error.setStyle("-fx-background-color: green");
            error.setFont(new Font("Default", 20));
            error.setLayoutY(100);
            error.setLayoutX(150);
            error.setEditable(false);
            error.setMinWidth(180);
            pane_contianer.getChildren().add(error);
        } else {
            //real shit start here
            pane_contianer.setMinHeight(allReports.size() * 120);      ///the height of the container is related to the amount of the items
            Label reportTitle = new Label();
            int handledReports = 0;
            //get number of hanldler reports
            for (int i = 0; i < allReports.size(); i++) {
                System.out.println(allReports.get(i));
                if (allReports.get(i).isHandled() == true) {
                    handledReports++;
                }
            }

            reportTitle.setText("you currently have " + allReports.size() + " reports \n " + handledReports + " of them are handled");
            reportTitle.setLayoutY(110);
            reportTitle.setLayoutX(15);
            reportTitle.setMinWidth(160);
            reportTitle.setFont(new Font(16));
            reportTitle.setStyle("-fx-text-fill: #F2F4F7");
            mainPane.getChildren().add(reportTitle);
            for (int i = 0; i < allReports.size(); i++) {
                if (i % 2 == 1) {
                    moveRight = true;
                } else {
                    moveRight = false;
                }
                AnchorPane p = new AnchorPane();            //container of each item
                p.setStyle("-fx-background-color: #243447");
                p.setMinSize(250, 130);
                Label reportNum = new Label();
                reportNum.setText("Report " + (i+1));
                reportNum.setLayoutY(5);
                reportNum.setLayoutX(100);

                reportNum.setFont(new Font(20));
                reportNum.setStyle("-fx-text-fill: #F2F4F7");
                CheckBox tabMe = new CheckBox();
                tabMe.setLayoutY(5);
                tabMe.setLayoutX(230);
                cheackBoxs.add(tabMe);

                Button viewme = new Button();
                viewme.setText("View Report");
                viewme.setStyle("-fx-background-color:  #E43A19;-fx-text-fill: #F2F4F7");
                viewme.setLayoutX(20);
                viewme.setLayoutY(100);
                int finalI = i;
                viewme.setOnAction(e -> {
                    System.out.println("dont press me  pls");
                    Stage popupwindow = new Stage();

                    popupwindow.initModality(Modality.APPLICATION_MODAL);
                    popupwindow.setTitle("This is a pop up window");


                    Label label1 = new Label();
                    label1.setText(allReports.get(finalI).getContent());
                    label1.setLayoutY(5);
                    label1.setLayoutX(5);


                    Button button1 = new Button("hit me");
                    button1.setLayoutX(100);
                    button1.setLayoutY(100);
                    button1.setOnAction(event -> {
                        popupwindow.close();
                    });
                    VBox layout = new VBox();


                    layout.getChildren().addAll(label1, button1);
                    Scene scene1 = new Scene(layout, 300, 250);

                    popupwindow.setScene(scene1);

                    popupwindow.showAndWait();


                });
                DateFormat parser = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                Date date1 = parser.parse(allReports.get(finalI).getdate().toString());

                DateFormat formatter = new SimpleDateFormat("MM.dd.yyyy");
                System.out.println();

                Label date=new Label();
                date.setText("Report Date: "+formatter.format(date1));
                date.setLayoutY(40);
                date.setStyle("-fx-text-fill:  #F2F4F7");
                date.setLayoutX(5);
                date.setFont(new Font(13));

                Label shop=new Label();
                shop.setText("Shop: "+allReports.get(finalI).getShop().getName());
                shop.setStyle("-fx-text-fill:  #F2F4F7");
                shop.setLayoutY(65);
                shop.setFont(new Font(13));
                shop.setLayoutX(5);

                Button iewAnswer = new Button();
                iewAnswer.setText("View Answer");
                iewAnswer.setLayoutY(100);
                iewAnswer.setStyle("-fx-background-color:  #E43A19;-fx-text-fill: #F2F4F7");
                iewAnswer.setLayoutX(160);
                iewAnswer.setOnAction(e -> {
                    System.out.println("dont press me  pls");
                    Stage popupwindow = new Stage();

                    popupwindow.initModality(Modality.APPLICATION_MODAL);
                    popupwindow.setTitle("This is a pop up window");


                    Label label1 = new Label();
                    label1.setText(allReports.get(finalI).getAnswer());
                    label1.setLayoutY(5);
                    label1.setLayoutX(5);



                    Button button1 = new Button("hit me");
                    button1.setOnAction(event -> {
                        popupwindow.close();
                    });
                    VBox layout = new VBox();


                    layout.getChildren().addAll(label1, button1);

                    layout.setAlignment(Pos.CENTER);

                    Scene scene1 = new Scene(layout, 300, 250);

                    popupwindow.setScene(scene1);

                    popupwindow.showAndWait();


                });
                if (allReports.get(i).isHandled() == false) {
                    iewAnswer.setDisable(true);
                }
                if (moveRight) {
                    p.setLayoutY(180 * j);
                    p.setLayoutX(300);
                    j++;
                } else {
                    p.setLayoutY(180 * j);
                }
                p.getChildren().add(iewAnswer);
                p.getChildren().add(viewme);
                p.getChildren().add(tabMe);
                p.getChildren().add(reportNum);
                p.getChildren().add(shop);
                p.getChildren().add(date);
                pane_contianer.getChildren().add(p);
                pane_contianer.setStyle("-fx-background-color:#111F4D");
            }
        }
        currentCustomerData = null;
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
