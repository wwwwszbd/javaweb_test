package com.itheima.pojo;

import java.util.List;

/**
 * 分页查询的的JavaBean，目的是为了给前端提供数据
 */
public class PageBean<T> {


    //总记录数
    private int totalCount;
    //当前页数据,泛型T，是为了更好的适配各种各样的实体
    private List<T> rows;



    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}