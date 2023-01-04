package cn.edu.zjut.uhcms.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zjut.uhcms.model.College;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.service.CollegeService;
import cn.edu.zjut.uhcms.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CollegeAction extends ActionSupport {
	
	public CollegeAction() {
		System.out.println("<CollegeAction>");
	}

	private College college;

	public College getCollege() {
		return college;
	}
	
	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	public String add() throws UnsupportedEncodingException {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if(session.get("user") == null )
			return "error";
		User user = (User) session.get("user");
		int level = new UserService().getPowerLevel(user);
		if (level > 0)
		{
			session.put("tips", "非法访问");
			return "error";
		}
		CollegeService serv = new CollegeService();
		if(serv.add(college)) 
			inputStream = new ByteArrayInputStream("添加学院信息成功".getBytes("UTF-8"));
		else
			inputStream = new ByteArrayInputStream("添加学院信息失败，请检查信息是否正确".getBytes("UTF-8"));
		return "success";
	}
	
	private String cname;
	
	
	
	public String getCname() {
		return cname;
	}
	
	
	
	public void setCollege(College college) {
		this.college = college;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String queryCollegeInfo()
	{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if(session.get("user") == null )
			return "error";
		User user = (User) session.get("user");
		int level = new UserService().getPowerLevel(user);
		if (level > 2)
		{
			session.put("tips", "非法访问");
			return "error";
		}
		CollegeService collegeserv = new CollegeService();
		List<College> allCollege = collegeserv.queryCollegeInfo(cname);
		if(allCollege==null)
		{
			session.put("tips", "查询失败");
			System.out.println("+");
			return "fail";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", allCollege.size());
		JSONArray array = JSONArray.fromObject(allCollege);
		result.put("data", array);
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
			
	}
}
