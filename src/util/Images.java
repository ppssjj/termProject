package util;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 이미지를 관리하는 클래스.<br /> 기본 이미지 폴더 경로는 프로젝트 폴더의 'images'폴더를 사용하면 됨.
 *
 */
public class Images {
	private HashMap<String, String> imageNames; // 이미지 목록을 저장한다.
	private String imageFolder; // 이미지 폴더의 위치를 지정한다.
	private int image_width;
	private int image_height;

	/**
	 * 이미지 이름을 부를 위치 지정, 없는 경우 images 폴더로 자동지정.
	 */
	public Images() { //
		imageNames = new HashMap<>();// 이미지 목록을 저장하기 위한 백터 컬렉션 객체를 인스턴스화.
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
			throw new IllegalArgumentException("[!setImageSize] : 높이(x축)나 넓이(y축)가 0보다 작습니다");
		this.image_width = width;
		this.image_height = height;
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
	 * @param name
	 *            추가할 이미지의 이름.
	 * @throws IllegalArgumentException
	 *             이미지 이름이 공백이거나 null인 경우 삭제 불가 에러 : 를 띄운다.
	 */
	public Images add(String name, String fileName) {

		// 만약 이미지 이름이 빈 값이면 예외처리 발생.
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("[!add] : 이름이 공백이거나 null입니다.");
		// 이미지 파일 이름이 빈 값이면 예외처리 발생.
		if (fileName == null || fileName.isEmpty())
			throw new IllegalArgumentException("[!add] : 이미지 파일 이름이 공백이거나 null입니다.");

		// 만약 등록하려는 이미지가 이미 등록된 이름과 동일한 경우 등록을 하지 않음.
		if (!(imageNames.get(name)==null)) {
			System.out.println("[!add] : 같은 이름을 가지는 이미지가 등록되어있습니다. 이미지가 추가되지 않았습니다.");
			return this;
		}

		// 실제로 이미지가 있는지 확인
		if (!(new File(imageFolder + "/" + fileName).exists())) {
			System.out.println("[add] : 이미지 " + fileName + "가 없습니다. 이미지가 추가되지 않았습니다.");
			return this;
		}

		// 없는 이미지는 새로 등록.
		imageNames.put(name, fileName);
		System.out.println("add : 이미지 파일 \"" + fileName + "\"이  \n\t \"" + name + "\" 이름으로 등록되었습니다.");
		return this;
	}

	/**
	 * 이미지 이름을 목록에서 삭제한다.
	 * 
	 * @param name
	 *            삭제할 이미지 이름
	 * @throws IllegalArgumentException
	 *             이미지 이름이 공백이거나 null인 경우 삭제 불가 에러 : 를 띄운다.
	 */
	public Images delete(String name) {

		// 만약 imageName이 빈 값이면 에러 : 를 띄우고 프로그램 종료
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("[!del] : 이미지 이름이 공백이거나 null입니다.");
		if (!imageNames.containsKey(name))
			System.out.println("[!del] : 삭제하려는 \"" + name + "\"이 없습니다. 삭제를 하지 못하였습니다.");
		else
			imageNames.remove(name);
		System.out.println("del : \""+name+"\" 이미지가 삭제되었습니다.");
		return this;
	}

	/**
	 * 이미지 파일을 ImageIcon으로 바꾸어 줌
	 * 
	 * @param name
	 *            찾을 이미지 이름
	 * @return ImageIcon
	 */
	public ImageIcon getImageIcon(String name) {
		return new ImageIcon(getImagePath(name));
	}

	/**
	 * 이미지 파일을 JLabel으로 바꾸어 줌
	 * 
	 * @param name
	 *            찾을 이미지 이름
	 * @return JLabel
	 */
	public JLabel getJLabel(String name) {
		return new JLabel(getScaledImage(new ImageIcon(getImagePath(name))));
	}
	
	/**
	 * 이미지 이름을 문자열 배열로 가져옴
	 * 
	 * @return 이미지 이름들을 문자배열 String[]으로 반환
	 */
	public String[] getNames(){
		
		return imageNames.keySet().toArray(new String[0]);
	}
	
	/**
	 * 이미지 크기를 조절, 이미지의 크기는 image_width, image_height의 두 변수에 영향을 받음.
	 * 
	 * @deprecated 작동 안됨 ㅠㅠ
	 * @param srcImgdp
	 *            조작할 이미지
	 */
	private ImageIcon getScaledImage(ImageIcon imageIcon) {
		// return new
		// ImageIcon(imageIcon.getImage().getScaledInstance(image_width,
		// image_height, Image.SCALE_SMOOTH));

		Image image = imageIcon.getImage(); // transform it
		imageIcon = new ImageIcon(image.getScaledInstance(image_width, image_height, Image.SCALE_SMOOTH));

		return imageIcon; // transform it back

	}

	/**
	 * 이미지의 이름을 프로그램이 찾을 수 있게 상대 경로를 반환해줌.
	 * 
	 * @param name
	 *            찾을 이미지의 이름 일부
	 * @return 이미지파일의 상대경로 주소
	 */
	private String getImagePath(String name) {
		// 이미지가 있는지 검색, 만약 발견하는 경우 해당 이미지 제거
		if (!imageNames.containsKey(name)) {
			System.out.println("[!getImage] : 찾으려는 \'" + name + "\'이 없습니다.");
			return imageFolder + "/";
		} else
			return imageFolder + "/" + imageNames.get(name);
	}
	
	/**
	 * 처음 이미지를 저장할 때 만든 폴더 경로를 변경한다.
	 * 
	 * @param imageFolderName
	 *            폴더 경로 변경
	 */
	
	@SuppressWarnings("unused")
	private Images changeFolder(String imageFolderName) {

		// 만약 imageName이 빈 값이면 에러 : 를 띄우고 프로그램 종료
		if (imageFolderName == null || imageFolderName.isEmpty())
			throw new IllegalArgumentException("[!changeFolder] : 폴더 이름이 공백이거나 null입니다.");

		System.out.print("changeFolder : 기존의 폴더 " + this.imageFolder + "에서 ");
		this.imageFolder = imageFolderName;
		System.out.println(this.imageFolder + "로 변경이 되었습니다.");
		return this;
	}
}