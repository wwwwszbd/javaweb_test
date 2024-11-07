package com.itheima.web.filter;import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;

        //判断资源路径是否是与登录注册相关
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/LoginServlet","/register.jsp","registerServlet","/checkCodeServlet"};
        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();

        for(String u:urls){
            if(url.contains(u)){
                chain.doFilter(request, response);
                return;
            }
        }

        HttpSession session = req.getSession();

        Object user = session.getAttribute("user");

        if (user != null) {
            //放行
            chain.doFilter(request, response);
        }else{
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("login.jsp").forward(req, response);
        }



    }


    public void init(FilterConfig config) throws ServletException {	
    }
    
    public void destroy() {
    }
    

}