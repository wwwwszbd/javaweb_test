package com.itheima.web;

import com.itheima.util.CheckCodeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException{
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100,50,os,4);


        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",checkCode);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException{
        this.doGet(request, response);
    }
}
