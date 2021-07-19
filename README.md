# 欢迎查阅AutomationTestSystem（软件自动化测试系统）
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

![](https://testerhome.com/uploads/photo/2019/fb6cb551-1f0a-4530-a259-f51bac2c9bf4.gif!large)

    AutomationTestSystem是一个多功能自动化测试系统，使用JavaFx编写的Windows应用程序，可用于Appium，Selenium，RestAssured等多框架应用的测试
     • Appium是一个移动端的自动化框架，可用于测试原生应用，移动网页应用和混合型应用，可用于Android和IOS以及Firefox OS等操作系统
     • Selenium是一个用于Web应用程序的自动化测试框架，直接运行在浏览器中，就像真正的用户在操作一样
     • Rest Assured是一套由Java实现的REST API测试框架，它是一个轻量级的REST API 客户端，可以直接编写代码向服务器端发起 HTTP 请求，并验证返回结果
     • 其中最重要的是AutomationTestSystem是跨平台的，何为跨平台，意思就是一套系统兼容多个平台，可以针对不同的平台用一套方法来编写测试用例

### 系统下载：
   百度网盘：https://pan.baidu.com/s/1ZYrihj5AS6V2U3zW1noVlw 提取码: t5nv
   
   腾讯微云：https://share.weiyun.com/s1Gtk5Nt

### 源码地址：
   Gitee地址： [https://gitee.com/hagyao520/AutomationTestSystem.git](https://gitee.com/hagyao520/AutomationTestSystem.git)

   GitHub地址：[https://github.com/hagyao520/AutomationTestSystem.git](https://github.com/hagyao520/AutomationTestSystem.git)

### 系统介绍：
    JavaFx + Appium + Selenium + Rest Assured + JDBC + Atom + Xml + Git + Maven + Ant + TestNG + App Inspector 
     • 使用JavaFx作为项目编程语言
     • 使用Appium作为App项目底层服务驱动框架
     • 使用Selenium作为Web项目底层服务驱动框架
     • 使用RestAssured作为Api项目底层服务驱动框架
     • 使用JDBC作为数据库管理工具，方便连接数据库，执行SQL
     • 使用Atom作为编辑器工具，方便编写Xml测试脚本，维护测试脚本
     • 使用Xml作为脚本管理文件，方便管理测试脚本用例
     • 使用Git作为仓库管理工具，方便管理测试脚本，上传，下载等
     • 使用Maven作为项目类型，方便管理架包
     • 使用Ant作为项目的构建工具，方便测试项目的自动编译，自动打包，自动运行测试脚本
     • 使用TestNG作为项目运行框架，方便执行测试脚本，生成测试报告
     • 使用App Inspector作为Appium的元素定位工具，方便查找和定位元素

### 主要功能：
    1.  实现了可在线下载自动化测试项目(Appium，Selenium，RestAssured)
    2.  实现了基于Appium框架的Android，IOS应用的自动化测试
    3.  实现了基于Selenium框架的Web应用的自动化测试
    4.  实现了基于RestAssured框架的Api接口的自动化测试 
    5.  实现了可以在线编写对象，使用Atom编辑器编写测试对象
    6.  实现了可以在线编写脚本，使用Atom编辑器编写测试脚本
    7.  实现了可以在线上传，下载脚本，实际调用Git命令push和pull
    8.  实现了可以在线运行测试脚本，使用Ant运行build文件，执行脚本
    9.  实现了可以在线生成测试报告，使用TestNG生成，调用ExtentReports进行二次美化，界面更美观，内容清晰
    10. 实现了可以在线发送邮件，自动发送测试报告到对应收件人邮箱，最大支持4个

### 环境配置：
   1. [JDK1.8以上](http://www.Oracle.com/technetwork/Java/javase/downloads/index.html)
   2. [Eclipse](http://www.eclipse.org/downloads)/[IDEA](https://www.jetbrains.com/idea/)
   3. [Appium](https://github.com/appium/appium•desktop/releases/tag/v1.6.3) 
   4. [Android SDK](http://www.androiddevtools.cn) 
   5. [App Inspector](https://macacajs.github.io/app-inspector/zh/) 
   6. [Node](https://nodejs.org/zh•cn/) 
   7. [Gradle](http://services.gradle.org/distributions/) 
   8. [Python](https://www.python.org/downloads/) 
   9. [Maven](http://maven.apache.org/download.cgi) 
   10. [Ant](https://ant.apache.org)
   11. [Atom](https://atom.io/)
   12. [Git](https://git•scm.com/)
   13. [一台安卓手机或者安卓模拟器，推荐使用真机](https://www.yeshen.com)

 • 部分网站需要翻墙，具体安装参考：https://blog.csdn.net/love4399/article/details/77164500

### 安装运行：
    1.  下载完exe系统程序后，双击AutomationTestSystem-1.1.3.exe，点击install按钮，完成安装
    2.  安装完成后，会自动运行程序，注：【系统需安装java环境】
    3.  鼠标右击任务栏中程序图标，打开程序安装位置，进入app目录
    4.  双击【点击生成桌面快捷方式】bat文件，即可自动生成桌面快捷程序，以后点击桌面图标运行即可
    5.  或者下载源码运行，具体使用方法，参考以下内容！

 - 使用说明：
![](https://testerhome.com/uploads/photo/2018/51527078-2165-425a-a457-348e7470b1b9.png!large)

---
### 注意事项：
 - 下载源码工程的项目编码需要设置成UTF-8，否则会出现中文乱码情况

### 一、源码工程，运行启动类，【StartClient.java】
    package AutomationTestSystem;

    import AutomationTestSystem.View.LoginPageView;

    public class StartClient {
    	public static void main(String[] args) {
     	    try {
     		    LoginPageView.main(args);
     	    } catch (Exception e) {
     		    e.printStackTrace();
     	    }
     	}
    }
![](https://images.gitee.com/uploads/images/2019/0123/160410_46aa3d8a_1325509.png)

### 二、系统登录：
 - 正常启动后，系统会自动进入登录界面，如下：

![](https://images.gitee.com/uploads/images/2019/0123/160410_cef3e8be_1325509.png)

 - 点击账号选项框，选择第一个账号，如下：

![](https://images.gitee.com/uploads/images/2019/0123/160410_821e7b47_1325509.png)

 - 点击登录按钮，等待3秒，自动进入系统加载页面，如下：
![](https://images.gitee.com/uploads/images/2019/0123/160410_43731a19_1325509.png)

 - 点击进入系统按钮，进入系统主页面，如下：
![](https://images.gitee.com/uploads/images/2019/0123/160410_f57ebd70_1325509.png)

#### PS：登录环节去掉了账号验证功能，当前选择的账号作为体验者账号，有需要验证的，后面自己新增接口或者数据库验证即可！

### 三、系统主界面：
 - 系统主页是一个自己设计的html的炫酷动画，点击音乐播放按钮，可以播放音视频，目前只集成了一两个，大家有喜欢歌的可以自己修改，集成进去即可。
![](https://testerhome.com/uploads/photo/2019/9e8e133a-8129-458b-a978-eed244dcf9dd.gif!large)

### 四、前端功能中心（需要根据自己的项目设计）：
 - 前端功能中心里面的内容，可以自己定义，主要是前端页面功能，例如一套很复杂的流程需求，可以设计成一个按钮，点击后就可以一键快速实现，自动完成。
![](https://images.gitee.com/uploads/images/2019/0123/160410_d082b021_1325509.gif)

### 五、后端功能中心（暂且未设计，有需要的根据自己的项目设计）：
 - 后端功能中心目前留白，主要是后端页面功能，可以根据测试的需求，自己设计页面功能。
![](https://images.gitee.com/uploads/images/2019/0123/160411_f4d1465e_1325509.png)

### 六、WEB自动化中心（Web网页自动化测试）：
 - WEB自动化中心是我自己设计的一套界面，专门针对与做网页测试的同学，极大的节省了测试的时间，以及重复的工作，让自动化代替人工！
![](https://testerhome.com/uploads/photo/2019/92f1ca34-6787-4f4e-820a-fb2bdc3bc7da.gif!large)
 - 使用流程：
![](https://images.gitee.com/uploads/images/2019/0123/160411_780c8123_1325509.png)
  1. 输入WEB项目工程地址，点击下载按钮，例如：https://gitee.com/hagyao520/Selenium.git
  2. 点击配置文件按钮，选择对应配置文件，例如：src\main\java\common.properties
  3. 点击测试对象按钮，选择对应测试对象，例如：TestCases\SearchProcess.java
  4. 点击测试脚本按钮，选择对应测试脚本，例如：TestCaseXml\SearchProcess.xml
  5. 点击脚本集合按钮，选择对应脚本集合，例如：TestReportXml\TestngReport.xml
  6. 输入对应提交备注，点击脚本上传按钮，即可上传脚本到GIT，实现同步，方便多人协调写脚本
  7. 点击脚本下载按钮，即可下载脚本到本地，实现同步，方便多人协调写脚本
  8. 点击脚本运行按钮，即可在线运行脚本，在脚本运行日志区域，可以看到脚本的运行日志情况
  9. 脚本运行结束后，点击生成报告按钮，即可自动打开浏览器，查看测试报告，需翻墙使用，有提示
  10. 选择邮件方式，输入发送人数，收件人邮箱，点击发送邮件按钮，即可自动发送测试报告到指定收件人邮箱

 - PS:注意测试执行时需要在common.properties文件中，指定浏览器和浏览器配置文件
 - 具体WEB项目框架请参考: https://testerhome.com/topics/13439

### 七、API自动化中心（API接口自动化测试）：
 - API自动化中心是我自己设计的一套界面，专门针对与做API接口测试的同学，极大的节省了测试的时间，以及重复的工作，让自动化代替人工！
![](https://testerhome.com/uploads/photo/2019/9dc2f2c4-dddf-493d-a12d-babac72e3b0e.gif!large)
 - 使用流程：
![](https://images.gitee.com/uploads/images/2019/0123/160412_5e36809c_1325509.png)
  1. 输入API项目工程地址，点击下载按钮，例如：https://gitee.com/hagyao520/RestAssured.git
  2. 点击配置文件按钮，选择对应配置文件，例如：src\main\java\jdbc.properties
  3. 点击测试对象按钮，选择对应测试对象，例如：TestCases\CaptchaTest.java
  4. 点击测试脚本按钮，选择对应测试脚本，例如：TestCaseXls\JMoney.Luckeylink.Api.xlsm
  5. 点击脚本集合按钮，选择对应脚本集合，例如：TestReportXml\TestngReport.xml
  6. 输入对应提交备注，点击脚本上传按钮，即可上传脚本到GIT，实现同步，方便多人协调写脚本
  7. 点击脚本下载按钮，即可下载脚本到本地，实现同步，方便多人协调写脚本
  8. 点击脚本运行按钮，即可在线运行脚本，在脚本运行日志区域，可以看到脚本的运行日志情况
  9. 脚本运行结束后，点击生成报告按钮，即可自动打开浏览器，查看测试报告，需翻墙使用，有提示
  10. 选择邮件方式，输入发送人数，收件人邮箱，点击发送邮件按钮，即可自动发送测试报告到指定收件人邮箱

 - 具体API项目框架请参考: https://testerhome.com/topics/13532

### 八、APP自动化中心（APP功能自动化测试）：
 - APP自动化中心是我自己设计的一套界面，专门针对与做APP功能测试的同学，极大的节省了测试的时间，以及重复的工作，让自动化代替人工！
![](https://testerhome.com/uploads/photo/2019/c4424bf5-1e40-4a55-8fde-90179272cc3e.gif!large)
 - 使用流程：
![](https://images.gitee.com/uploads/images/2019/0123/160412_8e9503e9_1325509.png)
  1. 输入APP项目工程地址，点击下载按钮，例如：https://gitee.com/hagyao520/Appium.git
  2. 选择对应软件APP名称，软件日期等，点击软件下载按钮，例如：微信
  3. 点击测试对象按钮，选择对应测试对象，例如：TestCases\WeChatLogin.java
  4. 点击测试脚本按钮，选择对应测试脚本，例如：TestCaseXls\WeChatLogin.xml
  5. 点击脚本集合按钮，选择对应脚本集合，例如：TestReportXml\TestngReport.xml
  6. 输入对应提交备注，点击脚本上传按钮，即可上传脚本到GIT，实现同步，方便多人协调写脚本
  7. 点击脚本下载按钮，即可下载脚本到本地，实现同步，方便多人协调写脚本
  8. 点击脚本运行按钮，即可在线运行脚本，在Appium运行日志和脚本运行日志区域，可以看到Appium及脚本的运行日志情况
  9. 脚本运行结束后，点击生成报告按钮，即可自动打开浏览器，查看测试报告，需翻墙使用，有提示
  10. 选择邮件方式，输入发送人数，收件人邮箱，点击发送邮件按钮，即可自动发送测试报告到指定收件人邮箱

 - 具体APP项目框架请参考: https://testerhome.com/topics/13401

### 九、性能自动化中心（Android性能监控）：
 - 性能自动化中心是我自己设计的一套界面，专门针对做Android性能监控的同学，可以即时获取手机内存，CPU，网络等数据进行分析，内存泄漏可以随时监控！
![](https://testerhome.com/uploads/photo/2019/460728c5-ee28-4a26-af1f-4486550ad97f.gif!large)
 - 使用流程：
![](https://images.gitee.com/uploads/images/2019/0123/160412_b4d94475_1325509.png)
![](https://images.gitee.com/uploads/images/2019/0123/160413_ee2a4e1c_1325509.png)

  1. 输入insights.py项目工程地址，点击下载按钮，例如：https://github.com/appetizerio/insights.py
  2. 输入insights.py依赖命令，点击安装按钮，例如：python -m pip install -r requirements.txt
  3. 点击账号登录按钮，点击注册账号，填写Appetizer账号信息，注册成功后，下载Appetizer客户端
  4. 点击账号登录按钮，填写已注册的Appetizer账号和密码，点击确认登录，登录成功即可
  5. 点击APK插桩按钮，选择对应本地debug版本的APK，进行插桩，插桩成功后会自动生成process.apk结尾的新APK
  6. 点击获取设备按钮，本地连接手机至USB调试模式，正常连接的情况下，可以获取到手机设备ID信息，选择对应要使用的手机设备ID即可
  7. 点击安装APK按钮，选择已插桩好的APK，例如XXXprocess.apk，安装成功后，启动手机上的APP
  8. 点击开始监控按钮，程序会自动获取对应手机内存，CPU，网络等数据，接口数据统计区域会显示，右侧是图表显示区域
  9. 点击停止监控按钮，可以暂停获取对应手机内存，CPU，网络等数据，并记录开始时间和结束时间，代表这一段时间内容性能信息
   10. 点击上传分析按钮，可以自动上传当前性能数据至Appetizer，登录Appetizer客户端，即可查看具体的性能分析报告

### 十、ATX设备集群化中心（Android多设备在线管理平台）：
 - ATX设备集群化中心是我自己设计的一套界面，专门针对于公司存在多部手机设备时，无法集中管理而开发的平台，可在线管理设备，远程操控，远程监控，获取元素等！
![](https://testerhome.com/uploads/photo/2019/b03c2e9b-6b2d-45c4-9a07-54de09e78bc0.gif!large)
 - 使用流程：
![](https://testerhome.com/uploads/photo/2019/b3813e56-2893-49a0-a9f2-2875cb7a690a.png!large)

&emsp;1.点击开启服务按钮，一键启动rethinkdb，atx-server，WEditor后台服务，点击关闭服务按钮，即可一键关闭所有服务
&emsp;2.点击获取设备按钮，保持手机和电脑之间的连接，并开启Android开发者USB调试模式，可在线一键获取手机设备信息
&emsp;3.点击远程操控按钮，选择谷歌浏览器安装路径，填写对应手机IP，手机UDID，电脑IP，即可一键远程操控设备
&emsp;4.点击远程监控按钮，选择谷歌浏览器安装路径，填写对应手机IP，手机UDID，电脑IP，即可一键远程监控设备
&emsp;5.点击获取元素按钮，填写对应手机IP，点击连接按钮，即可在线远程获取手机元素信息

### 十一、任务栏图标：
 - 该系统继承一般的Windows程序特性，可以最小化，生成任务栏图标，不使用时，可以挂起任务栏，方便使用。
![](https://testerhome.com/uploads/photo/2019/c86e4581-ac57-4dbb-a0d0-63f13ecb929f.gif!large)

### 十二、感谢：
#### 如果您觉得这个框架对您有用，您可以捐赠下我，让我有理由继续下去，非常感谢。
![](https://images.gitee.com/uploads/images/2019/0123/160413_dc7a01d5_1325509.png)

**非常感谢您花费时间阅读，祝您在这里记录、阅读、分享愉快！**
**欢迎留言评论，有问题也可以联系我或者加群交流...**

作者：[@刘智King](http://shang.qq.com/email/stop/email_stop.html?qq=1306086303&sig=a1c657365db7e82805ea4b2351081fc3ebcde159f8ae49b1&tttt=1)         
QQ：1306086303     
Email：hagyao520@163.com

> QQ官方交流群 126325132
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=346d11a1a76d05086cd48bc8249126f514248479b50f96288189ab5ae0ca7ba5"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="软件测试开发交流群" title="软件测试开发交流群"></a>