
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



		public class BankStatements extends HttpServlet {
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String ano=request.getParameter("ano");
				
				int ano1=Integer.parseInt(ano);
				PrintWriter out=response.getWriter();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectx", "Akshaya", "Latha@j4011");
					PreparedStatement pstmt=con.prepareStatement("select * from bankstatements WHERE accountnumber=?;");
				//	stmt.executeQuery("select * from bankstatements WHERE accountnumber="+ano1+" ;" );
					pstmt.setInt(1, ano1);
					out.println("<html><head><title>Bank Statements</title></head>");
					out.println("<body><h2>Bankstatements</h2>");
					out.println("<table>");
					out.println("<tr><th>Account number</th><th>Amount</th><th>Transcation type</th><th>Timestamp</th></tr>");
					ResultSet res=pstmt.executeQuery();
					while(res.next()) {
						int acnum=res.getInt("accountnumber");
						int amt=res.getInt("amount");
						String type=res.getString("transactiontype");
						String time=res.getString("time");
						
						out.println("<tr><th>'"+acnum+"'</th><th>'"+amt+"'</th><th>'"+type+"'</th><th>'"+time+"'</th></tr>");
					}
					out.println("</table>");
					out.println("</body></html>");
					
				}
				catch(Exception e) {
					
					out.println(e);

				}
			}
		}
	

