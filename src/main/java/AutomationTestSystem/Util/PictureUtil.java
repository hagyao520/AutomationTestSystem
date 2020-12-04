package AutomationTestSystem.Util;

import javax.swing.ImageIcon;

public class PictureUtil {

	public static ImageIcon getPicture(String name) {
		ImageIcon icon = new ImageIcon(PictureUtil.class.getClassLoader()
				.getResource("image/" + name));
		return icon;
	}

}
