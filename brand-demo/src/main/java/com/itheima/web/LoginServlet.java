package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException, ServletException {
        //获取用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        //调用userService
        User user = service.login(username, password);


        String remember = request.getParameter("remember");


        if(user != null){
            //成功

            //判断是否勾选记住我
            if("1".equals(remember)){

                //创建Cookie对象
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                //设置存活时间
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                //发送
                response.addCookie(c_username);
                response.addCookie(c_password);


            }


            //存储session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");
        }else{
            //失败
            //存储失败
            request.setAttribute("login_msg","用户名或密码错误");

            //跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException, ServletException {
        this.doGet(request, response);
    }
}
