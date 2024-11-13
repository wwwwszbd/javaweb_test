package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3.调取service接口的方法
        List<Brand> brands = mapper.selectAll();
        //4.释放资源
        sqlSession.close();
        //5.返回集合
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3.调取service接口的方法
        mapper.add(brand);
        //4.释放资源
        sqlSession.close();
    }

    @Override
    public int update(Brand brand) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3.调取service接口的方法
        int update = mapper.update(brand);
        //4.释放资源
        sqlSession.close();
        //5.给返回值
        return update;
    }

    @Override
    public void deleteById(int id) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3.调取service接口的方法
        mapper.deleteById(id);
        //4.释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //3.调取service接口的方法
        mapper.deleteByIds(ids);
        //4.释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.计算
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //4.查询当前页的数据
        List<Brand> rows= mapper.selectByPage(begin, size);

        //5.查询总记录数
        int totalCount = mapper.selectTotalCount();

        //6.把rows与totalCount封装成一个PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //7.释放资源
        sqlSession.close();

        //8.返回值
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //1.获取sqlsession的对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自定提交事务
        //2.获取BrandMapper映射文件
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3.计算,,处理一下brand条件，模糊表达式
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if(brandName != null  && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null  && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }

        //4.查询当前页的数据
        List<Brand> rows= mapper.selectByPageAndCondition(begin, size,brand);

        //5.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //6.把rows与totalCount封装成一个PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //7.释放资源
        sqlSession.close();

        //8.返回值
        return pageBean;
    }
}
