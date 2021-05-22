<%@page import="com.sql.SqlHelper"%>
<%@page import="com.entity.Courses"%>
<%@page import="java.util.Map"%>
<%@page import="com.tools.Myfuns"%>
<%@page import="java.util.List"%>
<%@page import="com.action.bean.ActionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String tid=(String)session.getAttribute("tid");
   		session.setAttribute("tid", tid);
        System.out.println(tid);
        SqlHelper sqlHelper = new SqlHelper();
        List <Courses>courselist=sqlHelper.CourseAll(tid);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/news/css/style.css">
<title>学生名单管理</title>
</head>
<body>

<div class="main-frame">
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="left.jsp"></jsp:include>
<div class="right">
<div class="admin">
<!-- <h1 class="title">名单管理</h1> -->
<table class="tab" cellspacing="0" style="margin:0;padding:0">
	<tr>
		<td colspan="5">
		<form action="newsList.jsp" method="post" id="frm">
			<label> 课程:</label>
			<select name="cid" id="cid" class="info">
  			<option value="0" >请选择你的课程</option>
			<%
				for(Courses FL:courselist){
			  			out.println("<option value=\""+FL.getCid()+"\">"+FL.getCname()+"</option>");
			  						}		
			%>
			</select>
			<input type="submit" class="select_submit" name="submit" value="提交" onclick="return go()"> 
		</form>
		</td>
	</tr>
						
</table>
</div>	
</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<div class="windows" id="windows">
	<h2 class="windows-title">修改新闻类别<button type="button" id="exit" onclick="close(0)">X</button></h2>
	<iframe src="" id="update-windows"></iframe>
</div>
<script type="text/javascript">
function go(){//如果选择默认选项，则不跳转
	var myselect=document.getElementById("cid");
	var index=myselect.selectedIndex;
	if(myselect.options[index].value==0)
		{
			alert("请选择一项");
			return false;
		}
	else
		{
			document.getElementById("frm").submit();
		}

}
</script>
</body>
</html>