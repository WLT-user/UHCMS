package cn.edu.zjut.uhcms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.service.TeacherService;
import cn.edu.zjut.uhcms.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TeacherAction extends ActionSupport {
	
	public TeacherAction() {
		System.out.println("<TeacherAction>");
	}
	
	/*
	 *  添加教师信息功能: teacher/add
	 */
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}
	
	public String add() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if (session.get("user") == null || new UserService().getPowerLevel((User)session.get("user")) > 0)
		{
			session.put("tips", "非法访问");
			return "error";
		}
		TeacherService serv = new TeacherService();
		if(serv.add(teacher)) {
			
			return "success";
		}
		return "fail";
	}
	
	
	/*
	 * 查询教师信息: teacher/queryTeacherInfo
	 */
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String queryTeacherInfo() {
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
			String college = teacher.getTcollege();
			List<Teacher> teacherList = new TeacherService().getTeacherList(name, college);
			if(teacherList == null) {
				session.put("tips", "查询失败");
				return "fail";
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 0);
			result.put("msg", "");
			result.put("count", teacherList.size());
			JSONArray array = JSONArray.fromObject(teacherList);
			result.put("data", array);
			// 将其转换为JSON数据，并压入值栈返回
			ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
			return "success";
		}
		List<Teacher> teacherList = new TeacherService().getTeacherList(name);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", teacherList.size());
		JSONArray array = JSONArray.fromObject(teacherList);
		result.put("data", array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	
}
