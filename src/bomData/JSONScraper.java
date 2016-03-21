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
		LocationList locations = LocationList.getAllFromServer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a search word:");
		String search = br.readLine();
		LocationList foundLocs = locations.fuzzySearch(search);
		Location match = foundLocs.get(0);
		System.out.println(match.getName() + " selected.\nFirst weather sample:");
		Vector<WthrSample> samples = match.getSamples();
		System.out.print(samples.get(0));
	}
}
