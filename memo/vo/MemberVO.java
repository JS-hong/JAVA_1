package memo.vo;

import java.util.Date;

public class MemberVO {
	private String mid;
	private String mname;
	private String pwd;
	private String admin_ck;
	private int age;
	private String phone;
	private Date regDate; //memberDB
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getAdmin_ck() {
		return admin_ck;
	}
	public void setAdmin_ck(String admin_ck) {
		this.admin_ck = admin_ck;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
