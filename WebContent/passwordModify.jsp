<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>修改密码</title>
  <link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
  <script src="layer.layer.js"></script>
  <script src="layui-master/dist/layui.js" charset="utf-8"></script>
  <script type="text/javascript">
  	function func(){
 		var pwd1 = document.getElementById("pwd1").value;
		var pwd2 = document.getElementById("pwd2").value;
		if(pwd1!=pwd2) {
			layer.alert("密码不一致");
			return;
		}
 		PwdmodifyForm.submit();
  	}
  </script>
</head>
<body>
	<div style="min-width:500px;max-width:800px;min-height:300px;border: 1px gray solid;margin: 10px;padding: 20px; box-shadow: 10px 10px  10px rgba(0, 0, 0, 0.6);float:left">
		<h3 style="color: gray;">修改密码</h3>
		<hr />
		<form class="layui-form" name="PwdmodifyForm" action="user/modifyPass">
		  <div class="layui-form-item">
			<label class="layui-form-label">原密码</label>
			<div class="layui-input-inline">
			  <input type="password" name="mpf.oldPassword" lay-verify="required" placeholder="请输入原密码" class="layui-input">
			</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
			  <input type="password" id="pwd1" name="mpf.newPassword" lay-verify="required" placeholder="请输入新密码" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
			  <input type="password" id="pwd2" name="mpf.surePassword" lay-verify="required" placeholder="请确认密码" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
		  </div>
			<div class="layui-form-item">
			  <div class="layui-input-block">
				<input type="button" class="layui-btn" onclick="func()" value="立即修改">
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			  </div>
			</div>
		</form>
		<script src="layui-master/dist/layui.js" charset="utf-8"></script>
		<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
	</div>
</body>
</html>