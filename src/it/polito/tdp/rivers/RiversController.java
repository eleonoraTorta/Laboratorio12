/**
 * Sample Skeleton for 'Rivers.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Event;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	private Simulator s;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doPopola(ActionEvent event) {
    
    	River river = boxRiver.getValue();
    	s.getMisurazioniPerFiume(river);
    	txtStartDate.setText(river.getPrima().getData() + "\n");
    	txtEndDate.setText(river.getUltima().getData() + "\n");
    	txtNumMeasurements.setText(river.getTotaleMisurazioni() + "\n");
    	txtFMed.setText(river.getPortataMedia() + "\n");
    
    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    
    	River river = boxRiver.getValue();
    	List <Flow> flows = new ArrayList <Flow>(river.getMisurazioni().values());
    	for( Flow f : flows){
    		s.addFlow(f);		
    	}
    	String media = txtFMed.getText();
    	double  fmed = Double.parseDouble(media);
    	String fattore = txtK.getText();
    	double k = Double.parseDouble(fattore);
    	String n = txtNumMeasurements.getText();
    	int totale = Integer.parseInt(n);
    	s.setParametri(fmed, k, totale);
    	
    	s.run();
    	
    	txtResult.appendText("Giorni in cui non è possibile erogare il servizio: " + s.getGiorniInsoddisfatti() + "\n");
    	txtResult.appendText("Capacita media del bacino: " + s.getCapacitaMedia() + "\n");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }

	public void setSimulator(Simulator s) {
		this.s = s;
		boxRiver.getItems().addAll(s.getFiumi());
	}
}
