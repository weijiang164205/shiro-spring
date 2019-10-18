package cn.innovact.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    private static final Map<String,String> userInfoMap = new HashMap<>();

    static {
        userInfoMap.put("jack","123");
        userInfoMap.put("xdclass","456");
    }
    //role -> permission
    private final Map<String,Set<String>> permissionMap = new HashMap<>();
    {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("video:find");
        set1.add("video:buy");

        set2.add("video:add");
        set2.add("video:delete");

        permissionMap.put("jack",set1);
        permissionMap.put("xdclass",set2);

    }




    //user -> role
    private final Map<String,Set<String>> roleMap = new HashMap<>();
    {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("role1");
        set1.add("role2");

        set2.add("root");

        roleMap.put("jack",set1);
        roleMap.put("xdclass",set2);

    }
    //用户登录的时候调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取用户输入的信息
        String username = (String) token.getPrincipal();
        String password = getPasswordFromDb(username);
        if (password ==null || "".equals(password)){
            return  null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password,this.getName());

        return authenticationInfo;
    }

    private String getPasswordFromDb(String username) {

        return userInfoMap.get(username);
    }

    //权限认证的时候调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = getPermissionsByNameFromDB(username);
        Set<String> roles = getRolesByNameFromDB(username);
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }


    /**
     * 模拟从数据库获取用户角色集合
     * @param name
     * @return
     */
    private Set<String> getRolesByNameFromDB(String name) {
        return roleMap.get(name);

    }

    /**
     *  模拟从数据库获取权限集合
     * @param name
     * @return
     */
    private Set<String> getPermissionsByNameFromDB(String name) {
        return permissionMap.get(name);
    }

}
