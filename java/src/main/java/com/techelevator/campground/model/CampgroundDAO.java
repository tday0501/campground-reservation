package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CampgroundDAO {

	public Campground findCampgroundName(String name);
	public List<Campground> findCampgroundsByParkId(int id);
	public LocalDate findOpenMonth (LocalDate openmonth);
	public LocalDate findCloseMonth (LocalDate closemonth);
	public BigDecimal findDailyFee (BigDecimal dailyfee);
	public List<Campground> findAllCampgrounds();
	
	
}
	
