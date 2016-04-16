package gui;

import javafx.scene.input.MouseEvent;

public interface GuiCallbacks 
{
	abstract void onStationClicked(StationButton button);
	abstract void onSearch(StationButtonsPane list, String searchTerm);
}
