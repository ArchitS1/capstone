package ca.wednesdaypc.lnf.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.wednesdaypc.lnf.dao.DAO;
import ca.wednesdaypc.lnf.netspec.JsonResponse;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAO dao = new DAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponse jr = new JsonResponse();
		String username = request.getParameter("username");
		jr.resultCode = dao.createAcct(username, request.getParameter("password"),
				request.getParameter("email")); 
		if (jr.resultCode == JsonResponse.CODE_NOMINAL) {
			request.getSession().setAttribute("username", username);
		}
		
		response.getWriter().append(jr.toJson());
	}

}
