package bomWeatherUser;

import java.util.Vector;

public class FavouriteList extends Vector<Favourite> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4022414816209237935L;

	//Initialize vector of favourites
	public static FavouriteList create()
	{
		FavouriteList favourites = new FavouriteList();
		return favourites;
	}

}
