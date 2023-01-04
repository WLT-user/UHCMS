package cn.edu.zjut.uhcms.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.zjut.uhcms.dao.StudentDAO;
import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.service.CollegeService;
import cn.edu.zjut.uhcms.service.StudentService;
import cn.edu.zjut.uhcms.service.StudentService;
import cn.edu.zjut.uhcms.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentAction extends ActionSupport {

	public StudentAction() {
		System.out.println("<StudentAction>");
	}
	
	/*
	 *添加学生信息: student/add 
	 */

	private Student student;

	public Student getStudent() {
		return student;
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
		StudentService serv = new StudentService();
		if(serv.add(student)) 
			inputStream = new ByteArrayInputStream("添加学生信息成功".getBytes("UTF-8"));
		else
			inputStream = new ByteArrayInputStream("添加学生信息失败，请检查信息是否正确".getBytes("UTF-8"));
		return "success";
	}
	
	/*
	 * 查询学生信息: student/queryStudentInfo 
	 */
	private String name;
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public String queryStudentInfo() {
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
			List<Student> studentList = new StudentService().getStudentList(name, college);
			if(studentList == null) {
				session.put("tips", "查询失败");
				return "fail";
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 0);
			result.put("msg", "");
			result.put("count", studentList.size());
			JSONArray array = JSONArray.fromObject(studentList);
			result.put("data", array);
			// 将其转换为JSON数据，并压入值栈返回
			ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
			return "success";
		}
		System.out.println("?" + name + "?");
		List<Student> StudentList = new StudentService().getStudentList(name);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", StudentList.size());
		JSONArray array = JSONArray.fromObject(StudentList);
		result.put("data", array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
