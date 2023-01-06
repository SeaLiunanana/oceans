package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService  brandService = new BrandServiceImpl();



    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        String param = reader.readLine();

        Brand brand = JSON.parseObject(param, Brand.class);
        brandService.add(brand);
        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        String param = reader.readLine();


        int ids[] = JSON.parseObject(param,int[].class);
        brandService.deleteByIds(ids);
        resp.getWriter().write("success");

    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage").trim());

        int pageSize = Integer.parseInt(req.getParameter("pageSize").trim());
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        String jsonStr = JSON.toJSONString(pageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage").trim());

        int pageSize = Integer.parseInt(req.getParameter("pageSize").trim());

        BufferedReader reader = req.getReader();
        String param = reader.readLine();
        Brand brand = JSON.parseObject(param,Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        String jsonStr = JSON.toJSONString(pageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }
}
