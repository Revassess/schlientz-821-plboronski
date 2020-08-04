package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.config.ConnectionUtil;
import com.revature.model.User;

public class UserRepository implements CrudRepository{
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Connection conn = cu.connect();
	public User save(Object u) {
		User us = (User) u;
		
		try {
			String sql = "INSERT INTO app_user VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, Integer.toString(us.getId()));
			ps.setString(2, us.getUsername());
			ps.setString(3, us.getPassword());
			ps.setString(4, us.getFirstName());
			ps.setString(5, us.getLastName());
			ps.setString(6, Integer.toString(us.getRole()));
			
			ps.executeQuery();
			return us;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Object> findAll() {
		try {
			String sql = "SELECT * FROM app_user";
			PreparedStatement ps = conn.prepareStatement(sql);
			Set<Object> users = new HashSet<>();
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getInt("ROLE_ID")));
			}
			return users;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object findById(Integer id) {
		try {
			String sql = "SELECT * FROM app_user WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getInt("ROLE_ID"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Object o) {
		try {
			String sql = "UPDATE app_user SET username = ?, password = ?, first_name = ?, last_name = ?, role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			User us = (User) o;
			ps.setString(1, us.getUsername());
			ps.setString(2, us.getPassword());
			ps.setString(3, us.getFirstName());
			ps.setString(4, us.getLastName());
			ps.setString(5, Integer.toString(us.getRole()));
			
			ps.executeQuery();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		String sql = "DELETE app_user WHERE user_id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, id.toString());
			
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
	}


}