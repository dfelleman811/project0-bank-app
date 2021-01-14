package dev.felleman.daotests;

import java.util.HashSet;
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
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "password");
		System.out.println(user);
		
		User user2 = userDAO.createUser("Greg", "Jordan", "gigi", "good");
		System.out.println(user2);
	}

	@Test 
	void getUserByIdTest() {
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "jolly");
		
		
		User user2 = userDAO.createUser("Johnny", "Bravo", "hairgel", "rocket");

		User u = userDAO.getUserById(1);
		
		User u2 = userDAO.getUserById(2);
		
		Assertions.assertEquals(u, user);
		
		Assertions.assertEquals(u2,  user2);
		
		
	}
	
	@Test
	void getUserByUsernameTest() {
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "jolly");
		
		User u = userDAO.getUserByUsername(user.getUsername());
		
		Assertions.assertEquals(user.getUsername(), u.getUsername());
	}
	
	@Test
	void updateUserTest() {
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "password");

		User updatedUser = userDAO.updateUser(user);
		
		// is this redundant?
	
	}
	
//	@Test
//	void getAccountByIdTest() {
//		User user = new User("Dan", "Felleman", "dfelleman");
//		userDAO.createUser(user);
//		
//		user.setAccountNumber(1234567890);
//		
//		userDAO.updateUser(user);
//		System.out.println(user);
//		
//		int accountNumber = userDAO.getAccountById(1);
//		
//		System.out.println(accountNumber);
//		
//	}
	
	
 	@Test
	void getAllUsersTest() {
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "password");
		
		User user2 = userDAO.createUser("Johnny", "Bravo", "hairgel", "mohawk");
		
		
		User user3 = userDAO.createUser("Greg", "Bacon", "sizzle", "canadianbacon");
		
		
		User user4 = userDAO.createUser("Timmy", "Big", "tiny", "willow");
		
		Set<User> getUserSetTest = new HashSet<User>();
		
		getUserSetTest.add(user);
		getUserSetTest.add(user2);
		getUserSetTest.add(user3);
		getUserSetTest.add(user4);
		
		Assertions.assertEquals(getUserSetTest, userDAO.getAllUsers());
		
	}
 	
// 	@Test
// 	void getAccountBalanceTest() {
// 		User user = new User("Peter", "Pettigrew", "ratlover");
// 		userDAO.createUser(user);
// 		
// 		user.setAccountBalance(500.00);
// 		
// 		userDAO.updateUser(user);
// 		
// 		System.out.println(user);
// 	}
	
	@Test
	void setPasswordTest() {
		User user = userDAO.createUser("Joni", "Mitchell", "caseofyou", "parkinglot");
		
		user.setPassword("password");
		userDAO.updateUser(user);
		
		Assertions.assertEquals("password", user.getPassword());
	}
	
	
 	@Test
	void deleteUserTest() {
		User user = userDAO.createUser("Dan", "Felleman", "dfelleman", "password");
		
		User user2 = userDAO.createUser("Johnny", "Bravo", "hairgel", "hello");
		
		User user3 = userDAO.createUser("Greg", "Bacon", "sizzle", "veganbacon");
		
		User user4 = userDAO.createUser("Timmy", "Big", "tiny", "fairyqueen");
		
		userDAO.deleteUser(user2); // need to refactor so that only super user can do this
		
		// to make sure that the count still works
		User user5 = userDAO.createUser("Harry", "Potter", "theChosenOne", "alohomora");
		
		Set<User> getUserSetTest = new HashSet<User>();
		
		getUserSetTest.add(user);
		getUserSetTest.add(user3);
		getUserSetTest.add(user4);
		getUserSetTest.add(user5);
		
		Assertions.assertEquals(userDAO.getAllUsers(), getUserSetTest);
	}
 	
 	
 }
