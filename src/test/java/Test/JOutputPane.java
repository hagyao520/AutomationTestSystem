package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import AutomationTestSystem.Util.Constants;
import AutomationTestSystem.Util.StringUtil;
 
public class JOutputPane extends JTextArea {
  
  public static void main(String[] args) {
    
    SwingUtilities.invokeLater(new Runnable() {
 
      public void run() {
        
        try {
          
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
          
          e.printStackTrace();
        }
        
        JFrame f = new JFrame("Test");
        
        JPanel p = new JPanel(new BorderLayout());
        final JOutputPane a = new JOutputPane();
        a.setRows(10);
        p.add(new JScrollPane(a));
        
//        p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        final JTextField field = new JTextField(100);
        p.add(field, BorderLayout.SOUTH);
        
        final PrintStream out = a.getPrintStream();
        field.addActionListener(new ActionListener() {
 
          public void actionPerformed(ActionEvent ae) {
            
            String s = field.getText();
            field.setText("");
            System.out.println(s);
            out.println(s);
          }
        });
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {// 失去鼠标焦点事件
                String s = field.getText();
                field.setText("");
                System.out.println(s);
                out.println(s);
            }

            @Override
            public void focusGained(FocusEvent e) {// 获得鼠标焦点事件
                String s = field.getText();
                field.setText("");
                System.out.println(s);
                out.println(s);
            }
        });
        
        JPanel control = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        control.add(field);
        control.add(new JButton(new AbstractAction("Clear") {
 
          public void actionPerformed(ActionEvent ae) {
            
            a.setText("");
          }
        }));
        
        p.add(control, BorderLayout.SOUTH);
        
        f.setContentPane(p);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        field.requestFocusInWindow();
      }
    });
  }
  
  private final PrintStream ps;
  
  private Color candy;
  
  public JOutputPane() {
    
    ps = new PrintStream(this.new AreaOutputStream());
//    candy = new Color(230, 230, 255);
    setOpaque(false);
    setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    setForeground(Color.GREEN.darker().darker().darker());
  }
  
  @Override
  public final boolean isEditable() {
    
    return false;
  }
  
  public PrintStream getPrintStream() {
    
    return ps;
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    
    int width = getWidth();
    int height = getHeight();
    
    Color old = g.getColor();
    g.setColor(getBackground());
    g.fillRect(0, 0, width, height);
    
    Rectangle r = new Rectangle();
    r.x = 0;
    r.y = 0;
    r.width = width;
    r.height = getRowHeight();
    
    g.setColor(candy);
    for(int heightIncrement = 2 * getRowHeight(); 
            r.y < height; 
            r.y += heightIncrement) {
      
      g.fillRect(r.x, r.y, r.width, r.height);
    }
    g.setColor(old);
    
    super.paintComponent(g);
  } 
  
  private class AreaOutputStream extends OutputStream {
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    
    @Override
    public void write(int b) {
      
      out.write(b);
//      append(out.toString());
      out.reset();
//      if ('\n' == (char) b) {
//        
//        append(out.toString());
//        out.reset();
//      }
    }
  }
}