package userController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminBean.admin;
import userBean.User;

@WebServlet("/userlogin")
public class UserLoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String e = req.getParameter("Email");
		String p = req.getParameter("Password");
		
		
		User ad = new User();
		ad.setEmail(e);
		ad.setPassword(p);
		
		RequestDispatcher rd;
		int s;
		try {
			
			s = ad.login();
			System.out.println(s);
			if(s==1) 
			{
				 rd = req.getRequestDispatcher("home.jsp");
				 HttpSession session = req.getSession();
				 session.setAttribute("m", e);
				 req.setAttribute("msg", "Welcome "+ e);
				
			}
				else 
				{
					rd = req.getRequestDispatcher("signIn.jsp");
					req.setAttribute("mseg", "Login Failed");
				}
				

			rd.forward(req, resp);
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	}
