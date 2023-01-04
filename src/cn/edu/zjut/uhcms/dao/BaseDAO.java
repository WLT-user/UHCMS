package cn.edu.zjut.uhcms.dao;
import java.sql.*;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
public class BaseDAO {
	MysqlDataSource dataSource=new MysqlDataSource();
    public BaseDAO () {
        try {
            final String USER = "root";
            final String PASS = "Cx3x815x233";
        	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
        	final String DB_URL = "jdbc:mysql://localhost:3306/uhcmsdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            dataSource.setURL(DB_URL);
            dataSource.setUser(USER);//ʵ�����ݿ����ӣ�������dataSource��
            dataSource.setPassword(PASS);
        }catch(Exception ne){
            System.out.println("Exception:"+ne);
        }
    }
    public Connection getConnection()throws Exception{
        return dataSource.getConnection();
    }
	public static void main(String[] args) {
		BaseDAO b = new BaseDAO();
		try {
			b.getConnection();
			System.out.print(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
