package cn.edu.zjut.uhcms.model;

public class TeacherRecord {
private String rno;
private String Tno;
private Teacher teacher;
private String Tnumber;
private String judgement;
private String current_state;
private String rtime;
private String rdate;
public Teacher getTeacher() {
	return teacher;
}
public void setTeacher(Teacher teacher) {
	this.teacher = teacher;
}

public String getRno() {
	return rno;
}
public void setRno(String rno) {
	this.rno = rno;
}
public String getTno() {
	return Tno;
}
public void setTno(String tno) {
	Tno = tno;
}
public String getTnumber() {
	return Tnumber;
}
public void setTnumber(String tnumber) {
	Tnumber = tnumber;
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
}
