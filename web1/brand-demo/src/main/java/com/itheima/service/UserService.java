package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class UserService{

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();



    public User login(String username, String password){
        //获取SqlSession
        SqlSession sqlsession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.select(username, password);

        sqlsession.close();
        return user;
    }


    //注册方法
    public boolean register(User user){
        //获取SqlSession
        SqlSession sqlsession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        //4.调用方法
        User u = mapper.selectByUsername(user.getUsername());
        if(u == null){
            //不存在则注册
            mapper.add(user);
            sqlsession.commit();

        }
        sqlsession.close();
        return u == null;
    }
}
