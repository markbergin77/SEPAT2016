package gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import app.Main;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.geometry.*;

import java.net.URL;


public class Alert 
{
	Stage window;
    //Design and execution for any alerts needed
    public Alert(String title, String message, EventHandler<WindowEvent> onCloseHandler)
    {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);

        Label label1 = new Label();
        label1.setText(message);

        Button button1 = new Button();
        button1.setText("Close");
        button1.setOnAction(e -> window.close() );

        button1.setOnMouseEntered(e -> button1.getStyleClass().add("button-hover"));
        button1.setOnMouseExited(e -> button1.getStyleClass().remove("button-hover"));

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1,button1);
        layout1.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1,250,100);
        getCss(scene1);

        window.setScene(scene1);
        window.showAndWait();
        window.setOnCloseRequest(onCloseHandler);
    }

    public void getCss(Scene scene){

        try {
            URL url = this.getClass().getResource("splash.css");
            if (url == null) {
                Alert alert = new Alert("Error","Could not load resource : splash.css ",event -> System.exit(-1));

            }
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
        }
        catch(Exception e){
            Alert alert = new Alert("Error","Could not load resource : splash.css ",event -> System.exit(-1));
        }

    }

    
}
