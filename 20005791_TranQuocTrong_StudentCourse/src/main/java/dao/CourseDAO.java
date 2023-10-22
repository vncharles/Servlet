package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.Course;
import entity.StatusCourse;
import entity.Student;

public class CourseDAO {
	private DataSource dataSource;
	private StudentDAO studentDAO;

	public CourseDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.studentDAO = new StudentDAO(dataSource);
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
	
	public List<Course> getAll() {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT * FROM Course;";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Course cource = new Course();
				cource.setID(resultSet.getInt("ID"));
				cource.setCourseName(resultSet.getNString("CourseName"));
				cource.setStatusCourse(StatusCourse.valueOf(resultSet.getString("StatusCourse")));
//				cource.setListStudent(studentDAO.getAllByCourseID(cource.getID()));
				list.add(cource);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return list;
	}
	
	public Course detailCourse(int id) {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT * FROM Course where ID="+id;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Course cource = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				cource = new Course();
				cource.setID(resultSet.getInt("ID"));
				cource.setCourseName(resultSet.getNString("CourseName"));
				cource.setStatusCourse(StatusCourse.valueOf(resultSet.getString("StatusCourse")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return cource;
	}

	public void add(Course course) {
		// TODO Auto-generated method stub
		String sql = "insert into COURSE values  ('"+ course.getCourseName() +"', '" + course.getStatusCourse() +"')" ;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void update(Course course) {
		String sql = "update course set CourseName =  '"+ course.getCourseName() +"', statusCourse = '" + course.getStatusCourse() +"' where id = "+ course.getID();;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "BEGIN TRANSACTION;\r\n"
				+ "\r\n"
				+ "BEGIN TRY\r\n"
				+ "    DELETE FROM register WHERE id_course = " + id 
				+ "    DELETE FROM course WHERE ID = " + id
				+ "\r\n"
				+ "    COMMIT TRANSACTION\r\n"
				+ " END TRY\r\n"
				+ "BEGIN CATCH\r\n"
				+ "    ROLLBACK TRANSACTION \r\n"
				+ " END CATCH";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
	}
}