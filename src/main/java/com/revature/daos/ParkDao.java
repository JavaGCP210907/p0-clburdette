package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Park;
import com.revature.utils.ConnectionUtil;

public class ParkDao implements ParkDaoInterface{

	@Override
	public List<Park> getParks() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks";
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			
			return parkResultSetToParkList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Park> getParksByCity(String city) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE city = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, city);
			rs = ps.executeQuery();
			
			return parkResultSetToParkList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Park> getParksByZip(int zipcode) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, zipcode);
			rs = ps.executeQuery();
			
			return parkResultSetToParkList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Park> getParksByAmenities(boolean[] choices) {
		try(Connection conn = ConnectionUtil.getConnection()){

			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE ";
			for(int i = 0; i < choices.length; i++) {
				switch(i) {
				case 0:
					if(choices[i]) {
						sql += "shady = ?";
					}
					break;
				case 1:
					if(choices[i] && !choices[i -1]) {
						sql += "barkPark = ?";
					}else if(choices[i]) {
						sql += " AND barkPark = ?";
					}
					break;
				case 2:
					if(choices[i] && !choices[i - 1] && !choices[i - 2]) {
						sql += "playFields = ?";
					}else if(choices[i]) {
						sql += " AND playFields = ?";
					}
					break;
				case 3:
					if(choices[i] && !choices[i - 1] && !choices[i - 2] && !choices[i - 3]) {
						sql += "walkingTrails = ?";
					}else if(choices[i]) {
						sql += " AND walkingTrails = ?";
					}
					break;
				default:
					break;
				}
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i = 0; i < choices.length; i++) {
				ps.setBoolean(i+1, choices[i]);
			}
			rs = ps.executeQuery();
			
			return parkResultSetToParkList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get park db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addPark(Park park) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Park> parkResultSetToParkList(ResultSet rs){
		List<Park> parkList = new ArrayList<>();
		try {
			while(rs.next()) {
				Park p = new Park(
					rs.getInt("park_id"),
					rs.getInt("streetAddress"),
					rs.getString("streetName"),
					rs.getString("city"),
					rs.getInt("zipcode"),
					rs.getBoolean("shady"),
					rs.getBoolean("barkPark"),
					rs.getBoolean("playFields"),
					rs.getBoolean("walkingTrails")
					);
				parkList.add(p);					
			}
			return parkList;
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get park db access failed");
			e.printStackTrace();
		}
		return null;	
	}

}
