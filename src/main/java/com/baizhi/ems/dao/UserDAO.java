package com.baizhi.ems.dao;

import com.baizhi.ems.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    //保存用户的方法
    void save(User user);

    //登录的方法
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
