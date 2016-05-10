package EXPGUI.Layouts.GuiElements.ExplorerPane;

import EXPGUI.Gui.Main;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class TabPaneCreator extends Parent {

    private Tab allStationsTab,favsTab;


    public  TabPane getTabLarge(TabPane tab) {

        ScrollPaneCreator scrollPaneCreator = new ScrollPaneCreator();
        scrollPaneCreator.setId("scrollpanecreator");

        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll = scrollPaneCreator.getScroll(stationsScroll, 1);

        tab.setId("tabpane");

//        StackPane scrollpane = new StackPane();
//        scrollpane.setPrefSize(420,400);
//
//        Rectangle rect = new Rectangle(420,500);
//        rect.setArcHeight(20);
//        rect.setArcWidth(20);
//        //  explorerRect.setOpacity(0);
//        rect.setStroke(Color.LIGHTSLATEGRAY);
//        rect.setFill(Color.rgb(38, 38, 38));
//        rect.setStrokeWidth(2);


//        scrollpane.getChildren().addAll(rect,stationsScroll);

        this.allStationsTab = new Tab();
        allStationsTab.setId("allstationstab");
        allStationsTab.setText("All stations");

        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(420, 615);

        this.favsTab = new Tab();
        favsTab.setId("favstab");
        favsTab.setText("Favourites");
        StackPane favsPane = new StackPane();
        favsPane.setPrefSize(420, 615);

        allStationsTab.setClosable(false);
        favsTab.setClosable(false);

        TextField searchBar = new TextField();

        // creating a search bar
        searchBar.setMaxSize(180, 25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        // setting padding and adding the scrollbar and searchbar
        //to the tab pane

        allStationsPane.getChildren().addAll(stationsScroll,searchBar,scrollPaneCreator);
        allStationsPane.setPadding(new Insets(0, 5, 10, 5));
        StackPane.setMargin(searchBar, new Insets(0, 217, 540, 0));
        StackPane.setMargin(stationsScroll, new Insets(0,5,0,0));

        allStationsTab.setContent(allStationsPane);
        tab.getTabs().addAll(allStationsTab, favsTab);
        tab.setTabMinWidth(185);
        tab.setTabMinHeight(35);


        return tab;
    }

    public static TabPane getTabMedium(TabPane tab) {

        return tab;
    }

    public static TabPane getTabSmall(TabPane tab) {

        return tab;
    }

    public Tab getAllStationsTab() {
        return allStationsTab;
    }

    public Tab getFavsTab() {
        return favsTab;
    }


}
