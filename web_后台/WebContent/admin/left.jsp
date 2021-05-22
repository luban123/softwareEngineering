<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    String tid=request.getParameter("id");
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="left_menu">
<h1 class="title"><span class="glyphicon glyphicon-list list"></span>功能栏</h1>
<ul class="nav nav-pills nav-stacked">
	<li><a href="select_Course.jsp" role="presentation">名单管理</a></li>
<!-- 	<li><a href="javascript:void(0);" role="presentation">待开发</a></li> -->
	<li><a href="/news/login.jsp" role="presentation">退出系统</a></li>
</ul>
</div>