package org.example.web;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.util.SqlSessionFactoryUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 注册逻辑处理
 */
@WebServlet(urlPatterns = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        // 接受用户名密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 调用MyBatis完成查询
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectByUsername(username);

        // 判断用户是否存在
        if (u == null) {
            // 用户不存在
            userMapper.add(user);
            // 事务提交
            sqlSession.commit();
            sqlSession.close();
        } else {
            // 用户存在
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("用户名已经存在");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req, resp);
    }
}
