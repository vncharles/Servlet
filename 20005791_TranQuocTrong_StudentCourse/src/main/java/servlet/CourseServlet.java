package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.CourseDAO;
import dao.RegisterDAO;
import dao.StudentDAO;
import entity.Course;
import entity.StatusCourse;
import entity.Student;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(CourseServlet.class.getName());
	
	@Resource(name="jdbc/Student")
	private DataSource dataSource;
	
	private CourseDAO courseDAO;
	private StudentDAO studentDAO;
    private RegisterDAO registerDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			courseDAO = new CourseDAO(dataSource);
			studentDAO = new StudentDAO(dataSource);
			registerDAO = new RegisterDAO(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String function = request.getParameter("function");
			if(function==null) function = "GET_LIST_COURSE";
			
			switch (function) {
			case "GET_LIST_COURSE": 
				getListCourse(request, response);
				break;
			case "POST_ADD":
				addCourse(request, response);
				break;
			case "GET_UPDATE":
				getFormUpdate(request, response);
				break;
			case "POST_UPDATE":
				updateCourse(request, response);
				break;
			case "DELETE":
				deleteCourse(request, response);
				break;
			case "REGISTER": 
				startRegister(request, response);
				break;
			case "DONE_REGISTER":
				saveRegister(request, response);
				break;
			case "UNREGISTER":
				unRegister(request, response);
				break;
			case "GET_LIST_STUDENT":
				getListStudent(request, response);
			default:
				throw new IllegalArgumentException("Unexpected value: " + function);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	

	private void getListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("StudentServlet").forward(request, response);
	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("courseId");
		courseDAO.delete(Integer.parseInt(id));
		getListCourse(request, response);
	}

	private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("ID");
		String courseName = request.getParameter("courseName");
		String statusCourse = request.getParameter("statusCourse");
		
		Course course = new Course(Integer.parseInt(id), courseName, StatusCourse.valueOf(statusCourse));
		
		courseDAO.update(course);
		getListCourse(request, response);
	}

	private void getFormUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("courseId");
		Course course = courseDAO.detailCourse(Integer.parseInt(id));
		
		request.setAttribute("COURSE", course);
		request.getRequestDispatcher("/course/form-course.jsp").forward(request, response);
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseName = request.getParameter("courseName");
		String statusCourse = request.getParameter("statusCourse");
		
		Course course = new Course(courseName, StatusCourse.valueOf(statusCourse));
		
		courseDAO.add(course);
		getListCourse(request, response);
	}

	private void unRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseId = request.getParameter("courseId");
		String studentId = request.getParameter("studentId");
		
		registerDAO.unregister(Integer.parseInt(courseId), Integer.parseInt(studentId));
		
		getListCourse(request, response);
	}

	private void saveRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseId = request.getParameter("courseId");
		String studentId = request.getParameter("studentId");
		
		registerDAO.register(Integer.parseInt(courseId), Integer.parseInt(studentId));
		
		getListCourse(request, response);
	}

	private void startRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String courseId = request.getParameter("courseId");
		
		Course course = courseDAO.detailCourse(Integer.parseInt(courseId));
		List<Student> listStudent = studentDAO.getAllByNotCourseID(Integer.parseInt(courseId));
		
		request.setAttribute("course", course);
		request.setAttribute("listStudent", listStudent);
		
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private void getListCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Course> listCourse = courseDAO.getAll();
		String courseId = request.getParameter("courseId");
		
		if(courseId!=null) {
			request.setAttribute("courseId", courseId);
			request.setAttribute("courseStudents", studentDAO.getAllByCourseID(Integer.valueOf(courseId)));
		}
		
		
		request.setAttribute("listCourse", listCourse);
		request.getRequestDispatcher("/list-course.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}