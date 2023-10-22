package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.DanhMuc;

public class DanhMucDAO {
	private DataSource dataSource;

	public DanhMucDAO(DataSource dataSource) {
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
	
	public List<DanhMuc> getAll() {
		List<DanhMuc> list = new ArrayList<>();
		String sql = "select * from DANHMUC";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDM(resultSet.getInt(1));
				danhMuc.setTenDM(resultSet.getNString(2));
				danhMuc.setNguoiQL(resultSet.getNString(3));
				danhMuc.setGhiChu(resultSet.getNString(4));
				
				list.add(danhMuc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return list;
	}
	
	public DanhMuc getDetail(int maDM) {
		String sql = "select * from DANHMUC where MADM="+maDM;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		DanhMuc danhMuc = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				danhMuc = new DanhMuc();
				danhMuc.setMaDM(resultSet.getInt(1));
				danhMuc.setTenDM(resultSet.getNString(2));
				danhMuc.setNguoiQL(resultSet.getNString(2));
				danhMuc.setGhiChu(resultSet.getNString(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return danhMuc;
	}
	
}
