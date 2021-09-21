package userBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class ProductCategory {

	int pCategoryId;
	String pCategoryName;
	String pCategoryDesc;
	public int getpCategoryId() {
		return pCategoryId;
	}
	public void setpCategoryId(int pCategoryId) {
		this.pCategoryId = pCategoryId;
	}
	public String getpCategoryName() {
		return pCategoryName;
	}
	public void setpCategoryName(String pCategoryName) {
		this.pCategoryName = pCategoryName;
	}
	public String getpCategoryDesc() {
		return pCategoryDesc;
	}
	public void setpCategoryDesc(String pCategoryDesc) {
		this.pCategoryDesc = pCategoryDesc;
	}
	
	public ArrayList<ProductCategory> getAllProductCategoryId() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql = "Select *from product_category where pCategoryId=?";
		
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,pCategoryId);
		ResultSet rs = ps.executeQuery();
		ArrayList<ProductCategory> productcategory = new ArrayList<ProductCategory>();
		while(rs.next())
		{
			ProductCategory c1 = new ProductCategory();
			c1.setpCategoryId(rs.getInt("pCategoryId"));
			c1.setpCategoryName(rs.getString("pCategoryName"));
			c1.setpCategoryDesc(rs.getString("pCategoryDesc"));
			productcategory.add(c1);
		}
		return productcategory;
	}
	
	public String getNameByCategoryId() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql = "Select *from product_category where pCategoryId=?";
		
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,pCategoryId);
		ResultSet rs = ps.executeQuery();
		String name="";
		if(rs.next())
		{
			name=rs.getString("pCategoryName");
			
		}
		return name;
	}
	
	public ArrayList<String> getAllProductCategory() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql = "Select *from product_category";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> productcategory = new ArrayList<String>();
		while(rs.next())
		{
			String n = rs.getString("pCategoryName");
			productcategory.add(n);
		}
		return productcategory;
	}
	
	
	
	
	

}

