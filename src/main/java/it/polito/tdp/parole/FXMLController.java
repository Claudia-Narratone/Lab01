package it.polito.tdp.parole;

import java.util.*;
import it.polito.tdp.parole.model.Parole;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Parole elenco=new Parole();
	Parole2 elenco2=new Parole2();
	private List<String> timeL=new LinkedList<String>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private TextArea txtTime;
    
    @FXML
    private Button btnCancella;

    @FXML
    private Button btnReset;
    
    private String getTimeString(long start, long end, char tipologia, char tipologia2) {
    	
    	long time=end-start;
    	String risp="";
    	String str="";
    	
    	if(tipologia2=='L') {
    		if(tipologia=='I')
    			risp="Tempo parola aggiunta(Linked): " + time;
    		if(tipologia=='C')
    			risp="Tempo cancellazione parola(Linked): " + time;
    		if(tipologia=='R')
    			risp="Tempo reset elenco(Linked): " +time;
    		}
    	else {
    		if(tipologia=='I')
    			risp="Tempo parola aggiunta(Array): " + time;
    		if(tipologia=='C')
    			risp="Tempo cancellazione parola(Array): " + time;
    		if(tipologia=='R')
    			risp="Tempo reset elenco(Array): " +time;
    	}
    	
    	timeL.add(risp);
    	
    	for(String s:timeL)
    		if(s!=null)
    			str+= s+"\n";
    	return str;
    }

    @FXML
    void doInsert(ActionEvent event) {
    	long start=System.nanoTime();
    	String parola=txtParola.getText();
    	if(parola.length()==0) {
    		txtResult.setText("Devi inserire una parola");
    		return;
    	}
    	elenco.addParola(parola);
    	for(String p:elenco.getElenco()) {
    		txtResult.setText(p+"\n");
    	}
    	long end=System.nanoTime();
    	String tempo=getTimeString(start, end, 'I', 'A');
    	txtTime.setText(tempo);
    	
    	start=System.nanoTime();
    	String parola2=txtParola.getText();
    	if(parola2.length()==0) {
    		txtResult.setText("Devi inserire una parola");
    		return;
    	}
    	elenco2.addParola(parola2);
    	for(String p:elenco2.getElenco()) {
    		txtResult.setText(p+"\n");
    	}
    	
    	end=System.nanoTime();
    	String tempo2=getTimeString(start, end, 'I', 'L');
    	txtTime.setText(tempo2);
    }
    
    @FXML
    void doCancella(ActionEvent event) {
    	long start=System.nanoTime();
    	if(txtResult.getSelectedText()==null) {
    		txtResult.setText("Selezionare cosa cancellare");
    	}else {
    		String parola=txtResult.getSelectedText();
        	elenco.getElenco().remove(parola);
        	for(String p:elenco.getElenco()) {
        		txtResult.setText(p+"\n");
        	}
    	}
    	long end=System.nanoTime();
    	String tempo=getTimeString(start, end, 'C', 'A');
    	txtTime.setText(tempo);
    	
    	start=System.nanoTime();
    	String parola2=txtResult.getSelectedText();
    	elenco2.getElenco().remove(parola2);
    	for(String p:elenco2.getElenco()) {
    		txtResult.setText(p+"\n");
    	}
    	end=System.nanoTime();
    	String tempo2=getTimeString(start, end, 'C', 'L');
    	txtTime.setText(tempo2);
    }

    @FXML
    void doReset(ActionEvent event) {
    	// cancella tutte le parole della lista (reset)
    	long start=System.nanoTime();
    	if(elenco.getElenco().isEmpty())
    		return;
    	elenco.reset();
    	txtResult.setText("");
    	long end=System.nanoTime();
    	String tempo=getTimeString(start, end, 'R', 'A');
    	txtTime.setText(tempo);
    	
    	start=System.nanoTime();
    	if(elenco2.getElenco().isEmpty())
    		return;
    	elenco2.reset();
    	txtResult.setText("");
    	end=System.nanoTime();
    	String tempo2=getTimeString(start, end, 'R', 'L');
    	txtTime.setText(tempo2);
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

        elenco = new Parole() ;
    }
}
