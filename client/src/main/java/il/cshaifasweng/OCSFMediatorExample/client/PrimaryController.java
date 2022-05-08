package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Flower;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.data;

public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

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
	private TextArea itemDesciption1;

	@FXML
	private TextArea itemDesciption2;

	@FXML
	private TextArea itemDesciption3;

	@FXML
	private TextArea itemDesciption4;

	@FXML
	private TextArea itemDesciption5;

	@FXML
	private TextField newPriceText1;

	@FXML
	private TextField newPriceText2;

	@FXML
	private TextField newPriceText3;

	@FXML
	private TextField newPriceText4;

	@FXML
	private TextField newPriceText5;

	@FXML
	private TextField text1;

	@FXML
	private TextField text2;

	@FXML
	private TextField text3;

	@FXML
	private TextField text4;

	@FXML
	private TextField text5;

	@FXML
	private Button updatePrice1;

	@FXML
	private Button updatePrice2;

	@FXML
	private Button updatePrice3;

	@FXML
	private Button updatePrice4;

	@FXML
	private Button updatePrice5;

	@FXML
	public void updatePrice (ActionEvent event){
		try {
			System.out.println("update Button");
			if(event.getSource().equals(updatePrice1)){
				MsgClass msg = new MsgClass("#update  1 "+newPriceText1.getText());
				SimpleClient.getClient().sendToServer(msg);
				newPriceText1.clear();
			}
			else if(event.getSource().equals(updatePrice2)){
				MsgClass msg = new MsgClass("#update  2 "+newPriceText2.getText());
				SimpleClient.getClient().sendToServer(msg);
				newPriceText2.clear();
			}
			else if(event.getSource().equals(updatePrice3)) {
				MsgClass msg = new MsgClass("#update  3 "+newPriceText3.getText());
				SimpleClient.getClient().sendToServer(msg);
				newPriceText3.clear();
			}
			else if(event.getSource().equals(updatePrice4)) {
				MsgClass msg = new MsgClass("#update  4 "+newPriceText4.getText());
				SimpleClient.getClient().sendToServer(msg);
				newPriceText4.clear();
			}
			else{
				MsgClass msg = new MsgClass("#update  5 "+newPriceText5.getText());
				SimpleClient.getClient().sendToServer(msg);
				newPriceText5.clear();
			}
		}
		catch (Exception e){
		}
	}

	@FXML
	void initialize() {
		assert img1 != null : "fx:id=\"img1\" was not injected: check your FXML file 'primary.fxml'.";
		assert img2 != null : "fx:id=\"img2\" was not injected: check your FXML file 'primary.fxml'.";
		assert img3 != null : "fx:id=\"img3\" was not injected: check your FXML file 'primary.fxml'.";
		assert itemDesciption1 != null : "fx:id=\"itemDesciption1\" was not injected: check your FXML file 'primary.fxml'.";
		assert itemDesciption2 != null : "fx:id=\"itemDesciption2\" was not injected: check your FXML file 'primary.fxml'.";
		assert itemDesciption3 != null : "fx:id=\"itemDesciption3\" was not injected: check your FXML file 'primary.fxml'.";
		assert newPriceText1 != null : "fx:id=\"newPriceText1\" was not injected: check your FXML file 'primary.fxml'.";
		assert newPriceText2 != null : "fx:id=\"newPriceText2\" was not injected: check your FXML file 'primary.fxml'.";
	    assert newPriceText3 != null : "fx:id=\"newPriceText3\" was not injected: check your FXML file 'primary.fxml'.";
		assert text1 != null : "fx:id=\"text1\" was not injected: check your FXML file 'primary.fxml'.";
		assert text2 != null : "fx:id=\"text2\" was not injected: check your FXML file 'primary.fxml'.";
		assert text3 != null : "fx:id=\"text3\" was not injected: check your FXML file 'primary.fxml'.";
		assert updatePrice1 != null : "fx:id=\"updatePrice1\" was not injected: check your FXML file 'primary.fxml'.";
		assert updatePrice2 != null : "fx:id=\"updatePrice2\" was not injected: check your FXML file 'primary.fxml'.";
		assert updatePrice3 != null : "fx:id=\"updatePrice3\" was not injected: check your FXML file 'primary.fxml'.";
		itemDesciption1.setText(((ArrayList<Flower>)data).get(0).toString());
		img1.setImage(new Image(((ArrayList<Flower>)data).get(0).getImgURL()));
		itemDesciption2.setText(((ArrayList<Flower>)data).get(1).toString());
		img2.setImage(new Image(((ArrayList<Flower>)data).get(1).getImgURL()));
		itemDesciption3.setText(((ArrayList<Flower>)data).get(2).toString());
		img3.setImage(new Image(((ArrayList<Flower>)data).get(2).getImgURL()));
		itemDesciption4.setText(((ArrayList<Flower>)data).get(3).toString());
		img4.setImage(new Image(((ArrayList<Flower>)data).get(3).getImgURL()));
		itemDesciption5.setText(((ArrayList<Flower>)data).get(4).toString());
		img5.setImage(new Image(((ArrayList<Flower>)data).get(4).getImgURL()));
	}

}
