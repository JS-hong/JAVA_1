package memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import memo.jdbc.DBConn;
import memo.vo.MemoVO;

public class MemoDAO {
	private String sql;						//쿼리문 저장 필드
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	public ArrayList<MemoVO> list() {
		ArrayList<MemoVO> memoList = new ArrayList<>();
		MemoVO mvo = null;	
		try {
			sql = " SELECT * FROM t_memo ";
			pstmt = DBConn.getConnecetion().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {
				mvo = new MemoVO();
				mvo.setMid_pk(rs.getString("mid"));	
				mvo.setMno(rs.getInt("mno"));
				mvo.setMemo(rs.getString("memo"));
				mvo.setRegDate(rs.getDate("reg_date"));			
				memoList.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt,rs);
		}
		return memoList;
	}
	public ArrayList<MemoVO> mylist(String sessionID_memo) {
		ArrayList<MemoVO> memoList = new ArrayList<>();
		MemoVO mvo = null;	
		try {
			sql = " SELECT * FROM t_memo where mid=? ";
			pstmt = DBConn.getConnecetion().prepareStatement(sql);
			pstmt.setNString(1,sessionID_memo);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {
				mvo = new MemoVO();
				mvo.setMid_pk(rs.getString("mid"));	
				mvo.setMno(rs.getInt("mno"));
				mvo.setMemo(rs.getString("memo"));
				mvo.setRegDate(rs.getDate("reg_date"));			
				memoList.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt,rs);
		}
		return memoList;
	}
	public int insert_memo(MemoVO mmvo,String mid) {
		int result=0;
		try {
			String query="insert into t_memo(mno,mid,memo,reg_date)"
					+ "values(memo_seq.nextVal,?,?,SYSDATE)";
			pstmt = DBConn.getConnecetion().prepareStatement(query);			
			pstmt.setNString(1, mmvo.getMid_pk());
			pstmt.setNString(2, mmvo.getMemo());
			result = pstmt.executeUpdate();
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
		return result;
	}
	public boolean update_memo(int i, String sessionID_memo) {
		int result=0;
		Scanner sc = new Scanner(System.in);
		try {
			String query="update t_memo set(mno,mid,memo,reg_date)"
					+ "values(memo_seq.nextVal,?,?,SYSDATE)";
			pstmt = DBConn.getConnecetion().prepareStatement(query);
			//pstmt.setNString(1,);
			//pstmt.setNString(1,);
			//pstmt.setNString(1,);
			result = pstmt.executeUpdate();
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
	public boolean delete_memo(int mno, String sessionID_memo) {
		int result=0;
		String sql="select mid from t_memo where mno=?";
		try {
			String query="delete from t_memo "
					+ "where mno=? ";
			pstmt = DBConn.getConnecetion().prepareStatement(query);			
			pstmt.setLong(1,mno);
			result = pstmt.executeUpdate();
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
	
}
