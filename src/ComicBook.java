import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComicBook extends JFrame {
	Container contentPane;
	String [] comicname={"드래곤볼 1권","란마1/2 1권","이누야샤1권","디그레이맨 1권"};//콤보박스 아이템
	ImageIcon [] images = {//이미지 객체 배열
			new ImageIcon("images/.dragonball1.jpg"),
			new ImageIcon("images/.ranma1.jpg"),
			new ImageIcon("images/.inuyasha1.jpg"),
			new ImageIcon("images/.dgrayman1.jpg")};
	JLabel imgLabel=new JLabel(images[0]);//이미지 레이블 컴포넌트   생성
	
	ComicBook(){
		setTitle("만화책 대여 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JComboBox strCombo=new JComboBox(comicname);//콤보박스 생성
		//Action 리스너 등록
		strCombo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb=(JComboBox)e.getSource();//Action 이벤트가 발생한 콤보박스 알아내기
				
				int index=cb.getSelectedIndex();//콤보박스의 선택된 아이템의 인덱스 번호 알아내기
				
				imgLabel.setIcon(images[index]);//인덱스의 이미지를 이미지 레이블 컴포넌트에 출력
				
				
			}
		});
		contentPane.add(strCombo);
		contentPane.add(imgLabel);
		
		setSize(300,250);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ComicBook();
	}
}
