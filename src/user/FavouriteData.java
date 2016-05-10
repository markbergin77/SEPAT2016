package user;

import java.io.Serializable;

/* Any field needed by the Favourite  class
 * that isn't the Station object itself.
 * The need for this is imposed by the gui,
 * and I won't try to explain that here. */
public class FavouriteData implements Serializable
{
	Integer timesViewed;
	public FavouriteData(int timesViewed) 
	{
		this.timesViewed = timesViewed;
		// TODO Auto-generated constructor stub
	}
}
