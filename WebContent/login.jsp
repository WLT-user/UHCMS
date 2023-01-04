<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%!String tips = null; %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<script src="layui-master/dist/layui.js" charset="utf-8"></script>
		<script src="layer.layer.js"></script>
		<link rel="stylesheet" href="layui-master/dist/css/layui.css">
		
		<style type="text/css">
			body{
				background-image: url(resource/bg.jpg); no-repeat; background-size: 100%;
			}
			.container{
				width: 500px; margin: 100px auto 0;border-radius: 8px;
				background-color: #FFFFFF;box-shadow: 0 3px 18px rgba(100, 0, 0);padding: 20px;
				
			}
			.logo{
				width: 140px;padding-left: 160px;padding-bottom: 20px;
			}
			.layui-input{
				 width: 320px;
			}
			.layui-btn-fluid{
				
			}
			.btn_submit{
				width: 365px;
				margin: 0px auto 10px;
			}
			.font-set{
				font-size: 13px;
				text-decoration: none; 
				margin-left: 120px;
			}
		</style>
		<title></title>
	</head>
	<body>
	<%if((tips = (String)request.getSession().getAttribute("tips")) != null) {%>
 		<script>
 			layer.alert("<%=tips%>");
 		</script>
	<%session.setAttribute("tips", null);
	}%>
		<div class="container">
			<img src="resource/login.png" class="logo"/>
			<form class="layui-form" action="user/login">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<input type="text" name="user.username" required  lay-verify="required" placeholder="USERNAME" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input type="password" name="user.password" required lay-verify="required" placeholder="PASSWORD" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
				    <label class="layui-form-label">身份</label>
				    <div class="layui-input-block">
				      <input type="radio" name="user.role" value="admin" title="管理员">
				      <input type="radio" name="user.role" value="school" title="校级">
				      <input type="radio" name="user.role" value="college" title="院级">
					  <input type="radio" name="user.role" value="personal" title="师生" checked>
				    </div>
				</div>
				<div class="btn_submit">
					<input type="submit" value="立即登录" class="layui-btn layui-btn-fluid">
				</div>
				<div>
					<a href="" class="font-set">忘记密码?</a>  
					<a href="" class="font-set">立即注册</a>
				</div>
			</form>
		</div>
	</body>
</html>