package bomWeatherGui;

import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.css.Rect;

import bomData.LoadingUpdater;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
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

public class SplashScreen implements LoadingUpdater
{
	Scene scene;
	StackPane rootPane;
	ProgressBar progressBar;
	Label loadingLabel;
	Label loadingActivity;
	Rectangle ldBarEffect;
	ImageView backgroundImageView;
	
	Duration fadeInDuration = Duration.millis(1000);
	Duration fadeOutDuration = Duration.millis(1000);

	Object loadingUpdateCallback;
	EventHandler<ActionEvent> onFinished;

	Vector<FadeTransition> fadeIns = new Vector<FadeTransition>();
	Vector<FadeTransition> fadeOuts = new Vector<FadeTransition>();

	public Scene getScene() 
	{
		return scene;
	}

	public SplashScreen() 
	{
		rootPane = new StackPane();

		Rectangle clipRect = new Rectangle(350, 300);
		clipRect.setArcHeight(20.0);
		clipRect.setArcWidth(20.0);
		rootPane.setClip(clipRect);

		backgroundImageView = 
				new ImageView(getClass().getResource("background.jpg").toExternalForm());
		backgroundImageView.setOpacity(0.3);
		backgroundImageView.toBack();
		
		progressBar = new ProgressBar();
		loadingLabel = new Label("Loading");
		loadingActivity = new Label("Vic");
		
		loadingLabel.setId("loadingLabel");
		loadingActivity.setId("loadingActivity");
		
		ldBarEffect = new Rectangle(275, 25);
		ldBarEffect.setArcHeight(10.0);
		ldBarEffect.setArcWidth(10.0);
		
		ldBarEffect.setFill(Color.TRANSPARENT);
		ldBarEffect.setStrokeWidth(2);
		ldBarEffect.setStroke(Color.gray(1, 0.15));
		
		addFadeInOut(progressBar, fadeInDuration);
		addFadeInOut(loadingLabel, fadeInDuration);
		addFadeInOut(loadingActivity, fadeInDuration);
		addFadeInOut(ldBarEffect, fadeInDuration);
		addFadeOut(backgroundImageView, fadeInDuration);

		rootPane.getChildren().addAll(loadingLabel, progressBar, 
				ldBarEffect, loadingActivity, backgroundImageView);
		rootPane.setAlignment(Pos.CENTER);

		StackPane.setMargin(progressBar, new Insets(100, 0, 0, 0));
		StackPane.setMargin(ldBarEffect, new Insets(100, 0, 0, 0));
		StackPane.setMargin(loadingLabel, new Insets(40, 0, 0, 0));
		StackPane.setMargin(loadingActivity, new Insets(150, 0, 0, 0));
		StackPane.setMargin(backgroundImageView, new Insets(0, 0, 0, 1300));

		scene = new Scene(rootPane, 350, 300);
		scene.setFill(Color.TRANSPARENT);

		loadCss();
	}
	
	void loadCss()
	{
		try 
		{
			URL url = this.getClass().getResource("splash.css");
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
	
	void addFadeOut(Node node, Duration dur)
	{
		FadeTransition fadeOut = new FadeTransition(dur, node);
		fadeOuts.add(fadeOut);
	}
	
	void addFadeInOut(Node node, Duration dur)
	{
		addFadeIn(node, dur);
		addFadeOut(node, dur);
	}

	public void finish() 
	{
		fadeOuts.lastElement().setOnFinished(onFinished);
		for (FadeTransition fade : fadeOuts)
		{
			Node node = fade.getNode();
			fade.setFromValue(node.getOpacity());
			fade.setToValue(0.0);
			fade.play();
		}		
	}

	private void startAnims()
	{
		FadeTransition ldBarCyclicFade = 
				new FadeTransition(Duration.millis(2200), progressBar);
		ldBarCyclicFade.setFromValue(1.0);
		ldBarCyclicFade.setToValue(0.45);
		ldBarCyclicFade.setCycleCount(Animation.INDEFINITE);
		ldBarCyclicFade.setAutoReverse(true);
		ldBarCyclicFade.play();

		FadeTransition loadingLabelFade = 
				new FadeTransition(Duration.millis(2200), loadingLabel);
		loadingLabelFade.setFromValue(1.0);
		loadingLabelFade.setToValue(0.2);
		loadingLabelFade.setCycleCount(Animation.INDEFINITE);
		loadingLabelFade.setAutoReverse(true);
		loadingLabelFade.play();

		FadeTransition ldBarEffectCycle = 
				new FadeTransition(Duration.millis(2200), ldBarEffect);
		ldBarEffectCycle.setFromValue(1.0);
		ldBarEffectCycle.setToValue(0.0);
		ldBarEffectCycle.setCycleCount(Animation.INDEFINITE);
		ldBarEffectCycle.setAutoReverse(false);
		ldBarEffectCycle.play();

		ScaleTransition ldBarEffectCyclicScale = 
				new ScaleTransition(Duration.millis(2200), ldBarEffect);
		ldBarEffectCyclicScale.setByX(0.1f);
		ldBarEffectCyclicScale.setByY(0.9f);
		ldBarEffectCyclicScale.setCycleCount(Animation.INDEFINITE);
		ldBarEffectCyclicScale.setAutoReverse(false);
		ldBarEffectCyclicScale.play();

		ldBarEffectCyclicScale = new ScaleTransition(Duration.seconds(20), backgroundImageView);
		ldBarEffectCyclicScale.setByX(0.5f);
		ldBarEffectCyclicScale.setByY(0.5f);
		ldBarEffectCyclicScale.setCycleCount(Animation.INDEFINITE);
		ldBarEffectCyclicScale.setAutoReverse(true);
		ldBarEffectCyclicScale.play();
	}
	
	public void begin()
	{
		fadeIns.lastElement().setOnFinished(e -> startAnims());
		for (Animation fade : fadeIns) 
		{
			fade.play();
		}
	}

	public void setOnFinished(EventHandler<ActionEvent> callback) 
	{
		onFinished = callback;
	}

	@Override
	public void loadingUpdate(String latestTask) 
	{
		// loadingActivity is a JavaFx node 
		// so must be modified later 
		// on the ui thread
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				    loadingActivity.setText(latestTask);                       
				  }
				});
	}
}
