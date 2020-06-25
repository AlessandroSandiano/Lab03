package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;

import it.polito.tdp.model.Dictionary;
import it.polito.tdp.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary dictionary;
	boolean linearResearch = true;
	List<String> inputTextList = new LinkedList<>();
	List<RichWord> listRichWords = new LinkedList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton languageButton;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Label wrongWordsMessage;

    @FXML
    private Label timeMessage;

    @FXML
    void doClearText(ActionEvent event) {
    	dictionary.getDictionaryWords().clear();
    	inputTextList.clear();
    	listRichWords.clear();
    	timeMessage.setText("");
    	wrongWordsMessage.setText("");
    	messageTextArea.clear();
    	inputTextArea.clear();
    	languageButton.setDisable(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	languageButton.setDisable(true);
    	int cont = 0;
    	Long start, stop;
    	dictionary.loadDictionary(languageButton.getText());
    	String s = inputTextArea.getText();
    	s = s.toLowerCase();
    	s = s.replaceAll("[.,\\/#!$%\\^&\\*;:{}?=\\-_`'´~()\\[\\]\"]", "");
    	s = s.replaceAll("\n", " ");
    	String array[] = s.split(" ");
    	for (int i=0; i<array.length; i++)
    		inputTextList.add(array[i]);
    	start = System.nanoTime();
    	if (linearResearch == true)
    		listRichWords.addAll(dictionary.spellCheckTextLinear(inputTextList));
    	else
    		listRichWords.addAll(dictionary.spellCheckTextDichotomic(inputTextList));
    	stop = System.nanoTime();
    	for (RichWord r: listRichWords)
    		if (r.isCorrect() == false) {
    			messageTextArea.appendText(r.toString() + "\n");
    			cont++;
    		}
    	wrongWordsMessage.setText("The text contains " + cont + " errors");
    	timeMessage.setText("Spell check completed in " + ((stop-start)/1e9) + " seconds");
    	inputTextList.clear();
    	listRichWords.clear();
    }

    @FXML
    void setEnglish(ActionEvent event) {
    	languageButton.setText("English");
    }

    @FXML
    void setItalian(ActionEvent event) {
    	languageButton.setText("Italian");
    }

    @FXML
    void initialize() {
        assert languageButton != null : "fx:id=\"languageButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert inputTextArea != null : "fx:id=\"inputTextArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert messageTextArea != null : "fx:id=\"messageTextArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert wrongWordsMessage != null : "fx:id=\"wrongWordsMessage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert timeMessage != null : "fx:id=\"timeMessage\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel (Dictionary model) {
    	this.dictionary = model;
    }
}


