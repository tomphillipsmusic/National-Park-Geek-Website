package com.techelevator;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.JdbcParkDao;
import com.techelevator.npgeek.model.JdbcSurveyDao;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDao;
import com.techelevator.npgeek.model.SurveyResult;

public class JdbcSurveyDaoTest {

	private static SingleConnectionDataSource dataSource;
	private static SurveyDao surveyDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		surveyDao = new JdbcSurveyDao(dataSource);
		Survey survey1 = new Survey();
		String parkCode = "YNP";
		survey1.setEmailAddress("mstuart85@gmail.com");
		survey1.setParkCode(parkCode);
		survey1.setActivityLevel("Sedentary");
		survey1.setState("Michigan");
		surveyDao.saveToDB(survey1);
		Survey survey2= new Survey();
		parkCode = "ENP";
		survey2.setEmailAddress("mstuart85@gmail.com");
		survey2.setParkCode(parkCode);
		survey2.setActivityLevel("Inactive");
		survey2.setState("Rhode Island");
		surveyDao.saveToDB(survey2);
		Survey survey3 = new Survey();
		parkCode = "YNP2";
		survey3.setEmailAddress("mstuart85@gmail.com");
		survey3.setParkCode(parkCode);
		survey3.setActivityLevel("Sedentary");
		survey3.setState("Michigan");
		surveyDao.saveToDB(survey3);
		Survey survey4 = new Survey();
		parkCode = "RMNP";
		survey4.setEmailAddress("mstuart85@gmail.com");
		survey4.setParkCode(parkCode);
		survey4.setActivityLevel("Active");
		survey4.setState("Colorado");
		surveyDao.saveToDB(survey4);
		Survey survey5 = new Survey();
		parkCode = "GNP";
		survey5.setEmailAddress("mstuart85@gmail.com");
		survey5.setParkCode(parkCode);
		survey5.setActivityLevel("Inactive");
		survey5.setState("New Hampshire");
		surveyDao.saveToDB(survey5);
		

		
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
	public void getAllSurveysReturnsSubmittedSurvey() {
		Survey survey = new Survey();
		survey.setParkCode("ENP");
		survey.setEmailAddress("mstuart85@gmail.com");
		survey.setState("MI");
		survey.setActivityLevel("Sedentary");
		surveyDao.saveToDB(survey);
		List<Survey> surveys = surveyDao.getAllSurveys();
		String expectedEmailAddress = "mstuart85@gmail.com";
		String actualEmailAddress = surveys.get(surveys.size() - 1).getEmailAddress();
		assertEquals(expectedEmailAddress, actualEmailAddress);
		
		}
	
	@Test
	public void getTop5SurveysReturns5Surveys() {
		List<SurveyResult> surveyResults = surveyDao.getTop5Surveys();
		int expectedSize =5;
		int actualSize =5;
		assertEquals(expectedSize, actualSize);
		
	}
	
	@Test 
	public void getTop5SurveysReturnsCorrectListByNumberOfVotes() {
		List<SurveyResult> surveyResults = surveyDao.getTop5Surveys();
		assertTrue(surveyResults.get(0).getNumberOfVotes() >= surveyResults.get(1).getNumberOfVotes());
		assertTrue(surveyResults.get(1).getNumberOfVotes() >= surveyResults.get(2).getNumberOfVotes());
		assertTrue(surveyResults.get(2).getNumberOfVotes() >= surveyResults.get(3).getNumberOfVotes());
		assertTrue(surveyResults.get(3).getNumberOfVotes() >= surveyResults.get(4).getNumberOfVotes());
		
		
	}
}
