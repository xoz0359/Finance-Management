package finance_Management;

import java.util.*;

public class FinanceCalculate {
	ArrayList <Integer> valist;
	ArrayList <String> namelist;
	Scanner sc;
	
	public FinanceCalculate(ArrayList <Integer> s) { 
		sc = new Scanner(System.in);
		valist = s;
		namelist.add("�����ڵ�");
		namelist.add("�����ڵ�");		
		namelist.add("���ݼ��ڻ�");
		namelist.add("�����ڻ�");
		namelist.add("��Ÿ�����ڻ�");
		namelist.add("����ä��");
		namelist.add("���Ա�");
		namelist.add("��������");
		namelist.add("��������");
		namelist.add("�������");
		namelist.add("��Ÿ���");
		namelist.add("�������");
		namelist.add("�����ڻ����");
		namelist.add("�����ڻ�ó��");
		namelist.add("��ä��ȯ");
	}
	
	public ArrayList <Integer> getInfo() {
		System.out.println("� value�� �Է����� ���ڷ� �Է����ּ���");
		for (int i=2; i<namelist.size();i++) {
			System.out.println(i-2+". "+namelist.get(i));
		}
		int input = sc.nextInt();
		System.out.println("�Է��� ���� ������ �Է��ϼ���");
		int input2 = sc.nextInt();
		
		switch(input){
		case 1: // ���ݼ��ڻ�
			valist.set(2, valist.get(2)+input2);
			System.out.println("�Է��� ���ݼ��ڻ� "+input2+"�� ���ݼ��ڻ꿡 �߰� �Ǿ����ϴ�");
			break;
		case 2: break;
		case 3: // �����ڻ�
		valist.set(3, valist.get(3)+input2);
		valist.set(2, valist.get(2)-input2);
		System.out.println("�Է��� �����ڻ� "+input2+"�� ��ϵǰ� ���ݼ��ڻ꿡 ���� �Ǿ����ϴ�");
			break; 
		case 4: // ��Ÿ�����ڻ�
			valist.set(4, valist.get(4)+input2);
			valist.set(2, valist.get(2)-input2);
			System.out.println("�Է��� ��Ÿ�����ڻ� "+input2+"�� ��ϵǰ� ���ݼ��ڻ꿡 ���� �Ǿ����ϴ�");
			break;
		case 5: // ����ä��
			valist.set(5, valist.get(5)+input2);
			valist.set(3, valist.get(3)+input2);
			System.out.println("�Է��� ����ä�� "+input2+"�� ��ϵǰ� �����ڻ꿡 �߰� �Ǿ����ϴ�");
			break;
		case 6: // ���Ա�
			valist.set(6, valist.get(6)+input2);
			valist.set(2, valist.get(2)+input2);
			System.out.println("�Է��� ���Ա� "+input2+"�� ��ϵǰ� ���ݼ��ڻ꿡 �߰� �Ǿ����ϴ�");
			break;
		case 7: // ��������
			valist.set(2, valist.get(2)+input2);
			System.out.println("�Է��� �������� "+input2+"�� ���ݼ��ڻ꿡 �߰� �Ǿ����ϴ�");
			break;
		case 8: // ��������
			valist.set(4, valist.get(4)+input2);
			System.out.println("�Է��� �������� "+input2+"�� ��Ÿ�����ڻ꿡 �߰� �Ǿ����ϴ�");
			break;
		case 9: // ��������
			valist.set(2, valist.get(2)-input2);
			System.out.println("�Է��� �������� "+input2+"�� ���ݼ��ڻ꿡 ���� �Ǿ����ϴ�");
			break;
		case 10: // �����ڻ����
			valist.set(2, valist.get(2)-input2);
			valist.set(3, valist.get(3)+input2);
			System.out.println("�Է��� �����ڻ� ��� "+input2+"�� �����ڻ꿡 �߰� �ǰ� ���ݼ��ڻ꿡 ���� �Ǿ����ϴ�");
			break;
		case 11: // �����ڻ�ó��
			valist.set(2, valist.get(2)+input2);
			valist.set(3, valist.get(3)-input2);
			System.out.println("�Է��� �����ڻ� ó�� "+input2+"�� ���ݼ��ڻ꿡 �߰� �ǰ� �����ڻ꿡 ���� �Ǿ����ϴ�");
			break;
		case 12: // ��ä��ȯ
			valist.set(2, valist.get(2)-input2);
			valist.set(6, valist.get(6)-input2);
			System.out.println("�Է��� ��ä��ȯ "+input2+"�� ���Աݿ� ���� �ǰ� ���ݼ��ڻ꿡 ���� �Ǿ����ϴ�");
			break;
		default: System.out.println("�߸��� �� �Է¿� ���� �޼ҵ� ����");
		}
		
		return valist;
	}
	
}
