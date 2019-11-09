package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

public class Campground {
	private int campgroundId;
	private int parkId;
	private String name;
	private int openFromMonth;
	private int openToMonth;
	private BigDecimal dailyFee;
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpenFromMonth() {
		return openFromMonth;
	}
	public String getOpenFromMonthName() {
		return new DateFormatSymbols().getMonths()[openFromMonth-1];
	}
	public void setOpenFromMonth(int month) {
		this.openFromMonth = month;
	}
	public int getOpenToMonth() {
		return openToMonth;
	}
	public String getOpenToMonthName() {
		return new DateFormatSymbols().getMonths()[openToMonth-1];
	}
	public void setOpenToMonth(int month) {
		this.openToMonth = month;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
