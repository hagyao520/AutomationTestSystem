package AutomationTestSystem.Util;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RmCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;

/**
 * 类描述：git工具类
 * @author 刘智
 * @date 2018年9月4日 18:10:58
 */
@SuppressWarnings({"unused","static-access"})
public class JGitUtil {

	static String UserName = "xxxxxx";
	static String PassWord  = "xxxxxx";
	
	//克隆仓库
	public static String CloneRepository(String Url, String LocalPath, String UserName, String PassWord) {
        try {
        	CredentialsProvider cp = new UsernamePasswordCredentialsProvider(UserName, PassWord);
        	System.out.println("开始下载："+Url);
            Git git = Git.cloneRepository()
                    .setURI(Url)
                    .setDirectory(new File(LocalPath))
                    .setCloneAllBranches(true)
                    .setCredentialsProvider(cp) //设置权限验证
                    .call();  
            System.out.println("下载成功，保存位置为："+LocalPath);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            String LocalPathName =LocalPath.replace(""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\", "");
            String AlreadyExisted ="Destination path \""+LocalPathName+"\" already exists and is not an empty directory";
            if(AlreadyExisted.equals(e.getMessage())){
            	return "AlreadyExisted";
            }else{
            	return "Error";
            }
        }	
    }
	
    //切换分支
    public void CheckoutBranch(String localPath, String branchName){
        String projectURL = localPath + "\\.git";
        Git git = null;
        try {
            git = Git.open(new File(projectURL));
            git.checkout().setCreateBranch(true).setName(branchName).call();
            git.pull().call();
            System.out.println("切换分支成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("切换分支失败");
        }
    }

    /**
     * 提交本地代码
     * @return 
     */
    public static String GitPush(String localPath, String message, String UserName, String PassWord) {
    	String projectURL = localPath + "\\.git";
    	Git git = null;
        try {
            git = Git.open(new File(projectURL));
            AddCommand addCommand = git.add();
            addCommand.addFilepattern(".");
            addCommand.call();

            RmCommand rmCommand = git.rm();
            rmCommand.addFilepattern(".");
            rmCommand.call();

            CommitCommand commitCommand = git.commit();
            commitCommand.setAll(true);
            commitCommand.setMessage(message);
            commitCommand.setAllowEmpty(true);
            commitCommand.call();

            PushCommand pushCommand = git.push();
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(UserName, PassWord);
            pushCommand.setCredentialsProvider(cp);
            pushCommand.call();
            git.close();
            return "提交成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "提交失败！";
        }
    }

    /**
     * 拉取远程仓库代码
     * @return 
     */
    public static String GitPull(String localPath, String UserName, String PassWord) {
    	String projectURL = localPath + "\\.git";
    	Git git = null;
        try {
            git = Git.open(new File(projectURL));
            PullCommand pullCommand = git.pull();
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(UserName, PassWord);
            pullCommand.setRemoteBranchName("master");
            pullCommand.setCredentialsProvider(cp);
            pullCommand.call();
            git.close();
            return "拉取成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "拉取失败！";
        }
    }
    
    //提交代码
    public static String Commit(String localPath,String pushMessage, String UserName, String PassWord)  {
        String projectURL = localPath + "\\.git";
        Git git = null;
        try {
            git = Git.open(new File(projectURL));
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(UserName, PassWord);
            git.pull().setCredentialsProvider(cp).call();
            Status status = git.status().call();
            if (status.hasUncommittedChanges() == false) {
                return "无已修改脚本！";
            }else{
                //忽略GitUtil.java文件
//                git.add().addFilepattern("GitUtil.java").call();
                git.add().addFilepattern(".").call();
                git.commit().setMessage(pushMessage).call();
                git.pull().setCredentialsProvider(cp).call();
                git.push().setCredentialsProvider(cp).call();

                //查看log信息
//                for(RevCommit revCommit : git.log().call()){
//                    System.out.println(git.log().call());
//                    System.out.println(revCommit.getFullMessage());
//                    System.out.println(revCommit.getCommitterIdent().getName() + " " + revCommit.getCommitterIdent().getEmailAddress());
//                }
            	return "提交成功！";
            }
        } catch (Exception e){
            e.printStackTrace();
        	return "提交失败！";
        } finally{
            if (git != null) {
                git.close();
            }
        }
    }

	public static void main(String[] args) {
        JGitUtil gitUtil = new JGitUtil();
        String url = "http://e-git.yfb.sunline.cn/yht-app/sunline.finline.android.test.git";
        String localPath =""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\Appium";
        String FilePath =""+System.getProperty("user.home")+"\\AppData\\Local\\AutomationTestSystem\\app\\Appium\\src\\test\\java\\TestCases\\LuckeyLogin.java";
        String branchName = "20171010_branch";
        try {
            String dd=gitUtil.CloneRepository(url,localPath,"liuzhi1","lz612425");
            System.out.println(dd);
//            gitUtil.CheckoutBranch(localPath,branchName);
//        	System.out.println(localPath+" "+FilePath);
//            gitUtil.Commit(localPath,"更新","","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}