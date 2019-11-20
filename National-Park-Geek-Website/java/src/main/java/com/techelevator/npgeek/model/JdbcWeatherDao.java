package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);

	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		String sqlSelectWeatherByParkCode = "SELECT * FROM weather WHERE parkcode= ?";
		List<Weather> weatherByParkCode = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectWeatherByParkCode, parkCode);
		while (results.next()) {
			Weather weather = mapRowResults(results);
			weatherByParkCode.add(weather);

		}
		return weatherByParkCode;
	}

	public Weather mapRowResults(SqlRowSet results) {
		Weather weather = new Weather();
		weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setForecast(results.getString("forecast"));
		weather.setHighTemperature(results.getInt("high"));
		weather.setLowTemperature(results.getInt("low"));
		weather.setParkCode(results.getString("parkCode"));
		weather.setImageName();
		weather.setForecastReccomendation();
		return weather;
	}

}
