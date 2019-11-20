package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.techelevator.npgeek.model.TemperatureConverter;

public class TemperatureConverterTest {

	private TemperatureConverter temperatureConverter = new TemperatureConverter();

	@Test
	public void zeroDegreesCelciusReturns32DegreesFahrenheit() {
		int celciusTemperature = 0;
		int expectedResult = 32;
		int actualResult = temperatureConverter.celciusToFahrenheit(celciusTemperature);
		System.out.println(actualResult);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void thirtyTwoDegreesFahrenheitReturnsZeroDegreesCelcius() {
		int fahrenheitTemperature = 32;
		int expectedResult = 0;
		int actualResult = temperatureConverter.fahrenheitToCelcius(fahrenheitTemperature);
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void thirtyDegreesCelciusReturns86DegreesFahrenheit() {
		int celciusTemperature = 30;
		int expectedResult = 86;
		int actualResult = temperatureConverter.celciusToFahrenheit(celciusTemperature);
		System.out.println(actualResult);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void twentyThreeDegreesFahrenheitReturnsMinus5DegreesCelcius() {
		int fahrenheitTemperature = 23;
		int expectedResult = -5;
		int actualResult = temperatureConverter.fahrenheitToCelcius(fahrenheitTemperature);
		assertEquals(expectedResult, actualResult);

	}
}
