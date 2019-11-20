package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		String sqlSelectAllParks = "SELECT * FROM park";
		List<Park> allParks = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
		Park park = mapRowToPark(results);
		allParks.add(park);
		}

		return allParks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		String sqlSelectParkCode = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectParkCode, parkCode);
		Park park = new Park();
		while (results.next()) {
	    park = mapRowToPark(results);

		}
		
		return park;
	}

	public Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getDouble("acreage"));
		park.setElevationInFeet(results.getDouble("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setQuote(results.getString("inspirationalquote"));
		park.setQuoteSource(results.getString("inspirationalquotesource"));
		park.setDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));

		return park;

	}

}
