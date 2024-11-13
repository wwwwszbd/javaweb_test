package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    //如果将来service层的代码发生了变化，相对应的servlet的代码也得跟着变，而接口不用变化，
    private BrandService brandService = new BrandServiceImpl();


    /**
     * selectAll查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取service实现类中的方法
        List<Brand> brands = brandService.selectAll();

        //2.把service实现类中返回值改成json格式，
        String s = JSON.toJSONString(brands);

        //3.别忘了编码问题，从数据库出来，改成json的格式,并设置data的结果值
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /**
     * 添加数据（暂时没有灵活性的添加数据）
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求行数据（获取json格式的独特方法JavaWeb）
        BufferedReader reader = request.getReader();

        //2.读取请求行数据（json字符串）
        String s = reader.readLine();

        //3.把json格式转为java对象
        Brand brand = JSONObject.parseObject(s, Brand.class);

        //4.调用BrandServiceImpl方法,并且传入数据
        brandService.add(brand);

        //5.相应成功后的数据（如果代码正常执行，给与前端一个相应成功的字符串）
        response.getWriter().write("success");
    }

    /**
     * 删除数据（根据单个的id传入参数，进行传入id）
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求行数据（获取json格式的独特方法JavaWeb）
        BufferedReader reader = request.getReader();

        //2.读取请求行数据（json字符串）
        String s = reader.readLine();

        //3.把json格式转为java对象
        Brand brand = JSONObject.parseObject(s, Brand.class);

        //4.调用BrandServiceImpl方法,并且传入数据
        brandService.deleteById(brand.getId());

        //5.相应成功后的数据（如果代码正常执行，给与前端一个相应成功的字符串）
        response.getWriter().write("success");
    }

    /**
     * 部分数据，和全部数据更新都有了
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求行数据（获取json格式的独特方法JavaWeb）
        BufferedReader reader = request.getReader();

        //2.读取请求行数据（json字符串）
        String s = reader.readLine();

        //3.把json格式转为java对象
        Brand brand = JSONObject.parseObject(s, Brand.class);
        //4.调用BrandServiceImpl方法,并且传入数据
        brandService.update(brand);

        //5.相应成功后的数据（如果代码正常执行，给与前端一个相应成功的字符串）
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求行数据（获取json格式的独特方法JavaWeb)(获取数据形式多种多样)
        BufferedReader reader = request.getReader();

        //2.读取请求行数据（json字符串）
        String s = reader.readLine();

        //3.把json格式转为java对象
        int[] ids = JSONObject.parseObject(s, int[].class);

        //4.调用BrandServiceImpl方法,并且传入数据
        brandService.deleteByIds(ids);

        //5.相应成功后的数据（如果代码正常执行，给与前端一个相应成功的字符串）
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取当前页码handleCurrentChange，和每页展示条数handleSizeChange url?currentPage=1&pageSize=5,把参数放到请求之后
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        //2.把接收的数据，转换成Integer
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //3.调用service进行查询
        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);

        //4.把service实现类中返回值改成json格式，
        String s = JSON.toJSONString(brandPageBean);

        //5.别忘了编码问题，从数据库出来，改成json的格式,并设置data的结果值
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /**
     * 分页动态条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取当前页码handleCurrentChange，和每页展示条数handleSizeChange url?currentPage=1&pageSize=5,把参数放到请求之后
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        //2.把接收的数据，转换成Integer
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);


        //1.获取请求行数据（获取json格式的独特方法JavaWeb）
        BufferedReader reader = request.getReader();

        //2.读取请求行数据（json字符串）
        String s = reader.readLine();

        //3.把json格式转为java对象
        Brand brand = JSONObject.parseObject(s, Brand.class);


        //3.调用service进行查询
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //4.把service实现类中返回值改成json格式，
        String s2 = JSON.toJSONString(brandPageBean);

        //5.别忘了编码问题，从数据库出来，改成json的格式,并设置data的结果值
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s2);
    }
}

