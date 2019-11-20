package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.techelevator.npgeek.model.Weather;

public class WeatherTest {

	private Weather weather = new Weather();

	@Test
	public void setImageNameAddsDotPng() {
		weather.setForecast("sunny");
		weather.setImageName();
		String expected = "sunny.png";
		String actual = weather.getImageName();
		assertEquals(expected, actual);

	}
	@Test
	public void setImageTurnsPartlyCloudyIntoCamelCase() {
		weather.setForecast("partly cloudy");
		weather.setImageName();
		String expected = "partlyCloudy.png";
		String actual = weather.getImageName();
		assertEquals(expected, actual);

	}
	@Test
	public void snowSetsCorrectReccomendation() {
		weather.setHighTemperature(32);
		weather.setLowTemperature(32);
		weather.setForecast("snow");
		weather.setForecastReccomendation();
		String expected = "Make sure to pack snowshoes! ";
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}

	@Test
	public void rainSetsCorrectReccomendation() {
		weather.setHighTemperature(32);
		weather.setLowTemperature(32);
		weather.setForecast("rain");
		weather.setForecastReccomendation();
		String expected = "Make sure to pack rain gear and wear waterproof shoes! ";
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}

	@Test
	public void thunderstormsSetsCorrectReccomendation() {
		weather.setHighTemperature(32);
		weather.setLowTemperature(32);
		weather.setForecast("thunderstorms");
		weather.setForecastReccomendation();
		String expected = "Seek shelter and avoid hiking on exposed ridges! ";
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}

	@Test
	public void sunnySetsCorrectReccomendation() {
		weather.setHighTemperature(75);
		weather.setLowTemperature(75);
		weather.setForecast("sunny");
		weather.setForecastReccomendation();
		String expected = "Pack sunblock! ";
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}
	
	@Test
	public void greaterThan75SetsCorrectReccomendation() {
		weather.setHighTemperature(76);
		weather.setLowTemperature(75);
		weather.setForecast("sunny");
		weather.setForecastReccomendation();
		String expected = "Pack sunblock! Bring an extra gallon of water! "; 
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}
	
	@Test
	public void lowerThan75SetsCorrectReccomendation() {
		weather.setHighTemperature(39);
		weather.setLowTemperature(19);
		weather.setForecast("partly cloudy");
		weather.setForecastReccomendation();
		String expected = "Exposure to temperatures this low can be dangerous! "; 
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}
	
	@Test
	public void degreeDifferenceGreaterThan20SetsCorrectReccomendation() {
		weather.setHighTemperature(65);
		weather.setLowTemperature(44);
		weather.setForecast("partly cloudy");
		weather.setForecastReccomendation();
		String expected = "Wear breathable layers! "; 
		String actual = weather.getForecastReccomendation();
		assertEquals(expected, actual);
	}
	
	
}
