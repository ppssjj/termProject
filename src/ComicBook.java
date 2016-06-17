import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComicBook extends JFrame {
	Container contentPane;
	String [] comicname={"�巡�ﺼ 1��","����1/2 1��","�̴��߻�1��","��׷��̸� 1��"};//�޺��ڽ� ������
	ImageIcon [] images = {//�̹��� ��ü �迭
			new ImageIcon("images/.dragonball1.jpg"),
			new ImageIcon("images/.ranma1.jpg"),
			new ImageIcon("images/.inuyasha1.jpg"),
			new ImageIcon("images/.dgrayman1.jpg")};
	JLabel imgLabel=new JLabel(images[0]);//�̹��� ���̺� ������Ʈ   ����
	
	ComicBook(){
		setTitle("��ȭå �뿩 ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JComboBox strCombo=new JComboBox(comicname);//�޺��ڽ� ����
		//Action ������ ���
		strCombo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox cb=(JComboBox)e.getSource();//Action �̺�Ʈ�� �߻��� �޺��ڽ� �˾Ƴ���
				
				int index=cb.getSelectedIndex();//�޺��ڽ��� ���õ� �������� �ε��� ��ȣ �˾Ƴ���
				
				imgLabel.setIcon(images[index]);//�ε����� �̹����� �̹��� ���̺� ������Ʈ�� ���
				
				
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
