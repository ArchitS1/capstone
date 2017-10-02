package ca.wednesdaypc.lnf.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.wednesdaypc.lnf.netspec.JsonResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonResponse jr = new JsonResponse();
		//Get session
		HttpSession session = request.getSession();
		//Destroy session
		session.invalidate();
		//if it worked, send prompt that logout was successful
		// request.getSession(false) should return null (acting as a comparison) and at the same time not create a new session
		if (request.getSession(false) == null) {
			//Output that the logout was successful
			System.out.println("Logout successful");
			jr.resultCode = JsonResponse.CODE_NOMINAL;
		} else {
			jr.resultCode = JsonResponse.CODE_DB_ERROR;
		}
		response.getWriter().append(jr.toJson());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
