package cn.innovact.shiro.dao;

import cn.innovact.shiro.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(@Param("username") String username);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);
    @Select("select * from user where username={username} and password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password")String password);

}
