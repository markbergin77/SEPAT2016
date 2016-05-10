package EXPGUI.Gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class Alert {


    public static void displayAlert(String title, String message) {

        // a simple alert class that displays a customized alert message
        // will be restyled in the future

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);

        Label label1 = new Label();
        label1.setText(message);

        Button button1 = new Button();
        button1.setText("Close");
        button1.setOnAction(e -> window.close());

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1, button1);
        layout1.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1, 450, 150);

        Utilities util = new Utilities();
        util.getCss(scene1);

        window.setScene(scene1);
        window.showAndWait();

    }


}
