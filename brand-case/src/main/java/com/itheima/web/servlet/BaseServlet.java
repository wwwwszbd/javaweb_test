package com.itheima.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.替换HttpServlet的protected service的方法，使之很具请求最后一段路径名来进行方法分发
 * 2.重写protected service方法准备重写
 */

public class BaseServlet extends HttpServlet {

    /**
     * service的方法是servlet会自动调用的，如果没有复写，就会去调用HttpServlet中的service方法
     * 根据请求的最后一段来进行方法分发
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求路径(获取地址栏输入的url地址路径)，短路径,req.getRequestURL()(长路径)
        String uri = req.getRequestURI(); //uri形式为/Brand-case/brand/selectAll

        //2.截取  整条路经的最后的执行文件（方法名）（截取字符串）
        int index = uri.lastIndexOf("/");//从后往前数 “/” 第一次出现的索引
        String methodName = uri.substring(index+1);//由于结果是  /selectAll带斜杆---我不是很理解

        //3.执行方法
        //3.1 获取BrandServlet/UserServlet的字节码文件 Class
        //this 谁调用我，我代表谁（谁调用this所在的方法，谁就是this，可以是brandServlet，UserServlet，Base的任何子类）
        Class<? extends BaseServlet> cls = this.getClass();

        //3.2获取方法method对象()请求参数
        try {
            //3.2获取方法method对象()请求参数
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);

            //3.3执行方法
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}