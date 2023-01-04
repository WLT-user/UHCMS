<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>新增信息</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
</head>
<body>
	<div style="min-width:500px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">新增教师信息</h3>
		<hr />
		<form class="layui-form" action="teacher/add">
		  <div class="layui-form-item">
			<label class="layui-form-label">工号</label>
			<div class="layui-input-inline">
			  <input type="text" name="teacher.tno" lay-verify="required"  placeholder="请输入工号" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
			  <input type="text" name="teacher.name" lay-verify="required"  placeholder="请输入姓名" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-inline">
			  <input type="text" name="teacher.sex" lay-verify="required"  placeholder="请输入性别" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">身份证号</label>
		  		<div class="layui-input-inline">
		  		  <input type="text" name="teacher.id" lay-verify="identity" placeholder="请输入身份证号" class="layui-input">
		  		</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">手机号</label>
		  		<div class="layui-input-inline">
		  		  <input type="text" name="teacher.number" lay-verify="identity" placeholder="请输入手机号" class="layui-input">
		  		</div>
		  </div>
		  <div class="layui-form-item">
		  		<label class="layui-form-label">学院</label>
		  		<div class="layui-input-inline">
		  			 <input type="text" name="teacher.collge" lay-verify="required"  placeholder="请输入学院" class="layui-input">
		  		</div>
		  </div>
		    <div class="layui-form-item">
		      <label class="layui-form-label">角色</label>
		      <div class="layui-input-inline">
		        <select name="interest" lay-filter="aihao">
				  <option value="0" selected="">普通教师</option>
		          <option value="1">校级管理员</option>
		          <option value="2">院级管理员</option>
		        </select>
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
	</div>
</body>
</html>