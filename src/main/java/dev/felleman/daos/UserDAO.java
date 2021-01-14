package dev.felleman.daos;

import java.util.Set;

import dev.felleman.entities.User;

/**
 * 
 * This is where we have our CRUD functionalities
 * 
 * @author DanielFelleman
 *
 */

public interface UserDAO {
	
	// CREATE
	public User createUser(String firstName, String lastName, String username, String password);
	
	public User setPassword(User user, String password);
	
	
	// READ
	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	//public int getAccountById(int accountNumer);
	
	public Set<User> getAllUsers();
	
	
	
	
	// UPDATE
	public User updateUser(User user);
	
	
	// DELETE
	public String deleteUser(User user);
	
	
	
	
	

}
