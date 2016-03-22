package gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Pavel Nikolaev on 14/03/2016.
 */
public class Utilities{

    public void getCss(Scene scene){

        try {
            URL url = this.getClass().getResource("main.css");
            if (url == null) {
                Alert.displayAlert("Error","Could not load resource: main.css");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
        }
        catch(Exception e){
            Alert.displayAlert("Error","Could not load resource: main.css" );
            System.exit(-1);
        }

    }
    public void resizeWindowIncrease(Stage window, int x , int y, int timeX, int timeY){

        Timer timer = new Timer(); //find explanation
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {


                if(i < x){
                    window.setWidth(window.getWidth() + 1.5);
                    window.setX(window.getX()-0.43);


                }
                else{
                    this.cancel();
                    Home home = new Home();
                    home.fadeIn();

                }
                 i+=2;

            }
        }, 0, timeX);

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {

                if(i < y) {
                    window.setHeight(window.getHeight() + 1.5);
                    window.setY(window.getY()-0.14);
                }
                else{

                    this.cancel();
                }

                i+=2;

            }
        },0, timeY);

    }
    public void resizeWindowDecrease(Stage window, int x , int y){

        Timer timer = new Timer(); //find explanation
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(int i = 0; i < x ;i++) {
                    window.setHeight(window.getHeight() - 1);
                }
            }
        }, 0, 5);

        timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    for(int i = 0; i < y ;i++) {
                        window.setHeight(window.getHeight() - 1);
                    }
                }

        }, 0, 17);
    }

   /* public Boolean passWordStrength(String pass){
        boolean  check = pass.matches("[a-zA-Z0-9]*");

        if (check == false){
            Alert.displayAlert("Error","Please make sure your password only contains letters and numbers");
            return false;
        }
        else if (pass.length() < 8){
            Alert.displayAlert("Error","make sure password is longer than 8 characters");
            return false;
        }
        else{
            return true;
        }

    }

    public Boolean strCmp(String str1, String str2){

        if(!str1.equals(str2)){
            Alert.displayAlert("Error","make sure passwords match");
            return false;
        }
        else{
            return true;
        }

    }*/



}
