<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String tishi=request.getParameter("tishi");
    String a="";
    if(tishi==null||tishi.equals("")){
    	a="";
    }
    else if(Integer.parseInt(tishi)==2){
    	a="用户名已经存在";
    }
   
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:8080/news/css/style.css">
<link rel="stylesheet" href="http://localhost:8080/news/bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
<script src="http://localhost:8080/news/bootstrap-3.3.7-dist/js/jquery-3.2.1.min.js"></script> 
<script src="http://localhost:8080/news/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
<title>学生系统管理</title>
</head>
<body>
	
	
	<div class="login_main">
		<div class="login_bg">
			 <div class="login_title">
		    	<span><img src="images//login-logo.png" align=“absbottom”></img>学生管理系统</span>
	        </div>
	        <div class="login register">
			<h2 ><b>用户注册</b></h2>
  			<form action="registerServlet" method="post" id="frm" >
  			<label>&nbsp&nbsp&nbsp&nbsp手机号:</label>
  			<input type="text" name="tphone" class="form-control"  id="tphone"placeholder="输入手机号"></input>
  			<p>
  			<label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp工号:</label>
  			<input type="text" name="tid" class="form-control"  id="tid"placeholder="输入工号"></input>
  			</p>
  			<p>
  			<label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp姓名:</label>
  			<input type="text" name="tname" class="form-control" id="tname"placeholder="输入姓名"></input>
  			</p>
  			<p>
  			<label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp密码:</label>
  			<input type="password" name=tpassword class="form-control" id="tpassword"placeholder="输入密码"></input>
  			</p>
  			<p>
  			<label>确认密码:</label>
  			<input type="password" name="re_tpassword" class="form-control" id="re_tpassword"placeholder="确认密码"></input>
  			</p>
  			<p><a class="register_button login_button_2 " href="javascript:void(0)"  onclick="post()">确认</a></p>
			<p><span id="tishi" class="tishi"><%=a %></span></p>
			</form>
			</div>
		</div>
	</div>
	  <jsp:include page="admin/bottom.jsp"></jsp:include>
	 <script type="text/javascript">
	 function post(){
		 var tphone=document.getElementById("tphone").value;
		 var tid=document.getElementById("tid").value;
		 var tname=document.getElementById("tname").value;
		 var tpassword=document.getElementById("tpassword").value;
		 var re_tpassword=document.getElementById("re_tpassword").value;
		  document.getElementById("tishi").innerHTML="";
		  if(tphone == "" || tid == "" || tname==""||tpassword==""||re_tpassword==""){
			  document.getElementById("tishi").innerHTML="请将注册信息填写完整";
			  return;
		  }
		  else if(tpassword!=re_tpassword){
			  document.getElementById("tishi").innerHTML="两次密码输入不一致";
			  return;
		  }
		  document.getElementById("frm").submit();
	 }
	 </script>
</body>
</html>