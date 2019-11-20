import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.JdbcWeatherDao;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDao;

public class JdbcWeatherDaoTest {
	
	private static SingleConnectionDataSource dataSource;
	private static WeatherDao weatherDao;

	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		weatherDao = new JdbcWeatherDao(dataSource);
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
	public void getWeatherByParkCodeReturnsCorrectWeather() {
		String parkCode = "RMNP";
		List<Weather> rockyMountainWeather = weatherDao.getWeatherByParkCode(parkCode);
		for (Weather weather: rockyMountainWeather) {
			String expectedParkCode = "RMNP";
			String actualParkCode = weather.getParkCode();
			assertEquals(expectedParkCode, actualParkCode);
		}
		
		int expectedSize = 5;
		int actualSize = rockyMountainWeather.size();
		assertEquals(expectedSize, actualSize);
	}
	
	
	
}
