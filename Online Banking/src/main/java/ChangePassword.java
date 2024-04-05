
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassword extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String psw=request.getParameter("password1");
		String psw2= request.getParameter("password2");
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx", "Akshaya", "Latha@j4011");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE details SET password='"+psw2+"' WHERE username='"+userName+"' and password='"+psw+"'" );
			
			response.sendRedirect("homepage.html"); 
	/*	//2	String updateQuery="UPDATE details SET password=? WHERE username=? and password=?";
			PreparedStatement pstmt=con.prepareStatement("UPDATE details SET password=? WHERE username=? and password=?");
            pstmt.setString(1,psw2);
			pstmt.setString(2,userName);
			pstmt.setString(3,psw);
			
			//RESULTSET OBJECT
			ResultSet res=pstmt.executeQuery();
			
			if(res.next())
			{
				response.sendRedirect("homepage.html");
			}
			else
			{
				System.out.print("INVALID CREDENTIALS");
			}*/
		}
		catch(Exception e) {
			PrintWriter out=response.getWriter();
			out.println(e);
		}
	}
}

