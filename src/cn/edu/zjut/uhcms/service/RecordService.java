package cn.edu.zjut.uhcms.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.Base64;

import cn.edu.zjut.uhcms.dao.RecordDAO;
import cn.edu.zjut.uhcms.dao.StudentDAO;
import cn.edu.zjut.uhcms.dao.TeacherDAO;
import cn.edu.zjut.uhcms.model.Student;
import cn.edu.zjut.uhcms.model.StudentRecord;
import cn.edu.zjut.uhcms.model.Teacher;
import cn.edu.zjut.uhcms.model.TeacherRecord;
import cn.edu.zjut.uhcms.pojo.PersonalPunchForm;
import cn.edu.zjut.uhcms.utils.QRCodeGenerator;

import com.google.zxing.*;

public class RecordService {
	
	public String getHealthCodeBase64(String name, String state) throws IOException {
		int color = QRCodeGenerator.QR_COLOR_GREEN;
		if("黄".equals(state))
			color = QRCodeGenerator.QR_COLOR_YELLOW;
		else if("红".equals(state))
			color = QRCodeGenerator.QR_COLOR_RED;
        BufferedImage image = null;
		try {
			image = QRCodeGenerator.createImage(name + ", " + state + ", " + new Date().toString(), color);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        System.out.println("生成健康码base64串为:" + base64);
		return base64;
	}
	
	private static final String[] level = {"绿", "黄", "红"};
	
	private int getLevel(String state) {
		for(int i = 0; i < 3; ++i)
			if(level[i].equals(state))
				return i;
		return 2;
	}
	
	public boolean punch(PersonalPunchForm ppf, String role, Object arg) {
		int judge = 0;
		if("yes".equals(ppf.getCheck_1()) || "yes".equals(ppf.getCheck_2()) ||
				("no".equals(ppf.getCheck_5()) && !ppf.getCheck_5_extra().contains(",")))
			judge = 1;
		else if("yes".equals(ppf.getCheck_3()) || "yes".equals(ppf.getCheck_4()) ||
				("no".equals(ppf.getCheck_5()) && ppf.getCheck_5_extra().contains(",")))
			judge = 2;
		RecordDAO recordDAO = new RecordDAO();
		if("student".equals(role)) {
			System.out.println("学生打卡，评级：" + level[judge]);
			Student student = (Student)arg;
			boolean res = recordDAO.insertStudentRecord(student, level[judge]);
			String curState = student.getState();
			int curLevel = getLevel(curState);
			int newLevel = Math.max(curLevel, judge);
			System.out.println(curLevel + ", " + newLevel);
			if(newLevel != curLevel) {
				student.setState(level[newLevel]);
				new StudentDAO().modifyStudentByStudent(student);
			}
			if(judge == 0 && recordDAO.checkIfStudentColorChange(student.getSno(), student.getState())) {
				if("黄".equals(curState))
					student.setState("绿");
				else if("红".equals(curState))
					student.setState("黄");
				res |= new StudentDAO().modifyStudentByStudent(student);
			}
			return res;
		}
		else {
			System.out.println("教职工打卡，评级：" + level[judge]);
			Teacher teacher = (Teacher)arg;
			boolean res = recordDAO.insertTeacherRecord(teacher, level[judge]);
			String curState = teacher.getTstate();
			int curLevel = getLevel(curState);
			int newLevel = Math.max(curLevel, judge);
			System.out.println(curLevel + ", " + newLevel);
			if(newLevel != curLevel) {
				teacher.setTstate(level[newLevel]);
				new TeacherDAO().modifyTeacherByTeacher(teacher);
			}
			if(judge == 0 && recordDAO.checkIfTeacherColorChange(teacher.getTno(), teacher.getTstate())) {
				if("黄".equals(curState))
					teacher.setTstate("绿");
				else if("红".equals(curState))
					teacher.setTstate("黄");
				res |= new TeacherDAO().modifyTeacherByTeacher(teacher);
			}
			return res;
		}
	}

	public List<TeacherRecord> getTeacherRecord(String name, String college) {
		RecordDAO recordDAO = new RecordDAO();
		List<TeacherRecord> list = recordDAO.selectTeacherRecordByFuzzyTnameAndCollege(name, college);
		return list;
	}

	public List<TeacherRecord> getTeacherRecord(String name) {
		RecordDAO recordDAO = new RecordDAO();
		List<TeacherRecord> list = recordDAO.selectTeacherRecordByFuzzyTname(name);
		return list;
	}	
	
	public List<StudentRecord> getStudentRecord(String name, String college) {
		RecordDAO recordDAO = new RecordDAO();
		List<StudentRecord> list = recordDAO.selectStudentRecordByFuzzySnameAndCollege(name, college);
		return list;
	}	

	public List<StudentRecord> getStudentRecord(String name) {
		RecordDAO recordDAO = new RecordDAO();
		List<StudentRecord> list = recordDAO.selectStudentRecordByFuzzySname(name);
		return list;
	}


}
