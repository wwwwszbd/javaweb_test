package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * dao层添加数据
     *
     * @param brand
     */
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 修改字段（修改全部字段你和修改部分字段），我用的是修改全部字段
     *sql语句写到了映射文件
     * @param brand
     * @return
     */
    int update(Brand brand);

    /**
     * 单个删除
     * @param id
     */
    @Delete("delete from  tb_brand where id = #{id};")
    void deleteById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( @Param("ids")int [] ids);

    /**
     * 因为有两个参数，所以要用param注解，我也不知道为啥(分页查询)
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size")int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select Count(*) from tb_brand")
    int selectTotalCount();

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @param brand
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size")int size,@Param("brand") Brand brand);

    /**
     * 查询总记录数（分页版本）(根据条件查询)
     * @param brand
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}