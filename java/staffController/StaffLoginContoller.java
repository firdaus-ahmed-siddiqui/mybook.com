package staffController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import staffBean.Staff;
@WebServlet(urlPatterns = "/login")
public class StaffLoginContoller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logemail=req.getParameter("Email");
		String logpassword=req.getParameter("Password");
		Staff staff = new Staff();
		staff.setEmail(logemail);
		staff.setPassword(logpassword);
		RequestDispatcher rd;
		int s;
		try {
			
			s = staff.login();
			System.out.println(s);
			if(s==1) 
			{
				 rd = req.getRequestDispatcher("staff/staffSignIn.jsp");
				req.setAttribute("msg", "Account not activated!!");
				
			}
			else if(s==0) {

					rd = req.getRequestDispatcher("staff/home.jsp");
					req.setAttribute("msg", "Login Success");
					req.setAttribute("logemail", logemail); 
				
				
				
				}
				else 
				{
					rd = req.getRequestDispatcher("staffSignIn.jsp");
					req.setAttribute("msg", "Login Failed");
				}
				

			rd.forward(req, resp);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	}