import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import java.awt.*;

/**
 * 이미지를 관리하는 클래스. 벡터로 이미지 목록을 관리한다. 기본 이미지 폴더 경로는 프로젝트 폴더의 'images'폴더이다.
 *
 */
class Images {
	private Vector<String> imageNames; // 이미지 목록을 저장한다.
	private String imageFolder; // 이미지 폴더의 위치를 지정한다.
	private int image_width;
	private int image_height;

	/**
	 * 이미지 이름을 부를 위치 지정, 없는 경우 images 폴더로 자동지정.
	 */
	public Images() { //
		imageNames = new Vector<>();// 이미지 목록을 저장하기 위한 백터 컬렉션 객체를 인스턴스화.
		imageFolder = "./images";
		image_width = image_height = 100;
	}

	/**
	 * 이미지의 크기를 정하는 함수.
	 * 
	 * @param width
	 *            넓이(x축)
	 * @param height
	 *            높이(y축)
	 * @throws IllegalArgumentException
	 *             높이 넓이가 음수인 경우, 에러 : 를 표시
	 */
	public Images setImageSize(int width, int height) {
		if (width < 0 || height < 0)
			throw new IllegalArgumentException("높이(x축)나 넓이(y축)가 0보다 작습니다");
		this.image_width = width;
		this.image_height = height;
		return this;
	}

	/**
	 * 처음 이미지를 저장할 때 만든 폴더 경로를 변경한다.
	 * 
	 * @param imageFolderName
	 *            폴더 경로 변경
	 */
	public Images changeFolder( String imageFolderName) {

		// 만약 imageName이 빈 값이면 에러 : 를 띄우고 프로그램 종료
		if ( imageFolderName == null || imageFolderName.isEmpty())
			throw new IllegalArgumentException("에러 :  changeFolder : 폴더 이름이 공백이거나 null입니다.");

		System.out.print("changeFolder : 기존의 폴더 " + this.imageFolder + "에서 ");
		this.imageFolder = imageFolderName;
		System.out.println(this.imageFolder + "로 변경이 되었습니다.");
		return this;
	}

	/**
	 * image 폴더에 있는 이미지의 이름을 등록해주는 함수.
	 * <p>
	 * 사용방법 <br />
	 * 마침표 (.)를 사용하여 연속적으로 삭제할 수있다.<br />
	 * <code>Images img = new Images();<br />
	 * img.<b>add</b>("picture_1.jpg").<b>add</b>("picture_2.jpg").<b>add</b>
	 * ("picture_3.jpg");<code>
	 * </p>
	 * 
	 * @param imageName
	 *            추가할 이미지의 이름.
	 * @throws IllegalArgumentException
	 *             이미지 이름이 공백이거나 null인 경우 삭제 불가 에러 : 를 띄운다.
	 */
	public Images add(String imageName) {

		// 만약 imageName이 빈 값이면 에러 : 를 띄우고 프로그램 종료
		if (imageName == null || imageName.isEmpty())
			new IllegalArgumentException("에러 :  add : 이미지 이름이 공백이거나 null입니다.");

		// 만약 등록하려는 이미지가 이미 등록된 이름과 동일한 경우 등록을 하지 않음.
		if (imageNames.contains(imageName)) {
			System.out.println("에러 :  add : 같은 이름을 가지는 이미지가 등록되어있습니다. 이미지가 추가되지 않았습니다.");
			return this;
		}
		
		if(!(new File(getImagePath(imageName)).exists())){//여기가 오작동을 일으키는데.... 있는데 없다고 구라침 
			System.out.println("경고 : add : 이미지 "+getImagePath(imageName)+"가 없습니다. 이미지가 추가되지 않았습니다.");
			return this;
		}

		// 없는 이미지는 새로 등록.
		imageNames.addElement(imageName);
		System.out.println("add : " + imageName + "이 등록되었습니다.");
		return this;
	}

	/**
	 * 이미지 이름을 목록에서 삭제한다.
	 * <p>
	 * 사용방법 <br />
	 * 마침표 (.)를 사용하여 연속적으로 삭제할 수있다.<br />
	 * <code>Images img = new Images();<br />
	 * img.<b>delete</b>("picture_1.jpg").<b>delete</b>("picture_2.jpg").
	 * <b>delete</b>("picture_3.jpg");<code>
	 * </p>
	 * 
	 * @param imageName
	 *            삭제할 이미지 이름
	 * @throws IllegalArgumentException
	 *             이미지 이름이 공백이거나 null인 경우 삭제 불가 에러 : 를 띄운다.
	 */
	public Images delete(String imageName) {

		// 만약 imageName이 빈 값이면 에러 : 를 띄우고 프로그램 종료
		if (imageName == null || imageName.isEmpty())
			throw new IllegalArgumentException("에러 :  del : 이미지 이름이 공백이거나 null입니다.");

		// 이미지가 있는지 검색, 만약 발견하는 경우 해당 이미지 제거
		for (int i = 0; i < imageNames.size(); i++) {
			if (imageNames.get(i).contains(imageName)) {
				imageNames.remove(i);
				System.out.println("del : " + imageName + "이 제거되었습니다.");
				break;
			}
			System.out.println("에러 :  del : 삭제하려는 \'" + imageName + "\'이 없습니다. 삭제를 하지 못하였습니다.");
		}
		return this;
	}

	/**
	 * 이미지 크기를 조절, 이미지의 크기는 image_width, image_height의 두 변수에 영향을 받음.
	 * 
	 * @param srcImg
	 *            조작할 이미지
	 */
	private ImageIcon getScaledImage(ImageIcon imageIcon) {
		BufferedImage resizedImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(imageIcon.getImage(), 0, 0, image_width, image_height, null);
		g2.dispose();

		return new ImageIcon(resizedImg);
	}

	/**
	 * 이미지의 전체 경로를 반환해줌.
	 * 일부만 맞아도 가장 먼저 발견하는 이미지 이름을 반환.
	 * <p>예제 <br />
	 * abcd.jpg abc.jpg, bbb.jpg 순서대로 이미지를 등록한 경우 <br />
	 * getImagepath(<b>"ab"</b>); 의 결과는 <br />
	 * abcd.jpg를 돌려주고 끝남.
	 * </p>
	 * 
	 * @param imageName
	 *            찾을 이미지의 이름 일부
	 */
	private String getImagePath(String imageName) {
		// 이미지가 있는지 검색, 만약 발견하는 경우 해당 이미지 제거
		for (int i = 0; i < imageNames.size(); i++) {
			if (imageNames.get(i).contains(imageName))
				return imageFolder + "/" + imageNames.get(i);
		}
		System.out.println("에러 :  getImage : 찾으려는 \'" + imageName + "\'이 없습니다. 찾기를 실패하였습니다.");
		return imageFolder + "/";
	}

	/**
	 * 이미지의 전체 경로를 반환해줌. 
	 * 
	 * @param imageNumber
	 *            찾을 이미지 번호
	 */
	private String getImagePath(int imageNumber) {
		// 이미지가 있는지 검색, 만약 발견하는 경우 해당 이미지 제거

		if (!(imageNames.get(imageNumber) == null))
			return imageFolder + "/" + imageNames.get(imageNumber);

		System.out.println("에러 :  getImage : 찾으려는 \'" + imageNumber + "\'가 없습니다. 찾기를 실패하였습니다.");
		return imageFolder + "/";
	}

	public JLabel getJLabel(String imageName) {
		return new JLabel(getScaledImage(new ImageIcon(getImagePath(imageName))));
	}

	public JLabel getJLabel(int imageNumber) {
		return new JLabel(getScaledImage(new ImageIcon(getImagePath(imageNumber))));
	}

	/**
	 * {@link add}함수를 사용하여 Vector에 등록된 이미지를 이미지 아이콘으로 반환하는 함수. 등록된 번호로 이미지를 찾음 
	 * 
	 * @param imageNumber
	 *            찾을 이미지 번호
	 * @return ImageIcon으로 반환
	 */
	public ImageIcon getImageIcon(int imageNumber) {
		
		if(imageNumber <0)
			throw new IllegalArgumentException("에러 :  getImageIcon : 0보다 큰 수여야합니다.");
		return new ImageIcon(getImagePath(imageNumber));
	}

	/**
	 * {@link add}함수를 사용하여 Vector에 등록된 이미지를 이미지 아이콘으로 반환하는 함수. 이름으로 이미지를 찾음
	 * 
	 * @param imageName
	 *            찾을 이미지 이름
	 * @return ImageIcon으로 반환
	 */
	public ImageIcon getImageIcon(String imageName) {
		return new ImageIcon(getImagePath(imageName));
	}
}

public class ComicBook extends JFrame {
	Container contentPane;
	String[] comicname;
	Images image;
	JLabel imgLabel;

	ComicBook() {
		comicname = new String[] { "드래곤볼 1편", "란마1/2 1편", "이누야샤 1편", "디그레이멘 1화" };
		image = new Images().add("dragonball1.png").add("ranma1.jpg").add("inuyasha1.jpg").add("dgrayman1.jpg");

		setTitle("만화책 대여 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JComboBox strCombo = new JComboBox(comicname);
		imgLabel = new JLabel();
		strCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(image.getImageIcon(index));

			}
		});
		contentPane.add(strCombo);
		contentPane.add(imgLabel);

		setSize(500, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComicBook();
	}
}
