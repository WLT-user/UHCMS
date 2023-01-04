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
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.StudentRecord;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.TeacherRecord;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.pojo.PersonalPunchForm;
import cn.edu.zjut.uhcms.service.RecordService;
import cn.edu.zjut.uhcms.service.StudentService;
import cn.edu.zjut.uhcms.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RecordAction extends ActionSupport {
	
	
	
	public RecordAction() {
		System.out.println("<RecordAction>");
	}

	/*
	 * 打卡: record/punch
	 */
	private PersonalPunchForm ppf;
	
	public PersonalPunchForm getPpf() {
		return ppf;
	}

	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String punch() throws UnsupportedEncodingException {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if (!session.containsKey("personalRole"))
		{
			session.put("tips","非法访问" );
			return "error";
		}
		String role = (String) session.get("personalRole");
		RecordService serv = new RecordService();
		Object arg = session.get("personalInfo");
		if(serv.punch(ppf, role, arg)) 
			inputStream = new ByteArrayInputStream("打卡成功".getBytes("UTF-8"));
		else
			inputStream = new ByteArrayInputStream("打卡失败，请检查信息是否正确".getBytes("UTF-8"));
		return "success";
	}
	
	/*
	 * 查询教师打卡信息: record/queryTeacherRecordInfo
	 */
	
	private String name = "";
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String queryTeacherRecordInfo() {
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
			List<TeacherRecord> recordList = new RecordService().getTeacherRecord(name, college);
			if(recordList == null) {
				session.put("tips", "查询失败");
				return "fail";
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 0);
			result.put("msg", "");
			result.put("count", recordList.size());
			JSONArray array = JSONArray.fromObject(recordList);
			result.put("data", array);
			// 将其转换为JSON数据，并压入值栈返回
			ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
			return "success";
		}
		List<TeacherRecord> recordList = new RecordService().getTeacherRecord(name);
		if(recordList == null) {
			session.put("tips", "查询失败");
			System.out.println("fail");
			return "fail";
		}
		System.out.println("suc");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", recordList.size());
		JSONArray array = JSONArray.fromObject(recordList);
		result.put("data", array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}
	
	/*
	 * 查询学生打卡信息: record/queryStudentRecordInfo
	 */
	public String queryStudentRecordInfo() {
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
			List<StudentRecord> recordList = new RecordService().getStudentRecord(name, college);
			if(recordList == null) {
				session.put("tips", "查询失败");
				System.out.println("查询失败");
				return "fail";
			}
			System.out.println("查询成功");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("code", 0);
			result.put("msg", "");
			result.put("count", recordList.size());
			JSONArray array = JSONArray.fromObject(recordList);
			result.put("data", array);
			// 将其转换为JSON数据，并压入值栈返回
			ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
			return "success";
		}
		List<StudentRecord> recordList = new RecordService().getStudentRecord(name);
		if(recordList == null) {
			session.put("tips", "查询失败");
			System.out.println("查询失败");
			return "fail";
		}
		System.out.println("查询成功");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", recordList.size());
		JSONArray array = JSONArray.fromObject(recordList);
		result.put("data", array);
		// 将其转换为JSON数据，并压入值栈返回
		ActionContext.getContext().getValueStack().set("jsonData", JSONObject.fromObject(result));
		return "success";
	}

	public void setPpf(PersonalPunchForm ppf) {
		this.ppf = ppf;
	}
	
	
}
