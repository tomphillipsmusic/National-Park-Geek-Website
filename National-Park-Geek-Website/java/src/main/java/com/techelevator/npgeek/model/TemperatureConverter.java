package com.techelevator.npgeek.model;

public class TemperatureConverter {

	public int fahrenheitToCelcius(int fahrenheitTemperature) {
		int celciusTemperature = (int) ((fahrenheitTemperature - 32) * (5.0 / 9.0));
		return celciusTemperature;

	}

	public int celciusToFahrenheit(int celciusTemperature) {
		int fahrenheitTemperature = (int) ((celciusTemperature * (9.0 / 5.0) + 32));
		return fahrenheitTemperature;
	}
}
