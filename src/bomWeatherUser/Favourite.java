package bomWeatherUser;

import java.io.IOException;
import java.io.Serializable;

import bomData.Station;

public class Favourite implements Serializable
{
	/**
	 * 
	 */
	// Integer is serializable
	private Integer timesViewed;
	Station loc;
	
	private Favourite(Station loc, int timesViewed) 
	{
		this.loc = loc;
		this.timesViewed = timesViewed; 
	}
	
	public int timesViewed()
	{
		return timesViewed;
	}
	
	//Add favorite to vector from initialized vector and matched station.
	public static Favourite create(Station loc)
	{
		return new Favourite(loc, 0);	
	}
	
	
	public Station getLoc()
	{
		return loc;
	}
	
	
	//Function for returning the favourite as a station (to use in weather stations
	//Remember to increment times Viewed by one
	public Station view()
	{
		++timesViewed;
		return loc;
	}
	
	public String toString()
	{
		String output = "Favourite:\nstation: ";
		output += loc.getName();
		return output;
	}
	
	
		
}
