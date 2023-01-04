<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>新增专业信息</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
</head>
<body>
	<div style="min-width:500px;max-width:800px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);">
		<h3 style="color: gray;">新增专业信息</h3>
		<hr />
		<form class="layui-form" action="major/add">
		  <div class="layui-form-item">
			<label class="layui-form-label">专业编号</label>
			<div class="layui-input-inline">
			  <input type="text" name="major.mno" lay-verify="required"  placeholder="请输入编号" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">专业名称</label>
			<div class="layui-input-inline">
			  <input type="text" name="major.mname" lay-verify="required"  placeholder="请输入专业名称" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">开设学院</label>
			<div class="layui-input-inline">
			  <input type="text" name="major.mcollege_no" lay-verify="required"  placeholder="请输入所属学院名称"" class="layui-input">
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