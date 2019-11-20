package com.techelevator.npgeek.model;

public class Weather {

	private String parkCode;
	private int fiveDayForecastValue;
	private int lowTemperature;
	private int highTemperature;
	private String forecast;
	private String forecastReccomendation;
	private String imageName;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}

	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

	public int getLowTemperature() {
		return lowTemperature;
	}

	public void setLowTemperature(int lowTemperature) {
		this.lowTemperature = lowTemperature;
	}

	public int getHighTemperature() {
		return highTemperature;
	}

	public void setHighTemperature(int highTemperature) {
		this.highTemperature = highTemperature;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getForecastReccomendation() {
		return forecastReccomendation;
	}

	public void setForecastReccomendation() {
		this.forecastReccomendation = "";
		if (forecast.equals("snow")) {
			this.forecastReccomendation = this.forecastReccomendation + "Make sure to pack snowshoes! ";
		}
		if (forecast.equals("rain")) {
			this.forecastReccomendation = this.forecastReccomendation
					+ "Make sure to pack rain gear and wear waterproof shoes! ";
		}
		if (forecast.equals("thunderstorms")) {
			this.forecastReccomendation = this.forecastReccomendation
					+ "Seek shelter and avoid hiking on exposed ridges! ";
		}
		if (forecast.equals("sunny")) {
			this.forecastReccomendation = this.forecastReccomendation + "Pack sunblock! ";
		}
		if (highTemperature > 75) {
			this.forecastReccomendation = this.forecastReccomendation + "Bring an extra gallon of water! ";
		}
		if (highTemperature - lowTemperature > 20) {
			this.forecastReccomendation = this.forecastReccomendation + "Wear breathable layers! ";
		}
		if (lowTemperature < 20) {
			this.forecastReccomendation = this.forecastReccomendation
					+ "Exposure to temperatures this low can be dangerous! ";

		}
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName() {
		this.imageName = this.forecast + ".png";
		if (this.forecast.equals("partly cloudy")) {
			this.imageName = "partlyCloudy.png";
		}

	}

}
