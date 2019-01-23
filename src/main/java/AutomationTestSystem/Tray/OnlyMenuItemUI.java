package AutomationTestSystem.Tray;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.text.View;

import sun.swing.SwingUtilities2;

@SuppressWarnings("restriction")
public class OnlyMenuItemUI extends BasicMenuItemUI {

	protected static final int DEFAULT_ACC_GAP = 5;
	protected static final int DEFAULT_LEFT_GAP = 35;
	protected static final int DEFAULT_RIGHT_GAP = 20;
	protected static final int ICON_AREA_WIDTH = 25;
	private static final String MAX_TEXT_WIDTH = "maxTextWidth";
	private static final String MAX_ACC_WIDTH = "maxAccWidth";
	private static Rectangle zeroRect = new Rectangle(0, 0, 0, 0);
	private static Rectangle iconRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();
	private static Rectangle acceleratorRect = new Rectangle();
	private static Rectangle viewRect = new Rectangle(Short.MAX_VALUE, Short.MAX_VALUE);
	private static Rectangle rect = new Rectangle();
	private String acceleratorDelimiter;

	private Image menu_item_background=UIBox.getImage(UIBox.key_menu_item_background);
	public static ComponentUI createUI(JComponent c) {
		return new OnlyMenuItemUI();
	}

	protected void installDefaults() {
		acceleratorFont = OnlyUIUtil.getDefaultFont();
		acceleratorDelimiter = "+";
	}

	protected void uninstallDefaults() {
	}

	public Dimension getPreferredSize(JComponent c) {
		JMenuItem item = (JMenuItem) c;
		String text = item.getText();
		KeyStroke accelerator = item.getAccelerator();
		String acceleratorText = "";
		Font font = item.getFont();
		FontMetrics fm = item.getFontMetrics(font);
		FontMetrics fmAccel = item.getFontMetrics(acceleratorFont);
		JComponent parent = getMenuItemParent(menuItem);
		Icon icon = item.getIcon();
		int iconHeight = icon == null ? 0 : icon.getIconHeight();

		if (accelerator != null) {
			int modifiers = accelerator.getModifiers();
			int keyCode = accelerator.getKeyCode();

			if (modifiers > 0) {
				acceleratorText = KeyEvent.getKeyModifiersText(modifiers) + acceleratorDelimiter;
			}

			if (keyCode != 0) {
				acceleratorText += KeyEvent.getKeyText(keyCode);
			} else {
				acceleratorText += accelerator.getKeyChar();
			}
		}

		resetRects();
		layoutMenuItem(fm, text, fmAccel, acceleratorText, item.getVerticalAlignment(), item.getHorizontalAlignment(), item.getVerticalTextPosition(), item.getHorizontalTextPosition(), viewRect, iconRect, textRect, acceleratorRect);
		addMaxWidth(parent, OnlyMenuItemUI.MAX_TEXT_WIDTH, textRect.width);
		addMaxWidth(parent, OnlyMenuItemUI.MAX_ACC_WIDTH, acceleratorRect.width + (acceleratorRect.width > 0 ? DEFAULT_ACC_GAP : 0));
		rect.width += DEFAULT_LEFT_GAP + DEFAULT_RIGHT_GAP;
		rect.height = max(textRect.height, acceleratorRect.height, iconHeight);
		return rect.getSize();
	}

	public void paint(Graphics g, JComponent c) {
		JMenuItem item = (JMenuItem) c;
		Font font = c.getFont();
		FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, font);
		FontMetrics fmAccel = SwingUtilities2.getFontMetrics(c, g, acceleratorFont);
		KeyStroke accelerator = item.getAccelerator();
		String acceleratorText = "";

		resetRects();
		viewRect.setBounds(0, 0, item.getWidth(), item.getHeight());

		if (accelerator != null) {
			int modifiers = accelerator.getModifiers();
			int keyCode = accelerator.getKeyCode();

			if (modifiers > 0) {
				acceleratorText = KeyEvent.getKeyModifiersText(modifiers) + acceleratorDelimiter;
			}

			if (keyCode != 0) {
				acceleratorText += KeyEvent.getKeyText(keyCode);
			} else {
				acceleratorText += accelerator.getKeyChar();
			}
		}

		String text = layoutMenuItem(fm, item.getText(), fmAccel, acceleratorText, item.getVerticalAlignment(), item.getHorizontalAlignment(), item.getVerticalTextPosition(), item.getHorizontalTextPosition(), viewRect, iconRect, textRect, acceleratorRect);

		paintBackground(g, item);
		paintIcon(g, item, iconRect);

		if (text != null) {
			View view = (View) c.getClientProperty(BasicHTML.propertyKey);
			g.setFont(font);

			if (view != null) {
				view.paint(g, textRect);
			} else {
				paintText(g, item, textRect, text);
			}
		}

		if (acceleratorText != null && !acceleratorText.equals("")) {
			paintAcceleratorText(g, item, acceleratorRect, acceleratorText);
		}
	}

	protected void paintBackground(Graphics g, JMenuItem menuItem) {
		ButtonModel model = menuItem.getModel();

		if (menuItem.isEnabled() && model.isArmed()) {
			OnlyUIUtil.paintImage(g, menu_item_background, new Insets(1, 1, 1, 1), new Rectangle(0, 0, menuItem.getWidth(), menuItem.getHeight()), menuItem);
		}
	}

	protected void paintIcon(Graphics g, JMenuItem menuItem, Rectangle iconRect) {
		ButtonModel model = menuItem.getModel();
		Icon icon = menuItem.getIcon();
		boolean existDisabledIcon = true;

		if (icon != null) {
			if (!model.isEnabled()) {
				icon = menuItem.getDisabledIcon();

				if (icon == null) {
					icon = menuItem.getIcon();
					existDisabledIcon = false;
				}
			} else if (model.isPressed() && model.isArmed()) {
				icon = menuItem.getPressedIcon();

				if (icon == null) {
					icon = menuItem.getIcon();
				}
			}

			if (icon != null) {
				Graphics2D g2d = (Graphics2D) g;
				Composite oldComposite = g2d.getComposite();

				if (!existDisabledIcon) {
					g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
				}

				icon.paintIcon(menuItem, g2d, iconRect.x, iconRect.y);
				g2d.setComposite(oldComposite);
			}
		}
	}

	protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
		FontMetrics fm = SwingUtilities2.getFontMetrics(menuItem, g);
		int mnemIndex = menuItem.getDisplayedMnemonicIndex();
		Color color = getTextColor();

		if (color != null) {
			g.setColor(color);
			SwingUtilities2.drawStringUnderlineCharAt(menuItem, g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
		} else {
			super.paintText(g, menuItem, textRect, text);
		}
	}

	protected Color getTextColor() {
		ButtonModel model = menuItem.getModel();
		Color color = null;

		if (menuItem instanceof OnlyMenuItem) {
			OnlyMenuItem cItem = (OnlyMenuItem) menuItem;

			if (!model.isEnabled()) {
				color = cItem.getDisabledTextColor();
			} else {
				if (model.isArmed()) {
					color = cItem.getSelectedForeground();
				} else {
					color = cItem.getForeground();
				}
			}
		}

		return color;
	}

	protected void paintAcceleratorText(Graphics g, JMenuItem menuItem, Rectangle acceleratorRect, String acceleratorText) {
		ButtonModel model = menuItem.getModel();
		FontMetrics fmAccel = SwingUtilities2.getFontMetrics(menuItem, g, acceleratorFont);
		boolean isCItem = menuItem instanceof OnlyMenuItem;
		OnlyMenuItem cItem = isCItem ? (OnlyMenuItem) menuItem : null;
		JComponent parent = getMenuItemParent(menuItem);
		Color color = null;
		int accOffset = 0;

		if (parent != null) {
			Integer maxValueInt = (Integer) parent.getClientProperty(OnlyMenuItemUI.MAX_ACC_WIDTH);
			int maxValue = maxValueInt != null ? maxValueInt.intValue() : acceleratorRect.width;
			accOffset = maxValue - acceleratorRect.width;
		}

		if (!model.isEnabled()) {
			color = isCItem ? cItem.getDisabledTextColor() : (disabledForeground == null ? Color.GRAY : disabledForeground);
		} else {
			if (model.isArmed()) {
				color = isCItem ? cItem.getSelectedForeground() : acceleratorSelectionForeground;
			} else {
				color = isCItem ? cItem.getForeground() : acceleratorForeground;
			}
		}

		g.setFont(acceleratorFont);
		g.setColor(color);
		SwingUtilities2.drawString(menuItem, g, acceleratorText, acceleratorRect.x - accOffset + DEFAULT_ACC_GAP, acceleratorRect.y + fmAccel.getAscent());
	}

	private String layoutMenuItem(FontMetrics fm, String text, FontMetrics fmAccel, String acceleratorText, int verticalAlignment, int horizontalAlignment, int verticalTextPosition, int horizontalTextPosition, Rectangle viewRect, Rectangle iconRect, Rectangle textRect,
			Rectangle acceleratorRect) {
		SwingUtilities.layoutCompoundLabel(menuItem, fm, text, null, verticalAlignment, horizontalAlignment, verticalTextPosition, horizontalTextPosition, viewRect, iconRect, textRect, 0);

		if ((acceleratorText == null) || acceleratorText.equals("")) {
			acceleratorRect.width = acceleratorRect.height = 0;
			acceleratorText = "";
		} else {
			acceleratorRect.width = SwingUtilities2.stringWidth(menuItem, fmAccel, acceleratorText);
			acceleratorRect.height = fmAccel.getHeight();
		}

		acceleratorRect.x = viewRect.x + viewRect.width - acceleratorRect.width - DEFAULT_RIGHT_GAP;
		acceleratorRect.y = textRect.y + (textRect.height / 2) - (acceleratorRect.height / 2);
		textRect.x += DEFAULT_LEFT_GAP;
		layoutIcon(iconRect);
		return text;
	}

	protected void layoutIcon(Rectangle iconRect) {
		Icon icon = menuItem.getIcon();

		if (icon != null) {
			int iconWidth = icon.getIconWidth();
			int iconHeight = icon.getIconHeight();
			iconRect.x = (int) Math.round((ICON_AREA_WIDTH - iconWidth) / 2.0);
			iconRect.y = (int) Math.round((menuItem.getHeight() - iconHeight) / 2.0);
			iconRect.width = iconWidth;
			iconRect.height = iconHeight;
		}
	}

	private void addMaxWidth(JComponent parent, String propertyName, int curWidth) {
		Integer maxWidth = null;

		if (parent != null) {
			maxWidth = (Integer) parent.getClientProperty(propertyName);
		}

		if (maxWidth == null) {
			maxWidth = 0;
		}

		if (curWidth > maxWidth) {
			maxWidth = curWidth;

			if (parent != null) {
				parent.putClientProperty(propertyName, maxWidth);
			}
		}

		if (maxWidth > 0) {
			rect.width += maxWidth;
		}
	}

	private void resetRects() {
		iconRect.setBounds(zeroRect);
		textRect.setBounds(zeroRect);
		acceleratorRect.setBounds(zeroRect);
		viewRect.setBounds(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
		rect.setBounds(zeroRect);
	}

	private JComponent getMenuItemParent(JMenuItem item) {
		Container parent = item.getParent();

		if (parent instanceof JComponent) {
			return (JComponent) parent;
		} else {
			return null;
		}
	}

	private int max(int... values) {
		int maxValue = Integer.MIN_VALUE;

		for (int value : values) {
			if (value > maxValue) {
				maxValue = value;
			}
		}

		return maxValue;
	}
}