package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Stu_manage;
import com.sql.SqlHelper;
import com.tools.Myfuns;

/**
 * Servlet implementation class InsertArticle
 */
@WebServlet("/admin/InsertArticle")
public class InsertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertArticle() {
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
		 String sno=request.getParameter("sno");
		 String sname=request.getParameter("sname");
		 String cid=request.getParameter("cid");	
		 String tid=request.getParameter("tid");	
		 String sphone=request.getParameter("sphone");		
		 Stu_manage m=new Stu_manage();
		 m.setCid(cid);
		 m.setSno(sno);
		 m.setTid(tid);
		 m.setStatus("1");
		 SqlHelper sqlHelper=new SqlHelper();
		 boolean a=sqlHelper.findByIDandCourses(sno,cid);
		 boolean b=sqlHelper.InsertStu(m);
		 if(a==false)
		 {
			 response.setCharacterEncoding("utf-8");
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
				out.print("该学生已在课程中。<a href=\"addArticle.jsp\">返 回重新输入。</a>");
				out.flush();
				out.close();
		 }
		 else {
		
		 
		 sqlHelper.destory();
		 if(!b) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("没有该学生。<a href=\"addArticle.jsp\">返回重新输入。</a>");
				out.flush();
				out.close();
			}
			else {
				response.sendRedirect("newsList.jsp?cid="+cid);
			}
			
			}
	}
		 

}
