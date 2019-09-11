package org.booksforyou.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.booksforyou.model.User;

public class UserService extends BaseService{
	
	public void registerUser(User user) {
	
		try { 
		
			String strInsert =
					 "INSERT INTO users_tbl(password,firstname,lastname,home_address,"
					 + "alternative_address,email_id,phone_num) "+ 
			 "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement= connection.prepareStatement(strInsert);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setString(4, user.getHomeAddress());
			preparedStatement.setString(5, user.getAlternativeAddress());
			preparedStatement.setString(6, user.getEmailId());
			preparedStatement.setString(7, user.getPhoneNumber());
			
			// execute insert SQL statement
			preparedStatement.executeUpdate();
			 
		} catch (SQLException e) {
		
			System.out.println("registerUser Failed! ");
			e.printStackTrace();
			return;
		
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean validateUser(String emailId,String password) {
		
			try {
				String strSelect =
						 "SELECT * FROM users_tbl\n" + 
						 "where email_id = ? and password = ? ";
				PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
				preparedStatement.setString(1, emailId);
				preparedStatement.setString(2,password);
				System.out.println(preparedStatement);
				ResultSet rsSet= preparedStatement.executeQuery();
				if(!rsSet.next()) {
					 return false;
				} else {
					 return true;
				}
				
			} catch (SQLException e) {
				System.out.println("validateUser Failed!");
				e.printStackTrace();
				return false;
			}finally{
				try {
					connection.close();
					System.out.println("Connection closed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
	}
  
	
  
	public User getUser(String emailId,String password) {
		try {
			String strSelect =
					 "SELECT * FROM users_tbl\n" + 
					 "where email_id = ? and password = ? ";
			 PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
			 preparedStatement.setString(1, emailId);
			 preparedStatement.setString(2,password);
			 
			 ResultSet rsSet= preparedStatement.executeQuery();
			 User user= new User();
			 if (rsSet.next()) {
				 user.setUserId(rsSet.getInt("user_id"));
				 user.setEmailId(rsSet.getString("email_id"));
				 user.setFirstname(rsSet.getString("firstname"));
				 user.setLastname(rsSet.getString("lastname"));
				 user.setHomeAddress(rsSet.getString("home_address"));
				 user.setAlternativeAddress(rsSet.getString("alternative_address"));
				 user.setPhoneNumber(rsSet.getString("phone_num"));
			 }
			 return user; 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
			
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
	
	public User getUser(int userId) {
		try {
			
			String strSelect= "Select * from users_tbl where user_id=?";
			PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
			 preparedStatement.setInt(1, userId);
			 
			ResultSet rsSet= preparedStatement.executeQuery();
			User user= new User();
			if (rsSet.next()) {
				 user.setUserId(rsSet.getInt("user_id"));
				 user.setEmailId(rsSet.getString("email_id"));
				 user.setFirstname(rsSet.getString("firstname"));
				 user.setLastname(rsSet.getString("lastname"));
				 user.setHomeAddress(rsSet.getString("home_address"));
				 user.setAlternativeAddress(rsSet.getString("alternative_address"));
				 user.setPhoneNumber(rsSet.getString("phone_num"));
			}
			return user;  
		} catch (SQLException e) {
			
			System.out.println("getUser Failed!");
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	 
  	}
				 