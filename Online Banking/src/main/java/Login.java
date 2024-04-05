
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName=request.getParameter("username");
		String userPassword=request.getParameter("password");
		
		PrintWriter out=response.getWriter();
		
		
		try {
			//REGISTER DRIVER
			Class.forName("com.mysql.jdbc.Driver");
			//GET CONNECTION
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx","Akshaya","Latha@j4011");
			//CREATE STATEMENT
			//Statement stmt=con.createStatement();
		//	stmt.executeQuery("select * from details where username=? and password=?");
			PreparedStatement pstmt=con.prepareStatement("select * from details where username=? and password=?");
			pstmt.setString(1,userName);
			pstmt.setString(2,userPassword);
			//RESULTSET OBJECT
			ResultSet res=pstmt.executeQuery();
			
			if(res.next())
			{
				response.sendRedirect("homepage.html");
			}
			else
			{
				System.out.print("INVALID CREDENTIALS");
			}
			
			
		}
		catch(Exception e) {
			System.out.print("Exception is "+e);
		}
	
	
	}

}
