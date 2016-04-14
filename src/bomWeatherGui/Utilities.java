package bomWeatherGui;

import javafx.scene.Scene;
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

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {


                if(window.getWidth() < x){
                    window.setWidth(window.getWidth() + 2.5);
                    window.setX(window.getX()-1.0);


                }
                else{
                    this.cancel();
                    //print(window.getWidth());
                    HomeScreen home = new HomeScreen(window);
                    home.fadeIn();


                }
                 i+=2;

            }
        }, 0, timeX);

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {

                if(window.getHeight() < y) {
                    window.setHeight(window.getHeight() + 2.5);
                    window.setY(window.getY()-0.3);
                }
                else{

                    this.cancel();
                    //print(window.getHeight());

                }

                i+=2;

            }
        },0, timeY);

    }

    public void resizeWindowDecrease(Stage window, int x , int y){

        Timer timer = new Timer();
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

    public void print(Object obj){
        System.out.println(obj);
    }

}
