package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ParkDAO {
public Park findParkID (int id);
public Park findParkName(String name);
public Park findLocation(String location);
public LocalDate findEstablishDate(LocalDate establishDate);
public Park findArea(int area);
public Park findVisitors(int visitors);
public Park findDescription(String description);

//public List<String> createParkNameArray();
public List<Park> findAllParks();
}
