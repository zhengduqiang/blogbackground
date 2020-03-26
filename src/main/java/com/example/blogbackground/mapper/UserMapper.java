package com.example.blogbackground.mapper;

import com.example.blogbackground.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper//这个注解表示这个是mybaits的mapper类：Dao
@Repository
public interface UserMapper {
    /**
     * 异步：注册用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 同步：登录
     *
     * @param user
     * @return
     */
    User findUserLogin(User user);
}
