package cn.edu.zjut.uhcms.pojo;

public class ModifyPassForm {
	private String oldPassword;
	private String newPassword;
	private String surePassword;
	
	public ModifyPassForm() {}
	
	public ModifyPassForm(String oldPassword, String newPassword, String surePassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.surePassword = surePassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getSurePassword() {
		return surePassword;
	}
	public void setSurePassword(String surePassword) {
		this.surePassword = surePassword;
	}
	
}
