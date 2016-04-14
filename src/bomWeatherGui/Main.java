package bomWeatherGui;

import java.awt.Dimension;
import java.awt.Toolkit;

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


public class Main extends Application
{
    private static Stage window;
    private static Scene scene;
    private static Button continueButton;
    private ProgressBar progressBar;
    private static Label loadingLabel;
    private static  ImageView backgroundImageView;
    private static Rectangle clipRect,ldBarEffect;
    private static  StackPane root;
    double xPos,yPos;

    public static void main(String args[])
    {
        launch(args);
    }
    
    Dimension calcHomeWindowSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		Dimension output = new Dimension();
		if(screenWidth > 1919){
            output.setSize(1320, 740);
        }
        else if(screenWidth > 1439){
        	output.setSize(1260,680);
        }
        else if( screenWidth > 1279){
        	output.setSize(1100,550);
        }
        else if(screenWidth > 1023){
        	output.setSize(900,500);
        }
        else
        	return null;
		return output;
	}

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        window = primaryStage;
        window.setResizable(false);
        window.initStyle(StageStyle.TRANSPARENT);


        loadingLabel = new Label("Loading");
        continueButton = new Button("Continue");
        progressBar = new ProgressBar();

        root = new StackPane();

        clipRect = new Rectangle(350,300);
        ldBarEffect = new Rectangle(275,25);

        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);
        ldBarEffect.setArcHeight(10.0);
        ldBarEffect.setArcWidth(10.0);

        ldBarEffect.setFill(Color.TRANSPARENT);
        ldBarEffect.setStrokeWidth(2);
        ldBarEffect.setStroke(Color.gray(1,0.15));


        root.setClip(clipRect);
        root.getChildren().addAll(continueButton,loadingLabel,progressBar,ldBarEffect);
        root.setAlignment(Pos.CENTER);

        StackPane.setMargin(progressBar,new Insets(100,0,0,0));
        StackPane.setMargin(ldBarEffect,new Insets(100,0,0,0));
        StackPane.setMargin(continueButton,new Insets(220,0,0,0));
        StackPane.setMargin(loadingLabel , new Insets(40,0,0,0));

        continueButton.getStyleClass().add("button-wait");

        continueButton.setOnMouseEntered(e -> continueButton.getStyleClass().add("button-wait-bright"));
        continueButton.setOnMouseExited(e -> continueButton.getStyleClass().remove("button-wait-bright"));

        window.setOnCloseRequest(e -> System.exit(0));

        window.setOnShowing(e -> {
            continueButton.toFront();
            load();
        });

        continueButton.setOnMousePressed(e -> {
            FadeTransition fadeTransition
                    = new FadeTransition(Duration.millis(500), continueButton);
            fadeTransition.setFromValue(0.5);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
            fadeOut();
            continueButton.setDisable(true);

        });

        scene = new Scene(root,350,300);
        scene.setFill(Color.TRANSPARENT);
        dragWindow(scene);


        Utilities util = new Utilities();
        util.getCss(scene);

        window.setScene(scene);
        window.setTitle("Login");
        window.show();
        fadeIn();

    }

    public void fadeOut(){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), loadingLabel);
        fT1.setFromValue(1.0);
        fT1.setToValue(0.0);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), continueButton);
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), backgroundImageView);
        fT3.setFromValue(0.3);
        fT3.setToValue(0.0);
        fT3.play();

        FadeTransition fT4
                = new FadeTransition(Duration.millis(2000), ldBarEffect);
        fT4.setFromValue(0.3);
        fT4.setToValue(0.0);
        fT4.play();


        FadeTransition fT5
                = new FadeTransition(Duration.millis(2000), progressBar);
        fT5.setFromValue(1.0);
        fT5.setToValue(0.0);
        fT5.setOnFinished(e -> {

            HomeScreen home = new HomeScreen(window,
            		calcHomeWindowSize());
            home.startShowing(window);

        });
        fT5.play();
    }

    public void fadeIn(){

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), progressBar);
        fT1.setFromValue(0.0);
        fT1.setToValue(1.);
        fT1.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), loadingLabel);
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.play();

    }

  //  public void display (Stage window){
      //  fadeIn();
     //   window.setScene(scene1);
   // }

    public void load(){

        FadeTransition fadeTransition
                = new FadeTransition(Duration.millis(2200), progressBar);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.45);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        FadeTransition fT1
                = new FadeTransition(Duration.millis(2200), loadingLabel);
        fT1.setFromValue(1.0);
        fT1.setToValue(0.2);
        fT1.setCycleCount(Animation.INDEFINITE);
        fT1.setAutoReverse(true);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2200), ldBarEffect);
        fT2.setFromValue(1.0);
        fT2.setToValue(0.0);
        fT2.setCycleCount(Animation.INDEFINITE);
        fT2.setAutoReverse(false);
        fT2.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(2200), ldBarEffect);
        scaleTransition1.setByX(0.1f);
        scaleTransition1.setByY(0.9f);
        scaleTransition1.setCycleCount(Animation.INDEFINITE);
        scaleTransition1.setAutoReverse(false);
        scaleTransition1.play();

        backgroundImageView = new ImageView( getClass().getResource( "background.jpg").toExternalForm());

        backgroundImageView.setOpacity(0.3);
        root.getChildren().add( backgroundImageView);
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
        continueButton.toFront();
        continueButton.setVisible(true);

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), continueButton);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.play();
    }

    public void dragWindow(Scene scene){

        scene.setOnMousePressed(e -> {

            this.xPos = window.getX() - e.getScreenX();
            this.yPos = window.getY() - e.getScreenY();

        });

        scene.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() + this.xPos);
            window.setY(e.getScreenY() + this.yPos);
        });

    }


}
