

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Transfer extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano=request.getParameter("ano");
		String ano2=request.getParameter("ano2");
		String amount= request.getParameter("amount");
		int ano1=Integer.parseInt(ano);
		int ano02=Integer.parseInt(ano2);
		double amt=Double.parseDouble(amount);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx", "Akshaya", "Latha@j4011");
		    Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE details SET balance=COALESCE(balance,0)-"+amt+" WHERE accountnumber="+ano1+";" );
			stmt.executeUpdate("UPDATE details SET balance=COALESCE(balance,0)+"+amt+" WHERE accountnumber="+ano02+";" );	

			String s1="Debit";
			String s2="Credit";
			SimpleDateFormat d=new SimpleDateFormat("yyyy-mm-dd hh:MM;ss");
			Timestamp t=new Timestamp(System.currentTimeMillis());
		
			stmt.executeUpdate("insert into bankstatements(accountnumber,amount,transactiontype,time) values('"+ano1+"','"+amt+"','"+s1+"','"+d.format(t)+"')");
			stmt.executeUpdate("insert into bankstatements(accountnumber,amount,transactiontype,time) values('"+ano02+"','"+amt+"','"+s2+"','"+d.format(t)+"')");

			
			
	//		String updateQuery1="UPDATE details SET balance=COALESCE(balance,0)-? WHERE accountnumber=?";
	//		String updateQuery2="UPDATE details SET balance=COALESCE(balance,0)+? WHERE accountnumber=?";
	/*		PreparedStatement pstmt1=con.prepareStatement(updateQuery1);
			PreparedStatement pstmt2=con.prepareStatement(updateQuery2);
				pstmt1.setDouble(1, amt);
				pstmt1.setInt(2,ano1);
				pstmt2.setDouble(1, amt);
				pstmt2.setInt(2,ano02);
				pstmt1.executeUpdate();
				pstmt2.executeUpdate();
				ResultSet res=pstmt1.executeQuery();
				
				if(res.next())
				{
					response.sendRedirect("homepage.html");
				}
				else
				{
					System.out.print("INVALID CREDENTIALS");
				}
		*/	
			response.sendRedirect("homepage.html");
			con.close();
			
		}
		catch(Exception e) {
			PrintWriter out=response.getWriter();
			out.println(e);
		}
	}
}
