package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Stu_manage;
import com.entity.Courses;
import com.sql.SqlHelper;

/**
 * Servlet implementation class DeleteNews
 */
@WebServlet("/admin/DeleteNews")
public class DeleteNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sno = request.getParameter("sno");
		String cid = request.getParameter("cid");
		System.out.println(sno);
		System.out.println(cid);
		if(!(sno==null)){
			Stu_manage stu=new Stu_manage();
			stu.setSno(sno);
			stu.setCid(cid);
			SqlHelper sqlHelper = new SqlHelper();
			sqlHelper.Deletemanage_student(stu);
			sqlHelper.destory();
		}
		response.sendRedirect("newsList.jsp?cid="+cid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
