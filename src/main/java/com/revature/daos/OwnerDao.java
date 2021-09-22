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

import com.revature.models.Owner;
import com.revature.utils.ConnectionUtil;

public class OwnerDao implements OwnerDaoInterface{

	@Override
	public List<Owner> getOwners() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM owners";
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			
			return ownerResultSetToOwnerList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;
	}

	public List<Owner> getOwnersByName(String fname, String lname) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql;
			PreparedStatement ps;
			if(fname.equals("") && lname.equals("")) {
				return getOwners();
			}else if(fname.equals("")){
				sql = "SELECT * FROM owners WHERE l_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, lname);
				rs = ps.executeQuery();
			}else if(lname.equals("")){
				sql = "SELECT * FROM owners WHERE f_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, fname);
				rs = ps.executeQuery();
			}else{
				sql = "SELECT * FROM owners WHERE f_name = ? AND l_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, fname);
				ps.setString(2, lname);
				rs = ps.executeQuery();
			}
			
			return ownerResultSetToOwnerList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Owner> getOwnersByZip(String zipcode) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM owners WHERE zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, zipcode);
			rs = ps.executeQuery();
			
			return ownerResultSetToOwnerList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Owner> getOwnersByHomePark(int park) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM owners WHERE homepark_fk = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, park);
			rs = ps.executeQuery();
			
			return ownerResultSetToOwnerList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addOwner(Owner owner) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO owners (f_name, l_name, zipcode, homepark_fk)" +
					 "VALUES (?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, owner.getF_name());
			ps.setString(2, owner.getL_name());
			ps.setString(3, owner.getZipcode());
			ps.setInt(4, owner.getHomepark_fk());
			ps.executeUpdate();
			
			System.out.println("New owner:");
			System.out.println(owner);
			System.out.println("added to Owners database");
			
		}catch(SQLException e) {
			System.out.println("Add owner failed. Returning to main menu.");
			e.printStackTrace();
		}
	}
	
	public List<Owner> ownerResultSetToOwnerList(ResultSet rs){
		List<Owner> ownerList = new ArrayList<>();
		try {
			while(rs.next()) {
				Owner o = new Owner(
					rs.getInt("owner_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getString("zipcode"),
					rs.getInt("homepark_fk")
					);
				ownerList.add(o);					
			}
			return ownerList;
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;	
	}

}
