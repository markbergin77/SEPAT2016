package bomData;

//Data types/attributes provided by BOM data, used for Monthly Observations
public class WthrSampleCoarse
{
	private String date;
	private String minTemp;
	private String maxTemp;
	private String rain;
	private String evap;
	private String sun;
	private String maxWindGustDir;
	private String maxWindGustSpd;
	private String maxWindGustTime;
	private String temp9am;
	private String relHumidity9am;
	private String cloud9am;
	private String windDir9am;
	private String windSpd9am;
	private String meanSeaLevelPressure9am;
	private String temp3pm;
	private String relHumidity3pm;
	private String cloud3pm;
	private String windDir3pm;
	private String windSpd3pm;
	private String meanSeaLevelPressure3pm;

	public WthrSampleCoarse(String date, String minTemp, String maxTemp, String rain, String evap,
			String sun, String maxWindGustDir, String maxWindGustSpd, String maxWindGustTime, String temp9am,
			String relHumidity9am, String cloud9am, String windDir9am, String windSpd9am,
			String meanSeaLevelPressure9am, String temp3pm, String relHumidity3pm, String cloud3pm, String windDir3pm,
			String windSpd3pm, String meanSeaLevelPressure3pm)
	{
		this.date = date;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.rain = rain;
		this.evap = evap;
		this.sun = sun;
		this.maxWindGustDir = maxWindGustDir;
		this.maxWindGustSpd = maxWindGustSpd;
		this.maxWindGustTime = maxWindGustTime;
		this.temp9am = temp9am;
		this.relHumidity9am = relHumidity9am;
		this.cloud9am = cloud9am;
		this.windDir9am = windDir9am;
		this.windSpd9am = windSpd9am;
		this.meanSeaLevelPressure9am = meanSeaLevelPressure9am;
		this.temp3pm = temp3pm;
		this.relHumidity3pm = relHumidity3pm;
		this.cloud3pm = cloud3pm;
		this.windDir3pm = windDir3pm;
		this.windSpd3pm = windSpd3pm;
		this.meanSeaLevelPressure3pm = meanSeaLevelPressure3pm;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getMaxTemp()
	{
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp)
	{
		this.maxTemp = maxTemp;
	}

	public String getMinTemp()
	{
		return minTemp;
	}

	public void setMinTemp(String minTemp)
	{
		this.minTemp = minTemp;
	}

	public String getRain()
	{
		return rain;
	}

	public void setRain(String rain)
	{
		this.rain = rain;
	}

	public String getEvap()
	{
		return evap;
	}

	public void setEvap(String evap)
	{
		this.evap = evap;
	}

	public String getSun()
	{
		return sun;
	}

	public void setSun(String sun)
	{
		this.sun = sun;
	}

	public String getMaxWindGustDir()
	{
		return maxWindGustDir;
	}

	public void setMaxWindGustDir(String maxWindGustDir)
	{
		this.maxWindGustDir = maxWindGustDir;
	}

	public String getMaxWindGustSpd()
	{
		return maxWindGustSpd;
	}

	public void setMaxWindGustSpd(String maxWindGustSpd)
	{
		this.maxWindGustSpd = maxWindGustSpd;
	}

	public String getMaxWindGustTime()
	{
		return maxWindGustTime;
	}

	public void setMaxWindGustTime(String maxWindGustTime)
	{
		this.maxWindGustTime = maxWindGustTime;
	}

	public String getTemp9am()
	{
		return temp9am;
	}

	public void setTemp9am(String temp9am)
	{
		this.temp9am = temp9am;
	}

	public String getRelHumidity9am()
	{
		return relHumidity9am;
	}

	public void setRelHumidity9am(String relHumidity9am)
	{
		this.relHumidity9am = relHumidity9am;
	}

	public String getCloud9am()
	{
		return cloud9am;
	}

	public void setCloud9am(String cloud9am)
	{
		this.cloud9am = cloud9am;
	}

	public String getWindDir9am()
	{
		return windDir9am;
	}

	public void setWindDir9am(String windDir9am)
	{
		this.windDir9am = windDir9am;
	}

	public String getWindSpd9am()
	{
		return windSpd9am;
	}

	public void setWindSpd9am(String windSpd9am)
	{
		this.windSpd9am = windSpd9am;
	}

	public String getMeanSeaLevelPressure9am()
	{
		return meanSeaLevelPressure9am;
	}

	public void setMeanSeaLevelPressure9am(String meanSeaLevelPressure9am)
	{
		this.meanSeaLevelPressure9am = meanSeaLevelPressure9am;
	}

	public String getTemp3pm()
	{
		return temp3pm;
	}

	public void setTemp3pm(String temp3pm)
	{
		this.temp3pm = temp3pm;
	}

	public String getRelHumidity3pm()
	{
		return relHumidity3pm;
	}

	public void setRelHumidity3pm(String relHumidity3pm)
	{
		this.relHumidity3pm = relHumidity3pm;
	}

	public String getCloud3pm()
	{
		return cloud3pm;
	}

	public void setCloud3pm(String cloud3pm)
	{
		this.cloud3pm = cloud3pm;
	}

	public String getWindDir3pm()
	{
		return windDir3pm;
	}

	public void setWindDir3pm(String windDir3pm)
	{
		this.windDir3pm = windDir3pm;
	}

	public String getWindSpd3pm()
	{
		return windSpd3pm;
	}

	public void setWindSpd3pm(String windSpd3pm)
	{
		this.windSpd3pm = windSpd3pm;
	}

	public String getMeanSeaLevelPressure3pm()
	{
		return meanSeaLevelPressure3pm;
	}

	public void setMeanSeaLevelPressure3pm(String meanSeaLevelPressure3pm)
	{
		this.meanSeaLevelPressure3pm = meanSeaLevelPressure3pm;
	}
}
