package Test;


import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager;
import java.util.LinkedHashMap;
import java.util.Map;
public class SwingTheme{
    public JPanel showJP;//用于显示所有的面板 
    //java Swing的8种类主题比较
    public static void main(String[] args){
        SwingTheme swingTheme=new SwingTheme();
        swingTheme.init();
    }
    public void init(){
        JFrame jframe=new JFrame();
        jframe.setSize(800,800);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("Swing主题");
        jframe.setLayout(new BorderLayout()); 
        JPanel mainJP=new JPanel(new BorderLayout());
        jframe.add(mainJP,BorderLayout.CENTER);
        
        showJP=new JPanel(new GridLayout(0,1));
        JScrollPane jsp=new JScrollPane(showJP,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        mainJP.add(jsp,BorderLayout.CENTER);
        //存储10中主题风格
        LinkedHashMap<String,String> themeMap=new LinkedHashMap<String,String>();
        themeMap.put("默认风格","");
        themeMap.put("Metal风格","javax.swing.plaf.metal.MetalLookAndFeel");
        themeMap.put(" Windows风格","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        themeMap.put("Windows Classic风格","com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        themeMap.put("Motif风格","com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //(需要在相关的操作系统上方可实现),win会报错
        themeMap.put("Mac风格","com.sun.java.swing.plaf.mac.MacLookAndFeel");
        //(需要在相关的操作系统上方可实现),win会报错
        themeMap.put("GTK风格","com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        themeMap.put("nimbus风格","com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        themeMap.put("可跨平台的默认风格", UIManager.getCrossPlatformLookAndFeelClassName());
        themeMap.put("当前系统的风格",UIManager.getSystemLookAndFeelClassName());
        //遍历显示所有主题
        for(Map.Entry<String,String> entry:themeMap.entrySet()){
            JPanel returnShowJP=getSwingThemeJP(entry.getKey(),entry.getValue());
            showJP.add(returnShowJP);
        }           
 
        jframe.setVisible(true);
    }
    public static JPanel  getSwingThemeJP(String name,String type){
        JPanel showMainJP=new JPanel(new FlowLayout());
        showMainJP.setPreferredSize(new Dimension(800,150));
        showMainJP.setBorder(BorderFactory.createTitledBorder(name));
        if(!name.equals("默认风格")){
            try{
                UIManager.setLookAndFeel(type);
            }catch(Exception e){
                System.out.println(name+"主题出错");
                e.printStackTrace();
            }
        }
        //加入所有的组件显示测试
        showMainJP.add(new JLabel("标签"));
        showMainJP.add(new JButton("按钮"));
        showMainJP.add(new JRadioButton("单选按钮"));
        showMainJP.add(new JCheckBox("复选框"));
        showMainJP.add(new JToggleButton("开关按钮"));
        showMainJP.add(new JTextField("文本框"));
        showMainJP.add(new JPasswordField("密码框"));
        showMainJP.add(new JTextArea("文本区域"));
        String item[]={"下拉列表框"};
        showMainJP.add(new JComboBox());
        item[0]="列表";
        showMainJP.add(new JList(item));
        showMainJP.add(new JProgressBar(10,100));
        showMainJP.add(new JSlider(10,100,50));
        showMainJP.add(new JTable(2,2));
        item[0]="树";
        showMainJP.add(new JTree(item));
        return showMainJP;
    }   
}
