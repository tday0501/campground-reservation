package com.techelevator;

import java.util.List;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.jdbc.JDBCParkDAO;
import com.techelevator.campground.jdbc.JDBCReservationDAO;
import com.techelevator.campground.jdbc.JDBCSiteDAO;
import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;
import com.techelevator.campground.view.Menu;

public class CampgroundCLI {

	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private ParkDAO parkDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;

	private static final String PARK_SUB_MENU_VIEW_CAMPGROUNDS = "View Campgrounds";
	//private static final String PARK_SUB_MENU_SEARCH_FOR_RESERVATION = "Search for Reservation";
	private static final String PARK_SUB_MENU_RETURN_TO_PREVIOUS_SCREEN = "Return to Previous Screen";
	private static final String[] PARK_SUB_MENU_OPTIONS = new String[] { PARK_SUB_MENU_VIEW_CAMPGROUNDS,
			PARK_SUB_MENU_RETURN_TO_PREVIOUS_SCREEN };

	private static final String CAMPGROUNDS_MENU_SEARCH_FOR_AVAILABLE_RESERVATION = "Search for Available Reservation";
	private static final String CAMPGROUNDS_MENU_RETURN_TO_PREVIOUS_SCREEN = "Return to Main Menu";
	private static final String[] CAMPGROUNDS_MENU_OPTIONS = new String[] {
			CAMPGROUNDS_MENU_SEARCH_FOR_AVAILABLE_RESERVATION, CAMPGROUNDS_MENU_RETURN_TO_PREVIOUS_SCREEN };

	public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);

	}

	public static void main(String[] args) {

		CampgroundCLI application = new CampgroundCLI();
		application.run();

	}

	public CampgroundCLI(DataSource datasource) {

	}

	public void run() {
		while (true) {
			System.out.print("Select a Park for Further Details");
			Object[] parkArray = parkDAO.findAllParks().toArray();
			Park parkChoice = (Park) menu.getChoiceFromOptions(parkArray);
			System.out.println("\n" + parkChoice.getName());
			System.out.printf("%-18s %s\n", "Location:", parkChoice.getLocation());
			System.out.printf("%-18s %2$tm/%2$td/%2$tY\n", "Established:", parkChoice.getEstablishDate());
			System.out.printf(Locale.US, "%-18s %,d sq km\n", "Area:", parkChoice.getArea());
			System.out.printf(Locale.US, "%-18s %,d\n\n", "Annual Visitors:", parkChoice.getVisitors());
			System.out.println(parkChoice.getDescription());
			while (true) {
				String choice = (String) menu.getChoiceFromOptions(PARK_SUB_MENU_OPTIONS);
				if (choice.equals(PARK_SUB_MENU_VIEW_CAMPGROUNDS)) {
					List<Campground> campgroundList = campgroundDAO.findCampgroundsByParkId(parkChoice.getParkId());
					System.out.printf("%6s %-35s %-10s %-10s %-10s\n", "", "Name", "Open", "Close", "Daily Fee");
					listCampgroundsInPark(campgroundList);
					while (true) {
						choice = (String) menu.getChoiceFromOptions(CAMPGROUNDS_MENU_OPTIONS);
						if (choice.equals(CAMPGROUNDS_MENU_SEARCH_FOR_AVAILABLE_RESERVATION)) {
							System.out.printf("%6s %-35s %-10s %-10s %-10s\n", "", "Name", "Open", "Close", "Daily Fee");
							listCampgroundsInPark(campgroundList);
							int campgroundChoice = (int) menu.promptUserForInt("Which campground (enter 0 to cancel)? >>> ");
							List<Site> siteList = new ArrayList<Site>();
							LocalDate arrivalChoice = null;
							LocalDate departureChoice = null;
							while (siteList.isEmpty()) {
								int count = 0;
								if (count > 0) {
									String repeatDateEntry = (String) menu.promptUserForString(
											"There are no reservations available within your chosen date range. Would you like to enter a different date range?"
											+ " (yes to re-enter dates, any other key to select a different campsite) ");
									if (repeatDateEntry.toLowerCase().equals("yes")) {
		
									} else {
										break;
									}
								}
								arrivalChoice = (LocalDate) menu.promptUserForDate("What is the arrival date? (mm/dd/yyyy) >>> ");
								departureChoice = (LocalDate) menu.promptUserForDate("What is the departure date? (mm/dd/yyyy) >>> ");
								siteList = siteDAO.findAvailableSites(campgroundChoice, arrivalChoice, departureChoice);
								count++;
							}
							int[] availableSiteIds = new int[5];
							int arrayIndex = 0;
							if (!siteList.isEmpty()) {
								System.out.printf("%-10s %-12s %-15s %-15s %-10s %-5s\n", "Site No.", "Max Occup.", "Accessible?",
										"Max RV Length", "Utility", "Cost");
								for (Site site : siteList) {
									
									System.out.printf("%-10d %-12d %-15s %-15s %-10s $%-5.2f\n", site.getSiteNum(),
											site.getMaxOccupancy(), site.getAccessibleAsYesNo(), site.getMaxRVAsString(),
											site.getUtilitiesAsYesNo(), site.getDailyFee());
									availableSiteIds[arrayIndex] = site.getSiteNum();
									arrayIndex++;
								}
								int siteReservation = (int) menu.promptUserForInt("Which site should be reserved (enter 0 to cancel)?  >>> ");
								for (int i : availableSiteIds) {
									if (i == siteReservation) {		//check if site_id is in siteList sites, if it is, do the following:
										String nameOfReservation = (String) menu.promptUserForString("What name should the reservation be made under? ");
										Reservation reservation = reservationDAO.createReservation(siteReservation, nameOfReservation, arrivalChoice, departureChoice);
										System.out.println("The reservation has been made, the confirmation id is " + reservation.getReservationId() + ".");
									}
								}
							}
						}
						if (choice.equals(CAMPGROUNDS_MENU_RETURN_TO_PREVIOUS_SCREEN)) {
							System.out.println();
							break;
						}
					}
				}
				if (choice.equals(PARK_SUB_MENU_RETURN_TO_PREVIOUS_SCREEN)) {
					break;
				}
			}
		}
	}

	/**
	 * @param campgroundArray
	 */
	private void listCampgroundsInPark(List<Campground> campgroundArray) {
		int count = 1;
		for (Campground campground : campgroundArray) {
			System.out.printf("#%-5d %-35s %-10s %-10s $%.2f\n", count, campground.getName(),
					campground.getOpenFromMonthName(), campground.getOpenToMonthName(), campground.getDailyFee());
			count++;
		}
	}

}
