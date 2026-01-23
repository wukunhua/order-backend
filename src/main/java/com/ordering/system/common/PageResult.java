package com.ordering.system.common;

import java.util.List;

public class PageResult<T> {
    private List<T> list;       // 当前页数据
    private Long total;         // 总条数
    private Integer pageNum;    // 当前页码
    private Integer pageSize;   // 每页条数
    private Integer pages;      // 总页数

    // 构造器、Getter、Setter
    public PageResult(List<T> list, Long total, Integer pageNum, Integer pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = (int) Math.ceil((double) total / pageSize);
    }

    // Getter & Setter
    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }
    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
