package userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import staffBean.Staff;
import userBean.User;

@WebServlet("/userregisterservlet")
public class UserRegisterController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
    	String e1= req.getParameter("first");
        String e2= req.getParameter("second");
        String e3= req.getParameter("third");
        String e4= req.getParameter("fourth");
        String e5= req.getParameter("fifth");
        String e6= req.getParameter("sixth");
        String e7=e1+e2+e3+e4+e5+e6;
        int otpvalue= Integer.parseInt(e7);
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("username");
		String email = (String)session.getAttribute("email");
		String contact = (String)session.getAttribute("contact");
		String address = (String)session.getAttribute("address");
		String password = (String)session.getAttribute("password");
		int otp= (Integer) session.getAttribute("otp");
	
		
		if(otp==otpvalue)
		{
		try
		{
		User user=new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setContact(contact);
		user.setAddress(address);
		user.setPassword(password);
		try {
			int row = user.addUser();
			
			if(row==1) {
				req.setAttribute("mseg", "Success");
				System.out.println("hii");
				RequestDispatcher rd = req.getRequestDispatcher("signIn.jsp");
				rd.forward(req, resp);
			}
			else {
				req.setAttribute("mseg", "Not Success");
				RequestDispatcher rd = req.getRequestDispatcher("signUp.jsp");
				rd.forward(req, resp);

			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}
		}
		else
		{
			
			session.invalidate();
			req.setAttribute("mseg", "Otp not matched");
			RequestDispatcher rd = req.getRequestDispatcher("signUp.jsp");
			rd.forward(req, resp);
		}
		
	}
}
