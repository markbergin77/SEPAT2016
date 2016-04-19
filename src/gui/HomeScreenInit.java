package gui;

import data.StationList;
import user.User;
/* To avoid passing a million things into this
 * class' constructor, assemble all those things here */
public class HomeScreenInit 
{
	StationList allStations;
	User user;
	public HomeScreenInit(StationList allStations, User user) 
	{
		this.user = user;
		this.allStations = allStations;
	}
	public StationList getAllStations() 
	{
		return allStations;
	}
	
	
}
