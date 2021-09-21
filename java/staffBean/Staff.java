package staffBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class Staff {

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
	public int addStudent() throws SQLException
	{
		Connection conn =  DBConnection.dbconnect();
		String sql="insert into staff (staffName,staffEmail,staffContact,staffAddress,staffPassword,status) values(?,?,?,?,?,?)";
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
	public int login() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql="Select * from staff where staffEmail=? and staffPassword=?";
		
		int result=0;
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		System.out.println(email);
		System.out.println(password);
		
		ResultSet rs= ps.executeQuery();
		
		
		Staff s1 = new Staff();
		
		if(rs.next())
		{
			s1.setUsername(rs.getString("staffName"));
			s1.setEmail(rs.getString("staffEmail"));
			s1.setContact(rs.getString("staffContact"));
			s1.setAddress(rs.getString("staffAddress"));
			s1.setPassword(rs.getString("staffPassword"));
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
	
}

/*package staffBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class Staff {

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
	public int addStudent() throws SQLException
	{
		Connection conn =  DBConnection.dbconnect();
		String sql="insert into staff (staffName,staffEmail,staffContact,staffAddress,staffPassword) values(?,?,?,?,?)";
		java.sql.PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setString(1, username);
		ps1.setString(2, email);
		ps1.setString(3, contact);
		ps1.setString(4, address);
		ps1.setString(5, password);
		int row = ps1.executeUpdate();
		conn.close();
		return row;
	}
	public Staff login() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql="Select * from staff where staffEmail=?, staffPassword=?";
		String sql1="Select status from staff where staffEmail=?";
		
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		java.sql.PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps.setString(1, email);
		ps.setString(2, password);
		ps1.setString(1, email);
		ResultSet rs= ps.executeQuery();
		
		ArrayList<Staff> allStuds1 = new ArrayList<Staff>();
		Staff s1 = new Staff();
		if(rs.next())
		{
			s1.setUsername(rs.getString("staffName"));
			s1.setEmail(rs.getString("staffEmail"));
			s1.setContact(rs.getString("staffContact"));
			s1.setAddress(rs.getString("staffAddress"));
			s1.setPassword(rs.getString("staffPassword"));
			s1.setStatus(rs.getInt("status"));
			allStuds1.add(s1);
			
		}
		else
		{
			s1=null;
		}
		
		
		return s1;
	}
	
}


/*package staffBean;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class Staff {

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
	public int addStudent() throws SQLException
	{
		Connection conn =  DBConnection.dbconnect();
		String sql="insert into staff (staffName,staffEmail,staffContact,staffAddress,staffPassword,status) values(?,?,?,?,?,?)";
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
	public Staff login() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql="Select * from staff where staffEmail=?, staffPassword=?";
		String sql1="Select status from staff where staffEmail=?";
		
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		java.sql.PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps.setString(1, email);
		ps.setString(2, password);
		ps1.setString(1, email);
		ResultSet rs= ps.executeQuery();
		
		ArrayList<Staff> allStuds1 = new ArrayList<Staff>();
		Staff s1 = new Staff();
		if(rs.next())
		{
			s1.setUsername(rs.getString("staffName"));
			s1.setEmail(rs.getString("staffEmail"));
			s1.setContact(rs.getString("staffContact"));
			s1.setAddress(rs.getString("staffAddress"));
			s1.setPassword(rs.getString("staffPassword"));
			s1.setStatus(rs.getInt("status"));
			allStuds1.add(s1);
			
		}
		else
		{
			s1=null;
		}
		
		
		return s1;
	}
	
}*/
