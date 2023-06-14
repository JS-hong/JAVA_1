package hotel.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import hotel.dao.HotelRoomInfoDAO;
import hotel.vo.HotelRoomControlVO;
import hotel.vo.HotelRoomInfoVO;

public class HotelRoomInfoMain {//RoomDB 방관련 입니다~~
	private static Scanner sc;
	private statuc String sessionemp;
	private HotelRoomInfoVO rivo;
	private HotelRoomInfoDAO ridao;
	public static int session=0;
	public HotelRoomInfoMain(){
		sc = new Scanner(System.in);
		rivo = new HotelRoomInfoVO();
		ridao = new HotelRoomInfoDAO();
	}
	public void checkin(){//체크인 접수
	System.out.println("1:객실 호수 입력 =>");
		rivo.setRno(sc.nextInt());
	System.out.println("2:예약자명 입력 =>");
		rivo.setCname(sc.next());
	System.out.println("3:전화번호 입력 =>");
		rivo.setCphone(sc.next());
	System.out.println("4:이메일 입력 =>");
		rivo.setCemail(sc.next());
	System.out.println("5:퇴실일 입력 =>");
		String now =sc.next(); //yyyy-mm-dd 형식을 지켜야 함
		java.sql.Date date = java.sql.Date.valueOf(now);
		rivo.setCout_date(date);	
	int result = ridao.checkin(rivo,date);
		if(result == 1) {
			System.out.println("체크인 완료");
			infomain();
		} 
		else{
			System.out.println("체크인 실패");
			infomain();
		}
	}
	public void checkout() {//체크아웃 접수
		System.out.println("체크아웃할 방 호수를 입력하세요:");
		int num = sc.nextInt();
		boolean result = ridao.checkout(num);
		if(result == true) {
			System.out.println("체크아웃 완료");
			infomain();
		} else {
			System.out.println("체크아웃 실패");
			infomain();
		}
	}
	public void check_roomlist() {//전체 객실 현황 조회
		System.out.println("\n------------------------------------------");
		System.out.println("               LOOM INFO LIST           ");
		System.out.println("------------------------------------------");
		
		ArrayList<HotelRoomInfoVO> rcvList = ridao.check_roomlist();
		
		if(rcvList.size() > 0) {	//등록된 방이 있으면 화면에 표시
			System.out.println("| 방번호 | 이름 | 입실일 | 퇴실예정일 | 청소 현황 | 체크인 유무 | DNS 유무 ");
			for(int i=0 ; i<rcvList.size() ; i++) {
				rivo = rcvList.get(i);
				System.out.println(rivo.getRno() + " | " + rivo.getCname() + " | " + 
						rivo.getCin_date() + " | " + rivo.getCout_date()+ " | " + rivo.getRoom_clean()
						+ " | " + rivo.getCheck_in() + " | " + rivo.getDns_check());
			}		
			System.out.println();				
		} else { 
			System.out.println("등록된 방이 없습니다.");
		}
		infomain();//메인메뉴 표시	
	}
	public void check_room() {//객실별 현황 조회
		System.out.print(" 객실 번호 입력 => ");
		int num=sc.nextInt();
		
		System.out.println("\n------------------------------------------");
		System.out.println("               LOOM INFO           ");
		System.out.println("------------------------------------------");
		
		ArrayList<HotelRoomInfoVO> rcvList = ridao.check_room(num);
		
		if(rcvList.size() > 0) {	//등록된 방이 있으면 화면에 표시
			System.out.println("| 방번호 | 이름 | 입실일 | 퇴실예정일 | 청소 현황 | 체크인 유무 | DNS 유무 ");
			for(int i=0 ; i<rcvList.size() ; i++) {
				rivo = rcvList.get(i);
				System.out.println(rivo.getRno() + " | " + rivo.getCname() + " | " + 
						rivo.getCin_date() + " | " + rivo.getCout_date()+ " | " + rivo.getRoom_clean()
						+ " | " + rivo.getCheck_in() + " | " + rivo.getDns_check());
			}		
			System.out.println();				
		} else { 
			System.out.println("등록된 방이 없습니다.");
		}
		infomain();//메인메뉴 표시	
	}
	public void check_res(){//입실가능객실 목록 조회
		
		System.out.println("\n------------------------------------------");
		System.out.println("               LOOM INFO           ");
		System.out.println("------------------------------------------");
		
		ArrayList<HotelRoomInfoVO> rcrList = ridao.check_res();
		
		if(rcrList.size() > 0) {	//등록된 방이 있으면 화면에 표시
			System.out.println("| 방번호 | 체크인 유무 |");
			for(int i=0 ; i<rcrList.size() ; i++) {
				rivo = rcrList.get(i);
				System.out.println("  "+rivo.getRno() + " |    " + rivo.getCheck_in());
			}		
			System.out.println("");				
		} else { 
			System.out.println("예약가능한 방이 없습니다.");
		}
		infomain();	
	}
	public void room_change() {//객실 변경
		System.out.println(" 현재 고객의 방 호수 입력=> ");
		int num1=sc.nextInt();
		System.out.println(" 이동할 방 호수 입력=> ");
		int num2=sc.nextInt();
	boolean result = ridao.room_change(num1,num2);
	if(result == true) {
		System.out.println("객실 변경 완료");
	} else {
		System.out.println("객실 변경 실패");
		}
	infomain();	
	}	
	public void infomain(String stremp){//객실 관리
		sessionemp = stremp;
		HotelRoomInfoMain sm = new HotelRoomInfoMain();
		System.out.println("==========객실 관리==========");
		System.out.println("1:체크인 접수");
		System.out.println("2:체크아웃 접수");
		System.out.println("3:전체 객실 현황 조회");
		System.out.println("4:객실 별 현황 조회");
		System.out.println("5:입실 가능한 객실 목록");
		System.out.println("6:객실 변경");
		System.out.println("7:뒤로가기");
		System.out.print(" 입력 => ");
		int num = sc.nextInt();	
		switch (num) {
		case 1:	
			checkin();
			break;
		case 2:
			checkout();
			break;
		case 3:
			check_roomlist();
			break;
		case 4:	
			check_room();
			break;
		case 5:
			check_res();
			break;
		case 6:
			room_change();
			break;
		case 7:
			if(){

			}
			else{

			}
			break;
		}
	}	
	public static void main(String[] args) throws ParseException {
		HotelRoomInfoMain sm = new HotelRoomInfoMain();
		sm.infomain();
	}
}
