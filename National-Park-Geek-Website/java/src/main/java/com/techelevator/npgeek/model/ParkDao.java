package com.techelevator.npgeek.model;

import java.util.List;

import org.springframework.stereotype.Component;


public interface ParkDao {

	public List<Park> getAllParks();
	public Park getParkByParkCode(String parkCode);
}
