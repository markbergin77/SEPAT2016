package bomData;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Use this class to communicate with the 
 * bureau of meteorology and get information
 * about it.
 */

public class Bom 
{
	enum State { vic, nsw, tas, wa, sa, nt, qld, ant };
	
	static String[] bomStateAliases()
	{
		String[] states = { "vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant" };
		return states;
	}
	
	/* The URL of a shtml page that contains a table 
	 * of all of the state's stations. */
	static String stateAllUrl(State state)
	{
		String stateStr = stateName(state);
		return "http://www.bom.gov.au/" + stateStr + "/observations/" + stateStr + "all.shtml";
	}
	// TO DO - ARCHIVE   MARK JOB ONLY 
	public static StationList getAllStations() throws IOException
	{
		StationList stations = new StationList();
		for (State state : State.values()) {
			stations.addAll(getStations(state));
		}
		return stations;
	}
	// Adds station details to separate list, 
	public static StationList getAllStations(LoadingUpdater progressNotifier) throws IOException
	{
		StationList stations = new StationList();
		//Also provides info for user on current state
		for (State state : State.values())
		{
			progressNotifier.loadingUpdate("Loading " + state + " stations");
			stations.addAll(getStations(state));
		}
		progressNotifier.loadingUpdate("");
		return stations;
	}
	//Grabs station location, saves details to object instances
	public static StationList getStations(State state) throws IOException
	{
		//Use of Jsoup Framework
		StationList stations = new StationList();
		Document doc = Jsoup.connect(stateAllUrl(state))
				.get();
		Elements tbodies = doc.select("tbody");
		Elements links = tbodies.select("a");
		for (Element link : links)
		{
			String url = link.attr("href");
			if (url.contains("products") && !url.contains("#"))
			{
				String htmlUrl = "http://www.bom.gov.au" + url;
				String jsonUrl = 
						"http://www.bom.gov.au" + url.replace("products", "fwo").replace("shtml", "json");
				String name = link.text();
				// Some names have an asterisk on the page
				if (name.endsWith("*"))
				{
					name = name.substring(0, name.length() - 1);
				}
				// Returns station of Observations
				stations.add(new Station(name, jsonUrl, htmlUrl, stateName(state)));
				
			}
		}
		return stations;
	}
	
	//List of fixed states needed to find appropriate stations
	static String stateName(State state)
	{
		String stateStr = "";
		switch (state)
		{
		case ant:
			stateStr = "ant";
			break;
		case nsw:
			stateStr = "nsw";
			break;
		case nt:
			stateStr = "nt";
			break;
		case qld:
			stateStr = "qld";
			break;
		case sa:
			stateStr = "sa";
			break;
		case tas:
			stateStr = "tas";
			break;
		case vic:
			stateStr = "vic";
			break;
		case wa:
			stateStr = "wa";
			break;
		default:
			return null;
		}
		return stateStr;
	}
}
