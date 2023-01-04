<%@page import="cn.edu.zjut.uhcms.service.RecordService"%>
<%@page import="cn.edu.zjut.uhcms.service.UserService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="cn.edu.zjut.uhcms.model.Teacher"%>
<%@page import="cn.edu.zjut.uhcms.model.Student"%>
<%@page import="cn.edu.zjut.uhcms.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%! User user = null; %>
<%if(request.getSession().getAttribute("user")==null){
	 session.setAttribute("tips","请先登录。。。");
	 response.sendRedirect("login.jsp");
	 return;
} else {
	user =  (User)request.getSession().getAttribute("user");
}
%>
<%if(session.getAttribute("personalRole") == null) {%>
	<script>
		layer.alert("非法访问");
	</script>
<%
	return;
}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>健康码管理</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
</head>
<body>
	<div style="min-width:500px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">健康码信息显示</h3>
		<hr />
		<div style="height: 50px;">
			<%
			String role =  (String)session.getAttribute("personalRole");
			System.out.println(role);
				boolean r = "student".equals(role);
				String name = "undefined"; 
				String state = "undefined";
				if(r) {
					Student student = (Student)session.getAttribute("personalInfo");
					name = student.getName();
					state = student.getState();
				}
				else {
					Teacher teacher = (Teacher)session.getAttribute("personalInfo");
					name = teacher.getTname();
					state = teacher.getTstate();
				}
			System.out.println(name);

			%>
			<label  style="width: 200px;padding: 100px;"><%=r ? "学生" : "教职工" %>:&nbsp;&nbsp;<%= name %>
			</label>
		</div>
		<div style="height: 50px;">
		<%
   		Date dNow = new Date();
   		SimpleDateFormat ftDate =new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat ftTime =new SimpleDateFormat ("HH:mm:ss");
   		%>
			<label  style="width: 200px;padding: 100px;">日期:&nbsp;&nbsp;<%=ftDate.format(dNow) %></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding: 100px;">时间:&nbsp;&nbsp;<%=ftTime.format(dNow) %></label>
		</div>
		<div style="padding-left: 100px;height: 200px;width: 200px;">
		<%String src="data:image/jpeg;base64," + new RecordService().getHealthCodeBase64(name, state);%>
			<img src="<%=src %>" alt='img' width="100%",height="100%">
		</div>
	</div>

</body>
</html>