package memo.main;

import java.util.List;
import java.util.Scanner;

import memo.dao.MemberDAO;
import memo.dao.MemoDAO;
import memo.vo.MemberVO;
import memo.vo.MemoVO;

public class MemoMain {
	private static Scanner sc;
	private MemoVO mmvo;
	private MemoDAO mdao;
	public static int session=0;
	public static String sessionID_memo;
	public MemoMain() {
		sc = new Scanner(System.in);
		mmvo = new MemoVO();
		mdao = new MemoDAO();
	}
	public void memo_main(String sessionID) {
		sessionID_memo = sessionID;
		mmvo.setMid_pk(sessionID);
		System.out.println(mmvo.getMid_pk());
		System.out.println("1:Memo write");
		System.out.println("2:All Memo");
		System.out.println("3:My memo");
		System.out.println("4:Exit");
		int num = sc.nextInt();	
		switch (num) {
		case 1:
			write_memo();
			break;
		case 2:
			memolist();
			break;
		case 3:
			mymemolist();
			break;
		case 4:	
			sc.close();
			System.exit(0);
			break;
		}
	}
	public void write_memo() {
		System.out.println("1:Memo write");
		mmvo.setMemo(sc.next());
		int result = mdao.insert_memo(mmvo,sessionID_memo);
		if(result == 1) {
			System.out.println("글 작성 완료");
			memo_main(sessionID_memo);
		} else {
			System.out.println("글 작성 실패");
		}
	}
	public void memolist() { 		//회원 목록
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMO LIST   ");
		System.out.println("------------------------------------------");
		
		List<MemoVO> mvoList = mdao.list(); 
		
		if(mvoList.size() > 0) {	//등록된 게시물이 있으면 화면에 표시
			//for문을 이용-------------------------------------------------
			System.out.println("아이디 | 글 번호 | 내용 | 작성일자");
			for(int i=0 ; i<mvoList.size() ; i++) {
				MemoVO mvo = mvoList.get(i);
				System.out.println(mvo.getMid_pk() + " | " + mvo.getMno() + " | " + 
										    mvo.getMemo()+ " | " + mvo.getRegDate());
			}		
			System.out.println();		
			/*System.out.println("아이디 | 글 번호 | 내용 | 작성일자");
			for(int i=0 ; i<mvoList.size() ; i++) {
				MemoVO mvo = mvoList.get(i);
				System.out.println(mvo.getMid_pk() + " | " + mvo.getMno() + " | " + 
										    mvo.getMemo()+ " | " + mvo.getRegDate());
			}*/
		} else { 
			System.out.println("등록된 글이 없습니다.");
		}
		memo_main(sessionID_memo);		//메인메뉴 표시
	}
	public void mymemolist() { 		//회원 목록
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMO LIST   ");
		System.out.println("------------------------------------------");
		
		List<MemoVO> mvoList = mdao.mylist(sessionID_memo); 
		
		if(mvoList.size() > 0) {	//등록된 게시물이 있으면 화면에 표시
			//for문을 이용-------------------------------------------------
			System.out.println("아이디 | 글 번호 | 내용 | 작성일자");
			for(int i=0 ; i<mvoList.size() ; i++) {
				MemoVO mvo = mvoList.get(i);
				System.out.println(mvo.getMid_pk() + " | " + mvo.getMno() + " | " + 
										    mvo.getMemo()+ " | " + mvo.getRegDate());
			}		
			System.out.println();
			/*
			System.out.println("아이디 | 글 번호 | 내용 | 작성일자");
			for(int i=0 ; i<mvoList.size() ; i++) {
				MemoVO mvo = mvoList.get(i);
				System.out.println(mvo.getMid_pk() + " | " + mvo.getMno() + " | " + 
										    mvo.getMemo()+ " | " + mvo.getRegDate());
			}*/
		} else { 
			System.out.println("등록된 글이 없습니다.");
		}
		System.out.println("1:수정 2:삭제 3:메뉴");
		int num1 = sc.nextInt();	
		switch (num1) {
		case 1:
			update();//update
			break;
		case 2:
			delete();//delete
			break;
		case 3:
			memo_main(sessionID_memo);//return
			break;
		}
	}
	
	public void update(){
		System.out.println("수정할 글 번호 입력");
		mmvo.setMno(sc.nextInt());
		boolean result = mdao.update_memo(mmvo.getMno(),sessionID_memo);
		if(result == true) {
			System.out.println("글 수정 완료");
			memo_main(sessionID_memo);
		} else {
			System.out.println("글 수정 실패");
		}
	}
	public void delete(){
		mmvo.setMemo(sc.next());
		boolean result = mdao.delete_memo(mmvo.getMno(),sessionID_memo);
		if(result == true) {
			System.out.println("글 삭제 완료");
			memo_main(sessionID_memo);
		} else {
			System.out.println("글 삭제 실패");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
