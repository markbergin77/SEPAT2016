package gui;

import java.util.Vector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/* Base class for lists of stations
 * is a child of the Explorer */
public class ExplorerPaneBase extends GridPane
{
	//Scroll box/list, where favorites are located on program
	VBox vbox;
    ScrollPane scrollPane;
    TextField searchBox;
    
    Vector<Button> allButtons = new Vector<Button>();
    
    public ExplorerPaneBase()
    {
    	super();
    	vbox = new VBox(0);
    	scrollPane = new ScrollPane();
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    	scrollPane.setContent(vbox);
    	scrollPane.setPrefHeight(HomeScreen.defaultHeight);
    	scrollPane.setPrefViewportWidth(ExplorerButtonNotFav.buttonWidth);
    	scrollPane.setFitToWidth(true);
    	searchBox = new TextField();
    	searchBox.setPromptText("Search...");
    	searchBox.setPrefWidth(scrollPane.getWidth());
    	searchBox.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				for(Button button: allButtons) {
					String label = button.getText();
					if (label.toLowerCase().contains(newValue.toLowerCase())) {
						if(! vbox.getChildren().contains(button))
						{
							vbox.getChildren().add(button);
						}
					}
					else {
						if(vbox.getChildren().contains(button))
						{
							vbox.getChildren().remove(button);
						}
					}
				}
			}
		});
    	add(searchBox, 0, 0);
    	add(scrollPane, 0, 1);
    }
    
    protected void addButton(Button node) 
    {
		vbox.getChildren().add(node);
		allButtons.add(node);		
	}
    
    void searchBoxKeyPressed(KeyEvent e)
    {
    	if(e.getCode() == KeyCode.ENTER)
    	{
    		searchBoxEnterPressed();
    	}
    }
    
    void searchBoxEnterPressed()
    {
    	String searchTerm = searchBox.getText();
    	
    }
    
    public VBox getVBox()
    {
        return vbox;
    }
}
