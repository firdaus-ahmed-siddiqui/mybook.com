package userBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBConnection;

public class Cart {

	int pId;
	String pName;
	String pAuthor;
	String pDescription;
	String pSeller;
	int pPrice;
	int pDiscount;
	String pImage;
	int pCategoryId;
	int pqty;
	String userEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpAuthor() {
		return pAuthor;
	}
	public void setpAuthor(String pAuthor) {
		this.pAuthor = pAuthor;
	}
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	public String getpSeller() {
		return pSeller;
	}
	public void setpSeller(String pSeller) {
		this.pSeller = pSeller;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpDiscount() {
		return pDiscount;
	}
	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public int getpCategoryId() {
		return pCategoryId;
	}
	public void setpCategoryId(int pCategoryId) {
		this.pCategoryId = pCategoryId;
	}
	
	
	
	public Cart() {
		super();
	}
	
	
	public Cart(int pId, String pName, String pAuthor, String pDescription, String pSeller, int pPrice, int pDiscount,
			String pImage, int pCategoryId, int pqty, String userEmail) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pAuthor = pAuthor;
		this.pDescription = pDescription;
		this.pSeller = pSeller;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pImage = pImage;
		this.pCategoryId = pCategoryId;
		this.pqty = pqty;
		this.userEmail = userEmail;
	}
	
	
	@Override
	public String toString() {
		return "Cart [pId=" + pId + ", pName=" + pName + ", pAuthor=" + pAuthor + ", pDescription=" + pDescription
				+ ", pSeller=" + pSeller + ", pPrice=" + pPrice + ", pDiscount=" + pDiscount + ", pImage=" + pImage
				+ ", pCategoryId=" + pCategoryId + ", pqty=" + pqty + ", userEmail=" + userEmail + "]";
	}
	public int AddToCartProducts() throws SQLException {
		
		Connection conn = DBConnection.dbconnect();
		String sql = "insert into cart (pName,pQty,pAuthor,pDescription,pSeller,pPrice,pDiscount,pImage,userEmail,pCategoryId)  values(?,?,?,?,?,?,?,?,?,?)";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pName);
		ps.setString(2, "1");
		ps.setString(3, pAuthor);
		ps.setString(4, pDescription);
		ps.setString(5, pSeller);
		ps.setInt(6, pPrice);
		ps.setInt(7, pDiscount);
		ps.setString(8, pImage);
		ps.setString(9, userEmail);
		ps.setInt(10, pCategoryId);
		int row = ps.executeUpdate();
		conn.close();
		return row;
	}
	
	public ArrayList<Cart> getAllProductsFromCartByEmail() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql = "select *from cart where userEmail=?";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userEmail);
		ResultSet rs = ps.executeQuery();
		ArrayList<Cart> allcarts = new ArrayList<Cart>();
		
		while(rs.next())
		{
			int id=rs.getInt("pId");
			String name=rs.getString("pName");
			String author=rs.getString("pAuthor");
			String description=rs.getString("pDescription");
			String seller=rs.getString("pSeller");
			int price=rs.getInt("pPrice");
			int discount=rs.getInt("pDiscount");
			String image=rs.getString("pImage");
			int category=rs.getInt("pCategoryId");
			int pqty= rs.getInt("pqty");
			String email=rs.getString("userEmail");
			Cart p5=new Cart(id,name,author,description,seller,price,discount,image,category,pqty,email);
			allcarts.add(p5);
		}
		
		
		return allcarts;
	}
	
	public ArrayList<Cart> getAllProductsFromCart() throws SQLException
	{
		Connection conn = DBConnection.dbconnect();
		String sql = "select *from cart";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Cart> cart = new ArrayList<Cart>();
		while(rs.next())
		{
			Cart c1 = new Cart();
			c1.setpId(rs.getInt("pId"));
			c1.setpName(rs.getString("pName"));
			c1.setpAuthor(rs.getString("pAuthor"));
			c1.setpDescription(rs.getString("pDescription"));
			c1.setpSeller(rs.getString("pSeller"));
			c1.setpPrice(rs.getInt("pPrice"));
			c1.setpDiscount(rs.getInt("pDiscount"));
			c1.setpImage(rs.getString("pImage"));
			c1.setpCategoryId(rs.getInt("pCategoryId"));
			c1.setPqty(rs.getInt("pqty"));
			cart.add(c1);

			
		}
		
		
		return cart;
	}
	
	public int DeleteProductFromCart() throws SQLException {
		
		Connection conn = DBConnection.dbconnect();
		String sql = "delete from cart where pId=?";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, pId);
		
		int row = ps.executeUpdate();
		conn.close();
		return row;
	}
	
	public int checkCart() throws SQLException {
		
		Connection conn = DBConnection.dbconnect();
		String sql = "select count(*) as cartlen from cart where userEmail=?";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userEmail);
		ResultSet rs = ps.executeQuery();
		int count=0;
		if(rs.next()) {
			count=rs.getInt("cartlen");
		}
		conn.close();
		return count;
	}
	
	
}
