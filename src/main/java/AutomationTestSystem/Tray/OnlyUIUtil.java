package AutomationTestSystem.Tray;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Transparency;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import sun.awt.AppContext;
import sun.awt.shell.ShellFolder;
import sun.swing.SwingUtilities2;
import sun.swing.SwingUtilities2.AATextInfo;

import com.sun.awt.AWTUtilities;
import com.sun.awt.AWTUtilities.Translucency;

/**
 * @author King
 * @date 2018年8月6日 10:34:22
 */
@SuppressWarnings("restriction")
public class OnlyUIUtil {

	/**
	 * JComponent的子类中绘制字体时使用的默认文本渲染参数，构造方法中加入如下代码即可<br>
	 * <code>putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, UIUtil.COMMON_AATEXT_INFO);</code>
	 * <br>
	 * 该值的来源：BasicLookAndFeel的子类中，方法initComponentDefaults利用类似如下的代码将其注册到UI中<br>
	 * <code>Object aaTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(true);<br>
	 * table.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, aaTextInfo);</code>
	 */

	public static final AATextInfo COMMON_AATEXT_INFO =  (AATextInfo) UIManager.getLookAndFeel().getDefaults().get(SwingUtilities2.AA_TEXT_PROPERTY_KEY);
	/**
	 * 移动窗口时限制其左侧不超出屏幕
	 */
	public static final int LOCATION_LIMIT_LEFT = 1;
	/**
	 * 移动窗口时限制其右侧不超出屏幕
	 */
	public static final int LOCATION_LIMIT_RIGHT = 1 << 1;
	/**
	 * 移动窗口时限制其上方不超出屏幕
	 */
	public static final int LOCATION_LIMIT_TOP = 1 << 2;
	/**
	 * 移动窗口时限制其下方不超出屏幕
	 */
	public static final int LOCATION_LIMIT_BOTTOM = 1 << 3;
	/**
	 * 移动窗口时限制其四周均不超出屏幕
	 */
	public static final int LOCATION_LIMIT_ALL = LOCATION_LIMIT_LEFT | LOCATION_LIMIT_RIGHT | LOCATION_LIMIT_TOP | LOCATION_LIMIT_BOTTOM;
	/**
	 * 操作系统是否支持形状
	 */
	private static Boolean shapeSupported;
	/**
	 * 操作系统是否支持半透明
	 */
	private static Boolean translucencySupported;
	/**
	 * 创建随机颜色组时用的RGB因子
	 */
	private static int[] colorFactors;

	/**
	 * 为RootPaneContainer组件添加键盘事件
	 *
	 * @param root
	 *            RootPaneContainer组件
	 * @param action
	 *            需要执行的动作
	 * @param keyName
	 *            键的名称，原则上可以是任意字符串，实际应用时建议使用有意义的字符串
	 * @param keyCode
	 *            键的数字代码，比如KeyEvent.VK_T
	 * @param modifiers
	 *            任意修饰符的按位或组合，比如Ctrl+Shift为KeyEvent.CTRL_MASK |
	 *            KeyEvent.SHIFT_MASK
	 */
	public static void registerKeyEvent(RootPaneContainer root, Action action, String keyName, int keyCode, int modifiers) {
		if (root != null) {
			JRootPane rootPane = root.getRootPane();
			InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(KeyStroke.getKeyStroke(keyCode, modifiers), keyName);
			rootPane.getActionMap().put(keyName, action);
		}
	}

	/**
	 * 为指定窗体组件设置缺省按钮（按下Enter时响应），并添加Escape事件（按下Esc时响应）
	 *
	 * @param root
	 *            RootPaneContainer 窗体组件
	 * @param defaultButton
	 *            JButton 缺省按钮（按下Enter时响应）
	 * @param escAction
	 *            Action Escape事件（按下Esc时响应）
	 */
	public static void escAndEnterAction(RootPaneContainer root, JButton defaultButton, Action escAction) {
		if (root != null) {
			registerKeyEvent(root, escAction, "ESC", KeyEvent.VK_ESCAPE, 0);

			if (defaultButton != null) {
				root.getRootPane().setDefaultButton(defaultButton);
			}
		}
	}

	/**
	 * 设置所有UI控件的字体
	 *
	 * @param font
	 *            字体对象
	 */
	public static void setUIFont(Font font) {

		Enumeration<?> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);

			if (value instanceof Font) {
				UIManager.put(key, font);
			}
		}
	}

	/**
	 * 根据JMenuItem获取它所对应的根JPopupMenu
	 *
	 * @param item
	 *            JMenuItem
	 * @return JPopupMenu
	 */
	public static JPopupMenu getPopupMenuByItem(JMenuItem item) {
		JPopupMenu menu = (JPopupMenu) item.getParent();
		Component invoker = menu.getInvoker();

		while (invoker != null && invoker instanceof JMenuItem) {
			menu = (JPopupMenu) invoker.getParent();
			invoker = menu.getInvoker();
		}

		return menu;
	}

	/**
	 * 通过Component获取它所在的窗口
	 *
	 * @param component
	 *            Component
	 * @return Window
	 */
	public static Window getWindowFromComponent(Component component) {
		if (component instanceof JMenuItem) {
			Container container = component.getParent();
			component = ((JPopupMenu) container).getInvoker();

			while (component != null && component instanceof JMenuItem) {
				container = component.getParent();

				if (container instanceof JMenuBar) {
					component = container;
					break;
				} else {
					component = ((JPopupMenu) container).getInvoker();
				}
			}
		} else if (component instanceof JPopupMenu) {
			component = ((JPopupMenu) component).getInvoker();
		}

		while (component != null && !(component instanceof Window)) {
			component = component.getParent();
		}

		return component == null ? null : (Window) component;
	}

	/**
	 * 通过Component获取它所在的RootPaneContainer
	 *
	 * @param component
	 *            Component
	 * @return RootPaneContainer
	 */
	public static RootPaneContainer getRootPaneContainerFromComponent(Component component) {
		if (component instanceof JMenuItem) {
			Container container = component.getParent();
			component = ((JPopupMenu) container).getInvoker();

			while (component != null && component instanceof JMenuItem) {
				container = component.getParent();

				if (container instanceof JMenuBar) {
					component = container;
					break;
				} else {
					component = ((JPopupMenu) container).getInvoker();
				}
			}
		} else if (component instanceof JPopupMenu) {
			component = ((JPopupMenu) component).getInvoker();
		}

		while (component != null && !(component instanceof RootPaneContainer)) {
			component = component.getParent();
		}

		return component == null ? null : (RootPaneContainer) component;
	}

	/**
	 * 通过Component获取它所在的JRootPane
	 *
	 * @param component
	 *            Component
	 * @return JRootPane
	 */
	public static JRootPane getRootPaneFromComponent(Component component) {
		RootPaneContainer container = getRootPaneContainerFromComponent(component);
		return container == null ? null : container.getRootPane();
	}

	/**
	 * 强制显示组件的ToolTip
	 *
	 * @param component
	 *            目标组件
	 */
	public static void showToolTip(JComponent component) {
		setToolTipVisible(component, true);
	}

	/**
	 * 强制隐藏组件的ToolTip
	 *
	 * @param component
	 *            目标组件
	 */
	public static void hideToolTip(JComponent component) {
		setToolTipVisible(component, false);
	}

	/**
	 * 设置组件ToolTip的可见性
	 *
	 * @param component
	 *            目标组件
	 * @param visible
	 *            ToolTip是否可见
	 */
	private static void setToolTipVisible(JComponent component, boolean visible) {
		String actionName = visible ? "postTip" : "hideTip";
		Action action = component.getActionMap().get(actionName);

		if (action != null) {
			ActionEvent event = new ActionEvent(component, ActionEvent.ACTION_PERFORMED, actionName, EventQueue.getMostRecentEventTime(), 0);
			action.actionPerformed(event);
		}
	}

	/**
	 * 旋转图像
	 *
	 * @param image
	 *            源图像
	 * @param angle
	 *            旋转角度
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return 旋转后的图像
	 */
	public static Image imageRotate(Image image, double angle, Component c) {
		double radians = Math.toRadians(angle % 360);
		int oldWidth = image.getWidth(null);
		int oldHeight = image.getHeight(null);
		int newWidth = (int) (Math.abs(Math.cos(radians) * oldWidth) + Math.abs(Math.sin(radians) * oldHeight));
		int newHeight = (int) (Math.abs(Math.sin(radians) * oldWidth) + Math.abs(Math.cos(radians) * oldHeight));
		int centerX = newWidth / 2;
		int centerY = newHeight / 2;
		BufferedImage bufferedImage = getGraphicsConfiguration(c).createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.rotate(radians, centerX, centerY);
		g2d.drawImage(image, (newWidth - oldWidth) / 2, (newHeight - oldHeight) / 2, null);
		g2d.rotate(-radians, centerX, centerY);
		g2d.dispose();
		return bufferedImage;
	}

	/**
	 * 将Image对象转化成BufferedImage
	 *
	 * @param image
	 *            Image对象
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image image, Component c) {
		return toBufferedImage(image, 1.0f, c);
	}

	/**
	 * 将Image对象转化成BufferedImage
	 *
	 * @param image
	 *            Image对象
	 * @param alpha
	 *            透明度
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image image, float alpha, Component c) {
		BufferedImage bufferedImage;

		if (image instanceof BufferedImage && !(alpha >= 0.0 && alpha < 1.0)) {
			bufferedImage = (BufferedImage) image;
		} else {
			bufferedImage = getGraphicsConfiguration(c).createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.TRANSLUCENT);
			Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

			if (alpha >= 0.0 && alpha < 1.0) {
				g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
			}

			g2d.drawImage(image, 0, 0, null);
			g2d.dispose();
		}

		return bufferedImage;
	}

	/**
	 * 将Icon对象转化成BufferedImage
	 *
	 * @param icon
	 *            Icon对象
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Icon icon, Component c) {
		return toBufferedImage(icon, 1.0f, c);
	}

	/**
	 * 将Icon对象转化成BufferedImage
	 *
	 * @param icon
	 *            Icon对象
	 * @param alpha
	 *            透明度
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Icon icon, float alpha, Component c) {
		BufferedImage bufferedImage = getGraphicsConfiguration(c).createCompatibleImage(icon.getIconWidth(), icon.getIconHeight(), Transparency.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

		if (alpha >= 0.0 && alpha < 1.0) {
			g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
		}

		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();
		return bufferedImage;
	}

	/**
	 * 裁剪图像
	 *
	 * @param image
	 *            原图像
	 * @param rect
	 *            裁剪后所需要的矩形
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return 裁剪后的图像
	 */
	public static BufferedImage cutImage(Image image, Rectangle rect, Component c) {
		BufferedImage bufferedImage = getGraphicsConfiguration(c).createCompatibleImage(rect.width, rect.height, Transparency.TRANSLUCENT);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(image, 0, 0, rect.width, rect.height, rect.x, rect.y, rect.width + rect.x, rect.height + rect.y, null);
		g.dispose();
		return bufferedImage;
	}

	/**
	 * 缩放图像
	 *
	 * @param image
	 *            原图像
	 * @param scale
	 *            缩放比例
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return 缩放后的图像
	 */
	public static BufferedImage zoomImage(Image image, float scale, Component c) {
		int width = Math.round(image.getWidth(null) * scale);
		int height = Math.round(image.getHeight(null) * scale);
		BufferedImage bufferedImage = getGraphicsConfiguration(c).createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bufferedImage;
	}

	/**
	 * 获取图像的类型
	 *
	 * @param obj
	 *            图像源
	 * @return 图像的类型
	 */
	public static String getImageFormat(Object obj) {
		String format = "unknown";

		try {
			ImageInputStream input = ImageIO.createImageInputStream(obj);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(input);
			input.close();

			if (iter.hasNext()) {
				format = iter.next().getFormatName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return format;
	}

	/**
	 * 根据文件扩展名获取对应的文件图标
	 *
	 * @param format
	 *            文件扩展名，为空时返回文件夹图标
	 * @param big
	 *            是否是大图标
	 * @return 图标
	 */
	public static Icon getFileIcon(String format, boolean big) {
		Icon icon = null;
		File file = null;

		if (OnlyFeelUtil.isEmpty(format)) {
			file = new File(System.getProperty("java.io.tmpdir") + "//icon.temp");
			file.mkdir();
		} else {
			try {
				file = File.createTempFile("icon.temp", '.' + format);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (file != null) {
			icon = getFileIcon(file, big);
			file.delete();
		}

		return icon;
	}

	/**
	 * 获取文件的图标
	 *
	 * @param file
	 *            目标文件
	 * @param big
	 *            是否是大图标
	 * @return 图标
	 */
	public static Icon getFileIcon(File file, boolean big) {
		Icon icon = null;
		ShellFolder folder;

		if (file != null) {
			try {
				folder = ShellFolder.getShellFolder(file);
				Image image = folder.getIcon(big);

				if (image != null) {
					icon = new ImageIcon(image, folder.getFolderType());
				} else {
					icon = UIManager.getIcon(file.isDirectory() ? "FileView.directoryIcon" : "FileView.fileIcon");
				}
			} catch (FileNotFoundException e) {
				icon = null;
			}
		}

		return icon;
	}

	/**
	 * 获取缺省字体
	 *
	 * @return 缺省字体
	 */
	public static Font getDefaultFont() {
		String osVersion = System.getProperty("os.version");
		Font font;

		if (OnlyFeelUtil.isWindows()) {
			if (osVersion.compareTo("6") >= 0) {
				font = new Font("微软雅黑", Font.PLAIN, 12);
			} else {
				font = new Font("微软雅黑", Font.PLAIN, 12);
				if (!"微软雅黑".equals(font.getFontName())) {
					font = new Font("宋体", Font.PLAIN, 12);
				}
			}
		} else {
			font = new Font(Font.DIALOG, Font.PLAIN, 12);
		}

		return font;
	}

	/**
	 * 获取缺省字体
	 *
	 * @return 缺省字体
	 */
	public static Font getDefaultFont(int size) {
		String osVersion = System.getProperty("os.version");
		Font font;

		if (OnlyFeelUtil.isWindows()) {
			if (osVersion.compareTo("6") >= 0) {
				font = new Font("微软雅黑", Font.PLAIN, size);
			} else {
				font = new Font("微软雅黑", Font.PLAIN, size);
				if (!"微软雅黑".equals(font.getFontName())) {
					font = new Font("宋体", Font.PLAIN, size);
				}
			}
		} else {
			font = new Font(Font.DIALOG, Font.PLAIN, size);
		}

		return font;
	}

	/**
	 * 获取缺省字体
	 *
	 * @return 缺省字体
	 */
	public static Font getDefaultFont(int style, int size) {
		String osVersion = System.getProperty("os.version");
		Font font;

		if (OnlyFeelUtil.isWindows()) {
			if (osVersion.compareTo("6") >= 0) {
				font = new Font("微软雅黑", style, size);
			} else {
				font = new Font("微软雅黑", style, size);
				if (!"微软雅黑".equals(font.getFontName())) {
					font = new Font("宋体", style, size);
				}
			}
		} else {
			font = new Font(Font.DIALOG, style, size);
		}
		return font;
	}

	/**
	 * 获取窗体标题字体
	 *
	 * @param isCH
	 *            是否为中文字体
	 * @return 窗体标题字体
	 */
	public static Font getTitleFont(boolean isCH) {
		String osVersion = System.getProperty("os.version");
		Font font;

		if (OnlyFeelUtil.isWindows()) {
			if (osVersion.compareTo("6") >= 0) {
				font = new Font("微软雅黑", Font.BOLD, isCH ? 13 : 12);
			} else {
				if (isCH) {
					font = new Font("宋体", Font.BOLD, 13);
				} else {
					font = new Font("Tahoma", Font.BOLD, 12);
				}
			}
		} else {
			font = new Font(Font.DIALOG, Font.BOLD, isCH ? 13 : 12);
		}

		return font;
	}

	/**
	 * 绘制图像
	 *
	 * @param g
	 *            画笔
	 * @param image
	 *            需要绘制的图像对象
	 * @param imageInsets
	 *            图像的边框
	 * @param paintRect
	 *            组件上需要绘制的区域
	 * @param observer
	 *            等待加载图像的对象
	 */
	public static void paintImage(Graphics g, Image image, Insets imageInsets, Rectangle paintRect, ImageObserver observer) {
		int x = paintRect.x;
		int y = paintRect.y;
		int width = paintRect.width;
		int height = paintRect.height;

		if (!(width > 0 && height > 0 && x + width > 0 && y + height > 0)) {
			return;
		}

		Graphics2D g2d = (Graphics2D) g;
		Object oldHintValue = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		int imageLeft = imageInsets.left;
		int imageRight = imageInsets.right;
		int imageTop = imageInsets.top;
		int imageBottom = imageInsets.bottom;
		int imageWidth = image.getWidth(observer);
		int imageHeight = image.getHeight(observer);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);
		// Top
		g2d.drawImage(image, 0, 0, imageLeft, imageTop, 0, 0, imageLeft, imageTop, observer);
		g2d.drawImage(image, imageLeft, 0, width - imageRight, imageTop, imageLeft, 0, imageWidth - imageRight, imageTop, observer);
		g2d.drawImage(image, width - imageRight, 0, width, imageTop, imageWidth - imageRight, 0, imageWidth, imageTop, observer);

		// Middle
		g2d.drawImage(image, 0, imageTop, imageLeft, height - imageBottom, 0, imageTop, imageLeft, imageHeight - imageBottom, observer);
		g2d.drawImage(image, imageLeft, imageTop, width - imageRight, height - imageBottom, imageLeft, imageTop, imageWidth - imageRight, imageHeight - imageBottom, observer);
		g2d.drawImage(image, width - imageRight, imageTop, width, height - imageBottom, imageWidth - imageRight, imageTop, imageWidth, imageHeight - imageBottom, observer);

		// Bottom
		g2d.drawImage(image, 0, height - imageBottom, imageLeft, height, 0, imageHeight - imageBottom, imageLeft, imageHeight, observer);
		g2d.drawImage(image, imageLeft, height - imageBottom, width - imageRight, height, imageLeft, imageHeight - imageBottom, imageWidth - imageRight, imageHeight, observer);
		g2d.drawImage(image, width - imageRight, height - imageBottom, width, height, imageWidth - imageRight, imageHeight - imageBottom, imageWidth, imageHeight, observer);
		g2d.translate(-x, -y);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldHintValue);
	}

	/**
	 * 绘制背景颜色和背景图像
	 *
	 * @param g
	 *            画笔
	 * @param c
	 *            组件
	 * @param background
	 *            背景颜色
	 * @param disabledBackground
	 *            禁用时的背景颜色
	 * @param image
	 *            背景图像
	 * @param imageOnly
	 *            是否只显示图像
	 * @param alpha
	 *            不透明度
	 * @param visibleInsets
	 *            背景边框
	 */
	public static void paintBackground(Graphics g, JComponent c, Color background, Color disabledBackground, Image image, boolean imageOnly, float alpha, Insets visibleInsets) {
		if (alpha > 0.0 && !(imageOnly && image == null)) {
			Graphics2D g2d = (Graphics2D) g;
			Composite oldComposite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));

			if (!imageOnly) {
				Color oldColor = g2d.getColor();
				g2d.setColor(c.isEnabled() ? background : disabledBackground);
				g2d.fillRect(visibleInsets.left, visibleInsets.top, c.getWidth() - visibleInsets.left - visibleInsets.right, c.getHeight() - visibleInsets.top - visibleInsets.bottom);
				g2d.setColor(oldColor);
			}

			if (image != null) {
				Object oldHintValue = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.drawImage(image, visibleInsets.left, visibleInsets.top, c.getWidth() - visibleInsets.left - visibleInsets.right, c.getHeight() - visibleInsets.top - visibleInsets.bottom, c);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldHintValue);
			}

			g2d.setComposite(oldComposite);
		}
	}

	/**
	 * 隐藏输入中文时的浮动小方框
	 */
	public static void hideInputRect() {
		System.setProperty("java.awt.im.style", "on-the-spot");
	}

	/**
	 * consume为true时，某些情况下JPopupMenu弹出后再点击别的JButton，则该JButton不会响应事件，需要再点击一次
	 * WindowsLookAndFeel中默认为true，MetalLookAndFeel中默认为false
	 *
	 * @param consume
	 */
	public static void setPopupMenuConsumeEventOnClose(boolean consume) {
		UIManager.put("PopupMenu.consumeEventOnClose", consume);
	}

	/**
	 * 初始化ToolTip为操作系统风格
	 */
	public static void initToolTipForSystemStyle() {
		try {
			String className = UIManager.getSystemLookAndFeelClassName();
			Class<?> clazz = Class.forName(className);
			LookAndFeel laf = (LookAndFeel) clazz.newInstance();
			Enumeration<Object> keys = laf.getDefaults().keys();
			Object key;

			while (keys.hasMoreElements()) {
				key = keys.nextElement();

				if (key.toString().startsWith("ToolTip.")) {
					UIManager.getDefaults().put(key, laf.getDefaults().get(key));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取图像高斯模糊的滤镜
	 *
	 * @param radius
	 *            模糊半径
	 * @param horizontal
	 *            是否为水平方向
	 * @return 高斯模糊滤镜
	 */
	public static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal) {
		if (radius < 1) {
			throw new IllegalArgumentException("Radius must be >= 1");
		}

		float sigmaRoot = (float) Math.sqrt(2.0 * Math.PI);


		int size = radius * 2 + 1;
	    float[] data = new float[size];
	    float sigma = (float)radius / 3.0f;
	    float sigma2 = 2.0f * sigma * sigma;
	    float sigmap = sigma * sigmaRoot;

	    System.out.println("size="+size);
	    for(int n = 0, i = -radius; i <= radius; i++, n++)  {
	    	System.out.println("n="+n);
	    	data[n]  = (float) Math.exp(-(float)(i * i) / sigma2) / sigmap;
	    }


//
//		int size = radius * 2 + 1;
//		float[] data = new float[size];
//		float sigma = radius / 3.0f;
//		float twoSigmaSquare = 2.0f * sigma * sigma;
//		float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
//		float total = 0.0f;
//
//		for (int i = -radius; i <= radius; i++) {
//			float distance = i * i;
//			int index = i + radius;
//			data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
//			total += data[index];
//		}
//
//		for (int i = 0; i < data.length; i++) {
//			data[i] /= total;
//		}

		Kernel kernel = horizontal ? new Kernel(size, 1, data) : new Kernel(1, size, data);

		return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		//return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	}

	/**
	 * 根据指定的图像创建TexturePaint
	 *
	 * @param image
	 *            目标图像
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return 创建的TexturePaint
	 */
	public static TexturePaint createTexturePaint(Image image, Component c) {
		BufferedImage bufferedImage = toBufferedImage(image, c);
		return new TexturePaint(bufferedImage, new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
	}

	/**
	 * 创建右边缘和下边缘模糊的图像
	 *
	 * @param image
	 *            源图像
	 * @param blurSize
	 *            模糊宽度（下边缘指高度）
	 * @param blurFilter
	 *            模糊滤镜
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return 经过边缘模糊处理过的图像
	 */
	public static BufferedImage createEdgeBlurryImage(Image image, int blurSize, BufferedImageOp blurFilter, Component c) {
		GraphicsConfiguration gc = getGraphicsConfiguration(c);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int imageMaxSize = Math.max(width, height);
		float scale = imageMaxSize / 600.0f;
		int zoomWidth = scale > 1.0f ? Math.round(width / scale) : width;
		int zoomHeight = scale > 1.0f ? Math.round(height / scale) : height;
		BufferedImage bufferedImage = OnlyUIUtil.toBufferedImage(image, c);
		BufferedImage blurImage = gc.createCompatibleImage(zoomWidth, zoomHeight, Transparency.TRANSLUCENT);
		BufferedImage edgeBlurImage = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		Graphics2D g2d = blurImage.createGraphics();
		g2d.drawImage(scale > 1.0f ? zoomImage(bufferedImage, 1 / scale, c) : bufferedImage, blurFilter, 0, 0);
		g2d.dispose();
		blurImage = scale > 1.0f ? zoomImage(blurImage, scale, c) : blurImage;
		g2d = edgeBlurImage.createGraphics();
		float alphaStep = 1.0f / blurSize;
		int x = width - blurSize;
		int y = height - blurSize;
		g2d.drawImage(bufferedImage, 0, 0, null);

		for (int i = 1; i <= blurSize; i++) {
			g2d.setComposite(AlphaComposite.SrcOver.derive(Math.min(1.0f, i * alphaStep)));
			g2d.drawImage(blurImage, x, 0, x + 1, y + 1, x, 0, x + 1, y + 1, null);
			g2d.drawImage(blurImage, 0, y, x, y + 1, 0, y, x, y + 1, null);
			x++;
			y++;
		}

		g2d.dispose();
		return edgeBlurImage;
	}

	/**
	 * 创建四个角大小不一致的圆角矩形，约定左上的圆角编号为1，顺时针方向递增
	 *
	 * @param x
	 *            开始X坐标
	 * @param y
	 *            开始Y坐标
	 * @param width
	 *            圆角矩形宽度
	 * @param height
	 *            圆角矩形高度
	 * @param cornerSize1
	 *            左上角的圆角大小
	 * @param cornerSize2
	 *            右上角的圆角大小
	 * @param cornerSize3
	 *            右下角的圆角大小
	 * @param cornerSize4
	 *            左下角的圆角大小
	 * @return 圆角矩形
	 */
	public static Polygon createRoundRect(int x, int y, int width, int height, int cornerSize1, int cornerSize2, int cornerSize3, int cornerSize4) {
		int nPoints = 8;
		int[] xPoints = { x, x + cornerSize1, x + width - cornerSize2, x + width, x + width, x + width - cornerSize3, x + cornerSize4, x };
		int[] yPoints = { y + cornerSize1, y, y, y + cornerSize2, y + height - cornerSize3, y + height, y + height, y + height - cornerSize4 };
		return new Polygon(xPoints, yPoints, nPoints);
	}

	/**
	 * 给JLabel添加动作，鼠标进入时显示下划线，单击左键时执行指定动作
	 *
	 * @param label
	 *            需要添加动作的JLabel
	 * @param action
	 *            单击左键时要执行的动作
	 */
	public static void actionLabel(final JLabel label, final Action action) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (action != null && SwingUtilities.isLeftMouseButton(e)) {
					action.actionPerformed(new ActionEvent(label, 0, null));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				underLine(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				underLine(false);
			}

			@SuppressWarnings("unchecked")
			private void underLine(boolean flag) {
				Font font = label.getFont();
				Map<TextAttribute, Object> map = (Map<TextAttribute, Object>) font.getAttributes();
				map.put(TextAttribute.UNDERLINE, flag ? TextAttribute.UNDERLINE_ON : -1);
				label.setFont(font.deriveFont(map));
			}
		});
	}

	/**
	 * 获取指定组件的GraphicsConfiguration，若空，则获取系统缺省GraphicsConfiguration
	 *
	 * @param c
	 *            用于获取GraphicsConfiguration的组件
	 * @return GraphicsConfiguration
	 */
	public static GraphicsConfiguration getGraphicsConfiguration(Component c) {
		GraphicsConfiguration gc = null;

		if (c == null || (gc = c.getGraphicsConfiguration()) == null) {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			gc = gd.getDefaultConfiguration();
		}

		return gc;
	}

	/**
	 * 获取比基准色较深的颜色
	 *
	 * @param color
	 *            基准色
	 * @param factor
	 *            深色因子
	 * @return 较深于基准色的颜色
	 */
	public static Color createDarkerColor(Color color, float factor) {
		return new Color(Math.max((int) (color.getRed() * factor), 0), Math.max((int) (color.getGreen() * factor), 0), Math.max((int) (color.getBlue() * factor), 0));
	}

	/**
	 * 创建无重复项的随机颜色组
	 *
	 * @param count
	 *            颜色组长度
	 * @return 无重复项的随机颜色组
	 */
	public static Color[] createRandomColors(int count) {
		if (count <= 0 || count > 256) {
			throw new IllegalArgumentException("count must be between 1 to 256");
		}

		final int sameFactor = 3;
		final int splitLength = 18;
		final int factorsSize = 255 / splitLength + 1;
		Color[] colors = new Color[count];
		Set<Color> set = new HashSet<Color>();
		Random rand = new Random();
		int indexR, indexG, indexB;

		if (colorFactors == null) {
			colorFactors = new int[factorsSize];

			for (int i = 0; i < factorsSize; i++) {
				colorFactors[i] = i * splitLength;
			}
		}

		while (set.size() < count) {
			indexR = indexG = indexB = 0;

			while (Math.abs(indexR - indexG) < sameFactor && Math.abs(indexG - indexB) < sameFactor && Math.abs(indexB - indexR) < sameFactor) {
				indexR = rand.nextInt(factorsSize);
				indexG = rand.nextInt(factorsSize);
				indexB = rand.nextInt(factorsSize);
			}

			set.add(new Color(colorFactors[indexR], colorFactors[indexG], colorFactors[indexB]));
		}

		return set.toArray(colors);
	}

	/**
	 * 操作系统是否支持形状
	 *
	 * @return 是否支持形状
	 */
	public static boolean isShapeSupported() {
		if (shapeSupported == null) {
			shapeSupported = AWTUtilities.isTranslucencySupported(Translucency.PERPIXEL_TRANSPARENT);
		}

		return shapeSupported.booleanValue();
	}

	/**
	 * 操作系统是否支持半透明
	 *
	 * @return 是否支持半透明
	 */
	public static boolean isTranslucencySupported() {
		if (translucencySupported == null) {
			translucencySupported = AWTUtilities.isTranslucencySupported(Translucency.TRANSLUCENT);
		}

		return translucencySupported.booleanValue();
	}

	/**
	 * 获取JPopupMenu所在的JWindow窗体
	 *
	 * @param popupMenu
	 *            目标JPopupMenu
	 * @return JPopupMenu所在的JWindow窗体
	 */
	public static JWindow getHeavyWeightWindow(JPopupMenu popupMenu) {
		Container c = popupMenu.getParent();

		while (c != null && !(c instanceof Window)) {
			c = c.getParent();
			// System.out.println(c);
			if (c instanceof JComponent) {
				JComponent jc = (JComponent) c;
				if (jc.isOpaque()) {
					jc.setOpaque(false);
				}
			}
		}

		if (c != null && c.getClass().getName().equalsIgnoreCase("javax.swing.Popup$HeavyWeightWindow")) {
			return (JWindow) c;
		} else {
			return null;
		}
	}


//	/**
//	 * 从目标容器中获取其边缘模糊处理的背景图像
//	 *
//	 * @param c
//	 *            目标容器，仅限于JCDialog、JCFrame
//	 * @return 边缘模糊的背景图像
//	 */
//	public static BufferedImage getEdgeBlurImageFromContainer(Container c) {
//
//		if (c == null) {
//			return null;
//		}
//
//		if (c instanceof OnlyFrame) {
//			return ((OnlyFrame) c).getEdgeBlurImage();
//		} else if (c instanceof OnlyDialog) {
//			return ((OnlyDialog) c).getEdgeBlurImage();
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 从目标容器中获取其背景图像
//	 *
//	 * @param c
//	 *            目标容器，仅限于JCDialog、JCFrame
//	 * @return 背景图像
//	 */
//	public static Image getBackgroundImageFromContainer(Container c) {
//		if (c == null) {
//			return null;
//		}
//
//		if (c instanceof OnlyFrame) {
//			return ((OnlyFrame) c).getBackgroundImage();
//		} else if (c instanceof OnlyDialog) {
//			return ((OnlyDialog) c).getBackgroundImage();
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 从目标容器中获取其背景图像的显示方式
//	 *
//	 * @param c
//	 *            目标容器，仅限于JCDialog、JCFrame
//	 * @return 背景图像的显示方式
//	 */
//	public static ImageDisplayMode getImageDisplayModeFromContainer(Container c) {
//		if (c == null) {
//			return null;
//		}
//
//		if (c instanceof OnlyFrame) {
//			return ((OnlyFrame) c).getImageDisplayMode();
//		} else if (c instanceof OnlyDialog) {
//			return ((OnlyDialog) c).getImageDisplayMode();
//		} else {
//			return null;
//		}
//	}
//
//	/**
//	 * 从目标容器中获取其背景图像的透明度
//	 *
//	 * @param c
//	 *            目标容器，仅限于JCDialog、JCFrame
//	 * @return 背景图像的透明度
//	 */
//	public static float getImageAlphaFromContainer(Container c) {
//		if (c == null) {
//			return 1.0f;
//		}
//
//		if (c instanceof OnlyFrame) {
//			return ((OnlyFrame) c).getImageAlpha();
//		} else if (c instanceof OnlyDialog) {
//			return ((OnlyDialog) c).getImageAlpha();
//		} else {
//			return 1.0f;
//		}
//	}

	static Object appContextGet(Object key) {
		return AppContext.getAppContext().get(key);
	}

	static void appContextPut(Object key, Object value) {
		AppContext.getAppContext().put(key, value);
	}

	static void appContextRemove(Object key) {
		AppContext.getAppContext().remove(key);
	}

	public static Window getWindowForComponent(Component parentComponent) throws HeadlessException {
		if (parentComponent == null)
			return getRootFrame();
		if (parentComponent instanceof Frame || parentComponent instanceof Dialog)
			return (Window) parentComponent;
		return getWindowForComponent(parentComponent.getParent());
	}

	private static final Object sharedFrameKey = JOptionPane.class;

	public static Frame getRootFrame() throws HeadlessException {
		Frame sharedFrame = (Frame) appContextGet(sharedFrameKey);
		if (sharedFrame == null) {
			sharedFrame = getSharedOwnerFrame();
			appContextPut(sharedFrameKey, sharedFrame);
		}
		return sharedFrame;
	}

	private static final Object sharedOwnerFrameKey = new StringBuffer("SwingUtilities.sharedOwnerFrame");

	static Frame getSharedOwnerFrame() throws HeadlessException {
		Frame sharedOwnerFrame = (Frame) appContextGet(sharedOwnerFrameKey);
		if (sharedOwnerFrame == null) {
			sharedOwnerFrame = new SharedOwnerFrame();
			appContextPut(sharedOwnerFrameKey, sharedOwnerFrame);
		}
		return sharedOwnerFrame;
	}

	static class SharedOwnerFrame extends Frame implements WindowListener {

		private static final long serialVersionUID = 1L;

		public void addNotify() {
			super.addNotify();
			installListeners();
		}

		/**
		 * Install window listeners on owned windows to watch for displayability
		 * changes
		 */
		void installListeners() {
			Window[] windows = getOwnedWindows();
			for (Window window : windows) {
				if (window != null) {
					window.removeWindowListener(this);
					window.addWindowListener(this);
				}
			}
		}

		/**
		 * Watches for displayability changes and disposes shared instance if
		 * there are no displayable children left.
		 */
		public void windowClosed(WindowEvent e) {
			synchronized (getTreeLock()) {
				Window[] windows = getOwnedWindows();
				for (Window window : windows) {
					if (window != null) {
						if (window.isDisplayable()) {
							return;
						}
						window.removeWindowListener(this);
					}
				}
				dispose();
			}
		}

		public void windowOpened(WindowEvent e) {
		}

		public void windowClosing(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowActivated(WindowEvent e) {
		}

		public void windowDeactivated(WindowEvent e) {
		}

		public void show() {
			// This frame can never be shown
		}

		public void dispose() {
			try {
				getToolkit().getSystemEventQueue();
				super.dispose();
			} catch (Exception e) {
				// untrusted code not allowed to dispose
			}
		}
	}

}