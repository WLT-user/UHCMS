package cn.edu.zjut.uhcms.service;

import java.util.List;

import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Teacher;

public class TeacherService {
	public boolean add(Teacher teacher) 
	{
		TeacherDAO teacherdao=new TeacherDAO();
		if(teacherdao.insertTeacher(teacher))
		{
		return true;
		}
		else
		{
		return false;	
		}
	}

	public List<Teacher> getTeacherList(String name) {
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> list = teacherDAO.selectByFuzzyTname(name);
		return list;
	}

	public List<Teacher> getTeacherList(String name, String college) {
		TeacherDAO teacherDAO = new TeacherDAO();
		List<Teacher> list = teacherDAO.selectByFuzzyTcollegeTname(college, name);
		return list;
	}
}
