package Test;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class JTabbedPaneDemo extends JPanel {

    private JTabbedPane jTabbedpane = new JTabbedPane();// 存放选项卡的组件
    private String[] tabNames = {"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项6", "选项6", "选项6", "选项6", "选项6"};
    ImageIcon icon = createImageIcon("image/LoginPane/SystemSettingPane/Server20x20.png");

    public JTabbedPaneDemo() {
        layoutComponents();
    }

    private void layoutComponents() {
        int i = 0;
        // 第一个标签下的JPanel
        JPanel jpanelFirst = new JPanel();
        for (String s : tabNames) {
            System.out.println(s + " ");
            jTabbedpane.addTab(s, icon, new JPanel(), "first");// 加入第一个页面
        }
//        jTabbedpane.addTab(tabNames[i++], icon, jpanelFirst, "first");// 加入第一个页面
//        jTabbedpane.setMnemonicAt(0, KeyEvent.VK_0);// 设置第一个位置的快捷键为0
//
//        // 第二个标签下的JPanel
//        JPanel jpanelSecond = new JPanel();
//        jTabbedpane.addTab(tabNames[i++], icon, jpanelSecond, "second");// 加入第一个页面
//        jTabbedpane.setMnemonicAt(1, KeyEvent.VK_1);// 设置快捷键为1
        setLayout(new GridLayout(1, 1));
        add(jTabbedpane);

    }

    private ImageIcon createImageIcon(String path) {

        URL url = JTabbedPaneDemo.class.getResource(path);
        if (url == null) {
            System.out.println("the image " + path + " is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                // JFrame.setDefaultLookAndFeelDecorated(true);// 将组建外观设置为Java外观
                JFrame frame = new JFrame();
                frame.setLayout(null);
                frame.setContentPane(new JTabbedPaneDemo());
                frame.setSize(400, 400);
                frame.setVisible(true);
                // new TabComponentsDemo().runTest();
            }
        });
    }
}
