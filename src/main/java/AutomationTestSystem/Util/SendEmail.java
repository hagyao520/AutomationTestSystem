package AutomationTestSystem.Util;

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
         ts.connect("smtp.qq.com", "1306086303@qq.com", "yeuzsyojkvpliddi");
         //4、创建邮件
         Message message = createMixedMail(session, "1306086303@qq.com", "4","1306086303@qq.com","1174863835@qq.com","liuzhi@jiumiaodai.com","1833082791@qq.com","乐客开门");
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
         
         //正文
         MimeBodyPart text = new MimeBodyPart();
         text.setContent("<tr><font size='3'<td>各位同事，大家好，以下为【"+EntryName+"】自动化测试报告样本，请查收，具体详情请下载附件！</td> </font><br>-------------------------------(本邮件是程序自动下发的，请勿回复！)------------------------------</br></tr> <img src='cid:index.png'><br>PS：请添加151.139.237.11 cdn.rawgit.com到本地hosts文件末尾，或者翻墙即可正常打开测试报告，否则会显示乱码！</br>","text/html;charset=UTF-8");
         
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
}