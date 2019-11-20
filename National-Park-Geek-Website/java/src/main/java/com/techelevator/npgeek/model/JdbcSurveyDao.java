package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveToDB(Survey survey) {
		long id = getNextSurveyId();
		String sqlInsertNewSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, " 
				+ "state, activitylevel) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertNewSurvey, id, survey.getParkCode(), survey.getEmailAddress(),
				survey.getState(), survey.getActivityLevel());

	}
	
	public long getNextSurveyId() {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT nextval('seq_surveyId')");
		results.next();
		long id = results.getLong(1);
		return id;
	}

	@Override
	public List<Survey> getAllSurveys() {
		String sqlSelectAllSurveys = "SELECT * FROM survey_result";
		List<Survey> allSurveys = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
		while (results.next()) {
			Survey survey = mapRowToSurvey(results);
			allSurveys.add(survey);
		}
		return allSurveys;
	}

	private Survey mapRowToSurvey(SqlRowSet results) {
		Survey survey = new Survey();
		survey.setSurveyId(results.getLong("surveyid"));
		survey.setParkCode(results.getString("parkcode"));
		survey.setEmailAddress(results.getString("emailaddress"));
		survey.setState(results.getString("state"));
		survey.setActivityLevel(results.getString("activitylevel"));

		return survey;

	}

	@Override
	public List<SurveyResult> getTop5Surveys() {
		List<SurveyResult> top5SurveyResults = new ArrayList<>();
		String sqlSelectTop5Parks = "SELECT park.parkcode, park.parkname, COUNT(survey_result.parkcode) AS numberOfVotes\r\n" + 
				" FROM survey_result JOIN park ON survey_result.parkcode = park.parkcode \r\n" + 
				" GROUP BY park.parkname, park.parkcode ORDER BY numberOfVotes DESC, park.parkname LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTop5Parks);
		while (results.next()) {
			SurveyResult surveyResult = new SurveyResult();
			surveyResult.setParkCode(results.getString("parkcode"));
			surveyResult.setParkName(results.getString("parkname"));
			surveyResult.setNumberOfVotes(results.getInt("numberofvotes"));
			top5SurveyResults.add(surveyResult);
		}
		
		return top5SurveyResults;
	}
	
	
}
