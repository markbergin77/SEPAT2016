package gui;

import java.util.Vector;
import data.LoadingUpdater;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/* Creates the gui components necessary to
display a splash screen. Currently displays
the splash screen immediately upon construction.
I expect that to change at some point. */
public class SplashScreen implements LoadingUpdater
{
	Stage window;
	Scene scene;
	StackPane rootPane;
	ProgressBar progressBar;
	Label loadingLabel;
	Label loadingActivity;
	Rectangle ldBarEffect;
	
	Duration fadeInDuration = Duration.millis(1);
	Duration fadeOutDuration = Duration.millis(1);

	Object loadingUpdateCallback;
	EventHandler<ActionEvent> onClosed;

	Vector<FadeTransition> fadeIns = new Vector<FadeTransition>();
	Vector<FadeTransition> fadeOuts = new Vector<FadeTransition>();

	public Scene getScene() 
	{
		return scene;
	}
    //Splash screen details, user views when launching app
	public SplashScreen() 
	{
		window = new Stage();
		window.setOnCloseRequest(e -> System.exit(0));
		window.initStyle(StageStyle.UNDECORATED);
		rootPane = new StackPane();

		Rectangle clipRect = new Rectangle(350, 300);
		clipRect.setArcHeight(20.0);
		clipRect.setArcWidth(20.0);
		rootPane.setClip(clipRect);
		
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

		rootPane.getChildren().addAll(loadingLabel, progressBar, 
				ldBarEffect, loadingActivity);
		rootPane.setAlignment(Pos.CENTER);

		StackPane.setMargin(progressBar, new Insets(100, 0, 0, 0));
		StackPane.setMargin(ldBarEffect, new Insets(100, 0, 0, 0));
		StackPane.setMargin(loadingLabel, new Insets(40, 0, 0, 0));
		StackPane.setMargin(loadingActivity, new Insets(150, 0, 0, 0));

		scene = new Scene(rootPane, 350, 300);
		scene.setFill(Color.TRANSPARENT);
		window.setScene(scene);
		loadCss();
		startShowing();
	}
	
	void loadCss()
	{
		// Not using Css yet (simple)
	}
	//Calling fading animations
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
	//Used when application closes
	public void startClosing() 
	{
		fadeOuts.lastElement().setOnFinished(e -> 
		{
			rootPane.getChildren().clear();
			window.close();
			onClosed.handle(e);
		});
		for (FadeTransition fade : fadeOuts)
		{
			Node node = fade.getNode();
			fade.setFromValue(node.getOpacity());
			fade.setToValue(0.0);
			fade.play();
		}		
	}
    //Functions detailing animations
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

	}
		
	public void startShowing()
	{
		window.show();
		fadeIns.lastElement().setOnFinished(e -> startAnims());
		for (Animation fade : fadeIns) 
		{
			fade.play();
		}
	}

	public void setOnClosed(EventHandler<ActionEvent> callback) 
	{
		onClosed = callback;
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
