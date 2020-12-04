package Test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

/**
 * @author: lishuai
 * @date: 2018/11/26 13:51
 */
public class WeimingSyncApplets {
    public static void main(String[] args) {
        // 面板组件
        JPanel taskPanel = new JPanel();
        JPanel dbPanel = new JPanel();
        JTabbedPane tabbedPane = buildJTabbedPane(taskPanel, dbPanel);
        buildFrame(tabbedPane);
    }

    private static JTabbedPane buildJTabbedPane(JPanel taskPanel, JPanel dbPanel) {
        // 选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        // 通过BorderFactory来设置边框的特性
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.add("执行任务", taskPanel);
        tabbedPane.add("数据源配置", dbPanel);

        // 下拉框
        dbPanel.add(buildJLabel("数据库类型", 10, 20, 80, 25));
        String dbs[] = {"mysql", "oracle", "sqlserver"};
        dbPanel.add(buildJComboBox("mysql", "mysql", dbs, 0, 100, 20, 165, 25));

        // 文本框
        dbPanel.add(buildJLabel("用户名", 10, 50, 80, 25));
        JTextField dbUserName = buildJTextField("dbUserName", "dbUserName", 20, 100, 50, 165, 25);
        dbPanel.add(dbUserName);

        // 密码
        dbPanel.add(buildJLabel("密码", 10, 80, 80, 25));
        JPasswordField dbPassWord = buildJPasswordField("dbPassWord", "dbPassWord", 20, 100, 80, 165, 25);
        dbPanel.add(dbPassWord);

        // 添加按钮，并为按钮绑定事件监听
        JButton saveButton = buildJButton("保存", 185, 230, 80, 25);
        addActionListener(saveButton);
        dbPanel.add(saveButton);

        return tabbedPane;
    }

    private static void addActionListener(JButton saveButton) {
        // 为按钮绑定监听器
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 对话框
                JOptionPane.showMessageDialog(null, "保存成功！");
            }
        });
    }

    private static JButton buildJButton(String name, int x, int y, int width, int height) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        return button;
    }

    private static JPasswordField buildJPasswordField(String defaultValue, String name, int columns, int x, int y, int width, int height) {
        JPasswordField jPasswordField = new JPasswordField(columns);
        jPasswordField.setText(defaultValue);
        jPasswordField.setName(name);
        jPasswordField.setBounds(x, y, width, height);
        return jPasswordField;
    }

    private static JTextField buildJTextField(String defaultValue, String name, int columns, int x, int y, int width, int height) {
        JTextField text = new JTextField(columns);
        text.setText(defaultValue);
        text.setName(name);
        text.setBounds(x, y, width, height);
        return text;
    }

    private static JComboBox buildJComboBox(Object selectedItem, String name, String[] elements, int selectedIndex, int x, int y, int width, int height) {
        DefaultComboBoxModel codeTypeModel = new DefaultComboBoxModel();
        // elements 下拉框中的选项
        for (String element : elements) {
            codeTypeModel.addElement(element);
        }
        JComboBox codeTypeBox = new JComboBox(codeTypeModel);
        codeTypeBox.setName(name);
        // 默认选中的下拉框选项
        codeTypeBox.setSelectedItem(selectedItem);
//        codeTypeBox.setSelectedItem(selectedIndex);
        codeTypeBox.setBounds(x, y, width, height);
        // 添加下拉框事件监听器
        codeTypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 选择的下拉框选项
                    System.out.println(e.getItem());
                }
            }
        });
        return codeTypeBox;
    }

    private static JLabel buildJLabel(String name, int x, int y, int width, int height) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        return label;
    }

    private static void buildFrame(JComponent component) {
        // 窗体容器
        JFrame frame = new JFrame("数据同步工具");
        frame.add(component);
        //  JFrame.EXIT_ON_CLOSE  退出
        //  JFrame.HIDE_ON_CLOSE  最小化隐藏
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // 设置布局
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER, component);
        // 设置窗口最小尺寸
        frame.setMinimumSize(new Dimension(1060, 560));
        // 调整此窗口的大小，以适合其子组件的首选大小和布局
        frame.pack();
        // 设置窗口相对于指定组件的位置
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // 设置窗口尺寸是否固定不变
        frame.setResizable(true);
    }

}