package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*
    * 查询所有的功能
    * */
    public List<Brand> selectAll() {
        //调用BrandMapper.selectAll()

        //获取SqlSession
        SqlSession sqlsession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlsession.getMapper(BrandMapper.class);

        //调用方法
        List<Brand> brands = mapper.selectAll();

        sqlsession.close();

        return brands;
    };
}
