package start;

import java.util.Scanner;

public class Login {
	String dbId;
	String dbPw;
	
	public Login() {}
	public Login(String dbId, String dbPw) {
		this.dbId = dbId;
		this.dbPw = dbPw;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Login login = new Login("scott","1234");
		
		System.out.print("Id : ");
		String inputId = sc.nextLine();
		System.out.print("Pw : ");
		String inputPw = sc.nextLine();
		
		if(login.dbId.equals(inputId) && login.dbPw.equals(inputPw)) {
			System.out.println("�α��ο� �����Ͽ����ϴ�.");
		}else {
			System.out.println("�α��ο� �����Ͽ����ϴ�.");
		}
		
		sc.close();	
	}	
}


