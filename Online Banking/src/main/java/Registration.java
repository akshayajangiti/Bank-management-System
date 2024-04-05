
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registration extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String userName=request.getParameter("username");
	String userEmail=request.getParameter("email");
	String userDOB=request.getParameter("dob");
	String userAccountnumber=request.getParameter("ano");
	String userPassword=request.getParameter("password");
	
	PrintWriter out=response.getWriter();
	
	try {
		//REGISTER DRIVER
		Class.forName("com.mysql.jdbc.Driver");
		//GET CONNECTION
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx","Akshaya","Latha@j4011");
		//CREATE STATEMENT
		Statement stmt=con.createStatement();
		stmt.executeUpdate("insert into details(username,email,dateofbirth,accountnumber,password) values('"+userName+"','"+userEmail+"','"+userDOB+"','"+userAccountnumber+"','"+userPassword+"')");
		
		response.sendRedirect("Login.html");
	
	}
	catch(Exception e) {
		System.out.print("Exception is "+e);
	}
	}

}

