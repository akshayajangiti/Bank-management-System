
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Deposit extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ano=request.getParameter("ano");
		String amount= request.getParameter("amount");
		int ano1=Integer.parseInt(ano);
		double amt=Double.parseDouble(amount);
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx","Akshaya","Latha@j4011");
/*
			Statement stmt=con.createStatement();
			
			stmt.executeUpdate("UPDATE details SET balance=balance+"+amt+" WHERE accountnumber="+ano1+";" );
			response.sendRedirect("homepage.html");
*/
			
			String updateQuery="UPDATE details SET balance=COALESCE(balance,0)+? WHERE accountnumber=?";
			PreparedStatement pstmt=con.prepareStatement(updateQuery);
			Statement stmt=con.createStatement();
				pstmt.setDouble(1, amt);
				pstmt.setInt(2,ano1);
				pstmt.executeUpdate();
				String s="Deposit";
				SimpleDateFormat d=new SimpleDateFormat("yyyy-mm-dd hh:MM;ss");
				Timestamp t=new Timestamp(System.currentTimeMillis());
			
				stmt.executeUpdate("insert into bankstatements(accountnumber,amount,transactiontype,time) values('"+ano1+"','"+amt+"','"+s+"','"+d.format(t)+"')");
			
			
			response.sendRedirect("homepage.html");
		
		}

		catch(Exception e) {

			PrintWriter out=response.getWriter();

			out.println(e);

		}

	}
}
