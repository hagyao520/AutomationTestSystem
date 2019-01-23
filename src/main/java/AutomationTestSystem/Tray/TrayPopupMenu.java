package AutomationTestSystem.Tray;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;

/**
 * @author King
 * @date 2018年8月6日 10:34:22
 */
public class TrayPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

//	private OnlyMenuItem awayMenuItem = new OnlyMenuItem();
//	private OnlyMenuItem busyMenuItem = new OnlyMenuItem();
//	private OnlyMenuItem invisibleMenuItem = new OnlyMenuItem();
//
//	private OnlyPopupMenu.Separator separator1 = new OnlyPopupMenu.Separator();
//	private OnlyPopupMenu.Separator separator2 = new OnlyPopupMenu.Separator();
//	private OnlyPopupMenu.Separator separator3 = new OnlyPopupMenu.Separator();
//	private OnlyPopupMenu.Separator separator4 = new OnlyPopupMenu.Separator();
//	private OnlyMenuItem muteMenuItem = new OnlyMenuItem();
//	private OnlyMenuItem omeMenuItem = new OnlyMenuItem();
//	private OnlyMenuItem onlineMenuItem = new OnlyMenuItem();
//	private OnlyMenuItem offlineMenuItem = new OnlyMenuItem();

	private OnlyMenuItem SystemSetupMenuItem = new OnlyMenuItem();
	private OnlyPopupMenu.Separator separator3 = new OnlyPopupMenu.Separator();
	private OnlyMenuItem OpenMainMenuItem = new OnlyMenuItem();
	private OnlyPopupMenu.Separator separator4 = new OnlyPopupMenu.Separator();
	private OnlyMenuItem ExitMenuItem = new OnlyMenuItem();

//	private ImageIcon offlineImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/imoffline.png");
//	private ImageIcon onlineImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/imonline.png");
//	private ImageIcon omeImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/Qme.png");
//	private ImageIcon awayImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/away.png");
//	private ImageIcon busyImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/busy.png");
//	private ImageIcon muteImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/mute.png");
//	private ImageIcon invisibleImageIcon = ImageUtil.getImageIcon("Resources/Images/Default/Status/FLAG/Big/invisible.png");
	private ImageIcon SystemSetupImageIcon = ImageUtil.getImageIcon("src/main/resources/image/Tray/MenuIco/SystemSetup14x14.png");
	private ImageIcon OpenMainImageIcon = ImageUtil.getImageIcon("src/main/resources/image/Tray/MenuIco/Main16x16.png");
	private ImageIcon ExitImageIcon = ImageUtil.getImageIcon("src/main/resources/image/Tray/MenuIco/Exit16x16.png");


	public TrayPopupMenu() {
		initMenu();
	}

	private void initMenu() {
//		onlineMenuItem.setText("我在线上");
//		onlineMenuItem.setToolTipText("");
//		omeMenuItem.setText("Call我吧");
//		awayMenuItem.setText("离开");
//		busyMenuItem.setText("忙碌");
//		muteMenuItem.setText("请勿打扰");
//		invisibleMenuItem.setText("隐身");
//		offlineMenuItem.setText("离线");
		SystemSetupMenuItem.setText("系统设置");
		OpenMainMenuItem.setText("打开主面板");
		ExitMenuItem.setText("退出");

//		onlineMenuItem.setIcon(onlineImageIcon);
//		omeMenuItem.setIcon(omeImageIcon);
//		awayMenuItem.setIcon(awayImageIcon);
//		busyMenuItem.setIcon(busyImageIcon);
//		muteMenuItem.setIcon(muteImageIcon);
//		invisibleMenuItem.setIcon(invisibleImageIcon);
//		offlineMenuItem.setIcon(offlineImageIcon);
		SystemSetupMenuItem.setIcon(SystemSetupImageIcon);
		OpenMainMenuItem.setIcon(OpenMainImageIcon);
		ExitMenuItem.setIcon(ExitImageIcon);

//		add(onlineMenuItem);
//		add(omeMenuItem);
//		add(separator1);
//		add(awayMenuItem);
//		add(busyMenuItem);
//		add(muteMenuItem);
//		add(separator2);
//		add(invisibleMenuItem);
//		add(separator3);
//		add(offlineMenuItem);
		add(SystemSetupMenuItem);
		add(separator3);
		add(OpenMainMenuItem);
		add(separator4);
		add(ExitMenuItem);
	}

	public void showAll(boolean show) {
//		onlineMenuItem.setVisible(show);
//		onlineMenuItem.setVisible(show);
//		omeMenuItem.setVisible(show);
//		awayMenuItem.setVisible(show);
//		busyMenuItem.setVisible(show);
//		muteMenuItem.setVisible(show);
//		invisibleMenuItem.setVisible(show);
//		offlineMenuItem.setVisible(show);
//		separator1.setVisible(show);
//		separator2.setVisible(show);
//		separator3.setVisible(show);
//		separator4.setVisible(show);
		SystemSetupMenuItem.setVisible(show);
		OpenMainMenuItem.setVisible(show);
		ExitMenuItem.setVisible(show);
	}

	public void addSystemSetupActionListener(ActionListener l) {
		SystemSetupMenuItem.addActionListener(l);
	}

	public void addOpenMainActionListener(ActionListener l) {
		OpenMainMenuItem.addActionListener(l);
	}

	public void addExitActionListener(ActionListener l) {
		ExitMenuItem.addActionListener(l);
	}
}
