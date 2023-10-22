package entity;

import java.util.List;

public class Course {
	private Integer ID;
	private String courseName;
	private StatusCourse statusCourse;
	
	public Course(Integer iD, String courseName, StatusCourse statusCourse) {
		super();
		ID = iD;
		this.courseName = courseName;
		this.statusCourse = statusCourse;
	}

	public Course(String courseName, StatusCourse statusCourse) {
		super();
		this.courseName = courseName;
		this.statusCourse = statusCourse;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public StatusCourse getStatusCourse() {
		return statusCourse;
	}

	public void setStatusCourse(StatusCourse statusCourse) {
		this.statusCourse = statusCourse;
	}
	@Override
	public String toString() {
		return "Course [ID=" + ID + ", courseName=" + courseName + ", statusCourse=" + statusCourse + "]";
	}
	
}