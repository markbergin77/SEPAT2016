package bomData;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

public class JSONScraper 
{
	public static void main(String[] args) throws IOException 
	{
		LocationList locations = scrapeLocations();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a search word:");
		String search = br.readLine();
		Vector<Location> foundLocs = locations.fuzzySearch(search);
		Location match = foundLocs.get(0);
		System.out.println(match.getName() + " selected.\nFirst weather sample:");
		Vector<WthrSample> samples = match.getSamples();
		System.out.print(samples.get(0));
	}
	
	public static LocationList scrapeLocations() throws IOException {
		// RMIT Proxy Settings
		// System.setProperty("http.proxyHost", "aproxy.rmit.edu.au");
		// System.setProperty("http.proxyPort", "8080");
		LocationList locations = new LocationList();
		String[] states = {"vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant"};
		String url;
		String name;
		
		for(String state: states) {
			Document doc = Jsoup.connect("http://www.bom.gov.au/" + state + "/observations/" + state + "all.shtml").get();
			Elements tbodies = doc.select("tbody");
			Elements links = tbodies.select("a");
			for (Element link: links) {
				url = link.attr("href");
				if (url.contains("products") && !url.contains("#")) {
					url = "http://www.bom.gov.au" + url.replace("products", "fwo").replace("shtml", "json");
					name = link.text();
					// Some names have an asterisk on the page
					if(name.endsWith("*"))
					{
						name = name.substring(0, name.length()-1);
					}
					locations.add(new Location(name, url, state));
				}			
			}
		}
		return locations;
	}
}
