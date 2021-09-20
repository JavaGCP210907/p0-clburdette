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
			log.info("get employee db access failed");
			e.printStackTrace();
		}
		return null;
	}

	public List<Owner> getOwnersByName(String fname, String lname) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql;
			PreparedStatement ps;
			
			if(fname == "" && lname == "") {
				return getOwners();
			}else if(fname == ""){
				sql = "SELECT * FROM owners WHERE l_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, lname);
				rs = ps.executeQuery();
			}else if(lname == ""){
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
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Owner> getOwnersByZip(int zipcode) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM owners WHERE zipcode = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, zipcode);
			rs = ps.executeQuery();
			
			return ownerResultSetToOwnerList(rs);
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get parks db access failed");
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
			log.info("get parks db access failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addOwner(Owner owner) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Owner> ownerResultSetToOwnerList(ResultSet rs){
		List<Owner> ownerList = new ArrayList<>();
		try {
			while(rs.next()) {
				Owner o = new Owner(
					rs.getInt("owner_id"),
					rs.getString("f_name"),
					rs.getString("l_name"),
					rs.getInt("zipcode"),
					rs.getInt("homepark_fk")
					);
				ownerList.add(o);					
			}
			return ownerList;
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owner db access failed");
			e.printStackTrace();
		}
		return null;	
	}

}
