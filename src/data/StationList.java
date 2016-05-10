package data;

import java.util.Vector;

public class StationList extends Vector<Station>
{
	/*Sorting location list based on user string recognition
	  ...User searches for desired location*/
	public StationList fuzzySearch(String key)
	{
		StationList stationsMatching = new StationList();
		for (int i = 0; i < size(); ++i)
		{
			Station iLoc = this.get(i);
			if (iLoc.getName().toLowerCase().contains(key.toLowerCase()))
			{
				stationsMatching.add(iLoc);
			}
		}
		return stationsMatching;
	}

}
