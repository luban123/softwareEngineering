package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.websocket.Session;

import com.entity.Teacher;
import com.sql.SqlHelper;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		// TODO Auto-generated method stub
		String tphone = request.getParameter("tphone");
		String tpassword = request.getParameter("tpassword");
		Teacher manger = new Teacher();
		manger.setTphone(tphone);
		manger.setTpassword(tpassword);
		SqlHelper sqlHelper = new SqlHelper();
		Teacher m = sqlHelper.findIdAndPwd(manger);
		if(m == null){
			response.sendRedirect("login.jsp?tishi=2");
		}else{
			boolean b=sqlHelper.insertManager(manger);
			response.sendRedirect("/news/admin/main.jsp?tid="+m.getTid());
			
		}
		sqlHelper.destory();
	}

}
