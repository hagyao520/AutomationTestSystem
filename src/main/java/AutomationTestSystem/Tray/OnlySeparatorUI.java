package AutomationTestSystem.Tray;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public class OnlySeparatorUI extends BasicSeparatorUI {

	private static final Image IMAGE_H = UIBox.getImage(UIBox.key_image_separator_h);
	private static final Image IMAGE_V = UIBox.getImage(UIBox.key_image_separator_v);

	public static ComponentUI createUI(JComponent c) {
		return new OnlySeparatorUI();
	}

	public void paint(Graphics g, JComponent c) {
		Insets insets = c.getInsets();
		int width = c.getWidth() - insets.left - insets.right;
		int height = c.getHeight() - insets.top - insets.bottom;
		Rectangle rect;
		Image image;
		int thickness;

		if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
			image = IMAGE_V;
			thickness = image.getWidth(null);
			rect = new Rectangle(insets.left, insets.top, thickness, height);
		} else {
			image = IMAGE_H;
			thickness = image.getHeight(null);
			rect = new Rectangle(insets.left, insets.top, width, thickness);
		}

		OnlyUIUtil.paintImage(g, image, new Insets(1, 1, 1, 1), rect, c);
	}

	public Dimension getPreferredSize(JComponent c) {
		Dimension size = super.getPreferredSize(c);
		Insets insets = c.getInsets();

		if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
			return new Dimension(IMAGE_V.getWidth(null) + insets.left + insets.right, size.height);
		} else {
			return new Dimension(size.width, IMAGE_H.getHeight(null) + insets.top + insets.bottom);
		}
	}

	protected void installDefaults(JSeparator s) {
	}
}