package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import javax.annotation.Resource;
import javax.sql.DataSource;

import entity.Product;

public class ProductDAO {
	private DataSource dataSource;
	
	
	
	public ProductDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
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


	public List<Product> getAll() {
		List<Product> list = new ArrayList<Product>();
		String sql = "SELECT * FROM Product;";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getLong("Id"));
				product.setName(resultSet.getString("Name"));
				product.setDescription(resultSet.getString("Description"));
				product.setPrice(resultSet.getDouble("Price"));
				
				list.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return list;
	}
	
	public Product getDetail(Long id) {		
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet reSet = null;
		Product product = null;
		
		try {
			String sql = "select * from Product where Id=?";
			
			connection = dataSource.getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, id);
			
			reSet = pStatement.executeQuery();
			
			if(reSet.next()) {
				String name = reSet.getString("Name");
				String description = reSet.getNString("Description");
				Double price = reSet.getDouble("Price");
				
				product = new Product(id, name, description, price);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, pStatement, reSet);
		}
		
		return product;
	}
	
	public void addProduct(Product product) {
		Connection connection = null;
		PreparedStatement preStatement = null;
		try {
			// 1. get Connection
			connection = dataSource.getConnection();
			// 2. Create Statement
			String sql = "insert into Product " + "(Name,Description, Price) " + "values (?,?,?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, product.getName());
			preStatement.setString(2, product.getDescription());
			preStatement.setDouble(3, product.getPrice());
			preStatement.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		finally {
			// TODO: handle finally clause
			close(connection, preStatement, null);
		}

	}

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preStatement = null;
		try {
			// 1. get Connection
			connection = dataSource.getConnection();
			// 2. Create Statement
			String sql = "UPDATE Product "
					+ "SET Name = ?, Description=?, Price = ? "
					+ "WHERE Id=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, product.getName());
			preStatement.setString(2, product.getDescription());
			preStatement.setDouble(3, product.getPrice());
			preStatement.setLong(4, product.getId());
			preStatement.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		finally {
			// TODO: handle finally clause
			close(connection, preStatement, null);
		}
	}

	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preStatement = null;
		try {
			// 1. get Connection
			connection = dataSource.getConnection();
			// 2. Create Statement
			String sql = "delete from Product where Id=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setLong(1, id);
			preStatement.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		finally {
			// TODO: handle finally clause
			close(connection, preStatement, null);
		}
	}
	
}
