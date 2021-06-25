package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 10;
	private final int TMAX = 4;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtTentativi;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultati;
    
    @FXML
    private HBox layoutTentativo;


    @FXML
    void doNuovaPartita(ActionEvent event) {
    	// gestione nuova partita
    	this.segreto = (int) ((Math.random() * NMAX) + 1);
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	// gestione interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	this.txtTentativoUtente.setText("");
    	this.txtRisultati.clear();
    	this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	// lettura input dell'utente
    	// controllo input
    	String ts = txtTentativoUtente.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch(NumberFormatException e) {
    		txtRisultati.appendText("Devi inserire un numero!");
    		return;
    	}
    	this.tentativiFatti++;
    	this.txtTentativi.setText(Integer.toString(TMAX - this.tentativiFatti));
    	if (tentativo == this.segreto) {
    		txtRisultati.setText("HAI INDOVINATO CON " + this.tentativiFatti + " TENTATIVI!");
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	// SE ANCHE ULTIMO TENTATICO è ANDATO A VUOTO, HO PERSO
    	if(tentativiFatti == TMAX) {
        	txtRisultati.setText("Hai perso! Il segreto era: " + this.segreto);
        	this.inGioco = false;
        	this.layoutTentativo.setDisable(true);
        	return;
        }
    	if(tentativo < this.segreto) {
    		txtRisultati.setText("Tentativo troppo basso");
    	} else {
    		txtRisultati.setText("Tentativo troppo alto");
    	}
    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

