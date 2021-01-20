package dev.felleman.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.Transaction;
import dev.felleman.entities.User;
import dev.felleman.util.JDBCConnection;

public class TransactionDAOImpl implements TransactionDAO {
	
	public static Connection conn = JDBCConnection.getConnection();
	
	

	@Override
	public boolean createTransaction(Transaction t, BankAccount account) {
		
		try {
			
			String sql = "CALL add_transaction(?,?,?,?,?) ";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(account.getAccountNumber()));
			cs.setString(2, t.getTransactionType());
			cs.setString(3, Double.toString(t.getTransactionAmount()));
			cs.setString(4, Double.toString(account.getAccountBalance()));
			cs.setString(5, Integer.toString(account.getUserId()));
			
			boolean success = cs.execute();
			
			return success;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Transaction getTransaction(int transactionId) {
		try {
			
			String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(transactionId));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Transaction t = new Transaction();
				
				t.setTransactionId(rs.getInt("TRANSACTION_ID"));
				t.setDatetime(rs.getDate("TRANSACTION_DATE"));
				t.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				t.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				t.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				t.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				t.setUserId(rs.getInt("USER_ID"));
				
				System.out.println(t);
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			
			String sql = "SELECT * FROM transactions ORDER BY transaction_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				
				t.setTransactionId(rs.getInt("TRANSACTION_ID"));
				t.setDatetime(rs.getDate("TRANSACTION_DATE"));
				t.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				t.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				t.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				t.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				t.setUserId(rs.getInt("USER_ID"));
				
				transactions.add(t);
			}
			
			for (Transaction t : transactions) {
				System.out.println(t);
			}
			return transactions;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> getAllUserTransactions(User user) {
		
		List<Transaction> allUserTransactions = new ArrayList<Transaction>();
		
		try {
			
			String sql = "SELECT * FROM transactions WHERE user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(user.getUserId()));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				
				t.setTransactionId(rs.getInt("TRANSACTION_ID"));
				t.setDatetime(rs.getDate("TRANSACTION_DATE"));
				t.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				t.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				t.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				t.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				t.setUserId(rs.getInt("USER_ID"));
				
				allUserTransactions.add(t);
			}
			
			for (Transaction t : allUserTransactions) {
				System.out.println(t);
			}
			return allUserTransactions;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Transaction> getAllBankAccountTransactions(BankAccount bankAccount) {
		
		List<Transaction> allBankAccountTransactions = new ArrayList<Transaction>();
		
		try {
			
			String sql = "SELECT * FROM transactions WHERE account_number = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(bankAccount.getAccountNumber()));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Transaction t = new Transaction();
				
				t.setTransactionId(rs.getInt("TRANSACTION_ID"));
				t.setDatetime(rs.getDate("TRANSACTION_DATE"));
				t.setTransactionType(rs.getString("TRANSACTION_TYPE"));
				t.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				t.setAccountNumber(rs.getInt("ACCOUNT_NUMBER"));
				t.setAccountBalance(rs.getDouble("ACCOUNT_BALANCE"));
				t.setUserId(rs.getInt("USER_ID"));
				
				allBankAccountTransactions.add(t);
			}
			for (Transaction t : allBankAccountTransactions) {
				System.out.println(t);
			}
			return allBankAccountTransactions;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//update
	
	@Override
	public boolean updateTransaction(Transaction transaction) {
		return false;
	}
	
	//delete

}
