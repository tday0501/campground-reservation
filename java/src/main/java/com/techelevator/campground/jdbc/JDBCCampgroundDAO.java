package com.techelevator.campground.jdbc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCCampgroundDAO (DataSource dataSource) {
		this.jdbcTemplate  = new JdbcTemplate(dataSource);
	}
	
	public List<Campground> findAllCampgrounds() {
		List<Campground> allCampgrounds = new ArrayList<Campground>();
		String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Campground campground = new Campground();
			campground.setCampgroundId(results.getInt("campground_id"));
			campground.setParkId(results.getInt("park_id"));
			campground.setName(results.getString("name"));
			campground.setOpenFromMonth(Integer.parseInt(results.getString("open_from_mm")));
			campground.setOpenToMonth(Integer.parseInt(results.getString("open_to_mm")));
			campground.setDailyFee(results.getBigDecimal("daily_fee"));
			allCampgrounds.add(campground);
		}
		return allCampgrounds;
	}
	

	@Override
	public List<Campground> findCampgroundsByParkId(int id) {
		List<Campground> campgroundList = new ArrayList<Campground>();
		String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while (results.next()) {
			Campground campground = new Campground();
			campground.setCampgroundId(results.getInt("campground_id"));
			campground.setParkId(results.getInt("park_id"));
			campground.setName(results.getString("name"));
			campground.setOpenFromMonth(Integer.parseInt(results.getString("open_from_mm")));
			campground.setOpenToMonth(Integer.parseInt(results.getString("open_to_mm")));
			campground.setDailyFee(results.getBigDecimal("daily_fee"));
			campgroundList.add(campground);
		}
		return campgroundList;
	}
	
	@Override
	public Campground findCampgroundName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate findOpenMonth(LocalDate openmonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate findCloseMonth(LocalDate closemonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal findDailyFee(BigDecimal dailyfee) {
		// TODO Auto-generated method stub
		return null;
	}

}
