package EXPGUI.Gui;

import EXPGUI.Layouts.GuiElements.GuiEffects;
import EXPGUI.Layouts.LayoutLarge;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Pavel Nikolaev on 14/03/2016.
 */
public class Utilities {

    // method for applying CSS to everything within a scene
    public void getCss(Scene scene) {

        try {
            URL url = this.getClass().getResource("main.css");
            if (url == null) {
                Alert.displayAlert("Error", "Could not load resource: main.css");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            Alert.displayAlert("Error", "Could not load resource: main.css");
            System.exit(-1);
        }

    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

}
