package org.booksforyou;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.booksforyou.model.User;
import org.booksforyou.services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String emailId= request.getParameter("emailId");
		String password= request.getParameter("password");
		System.out.println("reached login :" + password);
		UserService us= new UserService();
		boolean isValid= us.validateUser(emailId, password);
		System.out.println("valid:" + isValid );
		PrintWriter writer= response.getWriter();
		response.setContentType("text/html");
		request.setAttribute("loginMsg",null );
		
		if(!isValid) {
			request.setAttribute("loginMsg","Invalid Username/password" );
			RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
			view.forward(request, response);
			
		} else {
			HttpSession session = request.getSession();
			UserService userService= new UserService();
			User user = userService.getUser(emailId, password);
			session.setAttribute("user", user);
			RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
			view.forward(request, response);
			
		}

	}

}
