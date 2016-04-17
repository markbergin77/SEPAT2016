package gui;

import data.StationList;
import user.User;

public class HomeInitInfo 
{
	StationList allStations;
	User user;
	public HomeInitInfo(StationList allStations, User user) 
	{
		this.user = user;
		this.allStations = allStations;
	}
	public StationList getAllStations() 
	{
		return allStations;
	}
	
	
}
