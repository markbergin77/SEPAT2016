package EXPGUI.Layouts;

import EXPGUI.Gui.Utilities;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by Pavel Nikolaev on 5/05/2016.
 */
public class layoutMain {

    public static Scene returnSceneMain() {


        final Scene scene;
        // simple label
        Label loadingLabel = new Label("Loading");
        loadingLabel.setId("loadinglabel");

//        Button continueButton = new Button("Continue");
//        continueButton.setId("continuebutton");

//        // basic progress bar to show the user
//        // that data is currently loading
        ProgressBar progressBar = new ProgressBar();
        progressBar.setId("progressbar");
//
//        // using stackpane for easier styling
        StackPane root = new StackPane();
//
//        //creating different rectangles for
//        // different UI effects
//
//       // clip rect sets the visual bounds of the gui.
//       // anything outside these bounds will not be visible
        Rectangle clipRect = new Rectangle(350, 300);


        // a rectangle for a unnecessary but cool effect
        Rectangle ldBarEffect = new Rectangle(275, 25);
        ldBarEffect.setId("ldbareffect");

        //styling the rectangles
        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);

        // styling the loading bar
        ldBarEffect.setFill(Color.TRANSPARENT);
        ldBarEffect.setStrokeWidth(2);
        ldBarEffect.setArcHeight(10);
        ldBarEffect.setArcWidth(10);
        ldBarEffect.setStroke(Color.gray(1, 0.15));

        //setting the visual bounds to the dimensions of the clipRect
        root.setClip(clipRect);

        root.getChildren().addAll(loadingLabel, progressBar, ldBarEffect);
        root.setAlignment(Pos.CENTER);

        //position elements within the stack pane
        StackPane.setMargin(progressBar, new Insets(100, 0, 0, 0));
        StackPane.setMargin(ldBarEffect, new Insets(100, 0, 0, 0));
        //  StackPane.setMargin(continueButton, new Insets(220, 0, 0, 0));
        StackPane.setMargin(loadingLabel, new Insets(40, 0, 0, 0));

        scene = new Scene(root, 350, 300);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;

    }


}
