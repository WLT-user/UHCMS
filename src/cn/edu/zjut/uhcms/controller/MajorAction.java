package cn.edu.zjut.uhcms.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Major;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.service.CollegeService;
import cn.edu.zjut.uhcms.service.MajorService;
import cn.edu.zjut.uhcms.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MajorAction extends ActionSupport {

	private Major major;

	public MajorAction() {
		System.out.println("<MajorAction>");
	}

	public Major getMajor() {
		return major;
	}
	
	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String add() throws UnsupportedEncodingException {
		int a = 0;
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
		MajorService serv = new MajorService();
		if(serv.add(major)) 
			inputStream = new ByteArrayInputStream("添加专业信息成功".getBytes("UTF-8"));
		else
			inputStream = new ByteArrayInputStream("添加专业信息失败，请检查信息是否正确".getBytes("UTF-8"));
		return "success";
	}
	
	private String mname;
	
	
	
	public void setMajor(Major major) {
		this.major = major;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMname() {
		return mname;
	}

	public String queryMajorInfo()
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
		else if(level == 2) {
			Teacher teacher = new TeacherDAO().selectTeacherByTno(user.getUsername());
			String tno = teacher.getTno();
			MajorService majorserv = new MajorService();
			List<Major> allMajor = majorserv.queryMajorInfo(mname, tno);
			if(allMajor==null)
			{
				session.put("tips", "查询失败");
				System.out.println("查询专业失败");
				return "fail";
			}
			System.out.println("查询专业成功");
			Map<String, Object> result = new HashMap<String, Object>();
			 result.put("code", 0);
			 result.put("msg", "");
			 result.put("count", allMajor.size());
			JSONArray array = JSONArray.fromObject(allMajor);
			result.put("data", array);
			ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
			return "success";
		}
		MajorService majorserv = new MajorService();
		List<Major> allMajor = majorserv.queryMajorInfo(mname);
		if(allMajor==null)
		{
			session.put("tips", "查询失败");
			System.out.println("查询专业失败");
			return "fail";
		}
		System.out.println("查询专业成功");
		Map<String, Object> result = new HashMap<String, Object>();
		 result.put("code", 0);
		 result.put("msg", "");
		 result.put("count", allMajor.size());
		JSONArray array = JSONArray.fromObject(allMajor);
		result.put("data", array);
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
			
	}
}

