
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


		public class Withdraw extends HttpServlet {
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String ano=request.getParameter("ano");
				String amount= request.getParameter("amount");
				int ano1=Integer.parseInt(ano);
				double amt=Double.parseDouble(amount);
				PrintWriter out=response.getWriter();
				
				try {
					
					
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx", "Akshaya", "Latha@j4011");
					
					Statement stmt=con.createStatement();
					stmt.executeUpdate("UPDATE details SET balance=balance-"+amt+" WHERE accountnumber="+ano1+" ;" ); 
				
					String s="Withdraw";
					SimpleDateFormat d=new SimpleDateFormat("yyyy-mm-dd hh:MM;ss");
					Timestamp t=new Timestamp(System.currentTimeMillis());
				
					stmt.executeUpdate("insert into bankstatements(accountnumber,amount,transactiontype,time) values('"+ano1+"','"+amt+"','"+s+"','"+d.format(t)+"')");
				
					response.sendRedirect("homepage.html");
				}
				catch(Exception e) {
					
					out.println(e);

				}
			}
		}
	

