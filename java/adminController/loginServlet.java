package adminController;

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


@WebServlet(urlPatterns = "/adminLoginServ")


public class loginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String e =req.getParameter("email");
		String p = req.getParameter("password");
		
		
		admin ad = new admin();
		ad.setEmail(e);
		ad.setPassword(p);
		
		admin a = null;
		 
		try {
			a=ad.adminLogin();
			if(a!=null){
				
				//Set email in session variable
				HttpSession session = req.getSession(true);
				//we are storing email in session
		        session.setAttribute("session_email", e);	
		        
				
				//Set email & password in cookie
				Cookie cookie =new Cookie("cookie_email", e);
				resp.addCookie(cookie);
				cookie.setMaxAge(10202);
				
				Cookie cookie1 =new Cookie("cookie_password", p);
				resp.addCookie(cookie1);
				cookie1.setMaxAge(60*60*24);
				
				resp.sendRedirect("./admin/home.jsp");
				//rd.forward(req,resp);
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("admin/adminLogin.jsp");
				rd.forward(req, resp);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
