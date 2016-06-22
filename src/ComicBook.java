import javax.swing.*;

import util.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import java.awt.*;

public class ComicBook extends JFrame {
	Container contentPane;
	JLabel imgLabel; // 이미지를 출력하기 위한 라벨

	private final Images image; // 이미지 관리 클래스
	private final String programTitle; // 프로그램 제목
	private final Dimension size; // 프로그램 크기 정하기

	// 초기 설정, 한번 시작하면 왠만하면 안 바뀌는 옵션들
	public ComicBook() {
		//라벨 초기화
		imgLabel = new JLabel(); 
		// 이미지 등록
		image = new Images().add("드래곤볼 1편", "dragonball1.gif").add("란마1/2 1편", "ranma1.gif")
				.add("이누야샤 1편", "inuyasha1.gif").add("디그레이멘 1화", "dgrayman1.jpg").add("널 보고 있는 듕", "널보고있다.jpg");
		// 프로그램 이름 등록.
		programTitle = "만화책 대여 프로그램";
		// 프로그램 크기 정하기
		size = new Dimension(500, 500);
	}

	// 실행 시작!
	public void run() {
		setTitle(programTitle);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(image.getJComboBox(imgLabel));
		contentPane.add(imgLabel);

		setSize(size);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComicBook().run();
	}
}