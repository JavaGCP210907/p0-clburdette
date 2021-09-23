package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	public Park getParkByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE park_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			List<Park> tempPark = parkResultSetToParkList(rs);
			return tempPark.get(0);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Park> getParksByName(String name){
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE parkName = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
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
	public List<Park> getParksByZip(String zipcode) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM parks WHERE zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, zipcode);
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

			int yeses = 0;
			for(int i = 0; i < choices.length; i++)
			{
				if(choices[i]) {yeses++;}
			}
			if(yeses == 0) {
				System.out.println("No amenities chosen. Showing all parks.");
				return getParks();
			}else{
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
				for(int i = 0; i < yeses; i++) {
					ps.setBoolean(i+1, true);
				}
				rs = ps.executeQuery();
			
				return parkResultSetToParkList(rs);
			}	
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get park db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addPark(Park park) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO parks (parkName, streetName, city, zipcode, shady, barkPark, playFields, walkingTrails)" +
					 "VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, park.getParkName());
			ps.setString(2, park.getStreetName());
			ps.setString(3, park.getCity());
			ps.setString(4, park.getZipcode());
			ps.setBoolean(5, park.isShady());
			ps.setBoolean(6, park.isBarkPark());
			ps.setBoolean(7, park.isPlayFields());
			ps.setBoolean(8, park.isWalkingTrails());
			ps.executeUpdate();
			
			System.out.println("New park:");
			System.out.println(park);
			System.out.println("added to Parks database");
			
		}catch(SQLException e) {
			System.out.println("Add park failed. Returning to main menu.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePark(Park park) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE parks" + 
						 "SET parkName = ?, streetName = ?, city = ?, zipcode = ?, shady = ?, barkPark = ?, playFields = ?, walkingTrails = ?" +
						 "WHERE park_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, park.getParkName());
			ps.setString(2, park.getStreetName());
			ps.setString(3, park.getCity());
			ps.setString(4, park.getZipcode());
			ps.setBoolean(5, park.isShady());
			ps.setBoolean(6, park.isBarkPark());
			ps.setBoolean(7, park.isPlayFields());
			ps.setBoolean(8, park.isWalkingTrails());
			ps.setInt(9, park.getPark_id());
			ps.executeUpdate();
			
			System.out.println("Park updated to:");
			System.out.println(park);
			
		}catch(SQLException e) {
			System.out.println("Update park failed. Returning to main menu.");
			e.printStackTrace();
		}
	}
	
	public void deletePark(Scanner scan)
	{
		try(Connection conn = ConnectionUtil.getConnection()){
			
			System.out.println("Please enter the ID number of the park you would like to delete:");
			int choice = scan.nextInt();
			
			String sql = "DELETE FROM parks WHERE park_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, choice);
			ps.executeUpdate();
			
			System.out.println("Park with ID number " + choice + " deleted.");
			
		}catch(SQLException e) {
			System.out.println("Delete park failed. Returning to parks menu.");
			e.printStackTrace();
		}
	}
	public List<Park> parkResultSetToParkList(ResultSet rs){
		List<Park> parkList = new ArrayList<>();
		try {
			while(rs.next()) {
				Park p = new Park(
					rs.getInt("park_id"),
					rs.getString("parkName"),
					rs.getString("streetName"),
					rs.getString("city"),
					rs.getString("zipcode"),
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
