<%@page import="cn.edu.zjut.uhcms.model.Teacher"%>
<%@page import="cn.edu.zjut.uhcms.model.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%! String personalRole = null; %>
<%if(session.getAttribute("personalRole")==null){
	//session.setAttribute("tips", "非法访问");
	//response.sendRedirect("login.jsp");
	
	return;
}
personalRole = (String)session.getAttribute("personalRole");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>个人信息</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
</head>
<body>
	<div style="min-width:500px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">查询个人信息</h3>
		<hr />
		<% if("student".equals(personalRole)){
		Student student = (Student)session.getAttribute("personalInfo"); %>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">学号:&nbsp;&nbsp;<%=student.getSno()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">姓名:&nbsp;&nbsp;<%=student.getName()%></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding: 100px;">性别:&nbsp;&nbsp;<%=student.getSex()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">身份证号:&nbsp;&nbsp;<%=student.getId()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">手机号:&nbsp;&nbsp;<%=student.getNumber()%></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding: 100px;">所属学院:&nbsp;&nbsp;<%=student.getCollege()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">所属专业:&nbsp;&nbsp;<%=student.getMajor()%></label>
		</div>
		<% }
		else if("teacher".equals(personalRole)){
		Teacher teacher = (Teacher)session.getAttribute("personalInfo");%>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">工号:&nbsp;&nbsp;<%=teacher.getTno()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">姓名:&nbsp;&nbsp;<%=teacher.getTname()%></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding: 100px;">性别:&nbsp;&nbsp;<%=teacher.getTsex()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">身份证号:&nbsp;&nbsp;<%=teacher.getTid()%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">手机号:&nbsp;&nbsp;<%=teacher.getTnumber()%></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding: 100px;">所属学院:&nbsp;&nbsp;<%=teacher.getTcollege()%></label>
		</div>
		<% }%>
	</div>
</body>
</html>