package cn.edu.zjut.uhcms.model;

public class StudentRecord {
private String rno;
private String sno;
private String snumber;
private Student student;
public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}
private String judgement;
private String current_state;
private String rtime;
public String getRno() {
	return rno;
}
public void setRno(String rno) {
	this.rno = rno;
}
public String getSno() {
	return sno;
}
public void setSno(String sno) {
	this.sno = sno;
}
public String getSnumber() {
	return snumber;
}
public void setSnumber(String snumber) {
	this.snumber = snumber;
}
public String getJudgement() {
	return judgement;
}
public void setJudgement(String judgement) {
	this.judgement = judgement;
}
public String getCurrent_state() {
	return current_state;
}
public void setCurrent_state(String current_state) {
	this.current_state = current_state;
}
public String getRtime() {
	return rtime;
}
public void setRtime(String rtime) {
	this.rtime = rtime;
}
public String getRdate() {
	return rdate;
}
public void setRdate(String rdate) {
	this.rdate = rdate;
}
private String rdate;
}
