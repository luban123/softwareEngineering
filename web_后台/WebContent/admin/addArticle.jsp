<%@page import="java.util.List"%>
<%@page import="com.action.bean.ActionBean"%>
<%@page import="com.entity.Courses"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ActionBean actionBean=new ActionBean();
	String tid=(String)session.getAttribute("tid");
	String cid=(String)session.getAttribute("cid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/news/css/style.css">
<title>增加学生</title>
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
	<link rel="stylesheet" href="http://localhost:8080/news/bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
 <script src="http://localhost:8080/news/bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script> 
   <script src="http://localhost:8080/news/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
</head>
<body>
<div class="main-frame">
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="left.jsp"></jsp:include>
<div class="right">
<div class="admin">
<h1 class="title title_right">添加学生<a href="newsList.jsp?cid=<%=cid %>" class="back-btn">返回</a></h1>
	<form name="frm" action="InsertArticle" id="frm" method="post">
	<table>
	<tr class="add_tr">
	<td>学号:</td>
	<td><input id="sno" name="sno" class="form-control"  autocomplete="off" maxlength="100" type="text"/></td>
	<td id="err_sno"></td>
	</tr>
	<tr class="add_tr">
	<td>姓名:</td>
	<td> 
		<input id="sname" name="sname" class="form-control" autocomplete="off" maxlength="100" type="text"/></td>
	</td>
	<td id="err_sname"></td>
	</tr>
	
	<tr class="add_tr">
	<td>手机号:</td>
	<td><input id="sphone" name="sphone" class="form-control"  autocomplete="off" maxlength="20" type="text"/></td>
	<td id="err_sphone"></td>
	</tr>
	<input type="hidden" name="tid" id="tid" value="<%=tid%>">
	<input type="hidden" name="cid" id="cid" value="<%=cid%>">
	
	
	<tr class="add_tr"><td></td><td><a class="btn" onclick="start();" href="javascript:void(0)">保存</a></td></tr>
	
	</table>
	</form>
</div>
</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
function valiInput(id,b){
	   var val = document.getElementById(id).value;
	   if(val==""){
		   document.getElementById("err_"+id).innerHTML="<span class='err'>"+b+"</span>";
		   return false;
	   }else{
		   document.getElementById("err_"+id).innerHTML="<span class='success'>验证通过。</span>";
		   return true;
	   }
}
function start(){
	 var arrs=["sno","sname","sphone"];
	   var b=["请输入学号","请选择姓名","请输入手机号"];
	   var flag=true;
	   for(var i=0;i<arrs.length;i++){
		   flag = valiInput(arrs[i],b[i])&&flag;
	   }
	   if(!flag)return;
	   document.getElementById("frm").submit();
}
</script>
</body>
</html>