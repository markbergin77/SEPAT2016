package gui.plots;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.javafx.tk.TKStage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/* Convenience, takes a plot as constructor
 * argument and takes care of details
 * also state is savable to User file via
 * PlotWindowsOpen collection class */
public class PlotWindow
	implements Serializable
{
	Stage stage;
	Scene scene;
	PlotBase plot;
	
	public PlotWindow(PlotBase plot)
	{
		stage = new Stage();
		this.plot = plot;
		scene = new Scene(plot);
		scene.getStylesheets().add(plot.getCssPath());
		setScene(scene);
		setTitle(plot.getName());
	}
	public PlotBase getPlot() 
	{
		return plot;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(plot);
		out.writeDouble(stage.getX());
		out.writeDouble(stage.getY());
		out.writeDouble(stage.getWidth());
		out.writeDouble(stage.getHeight());
		return;
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		stage = new Stage();
		plot = (PlotBase)in.readObject();
		scene = new Scene(plot);
		stage.setX(in.readDouble());
		stage.setY(in.readDouble());
		stage.setWidth(in.readDouble());
		stage.setHeight(in.readDouble());
		return;
	}
	
	/* Had to wrap all Stage methods to allow serialization/deserialization */
	public final <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter)
	{
		stage.addEventFilter(eventType, eventFilter);
	}
	public final <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler)
	{
		stage.addEventHandler(eventType, eventHandler);
	}
	public final ReadOnlyBooleanProperty alwaysOnTopProperty()
	{
		return stage.alwaysOnTopProperty();
	}
	public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail)
	{
		return stage.buildEventDispatchChain(tail);
	}
	public void centerOnScreen()
	{
		stage.centerOnScreen();
	}
	public void close()
	{
		stage.close();
	}
	public final ObjectProperty<EventDispatcher> eventDispatcherProperty()
	{
		return stage.eventDispatcherProperty();
	}
	public final void fireEvent(Event event)
	{
		stage.fireEvent(event);
	}
	public final ReadOnlyBooleanProperty focusedProperty()
	{
		return stage.focusedProperty();
	}
	public final ObjectProperty<String> fullScreenExitHintProperty()
	{
		return stage.fullScreenExitHintProperty();
	}
	public final ObjectProperty<KeyCombination> fullScreenExitKeyProperty()
	{
		return stage.fullScreenExitKeyProperty();
	}
	public final ReadOnlyBooleanProperty fullScreenProperty()
	{
		return stage.fullScreenProperty();
	}
	public final EventDispatcher getEventDispatcher()
	{
		return stage.getEventDispatcher();
	}
	public final String getFullScreenExitHint()
	{
		return stage.getFullScreenExitHint();
	}
	public final KeyCombination getFullScreenExitKeyCombination()
	{
		return stage.getFullScreenExitKeyCombination();
	}
	public final double getHeight()
	{
		return stage.getHeight();
	}
	public final ObservableList<Image> getIcons()
	{
		return stage.getIcons();
	}
	public final double getMaxHeight()
	{
		return stage.getMaxHeight();
	}
	public final double getMaxWidth()
	{
		return stage.getMaxWidth();
	}
	public final double getMinHeight()
	{
		return stage.getMinHeight();
	}
	public final double getMinWidth()
	{
		return stage.getMinWidth();
	}
	public final Modality getModality()
	{
		return stage.getModality();
	}
	public final EventHandler<WindowEvent> getOnCloseRequest()
	{
		return stage.getOnCloseRequest();
	}
	public final EventHandler<WindowEvent> getOnHidden()
	{
		return stage.getOnHidden();
	}
	public final EventHandler<WindowEvent> getOnHiding()
	{
		return stage.getOnHiding();
	}
	public final EventHandler<WindowEvent> getOnShowing()
	{
		return stage.getOnShowing();
	}
	public final EventHandler<WindowEvent> getOnShown()
	{
		return stage.getOnShown();
	}
	public final double getOpacity()
	{
		return stage.getOpacity();
	}
	public final Window getOwner()
	{
		return stage.getOwner();
	}
	public final ObservableMap<Object, Object> getProperties()
	{
		return stage.getProperties();
	}
	public final Scene getScene()
	{
		return stage.getScene();
	}
	public final StageStyle getStyle()
	{
		return stage.getStyle();
	}
	public final String getTitle()
	{
		return stage.getTitle();
	}
	public Object getUserData()
	{
		return stage.getUserData();
	}
	public final double getWidth()
	{
		return stage.getWidth();
	}
	public final double getX()
	{
		return stage.getX();
	}
	public final double getY()
	{
		return stage.getY();
	}
	public boolean hasProperties()
	{
		return stage.hasProperties();
	}
	public int hashCode()
	{
		return stage.hashCode();
	}
	public final ReadOnlyDoubleProperty heightProperty()
	{
		return stage.heightProperty();
	}
	public void hide()
	{
		stage.hide();
	}
	public final ReadOnlyBooleanProperty iconifiedProperty()
	{
		return stage.iconifiedProperty();
	}
	public String impl_getMXWindowType()
	{
		return stage.impl_getMXWindowType();
	}
	public TKStage impl_getPeer()
	{
		return stage.impl_getPeer();
	}
	public void impl_setImportant(boolean arg0)
	{
		stage.impl_setImportant(arg0);
	}
	public void impl_setPrimary(boolean arg0)
	{
		stage.impl_setPrimary(arg0);
	}
	public final void initModality(Modality modality)
	{
		stage.initModality(modality);
	}
	public final void initOwner(Window owner)
	{
		stage.initOwner(owner);
	}
	public final void initStyle(StageStyle style)
	{
		stage.initStyle(style);
	}
	public final boolean isAlwaysOnTop()
	{
		return stage.isAlwaysOnTop();
	}
	public final boolean isFocused()
	{
		return stage.isFocused();
	}
	public final boolean isFullScreen()
	{
		return stage.isFullScreen();
	}
	public final boolean isIconified()
	{
		return stage.isIconified();
	}
	public final boolean isMaximized()
	{
		return stage.isMaximized();
	}
	public final boolean isResizable()
	{
		return stage.isResizable();
	}
	public final boolean isShowing()
	{
		return stage.isShowing();
	}
	public final DoubleProperty maxHeightProperty()
	{
		return stage.maxHeightProperty();
	}
	public final DoubleProperty maxWidthProperty()
	{
		return stage.maxWidthProperty();
	}
	public final ReadOnlyBooleanProperty maximizedProperty()
	{
		return stage.maximizedProperty();
	}
	public final DoubleProperty minHeightProperty()
	{
		return stage.minHeightProperty();
	}
	public final DoubleProperty minWidthProperty()
	{
		return stage.minWidthProperty();
	}
	public final ObjectProperty<EventHandler<WindowEvent>> onCloseRequestProperty()
	{
		return stage.onCloseRequestProperty();
	}
	public final ObjectProperty<EventHandler<WindowEvent>> onHiddenProperty()
	{
		return stage.onHiddenProperty();
	}
	public final ObjectProperty<EventHandler<WindowEvent>> onHidingProperty()
	{
		return stage.onHidingProperty();
	}
	public final ObjectProperty<EventHandler<WindowEvent>> onShowingProperty()
	{
		return stage.onShowingProperty();
	}
	public final ObjectProperty<EventHandler<WindowEvent>> onShownProperty()
	{
		return stage.onShownProperty();
	}
	public final DoubleProperty opacityProperty()
	{
		return stage.opacityProperty();
	}
	public final <T extends Event> void removeEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter)
	{
		stage.removeEventFilter(eventType, eventFilter);
	}
	public final <T extends Event> void removeEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler)
	{
		stage.removeEventHandler(eventType, eventHandler);
	}
	public final void requestFocus()
	{
		stage.requestFocus();
	}
	public final BooleanProperty resizableProperty()
	{
		return stage.resizableProperty();
	}
	public final ReadOnlyObjectProperty<Scene> sceneProperty()
	{
		return stage.sceneProperty();
	}
	public final void setAlwaysOnTop(boolean value)
	{
		stage.setAlwaysOnTop(value);
	}
	public final void setEventDispatcher(EventDispatcher value)
	{
		stage.setEventDispatcher(value);
	}
	public final void setFocused(boolean arg0)
	{
		stage.setFocused(arg0);
	}
	public final void setFullScreen(boolean value)
	{
		stage.setFullScreen(value);
	}
	public final void setFullScreenExitHint(String value)
	{
		stage.setFullScreenExitHint(value);
	}
	public final void setFullScreenExitKeyCombination(KeyCombination keyCombination)
	{
		stage.setFullScreenExitKeyCombination(keyCombination);
	}
	public final void setHeight(double value)
	{
		stage.setHeight(value);
	}
	public final void setIconified(boolean value)
	{
		stage.setIconified(value);
	}
	public final void setMaxHeight(double value)
	{
		stage.setMaxHeight(value);
	}
	public final void setMaxWidth(double value)
	{
		stage.setMaxWidth(value);
	}
	public final void setMaximized(boolean value)
	{
		stage.setMaximized(value);
	}
	public final void setMinHeight(double value)
	{
		stage.setMinHeight(value);
	}
	public final void setMinWidth(double value)
	{
		stage.setMinWidth(value);
	}
	public final void setOnCloseRequest(EventHandler<WindowEvent> value)
	{
		stage.setOnCloseRequest(value);
	}
	public final void setOnHidden(EventHandler<WindowEvent> value)
	{
		stage.setOnHidden(value);
	}
	public final void setOnHiding(EventHandler<WindowEvent> value)
	{
		stage.setOnHiding(value);
	}
	public final void setOnShowing(EventHandler<WindowEvent> value)
	{
		stage.setOnShowing(value);
	}
	public final void setOnShown(EventHandler<WindowEvent> value)
	{
		stage.setOnShown(value);
	}
	public final void setOpacity(double value)
	{
		stage.setOpacity(value);
	}
	public final void setResizable(boolean value)
	{
		stage.setResizable(value);
	}
	public final void setScene(Scene value)
	{
		stage.setScene(value);
	}
	public final void setTitle(String value)
	{
		stage.setTitle(value);
	}
	public void setUserData(Object value)
	{
		stage.setUserData(value);
	}
	public final void setWidth(double value)
	{
		stage.setWidth(value);
	}
	public final void setX(double value)
	{
		stage.setX(value);
	}
	public final void setY(double value)
	{
		stage.setY(value);
	}
	public final void show()
	{
		stage.show();
	}
	public void showAndWait()
	{
		stage.showAndWait();
	}
	public final ReadOnlyBooleanProperty showingProperty()
	{
		return stage.showingProperty();
	}
	public void sizeToScene()
	{
		stage.sizeToScene();
	}
	public final StringProperty titleProperty()
	{
		return stage.titleProperty();
	}
	public void toBack()
	{
		stage.toBack();
	}
	public void toFront()
	{
		stage.toFront();
	}
	public String toString()
	{
		return stage.toString();
	}
	public final ReadOnlyDoubleProperty widthProperty()
	{
		return stage.widthProperty();
	}
	public final ReadOnlyDoubleProperty xProperty()
	{
		return stage.xProperty();
	}
	public final ReadOnlyDoubleProperty yProperty()
	{
		return stage.yProperty();
	}
}
