package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

public interface BrandService {

    void add(Brand brand);
    void deleteByIds(int ids[]);

    PageBean<Brand> selectByPage(int currentPage, int pageSize);
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    void updateById(Brand brand);
}
