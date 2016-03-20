package bomData;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONScraper {
	public static void main(String[] args) throws IOException {
		// RMIT Proxy Settings
		// System.setProperty("http.proxyHost", "aproxy.rmit.edu.au");
		// System.setProperty("http.proxyPort", "8080");
		
		String[] states = {"vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant"};
		String locationURLs = "src/bomData/locations";
		String relURL;
		String location;
		
		BufferedWriter out = new BufferedWriter(new FileWriter(locationURLs));
		
		for(String state: states) {
			Document doc = Jsoup.connect("http://www.bom.gov.au/" + state + "/observations/" + state + "all.shtml").get();
			Elements tbodies = doc.select("tbody");
			Elements links = tbodies.select("a");
			for (Element link: links) {
				relURL = link.attr("href");
				if (relURL.contains("products") && !relURL.contains("#")) {
					relURL = "http://www.bom.gov.au" + relURL.replace("products", "fwo").replace("shtml", "json");
					location = link.text();
					out.write(location + "|" + relURL + "\n");
				}
				
	
				
			}
		}
		
	}
}
