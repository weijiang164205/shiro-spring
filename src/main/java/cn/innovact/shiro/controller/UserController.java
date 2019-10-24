package cn.innovact.shiro.controller;

import cn.innovact.shiro.domain.JsonData;
import cn.innovact.shiro.domain.User;
import cn.innovact.shiro.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("find")
    public JsonData findByUserName(@Param("username") String username){
       // User user = userService.findByUserName(username);
        User user = userService.findAllUserInfoByUsername(username);
        return JsonData.buildSuccess(user);
    }
}
