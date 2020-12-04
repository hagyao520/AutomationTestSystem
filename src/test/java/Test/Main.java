package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new Main();
        
    }

    public Main() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JPanel container = new JPanel();
        JScrollPane scrollPane1 = new JScrollPane();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelTwo1 = new JPanel();
        JTextArea textArea = new JTextArea();
        JTextField UserInfoTextField5 = new JTextField(20);
        
        JButton UserInfoButton = new JButton("用户信息查询1");
        UserInfoButton.setBounds(10, 10, 180, 25);
        UserInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent a) {
                textArea.append("功能："+UserInfoButton.getText()+"\n");
                textArea.append("参数："+UserInfoTextField5.getText()+"\n");
                textArea.append("SQL："+UserInfoTextField5.getText()+"\n");
                textArea.append("\n");  
            }
        });
        
        JButton UserInfoButton2 = new JButton("用户信息查询2");
        UserInfoButton2.setBounds(10, 40, 180, 25);

        JButton UserInfoButton3 = new JButton("用户信息查询3");
        UserInfoButton3.setBounds(10, 70, 180, 25);

        JButton UserInfoButton4 = new JButton("用户信息查询4");
        UserInfoButton4.setBounds(10, 100, 180, 25);

        JButton UserInfoButton5 = new JButton("用户信息查询5");
        UserInfoButton5.setBounds(10, 130, 180, 25);

        panelOne.setLayout(null);
        panelOne.setBackground(Color.red);
        panelOne.setPreferredSize(new Dimension(300, 440));
        // panelOne.setLayout(new GridLayout(3,1,10,5));
        // panelOne.setLayout(new FlowLayout(FlowLayout.LEFT,100,50));
        panelOne.add(UserInfoButton);
        panelOne.add(UserInfoButton2);
        panelOne.add(UserInfoButton3);
        panelOne.add(UserInfoButton4);
        panelOne.add(UserInfoButton5);

        JScrollPane scrollPane = new JScrollPane(panelOne);
        // scrollPane.setPreferredSize(new Dimension(300, 440));
        scrollPane.setBounds(10, 10, 330, 440);
        // scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        panelTwo.setLayout(null);
        panelTwo.setPreferredSize(new Dimension(300, 1550));
        // panelTwo.setBackground(Color.gray);
        // panelTwo.setLayout(new GridLayout(2,1));

        
        UserInfoTextField5.setBounds(10, 10, 180, 25);

        panelTwo1.setLayout(null);
        panelTwo1.setBackground(Color.black);
        panelTwo1.setBounds(350, 10, 330, 440);
        panelTwo1.add(UserInfoTextField5);

        
        textArea.setText("22222222222");
        JScrollPane scrollPane2 = new JScrollPane(textArea);
        scrollPane2.setBounds(700, 10, 200, 440);

        panelTwo.add(scrollPane);
        panelTwo.add(panelTwo1);
        panelTwo.add(scrollPane2);

        JTabbedPane jTabbedpane = new JTabbedPane();
        jTabbedpane.addTab("选项1", null, panelTwo, "first");// 加入第一个页面
        jTabbedpane.addTab("选项2", null, new JPanel(), "first");// 加入第一个页面
        jTabbedpane.addTab("选项3", null, new JPanel(), "first");// 加入第一个页面

        container.setLayout(new GridLayout(2, 1));
        container.add(scrollPane1);
        container.add(jTabbedpane);

        JFrame jf = new JFrame("测试窗口");
        jf.setSize(1000, 1000);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setContentPane(container);
        jf.setVisible(true);
    }

    public void Main2() {
        JButton UserInfoButton = new JButton("用户信息查询1");
        JButton UserInfoButton2 = new JButton("用户信息查询2");
        JButton UserInfoButton3 = new JButton("用户信息查询3");
        JButton UserInfoButton4 = new JButton("用户信息查询4");
        // UserInfoButton.setBounds(10, 10, 180, 25);

        JTextField UserInfoTextField = new JTextField(20);
        // UserInfoTextField.setBounds(10, 140, 180, 25);

        JButton UserInfoButton1 = new JButton("用户信息查询2");
        JTextField UserInfoTextField1 = new JTextField(20);

        JPanel MainPanel1 = new JPanel();
        MainPanel1.setSize(300, 300);
        MainPanel1.setBackground(Color.red);

        JPanel MainPanel2 = new JPanel();
        MainPanel2.setSize(300, 300);
        MainPanel2.setBackground(Color.red);

        MainPanel1.add(UserInfoButton);
        MainPanel1.add(UserInfoButton2);
        MainPanel1.add(UserInfoButton3);
        MainPanel1.add(UserInfoButton4);
        // MainPanel1.add(UserInfoButton1);
        // MainPanel1.add(UserInfoTextField1);

        MainPanel2.add(UserInfoButton1);
        MainPanel2.add(UserInfoTextField1);

        JPanel container = new JPanel();
        container.add(MainPanel1);
        // container.add(MainPanel2);

        // 创建文本区域组件
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true); // 自动换行
        textArea.setFont(new Font(null, Font.PLAIN, 18)); // 设置字体

        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(MainPanel1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // scrollPane.setBounds(20, 20, 200,100);
        // scrollPane.getViewport().add(container);
        //
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 400);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setContentPane(scrollPane);
        jf.setVisible(true);
    }

    public void Main3() {

        JButton btn1 = new JButton("one");
        JButton btn2 = new JButton("two");
        JButton btn3 = new JButton("three");
        JButton btn4 = new JButton("four");
        JButton btn5 = new JButton("five");

        JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 400);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(btn1);
        jf.add(btn2);
        jf.add(btn3);
        jf.add(btn4);
        jf.add(btn5);
        jf.setVisible(true);
    }
}
