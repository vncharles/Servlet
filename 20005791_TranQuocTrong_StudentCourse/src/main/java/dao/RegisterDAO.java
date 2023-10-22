package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class RegisterDAO {
	private DataSource dataSource;
	
	public RegisterDAO(DataSource dataSource) {
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
	
	public void register(int courseId, int studentId) {
		Connection connection = null;
		PreparedStatement preStatement = null;
		try {
			// 1. get Connection
			connection = dataSource.getConnection();
			// 2. Create Statement
			String sql = "insert into Register " + "(ID_Course, ID_Student) " + "values (?,?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, courseId);
			preStatement.setInt(2, studentId);
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
	
	public void unregister(int courseId, int studentId) {
		Connection connection = null;
		PreparedStatement preStatement = null;
		try {
			// 1. get Connection
			connection = dataSource.getConnection();
			// 2. Create Statement
			String sql = "delete from Register where ID_Course=? and ID_Student=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, courseId);
			preStatement.setInt(2, studentId);
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
