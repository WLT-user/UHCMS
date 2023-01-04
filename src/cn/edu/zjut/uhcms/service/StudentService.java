package cn.edu.zjut.uhcms.service;

import java.util.List;

import cn.edu.zjut.uhcms.dao.StudentDAO;
import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.Teacher;

public class StudentService {
	public boolean add(Student student) 
	{
		StudentDAO studentdao=new StudentDAO();
		if(studentdao.insertStudent(student))
		{
		return true;
		}
		else
		{
		return false;	
		}
	}

	public List<Student> getStudentList(String name, String college) {
		StudentDAO studentDAO = new StudentDAO();
		List<Student> list = studentDAO.selectByFuzzySnameAndCollege(name, college);
		return list;
	}

	public List<Student> getStudentList(String name) {
		StudentDAO studentDAO = new StudentDAO();
		List<Student> list = studentDAO.selectByFuzzySname(name);
		return list;
	}
}
