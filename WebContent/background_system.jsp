<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.edu.zjut.uhcms.model.User"%>
<%! User user = null; %>
<%if(request.getSession().getAttribute("user")==null){
	 session.setAttribute("tips","请先登录。。。");
	 response.sendRedirect("login.jsp");
	 return;
} else {
	user =  (User)request.getSession().getAttribute("user");
}
%>
<%!String tips = null; %>    
<%if((tips = (String)session.getAttribute("tips")) != null) {%>
	<script>
		layer.alert("<%=tips%>");
	</script>
<%session.setAttribute("tips", null);
}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="layui-master/dist/css/layui.css" media="all">
		 <script src="jquery/jquery-3.6.0.min.js"></script>
		 <script>
			function change(title){
				$('iframe').attr('src',title);
			}
		 </script>
		<title></title>
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
		  <div class="layui-header">
			<div class="layui-logo layui-hide-xs layui-bg-black">师生健康码管理系统</div>
			<!-- 头部区域（可配合layui 已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
			  <!-- 移动端显示 -->
			  <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
				<i class="layui-icon layui-icon-spread-left"></i>
			  </li>
			</ul>
			<ul class="layui-nav layui-layout-right">
			  <li class="layui-nav-item layui-hide layui-show-md-inline-block">
				<a href="javascript:;">
				  <img src="resource/2.jpeg" class="layui-nav-img">
				  <%=user.getUsername() %>
				</a>
				<dl class="layui-nav-child">
				  <dd><a href="javascript:;" onclick="change('passwordModify.jsp')">修改密码</a></dd>
				  <dd><a href="login.jsp">退出登录</a></dd>
				</dl>
			  </li>
			  <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
				<a href="javascript:;">
				  <i class="layui-icon layui-icon-more-vertical"></i>
				</a>
			  </li>
			</ul>
		  </div>
		  
		  <div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
			  <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			  <ul class="layui-nav layui-nav-tree" lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed">
				  <a class="" href="javascript:;" onclick="change('welcome.html')">系统公告</a>
				</li>
				
				<%if("personal".equals(user.getRole())){%>
					<li class="layui-nav-item">
						  <a href="javascript:;">个人信息管理</a>
						  <dl class="layui-nav-child">
							<dd><a href="javascript:;" onclick="change('personal_info_query.jsp')">查询个人信息</a></dd>
							<dd><a href="javascript:;" onclick="change('personal_info_modify.jsp')">修改个人信息</a></dd>
						  </dl>
					</li>
					<li class="layui-nav-item">
						  <a href="javascript:;">健康码管理</a>
						  <dl class="layui-nav-child">
							<dd><a href="javascript:;" onclick="change('personal_punch.jsp')">立即打卡</a></dd>
							<dd><a href="javascript:;"  onclick="change('health_code.jsp')">打卡情况</a></dd>
						  </dl>
					</li>
				<%} %>
				
				<%if(user.getRole().equals("school")){ %>
					<li class="layui-nav-item">
					  <a href="javascript:;">信息管理</a>
					  <dl class="layui-nav-child">
					  	<dd><a href="javascript:;" onclick="change('college_info_query.html')">学院信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('major_info_query.html')">专业信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('teacher_info_query.html')">教师信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_info_query.html')">学生信息查询</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">健康码管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('teacher_punch_query.html')">教师打卡情况查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_punch_query.html')">学生打卡情况查询</a></dd>
					  </dl>
					</li>
				<%} %>
				
				<%if(user.getRole().equals("college")){ %>
					<li class="layui-nav-item">
					  <a href="javascript:;">信息管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('major_info_query.html')">专业信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('teacher_info_query.html')">教师信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_info_query.html')">学生信息查询</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">健康码管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('teacher_punch_query.html')">教师打卡情况查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_punch_query.html')">学生打卡情况查询</a></dd>
					  </dl>
					</li>
				<%} %>
				
				<%if(user.getRole().equals("admin")){ %>
					<li class="layui-nav-item">
					  <a href="javascript:;">学院管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('college_info_query.html')">学院信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('college_info_add.jsp')">新增学院信息</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">专业管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('major_info_query.html')">专业信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('major_info_add.jsp')">新增专业信息</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">教师管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('teacher_info_query.html')">教师信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('teacher_info_add.jsp')">新增教师信息</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">学生管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('student_info_query.html')">学生信息查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_info_add.jsp')">新增学生信息</a></dd>
					  </dl>
					</li>
					<li class="layui-nav-item">
					  <a href="javascript:;">健康码管理</a>
					  <dl class="layui-nav-child">
						<dd><a href="javascript:;" onclick="change('teacher_punch_query.html')">教师打卡情况查询</a></dd>
						<dd><a href="javascript:;" onclick="change('student_punch_query.html')">学生打卡情况查询</a></dd>
					  </dl>
					</li>
				<%} %>
			  </ul>
			</div>
		  </div>
		  
		  <div class="layui-body">
			<!-- 内容主体区域 -->
			<div>
				<iframe src="welcome.html" id="demoAdmin" style="width: 100%; height: 700px; border-radius: 2px;" frameborder="0"></iframe>     
			</div>
		  </div>
		  
		  <div class="layui-footer">
			<!-- 底部固定区域 -->
			底部固定区域
		  </div>
		</div>
		<script src="./layui-master/dist/layui.js"></script>
		<script>
		//JS 
		layui.use(['element', 'layer', 'util'], function(){
		  var element = layui.element
		  ,layer = layui.layer
		  ,util = layui.util
		  ,$ = layui.$;
		  
		  //头部事件
		  util.event('lay-header-event', {
			//左侧菜单事件
			menuLeft: function(othis){
			  layer.msg('展开左侧菜单的操作', {icon: 0});
			}
			,menuRight: function(){
			  layer.open({
				type: 1
				,content: '<div style="padding: 15px;">暂无更多信息</div>'
				,area: ['260px', '100%']
				,offset: 'rt' //右上角
				,anim: 5
				,shadeClose: true
			  });
			}
		  });
		  
		});
		</script>
	</body>
</html>