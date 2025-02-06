package finance_Management;

import java.util.*;

public class FinanceCalculate {
	ArrayList <Integer> valist;
	ArrayList <String> namelist;
	Scanner sc;
	
	public FinanceCalculate(ArrayList <Integer> s) { 
		sc = new Scanner(System.in);
		valist = s;
		namelist.add("라지코드");
		namelist.add("스몰코드");		
		namelist.add("현금성자산");
		namelist.add("유형자산");
		namelist.add("기타금융자산");
		namelist.add("매입채무");
		namelist.add("차입금");
		namelist.add("영업수익");
		namelist.add("금융수익");
		namelist.add("영업비용");
		namelist.add("기타비용");
		namelist.add("금융비용");
		namelist.add("유형자산취득");
		namelist.add("유형자산처분");
		namelist.add("부채상환");
	}
	
	public ArrayList <Integer> getInfo() {
		System.out.println("어떤 value을 입력할지 숫자로 입력해주세요");
		for (int i=2; i<namelist.size();i++) {
			System.out.println(i-2+". "+namelist.get(i));
		}
		int input = sc.nextInt();
		System.out.println("입력할 값을 정수로 입력하세요");
		int input2 = sc.nextInt();
		
		switch(input){
		case 1: // 현금성자산
			valist.set(2, valist.get(2)+input2);
			System.out.println("입력한 현금성자산 "+input2+"이 현금성자산에 추가 되었습니다");
			break;
		case 2: break;
		case 3: // 유형자산
		valist.set(3, valist.get(3)+input2);
		valist.set(2, valist.get(2)-input2);
		System.out.println("입력한 유형자산 "+input2+"이 등록되고 현금성자산에 감산 되었습니다");
			break; 
		case 4: // 기타금융자산
			valist.set(4, valist.get(4)+input2);
			valist.set(2, valist.get(2)-input2);
			System.out.println("입력한 기타금융자산 "+input2+"이 등록되고 현금성자산에 감산 되었습니다");
			break;
		case 5: // 매입채무
			valist.set(5, valist.get(5)+input2);
			valist.set(3, valist.get(3)+input2);
			System.out.println("입력한 매입채무 "+input2+"이 등록되고 유형자산에 추가 되었습니다");
			break;
		case 6: // 차입금
			valist.set(6, valist.get(6)+input2);
			valist.set(2, valist.get(2)+input2);
			System.out.println("입력한 차입금 "+input2+"이 등록되고 현금성자산에 추가 되었습니다");
			break;
		case 7: // 영업수익
			valist.set(2, valist.get(2)+input2);
			System.out.println("입력한 영업수익 "+input2+"이 현금성자산에 추가 되었습니다");
			break;
		case 8: // 금융수익
			valist.set(4, valist.get(4)+input2);
			System.out.println("입력한 금융수익 "+input2+"이 기타금융자산에 추가 되었습니다");
			break;
		case 9: // 영업수익
			valist.set(2, valist.get(2)-input2);
			System.out.println("입력한 영업수익 "+input2+"이 현금성자산에 감산 되었습니다");
			break;
		case 10: // 유형자산취득
			valist.set(2, valist.get(2)-input2);
			valist.set(3, valist.get(3)+input2);
			System.out.println("입력한 유형자산 취득 "+input2+"이 유형자산에 추가 되고 현금성자산에 감산 되었습니다");
			break;
		case 11: // 유형자산처분
			valist.set(2, valist.get(2)+input2);
			valist.set(3, valist.get(3)-input2);
			System.out.println("입력한 유형자산 처분 "+input2+"이 현금성자산에 추가 되고 유형자산에 감산 되었습니다");
			break;
		case 12: // 부채상환
			valist.set(2, valist.get(2)-input2);
			valist.set(6, valist.get(6)-input2);
			System.out.println("입력한 부채상환 "+input2+"이 차입금에 감산 되고 현금성자산에 감산 되었습니다");
			break;
		default: System.out.println("잘못된 값 입력에 따른 메소드 종료");
		}
		
		return valist;
	}
	
}
