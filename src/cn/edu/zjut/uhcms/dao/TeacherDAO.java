package cn.edu.zjut.uhcms.dao;

import java.sql.*;
import java.util.ArrayList;

import cn.edu.zjut.uhcms.model.Teacher;
public class TeacherDAO extends BaseDAO {

	//添加教师
    public boolean insertTeacher(Teacher teacher){
        String sql = "INSERT INTO uhcms_teacher(tno,tname,tsex,tid,tnumber,tcollege,tstate)VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1,teacher.getTno());
             pstmt.setString(2,teacher.getTname());
             pstmt.setString(3,teacher.getTsex());
             pstmt.setString(4,teacher.getTid());
             pstmt.setString(5,teacher.getTnumber());
             pstmt.setString(6,teacher.getTcollege());
             pstmt.setString(7,teacher.getTstate());
             pstmt.executeUpdate();
             return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
  //根据姓名模糊查询
    public ArrayList<Teacher> selectByFuzzyTname(String tname){
        ArrayList<Teacher> teaList = new ArrayList<Teacher>();
        String sql = "SELECT * FROM uhcms_teacher WHERE tname like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"%" + tname + "%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Teacher teacher = new Teacher();
                    teacher.setTno(rst.getString("tno"));
                    teacher.setTname(rst.getString("tname"));
                    teacher.setTid(rst.getString("tid"));
                    teacher.setTcollege(rst.getString("tcollege"));
                    teacher.setTsex(rst.getString("tsex"));
                    teacher.setTnumber(rst.getString("tnumber"));
                    teacher.setTstate(rst.getString("tstate"));
                    teaList.add(teacher);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return teaList;
    }
    
  //根据学院和姓名模糊查询教师
    public ArrayList<Teacher> selectByFuzzyTcollegeTname(String college,String tname){
        ArrayList<Teacher> teaList = new ArrayList<Teacher>();
        String sql = "SELECT uhcms_teacher.*  FROM uhcms_college,uhcms_teacher " +
                "WHERE uhcms_teacher.tcollege=uhcms_college.cno and tcollege=? and tname like ? ";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,college);
            pstmt.setString(2,"%"+tname+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Teacher teacher = new Teacher();
                    teacher.setTno(rst.getString("tno"));
                    teacher.setTname(rst.getString("tname"));
                    teacher.setTid(rst.getString("tid"));
                    teacher.setTcollege(rst.getString("tcollege"));
                    teacher.setTsex(rst.getString("tsex"));
                    teacher.setTnumber(rst.getString("tnumber"));
                    teacher.setTstate(rst.getString("tstate"));
                    teaList.add(teacher);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return teaList;
    }
    
  //查找所有教师
    public ArrayList<Teacher> selectAllTeacher(){
        ArrayList<Teacher> teaList = new ArrayList<Teacher>();
        String sql = "SELECT * FROM uhcms_teacher";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Teacher teacher = new Teacher();
                teacher.setTno(rst.getString("tno"));
                teacher.setTname(rst.getString("tname"));
                teacher.setTid(rst.getString("tid"));
                teacher.setTcollege(rst.getString("tcollege"));
                teacher.setTsex(rst.getString("tsex"));
                teacher.setTnumber(rst.getString("tnumber"));
                teacher.setTstate(rst.getString("tstate"));
                teaList.add(teacher);
            }
            return teaList;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
    
    //根据学院查找教师
    public ArrayList<Teacher> selectCollegeTeacherByTcollege(String number){
        String sql = "SELECT uhcms_teacher.* FROM uhcms_college,uhcms_teacher " +
                "WHERE uhcms_teacher.tcollege=uhcms_college.cno and tcollege=? ";
        ArrayList<Teacher> teaList = new ArrayList<Teacher>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Teacher teacher = new Teacher();
                    teacher.setTno(rst.getString("tno"));
                    teacher.setTname(rst.getString("tname"));
                    teacher.setTid(rst.getString("tid"));
                    teacher.setTcollege(rst.getString("tcollege"));
                    teacher.setTsex(rst.getString("tsex"));
                    teacher.setTnumber(rst.getString("tnumber"));
                    teacher.setTstate(rst.getString("tstate"));
                    teaList.add(teacher);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return teaList;
    }
    
    
    //根据工号查找教师
    public Teacher selectTeacherByTno(String number){
        String sql = "SELECT * FROM uhcms_teacher WHERE tno=?";
        Teacher teacher = new Teacher();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                if(rst.next()){
                	 teacher.setTno(rst.getString("tno"));
                     teacher.setTname(rst.getString("tname"));
                     teacher.setTid(rst.getString("tid"));
                     teacher.setTcollege(rst.getString("tcollege"));
                     teacher.setTsex(rst.getString("tsex"));
                     teacher.setTnumber(rst.getString("tnumber"));
                     teacher.setTstate(rst.getString("tstate"));
                }
                else return null;
            }
        } catch (SQLException se) {
            return null;
        }
        return teacher;
    }
    
    //修改教师信息
    public boolean modifyTeacherByTeacher(Teacher teacher){
        String sql = "update uhcms_teacher set tno=?,tname=?,tsex=?,tid=?,tnumber=?,tcollege=?,tstate=? where tno=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,teacher.getTno());
            pstmt.setString(2,teacher.getTname());
            pstmt.setString(3,teacher.getTsex());
            pstmt.setString(4,teacher.getTid());
            pstmt.setString(5,teacher.getTnumber());
            pstmt.setString(6,teacher.getTcollege());
            pstmt.setString(7,teacher.getTstate());
            pstmt.setString(8,teacher.getTno());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
  //删除教师
    public boolean deleteTeacherByTno(String number){
        String sql = "delete from uhcms_teacher WHERE tno=?";
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
