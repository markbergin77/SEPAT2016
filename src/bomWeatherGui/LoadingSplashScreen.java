package bomWeatherGui;

import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.css.Rect;

import bomData.LoadingNotifier;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class LoadingSplashScreen implements LoadingNotifier
{
	Scene scene;
	StackPane rootPane;
	ProgressBar progressBar;
	Label loadingLabel;
	Label loadingActivity;
	Rectangle ldBarEffect;
	ImageView backgroundImageView;
	
	Duration fadeInDuration = Duration.millis(2000);

	Object loadingUpdateCallback;
	Object callOnFinished;

	Vector<Animation> fadeIns = new Vector<Animation>();

	public Scene getScene() {
		return scene;
	}

	public LoadingSplashScreen() {
		rootPane = new StackPane();

		Rectangle clipRect = new Rectangle(350, 300);
		clipRect.setArcHeight(20.0);
		clipRect.setArcWidth(20.0);
		rootPane.setClip(clipRect);

		progressBar = new ProgressBar();
		loadingLabel = new Label("Loading");
		loadingActivity = new Label("Vic");
		
		addFadeIn(progressBar, fadeInDuration);
		addFadeIn(loadingLabel, fadeInDuration);
		addFadeIn(loadingActivity, fadeInDuration);

		ldBarEffect = new Rectangle(275, 25);
		ldBarEffect.setArcHeight(10.0);
		ldBarEffect.setArcWidth(10.0);

		ldBarEffect.setFill(Color.TRANSPARENT);
		ldBarEffect.setStrokeWidth(2);
		ldBarEffect.setStroke(Color.gray(1, 0.15));

		rootPane.getChildren().addAll(loadingLabel, progressBar, ldBarEffect, loadingActivity);
		rootPane.setAlignment(Pos.CENTER);

		StackPane.setMargin(progressBar, new Insets(100, 0, 0, 0));
		StackPane.setMargin(ldBarEffect, new Insets(100, 0, 0, 0));
		StackPane.setMargin(loadingLabel, new Insets(40, 0, 0, 0));
		StackPane.setMargin(loadingActivity, new Insets(150, 0, 0, 0));

		scene = new Scene(rootPane, 350, 300);
		scene.setFill(Color.TRANSPARENT);

		try 
		{
			URL url = this.getClass().getResource("main.css");
			if (url == null) 
			{
				Alert.displayAlert("Error", "Could not load resource: main.css");
				System.exit(-1);
			}
			String css = url.toExternalForm();
			scene.getStylesheets().add(css);
		} 
		catch (Exception e) 
		{
			Alert.displayAlert("Error", "Could not load resource: main.css");
			System.exit(-1);
		}
	}

	void addFadeIn(Node node, Duration dur)
	{
		FadeTransition fade = new FadeTransition(dur, node);
		fade.setFromValue(0.0);
		fade.setToValue(1.0);
		fadeIns.add(fade);
	}

	public void fadeOut() {

		FadeTransition fT1 = new FadeTransition(Duration.millis(2000), loadingLabel);
		fT1.setFromValue(1.0);
		fT1.setToValue(0.0);
		fT1.play();

		FadeTransition fT3 = new FadeTransition(Duration.millis(2000), backgroundImageView);
		fT3.setFromValue(0.3);
		fT3.setToValue(0.0);
		fT3.play();

		FadeTransition fT4 = new FadeTransition(Duration.millis(2000), ldBarEffect);
		fT4.setFromValue(0.3);
		fT4.setToValue(0.0);
		fT4.play();

		FadeTransition fT5 = new FadeTransition(Duration.millis(2000), progressBar);
		fT5.setFromValue(1.0);
		fT5.setToValue(0.0);
		fT5.setOnFinished((EventHandler<ActionEvent>) callOnFinished);
		fT5.play();
	}

	public void fadeIn() {

		FadeTransition fadeTransition = new FadeTransition(Duration.millis(2200), progressBar);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.45);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.setAutoReverse(true);
		fadeTransition.play();

		FadeTransition fT1 = new FadeTransition(Duration.millis(2200), loadingLabel);
		fT1.setFromValue(1.0);
		fT1.setToValue(0.2);
		fT1.setCycleCount(Animation.INDEFINITE);
		fT1.setAutoReverse(true);
		fT1.play();

		FadeTransition fT2 = new FadeTransition(Duration.millis(2200), ldBarEffect);
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

		ImageView backgroundImageView = new ImageView(getClass().getResource("background.jpg").toExternalForm());

		backgroundImageView.setOpacity(0.3);
		rootPane.getChildren().add(backgroundImageView);
		StackPane.setMargin(backgroundImageView, new Insets(0, 0, 0, 1300));
		backgroundImageView.toBack();

		scaleTransition1 = new ScaleTransition(Duration.seconds(20), backgroundImageView);
		scaleTransition1.setByX(0.5f);
		scaleTransition1.setByY(0.5f);
		scaleTransition1.setCycleCount(Animation.INDEFINITE);
		scaleTransition1.setAutoReverse(true);
		scaleTransition1.play();

		for (Animation fade : fadeIns) 
		{
			fade.play();
		}
	
	}

	// public void display (Stage window){
	// fadeIn();
	// window.setScene(scene1);
	// }

	public void load() {

	}

	public void onFinished(Object callback) {
		callOnFinished = callback;
	}

	@Override
	public void onLoadingUpdate(String activity, String latest) 
	{
				
	}

}
