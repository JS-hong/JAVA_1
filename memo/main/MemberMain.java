package memo.main;

import java.util.ArrayList;
import java.util.Scanner;
import memo.dao.MemberDAO;
import memo.vo.MemberVO;
import memo.main.MemberMain;

public class MemberMain {
	private static Scanner sc;
	private MemberDAO dao;
	private MemberVO mvo;
	public static int session=0;
	public static String sessionID;
	public MemberMain() {
		sc = new Scanner(System.in);
		mvo = new MemberVO();
		dao = new MemberDAO();
	}
	public void main() {
		MemberMain mm = new MemberMain();
		System.out.println("1:Login Form");
		System.out.println("2:Join");
		System.out.println("3:ID Search");
		System.out.println("4:PWD Search");
		System.out.println("5:EXIT Programe");
		int num = sc.nextInt();	
		switch (num) {
		case 1:
			mm.login();
			break;
		case 2:
			mm.join();
			break;
		case 3:
			ID_search();
			break;
		case 4:
			PWD_search();
			break;
		case 5:	
			sc.close();
			System.exit(0);
			break;
		}
	}	
	public void menu() {
		System.out.println("1:");
		System.out.println("2:");
		System.out.println("3:Go Memo Form");
		System.out.println("4:Run admin");
		System.out.println("5:Logout:");
		int num = sc.nextInt();	
		switch (num) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			MemoMain mo = new MemoMain();
			System.out.println();
			mo.memo_main(sessionID);
			break;
		case 4:
			ck_admin();
			break;
		case 5:	
			logout();
			main();
			break;
		}
	}
	public void devmenu() {
		System.out.println("1:Member Info");
		System.out.println("2:Member Update");
		System.out.println("3:Member Delete");
		System.out.println("4:Dev Mode Off");
		int num = sc.nextInt();	
		switch (num) {
		case 1:
			dev_member_list();
			break;
		case 2:
			dev_update_member();
			break;
		case 3:
			dev_delete_query();
			break;
		case 4:	
			menu();
			break;
		}
	}
	public void dev_update_member() {
		System.out.println("수정할 멤버ID 입력:");
		String update_id1 = sc.next();
		boolean Result = dao.admin_update_query(update_id1);
		if(Result == true) {
			devmenu();
		}
	}
	public void dev_delete_query() {
		System.out.println("삭제할 멤버ID 입력:");
		String delete_id2 = sc.next();
		boolean Result = dao.admin_delete_query(delete_id2);
		if(Result == true) {
			devmenu();
		}
	}
	private void dev_member_list() {
		System.out.println("\n------------------------------------------");
		System.out.println("         DEV MODE MEMBER LIST   ");
		System.out.println("------------------------------------------");
		
		ArrayList<MemberVO> devmemList = dao.member_list(); 
		
		if(devmemList.size() > 0) {	//등록된 게시물이 있으면 화면에 표시
			//for문을 이용-------------------------------------------------
			System.out.println("아이디 | 이름 | 나이 | 전화번호 | 가입일자");
			for(int i=0 ; i<devmemList.size() ; i++) {
				MemberVO mvo = devmemList.get(i);
				System.out.println(mvo.getMid() + " | " + mvo.getMname() + " | " + 
										    mvo.getAge()+ " | " + mvo.getPhone() + " | " +
										    mvo.getRegDate());
			}
		} else { 
			System.out.println("   등록된 회원이 없습니다.");
		}
		devmenu();	
	}
	public void ck_admin() {	
		MemberVO mvo2 = dao.admin_check(sessionID);
		if(mvo2.getAdmin_ck().equals("1")) {
			System.out.println(sessionID + "관리자님 환영합니다");
			devmenu();
		}
		else {
			System.out.println("관리자가 아닙니다");
			menu();
		}
	}
	public void login() {
		MemberVO mvo = new MemberVO();
		System.out.print("ID:");
			mvo.setMid(sc.next());
		System.out.print("PWD:");
			mvo.setPwd(sc.next());
		int Result = dao.check(mvo.getMid(),mvo.getPwd());
		if(Result == 1) {
			session = 1;
			sessionID = mvo.getMid();
			menu();
		}
		else {
			System.out.println("Login Failed");
			main();
		}
	}
	public void ID_search() {//DAO는 맞으면 setID과정이 필요함
		MemberVO mvo = new MemberVO();
		System.out.print("이름 입력:");
			mvo.setMname(sc.next());
		System.out.print("전화번호 입력:");
			mvo.setPhone(sc.next());
		boolean Result = dao.IDsearch(mvo.getMname(),mvo.getPhone());
		if(Result == true) {
			System.out.println(mvo.getMid()); 
			main();
		}
		else {
			System.out.println("Login Failed");
			main();
		}
	}
	public void PWD_search() {//DAO는 맞으면 setPWD과정이 필요함
		MemberVO mvo = new MemberVO();
		System.out.print("ID 입력:");
			mvo.setMid(sc.next());
		System.out.print("전화번호 입력:");
			mvo.setPhone(sc.next());
		boolean Result = dao.PWDsearch(mvo.getMid(),mvo.getPhone());
		if(Result == true) {
			System.out.println(mvo.getPwd()); 
			main();
		}
		else {
			System.out.println("Login Failed");
			main();
		}
	}
	public void join() {//회원 가입
		System.out.println("JOiN TAB");
		
		System.out.print("ID:");
		mvo.setMid(sc.next());
		
		System.out.print("PWD:");
		mvo.setPwd(sc.next());
		
		System.out.print("AGE:");
		mvo.setAge(sc.nextInt());
		
		System.out.print("NAME:");
		mvo.setMname(sc.next());
		
		System.out.print("PHONE:");
		mvo.setPhone(sc.next());
		
		boolean anv = dao.insert(mvo);
		if(anv==true){
			System.out.println("회원가입 완료");
		}
		else{
			System.out.println("회원가입 실패");
		}
		main();
	}
	private void logout() {
		sessionID=null;
		session=0;
	}
	public static void main(String[] args){
		MemberMain mm = new MemberMain();
		if(session == 1) {
			mm.menu();
		}
		else{
			mm.main();
		}
	}
}
