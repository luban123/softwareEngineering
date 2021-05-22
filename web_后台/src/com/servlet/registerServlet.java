package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Teacher;
import com.sql.SqlHelper;
import com.tools.Myfuns;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		String tid = request.getParameter("tid");
		String tname = request.getParameter("tname");
		String tphone = request.getParameter("tphone");
		String tpassword = request.getParameter("tpassword");
		Teacher manger = new Teacher();
		manger.setTid(tid);
		manger.setTname(tname);
		manger.setTphone(tphone);
		manger.setTpassword(tpassword);
		SqlHelper sqlHelper = new SqlHelper();
		Teacher m = sqlHelper.ManagerByID(manger.getTphone());
		if(m != null){
			response.sendRedirect("register.jsp?tishi=2");
		}else{
			boolean b=sqlHelper.insertManager(manger);
			response.sendRedirect("/news/login.jsp");
		}
		sqlHelper.destory();
		}
}
