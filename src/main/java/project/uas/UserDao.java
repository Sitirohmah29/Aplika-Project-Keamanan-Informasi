package project.uas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

public class UserDao {
	
	private String queryInsert = "INSERT INTO users (username, name, password) VALUES (?, ?, ?)";
	private String querySelectAll = "SELECT * FROM users";
	private String queryUpdate = "UPDATE users SET username = ?, name = ?, password = ? WHERE id = ?";
	private String queryRemoveById = "DELETE FROM users WHERE id = ?";
	private String queryFindById = "SELECT * FROM users WHERE id = ?";
	
	public void saved(Users user) throws Exception{
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(queryInsert);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();
		c.close();
	}
	
	public List<Users> findAll() throws Exception{
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(querySelectAll);
		ResultSet rs = ps.executeQuery();
		
		List<Users> users = new ArrayList<>();
		while (rs.next()) {
			Users user = new Users();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		c.close();
		return users;
	}
	
	public void update(Users user) throws Exception{
		Connection c =  new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(queryUpdate);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();
		c.close();
	}
	
	public Users findById(int id) throws Exception{
		Connection c = new ConnectionDB().connect();
		PreparedStatement ps = c.prepareStatement(queryFindById);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Users user = new Users();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			c.close();
			return user;
		}
		c.close();
		return null;
	}

}
