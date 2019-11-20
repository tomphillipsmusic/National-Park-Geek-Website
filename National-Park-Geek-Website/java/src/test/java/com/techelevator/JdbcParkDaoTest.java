package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.JdbcParkDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;

public class JdbcParkDaoTest {

	private static SingleConnectionDataSource dataSource;
	private static ParkDao parkDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		parkDao = new JdbcParkDao(dataSource);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void getAllParksReturnsListOfSizeTen() {
		 List<Park> allParks = parkDao.getAllParks();
		 int expectedSize = 10;
		 int actualSize = allParks.size();
		 assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void parkCodeRMNPReturnsRockyMountainNationalPark() {
		String parkCode = "RMNP";
		Park rockyMountainPark = parkDao.getParkByParkCode(parkCode);
		String expectedName = "Rocky Mountain National Park";
		String actualName = rockyMountainPark.getName();
		assertEquals(expectedName, actualName);

	}
	
	@Test
	public void mapRowToParkMapsYosemiteCorrectly() {
		String parkCode = "YNP2";
		Park yosemite = parkDao.getParkByParkCode(parkCode);
		assertEquals("YNP2", yosemite.getParkCode());
		assertEquals("Yosemite National Park", yosemite.getName());
		assertEquals("California", yosemite.getState());
		assertTrue(747956 == yosemite.getAcreage());
		assertTrue(5000 == yosemite.getElevationInFeet());
		assertTrue(800 == yosemite.getMilesOfTrail());
		assertTrue(1720 == yosemite.getNumberOfCampsites());
		assertEquals("Forest", yosemite.getClimate());
		assertEquals(1890, yosemite.getYearFounded());
		assertEquals(3882642, yosemite.getAnnualVisitorCount());
		assertEquals("Yosemite Valley, to me, is always a sunrise, a glitter of green and golden wonder in a vast edifice of stone and space.", yosemite.getQuote());
		assertEquals("Ansel Adams", yosemite.getQuoteSource());
		assertEquals("Yosemite National Park vividly illustrates the effects of glacial erosion of granitic bedrock, creating geologic features that are unique in the world. Repeated glaciations over millions of years have resulted in a concentration of distinctive landscape features, including soaring cliffs, domes, and free-falling waterfalls. There is exceptional glaciated topography, including the spectacular Yosemite Valley, a 914-meter (1/2 mile) deep, glacier-carved cleft with massive sheer granite walls. These geologic features provide a scenic backdrop for mountain meadows and giant sequoia groves, resulting in a diverse landscape of exceptional natural and scenic beauty.", yosemite.getDescription());
		assertEquals(420, yosemite.getNumberOfAnimalSpecies());
		assertEquals(15, yosemite.getEntryFee());
	}
	
}


