package com.techelevator.campground.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO (DataSource dataSource) {
		this.jdbcTemplate  = new JdbcTemplate(dataSource);
	}
	
	public List<Site> findAvailableSites(int campgroundChoice, LocalDate arrivalChoice, LocalDate departureChoice) {
		List<Site> availableSites = new ArrayList<Site>();
		try  {
			String sql = "SELECT site.site_number, site.max_occupancy, site.accessible, site.max_rv_length, site.utilities, campground.daily_fee" + 
					" FROM site" + 
					" LEFT JOIN campground ON site.campground_id = campground.campground_id" + 
					" WHERE site.campground_id = ?" + 
					" AND site.site_id NOT IN" + 
					" (SELECT site_id FROM reservation WHERE from_date <= ? and to_date >= ?) ORDER BY site.site_id LIMIT 5;";
			
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundChoice, Date.valueOf(arrivalChoice), Date.valueOf(departureChoice));
			while (results.next()) {
				Site site = new Site();
				site.setSiteNum(results.getInt("site_number"));
				site.setMaxOccupancy(results.getInt("max_occupancy"));
				site.setAccessible(results.getBoolean("accessible"));
				site.setMaxRV(results.getInt("max_rv_length"));
				site.setUtilities(results.getBoolean("utilities"));
				site.setDailyFee(results.getBigDecimal("daily_fee"));
				availableSites.add(site);
			}
		} catch (NullPointerException e) {
				
		}
		return availableSites;
	}

	@Override
	public Site findSiteId(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site findName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site findSiteNum(int siteNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site findMaxOcc(int maxOcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccessible(boolean accessible) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Site findMaxRVLength(int maxRV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean utilities(boolean utilities) {
		// TODO Auto-generated method stub
		return false;
	}

}
