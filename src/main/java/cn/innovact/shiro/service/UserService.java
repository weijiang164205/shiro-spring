package cn.innovact.shiro.service;

import cn.innovact.shiro.domain.User;

public interface UserService {
    /**
     * 根据用户名获取信息
     * @param username
     * @return
     */
    public User findByUserName(String username);
    /**
     * 获取全部用户信息，包括角色，权限
     * @param username
     * @return
     */
    User findAllUserInfoByUsername(String username);


    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(int userId);
}
