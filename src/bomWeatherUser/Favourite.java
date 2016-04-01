package bomWeatherUser;

import java.io.IOException;
import java.io.Serializable;

import bomData.Location;

public class Favourite implements Serializable
{
	/**
	 * 
	 */
	// Integer is serializable
	private Integer timesViewed;
	Location loc;
	
	private Favourite(Location loc, int timesViewed) 
	{
		this.loc = loc;
		this.timesViewed = timesViewed; 
	}
	
	public int timesViewed()
	{
		return timesViewed;
	}
	
	//Add favorite to vector from initialized vector and matched location.
	public static Favourite create(Location loc)
	{
		return new Favourite(loc, 0);	
	}
	
	
	public Location getLoc()
	{
		return loc;
	}
	
	
	//Function for returning the favourite as a Location (to use in weather locations
	//Remember to increment times Viewed by one
	public Location view()
	{
		++timesViewed;
		return loc;
	}
	
	public String toString()
	{
		String output = "Favourite:\nLocation: ";
		output += loc.getName();
		return output;
	}
}
