package cn.edu.zjut.uhcms.dao;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import cn.edu.zjut.uhcms.model.*;
import cn.edu.zjut.uhcms.dao.*;
public class RecordDAO extends BaseDAO{

	//通过学生记录类添加学生打卡信息
		public boolean insertStudentRecordBySR(StudentRecord student_record){
	        String sql = "INSERT INTO uhcms_student_record(rno,sno,snumber,judgement,current_state,rtime,rdate)VALUES(?,?,?,?,?,?,?)";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,student_record.getRno());
	            pstmt.setString(2,student_record.getSno());
	            pstmt.setString(3,student_record.getSnumber());
	            pstmt.setString(4,student_record.getJudgement());
	            pstmt.setString(5,student_record.getCurrent_state());
	            pstmt.setString(6,student_record.getRtime());
	            pstmt.setString(7,student_record.getRdate());
	            pstmt.executeUpdate();
	            return true;
	        }catch (SQLException se){
	            se.printStackTrace();
	            return false;
	        }
	    }
		
		//通过教师打卡记录类添加教师打卡信息
	    public boolean insertTeacherRecordByTR(TeacherRecord teacher_record){
	        String sql = "INSERT INTO uhcms_teacher_record(rno,tno,tnumber,judgement,current_state,rtime,rdate)VALUES(?,?,?,?,?,?,?)";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,teacher_record.getRno());
	            pstmt.setString(2,teacher_record.getTno());
	            pstmt.setString(3,teacher_record.getTnumber());
	            pstmt.setString(4,teacher_record.getJudgement());
	            pstmt.setString(5,teacher_record.getCurrent_state());
	            pstmt.setString(6,teacher_record.getRtime());
	            pstmt.setString(7,teacher_record.getRdate());
	            pstmt.executeUpdate();
	            return true;
	        }catch (SQLException se){
	            se.printStackTrace();
	            return false;
	        }
	    }
	    
	  //按照学号查询打卡记录，并按照时间降序排列
	    public ArrayList<StudentRecord> selectStudentRecordBySno(String number){
	        String sql = "SELECT * FROM uhcms_student_record " +
	                "WHERE sno=? order by rdate desc,rtime desc";
	        ArrayList<StudentRecord> stuList = new ArrayList<StudentRecord>();
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,number);
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    StudentRecord student_record = new StudentRecord();
	                    student_record.setRno(rst.getString("rno"));
	                    student_record.setSno(rst.getString("sno"));
	                    student_record.setSnumber(rst.getString("snumber"));
	                    student_record.setJudgement(rst.getString("judgement"));
	                    student_record.setCurrent_state(rst.getString("current_state"));
	                    student_record.setRtime(rst.getString("rtime"));
	                    student_record.setRdate(rst.getString("rdate"));
	                    student_record.setStudent(new StudentDAO().selectStudentBySno(rst.getString("sno")));
	                    stuList.add(student_record);
	                }
	            }
	        } catch (SQLException se) {
	            return null;
	        }
	        return stuList;
	    }
	    
	  //根据教师号查找打卡记录
	    public ArrayList<TeacherRecord> selectTeacherRecordByTno(String number){
	        String sql = "SELECT * FROM uhcms_teacher_record " +
	                "WHERE tno=? order by rdate desc,rtime desc";
	        ArrayList<TeacherRecord> teaList = new ArrayList<TeacherRecord>();
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,number);
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    TeacherRecord teacher_record = new TeacherRecord();
	                    teacher_record.setRno(rst.getString("rno"));
	                    teacher_record.setTno(rst.getString("tno"));
	                    teacher_record.setTnumber(rst.getString("tnumber"));
	                    teacher_record.setJudgement(rst.getString("judgement"));
	                    teacher_record.setCurrent_state(rst.getString("current_state"));
	                    teacher_record.setRtime(rst.getString("rtime"));
	                    teacher_record.setRdate(rst.getString("rdate"));
	                    teacher_record.setTeacher(new TeacherDAO().selectTeacherByTno(rst.getString("tno")));
	                    teaList.add(teacher_record);
	                }
	            }
	        } catch (SQLException se) {
	            return null;
	        }
	        return teaList;
	    }
	    
	  //查询所有学生打卡记录降序排列
	    public ArrayList<StudentRecord> selectAllStudentRecord(){
	        ArrayList<StudentRecord> stuList = new ArrayList<StudentRecord>();
	        String sql = "SELECT * FROM uhcms_student_record order by rdate desc,rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rst = pstmt.executeQuery()){
	            while(rst.next()){
	                StudentRecord student_record = new StudentRecord();
	                student_record.setRno(rst.getString("rno"));
	                student_record.setSno(rst.getString("sno"));
	                student_record.setSnumber(rst.getString("snumber"));
	                student_record.setJudgement(rst.getString("judgement"));
	                student_record.setCurrent_state(rst.getString("current_state"));
	                student_record.setRtime(rst.getString("rtime"));
	                student_record.setRdate(rst.getString("rdate"));
	                student_record.setStudent(new StudentDAO().selectStudentBySno(rst.getString("sno")));
	                stuList.add(student_record);
	            }
	            return stuList;
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	    }
	    
	  //查找所有打卡记录
	    public ArrayList<TeacherRecord> selectAllTeacherRecord(){
	        ArrayList<TeacherRecord> teaList = new ArrayList<TeacherRecord>();
	        String sql = "SELECT * FROM uhcms_teacher_record order by rdate desc,rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rst = pstmt.executeQuery()){
	            while(rst.next()){
	                TeacherRecord teacher_record = new TeacherRecord();
	                teacher_record.setRno(rst.getString("rno"));
	                teacher_record.setTno(rst.getString("tno"));
	                teacher_record.setTnumber(rst.getString("tnumber"));
	                teacher_record.setJudgement(rst.getString("judgement"));
	                teacher_record.setCurrent_state(rst.getString("current_state"));
	                teacher_record.setRtime(rst.getString("rtime"));
	                teacher_record.setRdate(rst.getString("rdate"));
	                teacher_record.setTeacher(new TeacherDAO().selectTeacherByTno(rst.getString("tno")));
	                teaList.add(teacher_record);
	            }
	            return teaList;
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	    }
	    
	  //按照学生姓名模糊查询打卡记录，降序排列
	    public ArrayList<StudentRecord> selectStudentRecordByFuzzySname(String sname){
	        ArrayList<StudentRecord> stuList = new ArrayList<StudentRecord>();
	        String sql = "SELECT * FROM uhcms_student_record, uhcms_student WHERE uhcms_student_record.sno = uhcms_student.sno and sname like ? order by rdate desc,rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,"%"+sname+"%");
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    StudentRecord student_record = new StudentRecord();
	                    student_record.setRno(rst.getString("rno"));
	                    student_record.setSno(rst.getString("sno"));
	                    student_record.setSnumber(rst.getString("snumber"));
	                    student_record.setJudgement(rst.getString("judgement"));
	                    student_record.setCurrent_state(rst.getString("current_state"));
	                    student_record.setRtime(rst.getString("rtime"));
	                    student_record.setRdate(rst.getString("rdate"));
	                    student_record.setStudent(new StudentDAO().selectStudentBySno(rst.getString("sno")));
	                    stuList.add(student_record);
	                }
	            }
	        } catch (SQLException se) {
	            return null;
	        }
	        return stuList;
	    }
	    
	  //根据教师姓名模糊查询
	    public ArrayList<TeacherRecord> selectTeacherRecordByFuzzyTname(String tname){
	        ArrayList<TeacherRecord> teaList = new ArrayList<TeacherRecord>();
	        String sql = "SELECT * FROM uhcms_teacher_record, uhcms_teacher WHERE uhcms_teacher_record.tno = uhcms_teacher.tno and tname like ? order by rdate desc,rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,"%"+tname+"%");
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    TeacherRecord teacher_record = new TeacherRecord();
	                    teacher_record.setRno(rst.getString("rno"));
	                    teacher_record.setTno(rst.getString("tno"));
	                    teacher_record.setTnumber(rst.getString("tnumber"));
	                    teacher_record.setJudgement(rst.getString("judgement"));
	                    teacher_record.setCurrent_state(rst.getString("current_state"));
	                    teacher_record.setRtime(rst.getString("rtime"));
	                    teacher_record.setRdate(rst.getString("rdate"));
	                    teacher_record.setTeacher(new TeacherDAO().selectTeacherByTno(rst.getString("tno")));
	                    teaList.add(teacher_record);
	                }
	            }
	        } catch (SQLException se) {
	            return null;
	        }
	        return teaList;
	    }
	    
	  //按照时间查询打卡记录
	    public ArrayList<StudentRecord> selectAllStudentRecordByRdate(String s_time1){
	        ArrayList<StudentRecord> stuList = new ArrayList<StudentRecord>();
	        String sql = "SELECT * FROM uhcms_student_record where rdate=? order by rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,s_time1);
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    StudentRecord student_record = new StudentRecord();
	                    student_record.setRno(rst.getString("rno"));
	                    student_record.setSno(rst.getString("sno"));
	                    student_record.setSnumber(rst.getString("snumber"));
	                    student_record.setJudgement(rst.getString("judgement"));
	                    student_record.setCurrent_state(rst.getString("current_state"));
	                    student_record.setRtime(rst.getString("rtime"));
	                    student_record.setRdate(rst.getString("rdate"));
	                    student_record.setStudent(new StudentDAO().selectStudentBySno(rst.getString("sno")));
	                    stuList.add(student_record);
	                }
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return stuList;
	    }
	    
	  //根据日期查找打卡记录
	    public ArrayList<TeacherRecord> selectAllTeacherRecordByRdate(String t_time1){
	        ArrayList<TeacherRecord> teaList = new ArrayList<TeacherRecord>();
	        String sql = "SELECT * FROM uhcms_teacher_record where rdate=? order by rdate desc,rtime desc";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,t_time1);
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    TeacherRecord teacher_record = new TeacherRecord();
	                    teacher_record.setRno(rst.getString("rno"));
	                    teacher_record.setTno(rst.getString("tno"));
	                    teacher_record.setTnumber(rst.getString("tnumber"));
	                    teacher_record.setJudgement(rst.getString("judgement"));
	                    teacher_record.setCurrent_state(rst.getString("current_state"));
	                    teacher_record.setRtime(rst.getString("rtime"));
	                    teacher_record.setRdate(rst.getString("rdate"));
	                    teacher_record.setTeacher(new TeacherDAO().selectTeacherByTno(rst.getString("tno")));
	                    teaList.add(teacher_record);
	                }
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return teaList;
	    }
	    
	    //通过学生类插入
	    public boolean insertStudentRecord(Student student, String judgement) {
	    	Date date=new Date();
	        java.sql.Date date1=new java.sql.Date(date.getTime());
	        Time time=new Time(date.getTime());
	    	String sno=student.getSno();
	    	String snumber=student.getNumber();
	    	String sstate=student.getState();
	    	String rdate=date1.toString();
	    	String rtime=time.toString();
	    	String sql = "INSERT INTO uhcms_student_record(sno,snumber,judgement,current_state,rtime,rdate)VALUES(?,?,?,?,?,?)";
	    	 try (Connection conn = dataSource.getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)){
		            pstmt.setString(1,sno);
		            pstmt.setString(2,snumber);
		            pstmt.setString(3,judgement);
		            pstmt.setString(4,sstate);
		            pstmt.setString(5,rtime);
		            pstmt.setString(6,rdate);
		            pstmt.executeUpdate();
		            return true;
		        }catch (SQLException se){
		            se.printStackTrace();
		            return false;
		        }
	    }
	    
	    //通过教师类插入
	    public boolean insertTeacherRecord(Teacher teacher, String judgement) {
	    	Date date=new Date();
	        java.sql.Date date1=new java.sql.Date(date.getTime());
	        Time time=new Time(date.getTime());
	    	String tno=teacher.getTno();
	    	String tnumber=teacher.getTnumber();
	    	String tstate=teacher.getTstate();
	    	String rdate=date1.toString();
	    	String rtime=time.toString();
	    	String sql = "INSERT INTO uhcms_teacher_record(tno,tnumber,judgement,current_state,rtime,rdate)VALUES(?,?,?,?,?,?)";
	    	 try (Connection conn = dataSource.getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)){
		            pstmt.setString(1,tno);
		            pstmt.setString(2,tnumber);
		            pstmt.setString(3,judgement);
		            pstmt.setString(4,tstate);
		            pstmt.setString(5,rtime);
		            pstmt.setString(6,rdate);
		            pstmt.executeUpdate();
		            return true;
		        }catch (SQLException se){
		            se.printStackTrace();
		            return false;
		        }
	    }
	    
	    //检测学生是否达到标准
	    public boolean checkIfStudentColorChange(String sno, String color) {
	    	Student student=new StudentDAO().selectStudentBySno(sno);
	    	if(student.getState().equalsIgnoreCase(color)) {
	    		String sql = "SELECT count(*) FROM uhcms_student_record where DATE_SUB(CURDATE(), "
	    				+ "INTERVAL 7 DAY)<= date(rdate) and judgement='绿'and sno=? and current_state=?";
	    		try (Connection conn = dataSource.getConnection();
			             PreparedStatement pstmt = conn.prepareStatement(sql)){
			            pstmt.setString(1,sno);
			            pstmt.setString(2,color);
			            ResultSet rst=pstmt.executeQuery();
			            rst.next();
			            int c = rst.getInt("count(*)");
			            if(c<7)return false;
			            else return true;
			        }catch (SQLException se){
			            se.printStackTrace();
			            return false;
			        }
	    	}
	    	else return false;
	    }
	    
	    
	    //检测教师是否达到标准
	    public boolean checkIfTeacherColorChange(String tno, String color) {
	    	Teacher teacher=new TeacherDAO().selectTeacherByTno(tno);
	    	if(teacher.getTstate().equalsIgnoreCase(color)) {
	    		String sql = "SELECT count(*) FROM uhcms_teacher_record where DATE_SUB(CURDATE(), "
	    				+ "INTERVAL 7 DAY)<= date(rdate) and judgement='绿'and tno=? and current_state=?";
	    		try (Connection conn = dataSource.getConnection();
			             PreparedStatement pstmt = conn.prepareStatement(sql)){
			            pstmt.setString(1,tno);
			            pstmt.setString(2,color);
			            ResultSet rst=pstmt.executeQuery();
			            rst.next();
			            if(rst.getInt("count(*)")<7) {return false;}
			            else {return true;}
			        }catch (SQLException se){
			            se.printStackTrace();
			            return false;
			        }
	    	}
	    	else return false;
	    }
	    
	    //根据老师准确学院和模糊姓名查询
		public List<TeacherRecord> selectTeacherRecordByFuzzyTnameAndCollege(String name, String college) {
			ArrayList<TeacherRecord> teaList = new ArrayList<TeacherRecord>();
	        String sql = "SELECT uhcms_teacher_record.* FROM uhcms_teacher_record,uhcms_teacher where uhcms_teacher.tno=uhcms_teacher_record.tno and "
	        		+ "uhcms_teacher.tcollege=? and uhcms_teacher.tname like ? ";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,college);
	            pstmt.setString(2,"%"+name+"%");
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                    TeacherRecord teacher_record = new TeacherRecord();
	                    teacher_record.setRno(rst.getString("rno"));
	                    teacher_record.setTno(rst.getString("tno"));
	                    teacher_record.setTnumber(rst.getString("tnumber"));
	                    teacher_record.setJudgement(rst.getString("judgement"));
	                    teacher_record.setCurrent_state(rst.getString("current_state"));
	                    teacher_record.setRtime(rst.getString("rtime"));
	                    teacher_record.setRdate(rst.getString("rdate"));
	                    teacher_record.setTeacher(new TeacherDAO().selectTeacherByTno(rst.getString("tno")));
	                    teaList.add(teacher_record);
	                }
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return teaList;
		}
	    //根据学生准确学院和模糊姓名查询
		public List<StudentRecord> selectStudentRecordByFuzzySnameAndCollege(String name,String college) {
			ArrayList<StudentRecord> stuList = new ArrayList<StudentRecord>();
	        String sql = "SELECT uhcms_student_record.* FROM uhcms_student_record,uhcms_student where uhcms_student.sno=uhcms_student_record.sno and "
	        		+ "uhcms_student.scollege=? and uhcms_student.sname like ? ";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)){
	            pstmt.setString(1,college);
	            pstmt.setString(2,"%"+name+"%");
	            try (ResultSet rst = pstmt.executeQuery()){
	                while(rst.next()){
	                	StudentRecord student_record = new StudentRecord();
	                    student_record.setRno(rst.getString("rno"));
	                    student_record.setSno(rst.getString("sno"));
	                    student_record.setSnumber(rst.getString("snumber"));
	                    student_record.setJudgement(rst.getString("judgement"));
	                    student_record.setCurrent_state(rst.getString("current_state"));
	                    student_record.setRtime(rst.getString("rtime"));
	                    student_record.setRdate(rst.getString("rdate"));
	                    student_record.setStudent(new StudentDAO().selectStudentBySno(rst.getString("sno")));
	                    stuList.add(student_record);
	                }
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            return null;
	        }
	        return stuList;
		}

	
}
