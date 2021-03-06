package ca.wednesdaypc.lnf.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.wednesdaypc.lnf.dao.DAO;
import ca.wednesdaypc.lnf.netspec.JsonResponse;

/**
 * Servlet implementation class ViewAccount
 */
@WebServlet("/ViewAccount")
public class ViewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAO dao = new DAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponse jr = new JsonResponse();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if (username == null) {
			jr.resultCode = JsonResponse.CODE_NEED_LOGIN;
		} else {
			jr = dao.viewAccount(username);
		}
		response.getWriter().append(jr.toJson());
	}

}
