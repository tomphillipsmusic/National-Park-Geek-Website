package com.techelevator.npgeek.model;

import java.util.List;

public interface SurveyDao {

	public void saveToDB(Survey survey);
	public List<Survey> getAllSurveys();
	public List<SurveyResult> getTop5Surveys();

}
