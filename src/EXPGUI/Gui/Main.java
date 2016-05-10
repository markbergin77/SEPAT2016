package EXPGUI.Gui;

/**
 * Created by Pavel Nikolaev on 12/03/2016.
 */

import EXPGUI.Controller.Controller;
import EXPGUI.Layouts.GuiElements.GuiEffects;
import EXPGUI.Layouts.layoutMain;;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {
    private static Stage window;
    private static Scene scene;
    private double xPos, yPos;
    protected static Controller controller;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // removing all default OS styles, resizing and window dragging functionality for more control
        // over the gui
        window = primaryStage;
        window.initStyle(StageStyle.TRANSPARENT);

        scene = layoutMain.returnSceneMain();
        dragWindow(scene);

        window.setScene(scene);
        window.show();
        GuiEffects.fadeInMain(scene);

        controller = new Controller(scene);
        controller.getAllStations();
    }

    public void dragWindow(Scene scene) {

        scene.setOnMousePressed(e -> {

            this.xPos = window.getX() - e.getScreenX();
            this.yPos = window.getY() - e.getScreenY();

        });

        scene.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() + this.xPos);
            window.setY(e.getScreenY() + this.yPos);
        });
    }

    public static Controller returnControl(){
        return controller;
    }
}
