package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.Course;
import entity.StatusCourse;
import entity.Student;

public class StudentDAO {
	
	private DataSource dataSource;

	public StudentDAO(DataSource dataSource) {
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
	
	public List<Student> getAllByCourseID(int id) {
		List<Student> listStudent = new ArrayList<Student>();
		String sql = "select distinct s.* from Student s "
				+ "join Register r on s.ID=r.ID_Student "
				+ "join Course c on r.ID_Course=c.ID "
				+ "where c.ID="+id;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Integer studentID = resultSet.getInt("ID");
				String firstName = resultSet.getNString("FirstName");
				String lastName = resultSet.getNString("LastName");
				String email = resultSet.getString("Email");
				
				listStudent.add(new Student(studentID, firstName, lastName, email));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return listStudent;
	}
	
	public List<Student> getAllByNotCourseID(int id) {
		List<Student> listStudent = new ArrayList<Student>();
		String sql = "select distinct s.* from Student s "
				+ "join Register r on s.ID=r.ID_Student "
				+ "join Course c on r.ID_Course=c.ID "
				+ "where s.ID not in ("
				+ "	select sr.ID_Student from Register sr where sr.ID_Course=" + id
				+ ")";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Integer studentID = resultSet.getInt("ID");
				String firstName = resultSet.getNString("FirstName");
				String lastName = resultSet.getNString("LastName");
				String email = resultSet.getString("Email");
				
				listStudent.add(new Student(studentID, firstName, lastName, email));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return listStudent;
	}
	
	public List<Student> getAll() {
		List<Student> listStudent = new ArrayList<Student>();
		String sql = "select * from Student";;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Integer studentID = resultSet.getInt("ID");
				String firstName = resultSet.getNString("FirstName");
				String lastName = resultSet.getNString("LastName");
				String email = resultSet.getString("Email");
				
				listStudent.add(new Student(studentID, firstName, lastName, email));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return listStudent;
	}

	public void add(Student student) {
		// TODO Auto-generated method stub
		String sql = "insert into student values  ('"+ student.getFirstName() +"', '" + student.getLastName() +"','" +student.getEmail() + "')" ;
		
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

	public Student getDetail(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from Student where ID="+id;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Student student = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				Integer studentID = resultSet.getInt("ID");
				String firstName = resultSet.getNString("FirstName");
				String lastName = resultSet.getNString("LastName");
				String email = resultSet.getString("Email");
				
				student = new Student(studentID, firstName, lastName, email);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(connection, statement, resultSet);
		}
		
		return student;
	}

	public void update(Student student) {
		// TODO Auto-generated method stub
		String sql = "update student set FirstName =  '"+ student.getFirstName() +"', lastname = '" + student.getLastName() +"', email = '" +student.getEmail() + "' where id = "+ student.getID();
		
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
				+ "    DELETE FROM register WHERE id_student = " + id 
				+ "    DELETE FROM student WHERE ID = " + id
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
