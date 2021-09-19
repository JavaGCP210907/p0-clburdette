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
			String sql = "SELECT * FROM employees";
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			List<Park> parkList = new ArrayList<>();
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
			log.info("get employee db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Park> getParksByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Park> getParksByZip(int zipcode) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM employees WHERE zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			List<Park> parkList = new ArrayList<>();
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
			log.info("get employee db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addPark(Park park) {
		// TODO Auto-generated method stub
		
	}

}
