package semiProject2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends Frame{
    CardLayout cl;
    Panel cardPanel,p_login,p_main;
    JPanel jp_main_west,jp_login;
    JButton jb_back;

    public Test() {
    	//카드레이아웃 객체 생성
        cl = new CardLayout();
        //카드판넬이라는 판넬 생성 & 판넬 내부 레이아웃을 카드레이아웃으로 설정
        cardPanel = new Panel(cl);
        
        //메인웨스트 클래스의 객체 생성 + 로그인폼 클래스의 객체 생성
        Main_west westbar = new Main_west();
        LoginForm loginp = new LoginForm();
        
        //J판넬인 메인웨스트 객체+로그인 객체 생성
        jp_main_west = new JPanel(new GridLayout());
        jp_login = new JPanel();
        
        //메인웨스트 클레스의 웨스트바 객체의 p_main_west판넬이 담긴 멤버변수를 추가함
        jp_main_west.add(westbar.p_main_west);
        
        //로그인폼 클래스의 판넬 중에 j_window판넬을 가져와서 J판넬에 대입
        jp_login = loginp.j_window;
        
        // Panel login:jp_login을 담을 판넬 생성 후 추가
        p_login = new Panel(new BorderLayout());
        p_login.add(jp_login);
        //로그인폼 클래스의 로그인버튼을 가져와서 액션추가(이벤트 발생시 두 번째 판넬을 보여주겠다는 의미)
        loginp.login_btt.addActionListener(e -> cl.show(cardPanel, "Main"));

        // Panel main메뉴: jp_main_west를 추가할 메인판넬 생성 후 추가
        p_main = new Panel(new BorderLayout()); 
        p_main.add(jp_main_west,"West");
        jb_back = new JButton("뒤로 가기"); //뒤로가기 버튼 생성
        jb_back.addActionListener(e -> cl.show(cardPanel, "Login")); //버튼 누르면 이전 판넬로 전환되도록
        p_main.add(jb_back,"North"); //생성해놓은 버튼 추가

        // 로그인 화면을 카드레이아웃으로 설정된 카드판넬에 로그인으로 추가 
        cardPanel.add(p_login, "Login");
        // 메인 화면을 카드레이아웃으로 설정된 카드판넬에 메인으로 추가 
        cardPanel.add(p_main, "Main");

        //카드판넬을 프레임에 추가
        this.add(cardPanel);
        
        //로그인 판넬을 먼저 보여주기
        cl.show(cardPanel, "Login");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.setSize(800, 600);
        t.setVisible(true);
    }
}

