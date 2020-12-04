package Test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

public class ButtonGroupDemo {
    public static void main(String args[]) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        Border border = BorderFactory.createTitledBorder("Examples");
        panel.setBorder(border);

        ButtonGroup group = new ButtonGroup();
        AbstractButton abstract1 = new JToggleButton("Toggle Button");
        abstract1.setActionCommand("Toggle Button"); 
        panel.add(abstract1);

        group.add(abstract1);
        AbstractButton abstract2 = new JRadioButton("Radio Button");
        abstract2.setActionCommand("Toggle Button2"); 
        panel.add(abstract2);

        group.add(abstract2);
        AbstractButton abstract3 = new JCheckBox("Check Box");
        abstract3.setActionCommand("Toggle Button3"); 
        panel.add(abstract3);

        group.add(abstract3);
        AbstractButton abstract4 = new JRadioButtonMenuItem("Radio Button Menu Item");
        panel.add(abstract4);

        group.add(abstract4);
        AbstractButton abstract5 = new JCheckBoxMenuItem("Check Box Menu Item");
        panel.add(abstract5);
        group.add(abstract5);

        abstract5.addActionListener(new ActionListener() { // 监听确定按钮，JButton类型按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = group.getSelection().getActionCommand();
                System.out.println("选择了****-----: " + choice);
            }
        });
        
        class SexActionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = group.getSelection().getActionCommand();
                System.out.println("选择了****-----: " + choice);
            }
        }
        
        ActionListener alisten = new SexActionListener();
        
        abstract1.addActionListener(alisten);
        abstract2.addActionListener(alisten);
        abstract3.addActionListener(alisten);
        abstract4.addActionListener(alisten);
        
        
        JFrame frame = new JFrame("Button Group");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setVisible(true);
        
        
    }
}
