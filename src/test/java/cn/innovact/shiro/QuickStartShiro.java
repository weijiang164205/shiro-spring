package cn.innovact.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class QuickStartShiro {
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    private  CustomRealm customRealm = new CustomRealm();
    @Before
    public void init(){
        simpleAccountRealm.addAccount("123", "4567");
        defaultSecurityManager.setRealm(customRealm);
    }
    @Test
    public  void test(){
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("jack", "123");
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);

        System.out.println("认证结果"+subject.isAuthenticated());
        System.out.println("角色认证结果"+subject.hasRole("role1"));
        System.out.println("权限认证结果"+subject.isPermitted("video:find"));

    }



}
