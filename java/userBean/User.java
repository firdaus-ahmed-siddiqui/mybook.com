package userBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import staffBean.Staff;
import util.DBConnection;

public class User {
	private String eid;
	private String username;
	private String contact;
	private String address;
	private String email;
	private String password;
	private int status;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public int login() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql="Select * from user where userEmail=? and userPassword=?";
		
		int result=0;
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		System.out.println(email);
		System.out.println(password);
		
		ResultSet rs= ps.executeQuery();
		
		
		User s1 = new User();
		
		if(rs.next())
		{
			s1.setUsername(rs.getString("userName"));
			s1.setEmail(rs.getString("userEmail"));
			s1.setContact(rs.getString("userContact"));
			s1.setAddress(rs.getString("userAddress"));
			s1.setPassword(rs.getString("userPassword"));
			if(rs.getInt("status")==0)
			{
				result=1;
			}
			s1.setStatus(rs.getInt("status"));
		
			
		}
		else
		{
			result=2;
		}
		
		
		return result;
	}
	public int addUser() throws SQLException
	{
		Connection conn =  DBConnection.dbconnect();
		String sql="insert into user (userName,userEmail,userContact,userAddress,userPassword,status) values(?,?,?,?,?,?)";
		java.sql.PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setString(1, username);
		ps1.setString(2, email);
		ps1.setString(3, contact);
		ps1.setString(4, address);
		ps1.setString(5, password);
		ps1.setInt(6, 0);
		int row = ps1.executeUpdate();
		conn.close();
		return row;
	}
	
	public int check_duplicate_user(String email1) throws SQLException{
		
		Connection conn = DBConnection.dbconnect();
		
		String sql ="SELECT COUNT(*) FROM user where userEmail='"+email1+"'";
		
		Statement st=conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		String Countrow = rs.getString(1);
		if(Countrow.equals("0")){
			return 0;
		}
		else {
			return 1;
		}
		
		
	}

}