package dev.felleman.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.felleman.entities.User;

/**
 * This Implementation Class will do the tasks of saving all data
 * 
 * @author DanielFelleman
 *
 */
public class UserDAOImpl implements UserDAO {
	
	private static Map<Integer, User> user_table = new HashMap<Integer, User>();
	
	private static int idCount = 0;
	
	private static UserDAO userDAO = new UserDAOImpl();
	

	public User createUser(String firstName, String lastName, String username, String password) {
		
		// Create user
		User user = new User(firstName, lastName, username);
		
		// Set password
		user.setPassword(password);
		
		// Generate ID
		user.setUserId(++idCount);
		
		// Make registered = True
		user.setRegistered(true);
		
		// Add user to table
		user_table.put(idCount, user);
		
		return user;
		
	}
	
	public User setPassword(User user, String password) {
		user.setPassword(password);
		return user;
	}

	public User getUserById(int userId) {
		User user = user_table.get(userId);
		return user;
	}
	
	public User getUserByUsername(String username) {
		Set<User> userSet = userDAO.getAllUsers();
		
		for (User u : userSet) {
			if (u.getUsername().equals(username)) {
				return u;
			}
			else {
				System.out.println("Sorry, that username does not exist.");
				return null;
			}
		}
		
		return null;
	}

//	public int getAccountById(int userId) { // I don't know that I really need this
//		User user = user_table.get(userId);
//		
//		int accountNumber = user.getAccountNumber();
//		
//		return accountNumber;
//	}

	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<User>(user_table.values());
		return users;
	}

	
	public User updateUser(User user) {
		// Basically 'saving' into the table
		user_table.put(user.getUserId(), user);
		return user;
	}

	public String deleteUser(User user) {
//		if (user.getIsSuper()) {
			// Remove User
			user_table.remove(user.getUserId());
			
			return "User" + user.getUsername() + "was deleted";
//		}
//		else {
//			System.out.println("You do not have permission to create an account.");
//			return null;
//		}
		
	}

}
