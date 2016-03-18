package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Controller {
	
	Vector<Location> locations = new Vector<Location>();
	String locationURLs = "/home/uni/git/SEPAT2016/src/backend/locations";
	
	public void createObjectFile() {
		String locationURL;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(locationURLs))) {
			int i = 0;
			while((locationURL = bufferedReader.readLine()) != null) {
				JsonObject rootObject = new JsonParser().parse(new BufferedReader(new InputStreamReader(new URL(locationURL).openStream())))
						.getAsJsonObject().getAsJsonObject("observations");
				
				JsonObject header = rootObject.getAsJsonArray("header").get(0).getAsJsonObject();
				// Only getting first element to construct file of object skeletons
				JsonObject data = rootObject.getAsJsonArray("data").get(0).getAsJsonObject();
				
				Location newLocation = new Location(header.get("name").getAsString(), locationURL, data.get("lat").getAsString(), data.get("lon").getAsString(), header.get("state").getAsString());
				locations.addElement(newLocation);
				i++;
				System.out.print(i+"\r");
			}
		}
		// TODO
		catch (JsonIOException e) {}
		catch (JsonSyntaxException e) {}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		
		try (ObjectOutputStream out = new ObjectOutputStream(
		        new BufferedOutputStream(new FileOutputStream("src/backend/objects")))) {
			
			out.writeObject(locations);
		}
		// TODO
		catch (IOException e) {};
	}
	
	public void readObjectFile() {
		try (ObjectInputStream in = new ObjectInputStream(
		        new BufferedInputStream(new FileInputStream("src/backend/objects")))) {
			
			locations = (Vector<Location>) in.readObject();
			
		}
		// TODO
		catch (FileNotFoundException e) {}
		catch (IOException e) {System.out.println(e.getMessage());}
		catch (ClassNotFoundException e) {}
	}
	
	public Vector<Location> getLocations() {
		return locations;
	}
	
	public void fetchData(Location location) {
		String locationURL = location.getURL();
		
		try {
			JsonArray rootArray = new JsonParser().parse(new BufferedReader(new InputStreamReader(new URL(locationURL).openStream())))
					.getAsJsonObject().getAsJsonObject("observations").getAsJsonArray("data");
			for (JsonElement element: rootArray) {
				Readings newReadings = null;
				JsonObject reading = element.getAsJsonObject();
				String localDateTime = reading.get("local_date_time").getAsString();
				String localDateTimeFull = reading.get("local_date_time_full").getAsString();
				String apparentT = reading.get("apparent_t").getAsString();
				String cloud = reading.get("cloud").getAsString();
				String gustKmh = reading.get("gust_kmh").getAsString();
				String gustKt = reading.get("gust_kt").getAsString();
				String airTemp = reading.get("air_temp").getAsString();
				String relHumidity = reading.get("rel_hum").getAsString();
				String dewPt = reading.get("dewpt").getAsString();
				String windDir = reading.get("wind_dir").getAsString();
				String windSpdKmh = reading.get("wind_spd_kmh").getAsString();
				String windSpdKt = reading.get("wind_spd_kt").getAsString();
				
				newReadings = new Readings(localDateTime,  localDateTimeFull, apparentT, cloud, gustKmh, gustKt, airTemp, relHumidity, dewPt,
						windDir, windSpdKmh, windSpdKt);
				location.getData().add(newReadings);
			}
		}
		// TODO
		catch (JsonIOException e) {}
		catch (JsonSyntaxException e) {}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		
	}

}
