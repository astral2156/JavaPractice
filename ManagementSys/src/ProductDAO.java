import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.activation.DataSource;

public class ProductDAO {
	/*
	 * db connect get all close getproduct new product updateproduct
	 */

	String jdbcDriver = "";
	String jdbcUrl = "";
	Connection conn=null;
	
	PreparedStatement pstmt;
	ResultSet rs;
	java.sql.Statement stmt;
	
	String url = "jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC";
	String id = "root";
	String pw = "kim27903";
	
	int prcode;
	String prname;
	int price;
	String manufacture;
	Product p;
	
	Vector<String> items = null;
	String sql;

	//AppMain appMain = new AppMain();

	public ArrayList<Product> getAll() {// void 수정1
		connectDB(); // connectDB , productDao 메소드 사용
		sql = "select * from product";
		

		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Product> datas = new ArrayList<Product>();
		

		items = new Vector<String>();
		items.add("전체");
	
		try {
			while (rs.next()) {
				
				
				Product p = new Product();
				
				p.setPrcode(rs.getInt(1));
				p.setPrname(rs.getString(2));
				p.setPrice(rs.getInt(3));
				p.setManufacture(rs.getString(4));
				datas.add(p);
				items.add(String.valueOf(rs.getInt("prcode")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//getProduct();
		return datas;
	}// get all

	private void ProductDAO() {

		try {// 컨넥션
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("connceted!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void connectDB() {
		try {
			ProductDAO();
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Product getProduct(int prcode) { // java에서 가져오는 부분
	System.out.println("in getProduct");
		
		sql = "select * from product where prcode = ?";
		Product p = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prcode);// prcode error 수정2
			rs = pstmt.executeQuery();
			rs.next();
			p = new Product();
			p.setPrcode(rs.getInt("prcode"));
			p.setPrname(rs.getString("prname"));
			p.setPrice(rs.getInt("price"));
			p.setManufacture(rs.getString("manufacture"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;

	}

	public boolean delProduct(int prcode) {
		sql ="delete from product where prcode = ?";
		
		try { 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prcode);
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProduct(Product product) {
		sql = "update product set prname =?, price =?, manufacture=? where prcode =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(4, p.getPrcode());
			pstmt.setString(1, p.getPrname());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getManufacture());
			
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("eror");
			return false;
		}
	}

	public boolean newProduct(Product product) {
		sql ="insert into product values(?, ?, ?, ?)";
		Product p = null;
		try { 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 200);
			pstmt.setString(2, "test");
			pstmt.setInt(3, 1111);
			pstmt.setString(4, "test");
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Vector<String> getItems() {

		return items;
	}
}
