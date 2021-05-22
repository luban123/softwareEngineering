<%@page import="com.sql.SqlHelper"%>
<%@page import="com.entity.Courses"%>
<%@page import="java.util.Map"%>
<%@page import="com.tools.Myfuns"%>
<%@page import="java.util.List"%>
<%@page import="com.action.bean.ActionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String tid=(String)session.getAttribute("tid");//工号
        ActionBean actionBean=new ActionBean();
        int p=Myfuns.Int(request.getParameter("p"));
        //String cid=request.getParameter("cid");//从上一个页面获取课程号
        String cid=Myfuns.convert(request.getParameter("cid"));
        session.setAttribute("cid", cid);
        String sname=Myfuns.convert(request.getParameter("sname")); 
        SqlHelper sqlHelper=new SqlHelper();
        String cname=sqlHelper.findName(cid).getCname();
        if(p<0){
        	response.sendRedirect("newsList.jsp");
        }
    	Map result=actionBean.findStudentByPage(p,cid,sname);
        List <Map>list=(List)result.get("list");
        int rows=(Integer)result.get("rows");
        if(p>0&&list.size()<1){
        	response.sendRedirect("newsList.jsp");
        }       
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/news/css/style.css">
<title>学生管理系统</title>
</head>
<body>

<div class="main-frame">
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="left.jsp"></jsp:include>
<div class="right">
<div class="admin">
<div class="title choosed_course"><span style="color:black; float:left">课程编号及名称：</span>
<span style="float:left"><%=cid%> <%=cname %></span></div>
<table class="tab" cellspacing="0" style="margin:0;padding:0">
	<tr>
		<td colspan="5">
		<form action="newsList.jsp" method="post" id="frm">
			<label>姓名</label>
			<input type="text" class="form-control" name="sname" id="sname" value="<%=sname==null?"":sname%>"/>
			
			<input type="hidden" name="p" id="go_page_number" value="<%=p%>">
			<input type="hidden" name="cid" id="cid" value="<%=cid%>">
			<input type="submit" class="search_commit" value="搜索"/>
		</form>
		</td>
	</tr>

	<tr>		
	<td style="width:40px;font-weight:bold;font-size:18px;">选择</td>
	<td class="text-center" style="width:40px;font-weight:bold;font-size:18px;">序号</td>
		<td class="text-center" style="font-weight:bold;font-size:18px;">学号</td>
			<td class="text-center" style="font-weight:bold;font-size:18px;">姓名</td>
			<td class="text-center" style="font-weight:bold;font-size:18px;">手机号</td></tr>
		<%
		int i=10*p;
		int total;
		if(rows%10==0)
			{
			total=rows/10;
			}
		else {
			total=rows/10+1;
		}
		for(Map map:list){
			%>
			<tr>		
	<td ><input type="radio" name="rad" value="<%=map.get("sno")%>" autocomplete="off"/></td>
	<td><%=++i %></td>
		<td class="text-center"><%=map.get("sno") %></td>
			<td class="text-center"><%=map.get("sname") %></td><td class="text-center"><%=map.get("sphone") %></td></tr>
			<%
		}
		%>
		
		<tr>
		<td colspan="5" class="page">
		<span>总共<%=rows %>名学生</span>
		<span>当前是第<%=p+1 %>页</span>
		<%if(p>0){ %>
			<a href="javascript:void(0)" onclick="go(0)">首页</a>
			<a href="javascript:void(0)" onclick="go(<%=p-1%>)">上一页</a>
		<%} %>
		<%if(p<total-1){ %>
			<a href="javascript:void(0)" onclick="go(<%=p+1%>)" >下一页</a>
			<a href="javascript:void(0)" onclick="go(<%=total-1%>)">尾页</a>
		<%} %>
			<span>共<%=total%>页</span>
			<span>转到第
			<select onchange="go(this.value)"> 
				<%for(int j=0;j<total;j++){%>
			<option value="<%=j%>"<%=j==p?"selected='selected'":""%>><%=j+1%></option>
			<%=j+1%></option>
					<%} %>
			</select>页</span>
		
		</td>
		</tr>
		<tr><td colspan="5" style="border-bottom:0">
		<a class="btn" href="addArticle.jsp">添加学生</a>
		 <a class="btn" href="javascript:void(0)" onclick="DeleteNews()">删除学生</a>

     <form action="${pageContext.request.contextPath}/ExcelImportServlet" enctype="multipart/form-data" method="post" class="choose_file">
       <input type="file" name="file" value="excel导入" >
       <input type="submit" class="search_commit"  value="上传">
  </form>   
		</td></tr>
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
function go(n){
	document.getElementById("go_page_number").value=n;
	document.getElementById("frm").submit();
	
}
function DeleteNews(){
	var c=document.getElementById("cid").value;
	var choice=document.getElementsByName("rad");
	var j=null;
	for(var i=0;i<choice.length;i++){
		if(choice[i].checked){
			j=choice[i];
			break;
		}
	}
	if(j==null){
		alert("请选择一项");
		return;
	}
	location="DeleteNews?sno="+j.value+"&cid="+c;
}
</script>

<body οnlοad="a()">
</body>
</html>