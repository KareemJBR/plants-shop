package il.cshaifasweng.OCSFMediatorExample.client.controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static il.cshaifasweng.OCSFMediatorExample.client.controllers.SignUp.shop;
import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;

public class publicCatalogControl {

    @FXML
    private Button backBtn;

    @FXML
    private Button backBtn1;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private TextArea text1;

    @FXML
    private TextArea text2;

    @FXML
    private TextArea text3;

    @FXML
    private TextArea text4;

    @FXML
    private TextArea text5;

    @FXML
    private TextField title;


    @FXML
    void Back(ActionEvent event) throws IOException {
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/LogIN");
    }
    @FXML
    void createAcount(ActionEvent event) throws IOException {
        shop=true;
        MsgClass msg=new MsgClass("#get customers",null);
        SimpleClient.getClient().sendToServer(msg);
        App.setRoot("controllers/SignUp");
        //SignUp.main(null);
    }
    @FXML
    void initialize() {
        try {
            System.out.println("in init for catalog");
            // ArrayList<Flower> flowers = getAllFlowers();
            System.out.println(((ArrayList<Flower>)data));
            text1.setText(((ArrayList<Flower>)data).get(0).toString());
            img1.setImage(new Image(((ArrayList<Flower>)data).get(0).getImgURL()));
            text2.setText(((ArrayList<Flower>)data).get(1).toString());
            img2.setImage(new Image(((ArrayList<Flower>)data).get(1).getImgURL()));
            text3.setText(((ArrayList<Flower>)data).get(2).toString());
            img3.setImage(new Image(((ArrayList<Flower>)data).get(2).getImgURL()));
            text4.setText(((ArrayList<Flower>)data).get(3).toString());
            img4.setImage(new Image(((ArrayList<Flower>)data).get(3).getImgURL()));
            text5.setText(((ArrayList<Flower>)data).get(4).toString());
            img5.setImage(new Image(((ArrayList<Flower>)data).get(4).getImgURL()));
        } catch (Exception e) {
            System.out.println("init exception for reg catalog");
            System.out.println(e);
        }
    }



}
