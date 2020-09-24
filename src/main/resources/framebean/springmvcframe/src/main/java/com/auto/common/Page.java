package com.auto.common;

import java.util.List;

/**
 * Created by caoxue on 2016/8/30.
 */
public class Page<T> {

    private int currentPage = 1;//当前页
    private int pageSize = 10;//每页个数
    private int prePage;//上一页
    private int nextPage;//下一页
    private int totalRow;//总列数
    private int totalPage;//总页数
    private List<T> result;

    private int offset;//从第几行开始

    public boolean hasNextPage() {
        return this.getPageSize() + 1 <= this.getTotalPage();
    }

    public boolean isLastPage() {
        return !this.hasNextPage();
    }

    public void refresh() {
        if(this.totalRow == 0) {
            this.currentPage = 1;
            this.totalPage = 1;
        } else {
            this.totalPage = this.getTotalPage();
            this.currentPage = this.currentPage < 1?1:this.currentPage;
            this.currentPage = this.currentPage > this.totalPage?this.totalPage:this.currentPage;
        }

        this.nextPage = this.currentPage < this.totalPage?this.currentPage + 1:this.totalPage;
        this.prePage = this.currentPage - 1 > 1?this.currentPage - 1:1;

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 0) {
            this.pageSize = pageSize;
        }

    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        if(totalRow > 0) {
            this.totalRow = totalRow;
        }

    }

    public int getTotalPage() {
        return (int)Math.ceil((double)this.getTotalRow() / (double)this.getPageSize());
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getOffset() {
        int page_index = this.getCurrentPage() - 1;
        page_index = page_index < 0?0:page_index;
        return page_index * this.pageSize;
    }
}
