package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.DanhMuc;
import entity.TinTuc;

public class TinTucDAO {
	private DataSource dataSource;
	private DanhMucDAO danhMucDAO;

	public TinTucDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.danhMucDAO = new DanhMucDAO(dataSource);
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

	public void deleteTinTuc(int id) {
		String sql = "delete from Tintuc where matt="+id;
		Connection connection = null;
		Statement statement = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.executeQuery(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public void updateTinTuc(TinTuc tinTuc) {
		System.out.println(tinTuc);
		String sql = "UPDATE TINTUC set TIEUDE = ?, NOIDUNGTT=?, LIENKET=? where MATT=?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setNString(1, tinTuc.getTieuDe());
			statement.setNString(2, tinTuc.getNoiDung());
			statement.setNString(3, tinTuc.getLienKet());
			statement.setInt(4, tinTuc.getMaTT());

			resultSet = statement.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(connection, statement, resultSet);
		}
	}

	public TinTuc getTinTucById(int id) {

		String sql = "select * from TINTUC where MATT=" + id;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				TinTuc tinTuc = new TinTuc();
				tinTuc.setMaTT(resultSet.getInt(1));
				tinTuc.setTieuDe(resultSet.getNString(2));
				tinTuc.setNoiDung(resultSet.getNString(3));
				tinTuc.setLienKet(resultSet.getString(4));

				DanhMuc danhMuc = danhMucDAO.getDetail(resultSet.getInt(5));
				tinTuc.setDanhMuc(danhMuc);
				return tinTuc;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public List<TinTuc> getAllByDanhMuc(int maDM) {
		List<TinTuc> list = new ArrayList<>();
		String sql = "select * from TINTUC where MADM=" + maDM;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				TinTuc tinTuc = new TinTuc();
				tinTuc.setMaTT(resultSet.getInt(1));
				tinTuc.setTieuDe(resultSet.getNString(2));
				tinTuc.setNoiDung(resultSet.getNString(3));
				tinTuc.setLienKet(resultSet.getString(4));

				DanhMuc danhMuc = danhMucDAO.getDetail(maDM);
				tinTuc.setDanhMuc(danhMuc);

				list.add(tinTuc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}

		return list;
	}

	public void add(TinTuc tinTuc) {
		String sql = "INSERT INTO TINTUC values (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setNString(1, tinTuc.getTieuDe());
			statement.setNString(2, tinTuc.getNoiDung());
			statement.setNString(3, tinTuc.getLienKet());
			statement.setInt(4, tinTuc.getDanhMuc().getMaDM());

			statement.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
