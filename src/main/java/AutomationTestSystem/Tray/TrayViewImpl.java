package AutomationTestSystem.Tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 描述：
 *
 * @author King
 * @date 2018年8月6日 10:34:22
 * @version 0.0.1
 */
@SuppressWarnings("restriction")
public class TrayViewImpl extends Application{

	private Image image = new ImageIcon("src/main/resources/image/LoginPane/Logo/Logo_ico16x16.png").getImage();
	private OnlyTrayIcon trayIcon;
	private TrayPopupMenu trayPopupMenu = new TrayPopupMenu();

	public TrayViewImpl() {
		initTrayIcon();
		initTray();
		initEvent();
	}

	private void initTrayIcon() {
		trayIcon = new OnlyTrayIcon(image, "西域游");
		trayIcon.setMenu(trayPopupMenu);
//		this.showAllMenu(true);
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

	private void initEvent() {
		trayPopupMenu.addSystemSetupActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				LoginView.MainInterfaceStage.hide();
				System.out.println("22");
			}
		});
		trayPopupMenu.addOpenMainActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				LoginView.MainInterfaceStage.hide();
				System.out.println("33");
			}
		});
		trayPopupMenu.addExitActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});
	}

	public void showAllMenu(boolean show) {
		trayPopupMenu.showAll(show);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//			initTrayIcon();
//			initTray();
//			initEvent();
	}
	public static void main(String[] args) {
        launch(args);
    }
}
