package cn.innovact.shiro.service.impl;

import cn.innovact.shiro.dao.RoleMapper;
import cn.innovact.shiro.dao.UserMapper;
import cn.innovact.shiro.domain.Role;
import cn.innovact.shiro.domain.User;
import cn.innovact.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public User findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUserName(username);

        List<Role> roleList = roleMapper.findRoleListByUserId(user.getId());
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }
}
