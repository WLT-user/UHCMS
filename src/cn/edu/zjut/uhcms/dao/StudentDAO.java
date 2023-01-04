package cn.edu.zjut.uhcms.dao;

import java.sql.*;
import java.util.ArrayList;

import cn.edu.zjut.uhcms.model.Student;
public class StudentDAO extends BaseDAO {
 
	//添加学生
    public boolean insertStudent(Student student){
        String sql = "INSERT INTO uhcms_student(sno,sname,ssex,sid,snumber,scollege,smajor,sstate)VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,student.getSno());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setString(4,student.getId());
            pstmt.setString(5,student.getNumber());
            pstmt.setString(6,student.getCollege());
            pstmt.setString(7,student.getMajor());
            pstmt.setString(8,student.getState());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
  //按照名字模糊查询
    public ArrayList<Student> selectByFuzzySname(String sname){
        ArrayList<Student> stuList = new ArrayList<Student>();
        String sql = "SELECT * FROM uhcms_student WHERE sname like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"%"+sname+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Student student = new Student();
                    student.setSno(rst.getString("sno"));
                    student.setName(rst.getString("sname"));
                    student.setSex(rst.getString("ssex"));
                    student.setId(rst.getString("sid"));
                    student.setNumber(rst.getString("snumber"));
                    student.setCollege(rst.getString("scollege"));
                    student.setMajor(rst.getString("smajor"));
                    student.setState(rst.getString("sstate"));
                    stuList.add(student);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return stuList;
    }
    
    //按照名字模糊查询某一学院的学生信息
    public ArrayList<Student> selectByFuzzySnameAndCollege(String sname, String college){
        ArrayList<Student> stuList = new ArrayList<Student>();
        String sql = "SELECT * FROM uhcms_student WHERE sno like ? and scollege=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"%"+sname+"%");
            pstmt.setString(2, college);
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Student student = new Student();
                    student.setSno(rst.getString("sno"));
                    student.setName(rst.getString("sname"));
                    student.setSex(rst.getString("ssex"));
                    student.setId(rst.getString("sid"));
                    student.setNumber(rst.getString("snumber"));
                    student.setCollege(rst.getString("scollege"));
                    student.setMajor(rst.getString("smajor"));
                    student.setState(rst.getString("sstate"));
                    stuList.add(student);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return stuList;
    }
    
    
  //查询与教师号同学院的按学号进行模糊查询的学生
    public ArrayList<Student> selectByFuzzyTnoSno(String number1, String number2){
        ArrayList<Student> stuList = new ArrayList<Student>();
        String sql = "SELECT uhcms_student.* FROM uhcms_teacher,uhcms_student,uhcms_college " +
                "WHERE uhcms_college.cno=uhcms_teacher.tcollege and uhcms_student.scollege=uhcms_college.cno " +
                "and tno=? and sno like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number1);
            pstmt.setString(2,"%"+number2+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Student student = new Student();
                    student.setSno(rst.getString("sno"));
                    student.setName(rst.getString("sname"));
                    student.setSex(rst.getString("ssex"));
                    student.setId(rst.getString("sid"));
                    student.setNumber(rst.getString("snumber"));
                    student.setCollege(rst.getString("scollege"));
                    student.setMajor(rst.getString("smajor"));
                    student.setState(rst.getString("sstate"));
                    stuList.add(student);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return stuList;
    }
    
  //查询所有学生
    public ArrayList<Student> selectAllStudent(){
        ArrayList<Student> stuList = new ArrayList<Student>();
        String sql = "SELECT * FROM uhcms_student";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Student student = new Student();
                student.setSno(rst.getString("sno"));
                student.setName(rst.getString("sname"));
                student.setSex(rst.getString("ssex"));
                student.setId(rst.getString("sid"));
                student.setNumber(rst.getString("snumber"));
                student.setCollege(rst.getString("scollege"));
                student.setMajor(rst.getString("smajor"));
                student.setState(rst.getString("sstate"));
                stuList.add(student);
            }
            return stuList;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
    
  //查询与教师工号同学院的老师
    public ArrayList<Student> selectCollegestudentByTno(String number){
        String sql = "SELECT uhcms_student.* FROM uhcms_college,uhcms_teacher,uhcms_student " +
                "WHERE uhcms_college.cno=uhcms_teacher.tcollege and uhcms_student.scollege=uhcms_college.cno and tno=?";
        ArrayList<Student> stuList = new ArrayList<Student>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Student student = new Student();
                    student.setSno(rst.getString("sno"));
                    student.setName(rst.getString("sname"));
                    student.setSex(rst.getString("ssex"));
                    student.setId(rst.getString("sid"));
                    student.setNumber(rst.getString("snumber"));
                    student.setCollege(rst.getString("scollege"));
                    student.setMajor(rst.getString("smajor"));
                    student.setState(rst.getString("sstate"));
                    stuList.add(student);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return stuList;
    }
    
  //根据学号查找学生
    public Student selectStudentBySno(String number){
        String sql = "SELECT * FROM uhcms_student WHERE sno=?";
        Student student = new Student();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                if(rst.next()){
                	student.setSno(rst.getString("sno"));
                    student.setName(rst.getString("sname"));
                    student.setSex(rst.getString("ssex"));
                    student.setId(rst.getString("sid"));
                    student.setNumber(rst.getString("snumber"));
                    student.setCollege(rst.getString("scollege"));
                    student.setMajor(rst.getString("smajor"));
                    student.setState(rst.getString("sstate"));
                }
                else return null;
            }
        } catch (SQLException se) {
            return null;
        }
        return student;
    }
    
  //修改学生信息
    public boolean modifyStudentByStudent(Student student){
        String sql = "update uhcms_student set sno=?,sname=?,ssex=?,sid=?,snumber=?,scollege=?,smajor=?,sstate=? where sno=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,student.getSno());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setString(4,student.getId());
            pstmt.setString(5,student.getNumber());
            pstmt.setString(6,student.getCollege());
            pstmt.setString(7,student.getMajor());
            pstmt.setString(8,student.getState());
            pstmt.setString(9,student.getSno());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
  //删除学生
    public boolean deleteStudentBySno(String number){
        String sql = "delete from uhcms_student WHERE sno=?";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,number);
            if(pstmt.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException se){
            return false;
        }
        return false;
    }

}
