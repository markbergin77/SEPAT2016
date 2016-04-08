package bomData;

import java.util.Vector;

public class StationList extends Vector<Station>
{
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
