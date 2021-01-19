package dev.felleman.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;
import dev.felleman.util.JDBCConnection;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	// Establish a connection to database
	public static Connection conn = JDBCConnection.getConnection();

	public static UserDAO userDAO = new UserDAOImpl();
	

	@Override
	public boolean openAccount(User user) {
		
		try {
			
			String sql = "CALL add_bank_account(?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(user.getUserId()));
			
			boolean success = cs.execute();
			
			return success;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public BankAccount getBalance(BankAccount account) { // TODO do the same output for deposits and withdrawals
		
		try {
			
			String sql = "SELECT * FROM bank_accounts WHERE account_number = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(account.getAccountNumber()));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BankAccount acct = new BankAccount();
				acct.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				acct.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				acct.setUserId(rs.getInt("USER_ID"));
				
				return acct;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	@Override
	public BankAccount getBankAccountByAccountNumber(int accountNumber) {
		
		try {
			
			String sql = "SELECT * FROM bank_accounts WHERE account_number = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(accountNumber));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BankAccount ba = new BankAccount();
				ba.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				ba.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				ba.setUserId(rs.getInt("USER_ID"));
				
				return ba;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public BankAccount getBankAccountById(int userId) {
		
		try {
			
			String sql = "SELECT * FROM bank_accounts WHERE user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				BankAccount acct = new BankAccount();
				acct.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				acct.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				acct.setUserId(rs.getInt("USER_ID"));
				
				return acct;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<BankAccount> getAllUserBankAccountsById(int userId) {
		
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		try {
		
			String sql = "SELECT * FROM bank_accounts WHERE user_id = ? ORDER BY account_number";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BankAccount acct = new BankAccount();
				acct.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				acct.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				acct.setUserId(rs.getInt("USER_ID"));
				
				bankAccounts.add(acct);
				
			}

			return bankAccounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	@Override
	public List<BankAccount> getAllBankAccounts() {
		
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		try {
			
			String sql = "SELECT * FROM bank_accounts ORDER BY account_number";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BankAccount acct = new BankAccount();
				acct.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				acct.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				acct.setUserId(rs.getInt("USER_ID"));
				
				bankAccounts.add(acct);
			}
			
			System.out.println("\t******* ALL BANK ACCOUNTS *******");
			System.out.println("ACCOUNT NUMBER ..........NAME ON ACCOUNT....................... BALANCE");
			for (BankAccount acct : bankAccounts) {
				System.out.println(acct.getAccountNumber() + ".........." + userDAO.getUserById(acct.getUserId()).getLastName() + "......................................" + acct.getAccountBalance());
				
			}
			return bankAccounts;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

//	@Override // move to Account Services
//	public BankAccount withdraw(BankAccount account, double amount) {
//		// warn if user is going to overdraw
//		if (amount > account.getAccountBalance()) {
//			System.out.println("WARNING: If you withdraw " + amount 
//					           + ", your account will be overdrawn.");
//			System.out.println("Do you wish to continue? Y/N");
//		}
//		account.setAccountBalance(account.getAccountBalance() - amount);
//		return account;
//	}
//
//	@Override
//	public BankAccount deposit(BankAccount account, double amount) {
//		account.setAccountBalance(account.getAccountBalance() + amount);
//		
//		return account;
//	}
	
	@Override
	public boolean updateAccount(BankAccount account) {
		
		try {
			
			String sql = "UPDATE bank_accounts SET account_balance = ? WHERE account_number = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(account.getAccountBalance()));
			ps.setString(2, Integer.toString(account.getAccountNumber()));
			
			boolean success = ps.execute();
			
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean closeAccount(BankAccount account) {
		
		try {
			
			String sql = "DELETE FROM bank_accounts WHERE account_number = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(account.getAccountNumber()));
			
			boolean success = ps.execute();
			
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	
}
