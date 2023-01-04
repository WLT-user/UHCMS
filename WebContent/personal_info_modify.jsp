<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>修改个人信息</title>
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
		<h3 style="color: gray;">修改个人信息</h3>
		<hr />
		<form class="layui-form" action="user/modifyInfo">
		  <div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-inline">
			  <input type="text" name="number" lay-verify="required"  placeholder="请输入手机号" class="layui-input">
			</div>
		  </div>
		   <div class="layui-form-item">
			  <div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			  </div>
			</div>
		</form>
	</div>
		<script src="layui-master/dist/layui.js" charset="utf-8"></script>
		<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
</body>
</html>