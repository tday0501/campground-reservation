package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
public Reservation findResId(int ResId);
public Reservation findSiteId(int SiteId);
public Reservation findName(String name);
public LocalDate findFromDate(LocalDate fromDate);
public LocalDate findToDate(LocalDate toDate);
public LocalDate createDate(LocalDate createDate);
public List<Reservation> findAvailableReservations(int campgroundChoice, LocalDate arrivalChoice, LocalDate departureChoice);
public Reservation createReservation(int site_id, String name, LocalDate arrivalChoice, LocalDate departureChoice);

}
