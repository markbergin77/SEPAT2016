package data.samples;

import java.time.LocalDateTime;

public class FioSampleFine {
	
	LocalDateTime time;
	String icon;
	private String apparentT;
	private String airTemp;
	private String relHumidity;
	private String dewPt;
	
	public FioSampleFine(LocalDateTime time, String icon, String apparentT, String airTemp, String relHumidity,
			String dewPt) {
		this.time = time;
		this.icon = icon;
		this.apparentT = apparentT;
		this.airTemp = airTemp;
		this.relHumidity = relHumidity;
		this.dewPt = dewPt;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	public String getIcon() {
		return icon;
	}
	public String getApparentT() {
		return apparentT;
	}
	public String getAirTemp() {
		return airTemp;
	}
	public String getRelHumidity() {
		return relHumidity;
	}
	public String getDewPt() {
		return dewPt;
	}
}
