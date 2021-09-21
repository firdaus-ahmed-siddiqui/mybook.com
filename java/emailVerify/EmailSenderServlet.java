package emailVerify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import userBean.User;

@WebServlet("/emailCheck")
public class EmailSenderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String username= req.getParameter("username");
		String email = req.getParameter("email");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("contact", contact);
		session.setAttribute("address", address);
		session.setAttribute("password", password);
		int t=0;
		User u =new User();
		try {
			t=u.check_duplicate_user(email);
			if(t==0) {
				Random rand = new Random();
				int otp = rand.nextInt(900000) + 100000;
				session.setAttribute("otp",otp);
				String mes = "Your OTP is "+otp;
				try
				{
				   	String to=email;//change accordingly
					//Get the session object
					Properties props = new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.socketFactory.port", "465");
					props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.port", "465");
					Session ses = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("ahmedsiddiqui4799@gmail.com","77@firdaus77");//Put your email id and password here
						}
					});
					//compose message
					try {
						MimeMessage message = new MimeMessage(ses);
						message.setFrom(new InternetAddress("ahmedsiddiqui4799@gmail.com"));//change accordingly
						message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
						message.setSubject("Nice to meet u");
						message.setText(mes);
						//send message
						Transport.send(message);
						System.out.println("message sent successfully");
						resp.sendRedirect("otp.jsp");
					} 
					catch (MessagingException e) {
						throw new RuntimeException(e);
					}

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("signIn.jsp");
				req.setAttribute("mseg", "Already Email or Number Registered");
				rd.forward(req, resp);
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}	
}
