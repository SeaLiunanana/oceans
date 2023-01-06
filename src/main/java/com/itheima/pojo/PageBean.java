package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    //总记录数
    private int totalCount;
    //当前数据
    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
