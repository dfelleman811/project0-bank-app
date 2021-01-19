package dev.felleman.daos;

import java.util.List;
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
	public boolean createUser(User user);
	
	//public User setPassword(User user, String password); more of a user services anyways
	
	
	// READ
	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
	//public int getAccountById(int accountNumer);
	
	public List<User> getAllUsers();
	
	
	// UPDATE
	public boolean updateUser(User user);
	
	
	// DELETE
	public boolean deleteUser(User user);
	
	
	
	
	

}
