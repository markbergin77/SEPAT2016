package Main;

/**
 * Created by Pavel Nikolaev on 12/03/2016.
 */
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;


public class Main extends Application{

    private static Stage window;
    private static Scene scene1;
    private static Button button;
    private ProgressBar bar;
    private static Label LABEL;
    private static  ImageView backgroundImageView;
    private static Rectangle rect,rect2;
    private static  StackPane pane;


    public static void main(String args[]){

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setResizable(false);
        window.initStyle(StageStyle.TRANSPARENT);

        LABEL = new Label("Loading");
        button = new Button("Continue");
        bar = new ProgressBar();

        pane = new StackPane();

        rect = new Rectangle(350,300);
        rect2 = new Rectangle(275,25);

        rect.setArcHeight(20.0);
        rect.setArcWidth(20.0);
        rect2.setArcHeight(10.0);
        rect2.setArcWidth(10.0);

        rect2.setFill(Color.TRANSPARENT);
        rect2.setStrokeWidth(2);
        rect2.setStroke(Color.gray(1,0.15));


        pane.setClip(rect);
        pane.getChildren().addAll(button,LABEL,bar,rect2);
        pane.setAlignment(Pos.CENTER);

        StackPane.setMargin(bar,new Insets(100,0,0,0));
        StackPane.setMargin(rect2,new Insets(100,0,0,0));
        StackPane.setMargin(button,new Insets(220,0,0,0));
        StackPane.setMargin(LABEL , new Insets(40,0,0,0));

        button.getStyleClass().add("button-wait");
        //button.setVisible(false);

        button.setOnMouseEntered(e -> button.getStyleClass().add("button-wait-bright"));
        button.setOnMouseExited(e -> button.getStyleClass().remove("button-wait-bright"));

        window.setOnCloseRequest(e -> System.exit(0));

        window.setOnShowing(e -> {
            button.toFront();
            load();
        });

        button.setOnMousePressed(e -> {
            FadeTransition fadeTransition
                    = new FadeTransition(Duration.millis(500), button);
            fadeTransition.setFromValue(0.5);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
            fadeOut();
            button.setDisable(true);

        });

        scene1 = new Scene(pane,350,300);
        scene1.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene1);

        window.setScene(scene1);
        window.setTitle("Login");
        window.show();
        fadeIn();

    }

    public void fadeOut(){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), LABEL);
        fT1.setFromValue(1.0);
        fT1.setToValue(0.0);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), button);
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), backgroundImageView);
        fT3.setFromValue(0.3);
        fT3.setToValue(0.0);
        fT3.play();

        FadeTransition fT4
                = new FadeTransition(Duration.millis(2000), rect2);
        fT4.setFromValue(0.3);
        fT4.setToValue(0.0);
        fT4.play();


        FadeTransition fT5
                = new FadeTransition(Duration.millis(2000), bar);
        fT5.setFromValue(1.0);
        fT5.setToValue(0.0);
        fT5.setOnFinished(e -> {

            Home home = new Home();
            home.display(window);

        });
        fT5.play();


    }

    public void fadeIn(){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), bar);
        fT1.setFromValue(0.0);
        fT1.setToValue(1.);
        fT1.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), LABEL);
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.play();

    }

    public void display (Stage window){
        fadeIn();
        window.setScene(scene1);
    }

    public void load(){

        FadeTransition fadeTransition
                = new FadeTransition(Duration.millis(2200), bar);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.45);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2200), LABEL);
        fT1.setFromValue(1.0);
        fT1.setToValue(0.2);
        fT1.setCycleCount(Animation.INDEFINITE);
        fT1.setAutoReverse(true);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2200), rect2);
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.setCycleCount(Animation.INDEFINITE);
        fT2.setAutoReverse(false);
        fT2.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(2200), rect2);
        scaleTransition1.setByX(0.1f);
        scaleTransition1.setByY(0.9f);
        scaleTransition1.setCycleCount(Animation.INDEFINITE);
        scaleTransition1.setAutoReverse(false);
        scaleTransition1.play();

        backgroundImageView = new ImageView( getClass().getResource( "background.jpg").toExternalForm());

        backgroundImageView.setOpacity(0.3);
        pane.getChildren().add( backgroundImageView);
        StackPane.setMargin(backgroundImageView,new Insets(0,0,0,1300));
        backgroundImageView.toBack();


        scaleTransition1 = new ScaleTransition(Duration.seconds(20), backgroundImageView);
        scaleTransition1.setByX(0.5f);
        scaleTransition1.setByY(0.5f);
        scaleTransition1.setCycleCount(Animation.INDEFINITE);
        scaleTransition1.setAutoReverse(true);
        scaleTransition1.play();


    }

    public void oncomplete(){
        button.toFront();
        button.setVisible(true);

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), button);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.play();

    }


}
