package memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import memo.vo.MemberVO;
import memo.vo.MemoVO;
import memo.jdbc.DBConn;

public class MemberDAO {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	Connection con = DBConn.getConnecetion();
	ResultSet rs = null;
	String sql = null;
	private MemberVO mvo;
	public MemberVO admin_check(String sessionID) {
		String sql = "select admin_ck from member "
				+"where mid=? ";	//보통 where = password id를 받음			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,sessionID);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {		
				mvo = new MemberVO();
				mvo.setAdmin_ck(rs.getString("admin_ck"));	
				System.out.println(rs.getString("admin_ck"));
			}
			//
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(pstmt);
		}	
		return mvo;	
	}
	public int check(String mid, String pwd) {
		new MemberVO();
		int result=0;
		String sql = "select * from member "
				+"where mid=? AND pwd=? ";	//보통 where = password id를 받음			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,mid);
			pstmt.setNString(2,pwd);
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println(mid+"님 환영합니다");
				System.out.println("success");
			} else {
				System.out.println("falid");
			}
			//
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(pstmt);
		}	
		return result;
	}
	public boolean IDsearch(String mname,String phone) {//이름과 전화번호를 받아서 맞을 경우에만 ID값 리턴
		new MemberVO();
		String sql = "select * from member "
				+"where mname=? AND phone=? ";	//			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,mname);//
			pstmt.setNString(2,phone);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {
				mvo = new MemberVO();
				mvo.setMid(rs.getNString("mid"));	
			}
			//
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(pstmt);
		}	
		return true;
	}
	public boolean PWDsearch(String mid,String phone) {
		new MemberVO();
		String sql = "select pwd from member "
				+"where mid=? AND phone=? ";	//보통 where = password id를 받음			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,mvo.getMid());
			rs = pstmt.executeQuery();
			while (rs.next() == true) {
				mvo = new MemberVO();
				mvo.setPwd(rs.getNString("pwd"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(pstmt);
		}	
		return true;
	}
	public boolean insert(MemberVO mvo) {		
		try {
			String query="insert into member(mid,pwd,age,mname,phone,admin_ck,reg_date)"
					+ "values(?,?,?,?,?,'0',SYSDATE)";
			pstmt = con.prepareStatement(query);			
			pstmt.setNString(1, mvo.getMid());
			pstmt.setNString(2, mvo.getPwd());
			pstmt.setInt(3,mvo.getAge());
			pstmt.setNString(4,mvo.getMname());
			pstmt.setNString(5,mvo.getPhone());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("success");
			} else {
				System.out.println("falid");
			}

		}catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}finally{
			DBConn.close(pstmt);
		}		
		return true;
	}
	//관리자 관련 메소드 추가하기
	public ArrayList<MemberVO> member_list() {
		ArrayList<MemberVO> devmemList = new ArrayList<>();
		MemberVO mvo = null;	
		try {
			sql = " SELECT * FROM member ";
			pstmt = DBConn.getConnecetion().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {		//조회된 레코드들이 있다면 
				mvo = new MemberVO();	//MemberVO 객체를 생성하여 
				mvo.setMid(rs.getString("mid"));		//해당 레코드 값을 저장한 후
				mvo.setMname(rs.getString("mname"));
				mvo.setAge(rs.getInt("age"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setRegDate(rs.getDate("reg_date"));
				
				devmemList.add(mvo);  //List 객체에 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return devmemList;
	}
	public boolean admin_update_query(String update_id1){//수정할 회원의 ID를 입력받고 마음대로 수정
		return true;
	}
	public boolean admin_delete_query(String delete_id2){//삭제할 ID 입력받고 누구든 삭제한다
		return true;
	}
}
