package AutomationTestSystem.Tray;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.plaf.basic.DefaultMenuLayout;


public class OnlyPopupMenuUI extends BasicPopupMenuUI {
	private PropertyChangeHandler propertyChangeListener;

	public static ComponentUI createUI(JComponent c) {
		return new OnlyPopupMenuUI();
	}

	public void update(Graphics g, JComponent c) {
		boolean translucencySupported = OnlyUIUtil.isTranslucencySupported();
		Insets imageInsets = translucencySupported ? new Insets(9, 32, 9, 8) : new Insets(4, 27, 4, 3);
		Image image = UIBox.getImage(translucencySupported ? UIBox.key_image_menu_shadow: UIBox.key_image_menu_no_shadow);
		OnlyUIUtil.paintImage(g, image, imageInsets, new Rectangle(0, 0, popupMenu.getWidth(), popupMenu.getHeight()), popupMenu);
		paint(g, c);
	}

	protected void installListeners() {
		super.installListeners();

		if (propertyChangeListener == null) {
			propertyChangeListener = new PropertyChangeHandler();
		}

		UIManager.addPropertyChangeListener(propertyChangeListener);
	}

	protected void uninstallListeners() {
		super.uninstallListeners();

		if (propertyChangeListener != null) {
			UIManager.removePropertyChangeListener(propertyChangeListener);
		}
	}

	public void installDefaults() {
		if (popupMenu.getLayout() == null || popupMenu.getLayout() instanceof UIResource) {
			popupMenu.setLayout(new DefaultMenuLayout(popupMenu, BoxLayout.Y_AXIS));
		}
	}

	protected void uninstallDefaults() {
	}

	private class PropertyChangeHandler implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent e) {
			if ("lookAndFeel".equalsIgnoreCase(e.getPropertyName())) {
				SwingUtilities.updateComponentTreeUI(popupMenu);
			}
		}
	}
}