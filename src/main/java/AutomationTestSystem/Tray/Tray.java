/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutomationTestSystem.Tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */

public class Tray {

	private OnlyTrayIcon trayIcon;

	Image image = new ImageIcon("Resources/Images/Logo/logo_16.png").getImage();
	//TrayPopupMenu trayPopupMenu = new TrayPopupMenu();

	public Tray() {
		trayIcon = new OnlyTrayIcon(image, "OIM");
		initTray();
		initEvent();
	}


	public Tray(Image image,String text) {
		initTrayIcon(image, text);
		initTray();
		initEvent();
	}

	private void initTrayIcon(Image image,String text) {
		trayIcon = new OnlyTrayIcon(image, "OIM");
	}

	private void initEvent() {
		trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				trayMouseClicked(evt);
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				trayMouseEntered(evt);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				trayMouseExited(evt);
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				trayMousePressed(evt);
			}
		});
	}

	private void initTray() {
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
			}
		} catch (AWTException ex) {
			Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void trayMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
		}
	}

	private void trayMouseEntered(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void trayMouseExited(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void trayMousePressed(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	public static void main(String a[]){

	}
}
