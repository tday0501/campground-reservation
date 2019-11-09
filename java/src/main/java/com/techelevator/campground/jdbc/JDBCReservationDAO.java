package com.techelevator.campground.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {


	private JdbcTemplate jdbcTemplate;
	
	public JDBCReservationDAO (DataSource dataSource) {
		this.jdbcTemplate  = new JdbcTemplate(dataSource);
	}
	
	public List<Reservation> findAvailableReservations(int campgroundChoice, LocalDate arrivalChoice, LocalDate departureChoice) {
		List<Reservation> availableReservations = new ArrayList<Reservation>();
		String sql = "SELECT site.site_id, site.max_occupancy, site.accessible, site.max_rv_length, site.utilities, campground.daily_fee" + 
				" FROM site" + 
				" LEFT JOIN campground ON site.campground_id = campground.campground_id" + 
				" WHERE site.campground_id = ?" + 
				" AND site.site_id NOT IN" + 
				" (SELECT site_id FROM reservation WHERE from_date <= ? and to_date >= ?) ORDER BY site.site_id LIMIT 5;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundChoice, Date.valueOf(arrivalChoice), Date.valueOf(departureChoice));
		while (results.next()) {
			Reservation reservation = new Reservation();
			//reservation.setReservationId(results.getInt("reservation_id"));
			reservation.setSiteId(results.getInt("site_id"));
			reservation.setName(results.getString("name"));
			reservation.setFromDate(results.getDate("from_date").toLocalDate());
			reservation.setToDate(results.getDate("to_date").toLocalDate());
			reservation.setCreateDate(results.getDate("create_date").toLocalDate());
			availableReservations.add(reservation);
		}
		return availableReservations;
	}
	
	
	public Reservation createReservation(int site_id, String name, LocalDate arrivalChoice, LocalDate departureChoice) {
		LocalDate reservationMadeOnDate = getDate();
		String sql = "INSERT INTO reservation (site_Id, name, from_date, to_date, create_date)"
				+ " VALUES (?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, site_id, name, Date.valueOf(arrivalChoice), Date.valueOf(departureChoice), Date.valueOf(reservationMadeOnDate));
		Reservation reservation = new Reservation();
		sql = "SELECT reservation_id FROM reservation WHERE name = ? and site_id = ? and from_date = ? and to_date = ? and create_date = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name, site_id,  Date.valueOf(arrivalChoice), Date.valueOf(departureChoice), Date.valueOf(reservationMadeOnDate));
		if (result.next()) {
		reservation.setReservationId(Integer.parseInt(result.getString("reservation_Id")));
		reservation.setSiteId(site_id);
		reservation.setName(name);
		reservation.setFromDate(arrivalChoice);
		reservation.setToDate(departureChoice);
		reservation.setCreateDate(reservationMadeOnDate);
		}
		return reservation;
	}
	
	private LocalDate getDate() {
		LocalDate myDateObj = LocalDate.now();
		return myDateObj;
	}
	
	private Object getReservationCampground(Reservation reservation) {

		return reservation;
	}
	
	@Override
	public Reservation findResId(int ResId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findSiteId(int SiteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate findFromDate(LocalDate fromDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate findToDate(LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate createDate(LocalDate createDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
