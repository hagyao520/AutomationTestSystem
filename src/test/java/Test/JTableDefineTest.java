package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.JPanel;
import javax.swing.JButton;
 
public class JTableDefineTest extends JFrame{   
 
     private int currentPage=1;
     private  int pageSize=2;
     private int lastPage;
     JTable table=null;
     DefaultTableModel dtm=null;
     JScrollPane jsp=null;
     JTableDefineTest jTableDefineTest=null;
     List list,list1; 
     JButton button1 =null;
     
     JPanel panel1 = new JPanel();
     JPanel panel2 = new JPanel();
     
    public int getLastPage() {
        return lastPage;
    }
 
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
 
    public int getCurrentPage() {
        return currentPage;
    }
 
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public JTableDefineTest(){  
        
        String[] columnNames = {"用户名","密码"}; 
        dtm=new DefaultTableModel(columnNames, 0);
        
         table=new JTable(dtm);
         jsp = new JScrollPane();
         jsp.setViewportView(table);
//         getContentPane().add(panel2);
    
        setTitle("表格");       
        setBounds(100,100,500,500);       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    
        showTable(currentPage);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        
        JButton button = new JButton("首页");
        button.addActionListener(new MyTable());
        button.setActionCommand("首页");
        panel.add(button);
         button1 = new JButton("上一页");
        button1.addActionListener(new MyTable());
        panel.add(button1);
        JButton button2 = new JButton("下一页");
        button2.addActionListener(new MyTable());
        panel.add(button2);
        JButton button3 = new JButton("末页");
        button3.addActionListener(new MyTable());
        panel.add(button3);
        
        panel1 = new JPanel();
        getContentPane().add(panel1, BorderLayout.CENTER);
        JButton button11 = new JButton("首页1");
        JButton button22 = new JButton("首页2");
        panel1.add(button11);
        panel1.add(button22);
        
        panel2 = new JPanel();
        getContentPane().add(panel2, BorderLayout.AFTER_LAST_LINE);
        JButton button33 = new JButton("首页3");
        JButton button44 = new JButton("首页4");
        panel2.add(button33);
        panel2.add(button44);
        panel2.setVisible(false);
        setVisible(true);    
        
        }   
    
    public void showTable(int currentPage){
        dtm.setRowCount(0);// 清除原有行
        setCurrentPage(currentPage);
        for(int row = 0;row<3;row++)    //获得数据     
            { 
                Vector rowV = new Vector();              
                rowV.add("11");  //数据   
                rowV.add("22"); 
            dtm.addRow(rowV);    
            }   
         
        
    //  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列的自动调整功能。       
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选      
        table.setSelectionBackground(Color.YELLOW);       
        table.setSelectionForeground(Color.RED); 
        table.setRowHeight(30);  
    }
    
    public  void init(){
        
    }
    
    public static void main(String[] args) {        
         new JTableDefineTest();                        
    }
    
    
    class MyTable  implements ActionListener  
    {      
        public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("首页")){
                    panel1.setVisible(true);
                    panel2.setVisible(false);
                }
                
                if(e.getActionCommand().equals("上一页")){
                    if(getCurrentPage()<=1){
                        setCurrentPage(2);
                    }
                    showTable(getCurrentPage()-1);
                }
                
                if(e.getActionCommand().equals("下一页")){
//                    if(getCurrentPage()<getLastPage()){
//                        showTable(getCurrentPage()+1);
//                    }else{
//                        showTable(getLastPage());
//                    }
                    panel1.setVisible(false);
                    panel2.setVisible(true);
                }
                
                if(e.getActionCommand().equals("末页")){
                    showTable(getLastPage());
                }
            }   
        }
    }
