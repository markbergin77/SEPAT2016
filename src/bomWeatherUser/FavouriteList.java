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
	
	//Sorts the favourites  on a number of views basis.
		public void SortFavourites(FavouriteList list)
			{	
				boolean notSorted = true;
				while (notSorted)
				{
					notSorted = false;
					for(int base = 0; base < list.size() - 1; base++)
					{
						if(list.get(base).timesViewed() < list.get(base+1).timesViewed())
							{
								Favourite temp = list.get(base);
								list.set(base,list.get(base + 1));
								list.set(base + 1, temp);
								notSorted = true;
							}	
					}
				}
						
			}

}
