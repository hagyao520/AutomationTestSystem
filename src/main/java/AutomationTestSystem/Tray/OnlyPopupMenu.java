package AutomationTestSystem.Tray;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("restriction")
public class OnlyPopupMenu extends JPopupMenu {
	private static final long serialVersionUID = -3145232751724445736L;
	private JWindow heavyWeightWindow;
	private Boolean heavyWeightWindowOpaque;
	private Boolean heavyWeightWindowContentPaneOpaque;
	private boolean buffered;

	public OnlyPopupMenu() {
		this(null);
	}

	public OnlyPopupMenu(String label) {
		super(label);
		setUI(new OnlyPopupMenuUI());
		setOpaque(false);
		setBorder(OnlyUIUtil.isTranslucencySupported() ? new EmptyBorder(7, 7, 7, 7) : new EmptyBorder(2, 2, 2, 2));
	}

	public void paint(Graphics g) {
		if (!OnlyUIUtil.isTranslucencySupported() || !buffered) {
			super.paint(g);
			///System.out.println(1111111);
		} else {
			Insets insets = this.getInsets();
			int x = insets.left;
			int y = insets.top;
			int width = this.getWidth();
			int height = this.getHeight();
			int contentWidth = width - insets.left - insets.right;
			int contentHeight = height - insets.top - insets.bottom;
			BufferedImage image = OnlyUIUtil.getGraphicsConfiguration(this).createCompatibleImage(width, height, Transparency.TRANSLUCENT);
			BufferedImage contentImage = OnlyUIUtil.getGraphicsConfiguration(this).createCompatibleImage(contentWidth, contentHeight, Transparency.OPAQUE);
			Graphics2D g2d = image.createGraphics();
			Graphics2D contentG2d = contentImage.createGraphics();
			contentG2d.translate(-x, -y);
			super.paint(g2d);
			super.paint(contentG2d);
			g2d.dispose();
			contentG2d.dispose();
			g.drawImage(image, 0, 0, this);
			g.drawImage(contentImage, x, y, this);
			///System.out.println(2222222);
		}
	}

	public void setVisible(boolean visible) {
		//System.out.println(88888 + "  " + visible);
		if (visible == isVisible()) {
			return;
		}

		super.setVisible(visible);

		if (OnlyUIUtil.isTranslucencySupported()) {
			if (visible && !isOpaque()) {
				heavyWeightWindow = OnlyUIUtil.getHeavyWeightWindow(this);
				//System.out.println(3333333 + "广告歌" + heavyWeightWindow);
			}

			if (heavyWeightWindow != null) {
				JComponent contentPane = (JComponent) heavyWeightWindow.getContentPane();

				if (visible) {
					buffered = true;
					heavyWeightWindowOpaque = AWTUtilities.isWindowOpaque(heavyWeightWindow);
					heavyWeightWindowContentPaneOpaque = contentPane.isOpaque();

					contentPane.setOpaque(false);
					AWTUtilities.setWindowOpaque(heavyWeightWindow, false);
					//System.out.println(444444);
				} else {
					contentPane.setOpaque(heavyWeightWindowContentPaneOpaque);
					AWTUtilities.setWindowOpaque(heavyWeightWindow, heavyWeightWindowOpaque);
					heavyWeightWindowOpaque = null;
					heavyWeightWindowContentPaneOpaque = null;
					heavyWeightWindow = null;
				//	System.out.println(5555555);
				}
			}
		}
		//System.out.println(666666 + "  " + visible);
	}

	public void addSeparator() {
		add(new OnlyPopupMenu.Separator());
	}

	@Deprecated
	public void updateUI() {
		setUI(this.getUI());
	}

	static public class Separator extends OnlySeparator {
		private static final long serialVersionUID = 3460403283019419638L;

		public Separator() {
			setBorder(new EmptyBorder(1, 26, 0, 2));
		}
	}
}