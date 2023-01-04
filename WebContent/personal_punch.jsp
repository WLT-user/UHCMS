<%@page import="cn.edu.zjut.uhcms.model.Teacher"%>
<%@page import="cn.edu.zjut.uhcms.model.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.edu.zjut.uhcms.model.User" %>
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
  <title>打卡</title>
  <script src="layer.layer.js"></script>
  <script src="layui-master/dist/layui.js" charset="utf-8"></script>
  <script type="text/javascript">
  
  	function func(){
  		if(!document.getElementById("check").checked){
  		    layer.alert("请先勾选承诺");
  		    return;
  		}
  		if(document.getElementById("healthNo").checked){
  				var value = document.getElementById("symptom").value.trim();
  				if(!value) {layer.alert("请输入症状的描述");return;}
  		}
  		punchForm.submit();
  	}
  	function trim()
    {
  		return this.replace(/(^\s*)|(\s*$)/g, "");  
    } 
  </script>
  <link rel="stylesheet" href="./layui-master/dist/css/layui.css">
</head>
<body>
	<div style="min-width:750px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">健康码打卡</h3>
		<hr />
		<%
			String role =  (String)session.getAttribute("personalRole");
			System.out.println(role);
				boolean r = "student".equals(role);
				String no = "undefined";
				String name = "undefined"; 
				String sex = "undefined";
				String id = "undefined";
				if(r) {
					Student student = (Student)session.getAttribute("personalInfo");
					no = student.getSno();
					name = student.getName();
					sex = student.getSex();
					id = student.getId();
				}
				else {
					Teacher teacher = (Teacher)session.getAttribute("personalInfo");
					no = teacher.getTno();
					name = teacher.getTname();
					sex = teacher.getTsex();
					id = teacher.getTid();
				}

			%>
		<form class="layui-form" name="punchForm" action="record/punch">
		<div style="height: 50px;">
			<label  style="width: 200px;padding-left: 100px;">学工号：<%=no%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding-left: 100px;">姓名：<%=name%></label>
		</div>
		<div style="height: 50px;">
			<label style="width: 200px;padding-left: 100px;">性别：<%=sex%></label>
		</div>
		<div style="height: 50px;">
			<label  style="width: 200px;padding-left: 100px;">身份证号：<%=id%></label>
		</div>
		<div style="height: 50px;">
				<label style="padding-left: 100px;padding-right:20px">手机号:</label>
				<div class="layui-input-inline" >
			  		<input type="text" name="ppf.number" lay-verify="required" placeholder="请输入手机号" class="layui-input" style="height:25px">
				</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<label style="width: 200px;padding: 100px;">本人近期（14天内）是否去过湖北省或重点疫区？</label>
			<div class="layui-input-block">
			    <input type="radio" name="ppf.check_1" value="yes" title="是">
			    <input type="radio" name="ppf.check_1" value="no" title="否">
			</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">本人近期（14天内）是否去过国外？</label>
			<div class="layui-input-block">
			    <input type="radio" name="ppf.check_2" value="yes" title="是">
			    <input type="radio" name="ppf.check_2" value="no" title="否">
			</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">本人近期（14天内）是否接触过新冠确诊病人或疑似病人？</label>
			<div class="layui-input-block">
			    <input type="radio" name="ppf.check_3" value="yes" title="是">
			    <input type="radio" name="ppf.check_3" value="no" title="否">
			</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">本人是否被卫生部门确认为新冠肺炎确诊病例或疑似病例？</label>
			<div class="layui-input-block">
			    <input type="radio" name="ppf.check_4" value="yes" title="是">
			    <input type="radio" name="ppf.check_4" value="no" title="否">
			</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<label  style="width: 200px;padding: 100px;">当前健康状况？无异常、发烧（≥37.3℃）、乏力、干咳、鼻塞、流涕、咽痛、腹泻等。</label>
			<div class="layui-input-block">
			    <input type="radio" id="healthYes" name="ppf.check_5" value="yes" title="健康">
			    <input type="radio" id="healthNo" name="ppf.check_5" value="no" title="异常">
			</div>
		</div>
		<div style="height: 50px;">
				<label style="padding-left: 100px;padding-right:20px">如果有以上症状，请填写:</label>
				<div class="layui-input-inline" >
			  		<input type="text" id="symptom" name="ppf.check_5_extra" lay-verify="required" placeholder="症状描述" class="layui-input" style="height:25px;weith:300px">
				</div>
		</div>
		<div class="layui-form-item" style="height: 50px;">
			<input type="checkbox" name="promise" id="check" title="本人郑重承诺：填报信息真实，愿意承担相应的法律责任。" lay-skin="primary">
		</div>	
		<div class="btn_submit">
			<input type="button" value="立即提交" class="layui-btn layui-btn-fluid" onclick="func()">
		</div>
	</div>
</body>
</html>