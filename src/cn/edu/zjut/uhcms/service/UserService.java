package cn.edu.zjut.uhcms.service;
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.User;
import cn.edu.zjut.uhcms.pojo.PersonalPunchForm;
import net.sf.json.JSONObject;

import java.util.Map;

import cn.edu.zjut.uhcms.dao.RecordDAO;
import cn.edu.zjut.uhcms.dao.StudentDAO;
import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.dao.UserDAO;
public class UserService {
	
	public int getPowerLevel(User user) {
		String role = user.getRole();
		if("admin".equals(role))
			return 0;
		else if("school".equals(role))
			return 1;
		else if("college".equals(role))
			return 2;
		return 3;
	}
	
	public boolean login(User user, Map<String, Object> session) 
	{ 			
		String role = user.getRole();
		String username = user.getUsername();
		String pass = user.getPassword();
		UserDAO userdao = new UserDAO();
		String confpass = userdao.selectPasswordByUsernameAndRole(username, role);
		if("personal".equals(role)) {
			int len = username.length();
			if(len <= 6) {
				// 老师
				TeacherDAO teacherDAO = new TeacherDAO();
				Teacher teacher = teacherDAO.selectTeacherByTno(username);
				if(teacher == null) return false;
				session.put("personalRole", "teacher");
				session.put("personalInfo", teacher);
				String tid = teacher.getTid();
				if(confpass == null)
					confpass = tid.substring(tid.length() - 8);
			}
			else {
				// 学生
				StudentDAO studentDAO = new StudentDAO();
				Student student = studentDAO.selectStudentBySno(username);
				if(student == null) return false;
				session.put("personalRole", "student");
				session.put("personalInfo", student);
				String sid = student.getId();
				if(confpass == null)
					confpass = sid.substring(sid.length() - 8);
			}
		}
		if(pass.equals(confpass))
			return true;
		else
			return false;
	}
	
	public void modifyInfo() 
	{} 

	
	
	public boolean modifyPass(String username, String password, String role) 
	{
		UserDAO userdao=new UserDAO();
		System.out.println("将用户 " + username + " 的密码修改为 " + password);
		String oldPass = userdao.selectPasswordByUsername(username);
		if(oldPass == null) {
			return userdao.insertUserInfo(username, password, role);
		}
		if(userdao.updatePasswordByUsername(username,password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean validate(String username, String pass, String role) {
		UserDAO userdao = new UserDAO();
		String confpass = userdao.selectPasswordByUsernameAndRole(username, role);
		if("personal".equals(role)) {
			int len = username.length();
			if(len <= 6) {
				// 老师
				TeacherDAO teacherDAO = new TeacherDAO();
				Teacher teacher = teacherDAO.selectTeacherByTno(username);
				if(teacher == null) return false;
				String tid = teacher.getTid();
				if(confpass == null)
					confpass = tid.substring(tid.length() - 8);
			}
			else {
				// 学生
				StudentDAO studentDAO = new StudentDAO();
				Student student = studentDAO.selectStudentBySno(username);
				if(student == null) return false;
				String sid = student.getId();
				if(confpass == null)
					confpass = sid.substring(sid.length() - 8);
			}
		}
		if(pass.equals(confpass))
			return true;
		else
			return false;
	} 
}
