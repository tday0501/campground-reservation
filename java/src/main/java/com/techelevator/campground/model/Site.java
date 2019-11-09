package com.techelevator.campground.model;

import java.math.BigDecimal;

public class Site {
	private int siteId;
	private int campgroundId;
	private int siteNum;
	private int maxOccupancy;
	private boolean accessible;
	private int maxRV;
	private boolean utilities;
	private BigDecimal dailyFee;
	
	public int getSiteId() {
		return siteId;
	}
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	public int getCampgroundId() {
		return campgroundId;
	}
	
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public int getSiteNum() {
		return siteNum;
	}
	
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	
	public boolean getAccessible() {
		return accessible;
	}
	
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	public String getAccessibleAsYesNo() {
		String accessibleYesNo = null;
		if (getAccessible()) {
			accessibleYesNo = "Yes";
		}
		else if (!getAccessible()) {
			accessibleYesNo = "No";
		}
		return accessibleYesNo;
	}
	
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public int getMaxRV() {
		return maxRV;
	}
	
	public void setMaxRV(int maxRV) {
		this.maxRV = maxRV;
	}
	
	public String getMaxRVAsString() {
		String maxRVString = null;
		if (maxRV == 0) {
			maxRVString = "N/A";
		}
		else {
			maxRVString = Integer.toString(getMaxRV());
		}
		return maxRVString;
	}
	
	public boolean getUtilities() {
		return utilities;
	}
	
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
	public String getUtilitiesAsYesNo() {
		String utilitiesYesNo = null;
		if (getUtilities()) {
			utilitiesYesNo = "Yes";
		}
		else if (!getUtilities()) {
			utilitiesYesNo = "N/A";
		}
		return utilitiesYesNo;
	}
	
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
}
