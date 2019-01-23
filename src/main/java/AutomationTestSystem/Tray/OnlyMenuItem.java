package AutomationTestSystem.Tray;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

public class OnlyMenuItem extends JMenuItem {
	private static final long serialVersionUID = -1476962131254999081L;
	private Icon disabledIcon;
	private Color disabledTextColor;
	private Color selectedForeground;
	private int preferredHeight;

	public OnlyMenuItem() {
		this(null, (Icon) null);
	}

	public OnlyMenuItem(Icon icon) {
		this(null, icon);
	}

	public OnlyMenuItem(String text) {
		this(text, (Icon) null);
	}

	public OnlyMenuItem(Action a) {
		this();
		setAction(a);
	}

	public OnlyMenuItem(String text, Icon icon) {
		super(text, icon);
		init();
	}

	public OnlyMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
		init();
	}

	private void init() {
		setUI(new OnlyMenuItemUI());
		setOpaque(false);
		setFont(OnlyUIUtil.getDefaultFont());
		setForeground(new Color(0, 20, 35));
		setBackground(Color.GRAY);
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFocusPainted(false);
		setRolloverEnabled(true);
		setMargin(new Insets(0, 0, 0, 0));
		selectedForeground = new Color(253, 253, 253);
		disabledTextColor = new Color(127, 137, 144);
		preferredHeight = 22;
	}

	public Color getDisabledTextColor() {
		return disabledTextColor;
	}

	public void setDisabledTextColor(Color disabledTextColor) {
		this.disabledTextColor = disabledTextColor;

		if (!this.isEnabled()) {
			this.repaint();
		}
	}

	public Color getSelectedForeground() {
		return selectedForeground;
	}

	public void setSelectedForeground(Color selectedForeground) {
		this.selectedForeground = selectedForeground;
		this.repaint();
	}

	public int getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(int preferredHeight) {
		this.preferredHeight = preferredHeight;
		this.revalidate();
	}

	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();

		if (preferredHeight > 0) {
			size.height = preferredHeight;
		}

		return size;
	}

	public Icon getDisabledIcon() {
		return disabledIcon;
	}

	public void setDisabledIcon(Icon disabledIcon) {
		super.setDisabledIcon(disabledIcon);
		this.disabledIcon = disabledIcon;
	}

	@Deprecated
	public void updateUI() {
	}
}