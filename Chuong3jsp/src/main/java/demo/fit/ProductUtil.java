package demo.fit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProductUtil {
	private DataSource datasource;

	public ProductUtil(DataSource datasource) {

		this.datasource = datasource;

	}

	public List<Product> getProducts() throws Exception {
		List<Product> products = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
//			1. get Connection
			myConn = datasource.getConnection();
			// 2. create sql
			String sql = "Select * from Product";
			myStmt = myConn.createStatement();
			// 3. Excute
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("Id");
				String name = myRs.getString("Name");
				String desc = myRs.getString("Description");
				BigDecimal price = myRs.getBigDecimal("Price");
				Product product = new Product(id, name, desc, price);
				products.add(product);

			}

		} finally {
			close(myConn, myStmt, myRs);
		}

		return products;

	}

	private void close(Connection myCon, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myCon != null) {
				myCon.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStm = null;
		try {
			// 1. get Connection
			myConn = datasource.getConnection();
			// 2. Create Statement
			String sql = "insert into Product " + "(Name,Description, Price) " + "values (?,?,?)";
			myStm = myConn.prepareStatement(sql);
			myStm.setString(1, product.getName());
			myStm.setString(2, product.getDescription());
			myStm.setBigDecimal(3, product.getPrice());
			myStm.execute();

		} finally {
			// TODO: handle finally clause
			close(myConn, myStm, null);
		}

	}
	public  Product getProductID(String proID) throws Exception {
		Connection myCon=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		Product theproduct=null;
		int id=Integer.parseInt(proID);
		try {
			myCon=datasource.getConnection();
			String sql="select * from Product where Id=?";
			myStmt=myCon.prepareStatement(sql);
			myStmt.setInt(1, id);
			
			myRs=myStmt.executeQuery();
			if(myRs.next()){
				String name=myRs.getString("Name");
				String des=myRs.getNString("Description");
				BigDecimal price=myRs.getBigDecimal("Price");
				theproduct = new Product(name, des, price);
				
			}else {
				throw new Exception("Could not find id "+ proID);
			}
			return theproduct;			
		
		} finally {
			close(myCon, myStmt, myRs);
		} 
		
	}

}
