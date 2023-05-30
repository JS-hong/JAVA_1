package memo.vo;

import java.util.Date;

public class MemoVO {
	private int mno;
	private String memo;
	private String mid_pk;
	private Date regDate;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMid_pk() {
		return mid_pk;
	}
	public void setMid_pk(String mid_pk) {
		this.mid_pk = mid_pk;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
