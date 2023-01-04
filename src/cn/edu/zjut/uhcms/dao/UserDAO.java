package cn.edu.zjut.uhcms.dao;

import java.sql.*;

import cn.edu.zjut.uhcms.model.User;
public class UserDAO extends BaseDAO {
public String selectPasswordByUsernameAndRole(String username,String role) {
	String password;
	String sql = "SELECT * FROM uhcms_user " +
            "WHERE username=? and role=?";
	try {
	       Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1,username);
           pstmt.setString(2,role);
           ResultSet rst = pstmt.executeQuery();
          if(!rst.next()) return null;
          else {password=rst.getString("password");}
       return password;}
	catch(SQLException se) {
		se.printStackTrace();
        return null;
	}
}

public String selectPasswordByUsername(String username) {
	String password;
	String sql = "SELECT * FROM uhcms_user " +
            "WHERE username=? ";
	try {
	       Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1,username);
           ResultSet rst = pstmt.executeQuery();
          if(!rst.next()) return null;
          else {password=rst.getString("password");}
       return password;}
	catch(SQLException se) {
		se.printStackTrace();
        return null;
	}
}
public boolean updatePasswordByUsername(String username,String password) {
	String sql = "update  uhcms_user set password=? where username=?";
	try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setString(1,password);
           pstmt.setString(2,username);
           pstmt.executeUpdate();
           return true;
       }catch (SQLException se){
           se.printStackTrace();
           return false;
       }
}
	public boolean insertUserInfo(String username, String password, String role) {
		if(role == null) return false;
		String sql = "INSERT INTO uhcms_user VALUES(?,?,?)";
		try (Connection conn = dataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql)){
	           pstmt.setString(1,username);
	           pstmt.setString(2,password);
	           pstmt.setString(3, role);
	           pstmt.executeUpdate();
	           return true;
	       }catch (SQLException se){
	           se.printStackTrace();
	           return false;
	       }
	}
}
