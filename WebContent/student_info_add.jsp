<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>新增学生信息</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
</head>
<body>
	<div style="min-width:500px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">新增学生信息</h3>
		<hr />
		<form class="layui-form" action="student/add">
		  <div class="layui-form-item">
			<label class="layui-form-label">学号</label>
			<div class="layui-input-inline">
			  <input type="text" name="student.sno" lay-verify="required"  placeholder="请输入学号" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
			  <input type="text" name="student.name" lay-verify="required"  placeholder="请输入姓名" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
			  <input type="text" name="student.sex" lay-verify="required"  placeholder="请输入性别" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">身份证号</label>
		  		<div class="layui-input-inline">
		  		  <input type="text" name="student.id" lay-verify="required" placeholder="请输入身份证号" class="layui-input">
		  		</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">手机号</label>
		  		<div class="layui-input-inline">
		  		  <input type="text" name="student.number" lay-verify="required" placeholder="请输入手机号" class="layui-input">
		  		</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">学院</label>
		  		<div class="layui-input-inline">
		  			 <input type="text" name="student.college" lay-verify="required"  placeholder="请输入学院" class="layui-input">
		  		</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">专业</label>
		  		<div class="layui-input-inline">
		  			 <input type="text" name="student.major" lay-verify="required"  placeholder="请输入专业" class="layui-input">
		  		</div>
		  </div>
			<div class="layui-form-item">
			  <div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			  </div>
			</div>
		</form>
		<script src="layui-master/dist/layui.js" charset="utf-8"></script>
		<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
	</div>
</body>
</html>