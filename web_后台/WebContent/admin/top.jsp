<%@page import="com.entity.Teacher"%>
<%@page import="com.sql.SqlHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String tid=(String)session.getAttribute("tid");//工号
    	SqlHelper sqlHelper=new SqlHelper();
    	String tname=sqlHelper.ManagerByID2(tid).getTname();
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="top">
	<h1>学生管理</h1>
<!-- 	<div class="user_teacher" > -->
	<a href="main.jsp?tid=<%=tid %>" class="back-btn back">返回主页面</a>
	<span class="user_name" ><span class="glyphicon glyphicon-user" ></span>你好，<%=tname%>老师</span>
	
	
	
<!-- 	</div> -->
</div>