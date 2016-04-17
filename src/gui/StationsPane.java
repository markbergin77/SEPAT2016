package gui;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class StationsPane extends GridPane
{
	//Scroll box/list, where favorites are located on program
	VBox vbox;
    ScrollPane scrollPane;
    TextField searchBox;
    public StationsPane()
    {
    	super();
    	vbox = new VBox(0);
    	scrollPane = new ScrollPane();
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    	scrollPane.setContent(vbox);
    	scrollPane.setPrefHeight(HomeScreen.defaultHeight);
    	scrollPane.setPrefViewportWidth(StationButton.buttonWidth);
    	scrollPane.setFitToWidth(true);
    	searchBox = new TextField();
    	searchBox.setPromptText("Search...");
    	searchBox.setPrefWidth(scrollPane.getWidth());
    	searchBox.setOnKeyPressed(e -> searchBoxKeyPressed(e));
    	add(searchBox, 0, 0);
    	add(scrollPane, 0, 1);
    }
    
    void searchBoxKeyPressed(KeyEvent e)
    {
    	
    }
    
    public VBox getVBox()
    {
        return vbox;
    }
}
