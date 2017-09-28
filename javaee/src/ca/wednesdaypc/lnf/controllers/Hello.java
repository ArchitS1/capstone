package ca.wednesdaypc.lnf.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.wednesdaypc.lnf.dao.HelloDAO;
import ca.wednesdaypc.lnf.json.JsonResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello() {
		super();
		//TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = (new HelloDAO(getServletContext())).getHello();
		JsonResponse resp = new JsonResponse();
		if (message == null) {
			resp.resultCode = JsonResponse.CODE_DB_ERROR;
		} else {
			resp.payload = message;
			resp.resultCode = JsonResponse.CODE_NOMINAL;
		}
		response.getWriter().append(resp.toJson());
		//response.getWriter().append("Hello World!");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
