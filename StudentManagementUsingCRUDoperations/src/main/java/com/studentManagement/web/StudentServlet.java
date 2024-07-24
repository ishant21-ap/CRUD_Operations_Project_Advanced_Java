package com.studentManagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.studentManagement.dao.StudentDao;
import com.studentManagement.model.Student;


public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	
	@Override
	public void init() {  
		 // Initialize StudentDao instance to interact with the database
		studentDao = new StudentDao();
	}

    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Determine the action to take based on the URL path
        String action = request.getServletPath();
        
        try {
            // Handle different actions based on the URL path
            switch (action) {
                case "/new":
                    showNewForm(request, response); // Show form to create a new student
                    break;
                case "/insert":
                    insertStudent(request, response); // Insert a new student into the database
                    break;
                case "/delete":
                    deleteStudent(request, response); // Delete a student from the database
                    break;
                case "/edit":
                    showEditForm(request, response); // Show form to edit an existing student
                    break;
                case "/update":
                    updateStudent(request, response); // Update an existing student in the database
                    break;
                default:
                    listStudent(request, response); // List all students
                    break;
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            throw new ServletException(ex);
        }
	}
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	   // Display the form to add a new student
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to student-form.jsp
        request.getRequestDispatcher("student-form.jsp").forward(request, response);
    }
    
    
    // Insert a new student into the database
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");

        // Convert the date string to a Date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = sdf.parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
     // Create a new Student object and insert it into the database
        Student newStudent = new Student(name, email, dob);
        studentDao.insertStudent(newStudent);
        // Redirect to the list of students
        response.sendRedirect("list");
    }
    
 // Display the form to edit an existing student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        // Get the student ID from the request parameters
        int id = Integer.parseInt(request.getParameter("id"));
        // Fetch the student details from the database
        Student existingStudent = studentDao.selectStudentById(id);
        // Set the student object as a request attribute
        request.setAttribute("student", existingStudent);
        // Forward the request to student-form.jsp for editing
        request.getRequestDispatcher("student-form.jsp").forward(request, response);
    }

    // Update an existing student's information in the database
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Get form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");

        // Convert the date string to a Date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = null;
        try {
            dob = sdf.parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a Student object with the updated information
        Student student = new Student(id, name, email, dob);
        // Update the student in the database
        studentDao.updateStudent(student);
        // Redirect to the list of students
        response.sendRedirect("list");
    }

    // Delete a student from the database
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Get the student ID from the request parameters
        int id = Integer.parseInt(request.getParameter("id"));
        // Delete the student from the database
        studentDao.deleteStudent(id);
        // Redirect to the list of students
        response.sendRedirect("list");
    }

    // List all students and forward the request to student-list.jsp
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Retrieve the list of all students from the database
        List<Student> listStudent = studentDao.selectAllStudents();
        // Set the list of students as a request attribute
        request.setAttribute("listStudent", listStudent);
        // Forward the request to student-list.jsp
        request.getRequestDispatcher("student-list.jsp").forward(request, response);
    }

}
