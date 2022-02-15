package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;

/**
 * XML代理接口
 */
public interface UserMapper {
    /**
     * 查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户实体类
     */
    User select(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户，用于重名判断
     * @param username 用户名
     * @return 用户实体类
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 添加一个用户
     * @param user 用户实体类
     */
    void add(User user);
}
