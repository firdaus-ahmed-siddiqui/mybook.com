package userController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userBean.Cart;

@WebServlet(urlPatterns = "/delete")
public class DeleteFromCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("eid")) ;
		Cart cart = new Cart();
		cart.setpId(id);
		try {
			int row=cart.DeleteProductFromCart();
			if(row==1) {
				resp.sendRedirect("cart.jsp");
			}
			else {
				System.out.println("Error occured...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
