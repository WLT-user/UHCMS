package cn.edu.zjut.uhcms.dao;

import java.sql.*;
import java.util.ArrayList;

import cn.edu.zjut.uhcms.model.Major;
public class MajorDAO extends BaseDAO {
	//添加专业
    public boolean insertMajor(Major major){
        String sql = "INSERT INTO uhcms_major(mno,mname,mcollege_no)VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,major.getMno());
            pstmt.setString(2,major.getMname());
            pstmt.setString(3,major.getMcollege_no());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
    //根据专业名模糊查询
    public ArrayList<Major> selectByFuzzyMname(String name){
        ArrayList<Major> majList = new ArrayList<Major>();
        String sql = "SELECT * FROM uhcms_major WHERE mname like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"%"+name+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Major major = new Major();
                    major.setMno(rst.getString("mno"));
                    major.setMname(rst.getString("mname"));
                    major.setMcollege_no(rst.getString("mcollege_no"));
                    majList.add(major);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return majList;
    }
    
    //根据教师工号用专业名模糊查询
    public ArrayList<Major> selectByFuzzyTnoMname(String number,String name){
        ArrayList<Major> majList = new ArrayList<Major>();
        String sql = "SELECT mno,mname,mcollege_no FROM uhcms_major,uhcms_college,uhcms_teacher " +
                "WHERE uhcms_teacher.tcollege=uhcms_college.cno and uhcms_college.cno=mcollege_no and tno=? and mname like ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            pstmt.setString(2,"%"+name+"%");
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Major major = new Major();
                    major.setMno(rst.getString("mno"));
                    major.setMname(rst.getString("mname"));
                    major.setMcollege_no(rst.getString("mcollege_no"));
                    majList.add(major);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return majList;
    }
    
  //查询所有专业
    public ArrayList<Major> selectAllMajor(){
        ArrayList<Major> majList = new ArrayList<Major>();
        String sql = "SELECT * FROM uhcms_major";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                Major major = new Major();
                major.setMno(rst.getString("mno"));
                major.setMname(rst.getString("mname"));
                major.setMcollege_no(rst.getString("mcollege_no"));
                majList.add(major);
            }
            return majList;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
    
  //按照教师工号查询所在学院专业
    public ArrayList<Major> selectMajorByTno(String number){
        String sql = "SELECT mno,mname,mcollege_no FROM uhcms_major,uhcms_college,uhcms_teacher " +
                "WHERE uhcms_college.cno=uhcms_teacher.tcollege and uhcms_college.cno=mcollege_no and tno=?";
        ArrayList<Major> majList = new ArrayList<Major>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,number);
            try (ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    Major major = new Major();
                    major.setMno(rst.getString("mno"));
                    major.setMname(rst.getString("mname"));
                    major.setMcollege_no(rst.getString("mcollege_no"));
                    majList.add(major);
                }
            }
        } catch (SQLException se) {
            return null;
        }
        return majList;
    }
    
    //修改专业信息
    public boolean modifyMajorByMajor(Major major){
        String sql = "update uhcms_major set mname=?,mcollege_no=? where mno=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,major.getMname());
            pstmt.setString(2,major.getMcollege_no());
            pstmt.setString(3,major.getMno());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
  //删除专业
    public boolean deleteMajorByMno(String number){
        String sql = "delete from uhcms_major WHERE mno=?";
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
