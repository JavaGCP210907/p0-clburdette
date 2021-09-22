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

import com.revature.models.Dog;
import com.revature.models.Owner;
import com.revature.utils.ConnectionUtil;

public class DogDao implements DogDaoInterface{

	@Override
	public List<Dog> getDogs() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM dogs";
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			
			return dogResultSetToDogList(rs);
			
		}catch(SQLException e) {
			System.out.println("get dogs db access failed.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Dog> getDogsByName(String name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM dogs WHERE name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			return dogResultSetToDogList(rs);
			
		}catch(SQLException e) {
			System.out.println("get dogs db access failed.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Dog> getDogsByBreed(String breed) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM dogs WHERE breed = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, breed);
			rs = ps.executeQuery();
			
			return dogResultSetToDogList(rs);
			
		}catch(SQLException e) {
			System.out.println("get dogs db access failed.");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Dog> getDogsByAge(int startAge, int endAge) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM dogs WHERE age BETWEEN ? AND ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, startAge);
			ps.setInt(2, endAge);
			rs = ps.executeQuery();
			
			return dogResultSetToDogList(rs);
			
		}catch(SQLException e) {
			System.out.println("get dogs db access failed.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Dog> getDogsBySize(String size) {
		try(Connection conn = ConnectionUtil.getConnection()){
			boolean small = false;
			if(size == "y") {
				small = true;
			}
			ResultSet rs = null;
			String sql = "SELECT * FROM dogs WHERE size = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, small);
			rs = ps.executeQuery();
			
			return dogResultSetToDogList(rs);
			
		}catch(SQLException e) {
			System.out.println("get dogs db access failed.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addDog(Dog dog) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO dogs (name, breed, age, small, owner_fk)" +
					 "VALUES (?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dog.getName());
			ps.setString(2, dog.getBreed());
			ps.setInt(3, dog.getAge());
			ps.setBoolean(4, dog.isSmall());
			ps.setInt(5, dog.getOwner_fk());
			ps.executeUpdate();
			
			System.out.println("New dog:");
			System.out.println(dog);
			System.out.println("added to Dogss database");
			
		}catch(SQLException e) {
			System.out.println("Add dog failed. Returning to main menu.");
			e.printStackTrace();
		}
	}
	
	public List<Dog> dogResultSetToDogList(ResultSet rs){
		List<Dog> dogList = new ArrayList<>();
		try {
			while(rs.next()) {
				Dog dog = new Dog(
					rs.getInt("dog_id"),
					rs.getString("name"),
					rs.getString("breed"),
					rs.getInt("age"),
					rs.getBoolean("small"),
					rs.getInt("owner_fk"));
				dogList.add(dog);
			}
		
			return dogList;
			
		}catch(SQLException e) {
			Logger log = LogManager.getLogger(ParkDao.class);
			log.info("get owners db access failed");
			e.printStackTrace();
		}
		return null;	
	}
	
}
