package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {

public Site findSiteId(int siteId);
public Site findName(String name);
public Site findSiteNum(int siteNum);
public Site findMaxOcc(int maxOcc);
public boolean isAccessible(boolean accessible);
public Site findMaxRVLength(int maxRV);
public boolean utilities(boolean utilities);
public List<Site> findAvailableSites(int campgroundChoice, LocalDate arrivalChoice, LocalDate departureChoice);

}
