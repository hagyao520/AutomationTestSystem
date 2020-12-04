package AutomationTestSystem.Util;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 刘智
 * @date: 2018年9月10日 10:35:59
 *
 */ 
 public class SendEmail {
 
    /**
     * @param args
      * @throws Exception 
      */
    public static void main(String[] args) throws Exception {
         
        Properties prop = new Properties();
//         prop.setProperty("mail.smtp.host", "smtp.sohu.com");
         prop.setProperty("mail.transport.protocol", "smtp");
         prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
         //1、创建session
         Session session = Session.getInstance(prop);
         //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
         session.setDebug(true);
         //2、通过session得到transport对象
         Transport ts = session.getTransport();
         //3、连上邮件服务器
//         ts.connect("smtp.qq.com", "1306086303@qq.com", "ticmipgebuznhdcd");
         ts.connect("smtp.163.com", "hagyao520@163.com", "YPFCHNRRCIQWFUBK");
         //4、创建邮件
         Message message = createMixedMail(session, "hagyao520@163.com", "1","1306086303@qq.com","1174863835@qq.com","liuzhi@jiumiaodai.com","1833082791@qq.com","sunline.finline.android.test");
         //5、发送邮件
         ts.sendMessage(message, message.getAllRecipients());
         System.out.println(message.getMessageNumber());
         ts.close();
     }
     
     public static int sendEmail(String Smtp,String MailboxAccount,String MailboxAuthorizationPassword,String SendPeopleNumber,String AddresseeOne,String AddresseeTwo,String AddresseeThree,String AddresseeFour,String EntryName) throws Exception {
        
        Properties prop = new Properties();
//         prop.setProperty("mail.smtp.host", "smtp.sohu.com");
         prop.setProperty("mail.transport.protocol", "smtp");
         prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
         //1、创建session
         Session session = Session.getInstance(prop);
         //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//         session.setDebug(true);
         //2、通过session得到transport对象
         Transport ts = session.getTransport();
         //3、连上邮件服务器
         ts.connect(Smtp, MailboxAccount, MailboxAuthorizationPassword);
         //4、创建邮件
         Message message = createMixedMail(session,MailboxAccount,SendPeopleNumber,AddresseeOne,AddresseeTwo,AddresseeThree,AddresseeFour,EntryName);
         //5、发送邮件
         ts.sendMessage(message, message.getAllRecipients());
//         System.out.println(message.getMessageNumber());
         ts.close();
         
		return message.getMessageNumber();
     }
    
     /**
     * @Method: createMixedMail
     * @Description: 生成一封带附件和带图片的邮件
     * @author: 刘智
     * @date: 2018年9月10日 10:35:59
     *
     * @param session
     * @return
     * @throws Exception
     */ 
     public static MimeMessage createMixedMail(Session Session,String TheSender,String SendPeopleNumber,String AddresseeOne,String AddresseeTwo,String AddresseeThree,String AddresseeFour,String EntryName) throws Exception {
         //创建邮件
         MimeMessage message = new MimeMessage(Session);
         
         //设置邮件的基本信息
         message.setFrom(new InternetAddress(TheSender));//设置发送人
         if("1".equals(SendPeopleNumber)){
        	 message.setRecipient(Message.RecipientType.TO, new InternetAddress(AddresseeOne));//一个收件人
         }else if("2".equals(SendPeopleNumber)){
     		 message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(AddresseeOne),new InternetAddress(AddresseeTwo)});
         }else if("3".equals(SendPeopleNumber)){
     		 message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(AddresseeOne),new InternetAddress(AddresseeTwo),new InternetAddress(AddresseeThree)});
         }else if("4".equals(SendPeopleNumber)){
     		 message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(AddresseeOne),new InternetAddress(AddresseeTwo),new InternetAddress(AddresseeThree),new InternetAddress(AddresseeFour)});
         }
         message.setSubject("【"+EntryName+"】自动化测试报告");//设置邮件主题
         
         Object[] TestResult =GetTestResult(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+EntryName+"\\TestOutput\\TestngReport\\testng-results.xml");
         
         //正文
         MimeBodyPart text = new MimeBodyPart();
         text.setContent("<tr><td><br /><b><font size='4' color='#0000FF'>各位同事，大家好，以下为【"+EntryName+"】自动化测试报告样本，请查收，具体详情请下载附件！</font></b><br>-------------------------------(本邮件是程序自动下发的，请勿回复！)------------------------------</br></td></tr><tr><td><br /><b><font size='5' color='#d98719'>测试结果</font></b><hr size='2' width='100%' align='center' /></td></tr><tr><td><font size='3' <ul><li>测试项目 ：『"+EntryName+"』</li><li>******** 测 试 执 行 结 果 ********</li><li>测试总数量： <font color='#0000FF'>"+TestResult[0]+"</font></li><li>测试通过数量：<font color='#238e23'>"+TestResult[1]+"</font></li><li>测试失败数量：<font color='#FF0000'>"+TestResult[2]+"</font></li><li>测试受阻数量：<font color='#e47833'>"+TestResult[3]+"</font></li><li>测试报告：****具体情况，请下载附件****</li><img src='cid:index.png'><br>PS：请添加151.139.237.11 cdn.rawgit.com到本地C:/Windows/System32/drivers/etc/hosts文件末尾，或者翻墙即可正常打开测试报告，否则会显示乱码！</br></ul> </font></td></tr> ","text/html;charset=UTF-8");
         
         //图片
         MimeBodyPart image = new MimeBodyPart();
         image.setDataHandler(new DataHandler(new FileDataSource("src/main/resources/png/测试报告.png")));
         image.setContentID("index.png");
         
         //附件1
         MimeBodyPart attach = new MimeBodyPart();
         DataHandler dh = new DataHandler(new FileDataSource(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\"+EntryName+"\\TestOutput\\ExtentReport\\index.html"));
         attach.setDataHandler(dh);
         attach.setFileName(dh.getName());
         
//         //附件2
//         MimeBodyPart attach2 = new MimeBodyPart();
//         DataHandler dh2 = new DataHandler(new FileDataSource("TestOutput\\ExtentReport\\index.html"));
//         attach2.setDataHandler(dh2);
//         attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
         
         //描述关系:正文和图片
         MimeMultipart mp1 = new MimeMultipart();
         mp1.addBodyPart(text);
         mp1.addBodyPart(image);
         mp1.setSubType("related");
         
         //描述关系:正文和附件
         MimeMultipart mp2 = new MimeMultipart();
         mp2.addBodyPart(attach);
//         mp2.addBodyPart(attach2);
         
         //代表正文的bodypart
         MimeBodyPart content = new MimeBodyPart();
         content.setContent(mp1);
         mp2.addBodyPart(content);
         mp2.setSubType("mixed");
         
         message.setContent(mp2);
         message.saveChanges();
         
//         message.writeTo(new FileOutputStream("D:\\MixedMail.eml"));
         //返回创建好的的邮件
         return message;
     }
     
     public static Object[] GetTestResult(String soucePath) throws SAXException, IOException{
    	 String total = "";
    	 String passed = "";
    	 String failed = "";
    	 String skipped = "";
    	 DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();  
         try {   
             DocumentBuilder b = a.newDocumentBuilder();  
             Document document = b.parse(soucePath);  
             total = document.getElementsByTagName("testng-results").item(0).getAttributes().getNamedItem("total").getNodeValue();
             passed = document.getElementsByTagName("testng-results").item(0).getAttributes().getNamedItem("passed").getNodeValue();
             failed = document.getElementsByTagName("testng-results").item(0).getAttributes().getNamedItem("failed").getNodeValue();
             skipped = document.getElementsByTagName("testng-results").item(0).getAttributes().getNamedItem("skipped").getNodeValue();
         } catch (ParserConfigurationException e) {  
             e.printStackTrace();  
         }
         Object[] TestResult = {total,passed,failed,skipped};
         return TestResult;
     }
}