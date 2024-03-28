package com.bank.bankdao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.bankdto.BankDTO;
import com.bank.tansactiondto.TransactionDTO;
import com.bank.userdto.UserDTO;

import db.DatabaseConnection;



public class BankDAO
{
	Connection con=null;
	public boolean setUserDetails(UserDTO userdto) 
	
	{
		String query="INSERT INTO user_info(user_name,user_password,user_full_name,user_phno,user_email,user_address)"+ "VALUES('"+userdto.getUserName()+"','"+userdto.getPassword()+"','"+userdto.getFullName()+"','"+userdto.getPhno()+"','"+userdto.getEmail()+"','"+userdto.getAddress()+"')";
		try {
		con =DatabaseConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		int rowSAffected = pst.executeUpdate();
		return rowSAffected>0;
	}
	catch(Exception ie) {
		ie.printStackTrace();
	}
	finally {
		DatabaseConnection.closeConnection(con);
	}
	return false;	
}
	public UserDTO getUserDetails(String userName,String password)  {
		
		UserDTO dto = new UserDTO();
		try {	
		String query="SELECT user_id,user_name,user_password,user_full_name,user_phno,user_email,user_address FROM user_info WHERE user_name='"+userName+"' and user_password='"+password+"'";
	    con = DatabaseConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery(query);
		while(rst.next()) {
			dto.setUserId(rst.getInt("user_id"));
			dto.setUserName(rst.getString("user_name"));
			dto.setPassword(rst.getString("user_password"));
			dto.setFullName(rst.getString("user_full_name"));
			dto.setPhno(rst.getString("user_phno"));
			dto.setEmail(rst.getString("user_email"));
			dto.setAddress(rst.getString("user_address"));
		}
		if(dto.getUserId()==0) {
			return null;
		}	
		}
		catch(Exception ie) {
			ie.printStackTrace();
		}
		return dto;
	}
	public Boolean setAccountDetails(BankDTO dto) {
		
		try {
		con = DatabaseConnection.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO bank_info(user_name,account_no,bank_name,IFSC_code,account_type,current_balance)"+ "VALUES('"+dto.getUserName()+"','"+dto.getAccountNo()+"','"+dto.getBankName()+"','"+dto.getIfscCode()+"','"+dto.getAccountType()+"','"+dto.getCurrentBalance()+"')");
	    int rowSAffected=pst.executeUpdate();
	    return rowSAffected>0;
		}
		catch(Exception ie) {
			ie.printStackTrace();
		}
		finally {
			DatabaseConnection.closeConnection(con);
		}
		return false;
	}
	public List<BankDTO> getAccountDetails(String userName) {
		

		List<BankDTO> acclist = new ArrayList<BankDTO>();
		try {
			con = DatabaseConnection.getConnection();
			Statement smt = con.createStatement();
			ResultSet rst = smt.executeQuery("SELECT account_id,account_no,bank_name,ifsc_code,account_type,current_balance FROM bank_info WHERE user_name='"+userName+"'");
			while(rst.next()) {
				BankDTO  dto = new BankDTO();
				dto.setAccountId(rst.getInt("account_id"));
				dto.setAccountNo(rst.getString("account_no"));
				dto.setBankName(rst.getString("bank_name"));
				dto.setIfscCode(rst.getString("ifsc_code"));
				dto.setAccountType(rst.getString("account_type"));
				dto.setCurrentBalance(rst.getDouble("current_balance"));
				acclist.add(dto);
			}
			}
		    catch(Exception ie) {
		    	ie.printStackTrace();
		}
		finally {
			DatabaseConnection.closeConnection(con);
		}
		return acclist;
	}
	public BankDTO getBalance(int accountId)
	{
	   try
	   {
		con = DatabaseConnection.getConnection();
		Statement smt = con.createStatement();
		ResultSet rst = smt.executeQuery("SELECT account_no,current_balance FROM bank_info WHERE account_id ='"+accountId+"'");
		BankDTO dto = new BankDTO();
		while(rst.next())
		{
			dto.setCurrentBalance(rst.getDouble("current_balance"));
			dto.setAccountNo(rst.getString("account_no"));
			return dto;
		}	
	  }
	  catch(Exception ie) {
		  ie.printStackTrace();
	  }
	   return null;
	}
	public Boolean updateBalance(BankDTO dto, int accountId)
	{
		int rst=-1;
		try {
		con = DatabaseConnection.getConnection();
		PreparedStatement pst = con.prepareStatement("UPDATE bank_info SET current_balance='"+dto.getCurrentBalance()+"' WHERE account_id = '"+accountId+"'");
		rst=pst.executeUpdate();
		if(rst==1) {
			return true;
		}
		}
		catch(Exception ie) {
			ie.printStackTrace();
			
		}
		return false;
	}
	public Boolean setTransactionDetails(TransactionDTO dto) {
		  int rst=-1;
		try {
			con=DatabaseConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("INSERT INTO Transaction_info(transaction_id,target_accountno,amount)" +" VALUES('"+dto.getTransactionId()+"','"+dto.getTargetAccountNo()+"','"+dto.getAmount()+"')");
			rst=pst.executeUpdate();
			if(rst==1)
			{
				return true;
			}
		}
		catch(Exception ie)
		{
			ie.printStackTrace();
		}
		return false;
	}
	
}


