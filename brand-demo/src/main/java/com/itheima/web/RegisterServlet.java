package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException, ServletException {
        //获取账号与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        //客户验证码
        String checkCode = request.getParameter("checkCode");
        //生成验证吗
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //比对
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            //no
            return;
        }


        boolean flag = service.register(user);

        if(flag){
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else{
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException, ServletException {
        this.doGet(request, response);
    }
}
