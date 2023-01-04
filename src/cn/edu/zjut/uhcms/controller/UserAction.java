package cn.edu.zjut.uhcms.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.pojo.ModifyPassForm;
import cn.edu.zjut.uhcms.pojo.PersonalPunchForm;
import cn.edu.zjut.uhcms.service.UserService;

public class UserAction extends ActionSupport{
	
	public UserAction() {
		System.out.println("<UserAction>"); 
	}
	
	/*
	 * 用户登录功能: user/login
	 */
	
	private User user;
	

	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public String login() {
		UserService serv = new UserService();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if(serv.login(user, session)) {
			user.setPassword("");
			session.put("user", user);
			return "success";
		}
		session.put("tips", "login failed: invailid username or password.");
		return "fail";
	}
	
	
	/*
	 * 用户修改密码功能: user/modifyPass
	 */
	
	
	private ModifyPassForm mpf;
	
	public ModifyPassForm getMpf() {
		return mpf;
	}
	
	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String modifyPass() throws UnsupportedEncodingException {
		UserService serv = new UserService();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if (session.get("user")==null)
		{
			session.put("tips","请先登录" );
			return "error";
		}
		if(mpf == null) {
			inputStream = new ByteArrayInputStream("修改失败：参数错误".getBytes("UTF-8"));
		}
		String oldPass = mpf.getOldPassword();
		String newPass = mpf.getNewPassword();
		User user = (User)session.get("user");
		String username = user.getUsername();
		String role = user.getRole(); 
		if(!serv.validate(username, oldPass, role)) {
			inputStream = new ByteArrayInputStream("原密码验证失败".getBytes("UTF-8"));
			return "success";
		}
		if(serv.modifyPass(username, newPass, role)) {
			user.setPassword("");
			inputStream = new ByteArrayInputStream("修改成功".getBytes("UTF-8"));
			return "success";
		}
		inputStream = new ByteArrayInputStream("修改失败".getBytes("UTF-8"));
		return "success";
	}

	public void setMpf(ModifyPassForm mpf) {
		this.mpf = mpf;
	}
}
