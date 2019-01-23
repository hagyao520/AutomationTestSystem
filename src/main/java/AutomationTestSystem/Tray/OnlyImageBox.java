/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomationTestSystem.Tray;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Date 2012-8-12 11:10:49
 *
 * @author XiaHui
 */
public class OnlyImageBox {

	public static final String image_path = "Resources/Images/Default/Component/";
	private static final String class_path_images = "/image/";
	private static HashMap<Object, Image> imageMap = new HashMap<Object, Image>();
	private static HashMap<Object, ImageIcon> imageIconMap = new HashMap<Object, ImageIcon>();
	private static HashMap<Object, Icon> iconMap = new HashMap<Object, Icon>();

	public static Icon getIcon(String path, boolean createNew) {
		Icon icon = iconMap.get(path);
		if (null == icon || createNew) {
			icon = new ImageIcon(path);
			iconMap.put(path, icon);
		}
		return icon;
	}

	public static ImageIcon getImageIcon(String path, boolean createNew) {
		ImageIcon imageIcon = imageIconMap.get(path);
		if (null == imageIcon || createNew) {
			imageIcon = new ImageIcon(path);
			imageIconMap.put(path, imageIcon);
		}
		return imageIcon;
	}

	public static Image getImage(String path, boolean createNew) {
		Image image = imageMap.get(path);
		if (null == image || createNew) {
			image = new ImageIcon(path).getImage();
			imageMap.put(path, image);
		}
		return image;
	}

	// ///////////////////////

	public static Icon getIconByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getIcon(path.toString(), createNew);
	}

	public static ImageIcon getImageIconByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getImageIcon(path.toString(), createNew);
	}

	public static Image getImageByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getImage(path.toString(), createNew);
	}

	// ///////////////////
	public static Icon getIconByPath(String path) {
		return getIcon(path, false);
	}

	public static ImageIcon getImageIconByPath(String path) {
		return getImageIcon(path, false);
	}

	public static Image getImageByPath(String path) {
		return getImage(path, false);
	}

	// ///////////////////

	public static Icon getIconByName(String name) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getIcon(path.toString(), false);
	}

	public static ImageIcon getImageIconByName(String name) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getImageIcon(path.toString(), false);
	}

	public static Image getImageByName(String name) {
		StringBuilder path = new StringBuilder(image_path);
		path.append(name);
		return getImage(path.toString(), false);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	public static Icon getIcon(URL url, boolean createNew) {
		Icon icon = iconMap.get(url);
		if (null == icon || createNew) {
			icon = new ImageIcon(url);
			iconMap.put(url, icon);
		}
		return icon;
	}

	public static ImageIcon getImageIcon(URL url, boolean createNew) {
		ImageIcon imageIcon = imageIconMap.get(url);
		if (null == imageIcon || createNew) {
			imageIcon = new ImageIcon(url);
			imageIconMap.put(url, imageIcon);
		}
		return imageIcon;
	}

	public static Image getImage(URL url, boolean createNew) {
		Image image = imageMap.get(url);
		if (null == image || createNew) {
			image = new ImageIcon(url).getImage();
			imageMap.put(url, image);
		}
		return image;
	}

	// /////////////////
	public static Icon getClassPathIconByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(class_path_images);
		path.append(name);
		return getIcon(OnlyImageBox.class.getResource(path.toString()), createNew);
	}

	public static ImageIcon getClassPathImageIconByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(class_path_images);
		path.append(name);
		return getImageIcon(OnlyImageBox.class.getResource(path.toString()), createNew);
	}

	public static Image getClassPathImageByName(String name, boolean createNew) {
		StringBuilder path = new StringBuilder(class_path_images);
		path.append(name);
//		System.out.println(name);
		return getImage(OnlyImageBox.class.getResource(path.toString()), createNew);
	}

	// /////////////////
	public static Icon getClassPathIconByName(String name) {
		return getClassPathIconByName(name, false);
	}

	public static ImageIcon getClassPathImageIconByName(String name) {
		return getClassPathImageIconByName(name, false);
	}

	public static Image getClassPathImageByName(String name) {
		return getClassPathImageByName(name, false);
	}

	public static void main(String age[]) {
		System.out.println(OnlyImageBox.class.getResource("/images/b-d.png"));
	}
}
