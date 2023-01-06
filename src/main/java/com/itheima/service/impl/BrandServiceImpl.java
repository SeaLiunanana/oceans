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
  SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();



    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Brand> list = mapper.selectByPage(begin,size);
        int totalCount = mapper.selectTotalCount();

        PageBean<Brand>  pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        String brandName  = brand.getBrandName();
        if(brandName != null && brandName.length() > 0){
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName  = brand.getCompanyName();
        if(companyName != null && companyName.length() > 0){
            brand.setCompanyName("%" + companyName + "%");
        }

        List<Brand> list = mapper.selectByPageAndCondition(begin,size,brand);
        int totalCount = mapper.selectCountByCondition(brand);

        PageBean<Brand>  pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateById(Brand brand) {

            SqlSession sqlSession = sqlSessionFactory.openSession();
            BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

            mapper.updateById(brand);
            sqlSession.commit();
            sqlSession.close();

    }
}
