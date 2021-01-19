package dev.felleman.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.felleman.entities.User;
import dev.felleman.util.JDBCConnection;

/**
 * This Implementation Class will do the tasks of saving all data to the DB
 * 
 * @author DanielFelleman
 *
 */
public class UserDAOImpl implements UserDAO {

	// Establish a connection to database
	public static Connection conn = JDBCConnection.getConnection();
	
	
	public boolean createUser(User user) {
		
		// try catch block for any unforeseen SQL errors
		try {
			
			// Prepare sql statement
			String sql = "CALL add_user_account(?,?,?,?)";
			
			// Create a call statement
			CallableStatement cs = conn.prepareCall(sql);
			
			// Set values from object
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getPassword());
			
			// Execute and return result
			boolean success = cs.execute();
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// If something goes wrong
		return false;
		
		}
	
	public User getUserById(int userId) {
		
		try {
	
			String sql = "SELECT * FROM user_accounts WHERE USER_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			
			// Call prepared statement (and save to Result Set)
			ResultSet rs = ps.executeQuery();
			
			// if there's a value in ResultSet
			if (rs.next()) {
				
				// Create temp user object
				User u = new User();
				
				// Set values
				u.setUserId(rs.getInt("USER_ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setRegistered(rs.getInt("REGISTERED"));
				u.setLoggedIn(rs.getInt("LOGGEDIN"));
				u.setIsSuper(rs.getInt("ISSUPER"));
				
				// return user object
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// if no user found return null
		return null;
	}
	
	
	
	public List<User> getAllUsers() {
		
		// Create empty list to store results
		List<User> users = new ArrayList<User>();
		
		try {
			
			String sql = "SELECT * FROM user_accounts ORDER BY user_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			// loop through result set and add users to list
			while (rs.next()) {
				// temp user
				User u = new User();
				u.setUserId(rs.getInt("USER_ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setRegistered(rs.getInt("REGISTERED"));
				u.setLoggedIn(rs.getInt("LOGGEDIN"));
				u.setIsSuper(rs.getInt("ISSUPER"));
	
				users.add(u);
			}
			System.out.println("\t******* USERS *******");
			System.out.println("USER ID..........USER NAME..........USERNAME.....LOGGED IN");
			for (User u : users) {
				System.out.println(u.getUserId() + ".........." + u.getFirstName() + " " + u.getLastName() + ".........." +u.getUsername() + ".........." + u.getLoggedIn());
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	public User getUserByUsername(String username) {
		
		try {
			
			String sql = "SELECT * FROM user_accounts WHERE username = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("USER_ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setRegistered(rs.getInt("REGISTERED"));
				u.setLoggedIn(rs.getInt("LOGGEDIN"));
				u.setIsSuper(rs.getInt("ISSUPER"));
				
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean updateUser(User user) {
		
		try {
			
			String sql = "UPDATE user_accounts SET first_name = ?, last_name = ?, username = ?, password = ?, registered = ?, loggedin = ?, issuper = ? WHERE user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, Integer.toString(user.getRegistered()));
			ps.setString(6, Integer.toString(user.getLoggedIn()));
			ps.setString(7, Integer.toString(user.getIsSuper()));
			ps.setString(8, Integer.toString(user.getUserId()));
			
			boolean success = ps.execute();
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return false;
	}

	public boolean deleteUser(User user) {
		// make sure no one accidentally deletes the super user
		if (user.getIsSuper() == 1) {
			System.out.println("Sorry, the admin user account cannot be deleted.");
			System.out.println("Please select again from the menu");
			return false;
		}
		
		try {
			
			String sql = "DELETE user_accounts WHERE user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(user.getUserId()));
			
			boolean success = ps.execute();
			
			return success;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

}
