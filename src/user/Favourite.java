package user;

import java.io.Serializable;

import data.Station;

public class Favourite implements Serializable
{
	// Integer is serializable
	FavouriteData favData;
	Station station;
	
	public Favourite(Station loc, int timesViewed) 
	{
		this.station = loc;
		this.favData = new FavouriteData(timesViewed); 
	}
	
	public boolean equals(Favourite other)
	{
		return station.equals(other.station);
	}
	
	public int timesViewed()
	{
		return favData.timesViewed;
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
		++favData.timesViewed;
		return station;
	}
	
	@Override
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

	public FavouriteData getData() 
	{
		return favData;
	}		
}
