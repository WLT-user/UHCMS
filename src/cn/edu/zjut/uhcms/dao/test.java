package cn.edu.zjut.uhcms.dao;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import cn.edu.zjut.uhcms.model.*;
public class test {

	public static void main(String[] args) {
     RecordDAO r=new RecordDAO();
     Student student=new Student();
     ArrayList<StudentRecord> teaList = new ArrayList<StudentRecord>();
     teaList=(ArrayList<StudentRecord>) r.selectStudentRecordByFuzzySnameAndCollege("王", "C06");
     for(int i=0;i<teaList.size();i++) {
    	 student=teaList.get(i).getStudent();
    	 System.out.println(teaList.get(i).getSno());
    	 System.out.println(student.getName()+" "+student.getSex()+" "+student.getId());
     }
}
}
