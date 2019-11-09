package com.techelevator.campground.jdbc;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{
	private JdbcTemplate jdbcTemplate;
	
	public JDBCParkDAO (DataSource dataSource) {
		this.jdbcTemplate  = new JdbcTemplate(dataSource);
	}
	
	public List<String> gatherParkInfo() {
	List<String> parkinfo = new ArrayList<String>();  // WE ARE DOING THIS 
	
	return parkinfo;
	}
	
	public List<Park> findAllParks() {
		List<Park> allParks = new ArrayList<Park>();
		String sql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM Park ORDER BY name;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Park park = new Park();
			park.setParkId(results.getInt("park_id"));
			park.setName(results.getString("name"));
			park.setLocation(results.getString("location"));
			park.setEstablishDate(results.getDate("establish_date").toLocalDate());
			park.setArea(results.getInt("area"));
			park.setVisitors(results.getInt("visitors"));
			park.setDescription(results.getString("description"));
			allParks.add(park);
		}
		return allParks;
	}
	
/*	public List<String> createParkNameArray() {
		List<String> parkNameAndId = new ArrayList<String>();
		String sql = "SELECT name FROM park ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			parkNameAndId.add(results.getString("name"));
			
		}
		return parkNameAndId;	
	}
*/
	
	@Override
	public Park findParkID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Park findParkName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Park findLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocalDate findEstablishDate(LocalDate establishDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Park findArea(int area) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Park findVisitors(int visitors) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Park findDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}
}
