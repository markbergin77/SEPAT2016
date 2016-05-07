package dataTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.YearMonth;
import java.util.Vector;

import data.Bom;
import data.Station;
import data.StationList;
import data.samples.WthrSampleFine;

public class SelectStation 
{
	//Large unit test used in the first an second sprint
	public static void main(String[] args) throws IOException 
	{
		Station matched = findStation();
		// Example usage of getWthrLastMonth(date)
		// Vector<WthrSampleCoarse> coarseSamples = matched.getWthrLastMonth("201603");
		//prints most recent observation.
		System.out.println(matched.getName() + " selected.\nFirst weather sample:");
		//Queries all the data regarding station
		Vector<WthrSampleFine> samples = Bom.getWthrLast72hr(matched);
		System.out.print(samples.get(0));
	}
	
	public static Station findStation() throws IOException
	{
		Station match = null;
		int stationNumber = 1;	
		boolean stationFound = false;
		//Grabbing BOM stations/stations
	    StationList stations = Bom.getAllStations();
	    
	    // Rad's testing code
	    for (Station station: stations) {
	    	YearMonth month;
	    	YearMonth testDate = YearMonth.of(2016, 03);
	    	Bom.getWthrLastMonth(station, testDate);
	    }
	    
	    //User inputs station, searches for match
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));					
		System.out.println("Enter a search word:");
		String search = br.readLine();				
		//Works out number of potential stations
		StationList foundLocs = stations.fuzzySearch(search);
		for (Station stationSearch : foundLocs) 
		{
			System.out.println("Station " + stationNumber + " : " + stationSearch.getName());
			stationNumber++;
	    }
		//Determines if only one potential station
		if(foundLocs.size() != 1)
		{
			//User chooses based on list of stations	
			while(stationFound == false)
	    {
			try
			{
				System.out.println("Enter Correct Station");
				String matchedLoc = br.readLine();	
				match = foundLocs.get(Integer.parseInt(matchedLoc) - 1);
				stationFound = true;
			}catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Selected index not listed, please try again \n");
			}
	    }
			return  match;
		}
		//Else match is automatically chosen
		match = foundLocs.get(0);
		return match;
	}
	
}
