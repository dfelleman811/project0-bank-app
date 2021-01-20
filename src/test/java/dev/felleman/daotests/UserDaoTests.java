package dev.felleman.daotests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.User;

class UserDaoTests {
	
	private static UserDAO userDAO = new UserDAOImpl();

	@Test 
	void createUserTest() {
		User user = new User("Minerva", "McGonagall", "tabbygirl99", "youthree");
		user.setUserId(8); // testing purposes only
		user.setRegistered(1);
		userDAO.createUser(user);
		System.out.println(user);
		
		User user2 = new User("James", "Potter", "prongs", "deaddead");
		user2.setUserId(9); // testing purposes only
		user2.setRegistered(1);
		userDAO.createUser(user2);
		System.out.println(user2);
		
		Assertions.assertEquals(user, userDAO.getUserById(8));
		Assertions.assertEquals(user2, userDAO.getUserById(9));
		
	}

	@Test 
	void getUserByIdTest() {
		User user = new User("Dan", "Felleman", "dfelleman", "password");
		user.setUserId(1);
		user.setRegistered(1);
		User u = userDAO.getUserById(1);
		
		System.out.println(user);
		System.out.println(u);
		
		Assertions.assertEquals(user, u);
				
	}
	
	@Test
	void getUserByUsernameTest() {
		User user = new User("Dan", "Felleman", "dfelleman", "password");
		user.setUserId(1);
		user.setRegistered(1);
		User u = userDAO.getUserByUsername("dfelleman");
		
		Assertions.assertEquals(user, u);
		
	}
	
	@Test
	void updateUserTest() {
		User user = userDAO.getUserById(1);
		
		// eventually I'll test Services but for now I'm doing manually
		user.setPassword("newerPassword");
		userDAO.updateUser(user);
		
		
		Assertions.assertEquals("newerPassword", user.getPassword());

	
	}
	
	
	
 	@Test
	void getAllUsersTest() {
		User user = userDAO.getUserById(1);
		
		User user2 = userDAO.getUserById(2);
		
		
		User user3 = userDAO.getUserById(3);
		
		
		User user4 = userDAO.getUserById(4);
		
		List<User> getUserListTest = new ArrayList<User>();
		
		getUserListTest.add(user);
		getUserListTest.add(user2);
		getUserListTest.add(user3);
		getUserListTest.add(user4);
		
		Assertions.assertEquals(getUserListTest, userDAO.getAllUsers());
		
	}

	
	
 	@Test
	void deleteUserTest() {
 		User user = userDAO.getUserById(1);
		User user3 = userDAO.getUserById(3);
		User user4 = userDAO.getUserById(4);
		User user5 = userDAO.getUserById(5);
		User user6 = userDAO.getUserById(6);
		User user7 = userDAO.getUserById(7);
		User user8 = userDAO.getUserById(8);
		User user9 = userDAO.getUserById(9);
		
		userDAO.deleteUser(user3); // need to refactor so that only super user can do this
		
		// to make sure that the count still works
		User user10 = new User("Hermione", "Granger", "TinaTimeTurner", "leviOsa");
		user10.setUserId(10);
		user10.setRegistered(1);
		userDAO.createUser(user10);
		
		List<User> getUserListTest = new ArrayList<User>();
		
		getUserListTest.add(user);
		getUserListTest.add(user4);
		getUserListTest.add(user5);
		getUserListTest.add(user6);
		getUserListTest.add(user7);
		getUserListTest.add(user8);
		getUserListTest.add(user9);
		getUserListTest.add(user10);
		
		Assertions.assertEquals(getUserListTest, userDAO.getAllUsers());
	}
 	
 	
 }
