package cn.edu.zjut.uhcms.dao;
import java.sql.*;
import java.util.ArrayList;

import cn.edu.zjut.uhcms.model.College;
public class CollegeDAO extends BaseDAO{
	//添加学院
    public boolean insertCollege(College college){
        String sql = "INSERT INTO uhcms_college(cno,cname)VALUES(?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,college.getCno());
            pstmt.setString(2,college.getCname());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
  //根据名称模糊查询
    public ArrayList<College> SelectByFuzzyName(String name){
        ArrayList<College> colList = new ArrayList<College>();
        String sql = "SELECT cno,cname FROM uhcms_college WHERE cname like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"%"+name+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    College college = new College();
                    college.setCno(rst.getString("cno"));
                    college.setCname(rst.getString("cname"));
                    colList.add(college);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return colList;
    }
    
  //查找所有学院
    public ArrayList<College> selectAllCollege(){
        ArrayList<College> colList = new ArrayList<College>();
        String sql = "SELECT * FROM uhcms_college";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                College college = new College();
                college.setCno(rst.getString("cno"));
                college.setCname(rst.getString("cname"));
                colList.add(college);
            }
            return colList;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
    
  //根据教师工号查找学院
    public College selectCollegeByTno(String number){
        String sql = "SELECT cno,cname FROM uhcms_college,uhcms_teacher " +
                "WHERE uhcms_college.cno=uhcms_teacher.tcollege and tno=?";
        College college = new College();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                if(rst.next()){
                    college.setCno(rst.getString("cno"));
                    college.setCname(rst.getString("cname"));
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return college;
    }
    
  //根据学院编号修改学院信息
    public boolean modifyCollegeByCollege(College college){
        String sql = "update uhcms_college set cname=? where cno=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,college.getCname());
            pstmt.setString(2,college.getCno());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
    //根据学院编号删除学院信息
    public boolean deleteCollegeByCno(String number){
        String sql = "delete from uhcms_college WHERE cno=?";
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
