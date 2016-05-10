package EXPGUI.Layouts.GuiElements;

import EXPGUI.Gui.HomeScreen;
import app.Main;
import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class GuiEffects {

    public static void fadeIn(Scene scene){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(1000), scene.lookup("#datatablepane"));
        fT1.setFromValue(0.0);
        fT1.setToValue(1.0);
        fT1.setDelay(Duration.millis(540));
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(1000), scene.lookup("#toolbar"));
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.setDelay(Duration.millis(540));
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(1000), scene.lookup("#graphpane"));
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.setDelay(Duration.millis(540));
        fT3.play();

        FadeTransition fT4
                = new FadeTransition(Duration.millis(1000), scene.lookup("#explorerpane"));
        fT4.setFromValue(0.0);
        fT4.setToValue(1.0);
        fT4.setDelay(Duration.millis(540));
        fT4.play();

        FadeTransition fT5
                = new FadeTransition(Duration.millis(1000), scene.lookup("#exportbutton"));
        fT5.setFromValue(0.0);
        fT5.setToValue(1.0);
        fT5.setDelay(Duration.millis(540));
        fT5.play();

    }

    public static void fadeInMain(Scene scene){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), scene.lookup("#loadinglabel"));
        fT1.setFromValue(0.0);
        fT1.setToValue(1.1);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), scene.lookup("#continuebutton"));
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), scene.lookup("#backgroundimageview"));
        fT3.setFromValue(0.0);
        fT3.setToValue(0.03);
        fT3.play();

        FadeTransition fT4
                = new FadeTransition(Duration.millis(2000), scene.lookup("#ldbareffect"));
        fT4.setFromValue(0.0);
        fT4.setToValue(0.3);
        fT4.play();

        FadeTransition fT5
                = new FadeTransition(Duration.millis(2000), scene.lookup("#progressbar"));
        fT5.setFromValue(0.0);
        fT5.setToValue(1.0);
        fT5.setOnFinished(e -> loadEffect(scene));
        fT5.play();

    }
    public static void loadEffect(Scene scene){

        FadeTransition fadeTransition
                = new FadeTransition(Duration.millis(2200),scene.lookup("#progressbar"));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.45);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2200), scene.lookup("#loadinglabel"));
        fT1.setFromValue(1.0);
        fT1.setToValue(0.2);
        fT1.setCycleCount(Animation.INDEFINITE);
        fT1.setAutoReverse(true);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2200), scene.lookup("#ldbareffect"));
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.setCycleCount(Animation.INDEFINITE);
        fT2.setAutoReverse(false);
        fT2.play();

        //creating a ripple effect for the loading bar rectangle
        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(2200), scene.lookup("#ldbareffect"));
        scaleTransition1.setByX(0.1f);
        scaleTransition1.setByY(0.9f);
        scaleTransition1.setCycleCount(Animation.INDEFINITE);
        scaleTransition1.setAutoReverse(false);
        scaleTransition1.play();

    }

    public static void fadeOut(Scene scene){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), scene.lookup("#loadinglabel"));
        fT1.setFromValue(1.0);
        fT1.setToValue(0.0);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), scene.lookup("#continuebutton"));
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), scene.lookup("#backgroundimageview"));
        fT3.setFromValue(0.3);
        fT3.setToValue(0.0);
        fT3.play();

        FadeTransition fT4
                = new FadeTransition(Duration.millis(2000), scene.lookup("#ldbareffect"));
        fT4.setFromValue(0.3);
        fT4.setToValue(0.0);
        fT4.play();

        FadeTransition fT5
                = new FadeTransition(Duration.millis(2000), scene.lookup("#progressbar"));
        fT5.setFromValue(1.0);
        fT5.setToValue(0.0);
        fT5.setOnFinished(e -> {
            //when done fading out, let the HOMESCREEN
            // to take control
            HomeScreen home = new HomeScreen();
            home.display((Stage)scene.getWindow());
        });
        fT5.play();

    }

    public static void resizeWindowIncrease(Stage window, double x, double y, double screenW,double screenH) {


        // main resizing method
        // specify what height and width to resize too
        // keep resizing over certain period of time until specified dimensions are reached
        // this fixes the problem for the window resizing differently on different platforms


        WritableValue<Object> writableHeight = new WritableValue<Object>() {
            @Override
            public Object getValue() {
                return window.getHeight();
            }

            @Override
            public void setValue(Object value) {
                window.setHeight((double) value);
            }
        };

        WritableValue<Object> writableWidth = new WritableValue<Object>() {
            @Override
            public Object getValue() {
                return window.getWidth();
            }

            @Override
            public void setValue(Object value) {
                window.setWidth((double) value);
            }
        };


        WritableValue<Object> writableXpos = new WritableValue<Object>() {
            @Override
            public Object getValue() {
                return window.getX();
            }

            @Override
            public void setValue(Object value) {
                window.setX((double) value);
            }
        };

        WritableValue<Object> writableYpos = new WritableValue<Object>() {
            @Override
            public Object getValue() {
                return window.getY();
            }

            @Override
            public void setValue(Object value) {
                window.setY((double) value);
            }
        };

        Timeline increaseWidth = new Timeline();
        increaseWidth.setCycleCount(1);
        final KeyValue Wkey1 = new KeyValue(writableWidth, x-500);
        final KeyValue Wkey2 = new KeyValue(writableWidth, x-300);
        final KeyValue Wkey3 = new KeyValue(writableWidth, x-100);
        final KeyValue Wkey4 = new KeyValue(writableWidth, x+250);
        final KeyValue Wkey5 = new KeyValue(writableWidth, x-250);
//        final KeyValue Wkey6 = new KeyValue(writableWidth, x+180);
//        final KeyValue Wkey7 = new KeyValue(writableWidth, x-180);
//        final KeyValue Wkey8 = new KeyValue(writableWidth, x+120);
//        final KeyValue Wkey9 = new KeyValue(writableWidth, x-120);
//        final KeyValue Wkey10 = new KeyValue(writableWidth, x+70);
//        final KeyValue Wkey11 = new KeyValue(writableWidth, x-70);
//        final KeyValue Wkey12 = new KeyValue(writableWidth, x+40);
//        final KeyValue Wkey13 = new KeyValue(writableWidth, x-40);
//        final KeyValue Wkey14 = new KeyValue(writableWidth, x+20);
        final KeyValue Wkey15 = new KeyValue(writableWidth, x);
        final KeyFrame Wkf1 = new KeyFrame(Duration.millis(300), Wkey1);
        final KeyFrame Wkf2 = new KeyFrame(Duration.millis(400), Wkey2);
        final KeyFrame Wkf3 = new KeyFrame(Duration.millis(460), Wkey3);
        final KeyFrame Wkf4 = new KeyFrame(Duration.millis(480), Wkey4);
        final KeyFrame Wkf5 = new KeyFrame(Duration.millis(720), Wkey5);
//        final KeyFrame Wkf6 = new KeyFrame(Duration.millis(800), Wkey6);
//        final KeyFrame Wkf7 = new KeyFrame(Duration.millis(1000), Wkey7);
//        final KeyFrame Wkf8 = new KeyFrame(Duration.millis(1080), Wkey8);
//        final KeyFrame Wkf9 = new KeyFrame(Duration.millis(1280), Wkey9);
//        final KeyFrame Wkf10 = new KeyFrame(Duration.millis(1340), Wkey10);
//        final KeyFrame Wkf11 = new KeyFrame(Duration.millis(1500), Wkey11);
//        final KeyFrame Wkf12 = new KeyFrame(Duration.millis(1560), Wkey12);
//        final KeyFrame Wkf13 = new KeyFrame(Duration.millis(1660), Wkey13);
//        final KeyFrame Wkf14 = new KeyFrame(Duration.millis(1700), Wkey14);
        final KeyFrame Wkf15 = new KeyFrame(Duration.millis(800), Wkey15);
        increaseWidth.getKeyFrames().addAll(Wkf1,Wkf2,Wkf3,Wkf4,Wkf5,Wkf15);
        increaseWidth.play();

        Timeline increaseHeight = new Timeline();
        increaseHeight.setCycleCount(1);
        final KeyValue Hkey1 = new KeyValue(writableHeight, y-300);
        final KeyValue Hkey2 = new KeyValue(writableHeight, y-200);
        final KeyValue Hkey3 = new KeyValue(writableHeight, y-100);
        final KeyValue Hkey4 = new KeyValue(writableHeight, y+250);
        final KeyValue Hkey5 = new KeyValue(writableHeight, y);
//        final KeyValue Hkey6 = new KeyValue(writableHeight, y+180);
//        final KeyValue Hkey7 = new KeyValue(writableHeight, y);
//        final KeyValue Hkey8 = new KeyValue(writableHeight, y+120);
//        final KeyValue Hkey9 = new KeyValue(writableHeight, y);
//        final KeyValue Hkey10 = new KeyValue(writableHeight, y+70);
//        final KeyValue Hkey11 = new KeyValue(writableHeight, y);
//        final KeyValue Hkey12 = new KeyValue(writableHeight, y+40);
//        final KeyValue Hkey13 = new KeyValue(writableHeight, y);
//        final KeyValue Hkey14 = new KeyValue(writableHeight, y+20);
        final KeyValue Hkey15 = new KeyValue(writableHeight, y);
        final KeyFrame Hkf1 = new KeyFrame(Duration.millis(300), Hkey1);
        final KeyFrame Hkf2 = new KeyFrame(Duration.millis(400), Hkey2);
        final KeyFrame Hkf3 = new KeyFrame(Duration.millis(460), Hkey3);
        final KeyFrame Hkf4 = new KeyFrame(Duration.millis(480), Hkey4);
        final KeyFrame Hkf5 = new KeyFrame(Duration.millis(720), Hkey5);
//        final KeyFrame Hkf6 = new KeyFrame(Duration.millis(800), Hkey6);
//        final KeyFrame Hkf7 = new KeyFrame(Duration.millis(1000), Hkey7);
//        final KeyFrame Hkf8 = new KeyFrame(Duration.millis(1080), Hkey8);
//        final KeyFrame Hkf9 = new KeyFrame(Duration.millis(1280), Hkey9);
//        final KeyFrame Hkf10 = new KeyFrame(Duration.millis(1340), Hkey10);
//        final KeyFrame Hkf11 = new KeyFrame(Duration.millis(1500), Hkey11);
//        final KeyFrame Hkf12 = new KeyFrame(Duration.millis(1560), Hkey12);
//        final KeyFrame Hkf13 = new KeyFrame(Duration.millis(1660), Hkey13);
//        final KeyFrame Hkf14 = new KeyFrame(Duration.millis(1700), Hkey14);
        final KeyFrame Hkf15 = new KeyFrame(Duration.millis(800), Hkey15);
        increaseHeight.getKeyFrames().addAll(Hkf1,Hkf2,Hkf3,Hkf4,Hkf5,Hkf15);
        increaseHeight.play();


        Timeline moveWindowX = new Timeline();
        moveWindowX.setCycleCount(1);
        final KeyValue Xkey1 = new KeyValue(writableXpos,(screenW/2)-(x/2));
        final KeyFrame Xkf1 = new KeyFrame(Duration.millis(1000), Xkey1);
        moveWindowX.getKeyFrames().add(Xkf1);
        moveWindowX.play();
        moveWindowX.setOnFinished(e->{
            fadeIn(window.getScene());
        });

        Timeline moveWindowY = new Timeline();
        moveWindowX.setCycleCount(1);
        final KeyValue Ykey1 = new KeyValue(writableYpos,(screenH/2)-(y/2));
        final KeyFrame Ykf1 = new KeyFrame(Duration.millis(1000), Ykey1);
        moveWindowY.getKeyFrames().add(Ykf1);
        moveWindowY.play();

    }

}
