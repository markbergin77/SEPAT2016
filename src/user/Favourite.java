package user;

import java.io.IOException;
import java.io.Serializable;

import data.Station;

public class Favourite implements Serializable
{
	// Integer is serializable
	private Integer timesViewed;
	Station station;
	
	private Favourite(Station loc, int timesViewed) 
	{
		this.station = loc;
		this.timesViewed = timesViewed; 
	}
	
	public int timesViewed()
	{
		return timesViewed;
	}
	
	public static Favourite create(Station loc)
	{
		return new Favourite(loc, 0);	
	}
	
	public Station getLoc()
	{
		return station;
	}
	
	//Function for returning the favourite as a station (to use in weather stations
	//Remember to increment times Viewed by one
	public Station view()
	{
		++timesViewed;
		return station;
	}
	
	public String toString()
	{
		String output = "Favourite:\nstation: ";
		output += station.getName();
		return output;
	}

	public Station getStation() 
	{
		return station;
	}		
}
