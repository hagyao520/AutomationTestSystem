package AutomationTestSystem.Tray;

import java.awt.Frame;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class OnlyTrayIcon extends TrayIcon {

    private JDialog parent;
    private JPopupMenu menu;
    private MouseListener mouseListener;
    private PopupMenuListener popupMenuListener;

    //{{{ JTrayIcon constructor
    public OnlyTrayIcon(Image image, String tooltip) {
    	super(image, tooltip, null);
    } //}}}

    //{{{ getMenu() method
    public JPopupMenu getMenu() {
        return menu;
    } //}}}

    //{{{ setMenu() method
    public void setMenu(JPopupMenu menu) {
        if (menu == null) {

            if (mouseListener != null) {
                removeMouseListener(mouseListener);
                mouseListener = null;
            }
            if (popupMenuListener != null) {
                this.menu.removePopupMenuListener(popupMenuListener);
                popupMenuListener = null;
            }
            parent = null;
        } else {
            parent = new JDialog((Frame) null);
            parent.setUndecorated(true);
            parent.setAlwaysOnTop(true);
            if (mouseListener == null) {
                mouseListener = new MyMouseListener();
                addMouseListener(mouseListener);
            }
            popupMenuListener = new MyPopupMenuListener();
            menu.addPopupMenuListener(popupMenuListener);
        }
        this.menu = menu;
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isPopupTrigger(e)) {
                parent.setLocation(e.getX(), e.getY() - menu.getPreferredSize().height);
                parent.setVisible(true);
                menu.show(parent, 0, 0);
            }
        }
    }

    private class MyPopupMenuListener implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            parent.setVisible(false);
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            parent.setVisible(false);
        }
    }

    public static boolean isPopupTrigger(MouseEvent evt) {
        return isRightButton(evt.getModifiers());
    }

    public static boolean isRightButton(int modifiers) {
        return (modifiers & InputEvent.BUTTON3_MASK) != 0;
    }
}
