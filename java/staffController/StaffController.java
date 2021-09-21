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



@WebServlet(urlPatterns = "/getuserservlet")
public class StaffController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username= req.getParameter("username");
		String email = req.getParameter("email");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		Staff staff=new Staff();
		staff.setUsername(username);
		staff.setEmail(email);
		staff.setContact(contact);
		staff.setAddress(address);
		staff.setPassword(password);
		try {
			int row = staff.addStudent();
			
			if(row==1) {
				req.setAttribute("mseg", "Success");
				System.out.println("hii");
				RequestDispatcher rd = req.getRequestDispatcher("staff/staffSignIn.jsp");
				rd.forward(req, resp);
			}
			else {
				req.setAttribute("mseg", "Not Success");
				RequestDispatcher rd = req.getRequestDispatcher("staff/staffSignUp.jsp");
				rd.forward(req, resp);

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}

